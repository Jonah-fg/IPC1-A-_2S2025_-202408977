
package Modelo;

public class productosAlimenticios extends Productos{
    private String fechaCaducidad;
    
    public productosAlimenticios(String nombreProducto, String codigoProducto, String Categoria, double precio) {
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
