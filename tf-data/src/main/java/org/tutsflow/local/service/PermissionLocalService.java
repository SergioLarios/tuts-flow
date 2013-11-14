package org.tutsflow.local.service;

import java.util.List;

import org.tutsflow.model.Permission;
import org.tutsflow.model.id.PermissionId;

public interface PermissionLocalService {
	
	/**
	 * Updates or creates a new permission
	 * @param entry
	 * @return
	 */
	public Permission updatePermission(Permission entry);
	
	/**
	 * Tries to find a permission by its id, if no value is found then null is returned
	 * @param id
	 * @return
	 */
	public Permission findById(PermissionId id);
	
	/**
	 * Finds by action and type
	 * @param action
	 * @param type
	 * @return
	 */
	public List<Permission> findByActionAndType(String action, int type);
	
	/**
	 * Finds by action and type
	 * @param action
	 * @param type
	 * @return
	 */
	public List<Permission> findByType(int type);
	
}
