package com.se1404_prx301_hllt.Blog.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.se1404_prx301_hllt.Blog.model.Blog;
import com.se1404_prx301_hllt.Blog.service.StaxService;

@Controller
public class MainController {
	List<Blog> listBlog = new ArrayList<>();
	@Autowired
	StaxService staxService;

	@GetMapping("/")
	public String mainController(Model model) {
		listBlog = staxService.read();
		Collections.reverse(listBlog);
		if (listBlog.size()>6)
			model.addAttribute("listBlog", listBlog.subList(0, 6));
		else {
			model.addAttribute("listBlog", listBlog);
		}
		return "theme/magdesign/index";
	}

	@GetMapping("/allblog")
	public String allBlogController(Model model) {
		listBlog = staxService.read();
		Collections.reverse(listBlog);
		model.addAttribute("listBlog", listBlog);
		return "theme/magdesign/allblog";
	}

	@GetMapping("/blog")
	public String getBlogByIdController(@RequestParam int id, Model model) {
		listBlog = staxService.read();
		Blog blog = staxService.findByID(id);
		model.addAttribute("Blog", blog);
		return "theme/magdesign/blogdetail";
	}

	
	

}
