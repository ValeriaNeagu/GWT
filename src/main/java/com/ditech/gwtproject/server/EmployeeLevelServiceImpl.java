package com.ditech.gwtproject.server;

import java.util.List;

import com.ditech.gwtproject.client.services.HTTPRequestMethods;
import com.ditech.gwtproject.client.services.employeeLevel.EmployeeLevelService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class EmployeeLevelServiceImpl extends RemoteServiceServlet implements EmployeeLevelService {

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> findAll(String url) throws IllegalArgumentException {

		List<Object> listTemp = null;
		try {
//			CloseableHttpClient httpClient = HttpClients.createDefault();
//			HttpGet request = new HttpGet(url);
//			CloseableHttpResponse response = httpClient.execute(request);
//			ArrayList<Object> qualifyingList = new ObjectMapper().readValue(EntityUtils.toString(response.getEntity()), new TypeReference<ArrayList<Qualifying>>() {});
			listTemp = (List<Object>) HTTPRequestMethods.doHTTPGet(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listTemp;
	}

}
