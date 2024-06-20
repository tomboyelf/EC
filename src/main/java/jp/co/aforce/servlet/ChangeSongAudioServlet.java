package jp.co.aforce.servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import jp.co.aforce.beans.Song;
import jp.co.aforce.beans.User;
import jp.co.aforce.dao.ProductDAO;

@WebServlet("/ChangeSongAudioServlet")
@MultipartConfig(location = "C:\\pleiades-2024-03-java-win-64bit-jre_20240325\\workspace\\ShoppingSite\\src\\main\\webapp\\WEB-INF\\upload")
public class ChangeSongAudioServlet extends HttpServlet {
	@Override
	public void init() throws ServletException {
		// 必要に応じて一時ディレクトリを設定
		System.setProperty("java.io.tmpdir",
				"C:\\pleiades-2024-03-java-win-64bit-jre_20240325\\workspace\\ShoppingSite\\src\\main\\webapp\\WEB-INF\\upload");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		Song oldSong = new Song();
		ProductDAO productDao = new ProductDAO();

		//		鰹節
		if (user == null) {
			response.sendRedirect("views/login.jsp");
		}

		//		inputからファイルを取得
		Part part = request.getPart("file");
		String audioName = this.getFileName(part);
		
		///////////////////////////////////////////////
		//		ファイルがnullだったら、もしくは名前が空白だったら
		if (part == null || audioName.equals("")) {
			response.sendRedirect("views/admin-index.jsp");
		}

		List<String> fileList = new ArrayList<String>();
		File dir = new File(
				"C:\\pleiades-2024-03-java-win-64bit-jre_20240325\\workspace\\ShoppingSite\\src\\main\\webapp\\audio"); //Fileクラスのオブジェクトを生成し対象のディレクトリを指定
		File[] list = dir.listFiles(); //listFilesを使用してファイル一覧を取得
		for (File file : list) {
			fileList.add(file.getName());
		}

		//		フォルダがなかった場合、作る
		String uploadDirPath = getServletContext().getRealPath("/audio");
		File uploadDir = new File(uploadDirPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}
		String filePath = "C:\\pleiades-2024-03-java-win-64bit-jre_20240325\\workspace\\ShoppingSite\\src\\main\\webapp\\audio"
				+ File.separator + audioName;

		//		フォルダ内にすでにファイル名が存在する場合
		if (fileList.contains(audioName)) {
			//			存在しない場合
		} else if (!fileList.contains(audioName)) {
			try {
				part.write(filePath);
				System.out.println("File saved to: " + filePath);
			} catch (IOException e) {
				System.out.println("Failed to save file: " + e.getMessage());
			}
		}
		///////////////////////////////////////////////

		int songId = Integer.parseInt(request.getParameter("oldSongId"));
		System.out.println("idの取得" + songId);
		
		try {
			//  旧情報
			oldSong = productDao.getSpecificSong(songId);
			request.setAttribute("oldSong", oldSong);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		//  新情報
		Song newSong = new Song(songId, oldSong.getAlbumId(), audioName, oldSong.getName(), oldSong.getAlbumName(), oldSong.getPrice());
		request.setAttribute("newSong", newSong);
		

		try {
			Thread.sleep(5000);
			request.getRequestDispatcher("views/confirm.jsp").forward(request, response);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private String getFileName(Part part) {
		String name = null;
		for (String dispotion : part.getHeader("Content-Disposition").split(";")) {
			if (dispotion.trim().startsWith("filename")) {
				name = dispotion.substring(dispotion.indexOf("=") + 1).replace("\"", "").trim();
				name = name.substring(name.lastIndexOf("\\") + 1);
				break;
			}
		}
		return name;
	}
}
