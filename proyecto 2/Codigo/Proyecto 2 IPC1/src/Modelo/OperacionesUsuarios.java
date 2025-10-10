
package Modelo;

public interface OperacionesUsuarios extends CRUD {
    Usuario buscarUsuarioCodigo(String codigo);
    boolean autenticacion(String codigo, String contraseña);
    Usuario[] obtenerTodosUsuarios();   
}
