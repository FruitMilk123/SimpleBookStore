package cn.fruitmilk.order.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.fruitmilk.order.domain.Order;
import cn.fruitmilk.order.service.OrderService;
import cn.fruitmilk.user.domain.User;

public class myorderServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			User user = (User) request.getSession().getAttribute("user"); //��session�еĵõ��û�����
			OrderService os = new OrderService();
			List<Order> order_list = os.findByuid(user.getUid());  //���û�ID��Ϊ��������serviceһ��myorder����������һ��List<Order>
			request.setAttribute("orderlist", order_list);
			request.getRequestDispatcher("/jsps/order/list.jsp").forward(request, response);
	}

}
