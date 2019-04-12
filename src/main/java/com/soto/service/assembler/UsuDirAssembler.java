package com.soto.service.assembler;

import org.springframework.stereotype.Component;

import com.soto.dto.UsuarioDTO;
import com.soto.repository.entity.Usuario;

@Component
public class UsuDirAssembler extends UsuarioAssembler  {
	
	@Override
	public UsuarioDTO getDTOTransform(Usuario mapping) {
		if(mapping == null) {
			return null;
		}
		
		UsuarioDTO dto = super.getDTOTransform(mapping);
		if(mapping.getDireccion() != null) {
			dto.setDireccion(getDireccionAssembler().getDTOTransform(mapping.getDireccion())); 
		}
		
		return dto;
	}

}
