package jp.co.aforce.action;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jp.co.aforce.beans.Song;
import jp.co.aforce.dao.ProductDAO;
import jp.co.aforce.tool.Action;

public class PurchaseAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ProductDAO dao = new ProductDAO();
		String[] selectedSongs = request.getParameterValues("selectedSongs");
		if(selectedSongs == null) {
			return "cart.jsp";
		}
		
		List<Song> selectedSongsList = new ArrayList<>();
		for(String selectedSongId : selectedSongs) {
			int songId = Integer.parseInt(selectedSongId);
			Song selectedSong = dao.getSongswithSongId(songId);
			selectedSongsList.add(selectedSong);
		}
		
		request.setAttribute("selectedSongsList",  selectedSongsList);
		
		return "confirm.jsp";
	}
}