package jp.co.aforce.action;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jp.co.aforce.beans.User;
import jp.co.aforce.dao.AdminDAO;
import jp.co.aforce.dao.ProductDAO;
import jp.co.aforce.tool.Action;
import jp.co.aforce.tool.Message;

public class DeleteAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		ProductDAO productDao = new ProductDAO();
		AdminDAO adminDao = new AdminDAO();
		Message msg = new Message();

		//		ログインしてないなら
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "login.jsp";
		}

		if (request.getParameter("deleteId") != null) {
			//		カートから削除
			if (request.getParameter("deleteId").equals("cart")) {
				int line = productDao.removeSongFromCart(user.getId(),
						Integer.parseInt(request.getParameter("songId")));
				if (line > 0) {
					request.setAttribute("completeMsg", msg.getCompleteMsg(5));
					List<Integer> inCartList = productDao.getInCartList(user.getId());
					session.setAttribute("inCartList", inCartList);
					return "cart.jsp";
				}
			}

			if (request.getParameter("deleteId").equals("song")) {
				String deleteId = request.getParameter("deleteId");
				int songId = Integer.parseInt(request.getParameter("songId"));
				System.out.println(deleteId);
				System.out.println(songId);
				request.setAttribute("deleteId", deleteId);
				request.setAttribute("songId", songId);
				return "confirm.jsp";
			}
			if (request.getParameter("deleteId").equals("album")) {
				String deleteId = request.getParameter("deleteId");
				int albumId = Integer.parseInt(request.getParameter("albumId"));
				System.out.println(deleteId);
				System.out.println(albumId);
				request.setAttribute("deleteId", deleteId);
				request.setAttribute("albumId", albumId);
				return "confirm.jsp";
			}
			if (request.getParameter("deleteId").equals("category")) {
				String deleteId = request.getParameter("deleteId");
				int categoryId = Integer.parseInt(request.getParameter("categoryId"));
				System.out.println(deleteId);
				System.out.println(categoryId);
				request.setAttribute("deleteId", deleteId);
				request.setAttribute("categoryId", categoryId);
				return "confirm.jsp";
			}

			//			曲削除
			if (request.getParameter("deleteId").equals("deleteSong")) {
				try {
					adminDao.deleteSong(Integer.parseInt(request.getParameter("songId")));
					request.setAttribute("adminCompleteMsg005", msg.getAdminCompleteMsg(5));
					return "admin-message.jsp";
				} catch (Exception e) {
					request.setAttribute("adminCompleteMsg006", msg.getAdminCompleteMsg(6));
					e.printStackTrace();
					return "admin-message.jsp";
				}
			}

			//			アルバム削除
			if (request.getParameter("deleteId").equals("deleteAlbum")) {
				try {
					adminDao.deleteAlbum(Integer.parseInt(request.getParameter("albumId")));
					request.setAttribute("adminCompleteMsg005", msg.getAdminCompleteMsg(5));
					return "admin-message.jsp";
				} catch (Exception e) {
					request.setAttribute("adminCompleteMsg007", msg.getAdminCompleteMsg(7));
					e.printStackTrace();
					return "admin-message.jsp";
				}
			}

			//			カテゴリ削除
			if (request.getParameter("deleteId").equals("deleteCategory")) {
				try {
					adminDao.deleteCategory(Integer.parseInt(request.getParameter("categoryId")));
					request.setAttribute("adminCompleteMsg005", msg.getAdminCompleteMsg(5));
					return "admin-message.jsp";
				} catch (Exception e) {
					request.setAttribute("adminCompleteMsg007", msg.getAdminCompleteMsg(7));
					e.printStackTrace();
					return "admin-message.jsp";
				}
			}
		}
		return "cart.jsp";
	}
}