
import java.text.SimpleDateFormat;
import java.util.Date;


public class bitacora {
    String fechayhoraAccion;
    String accion;
    String usuario;
    String resultadoAccion;
    
    //constructor
     public bitacora(String Accion, String ResultadoAccion, String Usuario) {
        this.fechayhoraAccion= new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
        this.accion = accion;
        this.usuario = usuario;
        this.resultadoAccion = resultadoAccion;
    }
     
     public void mostrarRegistro() {
        System.out.println("Fecha: " + fechayhoraAccion);
        System.out.println("Acción: " + accion);
        System.out.println("Resultado :" + resultadoAccion);
        System.out.println("Usuario: " + usuario);   
    }
     //Getters
    public String getFechayhoraAccion() {
        return fechayhoraAccion;
    }
    public String getAccion() {
        return accion;
    }
    public String getUsuario() {
        return usuario;
    }
    public String getResultadoAccion() {
        return resultadoAccion;
    }  
}