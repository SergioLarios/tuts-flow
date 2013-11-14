package org.tutsflow.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.tutsflow.util.BeanToString;

@Entity
@Table(name = "USER_")
public class User extends BeanToString {

	/* *******************************
	 ******** Table Fields ***********
	 ****************************** */
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "USER_ID")
	private long userId;
	
	@Column(name = "USER_NAME", length = 32, unique = true)
	private String userName;
	
	@Column(name = "USER_NAME_SIMPLE", length = 32, unique = true)
	private String userNameSimple;
	
	@Column(name = "USER_MAIL", length = 64, unique = true)
	private String mail;

	@Column(name = "USER_PSSW", length = 60)
	private String password;

	@Column(name = "TYPE")
	private int type;
	
	/* *******************************
	 ****** Setters & Getters ********
	 ******************************* */
	
	public long getUserId() { return userId; }
	public void setUserId(long userId) { this.userId = userId; }

	public String getUserName() { return userName; }
	public void setUserName(String userName) { this.userName = userName; }

	public String getUserNameSimple() { return userNameSimple; }
	public void setUserNameSimple(String userNameSimple) { this.userNameSimple = userNameSimple; }
	
	public String getMail() { return mail; }
	public void setMail(String mail) { this.mail = mail; }
	
	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }
	
	public int getType() { return type; }
	public void setType(int type) { this.type = type; }
	
}
