package com.ditech.gwtproject.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.ditech.gwtproject.client.GreetingService;
import com.ditech.gwtproject.shared.FieldVerifier;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
//import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {

	private void doPOSTRequestTest() {
		try {

			System.err.println(" ----- private void doPOSTRequestTest ");

			Gson gson = new Gson();
			String url = "http://127.0.0.1:8099/employeeLevel/saveUpdate";
			URL urlObj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
			con.setRequestMethod("PUT");
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Accept", "application/json");
			con.setDoOutput(true);
			Map<String, String> capitalCities = new HashMap<String, String>();
			capitalCities.put("description", "Dascaleac v");
			OutputStream os = con.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
			osw.write(gson.toJson(capitalCities));
			osw.flush();
			osw.close();
			os.close();
			con.connect();
			int responseCode = con.getResponseCode();
			System.out.println("POST Response Code :: " + responseCode);
			if (responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				System.out.println(response.toString());
			} else {
				System.out.println("POST request did not work.");
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void doGetRequestTest() {
		System.err.println(" Greeting Service impl ---> private void doGetRequestTest");
		try {
			Gson gson = new Gson();
//			gson.from
			String url = "http://localhost:8099/employeeLevel/findAll";
//			HttpURLConnection httpURLConnection;
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			int responseCode = con.getResponseCode();
			System.out.println("GET Response Code :: " + responseCode);

			if (responseCode == HttpURLConnection.HTTP_OK) { // success
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();

//				Object objectEval = (JsonUtils.safeEval(response.toString()));

				String responseToString = response.toString();

				List<JsonObject> listDummy1 = gson.fromJson(responseToString, List.class);

				List listDummy2 = JsonParser.parseString(responseToString).getAsJsonArray().asList();

				System.out.println("list = " + listDummy1.toString());
			} else {
				System.out.println("GET request did not work.");
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		super.doGet(req, resp);
//	}

	private void doRequestTest_1() {
//		String url = "http://127.0.0.1:8099/employees";
		String url = "https://services.syncfusion.com/js/production/api/Orders";
//		String requestData = "/employees";
		RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url);
		try {
//			builder.sendRequest(requestData.toString(), new RequestCallback() {
			builder.sendRequest(null, new RequestCallback() {

				@Override
				public void onError(Request request, Throwable e) {
					Window.alert(e.getMessage());

				}

				@Override
				public void onResponseReceived(Request request, Response response) {
					if (200 == response.getStatusCode()) {
						Window.alert(response.getText());
					} else {
						Window.alert(
								"Dascaleac Received HTTP status code other than 200 : " + response.getStatusText());
					}
				}
			});
		} catch (RequestException e) {
			// Couldn't connect to server
			Window.alert(e.getMessage());
		} catch (Exception e) {
			Window.alert("Exception e = " + e.getMessage());
		}

	}

//	@Override
	public String greetServer(String input) throws IllegalArgumentException {
		// Verify that the input is valid.
		if (!FieldVerifier.isValidName(input)) {
			// If the input is not valid, throw an IllegalArgumentException back to
			// the client.
			throw new IllegalArgumentException("Name must be at least 4 characters long");
		}

//		this.doGetRequestTest();
		
		
//		this.doPOSTRequestTest();

//		String serverInfo = getServletContext().getServerInfo();
//		String userAgent = getThreadLocalRequest().getHeader("User-Agent");

		// Escape data from the client to avoid cross-site script vulnerabilities.
//		input = escapeHtml(input);
//		userAgent = escapeHtml(userAgent);
//		return "Hello, " + input + "!<br><br>I am running " + serverInfo + ".<br><br>It looks like you are using:<br>"
//				+ userAgent;
		return "DV";
	}

	
	/*
	public List<Object> greetServerRazvan() throws IllegalArgumentException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet request = new HttpGet("http://127.0.0.1:8089/employee-service/qualifying/findall");
		try {
			CloseableHttpResponse response = httpClient.execute(request);
			ArrayList<Object> qualifyingList = new ObjectMapper().readValue(EntityUtils.toString(response.getEntity()),
					new TypeReference<ArrayList<Object>>() {
					});
			return qualifyingList;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}
	
	*/

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;");
	}
}
