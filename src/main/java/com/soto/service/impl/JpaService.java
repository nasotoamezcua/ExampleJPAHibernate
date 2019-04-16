package com.soto.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soto.service.IJpaService;
import com.soto.service.assembler.Assembler;

public abstract class JpaService<T, D, PK extends Serializable> 
	implements IJpaService<T, D, PK> {
	
	protected  abstract JpaRepository<T, PK> getDAO();
	
	protected abstract Assembler<D, T> getAssembler();

	@Override
	public List<D> findAll() throws Exception {
		List<T> list = getDAO().findAll();
		List<D> list_ = getAssembler().getDTOListTransform(list);
		return list_;
	}
	
	@Override
	public D findById(PK id) throws Exception {
		T t = getDAO().findOne(id);
		D d = getAssembler().getDTOTransform(t);
		return d;
	}

	@Override
	public D create(T t) throws Exception {
		D d_ = getAssembler().getDTOTransform(getDAO().save(t));
		return d_;
	}

	@Override
	public D update(T t) throws Exception {
		D d_ = getAssembler().getDTOTransform(getDAO().save(t));
		return d_;
	}
	
	@Override
	public void delete(T t) throws Exception {
		getDAO().delete(t);
	}

	@Override
	public void delete(PK id) throws Exception {
		getDAO().delete(id);
	}

}
