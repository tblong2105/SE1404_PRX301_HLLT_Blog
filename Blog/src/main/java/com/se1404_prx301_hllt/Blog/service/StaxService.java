package com.se1404_prx301_hllt.Blog.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.se1404_prx301_hllt.Blog.model.Blog;

@Service
public class StaxService {
	private static String XML_FILE_NAME = "blog.xml";

	public List<Blog> getListBlog() {

		List<Blog> listBlogs = new ArrayList<>();
		Blog blog = null;
		String tagContent = null;

		File inputFile = new File(XML_FILE_NAME);
		InputStream is;
		try {
			is = new FileInputStream(inputFile);

			XMLInputFactory factory = XMLInputFactory.newInstance();
			XMLStreamReader reader = factory.createXMLStreamReader(is);

			while (reader.hasNext()) {
				int event = reader.next();

				switch (event) {
				case XMLStreamConstants.START_ELEMENT:
					if ("blog".equals(reader.getLocalName())) {
						blog = new Blog();
						blog.setId(Integer.parseInt(reader.getAttributeValue(0)));
					}
					break;

				case XMLStreamConstants.CHARACTERS:
					tagContent = reader.getText().trim();
					break;

				case XMLStreamConstants.END_ELEMENT:
					switch (reader.getLocalName()) {
					case "blog":
						listBlogs.add(blog);
						break;
					case "title":
						System.err.println(tagContent);
						blog.setTitle(tagContent);
						break;
					case "category":
						blog.setCategory(tagContent);
						break;
					case "sortDescription":
						blog.setSortDescription(tagContent);
						break;
					case "longDescription":
						blog.setLongDescription(tagContent);
						break;
					case "date":
						blog.setDate(tagContent);
						break;
					case "image":
						blog.setImage(tagContent);
						break;
					case "authorName":
						blog.setAuthorName(tagContent);
						break;
					case "authorPosition":
						blog.setAuthorPosition(tagContent);
						break;
					case "authorImage":
						blog.setAuthorImage(tagContent);
						break;
					}
					tagContent = "No-Data";
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listBlogs;
	}

	public void writeData(List<Blog> listBlogs) {
		XMLOutputFactory factory = XMLOutputFactory.newInstance();
		XMLEventFactory eventFactory = XMLEventFactory.newInstance();
		try {
			XMLEventWriter writer = factory.createXMLEventWriter(new FileWriter("blog.xml"));
			XMLEvent event = eventFactory.createStartDocument();
			writer.add(event);
			event = eventFactory.createStartElement("", "", "blog_list");
			writer.add(event);
			for (Blog blog : listBlogs) {

				event = eventFactory.createStartElement("", "", "blog");
				writer.add(event);
				event = eventFactory.createAttribute("id", blog.getId() + "");
				writer.add(event);

				event = eventFactory.createStartElement("", "", "title");
				writer.add(event);
				event = eventFactory.createCharacters(blog.getTitle());
				writer.add(event);
				event = eventFactory.createEndElement("", "", "title");
				writer.add(event);

				event = eventFactory.createStartElement("", "", "category");
				writer.add(event);
				event = eventFactory.createCharacters(blog.getCategory());
				writer.add(event);
				event = eventFactory.createEndElement("", "", "category");
				writer.add(event);

				event = eventFactory.createStartElement("", "", "sortDescription");
				writer.add(event);
				event = eventFactory.createCharacters(blog.getSortDescription());
				writer.add(event);
				event = eventFactory.createEndElement("", "", "sortDescription");
				writer.add(event);

				event = eventFactory.createStartElement("", "", "longDescription");
				writer.add(event);
				event = eventFactory.createCharacters(blog.getLongDescription());
				writer.add(event);
				event = eventFactory.createEndElement("", "", "longDescription");
				writer.add(event);

				event = eventFactory.createStartElement("", "", "date");
				writer.add(event);
				event = eventFactory.createCharacters(blog.getDate());
				writer.add(event);
				event = eventFactory.createEndElement("", "", "date");
				writer.add(event);

				event = eventFactory.createStartElement("", "", "image");
				writer.add(event);
				event = eventFactory.createCharacters(blog.getImage());
				writer.add(event);
				event = eventFactory.createEndElement("", "", "image");
				writer.add(event);

				event = eventFactory.createStartElement("", "", "authorName");
				writer.add(event);
				event = eventFactory.createCharacters(blog.getAuthorName());
				writer.add(event);
				event = eventFactory.createEndElement("", "", "authorName");
				writer.add(event);

				event = eventFactory.createStartElement("", "", "authorPosition");
				writer.add(event);
				event = eventFactory.createCharacters(blog.getAuthorPosition());
				writer.add(event);
				event = eventFactory.createEndElement("", "", "authorPosition");
				writer.add(event);

				event = eventFactory.createStartElement("", "", "authorImage");
				writer.add(event);
				event = eventFactory.createCharacters(blog.getAuthorImage());
				writer.add(event);
				event = eventFactory.createEndElement("", "", "authorImage");
				writer.add(event);

				event = eventFactory.createEndElement("", "", "blog");
				writer.add(event);
			}
			event = eventFactory.createEndElement("", "", "blog_list");
			writer.add(event);
			writer.flush();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public Boolean create(Blog blog, MultipartFile blogImage, MultipartFile authorImage) {
		try {
			List<Blog> listBlog = getListBlog();
			
			int index = listBlog.get(listBlog.size() - 1).getId() + 1;
			blog.setId(index);
			LocalDateTime myDateObj = LocalDateTime.now();
			DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String formattedDate = myDateObj.format(myFormatObj);
			blog.setDate(formattedDate);
			
			String bImage = StringUtils.cleanPath(blogImage.getOriginalFilename());
			saveImage(blogImage, index);
			blog.setImage(index + bImage);
			
			String aImage = StringUtils.cleanPath(authorImage.getOriginalFilename());
			saveImage(authorImage, index);
			blog.setAuthorImage(index + aImage);
			
			listBlog.add(blog);
			writeData(listBlog);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private final Path root = Paths.get("src/main/resources/static/img");
	
	public void saveImage(MultipartFile file, int id) {
	    try {
	      Files.copy(file.getInputStream(), this.root.resolve(id + file.getOriginalFilename()));
	    } catch (Exception e) {
	      throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
	    }
	  }
	
	public Boolean delete(int id) {
		List<Blog> listBlog = getListBlog();
		try {
			for (int i = 0; i < listBlog.size(); i++)
				if (listBlog.get(i).getId() == id) {
					listBlog.remove(i);
					writeData(listBlog);
					return true;
				}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public List<Blog> read() {
		List<Blog> listBlog = getListBlog();
		listBlog = getListBlog();
		return listBlog;
	}
	
	public Boolean update(Blog blog) {
		List<Blog> listBlog = getListBlog();
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
					writeData(listBlog);
					return true;
				}
			return false;
		} catch (Exception e) {
			return false;
		}
	}

}
