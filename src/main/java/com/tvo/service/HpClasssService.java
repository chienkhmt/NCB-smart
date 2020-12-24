package com.tvo.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tvo.dao.HpClasssInfoDAO;
import com.tvo.model.HpClassInfo;

@Service
public class HpClasssService extends HpTuitionFeeService<HpClassInfo> {
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	@Autowired
	private HpClasssInfoDAO InfoDAO;

	@Override
	protected String TAG() {
		return "class data ";
	}

	public List<HpClassInfo> findDataClass(String schoolCode, String facultyCode, String classCode, int status) {
		List<HpClassInfo> lsClass = new ArrayList<HpClassInfo>();
		try {
			LOGGER.info(TAG() + "filter start");
			HpClassInfo lsInfo = InfoDAO
					.findBySchoolCodeIgnoreCaseAndFacultyCodeIgnoreCaseAndClassCodeIgnoreCaseAndStatusAndDeleteAtIsNull(
							schoolCode.toUpperCase(), facultyCode.toUpperCase(), classCode.toUpperCase(), status);
			LOGGER.info(TAG() + "filter done: " + lsInfo);
			if (lsInfo != null) {
				lsClass.add(lsInfo);
			}
			return lsClass;
		} catch (Exception e) {
			LOGGER.error(TAG() + "filter error: " + e.getMessage());
			e.printStackTrace();
			return lsClass;
		}
	}
}
