package com.soto.service.assembler;

import org.springframework.stereotype.Component;

import com.soto.dto.PermisoDTO;
import com.soto.repository.entity.Permiso;

@Component
public class PermisoAssembler extends Assembler<PermisoDTO, Permiso>{

	@Override
	public PermisoDTO getDTOTransform(Permiso mapping) {
		
		if(mapping == null) {
			return null;
		}
		
		PermisoDTO dto = new PermisoDTO(mapping.getId(), mapping.getNombre(), mapping.getEstatus());
		
		return dto;
	}

	@Override
	public Permiso getMappingTransform(PermisoDTO dto) {
		
		Permiso entity = new Permiso(dto.getNombre(), dto.getEstatus());
		
		return entity;
	}

}
