package cn.fruitmilk.book.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.fruitmilk.book.service.BookService;

public class loadbookServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			String bid = request.getParameter("bid");
			BookService bs = new BookService();
			
			request.setAttribute("book", bs.findByBid(bid));
			request.getRequestDispatcher("/jsps/book/desc.jsp").forward(request, response);
	}

}
