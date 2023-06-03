//package prs.crazyk.config;
//
//import lombok.RequiredArgsConstructor;
//import net.miraeit.aiss.web.socket.EventWebSocketHandler;
//import net.miraeit.aiss.web.socket.FieldMonitorSocketHandler;
//import net.miraeit.aiss.service.MonitorServiceImpl;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.socket.config.annotation.EnableWebSocket;
//import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
//import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
//import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;
//
//@Configuration
//@EnableWebSocket
//@RequiredArgsConstructor
///**
// * fixme @인증 https://kanoos-stu.tistory.com/58 내용 참고해서 인증 관련 설정이 들어가야 한다
// */
//public class WebSocketConfig implements WebSocketConfigurer {
//	private final MonitorServiceImpl monitorService;
//
//	@Override
//	public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
//		webSocketHandlerRegistry
//			.addHandler(monitorDataSocketHandler(), "/ws/monitor")
//			.addHandler(chatHistorySocketHandler(), "/ws/event")
////			.addInterceptors(new HttpSessionHandshakeInterceptor())
//			.setAllowedOrigins("*");
//	}
//
//	@Bean
//	public FieldMonitorSocketHandler monitorDataSocketHandler() {
//		return new FieldMonitorSocketHandler(monitorService);
//	}
//
//	@Bean
//	public EventWebSocketHandler chatHistorySocketHandler() {
//		return new EventWebSocketHandler(monitorService);
//	}
//}
