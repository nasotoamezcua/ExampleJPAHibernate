package com.soto.service;

import java.io.Serializable;
import java.util.List;

public interface IJpaService<T, D, PK extends Serializable> {

	List<D> findAll() throws Exception;

	D create(D d) throws Exception;

	D findById(PK id) throws Exception;
	
	D update(D d) throws Exception;
	
	void delete(D d) throws Exception;
	
	void delete(PK id) throws Exception;

}
