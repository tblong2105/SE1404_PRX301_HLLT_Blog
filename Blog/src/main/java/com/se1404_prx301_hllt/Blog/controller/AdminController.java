package com.se1404_prx301_hllt.Blog.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.se1404_prx301_hllt.Blog.model.Blog;
import com.se1404_prx301_hllt.Blog.service.StaxService;

@Controller
public class AdminController {
	List<Blog> listBlog = new ArrayList<>();
	@Autowired
	private StaxService staxService;
	
	@GetMapping("/allBlogAdmin")
	public String adminController(Model model) {
		listBlog = staxService.getListBlog();
		System.err.println(listBlog);
		model.addAttribute("listBlog",listBlog);
		return "theme/admin/allBlogAdmin";
	}
	
	
	@GetMapping("/addBlogAdmin")
	public String addBlogController(@ModelAttribute("blog") Blog blog, ModelMap modelMap, Model model) {
		return "theme/admin/addBlogAdmin";
	}
	
	 @PostMapping("/addBlog")
	public String addBlog(@ModelAttribute("blog") Blog blog, @RequestParam("author-image") MultipartFile authorImage, @RequestParam("blog-image") MultipartFile blogImage, ModelMap modelMap, Model model) {
		 listBlog = staxService.getListBlog();
		 modelMap.addAttribute("blog", blog);
		 staxService.create(blog, blogImage, authorImage);
		return "redirect:/addBlogAdmin";
	}
	
	@GetMapping("/editBlogAdmin")
	public String editBlogController(Model model) {
		return "theme/admin/editBlogAdmin";
	}
	
	
	@RequestMapping(value = "/delete_blog", method = RequestMethod.GET)
	public String handleDeleteUser(@RequestParam(name="id")int id) {
		staxService.delete(id);
	    return "redirect:/allBlogAdmin";
	}
	
	
	
	
}
