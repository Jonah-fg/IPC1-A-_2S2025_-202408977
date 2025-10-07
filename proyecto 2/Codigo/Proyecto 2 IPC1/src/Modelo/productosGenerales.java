
package Modelo;

public class productosGenerales extends Productos{
    private String materialProducto;
    
    public productosGenerales(String nombreProducto, String codigoProducto, String Categoria, double precio) {
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
