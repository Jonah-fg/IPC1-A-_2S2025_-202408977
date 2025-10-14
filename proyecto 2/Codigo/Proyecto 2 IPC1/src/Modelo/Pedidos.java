
package Modelo;

public class Pedidos {
    private String codigoPedido;
    private String codigoCliente;
    private String nombreCliente;
    private String fechaGeneracion;
    private boolean confirmacion;
    private double total;

    public Pedidos(String codigoPedido, String codigoCliente, String fechaGeneracion, String nombreCliente, double total) {
        this.codigoPedido = codigoPedido;
        this.codigoCliente = codigoCliente;
        this.nombreCliente = nombreCliente;
        this.fechaGeneracion = fechaGeneracion;
        this.confirmacion=false;
        this.total = total;
    }
    
    //getters
    public String getCodigo() {
        return codigoPedido;
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
    
    public boolean esConfirmado() {
        return confirmacion;
    }
    
    //setters
    public void setConfirmacion(boolean confirmacion) {
        this.confirmacion = confirmacion;
    }
    
}
