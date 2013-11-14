package org.tutsflow.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.tutsflow.model.Permission;
import org.tutsflow.model.id.PermissionId;
import org.tutsflow.parent.DatabaseConectionTest;
import org.tutsflow.repository.PermissionRepository;

public class PermissionRepositoryTest extends DatabaseConectionTest {

	Logger log = Logger.getLogger(PermissionRepositoryTest.class);
	
	@Autowired
	PermissionRepository permissionRepository;
	
	@Test(expected = JpaSystemException.class)
	public void primaryKeyExceptionTest() {
		Permission newPermission = new Permission();
		permissionRepository.save(newPermission);
	}
	
	
	@Test
	public void permissionTests() {
		
		String testString= String.valueOf(new Date().getTime());
		String actionName = "TestAction"+testString;
		int permissionType = 99;
		
		Permission permission = new Permission();
		permission.setAction(actionName);
		permission.setType(permissionType);
		permission.setPublic(true);
		List<Long> userIdList = new ArrayList<Long>();userIdList.add(1L);userIdList.add(2L);
		permission.setUserIdList(userIdList);
		
		log.info("**** Saving the test object ****");
		Permission newPermission = permissionRepository.save(permission);
		
		assertEquals(actionName, newPermission.getAction());
		assertEquals(permissionType, newPermission.getType());
		assertTrue(permission.isPublic());
		
		PermissionId permissionId = new PermissionId(actionName, permissionType);
		newPermission = null;
		log.info("**** Testing we can find it again ****");
		newPermission = permissionRepository.findOne(permissionId);
		
		assertNotNull(newPermission);
		
		log.info("**** Deleting the test object ****");
		permissionRepository.delete(newPermission);
		
	}
	
}
