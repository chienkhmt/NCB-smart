package com.tvo.controller;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tvo.common.AppConstant;
import com.tvo.model.HpCostInfo;
import com.tvo.model.HpSchoolInfo;
import com.tvo.model.HpStudentInfo;
import com.tvo.response.ResponeData;
import com.tvo.service.HpCostsService;
import com.tvo.service.HpSchoolsService;
import com.tvo.service.HpStudentsService;

@RestController
@RequestMapping(value = "/schools")
public class HpSchoolsController {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private HpSchoolsService schoolsService;

	@GetMapping(value = "/ping")
	public String pingDemo() {
		return "read ok";
	}

	@PostMapping(value = "/save-schools")
	public ResponeData<String> saveSchools(@RequestBody List<HpSchoolInfo> request) {
		ResponeData<String> responeData = new ResponeData<>();
		logger.info("Save costs info start: {}", request);
		if (request == null) {
			responeData.setCode(AppConstant.PARAM_MANAGER_EXISTED_CODE);
			responeData.setDescription(AppConstant.PARAM_MANAGER_EXISTED_MESSAGE);
			responeData.setBody("");
		} else {
			int result = schoolsService.saveData(request);
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

	@PutMapping(value = "/update-school")
	public ResponeData<String> updateSchool(@RequestBody HpSchoolInfo request) {
		ResponeData<String> responeData = new ResponeData<>();
		logger.info("Update cost info start: {}", request);
		if (request == null) {
			responeData.setCode(AppConstant.PARAM_MANAGER_EXISTED_CODE);
			responeData.setDescription(AppConstant.PARAM_MANAGER_EXISTED_MESSAGE);
			responeData.setBody("");
		} else {
			request.setUpdateAt(LocalDate.now());
			int result = schoolsService.updateData(request);
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

	@DeleteMapping(value = "/delete-school")
	public ResponeData<String> deleteSchool(@RequestParam Integer idSchool) {
		ResponeData<String> responeData = new ResponeData<>();
		logger.info("Delete school info start: {}", idSchool);
		if (idSchool == null) {
			responeData.setCode(AppConstant.PARAM_MANAGER_EXISTED_CODE);
			responeData.setDescription(AppConstant.PARAM_MANAGER_EXISTED_MESSAGE);
			responeData.setBody("");
		} else {
			int result = schoolsService.deleteData(idSchool);
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

	@GetMapping(value = "/get-schools")
	public ResponeData<List<HpSchoolInfo>> getSchools(
			@RequestParam(required = false, defaultValue = "") String schooCode,
			@RequestParam(required = false, defaultValue = "0") Integer status,
			@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(name = "offset", required = false, defaultValue = "10") Integer offset) {
		ResponeData<List<HpSchoolInfo>> responeData = new ResponeData<>();
		logger.info("Get school info start - {}|{}|{}|{}", schooCode, status, page, offset);
		List<HpSchoolInfo> lsSchools;
		if (schooCode == null || schooCode.isEmpty()) {
			lsSchools = schoolsService.getData(PageRequest.of(page, offset, Sort.by("id").descending()));
		} else {
			lsSchools = schoolsService.findDataSchool(schooCode, status);
		}
		responeData.setCode(AppConstant.SYSTEM_SUCCESS_CODE);
		responeData.setDescription(AppConstant.SYSTEM_SUCCESS_MESSAGE);
		responeData.setBody(lsSchools);
		return responeData;
	}

}
