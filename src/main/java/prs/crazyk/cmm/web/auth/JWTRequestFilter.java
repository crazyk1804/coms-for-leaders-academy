package prs.crazyk.cmm.web.auth;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import prs.crazyk.cmm.web.util.JWTUtil;

import javax.security.auth.message.AuthException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@AllArgsConstructor
@Slf4j
public class JWTRequestFilter extends OncePerRequestFilter {
	private UserDetailsService userDetailsService;
	private JWTUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		String requestURI = request.getRequestURI();
//		log.info("REQ URI: {}, METHOD={}", requestURI, request.getMethod());

		// 헤더의 Authorization 을 찾는다
		final String requestTokenHeader = requestURI.startsWith("/ws") ? request.getParameter("jwt") : request.getHeader("Authorization");
		// 토큰을 보내지 않았다면 아무 의미 없다
		if(requestTokenHeader==null) {
			filterChain.doFilter(request, response);
			return;
		}

		String username = null, token;
		// Bearer 로 시작하는지 판단하는 왜 하는건지는 잘 모르겠다.  불문율 같은건가...
		if(requestTokenHeader.startsWith("Bearer ")) {
			token = requestTokenHeader.substring(7);
		} else {
			token = requestTokenHeader;
			log.warn("JWT Token does not begin with Bearer String");
		}

		// 예외가 발생하면 request 에 해당 예외 객체를 넣어주는 작업
		Optional<AuthException> optional = Optional.empty();
		try {
			username = jwtUtil.getUsernameFromToken(token);
		} catch (ExpiredJwtException e) {
			optional = Optional.of(new AuthException("JWT Token has expired"));
		}
		optional.ifPresent(e -> request.setAttribute("authException", e));

		if(username != null && SecurityContextHolder.getContext().getAuthentication()==null) {
			// 사용자 정보를 조회해서 SecurityContextHolder 에 적절한 값을 세팅해줌
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
			if (jwtUtil.isValidateToken(token, userDetails)) {
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
					userDetails, null, userDetails.getAuthorities()
				);
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}

		filterChain.doFilter(request, response);
	}
}