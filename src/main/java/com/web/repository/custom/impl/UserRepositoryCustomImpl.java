package com.web.repository.custom.impl;

import java.lang.reflect.Field;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.web.entity.UserEntity;
import com.web.model.request.UserRequest;
import com.web.repository.custom.UserRepositoryCustom;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class UserRepositoryCustomImpl implements UserRepositoryCustom{

	@PersistenceContext
	private EntityManager entityManager;
	
	public void queryNormal(UserRequest userRequest, StringBuilder sql) {
		try {
			Field[] fields = UserRequest.class.getDeclaredFields();
			for(Field it : fields) {
				it.setAccessible(true);
				String fieldName = it.getName();
				if(!fieldName.equals("") && fieldName != null) {
					Object value = it.get(userRequest);
					if(value != null) {
						if(it.getType().getName().equals("java.lang.Long") || it.getType().getName().equals("java.lang.Integer")) {
							sql.append(" AND u." + fieldName + "=" + value);
						} else if(it.getType().getName().equals("java.lang.String") && !value.equals("")) {
							sql.append(" AND u." + fieldName + " LIKE '%" + value + "%'");
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Override
	public Page<UserEntity> findAll(UserRequest userRequest, Pageable pageable, int total) {
		StringBuilder sql = new StringBuilder("SELECT u.* FROM user u ");
		StringBuilder where = new StringBuilder(" WHERE 1 = 1 ");
		queryNormal(userRequest, where);
		sql.append(where);
		Query query = entityManager.createNativeQuery(sql.toString(), UserEntity.class);
		query.setFirstResult((int) pageable.getOffset());
		query.setMaxResults(pageable.getPageSize());
		return new PageImpl<UserEntity>(query.getResultList(), pageable, total);
	}

}
