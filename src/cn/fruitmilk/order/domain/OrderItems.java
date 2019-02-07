package cn.fruitmilk.order.domain;

import cn.fruitmilk.book.domain.Book;

public class OrderItems {
	private String iid; //��Ŀ���
	private int count; //��Ŀ����
	private double subtotal; //��Ŀ�ܼ۸�(С��)
	private Order order; //�������(���)
	private Book book; //����(���)
	
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
