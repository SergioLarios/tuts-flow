package org.tutsflow.model.id;

import java.io.Serializable;

import org.tutsflow.util.BeanToString;

public class PermissionId extends BeanToString implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/* *******************************
	 ****** Table Fields Ids *********
	 ****************************** */
	private String action;
	private int type;
	
	/* *******************************
	 ********** Constructors *********
	 ******************************* */
	
	public PermissionId() {}
	
	public PermissionId(String action, int type) {
		this.action = action;
		this.type = type;
	}
	
	/* *******************************
	 ****** Setters & Getters ********
	 ******************************* */
	
	public String getAction() { return action; }
	public void setAction(String action) { this.action = action; }
	
	public int getType() { return type; }
	public void setType(int type) { this.type = type; }
	
	/* *******************************
	 ****** Overridden methods *******
	 ******************************* */
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		result = prime * result + type;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		
		PermissionId other = (PermissionId) obj;
		if (action == null) {
			if (other.action != null) return false;
		}
		else if (!action.equals(other.action)) return false;
		if (type != other.type) return false;
		return true;
	}
}
