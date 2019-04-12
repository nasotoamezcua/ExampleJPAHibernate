package com.soto.api;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.soto.dto.UsuarioDTO;
import com.soto.modelo.UsuarioDireccion;
import com.soto.repository.dao.IUsuarioDAO;
import com.soto.repository.entity.Direccion;
import com.soto.repository.entity.Permiso;
import com.soto.repository.entity.Usuario;
import com.soto.service.IUsuarioService;
import com.soto.utils.MyCollecUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsuarioTest {
	
	@Autowired
	IUsuarioDAO usuarioDAO;
	
	@Autowired
	IUsuarioService usuarioService;
	
	/**
	 * EJEMPLOS JOINS
	 */
	
	@Test
	public void innerJoinTest() {
		List<Usuario> u = usuarioDAO.innerJoin();
		System.out.println(u);
	}
	
	@Test
	public void leftOuterJoinTest() {
		List<Usuario> u = usuarioDAO.leftOuterJoin();
		System.out.println(u);
	}
	
	@Test
	public void innerRigtJoinTest() {
		List<Usuario> u = usuarioDAO.rightOuterjoin();
		System.out.println(u);
	}
	
	@Test
	public void innerJoinWithTest() {
		List<Usuario> us = usuarioDAO.innerJoinWith();
		System.out.println(us);
	}
	
	@Test
	public void innerJoinFetchTest() {
		List<Usuario> us = usuarioDAO.innerJoinFetch();
		for(int i=0; i<us.size();i++ ) {
			System.out.println(us.get(i));
			List<Permiso> ps = us.get(i).getPermisos();
			for(int j =0; j<ps.size();j++) {
				System.out.println(ps.get(i));
			}
		}	
	}
	
	@Test
	public void joinImplicitoTest() {
		List<Usuario> us = usuarioDAO.joinImplicito();
		System.out.println(us);
	}
	
	/**
	 * EJEMPLOS SELECT
	 */
	
	@Test
	public void selectDireccionTest() {
		List<Direccion> dirs = usuarioDAO.objetosDireccion();
		System.out.println(dirs);
	}
	
	@Test
	public void objetosStringTest() {
		List<String> s = usuarioDAO.objetosString();
		System.out.println(s);
	}
	
	@Test
	public void objetosLongTest() {
		List<Long> l = usuarioDAO.objetosLong();
		System.out.println(l);
	}
	
	@Test
	public void arraysObjetosTest() {
		List<Object[]> objetos = usuarioDAO.arraysObjetos();
		for(int i =0; i<objetos.size(); i++) {
			System.out.println("id: " + objetos.get(i)[0] 
					+ " Nombre: " + objetos.get(i)[1]
					+ " UserName: " + objetos.get(i)[2]
					+ " Password: " + objetos.get(i)[3]);
		}
	}
	
	@Test
	public void listObjetosTest() {
		List<List<Object>> objetos = usuarioDAO.listObjetos();
		for(int i =0; i<objetos.size(); i++) {
			System.out.println("id: " + objetos.get(i).get(0) 
					+ " Nombre: " + objetos.get(i).get(1) 
					+ " UserName: " + objetos.get(i).get(2) 
					+ " Password: " + objetos.get(i).get(3));
		}
	}
	
	@Test
	public void mapObjetosTest() {
		List<Map<String, Object>> objetos = usuarioDAO.mapObjetos();
		for(int i=0; i<objetos.size(); i++) {
			Map<String, Object> mapa = objetos.get(i);
			System.out.println("Datos del mapa" + i);
			Set<String> llaves = mapa.keySet();
			for(Iterator<String> it = llaves.iterator(); it.hasNext();) {
				String llaveActual = it.next();
				System.out.println("\tLlave: " + llaveActual + ", valor: " + mapa.get(llaveActual));
			}
		}
	}
	
	@Test
	public void constructorObjetosTest() {
		List<UsuarioDireccion> objetos = usuarioDAO.constructorObjetos();
		for(int i=0; i<objetos.size(); i++) {
			UsuarioDireccion ud = objetos.get(i);
			System.out.println("->" + ud.getNombre() + ", permisos: " + ud.getNumeroPermisos());
		}
	}
	
	/**
	 * EJEMPLO FUNCIONES DE AGREGACION
	 */
	@Test
	public void countGroupByTest() {
		List<UsuarioDireccion> objetos = usuarioDAO.countGroupBy();
		for(int i=0; i<objetos.size(); i++) {
			UsuarioDireccion ud = objetos.get(i);
			System.out.println("->" + ud.getNombre() + ", permisos: " + ud.getNumeroPermisos());
		}
	}
	
	@Test
	public void countSinGroupByTest() {
		UsuarioDireccion objeto = usuarioDAO.countSinGroupBy();
		System.out.println("->" + objeto.getNombre() + ", permisos: " + objeto.getNumeroPermisos());
	}
	
	/**
	 * EJEMPLOS CLAUSALA WHERE
	 */
	
	@Test
	public void whereRestriccionTest() {
		List<Permiso> where = usuarioDAO.whereRestriccion();
		System.out.println(where);
	}
	
	@Test
	public void wherePropiedadTest() {
		Usuario where = usuarioDAO.wherePropiedad();
		System.out.println(where);
	}
	
	@Test
	public void whereObjetoTest() {
		List<Usuario> where = usuarioDAO.whereObjeto();
		System.out.println(where);
	}
	
	@Test
	public void comparacionInstanciaTest() {
		List<Usuario> where = usuarioDAO.comparacionInstancia();
		System.out.println(where);
	}
	
	/**
	 * EJEMPLOS DE SUBCONSULTAS
	 */
	
	@Test
	public void sobcounsultasTest() {
		List<Usuario> u = usuarioDAO.sobcounsultas();
		System.out.println(u);
	}
	
	/**
	 * EJEMPLOS DE SINTAXIS ROW VALUE CONSTRUCTOR
	 */
	
	@Test
	public void rowValueConstructorTest() {
		List<Usuario> u = usuarioDAO.rowValueConstructor();
		System.out.println(u);
	}
	
	/**
	 * EJEMPLOS DE COMPONENTES
	 */
	
	@Test
	public void componentesTest() {
		List<Object[]> u = usuarioDAO.componentes();
		for(int i = 0; i<u.size(); i++) {
			Object[] objetos = u.get(i);
			System.out.println("Nombre: " + objetos[0] + ", calle: " + objetos[1] + ", codigo postal: " + objetos[2]);
		}
	}
	
	@Test
	public void componentesWhereTest() {
		Usuario u = usuarioDAO.componentesWhere();
		System.out.println(u);
	}
	
	/**
	 * EJEMPLOS TIPS Y TRUCOS
	 */
	@Test
	public void contarTest() {
		Long c = usuarioDAO.contar();
		System.out.println("Numero de resultados que regresara la consulta: " +c);
	}
	
	@Test
	public void tamanoColeccionTest() {
		List<Usuario> u = usuarioDAO.tamanoColeccion();
		System.out.println(u);
	}
	
	@Test
	public void tamanoCondicionTest() {
		List<Usuario> u = usuarioDAO.tamanoCondicion();
		System.out.println(u);
	}
	
	@Test
	public void tamanoCondicionNosubconsultasTest() {
		List<Usuario> u = usuarioDAO.tamanoCondicionNosubconsultas();
		System.out.println(u);
	}
	
	/**
	 * EJEMPLOS PARAMETROS EN HQL
	 */
	
	@Test
	public void parametrosNamedTest() {
		Usuario u = usuarioDAO.parametrosNamed("usr001", "abcdefg");
		System.out.println(u);
	}
	
	/**
	 * OPTIMIZAR CONSULTAS HQL
	 */
	
	@Test
	public void usuAllTest() {
		List<Usuario> us = usuarioDAO.usuAll();
		System.out.println(us);
	}
	
	@Test
	public void usuAlljoinTest() {
		List<Usuario> us = usuarioDAO.usuAlljoin();
		System.out.println(us);
	}
	
	@Test
	public void usuAllFetchTest() {
		List<Usuario> us = usuarioDAO.usuAllFetch();
		System.out.println(us);
	}
	
	@Test
	public void usuPerJoinTest() {
		List<Usuario> us = MyCollecUtils.toList(usuarioDAO.usuPerJoin()) ;
		System.out.println(us);
	}
	
	@Test
	public void usuPerFetchTest() {
		List<Usuario> us = MyCollecUtils.toList(usuarioDAO.usuPerFetch());
		System.out.println(us);
	}
	
	/**
	 * SERVICE
	 * @throws Exception
	 */
	@Test
	public void usuPerJoinServiceTest() throws Exception {
		List<UsuarioDTO> us = usuarioService.usuPerJoin();
		System.out.println(us);
	}
	
	@Test
	public void usuPerFetchServiceTest() throws Exception {
		List<UsuarioDTO> us = usuarioService.usuPerFetch();
		for(int i = 0; i<us.size(); i++) {
			System.out.println(us.get(i).getPermisos());
		}
	}
}
