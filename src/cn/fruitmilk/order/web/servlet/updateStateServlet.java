package cn.fruitmilk.order.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.fruitmilk.order.dao.OrderException;
import cn.fruitmilk.order.service.OrderService;

public class updateStateServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String oid  = request.getParameter("oid");
		OrderService os = new OrderService();
		
		try {
			os.updateState(oid);
			request.setAttribute("msg", "交易♂成功");
			request.getRequestDispatcher("/jsps/order/msg.jsp").forward(request, response);
		} catch (OrderException e) {
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("/jsps/order/msg.jsp").forward(request, response);
		}
	}

}
