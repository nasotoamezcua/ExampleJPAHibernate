package com.soto.dto;

import java.io.Serializable;

public class DireccionDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String calle;
	private String codigoPostal;
	
	public DireccionDTO() {}

	public DireccionDTO(Long id, String calle, String codigoPostal) {
		super();
		this.id = id;
		this.calle = calle;
		this.codigoPostal = codigoPostal;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

}
