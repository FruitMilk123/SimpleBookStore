package cn.fruitmilk.user.service;

import cn.fruitmilk.user.dao.UserDao;
import cn.fruitmilk.user.dao.UserException;
import cn.fruitmilk.user.domain.User;

public class UserService {
	private UserDao userdao = new UserDao();
	
	public void regist(User form) throws UserException {
		User user = userdao.findByUsername(form.getUsername());
		if(user!=null) 
			throw new UserException("用户名已被注册");
		
		user = userdao.findByEmail(form.getEmail());
		if(user!=null) 
			throw new UserException("Email已被注册");
		
		userdao.add(form);
	}
	
	public User login(User form) throws UserException {
		User user = userdao.findByUsername(form.getUsername());
		if(user==null)
			throw new UserException("用户名不存在");
		if(!user.getPassword().equals(form.getPassword()))
			throw new UserException("密码错误");
		
		return user;
	}
}
