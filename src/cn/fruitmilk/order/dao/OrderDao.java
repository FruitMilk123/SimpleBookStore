package cn.fruitmilk.order.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.fruitmilk.book.domain.Book;
import cn.fruitmilk.order.domain.Order;
import cn.fruitmilk.order.domain.OrderItems;
import cn.itcast.jdbc.TxQueryRunner;
import cn.itcast.utils.CommonUtils;

public class OrderDao {
	private QueryRunner qr = new TxQueryRunner();
	
	//添加订单
	public void addOrder(Order order) {
		String sql = "INSERT INTO orders VALUES (?,?,?,?,?,?)";
		
		//需要处理时间单位的问题：需要吧utils的date装换成sql的date
		Timestamp timestamp = new Timestamp(order.getOrdertime().getTime());
		
		Object[] pamras = {order.getOid(),timestamp,order.getTotal(),
				order.getState(),order.getOwner().getUid(),order.getAddress()};
		try {
			qr.update(sql , pamras);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	//添加订单条目
	public void addOrderItems(List<OrderItems> orderItems) {
		String sql = "INSERT INTO orderitem VALUES (?,?,?,?,?)";
		Object[][] pamras = new Object[orderItems.size()][];
		for(int i = 0; i<orderItems.size(); i++) {
			OrderItems oi = orderItems.get(i); //把List中的对象一一取出
			pamras[i] = new Object[] {oi.getIid(),oi.getCount(),oi.getSubtotal()
					,oi.getOrder().getOid(),oi.getBook().getBid()};
		}
		try {
			qr.batch(sql, pamras);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Order> findByuid(String uid) {
		String sql ="SELECT * FROM orders WHERE uid=?";
		try {
			List<Order> order_list = qr.query(sql, new BeanListHandler<Order>(Order.class),uid);
			
			for(Order order : order_list) {
				loadorderItems(order);  //这个方法有为能Order对象添加订单条目的能力
			}
			
			return order_list;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	private void loadorderItems(Order order) throws SQLException {
		
		//由于订单条目表只有一个BID，而不带其他的书籍信息，所以需要多表查询
		String sql ="SELECT * FROM orderitem o , book b WHERE o.bid=b.bid AND oid=?";
		List<Map<String,Object>> maplist = qr.query(sql, new MapListHandler(),order.getOid());
		
		//把Map中的
		List<OrderItems> orderItemsList = toOrderItemsList(maplist);
		
		order.setOrderItemsList(orderItemsList);
	}

	private List<OrderItems> toOrderItemsList(List<Map<String, Object>> maplist) {
		
		List<OrderItems> orderlist = new ArrayList<OrderItems>();
		for(Map<String , Object> map : maplist) {
			OrderItems item = toOrderItems(map);
			orderlist.add(item);		
		}
		return orderlist;
	}

	private OrderItems toOrderItems(Map<String, Object> map) {
		OrderItems oi = CommonUtils.toBean(map, OrderItems.class);
		Book book = CommonUtils.toBean(map, Book.class);
		
		oi.setBook(book);
		return oi;
	}

	public Order findOrderByoid(String oid) {
		String sql = "SELECT * FROM orders WHERE oid=?";
		try {
			Order order = qr.query(sql, new BeanHandler<Order>(Order.class),oid);
			loadorderItems(order);
			
			return order;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	//返回某个订单的状态
	public int getStateByoid(String oid) {
		String sql = "SELECT state FROM orders WHERE oid=?";
		Number num;
		try {
			num = (Number) qr.query(sql, new ScalarHandler(),oid);
			int state = num.intValue();
			
			return state;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	//修改某个订单的状态
	public void updateStateByoid(String oid,int state) {
		String sql = "UPDATE orders SET state=? WHERE oid=?";
		Object[] pamras = {state,oid};
		try {
			qr.update(sql,pamras);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
