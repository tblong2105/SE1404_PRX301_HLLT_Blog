package com.se1404_prx301_hllt.Blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

	@GetMapping("/")
	public String mainController(Model model) {
		return "theme/magdesign/index";
	}
	
	@GetMapping("/allblog")
	public String allBlogController(Model model) {
		return "theme/magdesign/allblog";
	}
	
	
	@GetMapping("/blog")
	public String getBlogByIdController(@RequestParam int id, Model model) {
		return "theme/magdesign/blogdetail";
	}
}
