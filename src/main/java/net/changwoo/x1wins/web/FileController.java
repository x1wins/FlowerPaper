package net.changwoo.x1wins.web;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.changwoo.x1wins.entity.File;
import net.changwoo.x1wins.service.BbsService;
import net.changwoo.x1wins.service.FileService;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/file")
public class FileController {
	
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

	@Autowired
	private FileService fileService;

	@Autowired
	private BbsService bbsService;

	// http://localhost:8080/x1wins/file/download/portfolio/2/marry.PNG

	@RequestMapping("/download/{classname}/{bbsnum}/{snum}/{filename}.{ext}")
	public String download(@PathVariable("classname") String classname,
			@PathVariable("bbsnum") int bbsnum,
			@PathVariable("snum") int snum,
			@PathVariable("filename") String filename,
			@PathVariable("ext") String ext,
			HttpServletRequest request ,HttpServletResponse response)  {

		filename = filename + "." + ext;
		logger.info("snum : "+snum+" classname : "+classname+" filename : "+filename);
		
		try {
			
			if(classname.equals("bbs")){
				bbsService.validRead(bbsnum, request);
			}
			
			File file = fileService.findFile(snum, classname, filename);
			logger.info("file?");
			response.setHeader("Content-Disposition", "inline;filename=\""
					+ file.getFilename() + "\"");
			OutputStream out = response.getOutputStream();
			response.setContentType(file.getContentType());
			IOUtils.copy(file.getContent().getBinaryStream(), out);
//			IOUtils.copy(file.getContent(), out);
			out.flush();
			out.close();

		} catch (Exception e) {
			logger.debug(e.toString());
			try {
				response.sendRedirect(request.getContextPath()+"/images/pic1.jpg");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				logger.debug(e1.toString());
			}
		}

		return null;
	}
	
	@RequestMapping("/image/{classname}/{bbsnum}/{snum}")
	public String showFirstImage(@PathVariable("classname") String classname,
			@PathVariable("snum") int snum,
			@PathVariable("bbsnum") int bbsnum,
			HttpServletRequest request ,HttpServletResponse response) throws IOException  {
	
		logger.debug("image");
		
		try {
			
			if(classname.equals("bbs")){
				bbsService.validRead(bbsnum, request);
			}
			
			File file = fileService.findFirstFile(snum, classname);
			
			if(file == null){
				logger.debug("file is null");
				response.sendRedirect(request.getContextPath()+"/images/pic1.jpg");
//				return "redirect:/images/pic1.jpg";
			}else{
				response.setHeader("Content-Disposition", "inline;filename=\""
						+ file.getFilename() + "\"");
				OutputStream out = response.getOutputStream();
				response.setContentType(file.getContentType());
				IOUtils.copy(file.getContent().getBinaryStream(), out);
//			IOUtils.copy(file.getContent(), out);
				out.flush();
				out.close();
			}
			

		} catch (Exception e) {
			logger.debug(e.toString());
			//				response.sendRedirect(request.getContextPath()+"/images/pic1.jpg");
			return "redirect:/images/pic1.jpg";
		}

		return null;
	}

	//http://localhost:8080/x1wins/file/delete/bbs/2
	@RequestMapping("/delete/{classname}/{num}")
	public String delete(@PathVariable("num") int num, @PathVariable("classname") String classname, Map model, HttpServletRequest request)  {

		int result = 1;
		try{
			
			fileService.delete(num, classname, request);
			
		} catch (Exception e) {
			logger.debug(e.toString());
			result = 0;
		}
		
		model.put("result",result);

		return "jsonView";
	}

}
