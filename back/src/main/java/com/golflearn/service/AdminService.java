package com.golflearn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.golflearn.domain.AdminRepository;
import com.golflearn.dto.LessonLine;
import com.golflearn.dto.PageBean;
import com.golflearn.dto.UserInfo;
import com.golflearn.exception.FindException;

@Service(value = "adminService")
public class AdminService {
	private static final int CNT_PER_PAGE = 5;

	@Autowired
	private AdminRepository adminRepo;

	/**
	 * 페이지별 유저 목록과 페이지 그룹 정보를 반환
	 * @param currentPage 검색할 페이지
	 * @return
	 * @throws FindException
	 */
	public PageBean<UserInfo> userInfoList(int currentPage) throws FindException{

		List<UserInfo> userList = adminRepo.selectByUserPage(currentPage, CNT_PER_PAGE);
		int totalCnt = adminRepo.selectCountAllUser();

		int cntPerPageGroup = 5;

		PageBean<UserInfo> pu = new PageBean<>(userList, totalCnt, currentPage, cntPerPageGroup, CNT_PER_PAGE);

		return pu;
	}

	public PageBean<UserInfo> userInfoByType(int currentPage, int userType) throws FindException {

		List<UserInfo> userTypeList = adminRepo.selectByUserType(currentPage, CNT_PER_PAGE, userType);

		int totalCnt = adminRepo.selectCountUserType(userType);
		int cntPerPageGroup = 5;

		PageBean<UserInfo> pu = new PageBean<>(userTypeList, totalCnt, currentPage, cntPerPageGroup, CNT_PER_PAGE);

		return pu;

	}

	public UserInfo userInfoDetail(String userId) throws FindException {
		UserInfo userDetail = adminRepo.selectUserDetail(userId);
		return userDetail;
	}

	public PageBean<LessonLine> userLesson(int currentPage, int userType, String userId) throws FindException {

		List<LessonLine> userLessonList = adminRepo.selectUserLesson(currentPage, currentPage, userType);

		int totalCnt= 0;
		
		if(userType == 0) {
			totalCnt = adminRepo.selectCountStdtLesson(userId);
		}else if(userType == 1) {
			totalCnt = adminRepo.selectCountProLesson(userId); 
		}
		int cntPerPageGroup = 5;
		PageBean<LessonLine> pu = new PageBean<>(userLessonList, totalCnt, currentPage, cntPerPageGroup, CNT_PER_PAGE);

		return pu;
	}






}
