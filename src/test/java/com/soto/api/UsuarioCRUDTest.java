package com.soto.api;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.soto.repository.entity.Direccion;
import com.soto.repository.entity.Permiso;
import com.soto.repository.entity.Permiso.Estatus;
import com.soto.repository.entity.Usuario;
import com.soto.service.IUsuarioService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsuarioCRUDTest {
	
	@Autowired
	private IUsuarioService service;
	
	/*
	 * CREAD
	 */
	@Test
	public void saveUsuario() throws Exception {
		Usuario usuario = new Usuario("Nestor", "tepos05", "nasa050684");
		service.create(usuario);
	}
	
	@Test
	public void saveUsuarioDireccion() throws Exception{
		Direccion direccion = new Direccion("Lucrecia Toriz", "15440");
		Usuario usuario = new Usuario("Alejandro", "tepos05", "nasa050684");
		usuario.setDireccion(direccion);
		service.create(usuario);
	}
	
	@Test
	public void saveUsuarioDireccionPermisos() throws Exception{
		ArrayList<Permiso> permisos = new ArrayList<Permiso>();
		permisos.add(new Permiso("Lectura Archivos", Estatus.ACTIVO));
		permisos.add(new Permiso("Creacion Archivos", Estatus.ACTIVO));
		Direccion direccion = new Direccion("Juana de Arco", "15430");
		Usuario usuario = new Usuario("Fernado", "fers07", "fers240985");
		usuario.setPermisos(permisos);
		usuario.setDireccion(direccion);
		service.create(usuario);
	}
	
	/*
	 * UPDATE
	 */
	
	@Test
	public void updateTest() throws Exception {
		Usuario usuario = new Usuario();
		usuario.setId(15L);
		usuario.setUsername("alex55");
		service.update(usuario);
	}
	
	/*
	 * DELETE
	 */
	
	/*
	 * PARA BORRAR EN CASCADA UTILIZANDO EL METODO DELETE QUE PASA COMO PARAMETRO UN OBJETO
	 * ES NECESARIO TAMBIEN SETEAR LOS ID DE LOS OBJETOS QUE SE BORRARAN:
	 * EN ESTE CASO DIRECCION Y PERMISO
	 */
	@Test
	public void deleteTest() throws Exception{
		Usuario usuario = new Usuario();
		usuario.setId(22L);
		
		Direccion direccion = new Direccion();
		direccion.setId(14L);
		
		
		ArrayList<Permiso> permisos = new ArrayList<Permiso>();
		Permiso permiso1 = new Permiso();
		permiso1.setId(16L);
		permisos.add(permiso1);
		
		Permiso permiso2 = new Permiso();
		permiso2.setId(17L);
		permisos.add(permiso2);
		
		usuario.setDireccion(direccion);
		usuario.setPermisos(permisos);
		
		service.delete(usuario);
	}
	
	/*
	 * PARA BORRAR EN CASCADA UTILIZANDO EL METODO DELETE QUE PASA COMO PARAMETRO EL ID
	 * SOLO SE REQUIERE SETEAR EL ID DE OBJETO PRINCIPAL QUE SE BORRARA:
	 * EN ESTE CASO USUAIRO
	 */
	
	@Test
	public void deletePKTest() throws Exception{
		service.delete(21L);
	}

}
