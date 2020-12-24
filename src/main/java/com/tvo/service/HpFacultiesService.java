package com.tvo.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tvo.dao.HpFacultiesInfoDAO;
import com.tvo.model.HpFacultieInfo;

@Service
public class HpFacultiesService extends HpTuitionFeeService<HpFacultieInfo> {
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	@Autowired
	private HpFacultiesInfoDAO InfoDAO;

	@Override
	protected String TAG() {
		return "faculties data ";
	}

	public List<HpFacultieInfo> findDataFacultie(String schoolCode, String facultieCode, int status) {
		List<HpFacultieInfo> lsFaculties = new ArrayList<HpFacultieInfo>();
		try {
			LOGGER.info(TAG() + "filter start");
			HpFacultieInfo lsInfo = InfoDAO
					.findBySchoolCodeIgnoreCaseAndFacultyCodeIgnoreCaseAndStatusAndDeleteAtIsNull(
							schoolCode.toUpperCase(), facultieCode.toUpperCase(), status);
			LOGGER.info(TAG() + "filter done: " + lsInfo);
			if (lsInfo != null) {
				lsFaculties.add(lsInfo);
			}
			return lsFaculties;
		} catch (Exception e) {
			LOGGER.error(TAG() + "filter error: " + e.getMessage());
			e.printStackTrace();
			return lsFaculties;
		}
	}

}
