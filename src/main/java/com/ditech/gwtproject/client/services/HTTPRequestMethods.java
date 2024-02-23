package com.ditech.gwtproject.client.services;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;

final public class HTTPRequestMethods {
	public static final int STATUS_CODE_OK = 200;

	public static Object doHTTPGet(String url) {
		/*
		Object objTemp = null;
		try {
			Gson gson = new Gson();
//			String url = "http://127.0.0.1:8099/employees";
//			HttpURLConnection httpURLConnection;
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", "Mozilla/5.0");
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
				// print result
				System.out.println(response.toString());
				
				Object objDummy = gson.fromJson(response.toString(), Object.class);
				
				objTemp = objDummy;
//				List<Object> myDummyList = gson.fromJson(response.toString(), List.class);
				System.out.println(objDummy.toString());
			} else {
				System.out.println("GET request did not work.");
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return objTemp;
		*/
		
		return null;
	}
	
	

	public static final Request doHTTPGet_1(String url) {

//		System.out.println("GWT.getModuleBaseURL() = " + GWT.getModuleBaseURL());
		RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url);
		try {
			Request response = builder.sendRequest(null, new RequestCallback() {

				@Override
				public void onResponseReceived(Request request, Response response) {
					System.out.println("request = " + request.toString());
					System.out.println("response = " + response.toString());
				}

				@Override
				public void onError(Request request, Throwable exception) {
					System.out.println("request = " + request.toString());
				}

			});
			System.out.println("response = " + response.toString());
			return response;
		} catch (RequestException e) {
		}
		return null;
	}

	public static void doGet2(String url) {
		
		RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url);

		try {
			Request response = builder.sendRequest(null, new RequestCallback() {
				public void onError(Request request, Throwable exception) {
					// Code omitted for clarity
				}

				public void onResponseReceived(Request request, Response response) {
					// Code omitted for clarity
				}
			});
		} catch (RequestException e) {
			// Code omitted for clarity
		}
		
	}

//	default void doHTTPPost(String url, String postData) {
//		RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, url);
//		try {
//			builder.setHeader("Content-Type", "application/x-www-form-urlencoded");
//			Request response = builder.sendRequest(postData, new RequestCallback() {
//
//				public void onError(Request request, Throwable exception) {
//				}
//
//				public void onResponseReceived(Request request, Response response) {
//				}
//			});
//		} catch (RequestException e) {
//		}
//	}
}
