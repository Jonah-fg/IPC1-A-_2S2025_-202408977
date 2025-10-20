
package Modelo;

public class ProductosTecnologicos extends Productos{
    private int mesesGarantia;
    
    public ProductosTecnologicos(String nombreProducto, String codigoProducto, double precio, int mesesGarantia) {
        super(nombreProducto, codigoProducto, "Tecnologico", precio);
        this.mesesGarantia=mesesGarantia;
    }

    public int getMesesGarantia() {
        return mesesGarantia;
    }

    public void setMesesGarantia(int mesesGarantia) {
        this.mesesGarantia = mesesGarantia;
    } 
}
