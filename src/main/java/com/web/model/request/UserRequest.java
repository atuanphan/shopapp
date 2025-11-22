package com.web.model.request;

import com.web.model.dto.AbstractDTO;

import lombok.Data;

@Data
public class UserRequest extends AbstractDTO{
	private Long id;
	private String password;
	private String userName;
	private String fullName;
	private String email;
	private String phone;
	private String street;
	private String ward;
	private String district;
	private int status;
}
