package com.tvo.common;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.tvo.model.HpBaseModel;

public class HpTuitionFeesInterface<T extends HpBaseModel> {
	public List<T> findDataBy(String dataFind, Pageable pageable) {
		return null;
	};

	public List<T> findDataBy(String dataFind) {
		return null;
	};

	public List<T> getData(Pageable pageable) {
		return null;
	};
}
