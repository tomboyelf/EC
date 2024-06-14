package jp.co.aforce.action;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jp.co.aforce.beans.User;
import jp.co.aforce.dao.ProductDAO;
import jp.co.aforce.tool.Action;

public class PurchaseConfirmAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		ProductDAO dao = new ProductDAO();
		User user = (User) session.getAttribute("user");
		String[] selectedSongIds = request.getParameterValues("selectedSongs");
		for(String i: selectedSongIds) {
			int songId = Integer.parseInt(i);
			dao.purchase(user.getId(), songId);
			System.out.println(songId);
		}
		
		List<Integer> purchaseHistoryList = dao.getPurchaseHistoryList(user.getId());
		session.setAttribute("purchaseHistoryList", purchaseHistoryList);
		
		return "message.jsp";
	}
}