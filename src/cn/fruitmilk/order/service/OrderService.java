package cn.fruitmilk.order.service;

import java.util.List;

import cn.fruitmilk.order.dao.OrderDao;
import cn.fruitmilk.order.dao.OrderException;
import cn.fruitmilk.order.domain.Order;
import cn.itcast.jdbc.JdbcUtils;

public class OrderService {
	private OrderDao od = new OrderDao();
	
	public void addOrder(Order order) {
		try {
				//开启事务
				JdbcUtils.beginTransaction();
				
				od.addOrder(order);
				od.addOrderItems(order.getOrderItemsList());
				
				//提交事务
				JdbcUtils.commitTransaction();
		} catch(Exception e) {
			try {
				JdbcUtils.rollbackTransaction();
			} catch (Exception e1) {
				//回滚事务
				throw new RuntimeException(e);
			}
		}
	}

	public List<Order> findByuid(String uid) {
		return od.findByuid(uid);
	}

	public Order loadOrder(String oid) {
		return od.findOrderByoid(oid);
	}
	
	public void updateState(String oid) throws OrderException {
		int state = od.getStateByoid(oid);
		if(state != 3)
			throw new OrderException("您还未付款");
		od.updateStateByoid(oid, 4);
	}
}
