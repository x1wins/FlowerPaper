package net.changwoo.x1wins.web;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import net.changwoo.x1wins.entity.Document;
import net.changwoo.x1wins.service.DocumentService;

import org.apache.commons.io.IOUtils;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/doc")
public class DocumentController {
	
	@Autowired
	private DocumentService documentService;
	
	@RequestMapping("/index")
	public String index(Map<String, Object> map) {
		try {
			map.put("document", new Document());
			map.put("documentList", documentService.list());
		}catch(Exception e) {
			e.printStackTrace();
		}

		return "doc/documents.tiles";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@Valid
			@ModelAttribute("document") Document document, BindingResult result,
			@RequestParam("file") MultipartFile file) {
		
		
		if (result.hasErrors()) {
			return "doc/documents.tiles";
		}
		
		System.out.println("Name:" + document.getName());
		System.out.println("Desc:" + document.getDescription());
		System.out.println("File:" + file.getName());
		System.out.println("ContentType:" + file.getContentType());
		
		try {
			Blob blob = Hibernate.createBlob(file.getInputStream());

			document.setFilename(file.getOriginalFilename());
			document.setContent(blob);
			document.setContentType(file.getContentType());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			documentService.save(document);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/doc/index";
	}

	@RequestMapping("/download/{documentId}")
	public String download(@PathVariable("documentId")
			Integer documentId, HttpServletResponse response) {
		
		Document doc = documentService.get(documentId);
		try {
			response.setHeader("Content-Disposition", "inline;filename=\"" +doc.getFilename()+ "\"");
			OutputStream out = response.getOutputStream();
			response.setContentType(doc.getContentType());
			IOUtils.copy(doc.getContent().getBinaryStream(), out);
			out.flush();
			out.close();
		
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}

	@RequestMapping("/remove/{documentId}")
	public String remove(@PathVariable("documentId")
			Integer documentId) {
		
		documentService.remove(documentId);
		
		return "redirect:/doc/index";
	}
	
}
