package jp.co.aforce.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jp.co.aforce.beans.Category;
import jp.co.aforce.beans.User;
import jp.co.aforce.dao.ProductDAO;

@WebServlet("/ChangeCategoryNameServlet")
public class ChangeCategoryNameServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		Category oldCategory = new Category();
		ProductDAO productDao = new ProductDAO();

		//		鰹節
		if (user == null) {
			response.sendRedirect("views/login.jsp");
		}

		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
		String newName = request.getParameter("categoryName");

		try {
			//		旧情報
			oldCategory = productDao.getSpecificCategory(categoryId);
			request.setAttribute("oldCategory", oldCategory);

		} catch (Exception e) {
			e.printStackTrace();
		}

		//	　　新情報
		Category newCategory = new Category(categoryId, oldCategory.getImgName(), newName);
		request.setAttribute("newCategory", newCategory);

		try {
			Thread.sleep(5000);
			request.getRequestDispatcher("views/confirm.jsp").forward(request, response);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
