package jp.co.aforce.tool;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.DispatcherType;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jp.co.aforce.beans.Album;
import jp.co.aforce.dao.ProductDAO;

@jakarta.servlet.annotation.WebFilter(urlPatterns = { "/views/index.jsp" }, dispatcherTypes = { DispatcherType.REQUEST,
		DispatcherType.FORWARD })

public class IndexFilter implements jakarta.servlet.Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		ProductDAO dao = new ProductDAO();
		try {
			List<Album> albumAndSingleList_date = dao.getAlbumAndSingleOrderedByDate();
			request.setAttribute("albumAndSingleList_date", albumAndSingleList_date);
			List<Album> albumAndSingleList_traffic = dao.getAlbumAndSingleOrderedByTraffic();
			request.setAttribute("albumAndSingleList_traffic", albumAndSingleList_traffic);
		} catch (Exception e) {
			e.printStackTrace();
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig filterconfig) {
	}

	public void destroy() {
	}
}
