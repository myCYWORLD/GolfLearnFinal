package com.golflearn.domain.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@NoArgsConstructor 
@AllArgsConstructor 
@Setter @Getter 
@EqualsAndHashCode

@Entity
@Table(name = "qna_comment")
//@IdClass(QnACommentId.class)

@DynamicInsert 
@DynamicUpdate
public class QnACommentEntity {
	@Id
	@Column(name = "qna_cmt_no")
	private Long commentNo;
	
	@Column(name = "qna_cmt_content")	
	private String qnaCmtContent;
	
	@Column(name = "qna_cmt_dt")
	@JsonFormat(pattern = "yy/MM/dd", timezone ="Asia/Seoul")
	@ColumnDefault(value = "SYSDATE")
	private Date qnaCmtDt;
	
	@Column(name = "user_nickname")
	private String userNickname;
//1. 자식쪽 없음
	
	
//2. 자식쪽 1:1	
	@OneToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name = "qna_cmt_no")//insertable = false, updatable = false, nullable = true)
	@MapsId(value =  "boardNo")
	private QnABoardEntity board;
}