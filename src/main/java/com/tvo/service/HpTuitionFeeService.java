package com.tvo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tvo.common.HpTuitionFeesInterface;
import com.tvo.dao.HpTuitionFeesDAO;
import com.tvo.model.HpBaseModel;

public abstract class HpTuitionFeeService<T extends HpBaseModel> extends HpTuitionFeesInterface<T> {
	private static final Logger LOGGER = LoggerFactory.getLogger(HpTuitionFeeService.class);

	@Autowired
	private HpTuitionFeesDAO<T> tuitionFeesDAO;

	protected abstract String TAG();

	public Integer saveData(List<T> dataInfo) {
		try {
			LOGGER.info(TAG() + "insert start ");
			List<T> lsData = tuitionFeesDAO.saveAll(dataInfo);
			LOGGER.info(TAG() + "insert done: " + lsData);
			if (lsData == null || lsData.size() == 0) {
				return 1;
			} else {
				return 0;
			}
		} catch (Exception e) {
			LOGGER.error(TAG() + "insert error: " + e.getMessage());
			e.printStackTrace();
			return 1;
		}
	};

	public Integer updateData(T dataInfo) {
		try {
			LOGGER.info(TAG() + "update start");
			T hpDataInfo = tuitionFeesDAO.save(dataInfo);
			LOGGER.info(TAG() + "update done: " + hpDataInfo);
			if (hpDataInfo == null) {
				return 1;
			} else {
				return 0;
			}
		} catch (Exception e) {
			LOGGER.error(TAG() + "update error: " + e.getMessage());
			e.printStackTrace();
			return 1;
		}
	};

	public T getDataById(Integer id) {
		try {
			LOGGER.info(TAG() + "delete start");
			T dataInfo = tuitionFeesDAO.findByIdAndDeleteAtIsNull(id);
			LOGGER.info(TAG() + "delete done: " + dataInfo);
			if (dataInfo == null) {
				return null;
			} else {
				return dataInfo;
			}
		} catch (Exception e) {
			LOGGER.error(TAG() + "delete error: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	};

	public List<T> getData() {
		try {
			LOGGER.info(TAG() + "get start");
			List<T> lsData = tuitionFeesDAO.findByDeleteAtIsNull();
			LOGGER.info(TAG() + "get done: " + lsData);
			return lsData;
		} catch (Exception e) {
			LOGGER.error(TAG() + "get error: " + e.getMessage());
			e.printStackTrace();
			return new ArrayList<T>();
		}
	};

	public Integer deleteData(Integer id) {
		T info = getDataById(id);
		if (info == null) {
			return 1;
		} else {
			LOGGER.info(TAG() + "delete start");
			info.setDeleteAt(LocalDate.now());
			LOGGER.info(TAG() + "delete done");
			return updateData(info);
		}
	};

}
