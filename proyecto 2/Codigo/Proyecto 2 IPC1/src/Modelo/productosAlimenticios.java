
package Modelo;

public class ProductosAlimenticios extends Productos{
    private String fechaCaducidad;
    
    public ProductosAlimenticios(String nombreProducto, String codigoProducto, double precio, String fechaCaducidad) {
        super(nombreProducto, codigoProducto, "Alimentos", precio);
        this.fechaCaducidad=fechaCaducidad;
    }
    
    public String getFechaCaducidad() {
        return fechaCaducidad;
    }
   
    public void setFechaCaducidad(String fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }   
}
