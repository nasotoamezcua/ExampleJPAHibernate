package com.soto.modelo;

public class UserDirResultMaping {
	
	private Long id;
	private String nombre;
	private String userName;
	private String password;
	private String calle;
	private String codigoPostal;
	
	public UserDirResultMaping(Long id, String nombre, String userName, String password, String calle, String codigoPostal) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.userName = userName;
		this.password = password;
		this.calle = calle;
		this.codigoPostal = codigoPostal;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
		return "UserDirResultMaping [id=" + id + ", nombre=" + nombre + ", userName=" + userName + ", password="
				+ password + ", calle=" + calle + ", codigoPostal=" + codigoPostal + "]";
	}

}
