package com.ditech.gwtV1.server;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.ditech.gwtV1.client.services.qualifying.QualifyingService;
import com.ditech.gwtV1.shared.models.Qualifying;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class QualifyingServiceImpl extends RemoteServiceServlet implements QualifyingService {

	public ArrayList<Qualifying> findAll() throws IllegalArgumentException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet request = new HttpGet("http://localhost:8099/employeeLevel/findAll");
		try {
			CloseableHttpResponse response = httpClient.execute(request);
			ArrayList<Qualifying> qualifyingList = new ObjectMapper()
					.readValue(EntityUtils.toString(response.getEntity()), new TypeReference<ArrayList<Qualifying>>() {
					});
			return qualifyingList;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

	public Qualifying saveUpdate(Qualifying bean) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPut httpPut = new HttpPut("http://localhost:8099/employeeLevel/saveUpdate");
		try {
//			Thread.sleep(1000);
			httpPut.setHeader("Accept", "application/json");
			httpPut.setHeader("Content-type", "application/json");
			String qualifying = bean.toString();
			httpPut.setEntity(new StringEntity(qualifying));
			CloseableHttpResponse response = httpClient.execute(httpPut);

			Qualifying result = new ObjectMapper().readValue(EntityUtils.toString(response.getEntity()),
					Qualifying.class);
			return result;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
//		catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		return new Qualifying();
	}

	public Qualifying findById(Long id) throws IllegalArgumentException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet request = new HttpGet("http://localhost:8099/employeeLevel/findById/" + id);
		try {
			CloseableHttpResponse response = httpClient.execute(request);
			Qualifying qualifying = new ObjectMapper().readValue(EntityUtils.toString(response.getEntity()),
					Qualifying.class);
			return qualifying;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new Qualifying();
	}

	public void delete(Long id) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpDelete httpDelete = new HttpDelete("http://localhost:8099/employeeLevel/delete/" + id);
		try {
			httpClient.execute(httpDelete);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
