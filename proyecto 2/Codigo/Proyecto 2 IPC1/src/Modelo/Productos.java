
package Modelo;

public class Productos {
    private String nombreProducto;
    private String codigoProducto;
    private String categoria;
    private double precio;
    private int stock;

    public Productos(String nombreProducto, String codigoProducto, String Categoria, double precio) {
        this.nombreProducto = nombreProducto;
        this.codigoProducto = codigoProducto;
        this.categoria = Categoria;
        this.precio = precio;
        this.stock=0;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }
    public String getCodigoProducto() {
        return codigoProducto;
    }
    public String getCategoria() {
        return categoria;
    }
    public double getPrecio() {
        return precio;
    }
    public int getStock() {
        return stock;
    }
    //setters

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public void setStock(int Stock) {
        this.stock = Stock;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }
    
    
    public void agregarStock(int cantidadStock){
        this.stock+=cantidadStock;
    }
    public boolean reducirStock(int cantidadStock){
        if (this.stock >= cantidadStock){
        this.stock -= cantidadStock;
        return true;
        }
        
        return false;
    }
}
    
 

