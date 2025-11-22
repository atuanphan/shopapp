package com.web.model.response;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.web.model.dto.AbstractDTO;

import lombok.Data;

@Data
@Component
public class PageResponse<T> extends AbstractDTO {

	private List<T> result = new ArrayList<>();
}
