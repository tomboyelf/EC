package jp.co.aforce.action;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jp.co.aforce.beans.Album;
import jp.co.aforce.dao.ProductDAO;
import jp.co.aforce.tool.Action;

public class ShowAlbumOrderedByDateAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		ProductDAO dao = new ProductDAO();
		List<Album> albumListOrderedByDate = dao.showAlbumOrderedByDate();
		request.setAttribute("albumListOrderedByDate", albumListOrderedByDate);
		return "album.jsp";
	}

}