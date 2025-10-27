
package proyecto.pkg2.ipc1;

import Controlador.ControladorIniciarSesion;
import Modelo.AdministradorUsuarios;
import Reportes_e_hilos.MonitoreoSistemas;
import Vista.VistaInicioSesion;
import Vista.VistaMonitoreoHilos;

public class Proyecto2IPC1 {

    public static void main(String[] args){
        
        java.awt.EventQueue.invokeLater(new java.lang.Runnable(){
            
        public void run(){
            VistaMonitoreoHilos vistaMonitores=new VistaMonitoreoHilos();
            vistaMonitores.setVisible(true);
            
            VistaInicioSesion vistaInicioSesion=new VistaInicioSesion();
            AdministradorUsuarios adminUsuarios=new AdministradorUsuarios();
            ControladorIniciarSesion controladorLogin=new ControladorIniciarSesion(vistaInicioSesion, adminUsuarios);
            vistaInicioSesion.setVisible(true);
            }
       });
    }
}
    
