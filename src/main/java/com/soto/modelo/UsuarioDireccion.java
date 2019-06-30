package com.soto.modelo;

public class UsuarioDireccion {
	
	private String nombre;
    private String calle;
    private String codigoPostal;
    private long numeroPermisos;
    
    public UsuarioDireccion() {}

    public UsuarioDireccion(String nombre, String calle, String codigoPostal, long numeroPermisos)
    {
        this.nombre = nombre;
        this.calle = calle;
        this.codigoPostal = codigoPostal;
        this.numeroPermisos = numeroPermisos;
    }

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public long getNumeroPermisos() {
		return numeroPermisos;
	}

	public void setNumeroPermisos(long numeroPermisos) {
		this.numeroPermisos = numeroPermisos;
	}

	@Override
	public String toString() {
		return "UsuarioDireccion [nombre=" + nombre + ", calle=" + calle + ", codigoPostal=" + codigoPostal
				+ ", numeroPermisos=" + numeroPermisos + "]";
	}
	
	
	
}
