
package Modelo;

import java.io.Serializable;

public class ProductosTecnologicos extends Productos implements Serializable {
    private static final long serialVersionUID = 1L;
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
