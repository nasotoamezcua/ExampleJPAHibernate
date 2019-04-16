package com.soto.service;

import java.io.Serializable;
import java.util.List;

public interface IJpaService<T, D, PK extends Serializable> {

	List<D> findAll() throws Exception;
	
	D findById(PK id) throws Exception;

	D create(T t) throws Exception;	
	
	D update(T t) throws Exception;
	
	void delete(T t) throws Exception;
	
	void delete(PK id) throws Exception;

}
