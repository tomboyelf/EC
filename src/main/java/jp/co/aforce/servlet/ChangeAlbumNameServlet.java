package jp.co.aforce.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jp.co.aforce.beans.Album;
import jp.co.aforce.beans.Category;
import jp.co.aforce.beans.User;
import jp.co.aforce.dao.ProductDAO;

@WebServlet("/ChangeAlbumNameServlet")
public class ChangeAlbumNameServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		Album oldAlbum = new Album();
		Category category = new Category();
		ProductDAO productDao = new ProductDAO();
		

		//		鰹節
		if (user == null) {
			response.sendRedirect("views/login.jsp");
		}
		
		int albumId = Integer.parseInt(request.getParameter("albumId"));
		
		try {
			//  旧情報
			oldAlbum = productDao.getSpecificAlbum(albumId);
			request.setAttribute("oldAlbum", oldAlbum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String newName = oldAlbum.getName();
		String imgName = oldAlbum.getAlbumImgName();
		String newArtist = oldAlbum.getArtist();
		int newCategory = oldAlbum.getCategoryId();
		String newCategoryName = oldAlbum.getCategoryName();
		
		
		if(request.getParameter("name") != null) {
			newName = request.getParameter("name");
		}
		if(request.getParameter("artist") != null) {
			newArtist = request.getParameter("artist");
		}
		if(request.getParameter("categoryOption") != null) {
			newCategory = Integer.parseInt(request.getParameter("categoryOption"));
			try {
				category = productDao.getSpecificCategory(newCategory);
			} catch (Exception e) {
				e.printStackTrace();
			}
			newCategoryName = category.getName();
		}
		
		//  新情報
		Album newAlbum = new Album(imgName, newName, newArtist, newCategory, newCategoryName);
		request.setAttribute("newAlbum", newAlbum);

		try {
			Thread.sleep(5000);
			request.getRequestDispatcher("views/confirm.jsp").forward(request, response);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
