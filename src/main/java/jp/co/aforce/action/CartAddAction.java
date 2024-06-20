package jp.co.aforce.action;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jp.co.aforce.beans.User;
import jp.co.aforce.dao.ProductDAO;
import jp.co.aforce.tool.Action;
import jp.co.aforce.tool.Message;

public class CartAddAction extends Action {
	@SuppressWarnings("unchecked")
	public String execute(
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		ProductDAO dao = new ProductDAO();
		User user = new User();
		Message msg = new Message();
		user = (User) session.getAttribute("user");

		//		ログインチェック、鰹節
		if (user == null) {
			//		ログインを促す
			return "login.jsp";
		}

		//		ログイン時に取ってきたカート状況および購入履歴
		List<Integer> inCartList = (List<Integer>) session.getAttribute("inCartList");
		List<Integer> purchaseHistoryList = (List<Integer>) session.getAttribute("purchaseHistoryList");

		//		値段がクリックされたら
		int songId = Integer.parseInt(request.getParameter("addCartId"));

		//		ログインしてたらカートに入っている商品と購入済みの商品をはじく
		//			カートにあるか判断
		if (inCartList.contains(songId)) {
			request.setAttribute("purchaseErrorMsg", msg.getPurchaseErrorMsg(0));
			return "song.jsp";
		}
		//			購入済みか判断
		if (purchaseHistoryList.contains(songId)) {
			request.setAttribute("purchaseErrorMsg", msg.getPurchaseErrorMsg(1));
			return "song.jsp";
		}

		//		↑↑↑↑
		//		ここまで鰹節
		
		int line;
		
		if(dao.checkSongStasus(user.getId(), songId)) {
			line = dao.getSongIntoCart(user.getId(), songId);
		} else {
			line = dao.getSongIntoCartFTF(user.getId(), songId);
		}
		if (line > 0) {
			//				追加後の新しいカートを取得
			inCartList = dao.getInCartList(user.getId());
			session.setAttribute("inCartList", inCartList);
			request.setAttribute("completeMsg", msg.getCompleteMsg(2));
			return "song.jsp";
		} else {
			//					追加失敗、原因不明
			request.setAttribute("purchaseErrorMsg", msg.getPurchaseErrorMsg(4));
			return "song.jsp";
		}
	}
}