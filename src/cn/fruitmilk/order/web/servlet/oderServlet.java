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

		Order order = new Order();  //直接创建订单对象
		Cart cart = (Cart) request.getSession().getAttribute("cart"); //从session中拿到购物车
		User session_user = (User) request.getSession().getAttribute("user"); //从session中获取当前用户
		
		order.setOid(CommonUtils.uuid());  //设置订单编号
		order.setOrdertime(new Date());  //设置下单时间(当前系统时间)
		order.setTotal(cart.getTotal());  //订单总价格对应了购物车内所有商品的总价格，所以从购物车中获取
		order.setState(1); //设置订单状态，初始状态为1
		order.setOwner(session_user); //设置订单所有人
		
		//创建订单条目的List(往list中存放东西)
		List<OrderItems> list = new ArrayList<OrderItems>();
		
		//根据购物车条目来生成订单条目(二者为对应关系)
		for(CartItems items : cart.getCartItems()) {
			OrderItems orderItems = new OrderItems();
			
			orderItems.setIid(CommonUtils.uuid());  //设置订单条目编号
			orderItems.setOrder(order);  //设置关联的order对象，上面有
			orderItems.setCount(items.getCount()); //设置条目数量，对应购物车条目数量
			orderItems.setBook(items.getBook());  //设置对应实体类,从购物车条目中获取
			orderItems.setSubtotal(items.getprintSum()); //设置订单条目小计，从购物车条目小计中获取
			
			list.add(orderItems);  //将设置好了的订单条目对象保存到订单条目的LIST中
		}
		
		order.setOrderItemsList(list);  //设置订单条目list，将上面保存好的List放进去
		
		cart.clear();  //从购物车中生成订单后，清空购物车
		
		OrderService os = new OrderService();
		os.addOrder(order);  //调用service中的添加订单方法
		
		request.setAttribute("order", order);
		request.getRequestDispatcher("/jsps/order/desc.jsp").forward(request, response);
	}
}
