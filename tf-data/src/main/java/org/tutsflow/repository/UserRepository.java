package org.tutsflow.repository;

import org.tutsflow.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends PagingAndSortingRepository<User, Long>{

	public User findByMail(String mail);
	
	public User findByUserNameSimple(String userNameSimple);
	
	public Page<User> findAll(Pageable pageable);
	
	@Query(value = 
			"select case when count(u) > 0 then true else false end " +
			"from User u " +
			"where u.mail = :mail")
	public boolean existsByMail(@Param("mail") String mail);
	
	@Query(value = 
			"select case when count(u) > 0 then true else false end " +
			"from User u " +
			"where u.userNameSimple = :nameSimple")
	public boolean existsBySimpleName(@Param("nameSimple") String nameSimple);
	
}
