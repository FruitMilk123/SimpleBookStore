package cn.fruitmilk.book.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.fruitmilk.book.domain.Book;
import cn.fruitmilk.book.service.BookService;

public class findBookByCidServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BookService bs = new BookService();
		String cid = request.getParameter("cid");
		
		List<Book> category_book = bs.findByCategory(cid);
		
		request.setAttribute("booklist", category_book);
		request.getRequestDispatcher("/jsps/book/list.jsp").forward(request,response);
	}

}
