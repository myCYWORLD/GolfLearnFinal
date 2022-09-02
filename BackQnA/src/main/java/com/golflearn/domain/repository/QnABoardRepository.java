package com.golflearn.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.golflearn.domain.entity.QnABoardEntity;

public interface QnABoardRepository extends JpaRepository <QnABoardEntity, Long> {
//	List<QnABoardEntity> findByAll(org.springframework.data.domain.Pageable paging);
	//목록 불러오기
	@Query(value ="SELECT *\r\n"
			+"	   FROM(\r\n"
			+" 	   	     SELECT rownum r, a.*\r\n"
			+"    		 FROM (SELECT qna_board_no, qna_board_title, qna_board_dt, user_nickname\r\n"
			+" 				   FROM qna_board \r\n"
			+" 				   ORDER BY qna_board_no DESC\r\n"
			+" 					) a\r\n"
			+" 			  ) \r\n"
			+"      WHERE r BETWEEN ?1 AND ?2"
			, nativeQuery=true)
	List<QnABoardEntity> findByPage(int startRow, int endRow);
	
	//공개글인것만 불러오기(qna_board_secret이 0인것)
	@Query(value = "SELECT *\r\n"
			+ "		FROM( SELECT rownum r, a.*\r\n"
			+ "     	   FROM (\r\n"
			+ "           		 SELECT qna_board_no, qna_board_title, qna_board_dt, user_nickname\r\n"
			+ "            		 FROM qna_board  \r\n"
			+ "            		 WHERE qna_board_secret ='0' \r\n"
			+ "            		 ORDER BY qna_board_dt DESC\r\n"
			+ "            		)a\r\n"
			+ "   		 )\r\n"
			+ "		WHERE r BETWEEN ?1 AND ?2"
			, nativeQuery=true)
	List<QnABoardEntity> findByOpenPost(int startRow, int endRow);
	
	//상세보기
	@Query(value = "SELECT qb.*, qc.*\r\n"
			+ "   FROM qna_board qb LEFT JOIN qna_comment qc\r\n"
			+ "   ON(qb.qna_board_no = qc.qna_cmt_no)\r\n"
			+ "   WHERE qb.qna_board_no = ?1"
			, nativeQuery=true)
	List<QnABoardEntity> findDetail(Long qnaBoardNo);
	
	//작성자로 검색하기
	@Query(value = "SELECT *\r\n"
			+ "     FROM (SELECT rownum r, a.*\r\n"
			+ "           FROM (SELECT *\r\n"
			+ "                 FROM qna_board\r\n"
			+ "                 WHERE user_nickname LIKE'%?1%'\r\n"
			+ "                 ORDER BY qna_board_dt DESC;\r\n"
			+ "		            )a\r\n"
			+ "     WHERE r BETWEEN ?2 AND ?3"
			, nativeQuery=true)
	List<QnABoardEntity>findByNickname(String nickName, int startRow, int endRow);
	
}
