package com.trs.shiro.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.trs.shiro.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	@Query(value = "select * from user where name=?1 and password=?2", nativeQuery = true)
	public User findByNameAndPassword(String name, String password);
	
	@Query(value="select * from user where name=?1",nativeQuery=true)
	public User findByName(String name);

}
