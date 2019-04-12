package com.soto.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.soto.dto.PermisoDTO;
import com.soto.repository.dao.IPermisosDAO;
import com.soto.repository.entity.Permiso;
import com.soto.service.IPermisoService;
import com.soto.service.assembler.Assembler;
import com.soto.service.assembler.PermisoAssembler;

@Service
public class PermisoServiceImpl extends JpaService<Permiso, PermisoDTO, Long>  implements IPermisoService{
	
	@Autowired
	IPermisosDAO permisoDAO;
	
	@Autowired
	PermisoAssembler permisoAssembler;

	@Override
	protected JpaRepository<Permiso, Long> getDAO() {
		return permisoDAO;
	}

	@Override
	protected Assembler<PermisoDTO, Permiso> getAssembler() {
		return permisoAssembler;
	}	
	
}
