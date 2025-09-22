      package proyecto.pkg1.ipc1; 
import java.text.SimpleDateFormat;
import java.util.Date;
public class ventas {
    private String codigoProducto;
    private double total;
    private int cantidadVenta;
    private String fechayhora;
    
    public ventas(String codigoProducto, int cantidadVenta, double total){
        
        this.cantidadVenta=cantidadVenta;
        this.codigoProducto=codigoProducto;
        this.total=total;
        this.fechayhora=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
    }
    //Getters
    public String getCodigoProducto() {
        return codigoProducto;
    }
    public double getTotal() {
        return total;
    }
    public int getCantidadVenta() {
        return cantidadVenta;
    }
    public String getFechayhora() {
        return fechayhora;
    }
    
    //Setters
    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }
    public void setTotal(double total) {
        this.total = total;
    }
    public void setCantidadVenta(int cantidadVenta) {
        this.cantidadVenta = cantidadVenta;
    }
    public void setFechayhora(String fechayhora) {
        this.fechayhora = fechayhora;
    }
    
    public void MostrarInformacion(){
            System.out.println("Fecha y hora de venta: "+ fechayhora);
            System.out.println("codigo ID del producto: "+ codigoProducto);
            System.out.println("Cantidad: "+ cantidadVenta);
            System.out.println("total: "+ total);
    } 
            
}

