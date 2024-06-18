package jp.co.aforce.tool;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "*.action" })
public class FrontController extends HttpServlet {
	protected void doPost(
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String path = request.getServletPath().substring(6);
			String name = path.replace(".a", "A").replace("/", "jp.co.aforce.action.");
			Action action = (Action) Class.forName(name).newInstance();
			String url = action.execute(request, response);

			System.out.println("path:" + path);
			System.out.println("name:" + name);
			System.out.println("url:" + url);

			request.getRequestDispatcher(url).forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//	こうすることで、doGetリクエストを受け取った場合でもdoPostメソッドが呼び出される
	public void doGet(
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
