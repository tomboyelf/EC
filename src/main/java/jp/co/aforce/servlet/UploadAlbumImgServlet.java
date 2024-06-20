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
import jp.co.aforce.beans.Album;
import jp.co.aforce.beans.Category;
import jp.co.aforce.beans.User;
import jp.co.aforce.dao.ProductDAO;

@WebServlet("/UploadAlbumImgServlet")
@MultipartConfig(location = "C:\\pleiades-2024-03-java-win-64bit-jre_20240325\\workspace\\ShoppingSite\\src\\main\\webapp\\WEB-INF\\upload")
public class UploadAlbumImgServlet extends HttpServlet {
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
		User user = (User)session.getAttribute("user");
		ProductDAO productDao = new ProductDAO();
		Category category = new Category();
		
//		鰹節
		if(user == null) {
			response.sendRedirect("views/login.jsp");
		}
		//		inputからファイルを取得
		Part part = request.getPart("file");
		
//		新情報
		String imgName = this.getFileName(part);
		String newName = request.getParameter("albumName");
		String artist = request.getParameter("artist");
		int categoryId = Integer.parseInt(request.getParameter("categoryOption"));
		
		try {
			category = productDao.getSpecificCategory(categoryId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("画像名、アルバム名、アーティスト、カテゴリId:" + imgName + newName + artist + categoryId + category.getName());
		

		///////////////////////////////////////////////
		//		ファイルがnullだったら、もしくは名前が空白だったら
		if (part == null || imgName.equals("")) {
			response.sendRedirect("views/admin-index.jsp");
		}

		//		画像の保存先フォルダに、すでにある画像の名前一覧を取得し、ファイル名と比較
		List<String> fileList = new ArrayList<String>();
		File dir = new File(
				"C:\\pleiades-2024-03-java-win-64bit-jre_20240325\\workspace\\ShoppingSite\\src\\main\\webapp\\image\\album"); //Fileクラスのオブジェクトを生成し対象のディレクトリを指定
		File[] list = dir.listFiles(); //listFilesを使用してファイル一覧を取得
		for (File file : list) {
			fileList.add(file.getName());
		}

		//		フォルダがなかった場合、作る
		String uploadDirPath = getServletContext().getRealPath("/image/album");
		File uploadDir = new File(uploadDirPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}
		String filePath = "C:\\pleiades-2024-03-java-win-64bit-jre_20240325\\workspace\\ShoppingSite\\src\\main\\webapp\\image\\album"
				+ File.separator + imgName;

		//		フォルダ内にすでにファイル名が存在する場合
		if (fileList.contains(imgName)) {
			//			存在しない場合
		} else if (!fileList.contains(imgName)) {
			try {
				part.write(filePath);
				System.out.println("File saved to: " + filePath);
			} catch (IOException e) {
				System.out.println("Failed to save file: " + e.getMessage());
			}
		}
		///////////////////////////////////////////////
		
		Album album = new Album(imgName, newName, artist, categoryId, category.getName());
		request.setAttribute("newAlbum", album);
//
		try {
			Thread.sleep(5000);
			request.getRequestDispatcher("views/confirm.jsp").forward(request, response);
		} catch(InterruptedException e){
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
