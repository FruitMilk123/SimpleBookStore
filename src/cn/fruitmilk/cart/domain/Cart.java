package cn.fruitmilk.cart.domain;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
	private Map<String,CartItems> map = new LinkedHashMap<String, CartItems>();
	
	public double getTotal() {  //获取购物车内所有商品的总价格
		double total = 0;
		for(CartItems cartitems : map.values()) {
			total = total + cartitems.getprintSum();
		}
		return total;
	}
	
	public void add(CartItems cartitems) { //添加商品条目
		if(map.containsKey(cartitems.getBook().getBid())) { //判断是否包含老条目(即是否有相同类别的书)
			//获取老条目
			CartItems old_cartitems = map.get(cartitems.getBook().getBid());
			//在老条目数量的基础上加上新条目的数量(合并数量)
			old_cartitems.setCount(old_cartitems.getCount() + cartitems.getCount());
		} else {
			map.put(cartitems.getBook().getBid(), cartitems);  //如果不存在直接保存为新条目
		}
	}
	
	public void clear() { //清空所有条目
		map.clear();
	}
	
	public void delete(String bid) {  //删除指定条目，根据主键Bid来删除
		map.remove(bid);
	}
	
	public Collection<CartItems> getCartItems() {  //获取所有条目
		return map.values();
	}
}
