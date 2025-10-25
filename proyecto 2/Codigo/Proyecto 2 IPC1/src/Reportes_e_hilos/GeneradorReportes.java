
package Reportes_e_hilos;

import Modelo.AdministradorProductos;
import Modelo.AdministradorUsuarios;
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
    
    public void generarReporteInventario(){
        try{
            com.itextpdf.text.Document document=new com.itextpdf.text.Document();
            PdfWriter.getInstance(document,new FileOutputStream("inventario.pdf"));
            document.open();
            document.add(new Paragraph("INVENTARIO"));
            document.add(new Paragraph(" "));
            
            Productos[] productos=adminProductos.obtenerTodosProductos();
            for (Productos producto : productos){
                if (producto.getStock()< 10){
                    document.add(new Paragraph(producto.getNombreProducto() +" - Stock: " + producto.getStock()+ "(BAJO)"));
                }
            }         
            document.close();
            JOptionPane.showMessageDialog(null, "Inventario guardado");
        } 
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error");
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
            com.itextpdf.text.Document documento=new com.itextpdf.text.Document();
            PdfWriter.getInstance(documento,new FileOutputStream("ventas.pdf"));
            documento.open();          
            documento.add(new Paragraph("VENTAS POR VENDEDOR"));
            documento.add(new Paragraph(" "));
            
            Vendedor[] vendedores = adminUsuarios.getVendedores();
            for(Vendedor vendedor :vendedores){
                documento.add(new Paragraph(vendedor.getNombre() +" - Ventas: "+ vendedor.getVentasConfirmadas()));
            }
            documento.close();
            JOptionPane.showMessageDialog(null, "Ventas guardado");   
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error");
        }
    }
}
