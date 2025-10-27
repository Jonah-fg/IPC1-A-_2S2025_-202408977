
package Modelo;

import java.io.Serializable;

public class ProductosAlimenticios extends Productos implements Serializable{
    private static final long serialVersionUID = 1L;
    private String fechaCaducidad;
    
    public ProductosAlimenticios(String nombreProducto, String codigoProducto, double precio, String fechaCaducidad) {
        super(nombreProducto, codigoProducto, "Alimenticio", precio);
        this.fechaCaducidad=fechaCaducidad;
    }
    
    public String getFechaCaducidad() {
        return fechaCaducidad;
    }
   
    public void setFechaCaducidad(String fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }   
}
