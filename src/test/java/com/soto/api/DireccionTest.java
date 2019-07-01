package com.soto.api;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.soto.beans.DireccionBean;
import com.soto.modelo.UserDirResultMaping;
import com.soto.repository.dao.IDireccionDAO;
import com.soto.repository.entity.Direccion;
import com.soto.service.IDireccionBeanService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DireccionTest {
	
	@Autowired
	private IDireccionDAO dao;
	
	@Autowired
	private IDireccionBeanService service;
	
	/*
	 * METODOS DEL DAO
	 */
	
	@Test
	public void getFindByCalleTest(){
		List<Direccion> direcciones = dao.getFindByCalle("Lucrecia Toriz");
		for(Direccion d : direcciones){
			System.out.println(d);
		}
	}
	
	@Test
	public void getFindByCalleQueyTest(){
		List<Direccion> direcciones = dao.getFindByCalleQuey("Lucrecia Toriz");
		for(Direccion d : direcciones){
			System.out.println(d);
		}
	}
	
	@Test
	public void getFindByCodigoPostalTest(){
		List<Direccion> direcciones = dao.getFindByCodigoPostal("15440");
		for(Direccion d : direcciones){
			System.out.println(d);
		}
	}
	
	@Test
	public void getSinResultClassFindByCodigoPostalTest(){
		List<Direccion> direcciones = new ArrayList<Direccion>();
		List<Object[]> objetos = dao.getSinResultClassFindByCodigoPostal("15440");
		
		
		for(Object[] o : objetos){
			Direccion d = new Direccion((String) o[1], (String) o[2]);
			direcciones.add(d);
			System.out.println("Calle: "+ (String) o[1] + "\t CP:" + (String) o[2]);
			System.out.println(d);
		}
	}
	
	@Test
	public void getFindByCpQueyNativeTest(){
		List<Direccion> direcciones = dao.getFindByCpQueyNative("15440");
		for(Direccion d : direcciones){
			System.out.println(d);
		}
	}
	
	@Test
	public void getFindByCpOtraQueyNativeTest(){
		List<Direccion> direcciones = new ArrayList<Direccion>();
		List<Object[]> objetos = dao.getFindByCpOtraQueyNative("15440");
		
		
		for(Object[] o : objetos){
			Direccion d = new Direccion((String) o[1], (String) o[2]);
			direcciones.add(d);
			System.out.println("Calle: "+ (String) o[1] + "\t CP:" + (String) o[2]);
			System.out.println(d);
		}
	}
	
	@Test
	public void getFindByCpOtraQueyNativeCamposTest(){
		List<Direccion> direcciones = new ArrayList<Direccion>();
		List<Object[]> objetos = dao.getFindByCpOtraQueyNativeCampos("15440");
		
		
		for(Object[] o : objetos){
			Direccion d = new Direccion((String) o[0], (String) o[1]);
			direcciones.add(d);
			System.out.println("Calle: "+ (String) o[0] + "\t CP:" + (String) o[1]);
			System.out.println(d);
		}
	}
	
	/*
	 * METODOS SERVICE UTILIZANDO IDireccionBeanService
	 */
	
	@Test
	public void getFindByCpOtraQueyNativeServiceTest(){
		List<DireccionBean> beans = service.getFindByCpOtraQueyNativeService("15440");
		for(DireccionBean b : beans){
			System.out.println(b);
		}
	}
	
	/*
	 * Utilizando @SqlResultSetMapping
	 */
	@Test
	public void userDirResultMapingTest(){
		List<UserDirResultMaping> lista = dao.userDirResultMaping();
		for(UserDirResultMaping rm : lista){
			System.out.println(rm);
		}
	}

}
