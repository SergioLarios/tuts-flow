package org.tutsflow.repository;

import org.tutsflow.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, Long>{

	public User findByMail(String mail);
	
	public User findByUserNameSimple(String userNameSimple);
	
	public Page<User> findAll(Pageable pageable);
	
}
