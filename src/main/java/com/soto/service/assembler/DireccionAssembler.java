package com.soto.service.assembler;

import org.springframework.stereotype.Component;

import com.soto.dto.DireccionDTO;
import com.soto.repository.entity.Direccion;

@Component
public class DireccionAssembler extends Assembler<DireccionDTO, Direccion> {

	@Override
	public DireccionDTO getDTOTransform(Direccion mapping) {
		if(mapping == null) {
			return null;
		}
		
		DireccionDTO dto = new DireccionDTO(mapping.getId(), mapping.getCalle(), mapping.getCodigoPostal());
		
		return dto;
	}

	@Override
	public Direccion getMappingTransform(DireccionDTO dto) {
		
		Direccion entity = new Direccion(dto.getCalle(), dto.getCodigoPostal());
		
		return entity;
	}

}
