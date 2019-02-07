package cn.fruitmilk.book.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.fruitmilk.book.domain.Book;
import cn.itcast.jdbc.TxQueryRunner;

public class bookDao {
	private QueryRunner qr = new TxQueryRunner();
	
	//查询所有的图书
	public List<Book> findAllBook() {
		String sql = "SELECT * FROM book";
		try {
			return qr.query(sql, new BeanListHandler<Book>(Book.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Book> findByCategory(String cid) {
		String sql = "SELECT * FROM book WHERE cid=?";
		try {
			return qr.query(sql, new BeanListHandler<Book>(Book.class),cid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Book findByBid(String bid) {
		String sql = "SELECT * FROM book WHERE bid=?";
		try {
			return qr.query(sql, new BeanHandler<Book>(Book.class),bid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
