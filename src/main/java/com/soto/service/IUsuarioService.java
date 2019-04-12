package com.soto.service;

import java.util.List;

import com.soto.dto.UsuarioDTO;
import com.soto.repository.entity.Usuario;

public interface IUsuarioService extends IJpaService<Usuario, UsuarioDTO, Long> {
	
	List<UsuarioDTO> usuPerJoin() throws Exception;
	List<UsuarioDTO> usuPerFetch() throws Exception;
	List<UsuarioDTO> usuAll() throws Exception;
	List<UsuarioDTO> usuDirAll() throws Exception;
	List<UsuarioDTO> usuDirrPerAll() throws Exception;
	List<UsuarioDTO> setUserAll() throws Exception;
	
}
