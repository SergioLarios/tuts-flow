package org.tutsflow.local.service.impl;

import java.util.List;

import org.tutsflow.local.service.PermissionLocalService;
import org.tutsflow.model.Permission;
import org.tutsflow.model.id.PermissionId;
import org.tutsflow.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionLocalServiceImpl implements PermissionLocalService{
	

	/* *******************************
	 ******* Implementation **********
	 ****************************** */
	
	@Override
	public Permission updatePermission(Permission entry) {
		return prePermissionRepository.save(entry);
	}

	@Override
	public Permission findById(PermissionId id) {
		return prePermissionRepository.findOne(id);
	}
	
	@Override
	public List<Permission> findByActionAndType(String action, int type) {
		return prePermissionRepository.findByActionAndTypeLike(action, type);
	}
	
	@Override
	public List<Permission> findByType(int type) {
		return prePermissionRepository.findByType(type);
	}

	
	/* *******************************
	 ******** Repositories ***********
	 ****************************** */
	
	private PermissionRepository prePermissionRepository;
	
	/* *******************************
	 ********* Constructor ***********
	 ****************************** */
	
	@Autowired
	public PermissionLocalServiceImpl(PermissionRepository prePermissionRepository) {
		this.prePermissionRepository = prePermissionRepository;
	}

	
}
