package com.trs.shiro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trs.shiro.domain.User;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * Description:根据用户名和密码查找用户
	 *
	 * @param username
	 * @param password
	 * @return
	 * @author cui.jianbin  
	 * @date 2019年5月31日  上午9:53:19
	 * @version 1.0
	 */
	public User findUser(String username,String password){
		return userRepository.findByNameAndPassword(username, password);
	}
	
	/**
	 * Description:是否存在该用户名
	 *
	 * @param name
	 * @return
	 * @author cui.jianbin  
	 * @date 2019年5月31日  上午9:53:40
	 * @version 1.0
	 */
	public User findByUsername(String name){
		return userRepository.findByName(name);
	}
	
	/**
	 * Description:根据用户名查找该用户的所有角色
	 *
	 * @param username
	 * @return
	 * @author cui.jianbin  
	 * @date 2019年5月31日  下午3:45:33
	 * @version 1.0
	 */
	public List<String> findRolesByUsername(String username){
		return userRepository.findRolesByUsername(username);
	}
	
	/**
	 * Description:根据用户名查找该用户的所有权限
	 *
	 * @param username
	 * @return
	 * @author cui.jianbin  
	 * @date 2019年5月31日  下午3:45:33
	 * @version 1.0
	 */
	public List<String> findPermissionsByUsername(String username){
		return userRepository.findPermissionsByUsername(username);
	}

}
