package com.soto.service.assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.soto.dto.UsuarioDTO;
import com.soto.repository.entity.Usuario;

@Component
public class UsuarioAssembler extends Assembler<UsuarioDTO, Usuario>{
	
	@Autowired
	private DireccionAssembler direccionAssembler;
	
	@Autowired
	private PermisoAssembler permisoAssembler;

	@Override
	public UsuarioDTO getDTOTransform(Usuario mapping) {
		if(mapping == null) {
			return null;
		}	
		UsuarioDTO dto = new UsuarioDTO(mapping.getId(), mapping.getNombre(), mapping.getUsername(), mapping.getPassword());
		return dto;
	}
	

	@Override
	public Usuario getMappingTransform(UsuarioDTO dto) {
		Usuario entity = new Usuario(dto.getNombre(), dto.getUsername(), dto.getPassword());
		
		if(dto.getDireccion() != null) {
			entity.setDireccion(direccionAssembler.getMappingTransform(dto.getDireccion()));
		}
		
		if(dto.getPermisos()!= null && !dto.getPermisos().isEmpty()) {
			entity.setPermisos(permisoAssembler.getMappingListTransform(dto.getPermisos()));
		}
		
		return entity;
	}

	protected DireccionAssembler getDireccionAssembler() {
		return direccionAssembler;
	}


	protected PermisoAssembler getPermisoAssembler() {
		return permisoAssembler;
	}
	
}
