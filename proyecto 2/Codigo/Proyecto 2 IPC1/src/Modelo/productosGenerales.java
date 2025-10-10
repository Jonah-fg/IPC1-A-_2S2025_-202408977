
package Modelo;

public class ProductosGenerales extends Productos{
    private String materialProducto;
    
    public ProductosGenerales(String nombreProducto, String codigoProducto, double precio, String material) {
        super(nombreProducto, codigoProducto, "Generales", precio);
        this.materialProducto=materialProducto;
    }

    public void setMaterialProducto(String materialProducto) {
        this.materialProducto = materialProducto;
    }

    public String getMaterialProducto() {
        return materialProducto;
    }
    
}
