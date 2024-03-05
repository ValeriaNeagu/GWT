package com.ditech.gwtV1.client.services.qualifying;

import java.util.ArrayList;

import com.ditech.gwtV1.shared.models.Qualifying;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("qualifying")
public interface QualifyingService extends RemoteService {

	ArrayList<Qualifying> findAll();

	Qualifying saveUpdate(Qualifying bean);

	Qualifying findById(Long id);

	void delete(Long id);
}
