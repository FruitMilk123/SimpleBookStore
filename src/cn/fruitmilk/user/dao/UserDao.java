package cn.fruitmilk.user.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.fruitmilk.user.domain.User;
import cn.itcast.jdbc.TxQueryRunner;

public class UserDao {
	private QueryRunner qr = new TxQueryRunner();
	
	public void add(User form) {
		String sql = "INSERT INTO tb_user VALUES (?,?,?,?,?,?)";
		Object[] pamras = {form.getUid(),form.getUsername(),form.getPassword(),form.getEmail(),
				form.getCode(),form.isState()};
		try {
			qr.update(sql, pamras);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public User findByUsername(String username) {
		String sql = "SELECT * FROM tb_user WHERE username=?";
		Object[] pamras = {username};
		try {
			return qr.query(sql, new BeanHandler<User>(User.class),pamras);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public User findByEmail(String email) {
		String sql = "SELECT * FROM tb_user WHERE email=?";
		Object[] pamras = {email};
		try {
			return qr.query(sql, new BeanHandler<User>(User.class),pamras);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
