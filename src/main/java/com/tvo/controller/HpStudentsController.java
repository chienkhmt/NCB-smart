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
import com.tvo.model.HpSchoolInfo;
import com.tvo.model.HpStudentInfo;
import com.tvo.response.ResponeData;
import com.tvo.service.HpStudentsService;

@RestController
@RequestMapping(value = "/students")
public class HpStudentsController {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private HpStudentsService studentsService;

	@GetMapping(value = "/ping")
	public String pingDemo() {
		return "read ok";
	}

	@PostMapping(value = "/save-students")
	public ResponeData<String> saveStudents(@RequestBody List<HpStudentInfo> request) {
		ResponeData<String> responeData = new ResponeData<>();
		logger.info("Save students info start: {}", request);
		if (request == null || request.size() < 1) {
			responeData.setCode(AppConstant.PARAM_MANAGER_EXISTED_CODE);
			responeData.setDescription(AppConstant.PARAM_MANAGER_EXISTED_MESSAGE);
			responeData.setBody("");
		} else {
			int result = studentsService.saveData(request);
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

	@PutMapping(value = "/update-student")
	public ResponeData<String> updateStudent(@RequestBody HpStudentInfo request) {
		ResponeData<String> responeData = new ResponeData<>();
		logger.info("Update student info start: {}", request);
		if (request == null) {
			responeData.setCode(AppConstant.PARAM_MANAGER_EXISTED_CODE);
			responeData.setDescription(AppConstant.PARAM_MANAGER_EXISTED_MESSAGE);
			responeData.setBody("");
		} else {
			request.setUpdateAt(LocalDate.now());
			int result = studentsService.updateData(request);
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

	@DeleteMapping(value = "/delete-student")
	public ResponeData<String> deleteStudent(@RequestParam Integer idCost) {
		ResponeData<String> responeData = new ResponeData<>();
		logger.info("Delete student info start: {}", idCost);
		if (idCost == null) {
			responeData.setCode(AppConstant.PARAM_MANAGER_EXISTED_CODE);
			responeData.setDescription(AppConstant.PARAM_MANAGER_EXISTED_MESSAGE);
			responeData.setBody("");
		} else {
			int result = studentsService.deleteData(idCost);
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

	@GetMapping(value = "/get-students")
	public ResponeData<List<HpStudentInfo>> getStudents(@RequestParam(required = false) String schoolName,
			@RequestParam(name = "page", required = false) Integer page,
			@RequestParam(name = "offset", required = false) Integer offset) {
		ResponeData<List<HpStudentInfo>> responeData = new ResponeData<>();
		logger.info("Get students info start - {}|{}|{}", schoolName, page, offset);
		List<HpStudentInfo> lsSchools;
		if (schoolName == null || schoolName.isEmpty()) {
			lsSchools = studentsService.getData(PageRequest.of(page, offset, Sort.by("id").descending()));
		} else {
			lsSchools = studentsService.findDataBy(schoolName,
					PageRequest.of(page, offset, Sort.by("id").descending()));
		}
		responeData.setCode(AppConstant.SYSTEM_SUCCESS_CODE);
		responeData.setDescription(AppConstant.SYSTEM_SUCCESS_MESSAGE);
		responeData.setBody(lsSchools);
		return responeData;
	}

}
