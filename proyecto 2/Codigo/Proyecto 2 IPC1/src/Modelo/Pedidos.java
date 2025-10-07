
package Modelo;

public class Pedidos {
    private String codigo;
    private String codigoCliente;
    private String nombreCliente;
    private String fechaGeneracion;
    private boolean confirmacion;
    private double total;

    public Pedidos(String codigo, String codigoCliente, String nombreCliente, String fechaGeneracion, double total) {
        this.codigo = codigo;
        this.codigoCliente = codigoCliente;
        this.nombreCliente = nombreCliente;
        this.fechaGeneracion = fechaGeneracion;
        this.confirmacion=false;
        this.total = total;
    }
    
    //getters
    public String getCodigo() {
        return codigo;
    }
    public String getCodigoCliente() {
        return codigoCliente;
    }
    public String getNombreCliente() {
        return nombreCliente;
    }
    public String getFechaGeneracion() {
        return fechaGeneracion;
    }
    public double getTotal() {
        return total;
    }
    
    //setters
    public void setConfirmacion(boolean confirmacion) {
        this.confirmacion = confirmacion;
    }
    
}
