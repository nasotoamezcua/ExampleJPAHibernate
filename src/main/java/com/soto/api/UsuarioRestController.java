package com.soto.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soto.dto.UsuarioDTO;
import com.soto.repository.dao.IUsuarioDAO;
import com.soto.repository.entity.Usuario;
import com.soto.service.IUsuarioService;

@RestController
@RequestMapping("/usuarioRest")
public class UsuarioRestController {
	
	/*
	 * ******************************************************************************************
	 * Serializacion de objetos:																*	
	 * En esta capa los compopnentes @RestController serializan los objetos Entidad (@Entity) 	*
	 * para que se manden al navegador como una respuesta en formato JSON.						*
	 * 																							*
	 * Este comportamiento de serializacion en los objetos de entidades (@Entity) 				*
	 * puede ocasionar resultados no esperados, el problema mas comun es el siguiente:			*
	 * 																							*			
	 * 1.-Cuando se tiene en la Entidad (@Entity) una relacion Bidireccional se genera una 		*
	 * respuesta con informacion que produce un cilo infinito  y esto lanza una Exception: 		*	
	 * 																							*
	 * Solucion 1: Utilizar la etiqueta @JsonIgnore												*
	 * Pros:Elimina el problema del cliclo infinito												*
	 * Utilizar la etqueta @JsonIgnore en la propiedad de la Entidad (@Entity) que tenga la 	*
	 * relacion Bidireccional, ejemplo:															*
	 * 																							*
	 * @JsonIgnore																				*
	 * @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)							*
	 * private Direccion direccion;																*
	 * 																							*
	 * Nota: Es muy importante mencionar que al cambiar el tipo de carga de EAGER a LAZY 		*
	 * no soluciona el problema del ciclo infinito ya que debido a la serializacion que el		*
	 * componente @RestController realiza,  este no toma en cuenta el tipo de carga que exista	*
	 * en la relacion de las entidades ya sea EAGER o LAZY										*
	 * 								 															*
	 * @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)								*
	 * private Direccion direccion;																*
	 * La solucion es utilizar la etiquetra @JsonIgnore	en la propiedad de la Entidad (@Entity) *
	 * que tenga la	relacion Bidireccional, ejemplo:											*
	 * @JsonIgnore																				*		
	 * @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)								*	
	 * private Direccion direccion;																*
	 * 																							*
	 * Contras:																					*
	 * El problema con esta solucion es que ocultara la informacion del objeto Direccion,		* 
	 * pero si en algun momento se desea mostrar la informacion del objeto Direccion, 			*
	 * se tendra que eliminar la declaracion de la etiqueta @JsonIgnore y nuevamente se tendra 	*
	 * el problema del ciclo infinito.															*
	 * 																							*
	 * Solucion 2 (Recomendada): Utilizar Clases DTO's:											*
	 * Pros: Solo se mostrara la informacion de las propiedades de los objetos que se requiera,	*
	 * evitando asi el problema de los ciclos infinitos.										*
	 * 																							*
	 * Contras: Se agrega una capa mas al desarrollo de nuestra apliacion.						*
	 * 																							*
	 * Bonus:																					*
	 * Para crear las clases DTO se recomienda apoyarse en las clases Assembler					*
	 * Assembler:																				*
	 * Su principal funcion es convertir un objeto Entidad (@Entity) a un objeto DTO y viceversa*
	 * En los Assembler se decide que propiedades de la Entidad se requieren convertir al DTO.	*
	 * ******************************************************************************************																
	 */
		
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private IUsuarioDAO usuarioDAO;
	
	//Se genera un cilo infinito por utilizar en la respuesta un objeto Entidad (@Entity)
	@GetMapping("/findAll")
	public ResponseEntity<List<Usuario>> findAllRest() throws Exception{
		return new ResponseEntity<List<Usuario>>(usuarioDAO.findAll(), HttpStatus.OK);
	}
	
	/*
	 * UTILIZAR DTO'S
	 */
	
	//Se recomienda utilizar en la respuesta objetos DTO's apoyados por clases Assembler
	
	@GetMapping("/usuarios")
	public ResponseEntity<List<UsuarioDTO>> usuAllRest() throws Exception{
		List<UsuarioDTO> usuariosDTO = usuarioService.usuAll();
		return new ResponseEntity<List<UsuarioDTO>>(usuariosDTO, HttpStatus.OK);
	}
	
	@GetMapping("/usuariosDirr")
	public ResponseEntity<List<UsuarioDTO>> usuDirController() throws Exception{
		List<UsuarioDTO> usuariosDTO = usuarioService.usuDirAll();
		return new ResponseEntity<List<UsuarioDTO>>(usuariosDTO, HttpStatus.OK);
	}
	
	@GetMapping("/usuariosDirrPer")
	public ResponseEntity<List<UsuarioDTO>> usuDirrPerController() throws Exception{
		List<UsuarioDTO> usuariosDTO = usuarioService.usuDirrPerAll();
		return new ResponseEntity<List<UsuarioDTO>>(usuariosDTO, HttpStatus.OK);
	}
	
	/*
	 * INNER JOIN y FETCH JOIN
	 */
	
	@GetMapping("/innerjoin")
	public ResponseEntity<List<UsuarioDTO>> usuPerJoinRest() throws Exception{
		List<UsuarioDTO> usuariosDTO =usuarioService.usuPerJoin();
		return new ResponseEntity<List<UsuarioDTO>>(usuariosDTO, HttpStatus.OK);
	}
	
	@GetMapping("/fetchJoin")
	public ResponseEntity<List<UsuarioDTO>> usuPerFetchRest() throws Exception{
		List<UsuarioDTO> usuariosDTO = usuarioService.usuPerFetch();
		return new ResponseEntity<List<UsuarioDTO>>(usuariosDTO, HttpStatus.OK);
	}
	
	//TODOS LOS USUARIOS CON DIRECCIONES Y PERMISOS
	@GetMapping("/usuariosAll")
	public ResponseEntity<List<UsuarioDTO>> setUserAllRest() throws Exception{
		List<UsuarioDTO> usuariosDTO = usuarioService.setUserAll();
		return new ResponseEntity<List<UsuarioDTO>>(usuariosDTO, HttpStatus.OK);
	}

}
