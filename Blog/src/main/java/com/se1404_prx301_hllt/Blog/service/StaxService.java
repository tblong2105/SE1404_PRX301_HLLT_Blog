package com.se1404_prx301_hllt.Blog.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import com.se1404_prx301_hllt.Blog.model.Blog;


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
						break;
					}
					System.out.println("=======> item "+blog);
				}

				System.out.println("====list=====> "+listBlogs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listBlogs;
	}
	
}
