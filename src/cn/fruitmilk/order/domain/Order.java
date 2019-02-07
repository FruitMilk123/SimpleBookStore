package cn.fruitmilk.order.domain;

import java.util.Date;
import java.util.List;

import cn.fruitmilk.user.domain.User;

public class Order {
	private String oid; //订单编号
	private Date ordertime; //下单时间
	private double total; //订单总价格
	private int state; //订单状态(状态分4中：1、未付款 2、付款未发货
						//3、已付款已发货但未确认收货 4、已确认收货，即已完成交易)
	private User owner; //订单所有人
	private String address; //收货地址
	
	private List<OrderItems> orderItemsList;  //反向关联OrderItems(即当前订单下所有条目)
	
	public List<OrderItems> getOrderItemsList() {
		return orderItemsList;
	}
	public void setOrderItemsList(List<OrderItems> orderItemsList) {
		this.orderItemsList = orderItemsList;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public Date getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
