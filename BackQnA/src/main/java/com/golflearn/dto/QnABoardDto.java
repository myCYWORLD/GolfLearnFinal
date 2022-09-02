package com.golflearn.dto;

import java.util.Date;

import com.golflearn.domain.entity.QnACommentEntity;

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
public class QnABoardDto {
	private Long boardNo;
	private String boardTitle;
	private String boardContent;
	private Date qnaBoardDt;
	private int qnaBoardSecret;
	private QnACommentEntity comment;
}
