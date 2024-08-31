package forgesystem.apiclient;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created By : Ahmad Hossain
 * Created At : 01-Sept-2024 03:10 AM
 */

public class HttpApiClient implements Closeable {
	private HttpURLConnection connection;
	
	public HttpApiClient(String url, String method) throws IOException, NoSuchAlgorithmException, KeyManagementException {
		URL apiUrl = new URL(url);
		connection = (HttpURLConnection) apiUrl.openConnection();
		connection.setRequestMethod(method);
		connection.setDoInput(true);
		
		// If the URL is HTTPS, configure SSL
		if (connection instanceof HttpsURLConnection) {
			configureSSL((HttpsURLConnection) connection);
		}
	}
	
	private void configureSSL(HttpsURLConnection httpsConnection) throws NoSuchAlgorithmException, KeyManagementException {
		// Create a trust manager that does not validate certificate chains
		TrustManager[] trustAllCerts = new TrustManager[] {
			new X509TrustManager() {
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
				public void checkClientTrusted(X509Certificate[] certs, String authType) { }
				public void checkServerTrusted(X509Certificate[] certs, String authType) { }
			}
		};
		
		// Install the all-trusting trust manager
		SSLContext sslContext = SSLContext.getInstance("TLS");
		sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
		httpsConnection.setSSLSocketFactory(sslContext.getSocketFactory());
		
		// Disable hostname verification
		httpsConnection.setHostnameVerifier((hostname, session) -> true);
	}
	
	public void setRequestHeaders(Map<String, String> headers) {
		for (Map.Entry<String, String> header : headers.entrySet()) {
			connection.setRequestProperty(header.getKey(), header.getValue());
		}
	}
	
	public void setRequestBody(String body) throws IOException {
		connection.setDoOutput(true);
		try (OutputStream os = connection.getOutputStream()) {
			byte[] input = body.getBytes("utf-8");
			os.write(input, 0, input.length);
		}
	}
	
	public int getStatusCode() throws IOException {
		return connection.getResponseCode();
	}
	
	public Map<String, List<String>> getResponseHeaders() {
		return connection.getHeaderFields();
	}
	
	public String getResponseBody() throws IOException {
		StringBuilder response = new StringBuilder();
		try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
			String line;
			while ((line = in.readLine()) != null) {
				response.append(line.trim());
			}
		}
		return response.toString();
	}
	
	public Map<String, String> getCookies() {
		Map<String, String> cookies = new HashMap<>();
		List<String> setCookieHeaders = connection.getHeaderFields().get("Set-Cookie");
		if (setCookieHeaders != null) {
			for (String cookie : setCookieHeaders) {
				String[] parts = cookie.split(";");
				for (String part : parts) {
					String[] cookieParts = part.split("=", 2);
					if (cookieParts.length == 2) {
						cookies.put(cookieParts[0].trim(), cookieParts[1].trim());
					}
				}
			}
		}
		return cookies;
	}
	
	@Override
	public void close() throws IOException {
		if (connection != null) {
			connection.disconnect();
		}
	}
}
