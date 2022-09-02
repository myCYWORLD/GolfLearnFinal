package com.golflearn.dto;

import java.util.Date;

import com.golflearn.domain.entity.QnABoardEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class QnACommentDto {
	private Long commentNo;
	private String qnaCmtContent;
	private Date qnaCmtDt;
	private String userNickname;
	private QnABoardEntity board;
}
