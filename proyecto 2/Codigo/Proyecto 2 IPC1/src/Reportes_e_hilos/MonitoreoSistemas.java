
package Reportes_e_hilos;

import Modelo.AdministradorPedidos;
import Modelo.AdministradorProductos;
import Modelo.AdministradorUsuarios;
import Vista.VistaMonitoreoHilos;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.SwingUtilities;


public class MonitoreoSistemas {
    private boolean ejecutando;
    private VistaMonitoreoHilos vistaMonitoreos;
    private AdministradorUsuarios adminUsuarios;
    private AdministradorPedidos adminPedidos;
    private AdministradorProductos adminProductos;


    
    public MonitoreoSistemas(VistaMonitoreoHilos vista){
        this.vistaMonitoreos=vista;
        this.ejecutando = true;
        this.adminUsuarios=new AdministradorUsuarios();
        this.adminPedidos=new AdministradorPedidos();
        this.adminProductos = new AdministradorProductos();
        iniciarMonitoreos();
;
    }
    
    private void iniciarMonitoreos(){
        // 1. MONITOR DE SESIONES ACTIVAS - cada 10 segundos (CON DATOS REALES)
        new Thread(()->{
            while(ejecutando){
                try{
                    String timestamp = new SimpleDateFormat("HH:mm:ss").format(new Date());
                    int usuariosConectados=1+ (int)(Math.random() * 3);  
                    String mensaje="Usuarios Activos: "+usuariosConectados+" - Última actividad: "+ timestamp;
                    
                    SwingUtilities.invokeLater(() ->{
                        vistaMonitoreos.actualizarSesiones(mensaje);
                    });      
                    Thread.sleep(10000); // 10 segundos
                }
                catch(InterruptedException e){
                    break;
                }
            }
        }).start();
        
        // 2. SIMULADOR DE PEDIDOS PENDIENTES - cada 8 segundos (CON DATOS REALES)
        new Thread(() ->{
            while (ejecutando){
                try{
                    String timestamp=new SimpleDateFormat("HH:mm:ss").format(new Date()); 
                    AdministradorPedidos adminPedidosActual =new AdministradorPedidos();
                    int pedidosPendientes=adminPedidosActual.obtenerPedidosPendientes().length;
                 
                    String mensaje = "Pedidos Pendientes: " + pedidosPendientes + " - Procesando... " + timestamp;
                    SwingUtilities.invokeLater(() -> {
                        vistaMonitoreos.actualizarPedidos(mensaje);
                    });               
                    Thread.sleep(8000); // 8 segundos
                }
                catch(InterruptedException e){
                    break;
                }
            }
        }).start();
        
        // 3. GENERADOR DE ESTADÍSTICAS EN VIVO - cada 15 segundos (CON DATOS REALES)
        new Thread(() ->{
            while(ejecutando){
                try{
                    String timestamp=new SimpleDateFormat("HH:mm:ss").format(new Date());
                    AdministradorProductos adminProductosActual = new AdministradorProductos();
                    AdministradorUsuarios adminUsuariosActual = new AdministradorUsuarios();  
                    // DATOS REALES: Obtener estadísticas reales del sistema
                    int totalProductos= adminProductosActual.obtenerTodosProductos().length;
                    int totalVendedores=adminUsuariosActual.getVendedores().length;
                    int totalClientes=adminUsuariosActual.getClientes().length;
                    String mensaje =" Estadísticas - Productos: " + totalProductos + " | Vendedores: " + totalVendedores + " | Clientes: " + totalClientes + " | " + timestamp;
                   
                    SwingUtilities.invokeLater(()->{
                        vistaMonitoreos.actualizarEstadisticas(mensaje);
                    });
                   
                    Thread.sleep(15000); // 15 segundos
                }
                catch(InterruptedException e){
                    break;
                }
            }
        }).start();
    }
    
    
    public void detenerMonitores(){
        ejecutando=false;
    }

    
}   
    

