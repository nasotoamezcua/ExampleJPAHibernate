package com.soto.beans;

public class DireccionBean {
	
	private Long id;
	private String calle;
	private String codigoPostal;
	
	
	public DireccionBean(String calle, String codigoPostal) {
		super();
		this.calle = calle;
		this.codigoPostal = codigoPostal;
	}

	public DireccionBean(Long id, String calle, String codigoPostal) {
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

	@Override
	public String toString() {
		return "DireccionBean [id=" + id + ", calle=" + calle + ", codigoPostal=" + codigoPostal + "]";
	}

}
