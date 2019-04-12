package com.soto.repository.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.soto.modelo.UsuarioDireccion;
import com.soto.repository.entity.Direccion;
import com.soto.repository.entity.Permiso;
import com.soto.repository.entity.Usuario;

@Repository
public interface IUsuarioDAO  extends JpaRepository<Usuario, Long>{
	
	/* 
	 * Referencias:
	 * https://www.javatutoriales.com/2009/09/hibernate-parte-7-hql-primera-parte.html
	 * https://www.javatutoriales.com/2010/05/hibernate-parte-8-hql-segunda-parte.html
	 */
	
	/*
	 * ******************************************************************************************
	 * Asociaciones y Joins																		*
	 * Inner Join: Muestra todos los registros coincidentes de las dos tablas a unir			*
	 * Left Outer Join: Solo mostrara los registros que existan en la tabla del lado izquierdo.	*
	 * Right Outer Join: Solo mostrata los registros que existan en la tabla del lado derecho.	*
	 * Inner join fetch: Este fetch join "sobreescribe" la declaración de fetch LAZY a EAGER.	*
	 * ******************************************************************************************																
	 */
	
	//inner join tambien funciona solo dejando la palabra join
	@Query("SELECT u FROM Usuario u inner join u.direccion")
	List<Usuario> innerJoin();
	
	@Query("SELECT u FROM Usuario u right outer join u.direccion")
	List<Usuario> rightOuterjoin();
	
	@Query("SELECT u FROM Usuario u left outer join u.direccion")
	List<Usuario> leftOuterJoin();
	
	//Para proporcionar condiciones extra al join se utiliza la palabra reservada WITH
	//La relacion de Usuarios con la coleccion Permisos esta declarada como Lazy
	//Solo se recuperan los objetos Usuario y Direccion
	//Si tratamos de recuperar la coleccion de objetos Permiso
	//Se obtiene la siguiente Excepcion:LazyInitializationException
	@Query("SELECT u FROM Usuario u inner join u.permisos as p WITH p.estatus = 1")
	List<Usuario> innerJoinWith();
	
	//inner join fetch:
	//Con este tipo de join: podemos obtener objetos mapeados que esten declarados como LAZY. 
	//Lo que hace join fetch es cambiar de LAZY A EAGER (Solo lo hace para esta consulta)
	//En comparacion con "inner join" , en un "inner join fetch": 
	//no se puede utilizar la pabra reservada WITH, se necesita utilizar la palabra reservada WHERE
	//Se puede escribir de cualquiera de las siguientes formas y la funcionalidad es la misma:
	//A) inner join fetch
	//B) left join fetch
	//C) join fetch
	@Query("SELECT u FROM Usuario u "
			+ " left join fetch u.direccion as d "
			+ " join fetch u.permisos as p WHERE p.estatus = 1 ")
	List<Usuario> innerJoinFetch();
	
	//Join Implicito
	// SELECT u FROM Usuario u inner join u.direccion as d WHERE d.codigoPostal LIKE '%12%'
	//SELECT u FROM Usuario u WHERE u.direccion.codigoPostal LIKE '%12%'
	//Este tipo de joins  solo puede hacerse cuando tenemos una relación uno a uno o muchos a uno, 
	//o sea, no funciona cuando la propiedad es una colección:
	//SELECT u FROM Usuaio u WHERE u.permiso.estatus = 0
	@Query("SELECT u FROM Usuario u WHERE u.direccion.codigoPostal LIKE '%12%'")
	List<Usuario> joinImplicito();
	
	/*
	 * ******************************************************************************************
	 * Consultas SELECT con una y varias columnas.													*
	 * Cuando recuperamos solo un atributo de un objeto, 										*
	 * recuperamos una lista de objetos del tipo del atributo que queremos recuperar.			*
	 * (si es String recuperamos Strings, si es int recuperamos ints, etc.)						*
	 * 																							*
	 * Sin embargo, si recuperamos más de un atributo de un objeto, Hibernate NO nos regresa 	* 
	 * una lista del tipo de objeto (en este caso "Usuario"), si no que nos regresa 			*
	 * una lista de arreglos de objetos, o sea, una lista en la que cada uno de los elementos	* 
	 * es un arreglo de objetos.																*
	 * Cada uno de los objetos del arreglo representa uno de los valores recuperados.			*
	 * (recuperamos una lista de arreglos de objetos)											*
	 * Este tipo de consultas se pueden recuperar tambien de la siguientes formas:				*
	 * 1 Una lista de listas de objetos: SELECT new list (parametros) FROM Entidad alias		*
	 * 2 Una Lista de map de objetos: SELECT new map(parametros) FROM	Entidad alias			*
	 * 3 pasando los valores por medio de su constructor:										*
	 * SELECT NEW Qualified Name de la clase(parametros del constructor de la clase) 
	 * FROM	Entidad alias																		*
	 * ******************************************************************************************																
	 */
	
	//Regresa una lista de objetos Dieccion
	@Query("SELECT u.direccion FROM Usuario as u")
	List<Direccion> objetosDireccion();
	
	//Regresa una lista de objetos String
	@Query("SELECT u.nombre FROM Usuario as u")
	List<String> objetosString();
	
	//Regresa una lista de objetos Long
	@Query("SELECT u.id FROM Usuario as u")
	List<Long> objetosLong();
	
	//Regresa una lista de arreglo de objetos
	@Query("SELECT  u.id, u.nombre, u.username, u.password FROM Usuario as u")
	List<Object[]> arraysObjetos();
	
	//1 Regresa una lista de listas de objetos
	@Query("SELECT new list(u.id, u.nombre, u.username, u.password) FROM Usuario as u")
	List<List<Object>> listObjetos();
	
	//2 Regresa una lista de map de objetos
	@Query("SELECT new map(u.id as id, u.nombre as nombre, u.password as pass) FROM Usuario as u")
	List<Map<String, Object>> mapObjetos();
	
	//3 Regresa una kista de objetos de la clase (UsuarioDireccion) del constructor que se utilizo como parametro
	// se utiliza un left outer join para que nos regrese (en este caso) 
	//los datos de los Usuarios aunque estos no tengan ni Direcciones ni Permisos
	@Query("SELECT NEW com.soto.modelo.UsuarioDireccion(u.nombre, d.calle, d.codigoPostal, COUNT(p)) "
			+ "FROM Usuario u "
			+ "left outer join u.direccion as d "
			+ "left outer join u.permisos as p "
			+ "GROUP BY u.nombre")
	List<UsuarioDireccion> constructorObjetos();
	
	/*
	 * ******************************************************************************************
	 * Funciones de Agregacion:																	*
	 * las funciones de agregación operan sobre grupos de datos o resultados, por lo tanto, 	*
	 * cada vez que usemos una función de agregación obtendremos como resultado una única fila	*
	 * Si no queremos que esto ocurra deberemos agrupar nuestros resultados 					*
	 * (usando la clausula GROUP BY) usando el criterio apropiado.								*
	 * Debemos tener cuidado cuando hacemos uso de las funciones de agregación:					*	
	 * 	AVG(…), SUM(…), MIN(…), MAX(…)															*
	 *	COUNT(*)																				*
	 *	COUNT(…), COUNT(DISTINCT …), COUNT(ALL …)												*
	 * ******************************************************************************************																
	 */
	
	//Regresa una la lista de objetos UsuarioDireccion y el resultado es el esperado
	@Query("SELECT NEW com.soto.modelo.UsuarioDireccion(u.nombre, d.calle, d.codigoPostal, COUNT(p)) "
			+ "FROM Usuario u "
			+ "left outer join u.direccion as d "
			+ "left outer join u.permisos as p "
			+ "GROUP BY u.nombre")
	List<UsuarioDireccion> countGroupBy();
	
	//Regresa un solo objeto UsuarioDireccion y el resultado no es el esperado
	@Query("SELECT NEW com.soto.modelo.UsuarioDireccion(u.nombre, d.calle, d.codigoPostal, COUNT(p)) "
			+ "FROM Usuario u "
			+ "left outer join u.direccion as d "
			+ "left outer join u.permisos as p ")
	UsuarioDireccion countSinGroupBy();
	
	/*
	 * ******************************************************************************************
	 * La clausula WHERE:																		*
	 * Esta clausula nos permite reducir o limitar el número de registros que recuperamos 		*
	 * al hacer una consulta a nuestra base de datos.											*
	 * El trabajo con la clausula WHERE es en realidad muy sencillo, solo debemos colocar 		*
	 * el nombre del atributo que queremos restringir, y la restricción que tendrá.				*
	 * Podemos colocar más de una restricción, separando cada una con la palabra reservada AND.	*
	 * Algo interesante en HQL es que el operador "=" 											*
	 * no solo sirve para comparar valores de atributos, 										*
	 * sino que también nos sirva para comparar instancias *1:									*
	 * ******************************************************************************************																
	 */
	
	@Query("SELECT p FROM Permiso p WHERE p.id> 2 AND p.id < 5")
	List<Permiso> whereRestriccion();
	
	@Query("SELECT u FROM Usuario u WHERE u.direccion.codigoPostal = '12345'")
	Usuario wherePropiedad();
	
	@Query("SELECT u FROM Usuario u WHERE u.direccion IS NOT NULL")
	List<Usuario> whereObjeto();
	
	//1*Comparacion de instancias con el operador "="
	@Query("SELECT u1 FROM Usuario u1, Usuario u2 WHERE u1.direccion  = u2.direccion")
	List<Usuario> comparacionInstancia();
	
	
	/*
	 * ******************************************************************************************
	 * Subconsultas:																			*
	 * Las subconsultas deben estar encerradas entre paréntesis.								*
	 * las subconsultas solo pueden estar dentro de clausulas SELECT o WHERE.					*
	 * ******************************************************************************************																
	 */
	
	@Query("SELECT DISTINCT(u) "
			+ "FROM Usuario u "
			+ "inner join u.permisos as p "
			+ "WHERE p in (SELECT p FROM Permiso p WHERE p.estatus = 1)")
	List<Usuario> sobcounsultas();
	
	
	/*
	 * ******************************************************************************************
	 * SINTAXIS ROW VALUE CONSTRUCTOR:															*
	 * HQL soporta el uso de la sintaxis de SQL ANSI “row value constructor”, 					*
	 * algunas veces llamado sintaxis “AS tuple”, aun cuando el manejador de base de datos 		*
	 * que estemos usando podría no soportar dicha notación. 									*
	 * Aquí, por lo general nos referimos a comparaciones multi-valuadas, 						*
	 * típicamente asociadas con componentes (Relaciones de Mapeos).							*
	 * También puede ser usada en subconsultas que necesitan comparar multiples valores			*
	 * ******************************************************************************************																
	 */
	
	//Obtener los datos del Usuario cuya Direccion tiene la misma "calle" y "codigoPostal" que la Direccion con "id" = 2
	@Query("SELECT u FROM Usuario u "
			+ "WHERE (u.direccion.calle, u.direccion.codigoPostal) "
			+ "in ("
			+ "		SELECT d.calle, d.codigoPostal "
			+ "		FROM Direccion d "
			+ "		WHERE d.id = 2)")
	List<Usuario> rowValueConstructor(); 
	
	/*
	 * ******************************************************************************************
	 * COMPONENTES:																				*
	 * Nuestras clases entidad están construidas haciendo uso de la composición de objetos, 	*
	 * teniendo una relación de tipo HAS-A (objetos construidos en base a otros objetos) 		*
	 * como en el caso de la relación Usuario-Direccion; 										*
	 * en donde un Usuario tiene una Direccion y una Direccion tiene un Usuario. 				*
	 * 																							*
	 * Cuando tenemos una relación de este tipo, y esta relación es de Uno a Uno o 				*
	 * de Muchos a Muchos al Objeto incluido dentro de nuestra clase le llamamos “componente”.	*
	 * 																							*
	 * En este Caso si estamos dentro de la clase Usuario a la referencia que tenemos 			*
	 * a un objeto de la clase Direccion (private Direccion direccion;) 						*
	 * la llamamos el componente “dirección” del Usuario.										*
	 * 																							*
	 * HQL nos permite hacer consultas a propiedades de los componentes de nuestras clases 		*	
	 * de la misma forma en la que lo hacemos con las propiedades de nuestro objeto principal.	*
	 * 																						 	*
	 * Estos valores pueden aparecen en la clausula SELECT.										*
	 * Tambien podemos usar los atributos de los componentes en las clausulas WHERE y ORDER BY	*
	 * ******************************************************************************************																
	 */
	
	//Recordar que este tipo de consultas nos regresa una lista de arreglos de objetos
	@Query("SELECT u.nombre, u.direccion.calle, u.direccion.codigoPostal FROM Usuario u")
	List<Object[]> componentes();
	
	@Query("SELECT u FROM Usuario u WHERE u.direccion.calle = 'Calle1'")
	Usuario componentesWhere();
	
	/*
	 * ******************************************************************************************
	 * TIPS y TRUCOS																			*
	 * Contar en número de resultados 1*														*
	 * No Ejecutar consultas de tipo plano cartesianos para saber el numero de registros: 		*
	 * FROM Usuario u, Permiso p																*
	 * Mejor utilizar la siguiente conbsulta: SELECT COUNT(*) FROM Usuario u, Permiso p			*
	 * 																							*
	 * Resultados en base al tamaño de una colección 2*											*
	 * Si queremos ordenar los resultados que nos regresa una consulta en base al número 		*
	 * de elementos que tiene una colección podemos hacerlo usando la función de 				*
	 * agregación COUNT dentro de la clausula ORDER BY											*
	 * 3* También, si nuestra base de datos soporte subconsultas, podemos colocar una condición	*
	 * sobre el tamaño de la condición en la clausula SELECT de la consulta	
	 * ******************************************************************************************
	 */
	
	//1*
	@Query("SELECT COUNT(*) FROM Usuario u, Permiso p")
	Long contar();
	
	//2* 
	//Ordenar los Usuarios obtenidos de la base de datos, en base al número de Permisos que tiene cada uno, 
	@Query("SELECT u FROM Usuario u "
			+ "left join u.permisos p "
			+ "GROUP BY u ORDER BY COUNT(p) asc")
	List<Usuario> tamanoColeccion();
	
	//3*
	//Obtener todos los Usuarios que tienen menos de dos Permisos
	@Query("SELECT u FROM Usuario u WHERE size(u.permisos) < 2")
	List<Usuario> tamanoCondicion();
	
	//3*
	//Si no soporta subconsultas
	@Query("SELECT u FROM Usuario u "
			+ "join u.permisos p "
			+ "GROUP BY u "
			+ "HAVING  COUNT(p) < 2 ")
	List<Usuario> tamanoCondicionNosubconsultas();
	
	
	/*
	 * Referencia:
	 * https://www.javatutoriales.com/2010/07/hibernate-parte-9-parametros-en-hql.html 
	 */
	
	/*
	 * ******************************************************************************************
	 * Parametros en HQL																		*
	 * Hibernate proporciona dos formas de pasar parámetros a nuestras consultas:				*
	 * Parámetros con nombre (named query parameters):											*
	 * Es la forma más simple de pasar parámetros a una consulta en HQL ya que, como su nombre 	*
	 * lo indica, se le da un nombre al parámetro y posteriormente se hace uso de este nombre 	*
	 * para hacer referencia al parámetro.														*
	 * El nombre del parámetro debe ir dentro del String de la consulta, 						*
	 * este debe ir precedido por “:”.															* 
	 * ******************************************************************************************																
	 */
	
	@Query("SELECT u FROM Usuario as u WHERE u.username = :username AND u.password = :password")
	Usuario parametrosNamed(@Param("username")String username, @Param("password") String password);
	
	/*
	 * Referencia:
	 * http://www.cursohibernate.es/doku.php?id=unidades:05_hibernate_query_language:05_optimizacion 
	 */
	
	/*
	 * ******************************************************************************************
	 * Optimizar consultas HQL:																	*
	 * Este problema consiste en que al lanzar un consulta con HQL que retorna n filas, 		*
	 * el ORM lanza n+1 consultas SQL de SELECT: SELECT u FROM Usuario u						*
	 * 																							*
	 * Solucion con left join fetch:															*
	 * La solución más sencilla es modificar la consulta de HQL para que cargue también todos 	*
	 * los correos electrónicos: SELECT p FROM Usuario u LEFT JOIN FETCH u.permisos				*
	 * 																							*
	 * Desgraciadamente con ésto no es suficiente. Si ejecutamos el código veremos que se 		*
	 * repiten los objetos Usuario.																*
	 * ¿Porqué?																					*
	 * El lenguaje SQL no permite consultar jerárquicas , así que lo que hace es retornar todos	*
	 * los usuarios con todos los permisos , por lo que el mismo usuario está repetido tantas 	*
	 * veces como permisos.																		*
	 * 																							*
	 * Solucion con un Set:																		*
	 * Para eliminar los objetos duplicados usaremos el truco de pasarlos 						*
	 * todos a un Set de Java , ya que por definición no permite objetos duplicados, 			*
	 * a diferencia de un List que si que permite duplicado.									*
	 * Ejemplo:																					*
	 * 1.-List<Usuario> usuarios = listaDeUsuarios();											*
	 * 																							*
	 * 2a.-Set<Usuario> usuariosSinDuplicar  = new HashSet<Usuario>(usuarios)					*
	 * ó																						*
	 * 2b.-Set<Usuario> usuariosSinDuplicar  = new LinkedHashSet<Usuario>(usuarios)				*
	 * 																							*
	 * 3.- usuarios.clear();																	*
	 * 4.- usuarios.addAll(usuariosSinDuplicar);												*
	 * 																							*
	 * Otra solucion utilizando Set:																			*
	 * En la entidad Usuario, en la relacion que se tienen con su componente permisos cambiar 	*
	 * de: List<Permiso> permisos = new ArrayList<Permiso>()									*
	 * a: Set<Permiso> permisos = new HashSet<Permiso>()										*
	 * ó 																						*
	 * a: Set<Permiso> permisos = new LinkedHashSet<Permiso>()									*
	 * 																							*
	 * Nota: Se recomienda utilizar new LinkedHashSet:											*
	 * Pros: Simplemente porque el LinkedHashSet nos garantiza que al obtener los datos 		*		
	 * (en el  paso 4 usuarios.addAll(usuariosSinDuplicar) ) estarán en el mismo orden en el 	*
	 * que se insertaron lo que hará que se mantenga el mismo orden de la lista original, 		*
	 * cosa muy necesaria si en la HQL se uso un ORDER BY.										*
	 * Contras: El utilizar LinkedHashSet  es un poco más costosa que HashSet.					*
	 * 																							*
	 * Nota:Si las relaciones (mapeos) de la entidad se encuentran como carga LAZY, el problema *
	 * del n+1 SELECTs puede no ser un problema si no vamos a acceder a otros datos hijos 		*
	 * de la clase que hemos solicitado.														*
	 * 																							*
	 * Conclusion: 																				*
	 * Si el mapeo de las relaciones es carga EGER utilizar join fetch en la consulta.			*
	 * @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER) 							*
	 * private Direccion direccion;																*
	 * 																							*
	 * Si el mapeo de las relaciones es carga LAZY y no se desea acceder a los hijos 			*
	 * no hay problema con "n+1 SELECTs" y no es necesario utilizar en la consulta join fetch.	*
	 * @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY) 							*	
	 * private Direccion direccion;																*
	 * 																							*
	 * Si el mapeo de las relaciones es LAZY y se desea acceder a los hijos, si o solo si se 	*
	 * tiene que utilizar en la consulta join fetch.											*
	 * @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)								*
	 * private Direccion direccion;																*
	 * ******************************************************************************************																
	 */	
	
	
	
	//EAGER
	//CUANDO LA RELACION USUARIO - DIRECCION ES CARGA EAGER:
	//A Y B GENERAN 5 CONSULTAS A LA BASE DE DATOS y C SOLO 3 CONSULTAS A LA BASE DE DATOS
	//A B Y C: GENERAN EL MISMO RESULTADO
	//PROS: DESDE LOS OBJETOS USUARIO SE PUEDE ACCEDER A LOS OBJETOS DE DIRECCION
	//CONTRAS: INTERNAMENTE HIBERNATE GENERA MAS CONSULTAS A LA BASE DE DATOS
	
	//LAZY
	//CUANDO LA RELACION USUARIO - DIRECCION ES CARGA LAZY: 
	//A y B SOLO GENERAN UNA CONULTA A LA BASE DE DATOS Y C GENERA TRES CONSULTAS A LA BASE DE DATOS
	//A B Y C: GENERAN EL MISMO RESULTADO
	//PROS:INTERNAMENTE HIBERNATE GENERA MENOS CONSULTAS A LA BASE DE DATOS
	//CONTRAS: DESDE LOS OBJETOS USUARIO NO SE PUEDE ACCEDER A LOS OBJETOS DE DIRECCION
	
	//RELACIONES ONE TO ONE
	//A Internamente Hibernate Genera las mismas consultas que B, pero genera mas consultas que C
	@Query("SELECT u FROM Usuario u")
	List<Usuario> usuAll();
	
	//B Internamente Hibernate Genera las mismas consultas que A, pero genera mas consultas que C
	@Query("SELECT u FROM Usuario u left outer join u.direccion as d")
	List<Usuario> usuAlljoin();
	
	//C: En Comparacion con A Internamente Hibernate realiza menos consultas a la base de datos
	//y se obtiene el mismo resultado que la consulta A
	@Query("SELECT u FROM Usuario u inner join fetch u.direccion as d")
	List<Usuario> usuAllFetch();
	
	@Query("SELECT u FROM Usuario u")
	Set<Usuario> setUserAll();
	
	//LAZY inner join
	////RELACIONES ONE TO MANY:
	//A 
	@Query("SELECT u FROM Usuario u inner join u.permisos as p WITH p.estatus = 1")
	Set<Usuario> usuPerJoin();
	
	//B
	@Query("SELECT u FROM Usuario u join fetch u.permisos as p WHERE p.estatus = 1")
	Set<Usuario> usuPerFetch();
	
	//EN LAS RELACIONES ONE TO MANY CON CARGA LAZY: HIBERNATE GENERA EL MISMO NUMERO DE CONSULTAS PARA LA CONSULTA "A" COMO LA "B"
	
	//CONSLUSION FINAL: UN BUEN DESEMPEÑO DE LAS APLICACION CUANDO SE UTILIZA UN ORM COMO HIBERNATE
	//DEPENDE PRNCIPALMETE DE LAS CARGAS (EAGER o LAZY) QUE EXISTEN  ENTRE LAS RELACIONES DE LAS ENTIDADES 
	//Y SOBRE TODO DEL TIPO DE INFORMACION QUE SE QUIERA OBTENER.
}
