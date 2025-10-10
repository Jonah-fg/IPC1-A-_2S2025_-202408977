
package Modelo;

public interface OperacionesProductos  extends CRUD{
    boolean agregarStock(String codigoProducto, int cantidad);
    Productos buscarProductoCodigo(String codigo);
    Productos[] obtenerProductoCategoria(String categoria);
    Productos[] obtenerTodosProductos();
    String generarReporteInventario();

}
