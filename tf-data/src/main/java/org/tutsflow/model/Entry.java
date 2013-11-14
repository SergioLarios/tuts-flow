package org.tutsflow.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.tutsflow.util.BeanToString;

@Entity
@Table(name = "ENTRY_")
public class Entry extends BeanToString{

	/* *******************************
	 ******** Table Fields ***********
	 ****************************** */
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ENTRY_ID")
	private long id;
	
	@Column(name = "USER_ID", nullable = false)
	private long userId;
	
	@Column(name = "ENTRY_TITLE")
	private String title;
	
	@Column(name = "ENTRY_SUMMARY")
	@Lob
	private String summary;
	
	@Column(name = "ENTRY_CONTENT", nullable = false )
	@Lob
	private String content;

	/* *******************************
	 ****** Setters & Getters ********
	 ******************************* */
	
	public long getId() { return id; }
	public void setId(long id) { this.id = id; }

	public long getUserId() { return userId; }
	public void setUserId(long userId) { this.userId = userId; }

	public String getTitle() { return title; }
	public void setTitle(String title) { this.title = title; }

	public String getSummary() { return summary; }
	public void setSummary(String summary) { this.summary = summary; }

	public String getContent() { return content; }
	public void setContent(String content) { this.content = content; }
	
}
