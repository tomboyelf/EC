package jp.co.aforce.action;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jp.co.aforce.beans.Album;
import jp.co.aforce.beans.Song;
import jp.co.aforce.beans.User;
import jp.co.aforce.dao.ProductDAO;
import jp.co.aforce.tool.Action;

public class SearchSongAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		ProductDAO dao = new ProductDAO();
		User user = new User();

		//		アルバムがクリックされたら
		if (request.getParameter("searchSongId") != null) {
			int albumIdToSearchSongs = Integer.parseInt(request.getParameter("searchSongId"));
			//			アルバムに入ってる曲取得
			List<Song> songList_albumId = dao.getSongswithAlbumId(albumIdToSearchSongs);
			request.setAttribute("songList_albumId", songList_albumId);
			//			ランキング更新して
			dao.updateAlbumTraffic(albumIdToSearchSongs);

			//			非ログイン時の操作をはじく
			if (session.getAttribute("user")!= null) {
				//			ログインしてたら閲覧履歴テーブル更新
				user = (User) session.getAttribute("user");
				dao.updateUserAlbumStatus(user.getId(), albumIdToSearchSongs);
				//				同時に閲覧履歴をセッションに保存
				List<Album> albumList_hisotry = dao.getAlbumsClicked(user.getId());
				session.setAttribute("albumList_hisotry", albumList_hisotry);
			}
		}
		return "song.jsp";
	}

}