package forgesystem.apiclient;

import org.junit.Test;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Api Client Test
 * Created By : Ahmad Hossain
 * Created At : 01-Sept-2024 03:15 AM
 */

public class ApiClientTest {
	@Test
	public void test_get_request() {
		try (HttpApiClient client = new HttpApiClient("https://jsonplaceholder.typicode.com/posts/1", "GET")) {
			// Set request headers
			client.setRequestHeaders(Collections.singletonMap("Accept", "application/json"));
			
			// Make the request and print results
			int statusCode = client.getStatusCode();
			System.out.println("GET Status Code: " + statusCode);
			System.out.println("GET Response Headers: " + client.getResponseHeaders());
			System.out.println("GET Response Body: " + client.getResponseBody());
		} catch (IOException | NoSuchAlgorithmException | KeyManagementException e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void test_post_request() {
		try (HttpApiClient client = new HttpApiClient("https://jsonplaceholder.typicode.com/posts", "POST")) {
			Map<String, String> headers = new HashMap<>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json");
			// Set request headers
			client.setRequestHeaders(headers);
			
			// Set request body
			String jsonBody = "{\"title\": \"foo\", \"body\": \"bar\", \"userId\": 1}";
			client.setRequestBody(jsonBody);
			
			// Make the request and print results
			int statusCode = client.getStatusCode();
			System.out.println("POST Status Code: " + statusCode);
			System.out.println("POST Response Headers: " + client.getResponseHeaders());
			System.out.println("POST Response Body: " + client.getResponseBody());
		} catch (IOException | NoSuchAlgorithmException | KeyManagementException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test_put_request() {
		try (HttpApiClient client = new HttpApiClient("https://jsonplaceholder.typicode.com/posts/1", "PUT")) {
			
			Map<String, String> headers = new HashMap<>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json");
			
			// Set request headers
			client.setRequestHeaders(headers);
			
			// Set request body
			String jsonBody = "{\"id\": 1, \"title\": \"foo\", \"body\": \"bar\", \"userId\": 1}";
			client.setRequestBody(jsonBody);
			
			// Make the request and print results
			int statusCode = client.getStatusCode();
			System.out.println("PUT Status Code: " + statusCode);
			System.out.println("PUT Response Headers: " + client.getResponseHeaders());
			System.out.println("PUT Response Body: " + client.getResponseBody());
		} catch (IOException | NoSuchAlgorithmException | KeyManagementException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test_patch_request() {
		try (HttpApiClient client = new HttpApiClient("https://jsonplaceholder.typicode.com/posts/1", "PATCH")) {
			Map<String, String> headers = new HashMap<>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json");
			
			// Set request headers
			client.setRequestHeaders(headers);
			
			// Set request body
			String jsonBody = "{\"title\": \"foo\"}";
			client.setRequestBody(jsonBody);
			
			// Make the request and print results
			int statusCode = client.getStatusCode();
			System.out.println("PATCH Status Code: " + statusCode);
			System.out.println("PATCH Response Headers: " + client.getResponseHeaders());
			System.out.println("PATCH Response Body: " + client.getResponseBody());
		} catch (IOException | NoSuchAlgorithmException | KeyManagementException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test_delete_request() {
		try (HttpApiClient client = new HttpApiClient("https://jsonplaceholder.typicode.com/posts/1", "DELETE")) {
			// Set request headers
			client.setRequestHeaders(Collections.singletonMap("Accept", "application/json"));
			
			// Make the request and print results
			int statusCode = client.getStatusCode();
			System.out.println("DELETE Status Code: " + statusCode);
			System.out.println("DELETE Response Headers: " + client.getResponseHeaders());
			System.out.println("DELETE Response Body: " + client.getResponseBody());
		} catch (IOException | NoSuchAlgorithmException | KeyManagementException e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void test_custom_headers_and_cookies() {
		try (HttpApiClient client = new HttpApiClient("https://jsonplaceholder.typicode.com/posts", "POST")) {
			Map<String, String> headers = new HashMap<>();
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json");
			headers.put("Cookie", "sessionId=abc123");
			
			// Set custom request headers including cookies
			client.setRequestHeaders(headers);
			
			// Set request body
			String jsonBody = "{\"title\": \"foo\", \"body\": \"bar\", \"userId\": 1}";
			client.setRequestBody(jsonBody);
			
			// Make the request and print results
			int statusCode = client.getStatusCode();
			System.out.println("Custom Headers & Cookies Status Code: " + statusCode);
			System.out.println("Custom Headers & Cookies Response Headers: " + client.getResponseHeaders());
			System.out.println("Custom Headers & Cookies Response Body: " + client.getResponseBody());
			System.out.println("Cookies from Response: " + client.getCookies());
		} catch (IOException | NoSuchAlgorithmException | KeyManagementException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
}
