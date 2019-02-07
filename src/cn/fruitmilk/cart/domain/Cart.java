package cn.fruitmilk.cart.domain;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
	private Map<String,CartItems> map = new LinkedHashMap<String, CartItems>();
	
	public double getTotal() {  //��ȡ���ﳵ��������Ʒ���ܼ۸�
		double total = 0;
		for(CartItems cartitems : map.values()) {
			total = total + cartitems.getprintSum();
		}
		return total;
	}
	
	public void add(CartItems cartitems) { //�����Ʒ��Ŀ
		if(map.containsKey(cartitems.getBook().getBid())) { //�ж��Ƿ��������Ŀ(���Ƿ�����ͬ������)
			//��ȡ����Ŀ
			CartItems old_cartitems = map.get(cartitems.getBook().getBid());
			//������Ŀ�����Ļ����ϼ�������Ŀ������(�ϲ�����)
			old_cartitems.setCount(old_cartitems.getCount() + cartitems.getCount());
		} else {
			map.put(cartitems.getBook().getBid(), cartitems);  //���������ֱ�ӱ���Ϊ����Ŀ
		}
	}
	
	public void clear() { //���������Ŀ
		map.clear();
	}
	
	public void delete(String bid) {  //ɾ��ָ����Ŀ����������Bid��ɾ��
		map.remove(bid);
	}
	
	public Collection<CartItems> getCartItems() {  //��ȡ������Ŀ
		return map.values();
	}
}
