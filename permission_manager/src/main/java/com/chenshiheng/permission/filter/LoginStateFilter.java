package com.chenshiheng.permission.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chenshiheng.permission.dto.Subject;
import com.chenshiheng.permission.uitls.UsersManager;

/**
 * Servlet Filter implementation class LoginStateFilter
 */
public class LoginStateFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginStateFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		String url = httpRequest.getRequestURI();
		if(url.equals("/permission_manager/user/login") || url.equals("/permission_manager/user/tologin"))
			chain.doFilter(request, response);
		else{
			HttpSession httpSession = httpRequest.getSession();
			if(httpSession!=null){
				Subject subject = (Subject)httpSession.getAttribute("subject");
				if(subject!=null)
					if(UsersManager.containsSubject(subject))
						chain.doFilter(request, response);
					else
						httpRequest.getServletContext().getRequestDispatcher("/user/tologin").forward(httpRequest, httpResponse);
				else
					httpRequest.getServletContext().getRequestDispatcher("/user/tologin").forward(httpRequest, httpResponse);
			}else{
				httpRequest.getServletContext().getRequestDispatcher("/user/tologin").forward(httpRequest, httpResponse);
			}
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
