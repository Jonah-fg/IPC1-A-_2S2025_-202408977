
package Modelo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Bitacora {
    private static String archivoBitacora = "bitacora.txt";
    private static SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    
    public static void registrarBitacora(String tipoUsuario, String codigoUsuario, String operacion, String estado, String descripcion){
        try(FileWriter escribirArchivo = new FileWriter(archivoBitacora, true);
             BufferedWriter escribirLinea = new BufferedWriter(escribirArchivo)) {
            
            String fechaHora=formatoFecha.format(new Date());
            String registro=String.format("[%s] | %s | %s | %s | %s | %s", fechaHora, tipoUsuario, codigoUsuario, operacion, estado, descripcion);
            escribirLinea.write(registro);
            escribirLinea.newLine();         
        } 
        catch (IOException e){
            System.out.println("Error en bitácora: " + e.getMessage());
        }
    }
}
    

