package cn.fruitmilk.cart.domain;

import cn.fruitmilk.book.domain.Book;

public class CartItems {
	//��Ʒ��Ŀ�࣬���ﳵ�еĶ���
	private int count;  //��Ʒ����
	private Book book;  //��Ʒ
	
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
	
	public double getprintSum() { //��Ʒ�۸�С�ƣ��ɼ���ó�
		return book.getPrice()*count;
	}
}
