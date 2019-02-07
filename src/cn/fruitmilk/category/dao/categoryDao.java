package cn.fruitmilk.category.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.fruitmilk.category.domain.Category;
import cn.itcast.jdbc.TxQueryRunner;

public class categoryDao {
	private QueryRunner qr = new TxQueryRunner();
	
	public List<Category> findByAll() {
		String sql = "SELECT * FROM category";
		try {
			return qr.query(sql, new BeanListHandler<Category>(Category.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Category> findById(String cid) {
		String sql = "SELECT * FROM category WHERE cid=?";
		try {
			return qr.query(sql, new BeanListHandler<Category>(Category.class),cid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
