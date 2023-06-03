package prs.crazyk.cmm.model.response;

import org.springframework.http.*;
import org.springframework.util.Assert;
import org.springframework.util.MultiValueMap;

public class ServerResponseEntity<T> extends ResponseEntity<ServerResponse<T>> {
	public ServerResponseEntity(HttpStatus status) {
		super(
			ServerResponse.<T>builder()
				.hasSucceed(status.value() >= 200 && status.value() < 300)
				.build(),
			status
		);
	}

	public ServerResponseEntity(T body, HttpStatus status) {
		super(
			ServerResponse.<T>builder()
				.hasSucceed(status.value() >= 200 && status.value() < 300)
				.body(body)
				.build(),
			status
		);
	}

	public ServerResponseEntity(T body, HttpHeaders headers, HttpStatus status) {
		super(
			ServerResponse.<T>builder()
				.hasSucceed(status.value() >= 200 && status.value() < 300)
				.body(body)
				.build(),
				headers,
				status
		);
	}

	public static <T> ServerResponseEntity<T> OK(T body) {
		return STATUS(HttpStatus.OK).body(body);
	}

	public static ServerResponseEntityBodyBuilder STATUS(HttpStatus status) {
		Assert.notNull(status, "HttpStatus must not be null");
		return new ServerResponseEntityBuilder(status);
	}

	public interface ServerResponseEntityHeaderBuilder<T> {
		T header(String headerName, String...headerValues);
	}

	public interface ServerResponseEntityBodyBuilder
			extends ServerResponseEntityHeaderBuilder<ServerResponseEntityBodyBuilder> {
		<T> ServerResponseEntity<T> body(T body);
	}

	private static class ServerResponseEntityBuilder implements ServerResponseEntityBodyBuilder{
		private final HttpStatus status;
		private final HttpHeaders headers = new HttpHeaders();

		public ServerResponseEntityBuilder(HttpStatus status) {
			this.status = status;
		}

		public <T> ServerResponseEntity<T> body(T body) {
			return new ServerResponseEntity<>(body, status);
		}

		public ServerResponseEntityBuilder header(String headerName, String...headerValues) {
			for (String headerValue : headerValues) {
				this.headers.add(headerName, headerValue);
			}
			return this;
		}
	}

//	private ResponseEntity<ServerResponse<T>> innerEntity;
//	private ServerResponse<T> response;
//
//	public ServerResponseEntity(T body) {
//		response = ServerResponse.<T>builder()
//			.isOK(true)
//			.body(body)
//			.build();
//		innerEntity = ResponseEntity.ok().body(response);
//	}
//
//	public ServerResponseEntity(HttpStatus status, T body) {
//		response = ServerResponse.<T>builder()
//			.isOK(status.value() >= 200 && status.value() < 300)
//			.body(body)
//			.build();
//		innerEntity = ResponseEntity.status(status).body(response);
//	}
//
//	public ResponseEntity<ServerResponse<T>> entity() {
//		return innerEntity;
//	}
}
