
package Modelo;

public class ProductosTecnologicos extends Productos{
    private String mesesGarantia;
    
    public ProductosTecnologicos(String nombreProducto, String codigoProducto, double precio, int mesesGarantia) {
        super(nombreProducto, codigoProducto, "Tecnologico", precio);
    }

    public String getMesesGarantia() {
        return mesesGarantia;
    }

    public void setMesesGarantia(String mesesGarantia) {
        this.mesesGarantia = mesesGarantia;
    } 
}
