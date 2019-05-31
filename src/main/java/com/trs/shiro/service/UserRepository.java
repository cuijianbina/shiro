package com.trs.shiro.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.trs.shiro.domain.Role;
import com.trs.shiro.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	@Query(value = "select * from user where name=?1 and password=?2", nativeQuery = true)
	public User findByNameAndPassword(String name, String password);
	
	@Query(value="select * from user where name=?1",nativeQuery=true)
	public User findByName(String name);
	
	/**
	 * Description:查询该用户的所有角色
	 *
	 * @param username
	 * @return
	 * @author cui.jianbin  
	 * @date 2019年5月31日  下午3:33:30
	 * @version 1.0
	 */
	@Query(value="select r.name from user u,user_role ur,role r where u.id=ur.uid and ur.rid=r.id and u.name=?1",nativeQuery=true)
	public List<String> findRolesByUsername(String username);

	
	/**
	 * Description:查询该用户的所有权限
	 *
	 * @param username
	 * @return
	 * @author cui.jianbin  
	 * @date 2019年5月31日  下午3:44:05
	 * @version 1.0
	 */
	@Query(value="select  p.name from user_role ur,role_permission rp,permission p,user u where u.name=? and u.id=ur.uid and ur.rid=rp.rid and rp.pid=p.id",nativeQuery=true)
	public List<String> findPermissionsByUsername(String username);
	
}
