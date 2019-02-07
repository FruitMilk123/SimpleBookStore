package cn.fruitmilk.category.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.fruitmilk.category.domain.Category;
import cn.fruitmilk.category.service.CategoryService;

public class findallServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
			CategoryService cs = new CategoryService();
			
			List<Category> booklist = cs.findAll();
			
			request.setAttribute("booklist", booklist);
			request.getRequestDispatcher("/jsps/left.jsp").forward(request, response);
	}

}
