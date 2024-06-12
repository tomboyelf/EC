package jp.co.aforce.action;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jp.co.aforce.beans.Category;
import jp.co.aforce.dao.ProductDAO;
import jp.co.aforce.tool.Action;

public class ShowCategoryAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		ProductDAO dao = new ProductDAO();
		List<Category> categoryList = dao.showCategory();
		request.setAttribute("categoryList", categoryList);
		return "album.jsp";
	}

}