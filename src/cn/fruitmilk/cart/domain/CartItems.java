package cn.fruitmilk.cart.domain;

import cn.fruitmilk.book.domain.Book;

public class CartItems {
	//商品条目类，购物车中的东西
	private int count;  //商品数量
	private Book book;  //商品
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	
	public double getprintSum() { //商品价格小计：由计算得出
		return book.getPrice()*count;
	}
}
