package jp.co.aforce.action;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jp.co.aforce.beans.Cart;
import jp.co.aforce.beans.User;
import jp.co.aforce.dao.ProductDAO;
import jp.co.aforce.tool.Action;

public class CartAddAction extends Action {
	@SuppressWarnings("unchecked")
	public String execute(
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		ProductDAO dao = new ProductDAO();
		User user = new User();
		user = (User) session.getAttribute("user");

		//		ログイン時に取ってきたカート状況および購入履歴
		List<Integer> inCartList = (List<Integer>) session.getAttribute("inCartList");
		List<Integer> purchaseHistoryList = (List<Integer>) session.getAttribute("purchaseHistoryList");

		//		値段がクリックされたら
		if (user.getId() != 0) {
			int songId = Integer.parseInt(request.getParameter("addCartId"));
			//			カートにあるか判断
			if (inCartList.contains(songId)) {
				//				もうカートにある
				System.out.println("すでにカートにある");
				return "song.jsp";
			}
			//			購入済みか判断
			if (purchaseHistoryList.contains(songId)) {
				//				購入済み
				System.out.println("購入済み");
				return "song.jsp";
			}
			//			リクエスト元のuriでカート追加か購入か判断
			int line = dao.getSongIntoCart(user.getId(), songId);
			if (line > 0) {
				//					カート追加成功
				System.out.println("カート追加成功");
				
//				カート取得処理
				inCartList = dao.getInCartList(user.getId());
				session.setAttribute("inCartList", inCartList);
				List<Cart> cartInfo = new ArrayList<>();
				for(Integer list : inCartList) {
					Cart cart = dao.getCartInfo(list);
					cartInfo.add(cart);
				}
				request.setAttribute("cartInfo", cartInfo);
//				できればページにとどまりたい
//				ページ遷移ではなく、メッセージ表示にしたい。とても。
				return "cart.jsp";
			} else {
				//					追加失敗、原因不明
				System.out.println("カート追加失敗");
				return "song.jsp";
			}
		}
		//		ログインを促す
		return "login.jsp";
	}
}