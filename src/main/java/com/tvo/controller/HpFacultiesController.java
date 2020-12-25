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
import com.tvo.model.HpCostInfo;
import com.tvo.model.HpFacultieInfo;
import com.tvo.response.ResponeData;
import com.tvo.service.HpFacultiesService;

@RestController
@RequestMapping(value = "/faculties")
public class HpFacultiesController {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private HpFacultiesService service;

	@GetMapping(value = "/ping")
	public String pingDemo() {
		return "read ok";
	}

	@PostMapping(value = "/save-faculties")
	public ResponeData<String> saveFaculties(@RequestBody List<HpFacultieInfo> request) {
		ResponeData<String> responeData = new ResponeData<>();
		logger.info("Save faculties info start: {}", request);
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

	@PutMapping(value = "/update-facultie")
	public ResponeData<String> updateFacultie(@RequestBody HpFacultieInfo request) {
		ResponeData<String> responeData = new ResponeData<>();
		logger.info("Update faculties info start: {}", request);
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

	@DeleteMapping(value = "/delete-facultie")
	public ResponeData<String> deleteFacultie(@RequestParam Integer idRequest) {
		ResponeData<String> responeData = new ResponeData<>();
		logger.info("Delete faculties info start: {}", idRequest);
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

	@GetMapping(value = "/get-faculties")
	public ResponeData<List<HpFacultieInfo>> getFaculties(
			@RequestParam(required = false, defaultValue = "") String codeSchool,
			@RequestParam(required = false, defaultValue = "") String codeFacultie,
			@RequestParam(required = false, defaultValue = "0") Integer status) {
		ResponeData<List<HpFacultieInfo>> responeData = new ResponeData<>();
		List<HpFacultieInfo> lsCosts;
		logger.info("Get faculties info start - {}|{}|{} " + codeSchool, codeFacultie, status);
		if ((codeSchool == null || codeSchool.isEmpty()) && (codeFacultie == null || codeFacultie.isEmpty())) {
			lsCosts = service.getData();
		} else {
			lsCosts = service.findDataFacultie(codeSchool, codeFacultie, status);
		}
		responeData.setCode(AppConstant.SYSTEM_SUCCESS_CODE);
		responeData.setDescription(AppConstant.SYSTEM_SUCCESS_MESSAGE);
		responeData.setBody(lsCosts);
		return responeData;
	}

	@GetMapping(value = "/detailt")
	public ResponeData<HpFacultieInfo> getDetailt(@RequestParam(required = false, defaultValue = "0") Integer idCost) {
		ResponeData<HpFacultieInfo> responeData = new ResponeData<>();
		logger.info("Get faculty info start - {} ", idCost);
		HpFacultieInfo hpInfo = new HpFacultieInfo();
		if (idCost == null) {
			responeData.setCode(AppConstant.PARAM_MANAGER_EXISTED_CODE);
			responeData.setDescription(AppConstant.PARAM_MANAGER_EXISTED_MESSAGE);
		} else {
			hpInfo = service.getDataById(idCost);
		}
		responeData.setCode(AppConstant.SYSTEM_SUCCESS_CODE);
		responeData.setDescription(AppConstant.SYSTEM_SUCCESS_MESSAGE);
		responeData.setBody(hpInfo);
		return responeData;
	}

}
