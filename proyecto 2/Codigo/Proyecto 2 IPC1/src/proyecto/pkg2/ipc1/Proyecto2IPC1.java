
package proyecto.pkg2.ipc1;

import Controlador.ControladorIniciarSesion;
import Modelo.AdministradorUsuarios;
import Vista.VistaInicioSesion;

public class Proyecto2IPC1 {

    public static void main(String[] args){
        
        java.awt.EventQueue.invokeLater(new java.lang.Runnable(){
            
        public void run(){
            VistaInicioSesion vistaInicioSesion=new VistaInicioSesion();
            AdministradorUsuarios adminUsuarios=new AdministradorUsuarios();
            ControladorIniciarSesion controladorLogin=new ControladorIniciarSesion(vistaInicioSesion, adminUsuarios);
            vistaInicioSesion.setVisible(true);
            }
       });
    }
}
    
