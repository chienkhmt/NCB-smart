package com.tvo.controller;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tvo.common.AppConstant;
import com.tvo.model.HpClassInfo;
import com.tvo.response.ResponeData;
import com.tvo.service.HpClasssService;

@RestController
@RequestMapping(value = "/clazzs")
public class HpClasssController {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private HpClasssService service;

	@GetMapping(value = "/ping")
	public String pingDemo() {
		return "read ok";
	}

	@PostMapping(value = "/save-clazzs")
	public ResponeData<String> saveClazzs(@RequestBody List<HpClassInfo> request) {
		ResponeData<String> responeData = new ResponeData<>();
		logger.info("Save clazzs info start: {}", request);
		if (request == null) {
			responeData.setCode(AppConstant.PARAM_MANAGER_EXISTED_CODE);
			responeData.setDescription(AppConstant.PARAM_MANAGER_EXISTED_MESSAGE);
			responeData.setBody("");
		} else {
			int result = service.saveData(request);
			if (result == 0) {
				responeData.setCode(AppConstant.SYSTEM_SUCCESS_CODE);
				responeData.setDescription(AppConstant.SYSTEM_SUCCESS_MESSAGE);
				responeData.setBody("");
			} else {
				responeData.setCode(AppConstant.SYSTEM_ERROR_CODE);
				responeData.setDescription(AppConstant.SYSTEM_ERROR_MESSAGE);
				responeData.setBody("");
			}
		}
		return responeData;
	}

	@PutMapping(value = "/update-clazz")
	public ResponeData<String> updateClazz(@RequestBody HpClassInfo request) {
		ResponeData<String> responeData = new ResponeData<>();
		logger.info("Update clazzs info start: {}", request);
		if (request == null) {
			responeData.setCode(AppConstant.PARAM_MANAGER_EXISTED_CODE);
			responeData.setDescription(AppConstant.PARAM_MANAGER_EXISTED_MESSAGE);
			responeData.setBody("");
		} else {
			request.setUpdateAt(LocalDate.now());
			int result = service.updateData(request);
			if (result == 0) {
				responeData.setCode(AppConstant.SYSTEM_SUCCESS_CODE);
				responeData.setDescription(AppConstant.SYSTEM_SUCCESS_MESSAGE);
				responeData.setBody("");
			} else {
				responeData.setCode(AppConstant.SYSTEM_ERROR_CODE);
				responeData.setDescription(AppConstant.SYSTEM_ERROR_MESSAGE);
				responeData.setBody("");
			}
		}
		return responeData;
	}

	@DeleteMapping(value = "/delete-clazz")
	public ResponeData<String> deleteClazz(@RequestParam Integer idRequest) {
		ResponeData<String> responeData = new ResponeData<>();
		logger.info("Delete clazzs info start: {}", idRequest);
		if (idRequest == null) {
			responeData.setCode(AppConstant.PARAM_MANAGER_EXISTED_CODE);
			responeData.setDescription(AppConstant.PARAM_MANAGER_EXISTED_MESSAGE);
			responeData.setBody("");
		} else {
			int result = service.deleteData(idRequest);
			if (result == 0) {
				responeData.setCode(AppConstant.SYSTEM_SUCCESS_CODE);
				responeData.setDescription(AppConstant.SYSTEM_SUCCESS_MESSAGE);
				responeData.setBody("");
			} else {
				responeData.setCode(AppConstant.SYSTEM_ERROR_CODE);
				responeData.setDescription(AppConstant.SYSTEM_ERROR_MESSAGE);
				responeData.setBody("");
			}
		}
		return responeData;
	}

	@GetMapping(value = "/get-clazzs")
	public ResponeData<List<HpClassInfo>> getClazzs(
			@RequestParam(required = false, defaultValue = "") String schoolCode,
			@RequestParam(required = false, defaultValue = "") String facultieCode,
			@RequestParam(required = false, defaultValue = "") String classCode,
			@RequestParam(required = false, defaultValue = "0") Integer status) {
		ResponeData<List<HpClassInfo>> responeData = new ResponeData<>();
		List<HpClassInfo> lsCosts;
		logger.info("Get clazzs info start - {}|{}|{}|{} ", schoolCode, facultieCode, classCode, status);
		if ((schoolCode == null || schoolCode.isEmpty()) && (facultieCode == null || facultieCode.isEmpty())
				&& classCode == null || classCode.isEmpty()) {
			lsCosts = service.getData();
		} else {
			lsCosts = service.findDataClass(schoolCode, facultieCode, classCode, status);
		}
		responeData.setCode(AppConstant.SYSTEM_SUCCESS_CODE);
		responeData.setDescription(AppConstant.SYSTEM_SUCCESS_MESSAGE);
		responeData.setBody(lsCosts);
		return responeData;
	}

	@GetMapping(value = "/detailt")
	public ResponeData<HpClassInfo> getDetailt(@RequestParam(required = false, defaultValue = "0") Integer idClass) {
		ResponeData<HpClassInfo> responeData = new ResponeData<>();
		logger.info("Get clazzs info start - {} ", idClass);
		HpClassInfo hpClass = new HpClassInfo();
		if (idClass == null) {
			responeData.setCode(AppConstant.PARAM_MANAGER_EXISTED_CODE);
			responeData.setDescription(AppConstant.PARAM_MANAGER_EXISTED_MESSAGE);
		} else {
			hpClass = service.getDataById(idClass);
		}
		responeData.setCode(AppConstant.SYSTEM_SUCCESS_CODE);
		responeData.setDescription(AppConstant.SYSTEM_SUCCESS_MESSAGE);
		responeData.setBody(hpClass);
		return responeData;
	}

}
