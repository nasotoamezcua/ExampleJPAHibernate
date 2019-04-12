package com.soto.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.soto.dto.UsuarioDTO;
import com.soto.repository.dao.IUsuarioDAO;
import com.soto.repository.entity.Usuario;
import com.soto.service.IUsuarioService;
import com.soto.service.assembler.Assembler;
import com.soto.service.assembler.UsuDirAssembler;
import com.soto.service.assembler.UsuarioAssembler;
import com.soto.service.assembler.UsurDirPermAssembler;
import com.soto.utils.MyCollecUtils;

@Service
public class UsuarioServiceImpl  extends JpaService<Usuario, UsuarioDTO, Long> 
	implements IUsuarioService{
	
	/*
	 * ******************************************************************************************
	 * Assembler:																				*
	 * Son de gran importancia al momento de utilizar DTO's										*
	 * 1.- Nos ayuda a convertit un objeto Entidad a un ojeto DTO								*
	 * 2.- Nos ayuda a convertit un objeto DTO a un onjeto Entidad 								*
	 * 3.- Se muestra la respuesta al cliente solo con la informacion que se requiera.			*
	 * NOTA: Se requiere agregar otra capa a la aplicacion ya que los Assembler se tienen que 	*
	 * declarar	como un componente de Spring @Component											*
	 * ******************************************************************************************																
	 */
	
	@Autowired
	private IUsuarioDAO usuarioDAO;
	
	@Autowired
	private UsuarioAssembler usuarioAssembler;
	
	@Autowired
	private UsuDirAssembler usuDirAssembler;
	
	@Autowired
	private UsurDirPermAssembler usurDirPermAssembler;
	
	@Override
	protected JpaRepository<Usuario, Long> getDAO() {
		return usuarioDAO;
	}

	@Override
	protected Assembler<UsuarioDTO, Usuario> getAssembler() {
		return usuarioAssembler;
	}	
	
	@Override
	public List<UsuarioDTO> usuAll() throws Exception {
		List<Usuario> usuarios = MyCollecUtils.toList(usuarioDAO.usuPerJoin());
		List<UsuarioDTO> usuariosDTO = usuarioAssembler.getDTOListTransform(usuarios);
		return usuariosDTO;
	}
	
	@Override
	public List<UsuarioDTO> usuDirAll() throws Exception {
		List<Usuario> usuarios = MyCollecUtils.toList(usuarioDAO.usuPerJoin());
		List<UsuarioDTO> usuariosDTO = usuDirAssembler.getDTOListTransform(usuarios);
		return usuariosDTO;
	}
	
	@Override
	public List<UsuarioDTO> usuDirrPerAll() throws Exception {
		List<Usuario> usuarios = MyCollecUtils.toList(usuarioDAO.usuPerJoin());
		List<UsuarioDTO> usuariosDTO = usurDirPermAssembler.getDTOListTransform(usuarios);
		return usuariosDTO;
	}
	
	@Override
	public List<UsuarioDTO> usuPerJoin() throws Exception {
		List<Usuario> usuarios = MyCollecUtils.toList(usuarioDAO.usuPerJoin());
		List<UsuarioDTO> usuariosDTO = usurDirPermAssembler.getDTOListTransform(usuarios);
		return usuariosDTO;
	}

	@Override
	public List<UsuarioDTO> usuPerFetch() throws Exception {
		List<Usuario> usuarios = MyCollecUtils.toList(usuarioDAO.usuPerFetch());
		List<UsuarioDTO> usuariosDTO = usurDirPermAssembler.getDTOListTransform(usuarios);
		return usuariosDTO;
	}

	@Override
	public List<UsuarioDTO> setUserAll() throws Exception {
		List<Usuario> usuarios = MyCollecUtils.toList(usuarioDAO.setUserAll());
		List<UsuarioDTO> usuariosDTO = usurDirPermAssembler.getDTOListTransform(usuarios);
		return usuariosDTO;
	}	
	
}
