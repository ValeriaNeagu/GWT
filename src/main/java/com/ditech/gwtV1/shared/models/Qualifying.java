package com.ditech.gwtV1.shared.models;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Qualifying implements IsSerializable, Cloneable {

	private Long id;
	private Integer version;
	private String description;

	public Qualifying() {
	}

	public Qualifying(String description) {
		this.description = description;
	}

	public Qualifying(Qualifying qualifying) {
		this.id = qualifying.getId();
		this.version = qualifying.getVersion();
		this.description = qualifying.getDescription();
	}

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
