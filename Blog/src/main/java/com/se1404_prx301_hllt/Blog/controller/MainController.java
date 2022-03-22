package com.se1404_prx301_hllt.Blog.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.se1404_prx301_hllt.Blog.model.Blog;
import com.se1404_prx301_hllt.Blog.service.StaxService;

@Controller
public class MainController {
	List<Blog> listBlog = new ArrayList<>();
	@Autowired
	StaxService s;

	@GetMapping("/")
	public String mainController(Model model) {
		System.out.println("======init======>" + listBlog);
		read();
		System.out.println("======read======>" + listBlog);
//		Blog blog=new Blog("1","2","3","4","5","6","7","8");
//		create(blog);
//		System.out.println("======create======>"+ listBlog);
//		update(blog);
//		System.out.println("======update======>"+ listBlog);
//		delete(2);
//		System.out.println("======delete======>"+ listBlog);
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

	public Boolean create(Blog blog) {
		try {
			int index = listBlog.get(listBlog.size() - 1).getId() + 1;
			blog.setId(index);
			LocalDateTime myDateObj = LocalDateTime.now();
			DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String formattedDate = myDateObj.format(myFormatObj);
			blog.setDate(formattedDate);
			listBlog.add(blog);
			s.writeData(listBlog);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public List<Blog> read() {
		listBlog = s.getListBlog();
		return listBlog;
	}

	public Boolean update(Blog blog) {
		try {
			for (int i = 0; i < listBlog.size(); i++)
				if (listBlog.get(i).getId() == blog.getId()) {
					listBlog.get(i).setTitle(blog.getTitle());
					listBlog.get(i).setCategory(blog.getCategory());
					listBlog.get(i).setSortDescription(blog.getSortDescription());
					listBlog.get(i).setLongDescription(blog.getLongDescription());
					listBlog.get(i).setDate(blog.getDate());
					listBlog.get(i).setImage(blog.getImage());
					listBlog.get(i).setAuthorName(blog.getAuthorName());
					listBlog.get(i).setAuthorPosition(blog.getAuthorPosition());
					listBlog.get(i).setAuthorImage(blog.getAuthorImage());
					s.writeData(listBlog);
					return true;
				}
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	public Boolean delete(int id) {
		try {
			for (int i = 0; i < listBlog.size(); i++)
				if (listBlog.get(i).getId() == id) {
					listBlog.remove(i);
					s.writeData(listBlog);
					return true;
				}
			return false;
		} catch (Exception e) {
			return false;
		}
	}

}
