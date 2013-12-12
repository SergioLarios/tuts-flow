package org.tutsflow.controlpanel.form;

import org.tutsflow.util.BeanToString;
import org.tutsflow.web.FormUtils.Param;

public class UserEditForm extends BeanToString {

	/* *******************************
	 ********* Form Fields ***********
	 ****************************** */
	
	@Param(name= ID)
	private long id;
	
	@Param(name = MAIL)
	private String mail;
	
	@Param(name = NAME)
	private String name;
	
	@Param(name = NAME_SIMPLE)
	private String nameSimple;
	
	@Param(name = TYPE)
	private int type;
	
	@Param(name = PASSWORD)
	private String pssw;
	
	@Param(name = PASSWORD_REPEAT)
	private String psswRepeat;
	
	
	/* *******************************
	 ********* Form Names ************
	 ****************************** */
	
	public static final String ID = "cp_edit_id";
	public static final String MAIL = "cp_edit_usermail";
	public static final String NAME = "cp_edit_name";
	public static final String NAME_SIMPLE = "cp_edit_name_simple";
	public static final String TYPE = "cp_edit_user_type";
	public static final String PASSWORD = "cp_edit_password";
	public static final String PASSWORD_REPEAT = "cp_edit_password_repeat";

	/* *******************************
	 ****** Setters & Getters ********
	 ******************************* */
	
	public String getMail() { return mail; }
	public void setMail(String mail) { this.mail = mail; }
	
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	
	public String getNameSimple() { return nameSimple; }
	public void setNameSimple(String nameSimple) { this.nameSimple = nameSimple; }
	
	public int getType() { return type; }
	public void setType(int type) { this.type = type; }
	
	public String getPssw() { return pssw; }
	public void setPssw(String pssw) { this.pssw = pssw; }
	
	public String getPsswRepeat() { return psswRepeat; }
	public void setPsswRepeat(String psswRepeat) { this.psswRepeat = psswRepeat; }
	
	public long getId() { return id; }
	public void setId(long id) { this.id = id; }
	
}
