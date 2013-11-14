package org.tutsflow.repository;

import java.util.List;

import org.tutsflow.model.Permission;
import org.tutsflow.model.id.PermissionId;
import org.springframework.data.repository.CrudRepository;


public interface PermissionRepository extends CrudRepository<Permission, PermissionId>{

	List<Permission> findByActionAndTypeLike(String action, int type);
	
	List<Permission> findByType(int type);
}
