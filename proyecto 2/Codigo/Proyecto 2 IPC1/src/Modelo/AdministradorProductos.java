package Modelo;

public class AdministradorProductos{
    private Productos[] productos;
    private int MAX;
    private int contadorProductos;

    public AdministradorProductos(){
        this.MAX = 100;
        this.productos = new Productos[MAX];
        this.contadorProductos = 0;
        crearProductos(new ProductosTecnologicos("Laptop Gamng", "P001", 5000.00, 24));
        
        crearProductos(new ProductosAlimenticios("Jamon", "P002", 10.00, "15/12/2024"));
        crearProductos(new ProductosGenerales("Escritrio", "P003", 100.00, "Madera"));
    }
    
    public Productos buscarProductoCodigo(String codigo){
        for (int i = 0; i < contadorProductos; i++){
            if (productos[i].getCodigoProducto().equals(codigo)){
                return productos[i];
            }
        }
        return null;
    }
    
    public boolean crearProductos(Productos producto) {
        if (contadorProductos < MAX && buscarProductoCodigo(producto.getCodigoProducto()) == null){
            productos[contadorProductos] = producto;
            contadorProductos++;
            return true;
        }
        return false;
    }
    
    public boolean eliminarProducto(String codigo){
        for (int i = 0; i < contadorProductos;i++){
            if (productos[i].getCodigoProducto().equals(codigo)){
                for (int j = i; j < contadorProductos - 1; j++){
                    productos[j] = productos[j + 1];
                }
                contadorProductos--;
                return true;
            }
        }
        return false;
    }
    
    public Productos[] obtenerTodosProductos(){
        Productos[] todosProductos = new Productos[contadorProductos];
        for(int i = 0; i < contadorProductos; i++){
            todosProductos[i] = productos[i];
        }
        return todosProductos;
    }
    
    public Productos[] obtenerProductoCategoria(String categoria){
        int contador = 0;
        for (int i = 0; i < contadorProductos; i++){
            if (productos[i].getCategoria().equals(categoria)){
                contador++;
            }
        }   
        Productos[] resultado = new Productos[contador];
        int iterador = 0;
        for (int i = 0; i < contadorProductos; i++){
            if (productos[i].getCategoria().equals(categoria)){
                resultado[iterador] = productos[i];
                iterador++;
            }
        }
        return resultado;
    }
    
}

