package com.se1404_prx301_hllt.Blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping("/")
	public String mainController(Model model) {
		return "theme/magdesign/index";
	}
}
