package naves.config.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthenticationFilter extends GenericFilterBean {

	private static final Pattern pattern = Pattern.compile("\\/h2-console*|\\/swagger-ui*|\\/favicon.ico|\\/v3\\/api-docs");
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		if(!pattern.matcher(((HttpServletRequest)request).getRequestURL().toString()).find()) {
			try {
				Authentication authentication = AuthenticationService.getAuthentication((HttpServletRequest) request);
				SecurityContextHolder.getContext().setAuthentication(authentication);
			} catch (Exception exp) {
				HttpServletResponse httpResponse = (HttpServletResponse) response;
				httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				httpResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
				PrintWriter writer = httpResponse.getWriter();
				writer.print(exp.getMessage());
				writer.flush();
				writer.close();
			}	
		}
		

		filterChain.doFilter(request, response);
	}
}
