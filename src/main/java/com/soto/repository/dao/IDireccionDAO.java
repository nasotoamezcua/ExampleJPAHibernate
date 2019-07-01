package com.soto.repository.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.soto.modelo.UserDirResultMaping;
import com.soto.repository.entity.Direccion;

@Repository
public interface IDireccionDAO extends JpaRepository<Direccion, Long> {
	
	/*
	 * Referencia: https://dzone.com/articles/spring-data-with-jpa-and-namedqueries
	 * 
	 * Nota Importante: Para declarar metodos con la etiqueta @Query
	 * los nombres de dichos metodos (a diferencia de @NamedQuery y @NamedNativeQuery)  no siguen ninguna convencion de nombres
	 * y pueden ser declarados con el nombre que se desee, por ejemplo:
	 * getFindByCalleQuey
	 * getFindByCpQueyNative
	 * getFindByCpOtraQueyNative
	 * getFindByCpOtraQueyNativeCampos
	 */
	
	/** 
	 * Utilizando @NamedQuery delcarado en la entidad Direccion
	 * @param calle
	 * @return
	 */
	List<Direccion> getFindByCalle(@Param("calle") String calle);
	
	
	/**
	 * Utilizando @Query delcarado en el metodo de la interfaz
	 * @param calle
	 * @return
	 */
	@Query("SELECT d FROM Direccion d WHERE d.calle = :calle")
	List<Direccion> getFindByCalleQuey(@Param("calle") String calle);
	
	/**
	 * Utilizando @NamedNativeQuery delcarado en la entidad Direccion utilizando resultClass
	 * @param codigoPostal
	 * @return
	 */
	List<Direccion> getFindByCodigoPostal(@Param("codigoPostal") String codigoPostal);
	
	/**
	 * Utilizando @NamedNativeQuery delcarado en la entidad Direccion sin utilizar resultClass
	 * Nota: Si no se utiliza el parametro resultClass enla consulta es forzoso regresar un List<Object[]> o Object[] segun sea el caso 
	 * @param codigoPostal
	 * @return
	 */
	List<Object[]> getSinResultClassFindByCodigoPostal(@Param("codigoPostal") String codigoPostal);
	
	/**
	 * Utilizando @Query delcarado en el metodo de la interfaz y utilizando una consulta nativa de SQL
	 * @param codigoPostal
	 * @return
	 */
	@Query(value = "SELECT * FROM direcciones WHERE codigo_postal = :codigoPostal", nativeQuery = true)
	List<Direccion> getFindByCpQueyNative(@Param("codigoPostal") String codigoPostal);
	
	/**
	 * Utilizando @Query delcarado en el metodo de la interfaz y utilizando una consulta nativa de SQL y regresando una lista de Objet[]
	 * @param codigoPostal
	 * @return
	 */
	@Query(value = "SELECT * FROM direcciones WHERE codigo_postal = :codigoPostal", nativeQuery = true)
	List<Object[]> getFindByCpOtraQueyNative(@Param("codigoPostal") String codigoPostal);
	
	
	/**
	 * Utilizando @Query delcarado en el metodo de la interfaz y utilizando una consulta nativa de SQL y regresando una lista de Objet[]
	 * Nota: Si se desea mostrar solo algunos campos en especifico de la consulta es forzoso regresar un List<Object[]> o Object[] segun sea el caso 
	 * @param codigoPostal
	 * @return
	 */
	@Query(value = "SELECT calle, codigo_postal FROM direcciones WHERE codigo_postal = :codigoPostal", nativeQuery = true)
	List<Object[]> getFindByCpOtraQueyNativeCampos(@Param("codigoPostal") String codigoPostal);
	
	/**
	 * Utilizando @SqlResultSetMapping
	 * @return
	 */
	List<UserDirResultMaping> userDirResultMaping();
	
	

}
