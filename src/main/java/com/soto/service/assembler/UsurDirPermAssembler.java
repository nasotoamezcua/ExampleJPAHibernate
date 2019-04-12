package com.soto.service.assembler;

import org.springframework.stereotype.Component;

import com.soto.dto.UsuarioDTO;
import com.soto.repository.entity.Usuario;

@Component
public class UsurDirPermAssembler extends UsuDirAssembler {
	
	@Override
	public UsuarioDTO getDTOTransform(Usuario mapping) {
		
		if(mapping == null) {
			return null;
		}
		
		UsuarioDTO dto = super.getDTOTransform(mapping);
		
		if(!mapping.getPermisos().isEmpty() &&  mapping.getPermisos().size()>0) {
			dto.setPermisos(getPermisoAssembler().getDTOListTransform(mapping.getPermisos()));
		}
		
		return dto;
	}

}
