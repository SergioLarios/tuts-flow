package org.tutsflow.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.tutsflow.model.id.PermissionId;
import org.tutsflow.util.BeanToString;

@Entity
@Table(name = "PERMISSION")
@IdClass(PermissionId.class)
public class Permission extends BeanToString  {

	/* *******************************
	 ******** Table Fields ***********
	 ****************************** */
	
	@Id
	@Column(name = "ACTION", length = 64)
	private String action;
	
	@Id
	@Column(name = "TYPE")
	private int type;
	
	@Column(name = "IS_PUBLIC")
	private boolean isPublic;

	@ElementCollection(targetClass=Long.class, fetch = FetchType.EAGER)
	private List<Long> userIdList;
	
	
	/* *******************************
	 ****** Setters & Getters ********
	 ******************************* */

	public String getAction() { return action; }
	public void setAction(String action) { this.action = action; }

	public int getType() { return type; }
	public void setType(int type) { this.type = type; }

	public boolean isPublic() { return isPublic; }
	public void setPublic(boolean isPublic) { this.isPublic = isPublic; }
	
	public List<Long> getUserIdList() { return userIdList; }
	public void setUserIdList(List<Long> userIdList) { this.userIdList = userIdList; }

}
