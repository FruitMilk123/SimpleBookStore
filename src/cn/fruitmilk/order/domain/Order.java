package cn.fruitmilk.order.domain;

import java.util.Date;
import java.util.List;

import cn.fruitmilk.user.domain.User;

public class Order {
	private String oid; //�������
	private Date ordertime; //�µ�ʱ��
	private double total; //�����ܼ۸�
	private int state; //����״̬(״̬��4�У�1��δ���� 2������δ����
						//3���Ѹ����ѷ�����δȷ���ջ� 4����ȷ���ջ���������ɽ���)
	private User owner; //����������
	private String address; //�ջ���ַ
	
	private List<OrderItems> orderItemsList;  //�������OrderItems(����ǰ������������Ŀ)
	
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
