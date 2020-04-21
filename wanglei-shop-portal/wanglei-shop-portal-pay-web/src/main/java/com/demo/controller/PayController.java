package com.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class PayController {

	@RequestMapping("/aa")
	public String index() {
		return "index";
	}
}
