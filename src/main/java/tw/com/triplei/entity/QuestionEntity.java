package tw.com.triplei.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import tw.com.triplei.commons.GenericEntity;
import tw.com.triplei.enums.QuestionType;

/**
 * 我要提問
 */

@Entity
@Table(name = "QUESTION")
public class QuestionEntity extends GenericEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "ASKER_EMAIL")
	private String askerEmail;

	@Column(name = "CONTENT")
	private String content;

	@Enumerated(EnumType.STRING)
	@Column(name = "QUESTION_TYPE")
	private QuestionType questionType;  // 問題分類
	
	@Column(name = "QUESTION_TYPE2")
	private String questionType2; // 問題分類2 

	@Column(name = "POST_TIME")
	private LocalDateTime postTime;

	@Override
	public String toString() {
		return "QuestionEntity [id=" + id + ", askerEmail=" + askerEmail + ", content=" + content + ", questionType="
				+ questionType + ", questionType2=" + questionType2 + ", postTime=" + postTime + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAskerEmail() {
		return askerEmail;
	}

	public void setAskerEmail(String askerEmail) {
		this.askerEmail = askerEmail;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public QuestionType getQuestionType() {
		return questionType;
	}

	public void setQuestionType(QuestionType questionType) {
		this.questionType = questionType;
	}

	public String getQuestionType2() {
		return questionType2;
	}

	public void setQuestionType2(String questionType2) {
		this.questionType2 = questionType2;
	}

	public LocalDateTime getPostTime() {
		return postTime;
	}

	public void setPostTime(LocalDateTime postTime) {
		this.postTime = postTime;
	}

}
