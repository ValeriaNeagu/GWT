package com.ditech.gwtproject.server;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.ditech.gwtproject.client.services.employeeLevel.EmployeeLevelService;
import com.ditech.gwtproject.shared.EmployeeLevel;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class EmployeeLevelServiceImpl extends RemoteServiceServlet implements EmployeeLevelService {

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<EmployeeLevel> findAll(String url) throws IllegalArgumentException {

		ArrayList<EmployeeLevel> listTemp = null;
		try {
			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpGet request = new HttpGet(url);
			CloseableHttpResponse response = httpClient.execute(request);
			listTemp = new ObjectMapper().readValue(EntityUtils.toString(response.getEntity()),
					new TypeReference<ArrayList<EmployeeLevel>>() {
					});
//			listTemp = (List<Object>) HTTPRequestMethods.doHTTPGet(url);

			System.out.println(" EmployeeLevelServiceImpl public ArrayList<EmployeeLevel> findAll ");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listTemp;
	}

}
