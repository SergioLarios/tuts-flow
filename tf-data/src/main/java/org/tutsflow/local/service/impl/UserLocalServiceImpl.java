package org.tutsflow.local.service.impl;

import java.util.List;

import org.apache.commons.collections.IteratorUtils;
import org.apache.log4j.Logger;
import org.tutsflow.local.service.UserLocalService;
import org.tutsflow.model.User;
import org.tutsflow.repository.UserRepository;
import org.tutsflow.util.Validator;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings("unchecked")
public class UserLocalServiceImpl implements UserLocalService {
	
	Logger log = Logger.getLogger(UserLocalServiceImpl.class);
	
	/* *******************************
	 ******* Implementation **********
	 ****************************** */
	
	public User findById(long id) {
		return userRepository.findOne(id);
	}
	
	public void delete(long id) {
		userRepository.delete(id);
	}
	
	public void delete(User user) {
		userRepository.delete(user);
	}
	
	public List<User> findAll() {
		return IteratorUtils.toList(userRepository.findAll().iterator());
	}
	
	public Page<User> findAll(Pageable pageable) {
		return userRepository.findAll(pageable);
	}
	
	public User findByUserNameSimple(String userNameSimple) {
		return userRepository.findByUserNameSimple(userNameSimple);
	}
	
	public User update(User user) {
		return userRepository.save(user);
	}
	
	public User updatePassword(User user, String pssw) {
		String pass = BCrypt.hashpw(pssw, BCrypt.gensalt());
		user.setPassword(pass);
		return userRepository.save(user);
	}
	
	public boolean checkPssw(String userMail, String pssw) {
		try {
			User user = userRepository.findByMail(userMail);
			if (Validator.isNotNull(user)) {
				return BCrypt.checkpw(pssw, user.getPassword());
			}
			else {
				return false;
			}
			
		} catch (Exception e) {
			log.error("Error on -checkPssw(String userMail, String pssw)- ", e);
			return false;
		}
	}

	public boolean existsUserNameSimple(String userNameSimple) {
		boolean exists = true;
		
		try {
			
			User user = userRepository.findByUserNameSimple(userNameSimple);
			if (Validator.isNull(user)) {
				return false;
			}
			
		} catch (Exception e) {
			log.error("Error on -existsUserNameSimple(String userNameSimple)- ", e);
		}
		
		return exists;
	}
	
	public boolean existsUserMail(String userMail) {
		boolean exists = true;
		try {
			User user = userRepository.findByMail(userMail);
			if (Validator.isNull(user)) {
				exists = false;
			}
		} catch (Exception e) {
			log.error("Error on -existsUserMail(String userMail)- ", e);
		}
		return exists;
	}
	
	public User loginUser(String userMail) {
		return userRepository.findByMail(userMail);
	}
	
	/* *******************************
	 ******** Repositories ***********
	 ****************************** */
	
	private UserRepository userRepository;
	
	/* *******************************
	 ********* Constructor ***********
	 ****************************** */
	
	@Autowired
	public UserLocalServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
}
