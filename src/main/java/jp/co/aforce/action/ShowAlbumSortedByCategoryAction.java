package jp.co.aforce.action;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jp.co.aforce.beans.Album;
import jp.co.aforce.dao.ProductDAO;
import jp.co.aforce.tool.Action;

public class ShowAlbumSortedByCategoryAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if(request.getParameter("categoryId")!=null) {
			int categoryId = Integer.parseInt(request.getParameter("categoryId"));
			ProductDAO dao = new ProductDAO();
			List<Album> albumListSortedByCategory = dao.showAlbumSortedByCategory(categoryId);
			request.setAttribute("albumListSortedByCategory", albumListSortedByCategory);
		}
		
		return "album.jsp";
	}

}