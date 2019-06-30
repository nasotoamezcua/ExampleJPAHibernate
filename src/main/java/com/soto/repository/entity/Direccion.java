package com.soto.repository.entity;

import java.io.Serializable;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import com.soto.modelo.UserDirResultMaping;

/*
 * Nota importante: Para declarar nombres de metodos con @NamedQuery y @NamedNativeQuery 
 * los nombres de los metodos tienen que terminar con la convencion "FindByNombrePropiedad", por ejmplo:
 * getFindByCalle
 * getQuieroBuscarFindByCalle
 * getFindByCodigoPostal
 * getOtroQueryFindByCodigoPostal
 */

@Entity
@Table(name = "direcciones")
@NamedQueries({
	@NamedQuery(name = "Direccion.getFindByCalle", query = "SELECT d FROM Direccion d WHERE d.calle = :calle")
})

@NamedNativeQueries({
  @NamedNativeQuery(
		  name = "Direccion.getFindByCodigoPostal", 
		  query = "SELECT * FROM direcciones WHERE codigo_postal = :codigoPostal",
		  resultClass = Direccion.class),
  
  @NamedNativeQuery(
		  name = "Direccion.getSinResultClassFindByCodigoPostal", 
		  query = "SELECT * FROM direcciones WHERE codigo_postal = :codigoPostal")
})

/*
 * 
 */
//Referencia: https://arprastsoft.wordpress.com/2016/12/26/namednativequery-and-sqlresultsetmapping-in-spring-jpa/
@NamedNativeQuery(name = "Direccion.userDirResultMaping",
query = " select u.id,u.nombre, u.username, u.password, d.calle, codigo_postal "
		+ " from usuarios u, direcciones d "
		+ " where  u.direccion_id = d.id ",
resultSetMapping = "Direccion.userDirResultMaping"
)
@SqlResultSetMapping(name = "Direccion.userDirResultMaping",
classes = {
    @ConstructorResult(
            targetClass = UserDirResultMaping.class,
            columns = {
                @ColumnResult(name = "id", type = long.class),
                @ColumnResult(name = "nombre", type = String.class),
                @ColumnResult(name = "username", type = String.class),
                @ColumnResult(name = "password", type = String.class),
                @ColumnResult(name = "calle", type = String.class),
                @ColumnResult(name = "codigo_postal", type = String.class)
            })
})

public class Direccion implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String calle;
	
	private String codigoPostal;
	
	@OneToOne(mappedBy = "direccion")
	private Usuario usuario;
	
	public Direccion() {}

	public Direccion(String calle, String codigoPostal) {
		super();
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Direccion [id=" + id + ", calle=" + calle + ", codigoPostal=" + codigoPostal + "]";
	}

}
