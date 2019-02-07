package cn.fruitmilk.order.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.fruitmilk.order.domain.Order;
import cn.fruitmilk.order.service.OrderService;

public class loadorderServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			String oid = request.getParameter("oid");
			OrderService os = new OrderService();
			
			Order order = os.loadOrder(oid);
			request.setAttribute("order", order);
			request.getRequestDispatcher("/jsps/order/desc.jsp").forward(request, response);
	}

}
