package prs.crazyk.cmm.web.util;

import prs.crazyk.cmm.exception.HttpResponseException;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class HttpUtil {
	private static HttpClient client = HttpClient.newBuilder()
		.connectTimeout(Duration.ofMillis(100))
		.build();
	;

	public static void sendRequest(String url, String method, String body) throws Exception {
		HttpRequest request = HttpRequest.newBuilder()
			.uri(URI.create(url))
			.header("Content-Type", "application/json")
			.method(method, HttpRequest.BodyPublishers.ofString(body))
			.build();

		HttpResponse<String> response = null;
		synchronized(HttpUtil.class) {
			response = client.send(request, HttpResponse.BodyHandlers.ofString());
		}
		if (response==null || response.statusCode() != 200) {
			throw new HttpResponseException("REQUEST FAILED", response);
		}
	}

	public static void main(String...args) throws Exception {
		String body = "{\"deviceId\":\"T-3\",\"deviceLat\":null,\"deviceLng\":null,\"deviceAlt\":0.0,\"deviceFlr\":1,\"pulse\":0,\"temperature\":0.0,\"datetime\":null}";
		sendRequest("http://211.218.126.242:10002/ex/bio-signal", "POST", body);
	}
}
