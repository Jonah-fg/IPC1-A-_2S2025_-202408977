
package Reportes_e_hilos;

import Modelo.AdministradorProductos;
import Modelo.AdministradorUsuarios;
import Modelo.Bitacora;
import Modelo.Cliente;
import Modelo.Productos;
import Modelo.Vendedor;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import javax.swing.JOptionPane;
import javax.swing.text.Document;

public class GeneradorReportes {
    private AdministradorProductos adminProductos;
    private AdministradorUsuarios adminUsuarios;
    
    public GeneradorReportes(AdministradorUsuarios adminUsuarios, AdministradorProductos adminProductos){
        this.adminUsuarios=adminUsuarios;
        this.adminProductos=adminProductos;
    }
    
    private String generarNombreArchivo(String tipoReporte) {
        java.text.SimpleDateFormat formato=new java.text.SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
        return formato.format(new java.util.Date()) +"_"+ tipoReporte +".pdf";
    }
    
    public void generarReporteInventario(){
        try{
            Bitacora.registrarBitacora("SISTEMA", "admin", "GENERAR_REPORTE", "EXITOSO", "Reporte de Inventario");
            String nombreArchivo =generarNombreArchivo("ReporteInventario");
            com.itextpdf.text.Document documento= new com.itextpdf.text.Document();
            PdfWriter.getInstance(documento,new FileOutputStream(nombreArchivo));
            documento.open();
        
            documento.add(new Paragraph("REPORTE DE INVENTARIO"));
            documento.add(new Paragraph(" "));
            Productos[] productos=adminProductos.obtenerTodosProductos();
        
            for(Productos producto :productos){
                String estado=producto.getStock()<10 ?"CRITICO":"BAJO";

            if(producto.getStock()<20){
                documento.add(new Paragraph(producto.getCodigoProducto() + " -"+ producto.getNombreProducto() + "- " +"Stock: "+ producto.getStock() + " - " +"Estado: " + estado));
            }
            }         
            documento.close();
            JOptionPane.showMessageDialog(null,"Inventario generado: " +nombreArchivo);
        } 
        catch(Exception e){
            Bitacora.registrarBitacora("SISTEMA", "admin","GENERAR_REPORTE", "ERROR", "Error en Reporte Inventario: "+e.getMessage());
            JOptionPane.showMessageDialog(null, "Error en inventario");
        }
    }
    
        public void generarReporteClientes(){
        try{
            com.itextpdf.text.Document document=new com.itextpdf.text.Document();
            PdfWriter.getInstance(document,new FileOutputStream("clientes.pdf"));
            document.open(); 
            document.add(new Paragraph("CLIENTES"));
            document.add(new Paragraph(" "));
            
            Cliente[] clientes=adminUsuarios.getClientes();
            for(Cliente cliente :clientes){
                document.add(new Paragraph(cliente.getNombre() +" - " + cliente.getCodigo()));
            }  
            document.close();
            JOptionPane.showMessageDialog(null, "Clientes guardado");
            
        } 
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error");
        }
    }

    public void generarReporteProductos(){
        try{
            com.itextpdf.text.Document documento=new com.itextpdf.text.Document();
            PdfWriter.getInstance(documento,new FileOutputStream("productos.pdf"));
            documento.open();        
            documento.add(new Paragraph("PRODUCTOS"));
            documento.add(new Paragraph(" "));

            Productos[] productos =adminProductos.obtenerTodosProductos();
            for(Productos producto :productos){
                documento.add(new Paragraph(producto.getNombreProducto() + " - Stock: " + producto.getStock()));
            } 
            documento.close();
            JOptionPane.showMessageDialog(null, "Productos guardado"); 
        } 
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error");
        }
    }
    
     public void generarReporteVentas(){
        try{
            String nombreArchivo=generarNombreArchivo("ReporteVentasVendedor");
            com.itextpdf.text.Document documento=new com.itextpdf.text.Document();
            PdfWriter.getInstance(documento,new FileOutputStream(nombreArchivo));
            documento.open();          
        
            documento.add(new Paragraph("REPORTE DE VENTAS POR VENDEDOR"));
            documento.add(new Paragraph(" "));
            Vendedor[] vendedores=adminUsuarios.getVendedores();
        
            for(int i=0; i<vendedores.length -1;i++){
                for(int j=i+1; j< vendedores.length; j++){
                    if(vendedores[i].getVentasConfirmadas()< vendedores[j].getVentasConfirmadas()){
                        Vendedor temporal=vendedores[i];
                        vendedores[i]=vendedores[j];
                        vendedores[j] =temporal;
                    }
                }
            }
            documento.add(new Paragraph("TOP 3 VENDEDORES:"));
            for(int i=0;i<Math.min(3, vendedores.length);i++){
                documento.add(new Paragraph(vendedores[i].getNombre() +" - Ventas: "+ vendedores[i].getVentasConfirmadas()));
            }
            documento.close();
            JOptionPane.showMessageDialog(null,"Ventas generado: " + nombreArchivo);   
        } 
        catch(Exception e){
        JOptionPane.showMessageDialog(null,"Error en vetas");
        }
    }
}
