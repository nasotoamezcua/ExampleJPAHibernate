package com.soto.dto;

import java.io.Serializable;
import java.util.List;

public class UsuarioDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nombre;
	private String username;
	private String password;
	private DireccionDTO direccion;
	private List<PermisoDTO> permisos;
	
	public UsuarioDTO() {}
	
	public UsuarioDTO(Long id, String nombre, String username, String password) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.username = username;
		this.password = password;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}	

	public List<PermisoDTO> getPermisos() {
		return permisos;
	}

	public void setPermisos(List<PermisoDTO> permisos) {
		this.permisos = permisos;
	}

	public DireccionDTO getDireccion() {
		return direccion;
	}

	public void setDireccion(DireccionDTO direccion) {
		this.direccion = direccion;
	}	
	
}
