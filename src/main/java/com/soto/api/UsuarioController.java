package com.soto.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.soto.dto.UsuarioDTO;
import com.soto.repository.dao.IUsuarioDAO;
import com.soto.repository.entity.Usuario;
import com.soto.service.IUsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	/*
	 * ******************************************************************************************
	 * Serializacion de objetos:																*
	 * En esta capa los @Controller	serializan los objetos Entidad (@Entity) 					*
	 * Pero a gran diferencia de los @RestController las  Entidades (@Entity) que tienen una 	*
	 * relacion Bidireccional, no genera una un cilo infinito  y por lo tanto no se lanzara una *
	 * Exception.																				*
	 * @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)							*
	 * private Direccion direccion;																*
	 * 																							*
	 * En el caso de los @Controller es indistinto mandar a la respuesta directamente un objeto *
	 * Entidad (@Entity) o un objeto DTO.														*
	 * 																							*
	 * Por cuestiones de diseño se utilizan los DTO's											*
	 * Pros: Solo se mostrara la informacion de las propiedades de los objetos que se requiera	*
	 * 																							*
	 * Contras: Al utilizar DTO's y al apoyarse en los Assembler para realizar la conversion de	*
	 * Una Entidad @Entity a un DTO y viceversa se agrega una capa mas al desarrollo de nuestra *
	 * apliacion de nuetra apliacion.															*
	 * 																							*						
	 * Nota: Al igual que los @RestController, la serializacion que el componente @Controller 	*
	 * realiza,  este no toma en cuenta el tipo de carga que exista	en la relacion de las 		*
	 * entidades ya sea EAGER o LAZY															*
	 * ******************************************************************************************																
	 */
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private IUsuarioDAO usuarioDAO;
	
	
	//No se genera un cilo infinito por utilizar en la respuesta un objeto Entidad (@Entity)
	@GetMapping("/findAll")
	public ModelAndView findAllController() throws Exception{
		List<Usuario> usuarios = usuarioDAO.findAll();
		return new ModelAndView("viewUser", "usuarios", usuarios );
	}
	
	/*
	 * UTILIZAR DTO'S
	 */
	
	//Se recomienda utilizar en la respuesta objetos DTO's apoyados por clases Assembler
	
	@GetMapping("/usuarios")
	public ModelAndView usuAllController() throws Exception{
		List<UsuarioDTO> usuariosDTO = usuarioService.usuAll();
		return new ModelAndView("viewUser", "usuarios", usuariosDTO );
	}
	
	@GetMapping("/usuariosDirr")
	public ModelAndView usuDirController() throws Exception{
		List<UsuarioDTO> usuariosDTO = usuarioService.usuDirAll();
		return new ModelAndView("viewUser", "usuarios", usuariosDTO );
	}
	
	@GetMapping("/usuariosDirrPer")
	public ModelAndView usuDirrPerController() throws Exception{
		List<UsuarioDTO> usuariosDTO = usuarioService.usuDirrPerAll();
		return new ModelAndView("viewUser", "usuarios", usuariosDTO );
	}
	
	
	/*
	 * INNER JOIN y FETCH JOIN
	 */
	@GetMapping("/innerjoin")
	public ModelAndView usuPerJoinController() throws Exception{
		List<UsuarioDTO> usuariosDTO = usuarioService.usuPerJoin();
		return new ModelAndView("viewUser", "usuarios", usuariosDTO );
	}
	
	@GetMapping("/fetchJoin")
	public ModelAndView usuPerFetchController() throws Exception{
		List<UsuarioDTO> usuariosDTO = usuarioService.usuPerFetch();
		return new ModelAndView("viewUser", "usuarios", usuariosDTO );
	}
	
	//TODOS LOS USUARIOS CON DIRECCIONES Y PERMISOS
	@GetMapping("/usuariosAll")
	public ModelAndView setUserAllController() throws Exception{
		List<UsuarioDTO> usuariosDTO = usuarioService.setUserAll();
		return new ModelAndView("viewUser", "usuarios", usuariosDTO );
	}

}
