package com.ditech.gwtV1.client.services.qualifying;

import java.util.ArrayList;

import com.ditech.gwtV1.shared.models.Qualifying;
import com.google.gwt.user.client.rpc.AsyncCallback;


public interface QualifyingServiceAsync {

	void findAll(AsyncCallback<ArrayList<Qualifying>> callback);

	void saveUpdate(Qualifying bean, AsyncCallback<Qualifying> callback);

	void findById(Long id, AsyncCallback<Qualifying> callback);

	void delete(Long id, AsyncCallback<Void> callback);

}
