package Controlador;

import Modelo.AdministradorUsuarios;
import Vista.VistaAdministrador;
import Vista.VistaInicioSesion;

public class ControladorIniciarSesion {
    private VistaInicioSesion vista;
    private AdministradorUsuarios administrador;

    public ControladorIniciarSesion(VistaInicioSesion vista, AdministradorUsuarios Administrador){
        this.vista = vista;
        this.administrador = administrador;
    } 
    
    public void redireccionSegunUsuario(String codigo){
        vista.dispose(); 
        if(codigo.equals("admin")){
            VistaAdministrador vistaAdministrador= new VistaAdministrador();
            controladorAdministrador controladorAdmin=new controladorAdministrador(vistaAdministrador);
            vistaAdministrador.setVisible(true);
        }
    }
    
    public boolean IniciarSesion(String codigo, String contraseña){
        if(administrador.autenticacion(codigo, contraseña)){
            redireccionSegunUsuario(codigo);
            return true;
        }
        return false;
    }
 
}
