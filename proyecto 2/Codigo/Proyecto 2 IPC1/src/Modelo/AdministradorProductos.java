package Modelo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class AdministradorProductos implements Serializable{
    private static final long serialVersionUID = 1L;
    private static final String Archivo_productos="productos.ser";
    private Productos[] productos;
    private int MAX;
    private int contadorProductos;

    public AdministradorProductos(){
        this.MAX = 100;
        this.productos = new Productos[MAX];
        this.contadorProductos = 0;
        cargarDesdeArchivo();  
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
            guardarEnArchivo();
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
                guardarEnArchivo();
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
    
    public void guardarEnArchivo() {
        try(ObjectOutputStream salida=new ObjectOutputStream(new FileOutputStream(Archivo_productos))) {      
            Productos[] productosGuardar=new Productos[contadorProductos];
            for (int i=0; i<contadorProductos;i++){
                productosGuardar[i] =productos[i];
            }       
            salida.writeObject(productosGuardar);
            salida.writeInt(contadorProductos);        
        } 
        catch (IOException e) {
        }
    }
    
    @SuppressWarnings("unchecked")
    private void cargarDesdeArchivo(){
        try(ObjectInputStream entrada=new ObjectInputStream(new FileInputStream(Archivo_productos))){        
            Productos[] productosCargados=(Productos[]) entrada.readObject();
            contadorProductos=entrada.readInt();
            
            for(int i=0;i<contadorProductos&& i<MAX;i++){
                productos[i]=productosCargados[i];
            }
        }
        catch (FileNotFoundException e){
            System.out.println("Archivo de productos no encontrado, se creará uno nuevo");
        } 
        catch (IOException | ClassNotFoundException e){
        }
    }
    
}

