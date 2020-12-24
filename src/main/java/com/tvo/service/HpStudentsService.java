package com.tvo.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tvo.dao.HpStudentsInfoDAO;
import com.tvo.model.HpStudentInfo;

@Service
public class HpStudentsService extends HpTuitionFeeService<HpStudentInfo> {
	private static final Logger LOGGER = LoggerFactory.getLogger(HpStudentsService.class);
	@Autowired
	private HpStudentsInfoDAO studentsInfoDAO;

	@Override
	protected String TAG() {
		return "students data ";
	}

	@Override
	public List<HpStudentInfo> findDataBy(String dataFind, Pageable pageable) {
		try {
			LOGGER.info(TAG() + "filter start");
			List<HpStudentInfo> lsSchools = studentsInfoDAO
					.findByStudentNameContainingIgnoreCaseAndDeleteAtIsNull(dataFind.toUpperCase(), pageable);
			LOGGER.info(TAG() + "filter done: " + lsSchools);
			return lsSchools;
		} catch (Exception e) {
			LOGGER.error(TAG() + "filter error: " + e.getMessage());
			e.printStackTrace();
			return new ArrayList<HpStudentInfo>();
		}
	}

	@Override
	public List<HpStudentInfo> getData(Pageable pageable) {
		try {
			LOGGER.info(TAG() + "get start");
			List<HpStudentInfo> lsSchools = studentsInfoDAO.findByDeleteAtIsNull(pageable);
			LOGGER.info(TAG() + "get done: " + lsSchools);
			return lsSchools;
		} catch (Exception e) {
			LOGGER.error(TAG() + "get error: " + e.getMessage());
			e.printStackTrace();
			return new ArrayList<HpStudentInfo>();
		}
	}
}
