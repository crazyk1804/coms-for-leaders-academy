//package prs.crazyk.config;
//
//import lombok.RequiredArgsConstructor;
//import net.miraeit.cmm.web.auth.JWTRequestFilter;
//import net.miraeit.cmm.web.util.JWTUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//import java.util.Arrays;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled=true)
//@RequiredArgsConstructor
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Value("${jwt.private.key}")
//    private String privateKey;
//    @Value("#{ new Long('${jwt.expiry.duration}')}")
//    private long expiryDuration;
//    @Value("${cors.allowed.origins}")
//    private String allowedOrigins;
//
//    private final UserDetailsService userDetailsService;
//
//    @Bean
//    public JWTUtil jwtUtil() {
//        return new JWTUtil(privateKey, expiryDuration);
//    }
//
//    @Bean
//    public JWTRequestFilter jwtRequestFilter() {
//        return new JWTRequestFilter(userDetailsService, jwtUtil());
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(new PlainEncoder());
//    }
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/static/**");
//    }
//
//    // todo CORS 설정 (credential: include 요청의 처리를 위해 본 설정 추가되어야 함)
//    @Bean
//    protected CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.addAllowedOrigin(allowedOrigins);
//        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PATCH", "DELETE"));
//        configuration.setAllowedHeaders(Arrays.asList("content-type", "Authorization"));
//        configuration.setAllowCredentials(true);
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
////                .cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
//                // todo CORS 설정 (주석 처리한 윗줄 처럼 하면 client 에서 credential: include 로 요청을 보낼시 허용되지 않는다
//                .cors()
//                .and()
//                .authorizeRequests()
//                .antMatchers("/"
//                        ,   "/authenticate"
//                        ,   "/uat/uia/actionLogin.do"
//                        ,   "/check-cookie"
//                        ,   "/error"
//                        ,   "/error1"
//// ============================================================================================================
//                        // Swagger
//                        /* swagger v2 */
//                        ,   "/v2/api-docs"
//                        ,   "/swagger-resources"
//                        ,   "/swagger-resources/**"
//                        ,   "/configuration/ui"
//                        ,   "/configuration/security"
//                        ,   "/swagger-ui.html"
//                        ,   "/webjars/**"
//                        /* swagger v3 */
//                        ,   "/v3/api-docs/**"
//                        ,   "/swagger-ui/**"
//// ============================================================================================================
//                        ,   "/test/vvd"
//                        ,   "/test/esntl"
//                        ,   "/ex/field-event"
//                        ,   "/ex/bio-signal"
//                        ,   "/ex/device-signal"
//                        ,   "/snct/berth-plan"
//                        ,   "/device-coords-history"
//// ============================== JWT 운용이 잘 안되어 있어서 일단 급한대로 예외처리 함 ===============================
////                        ,   "/ws/monitor"
////                        ,   "/ws/event"
////                        ,   "/sts/**"
////                        ,   "/device/paged-list"
////                        ,   "/system/**"
////                        ,   "/cmm/**"
////                        ,   "/work-management/**"
//
//                ).permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .exceptionHandling()
////                .authenticationEntryPoint(entryPoint)
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .addFilterBefore(jwtRequestFilter(), UsernamePasswordAuthenticationFilter.class);
//    }
//}
