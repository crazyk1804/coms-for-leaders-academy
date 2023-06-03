package prs.crazyk.cmm.web;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import prs.crazyk.cmm.model.auth.AuthCredential;
import prs.crazyk.cmm.model.auth.AuthResponse;
import prs.crazyk.cmm.model.auth.JWTRefreshToken;
import prs.crazyk.cmm.model.response.ServerResponse;
import prs.crazyk.cmm.model.response.ServerResponseEntity;
import prs.crazyk.cmm.service.auth.JWTRefreshTokenServiceImpl;
import prs.crazyk.cmm.service.impl.TestServiceImpl;
import prs.crazyk.cmm.web.util.JWTUtil;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ApiIgnore
@RestController
@AllArgsConstructor
public class CMMController {
	private final TestServiceImpl service;
	private final AuthenticationManager authenticationManager;
	private final JWTUtil jwtUtil;
	private final JWTRefreshTokenServiceImpl refreshTokenService;
//	private final CMMServiceImpl cmmService;

//	@PostMapping("/authenticate")
//	public ResponseEntity<ServerResponse<AuthResponse>> authenticate(
//			@Valid @RequestBody AuthCredential credential,
//			HttpServletRequest request, HttpServletResponse response
//	) throws Exception {
//		// todo egov 사용하려니 encoder 를 구현하는 것으로는 처리가 불가했음(사용자 ID를 인자로 써야 하기때문에...)
//		credential.setPassword(
//			EgovFileScrty.encryptPassword(credential.getPassword(), credential.getIdentity())
//		);
//		Authentication authentication = authenticationManager
//				.authenticate(new UsernamePasswordAuthenticationToken(
//						credential.getIdentity(),
//						credential.getPassword()
//				));
//		LoginVO principal = (LoginVO)authentication.getPrincipal();
//		LoginVO clone = principal.toBuilder().build();
//		clone.setPassword(null);
//
//		HashMap<String, Object> claims = new HashMap<>();
//		claims.put("identity", principal.getId());
//		claims.put("username", principal.getName());
//		String token = jwtUtil.generateToken(claims, principal);
//
//		// todo CORS 설정 관련
//		//  RefreshToken 쿠키 설정 (httpOnly, secure 등은 테스트를 위해 풀어둔 상태
//		JWTRefreshToken refreshToken = refreshTokenService.issueRefreshToken(principal.getId());
//		ResponseCookie responseCookie = ResponseCookie.from("refresh-token", refreshToken.getRefreshToken())
////				.httpOnly(true)
////				.secure(true)
//				.path("/")
//				.maxAge(60)
////				.domain("localhost:3000")
//				.build();
//
//		ServerResponse<AuthResponse> resp = new ServerResponse<>();
//		resp.setBody(AuthResponse.builder().member(clone).token(token).build());
//		return ResponseEntity
//				.status(HttpStatus.OK)
//				.header(HttpHeaders.SET_COOKIE, responseCookie.toString())
//				.body(resp)
//		;
//	}

	private String printAndConcatCookie(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if(cookies==null) return "NO COOKIES";

		for(Cookie cookie : cookies) {
			System.out.printf("%s: %s\n", cookie.getName(), cookie.getValue());
		}
		return Arrays.stream(cookies).map(
				x -> String.format("%s: %s", x.getName(), x.getValue())
		).collect(Collectors.joining(","));
	}

//	@GetMapping("/cmm/code-map")
//	@ApiOperation("코드 조회")
//	public ServerResponseEntity<Map<String, List<SystemCode>>> getSystemCodeMap(@RequestParam List<String> codeIds) {
//		Map<String, List<SystemCode>> codeMap = cmmService.getSystemCode(
//			AISSConditions.builder().codeIds(codeIds).build()
//		);
//		return ServerResponseEntity.OK(codeMap);
//	}

//	@GetMapping("/cmm/user-groups")
//	@ApiOperation("사용자 그룹(권한) 목록 조회")
//	public ServerResponseEntity<List<UserGroup>> getUserGroups() {
//		List<UserGroup> groups = cmmService.getUserGroups();
//		return ServerResponseEntity.OK(groups);
//	}

//	@GetMapping("/check-cookie")
//	public ServerResponseEntity<String> getCookieCheck(HttpServletRequest request) {
//		return ServerResponseEntity.OK(printAndConcatCookie(request));
//	}
//
//	@PostMapping("/check-cookie")
//	public ServerResponseEntity<String> postCookieCheck(HttpServletRequest request) {
//		return ServerResponseEntity.OK(printAndConcatCookie(request));
//	}

//	@ApiOperation("SWAGGER TEST API GET")
//	@GetMapping("/test-api")
//	public ResponseEntity<TestModel> test() throws Exception {
//		TestModel tm = new TestModel();
//		tm.setTestValue("TEST");
//
////		if(1==1) throw new CustomException("EXCEPTION");
//		service.test();
//		return ResponseEntity.ok(tm);
//	}
//
//	@ApiOperation("SWAGGER TEST API POST")
//	@PostMapping("/test-api")
//	public ServerResponseEntity<TestModel> test(@Validated @RequestBody TestModel model) {
//		return ServerResponseEntity.OK(model);
//	}

	@GetMapping("/error")
	public ServerResponseEntity<String> error(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("response status: " + response.getStatus());
		return ServerResponseEntity.STATUS(HttpStatus.FORBIDDEN).body("접근이 거부 되었습니다.");
	}

//	@GetMapping("/error1")
//	public ServerResponseEntity<String> error1(HttpServletRequest request, HttpServletResponse response) {
//		System.out.println("response status: " + response.getStatus());
//		return ServerResponseEntity.STATUS(HttpStatus.FORBIDDEN).body("FUCK");
//	}
}
