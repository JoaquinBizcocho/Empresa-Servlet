package com.empresa.empladoDAO;

import com.empresa.empladoDAO.DAO;

 // Patron Factory method
 
 // Esta clase centraliza la creación de objetos DAO.
 
public class DAOFactory {
	
    public static DAO crearEmpleadoDAO() {
        return new DAO();
    }

}
