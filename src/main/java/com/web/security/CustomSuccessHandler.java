package com.web.security;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{
	private final RequestCache requestCache = new HttpSessionRequestCache();
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		// Lấy role từ đối tượng Authentication
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		String redirectURL = null;
		for (GrantedAuthority authority : authorities) {
			String role = authority.getAuthority();
			if (role.equals("ROLE_ADMIN")) {
				redirectURL = "/admin-home";
				break;
			} else if (role.equals("ROLE_USER")) {
				if (request.getCookies() != null) {
		            for (Cookie cookie : request.getCookies()) {
		                if ("PRE_LOGIN_URL".equals(cookie.getName())) {
		                	redirectURL = URLDecoder.decode(cookie.getValue(), "UTF-8");
		                    break;
		                }
		            }
		        }

				if(redirectURL == null) {
					redirectURL ="/davy/shop";
				}		
			}
		}
		response.sendRedirect(redirectURL);
	}
}
