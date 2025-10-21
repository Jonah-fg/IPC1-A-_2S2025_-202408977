package Controlador;

import Modelo.Administrador;
import Modelo.AdministradorUsuarios;
import Modelo.Cliente;
import Modelo.Usuario;
import Modelo.Vendedor;
import Vista.VistaAdministrador;
import Vista.VistaCliente;
import Vista.VistaInicioSesion;
import Vista.VistaVendedor;

public class ControladorIniciarSesion {
    private VistaInicioSesion vista;
    private AdministradorUsuarios administrador;

    public ControladorIniciarSesion(VistaInicioSesion vista, AdministradorUsuarios administrador){
        this.vista = vista;
        this.administrador = administrador;
        crearUsuarioAdminPorDefecto();
    } 
    
    private void crearUsuarioAdminPorDefecto(){
        Administrador admin=new Administrador("Administrador", "admin", "M", "IPC1A");
        if(administrador.buscarUsuarioCodigo("admin")==null){
            administrador.crearUsuario(admin);
        }
    }
    
    public void redireccionSegunUsuario(Usuario usuario){
        vista.dispose(); 
        if(usuario instanceof Administrador){
            VistaAdministrador vistaAdministrador = new VistaAdministrador();
            controladorAdministrador controladorAdmin = new controladorAdministrador(vistaAdministrador);
            vistaAdministrador.setVisible(true);
        }
        else if(usuario instanceof Vendedor){
            VistaVendedor vistaVendedor = new VistaVendedor();
            ControladorVendedor controladorVendedor = new ControladorVendedor(vistaVendedor, (Vendedor)usuario);
            vistaVendedor.setVisible(true);
        }
    }    
    
    public boolean IniciarSesion(String codigo, String contraseña){
         Usuario usuario = administrador.buscarUsuarioCodigo(codigo);
        if(usuario!=null&& usuario.getContraseña().equals(contraseña)){
            redireccionSegunUsuario(usuario);
            return true;
        }
       return false;
    }
       
 
}
