package net.changwoo.x1wins.service;

import java.io.IOException;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.changwoo.x1wins.dao.BbsDao;
import net.changwoo.x1wins.dao.FileDao;
import net.changwoo.x1wins.entity.Bbs;
import net.changwoo.x1wins.entity.File;
import net.changwoo.x1wins.web.UserController;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

	private static final Logger logger = LoggerFactory
			.getLogger(FileService.class);

	private static final Exception Exception = null;

	@Autowired
	private FileDao fileDao;

	@Autowired
	private BbsDao bbsDao;
	
	@Transactional
	public File findFile(int snum, String classname, String filename) throws Exception {
		
		List list = fileDao.findFile(snum, classname, filename);
		
		if(list.size()<=0){
			throw new Exception();
		}
		
		File file = (File)list.get(0);
		
		return file;
	}
	
	@Transactional
	public File findFirstFile(int snum, String classname) throws Exception {
		
		File file = fileDao.findFirstFile(snum, classname);
		
		return file;
		
	}
	
	@Transactional
	public List findFileList(String classname, int bbsnum,  int snum, HttpServletRequest request)throws Exception {
		
		List resultList = new ArrayList();

		List paramList = new ArrayList<Map>();

		Map classnameMap = new HashMap();
		
		classnameMap.put("propertyName","classname");
		classnameMap.put("value", classname);
		paramList.add(classnameMap);
		
		Map snumMap = new HashMap();
		snumMap.put("propertyName","snum");
		snumMap.put("value", snum);
		paramList.add(snumMap);
		
		List fileList = fileDao.findAllByProperty(paramList);
		logger.debug("file count "+fileList.size());
		
		
		////file/download/{classname}/{snum}/{filename}.{ext}
		for(int i=0; i<fileList.size(); i++){
			File file = (File)fileList.get(i);
			String filename = file.getFilename();
			String url = request.getContextPath()+"/file/download/"+classname+"/"+bbsnum+"/"+snum+"/"+filename;
			
			int num = file.getNum();
			String filetype = file.getContentType();
			int imageType = filetype.toLowerCase().indexOf("image");
			
			logger.debug("imagetype "+imageType+ " filetype " +filetype);
			
			Map map = new HashMap();
			if(imageType>=0){
				filetype = "image";
			}else{
				filetype = "file";
			}
			
			map.put("num", num);
			map.put("filetype", filetype);
			map.put("url", url);
			map.put("filename", filename);
			map.put("classname", classname);
			resultList.add(map);
			
			logger.debug("file url "+i + " : "+map.get("url"));
			logger.debug("filetype "+i + " : "+map.get("filetype"));
			logger.debug("filename "+i + " : "+map.get("filename"));
		}
		
		return resultList;
	}
	
	@Transactional
	public void delete(int num, String classname, HttpServletRequest request) throws Exception {

		
		validOwn(num, classname, request);
		File file = fileDao.findById(num);
		fileDao.delete(file);
		/*
		HttpSession session = request.getSession(false);
		logger.debug("login id "+session.getAttribute("userid"));
		if (session.getAttribute("userid") != null){
			//login y
			logger.debug("login y ");
			File file = fileDao.findById(num);
			int snum = file.getSnum();
			
			//bbs
			
			String userid = "";
			
			if(classname.equals("bbs")){
				Bbs bbs = bbsDao.findById(snum);
				userid = bbs.getUserid();
			}else if(classname.equals("user")){
				
			}else{
				throw new Exception();
			}
			
			String sessionUserid = session.getAttribute("userid").toString();
			if(userid.equals(sessionUserid)){
				
				fileDao.delete(file);
				
			}else{
				throw new Exception();
			}
			
		}else{
			throw new Exception();
		}
		*/
		
		
	}
	
	
	
	@Transactional
	public void saveFiles(MultipartFile mFiles[], int snum, String classname, HttpServletRequest request) throws Exception{
		
		if(mFiles.length>0){
			for(int i=0; i<mFiles.length; i++){
				if(!mFiles[i].isEmpty()){

					Blob blob = Hibernate.createBlob(mFiles[i].getInputStream());
					File file = new File();
					file.setSnum(snum);
					file.setClassname(classname);
					file.setFilename(mFiles[i].getOriginalFilename());
					file.setContent(blob);
//					file.setContent(mFiles[i].getInputStream());
					file.setContentType(mFiles[i].getContentType());
					fileDao.save(file);
					
				}				
			}
		}
	}
	
	public void validOwn(int num, String classname, HttpServletRequest request) throws Exception {
		
//		boolean result = false;
		
		HttpSession session = request.getSession(false);
//		if (request.getSession(false) != null){
		logger.debug("login id "+session.getAttribute("userid"));
		if (session.getAttribute("userid") != null){
			//login y
			logger.debug("login y ");
			File file = fileDao.findById(num);
			int snum = file.getSnum();
			
			//bbs
			
			String userid = "";
			
			if(classname.equals("bbs")){
				Bbs bbs = bbsDao.findById(snum);
				userid = bbs.getUserid();
			}else if(classname.equals("user")){
				
			}else{
				throw new Exception();
			}
			
			String sessionUserid = session.getAttribute("userid").toString();
			if(!userid.equals(sessionUserid)){
				
				throw new Exception();
			}
			
		}else{
			throw new Exception();
		}
		
//		return result;
	}
	
}

