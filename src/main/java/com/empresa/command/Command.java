package com.empresa.command;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


 // Patron Command
  
 // Interfaz base para todos los comandos.
 
public interface Command {
    void ejecutar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
