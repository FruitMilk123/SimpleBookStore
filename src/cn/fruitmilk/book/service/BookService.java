package cn.fruitmilk.book.service;

import java.util.List;

import cn.fruitmilk.book.dao.bookDao;
import cn.fruitmilk.book.domain.Book;

public class BookService {
	private bookDao bd = new bookDao();
	
	public List<Book> findAllBook() {
		return bd.findAllBook();
	}
	
	public List<Book> findByCategory(String cid) {
		return bd.findByCategory(cid);
	}
	
	public Book findByBid(String bid) {
		return bd.findByBid(bid);
	}
}
