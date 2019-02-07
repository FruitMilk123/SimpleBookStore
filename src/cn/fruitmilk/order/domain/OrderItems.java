package cn.fruitmilk.order.domain;

import cn.fruitmilk.book.domain.Book;

public class OrderItems {
	private String iid; //条目编号
	private int count; //条目数量
	private double subtotal; //条目总价格(小计)
	private Order order; //订单编号(外键)
	private Book book; //书编号(外键)
	
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	public String getIid() {
		return iid;
	}
	public void setIid(String iid) {
		this.iid = iid;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
}
