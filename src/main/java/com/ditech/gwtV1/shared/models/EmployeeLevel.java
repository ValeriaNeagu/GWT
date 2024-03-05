package com.ditech.gwtV1.shared.models;

import com.google.gwt.user.client.rpc.IsSerializable;

public class EmployeeLevel implements IsSerializable {
	private Long id;
	private Integer version;
	private String description;

//	public EmployeeLevel() {
//	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "{\"id\":" + id + ",\"version\":" + version + ",\"description\":\"" + description + "\"}";
	}

}
