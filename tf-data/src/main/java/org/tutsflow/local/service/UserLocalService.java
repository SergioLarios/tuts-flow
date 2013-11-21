package org.tutsflow.local.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.tutsflow.model.User;


public interface UserLocalService {

	/**
	 * Return the user given its id
	 * @param id
	 * @return
	 */
	public User findById(long id);
	
	/**
	 * Deletes the user given its id
	 * @param id
	 */
	public void delete(long id);
	
	/**
	 * Deletes the user given the object
	 * @param user
	 */
	public void delete(User user);
	
	/**
	 * Returns all users
	 * @return
	 */
	public List<User> findAll();
	
	/**
	 * Returns all users paginated
	 * @return
	 */
	public Page<User> findAll(Pageable pageable);
	
	/**
	 * Returns a User given its simple name
	 * @return
	 */
	public User findByUserNameSimple(String userNameSimple);
	
	/**
	 * Updates or creates the user given the object
	 * @param user
	 * @return
	 */
	public User update(User user);
	
	/**
	 * Updates or creates the user given the object and sets the password (BCrypt.hash)
	 * @param user
	 * @param pssw
	 * @return
	 */
	public User updatePassword(User user, String pssw);
	
	/**
	 * Checks the user mail with the password
	 * @param userMail
	 * @param pssw
	 * @return
	 */
	public boolean checkPssw(String userMail, String pssw);
	
	/**
	 * Checks if the user already exists (returns true if there is an error)
	 * @param userMail
	 * @return
	 */
	public boolean existsUserMail(String userMail);
	
	/**
	 * Checks if the user name simple already exists (returns true if there is an error)
	 * @param userMail
	 * @return
	 */
	public boolean existsUserNameSimple(String userNameSimple);
	
	/**
	 * Returns the Object User given it's mail.
	 * @param userMail
	 * @return
	 */
	public User loginUser(String userMail);
}
