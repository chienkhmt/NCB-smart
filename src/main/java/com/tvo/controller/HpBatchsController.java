package com.tvo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tvo.common.AppConstant;
import com.tvo.model.HpBatchsInfo;
import com.tvo.response.ResponeData;
import com.tvo.service.HpBatchsService;

@RestController
@RequestMapping(value = "/batchs")
public class HpBatchsController {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private HpBatchsService bathService;

	@GetMapping(value = "/ping")
	public String pingDemo() {
		return "read ok";
	}

	@PostMapping(value = "/save-batchs")
	public ResponeData<String> saveBatchs(@RequestBody List<HpBatchsInfo> request) {
		ResponeData<String> responeData = new ResponeData<>();
		logger.info("Save batchs info start: {}", request);
		if (request == null) {
			responeData.setCode(AppConstant.PARAM_MANAGER_EXISTED_CODE);
			responeData.setDescription(AppConstant.PARAM_MANAGER_EXISTED_MESSAGE);
			responeData.setBody("");
		} else {
			int result = bathService.saveData(request);
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

	@GetMapping(value = "/get-batchs")
	public ResponeData<List<HpBatchsInfo>> getBatchs(
			@RequestParam(required = false, defaultValue = "") String batchCode,
			@RequestParam(required = false, defaultValue = "0") Integer status,
			@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(name = "offset", required = false, defaultValue = "10") Integer offset) {
		ResponeData<List<HpBatchsInfo>> responeData = new ResponeData<>();
		logger.info("Get batchs info start - {}|{}|{}|{}", batchCode, status, page, offset);
		List<HpBatchsInfo> lsBatchsInfos;
		if (batchCode == null || batchCode.isEmpty()) {
			lsBatchsInfos = bathService.getData(PageRequest.of(page, offset, Sort.by("id").descending()));
		} else {
			lsBatchsInfos = bathService.findDataBatchs(batchCode, status);
		}
		responeData.setCode(AppConstant.SYSTEM_SUCCESS_CODE);
		responeData.setDescription(AppConstant.SYSTEM_SUCCESS_MESSAGE);
		responeData.setBody(lsBatchsInfos);
		return responeData;
	}

}
