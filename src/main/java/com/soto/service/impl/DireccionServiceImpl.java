package com.soto.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.soto.dto.DireccionDTO;
import com.soto.repository.dao.IDireccionDAO;
import com.soto.repository.entity.Direccion;
import com.soto.service.IDireccionService;
import com.soto.service.assembler.Assembler;
import com.soto.service.assembler.DireccionAssembler;

@Service
public class DireccionServiceImpl extends JpaService<Direccion, DireccionDTO, Long> 
	implements IDireccionService{
	
	@Autowired
	IDireccionDAO direccionDao;
	
	@Autowired
	DireccionAssembler direccionAssemble;

	@Override
	protected JpaRepository<Direccion, Long> getDAO() {
		return direccionDao;
	}

	@Override
	protected Assembler<DireccionDTO, Direccion> getAssembler() {
		return direccionAssemble;
	}
	
}
