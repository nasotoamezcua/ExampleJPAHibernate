package com.soto.dto;

import java.io.Serializable;

import com.soto.repository.entity.Permiso.Estatus;

public class PermisoDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nombre;
	private Estatus estatus = Estatus.PENDIENTE;
	
	public PermisoDTO() {}

	public PermisoDTO(Long id, String nombre, Estatus estatus) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.estatus = estatus;
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

	public Estatus getEstatus() {
		return estatus;
	}

	public void setEstatus(Estatus estatus) {
		this.estatus = estatus;
	}

}
