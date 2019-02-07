package cn.fruitmilk.order.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.fruitmilk.cart.domain.Cart;
import cn.fruitmilk.cart.domain.CartItems;
import cn.fruitmilk.order.domain.Order;
import cn.fruitmilk.order.domain.OrderItems;
import cn.fruitmilk.order.service.OrderService;
import cn.fruitmilk.user.domain.User;
import cn.itcast.utils.CommonUtils;

public class oderServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Order order = new Order();  //ֱ�Ӵ�����������
		Cart cart = (Cart) request.getSession().getAttribute("cart"); //��session���õ����ﳵ
		User session_user = (User) request.getSession().getAttribute("user"); //��session�л�ȡ��ǰ�û�
		
		order.setOid(CommonUtils.uuid());  //���ö������
		order.setOrdertime(new Date());  //�����µ�ʱ��(��ǰϵͳʱ��)
		order.setTotal(cart.getTotal());  //�����ܼ۸��Ӧ�˹��ﳵ��������Ʒ���ܼ۸����Դӹ��ﳵ�л�ȡ
		order.setState(1); //���ö���״̬����ʼ״̬Ϊ1
		order.setOwner(session_user); //���ö���������
		
		//����������Ŀ��List(��list�д�Ŷ���)
		List<OrderItems> list = new ArrayList<OrderItems>();
		
		//���ݹ��ﳵ��Ŀ�����ɶ�����Ŀ(����Ϊ��Ӧ��ϵ)
		for(CartItems items : cart.getCartItems()) {
			OrderItems orderItems = new OrderItems();
			
			orderItems.setIid(CommonUtils.uuid());  //���ö�����Ŀ���
			orderItems.setOrder(order);  //���ù�����order����������
			orderItems.setCount(items.getCount()); //������Ŀ��������Ӧ���ﳵ��Ŀ����
			orderItems.setBook(items.getBook());  //���ö�Ӧʵ����,�ӹ��ﳵ��Ŀ�л�ȡ
			orderItems.setSubtotal(items.getprintSum()); //���ö�����ĿС�ƣ��ӹ��ﳵ��ĿС���л�ȡ
			
			list.add(orderItems);  //�����ú��˵Ķ�����Ŀ���󱣴浽������Ŀ��LIST��
		}
		
		order.setOrderItemsList(list);  //���ö�����Ŀlist�������汣��õ�List�Ž�ȥ
		
		cart.clear();  //�ӹ��ﳵ�����ɶ�������չ��ﳵ
		
		OrderService os = new OrderService();
		os.addOrder(order);  //����service�е���Ӷ�������
		
		request.setAttribute("order", order);
		request.getRequestDispatcher("/jsps/order/desc.jsp").forward(request, response);
	}
}
