
package proyecto.pkg1.ipc1;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PDFs {
    
    public static void generadorreportesStock(productos productos[], int contadorProductos) {
        String nombrearchivoStock = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss").format(new Date()) + "_Stock.pdf";
        Document doc = new Document();
        
        try{
            Document docu = new Document();
             PdfWriter.getInstance(docu, new FileOutputStream(nombrearchivoStock));
            doc.open();
            
             doc.add(new Paragraph("REPORTE DE STOCK TIENDA"));
            doc.add(Chunk.NEWLINE);
             Font fuenteTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
            Paragraph titulo = new Paragraph("REPORTE DE STOCK TIENDA", fuenteTitulo);
            titulo.setAlignment(Element.ALIGN_CENTER);
            docu.add(titulo);
              docu.add(Chunk.NEWLINE);
            
              PdfPTable tabla = new PdfPTable(5);
             tabla.addCell("Codigo del producto");
              tabla.addCell("Nombre del producto");
             tabla.addCell("Categoria del producto");
              tabla.addCell("precio del producto");
             tabla.addCell("Stock actual");
             
             for(int i=0; i<contadorProductos; i++){
                 productos p = productos[i];
                  tabla.addCell(p.getID());
                  tabla.addCell(p.getNombre());
                 tabla.addCell(p.getCategoria());
                 tabla.addCell("Q" + p.getPrecio());
                  tabla.addCell(String.valueOf(p.getCantidad()));         
             }
             docu.add(tabla);
            docu.add(new Paragraph(" "));
            docu.add(new Paragraph("Generado: " + new Date()));
            docu.close();
            System.out.println("PDF generado: " + nombrearchivoStock);
              System.out.println("El PDF de Stock ha sid generado: " + nombrearchivoStock);
            
        } 
        catch (Exception e) {
            System.out.println("Error al generar el PDF de Stock: "+ e.getMessage());
        }
    }    
        
        public static void generarReporteVentas(ventas[] ventas, int contadorVentas){
        String nombrearchivoVenta = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss").format(new Date()) + "_Venta.pdf";
        
        try {
            Document docu = new Document();
             PdfWriter.getInstance(docu, new FileOutputStream(nombrearchivoVenta));
            docu.open();
            
            docu.add(new Paragraph("REPORTE DE VENTAS TIENDA"));
            docu.add(new Paragraph(" ")); 
            

            PdfPTable tabla = new PdfPTable(4);
            tabla.addCell("codigo del producto");
             tabla.addCell("Cantidad del producto");
            tabla.addCell("Total");
              tabla.addCell("Fecha");
              
            double totalGeneral = 0;
        for (int i = 0; i < contadorVentas; i++) {
            ventas v = ventas[i];
             tabla.addCell(v.getCodigoProducto());
            tabla.addCell(String.valueOf(v.getCantidadVenta()));
            tabla.addCell("Q" + v.getTotal());
             tabla.addCell(v.getFechayhora());
             totalGeneral += v.getTotal();
        }
          docu.add(tabla); 
        docu.add(new Paragraph(" "));
        docu.add(new Paragraph("Total en geeral: Q" + totalGeneral));
        docu.add(new Paragraph("Generado: " + new Date()));
        docu.close(); 
        System.out.println("El PDF fue generado: " + nombrearchivoVenta);
                
        }
        catch(Exception e){
            System.out.println("Error la generar el reporte: " + e.getMessage());
        }        
    }
    
}
