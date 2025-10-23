
package Modelo;

import java.io.Serializable;

public class Administrador extends Usuario implements Serializable{
     private static final long serialVersionUID = 1L;
    
    public Administrador(String nombre, String codigo, String genero, String contraseña) {
        super(nombre, codigo, genero, contraseña);
    }
    
}
