package net.changwoo.app.bbs.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.changwoo.app.bbs.entity.Bbs;

public interface IBbsService {
	public void saveBbs(Bbs bbs, int bbsnum, HttpServletRequest request) throws Exception ;
	public void updateBbs(Bbs bbs, int bbsnum, HttpServletRequest request) throws Exception ;
	public void delete(int bbsnum, int num)throws Exception ;
	public void findListAndPaging(int bbsnum, int pageNum, int perPage, Map model, HttpServletRequest request)throws Exception ;
	public Bbs findDetail(int num)throws Exception ;
	public boolean validSignin(int bbsnum, HttpServletRequest request) throws Exception;
	public boolean validOwn(int num, HttpServletRequest request) throws Exception;
	public boolean validRead(int bbsnum, HttpServletRequest request) throws Exception;
	public boolean validWrite(int bbsnum, HttpServletRequest request) throws Exception;
}
