package jp.co.aforce.tool;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jp.co.aforce.beans.Album;
import jp.co.aforce.dao.ProductDAO;

@jakarta.servlet.annotation.WebFilter(urlPatterns = {"/views/index.jsp"})
public class Filter implements jakarta.servlet.Filter {


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
	
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		ProductDAO dao = new ProductDAO();
		List<Album> albumListOrderedByDate;
		List<Album> albumListOrderedByTraffic;
		try {
			albumListOrderedByDate = dao.showAlbumOrderedByDate();
			albumListOrderedByTraffic = dao.showAlbumOrderedByTraffic();
			request.setAttribute("albumListOrderedByDate", albumListOrderedByDate);
			request.setAttribute("albumListOrderedByTraffic", albumListOrderedByTraffic);
		} catch (Exception e) {
			e.printStackTrace();
		}
		chain.doFilter(request, response);
	}
	
	public void init(FilterConfig filterconfig) {}
	public void destroy() {}
}

