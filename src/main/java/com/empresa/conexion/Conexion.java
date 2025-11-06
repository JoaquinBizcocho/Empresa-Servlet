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
 
 
 public static synchronized Conexion getInstancia() {
	 if (instancia == null) {
		 instancia = new Conexion();
	 }
	 return instancia;
 }
 
 
 public DataSource getDataSource() {
	 return dataSource;
 }
 
 
 
 
 public static Connection getConnection() throws SQLException {
  return getInstancia().getDataSource().getConnection();
 }
}

/*
package com.empresa.conexion;
 
import java.sql.Connection;
import java.sql.SQLException;
 
import javax.sql.DataSource;
 
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

 

public class Conexion {
 private static BasicDataSource dataSource = null;
 
 private static DataSource getDataSource() {
  if (dataSource == null) {
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
  return dataSource;
 }
 
 public static Connection getConnection() throws SQLException {
  return getDataSource().getConnection();
 }
}
*/
