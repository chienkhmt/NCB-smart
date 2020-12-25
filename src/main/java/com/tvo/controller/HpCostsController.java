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
import com.tvo.model.HpClassInfo;
import com.tvo.model.HpCostInfo;
import com.tvo.model.HpSchoolInfo;
import com.tvo.model.HpStudentInfo;
import com.tvo.response.ResponeData;
import com.tvo.service.HpCostsService;
import com.tvo.service.HpSchoolsService;
import com.tvo.service.HpStudentsService;

@RestController
@RequestMapping(value = "/costs")
public class HpCostsController {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private HpCostsService costsService;

	@GetMapping(value = "/ping")
	public String pingDemo() {
		return "read ok";
	}

	@PostMapping(value = "/save-costs")
	public ResponeData<String> saveCosts(@RequestBody List<HpCostInfo> request) {
		ResponeData<String> responeData = new ResponeData<>();
		logger.info("Save costs info start: {}", request);
		if (request == null) {
			responeData.setCode(AppConstant.PARAM_MANAGER_EXISTED_CODE);
			responeData.setDescription(AppConstant.PARAM_MANAGER_EXISTED_MESSAGE);
			responeData.setBody("");
		} else {
			int result = costsService.saveData(request);
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

	@PutMapping(value = "/update-cost")
	public ResponeData<String> updateCost(@RequestBody HpCostInfo request) {
		ResponeData<String> responeData = new ResponeData<>();
		logger.info("Update cost info start: {}", request);
		if (request == null) {
			responeData.setCode(AppConstant.PARAM_MANAGER_EXISTED_CODE);
			responeData.setDescription(AppConstant.PARAM_MANAGER_EXISTED_MESSAGE);
			responeData.setBody("");
		} else {
			request.setUpdateAt(LocalDate.now());
			int result = costsService.updateData(request);
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

	@DeleteMapping(value = "/delete-cost")
	public ResponeData<String> deleteCost(@RequestParam Integer idCost) {
		ResponeData<String> responeData = new ResponeData<>();
		logger.info("Delete cost info start: {}", idCost);
		if (idCost == null) {
			responeData.setCode(AppConstant.PARAM_MANAGER_EXISTED_CODE);
			responeData.setDescription(AppConstant.PARAM_MANAGER_EXISTED_MESSAGE);
			responeData.setBody("");
		} else {
			int result = costsService.deleteData(idCost);
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

	@GetMapping(value = "/get-costs")
	public ResponeData<List<HpCostInfo>> getCosts(@RequestParam(required = false) String codeCost) {
		ResponeData<List<HpCostInfo>> responeData = new ResponeData<>();
		List<HpCostInfo> lsCosts;
		logger.info("Get costs info start: " + codeCost);
		if (codeCost == null || codeCost.isEmpty()) {
			lsCosts = costsService.getData();
		} else {
			lsCosts = costsService.findDataBy(codeCost);
		}
		responeData.setCode(AppConstant.SYSTEM_SUCCESS_CODE);
		responeData.setDescription(AppConstant.SYSTEM_SUCCESS_MESSAGE);
		responeData.setBody(lsCosts);
		return responeData;
	}

	@GetMapping(value = "/detailt")
	public ResponeData<HpCostInfo> getDetailt(@RequestParam(required = false, defaultValue = "0") Integer idCost) {
		ResponeData<HpCostInfo> responeData = new ResponeData<>();
		logger.info("Get cost info start - {} ", idCost);
		HpCostInfo hpInfo = new HpCostInfo();
		if (idCost == null) {
			responeData.setCode(AppConstant.PARAM_MANAGER_EXISTED_CODE);
			responeData.setDescription(AppConstant.PARAM_MANAGER_EXISTED_MESSAGE);
		} else {
			hpInfo = costsService.getDataById(idCost);
		}
		responeData.setCode(AppConstant.SYSTEM_SUCCESS_CODE);
		responeData.setDescription(AppConstant.SYSTEM_SUCCESS_MESSAGE);
		responeData.setBody(hpInfo);
		return responeData;
	}

}
