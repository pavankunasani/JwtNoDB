package com.spring.jwt.controller;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("access")
public class HelloController {

	@GetMapping("firstPage")
	public String firstPage() {

		return "your are in firstpage " + HttpStatus.OK;
	}

	@GetMapping("secondPage")
	public String secondPage() {

		return "your are in secondPage " + HttpStatus.OK;
	}
}
