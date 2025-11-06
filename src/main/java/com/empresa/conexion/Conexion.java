package com.empresa.conexion;
 
import java.sql.Connection;
import java.sql.SQLException;
 
import javax.sql.DataSource;
 
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.apache.tomcat.jdbc.pool.ConnectionPool;
 
public class Conexion {
	
	//Patron Singleton
	// Variable estática que guardara la unica instancia de la clase Conexion.
 private static Conexion instancia;	
//Objeto que gestiona el pool de conexiones.
 private BasicDataSource dataSource;
 
 //creamos un constructor privado que impide crear instancias desde fuera de la clase
 private Conexion() {
   dataSource = new BasicDataSource();
   dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
   dataSource.setUsername("root");
   dataSource.setPassword("usuario");
   dataSource.setUrl("jdbc:mysql://localhost:3306/gestion_nomina?useTimezone=true&serverTimezone=UTC");
   dataSource.setInitialSize(20);
   dataSource.setMaxIdle(15);
   dataSource.setMaxTotal(20);
   dataSource.setMaxWaitMillis(5000);
 }
 
 // Metodo publico y estatico que devuelve la instancia unica de la clase.
 public static synchronized Conexion getInstancia() {
	 if (instancia == null) {
		 instancia = new Conexion();
	 }
	 return instancia;
 }
 
 // Este metodo devuelve el objeto DataSource configurado
 public DataSource getDataSource() {
	 return dataSource;
 }
 
 
 
 // Metodo estatico que permite obtener directamente una conexion lista para usar.
 public static Connection getConnection() throws SQLException {
  return getInstancia().getDataSource().getConnection();
 }
}
