
package Modelo;

public class Productostecnologicos extends Productos{
    private String mesesGarantia;
    
    public Productostecnologicos(String nombreProducto, String codigoProducto, String Categoria, double precio) {
        super(nombreProducto, codigoProducto, "Tecnologico", precio);
    }

    public String getMesesGarantia() {
        return mesesGarantia;
    }

    public void setMesesGarantia(String mesesGarantia) {
        this.mesesGarantia = mesesGarantia;
    } 
}
