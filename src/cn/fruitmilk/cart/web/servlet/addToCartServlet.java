package cn.fruitmilk.cart.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.fruitmilk.book.domain.Book;
import cn.fruitmilk.book.service.BookService;
import cn.fruitmilk.cart.domain.Cart;
import cn.fruitmilk.cart.domain.CartItems;

public class addToCartServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			Cart cart = (Cart) request.getSession().getAttribute("cart");
			int count =Integer.parseInt(request.getParameter("count")) ;
			BookService bs = new BookService();
			
			Book book = bs.findByBid(request.getParameter("bid"));
			
			CartItems cartitems = new CartItems();
			
			cartitems.setCount(count);
			cartitems.setBook(book);
			
			cart.add(cartitems); 
			
			request.getRequestDispatcher("/jsps/cart/list.jsp").forward(request, response);
	}

}
