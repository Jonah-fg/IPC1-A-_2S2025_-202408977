
package Modelo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AdministradorPedidos implements Serializable{
    private static final long serialVersionUID = 1L;
    private static final String Archivo_pedidos = "pedidos.ser";
    private int contadorPedidos;
    private Pedidos[] pedidos;
    private int MAX;

    public AdministradorPedidos() {
        this.MAX = 100;
        this.pedidos =new Pedidos[MAX];
        this.contadorPedidos =0;
        cargarDesdeArchivo();
        if(contadorPedidos==0){
            crearPedido(new Pedidos(generarCodigoPedido(), "C001", "2024-01-15", "Juan Pérez", 150.50));
            crearPedido(new Pedidos(generarCodigoPedido(), "C002", "2024-01-16", "María García", 75.25));
            crearPedido(new Pedidos(generarCodigoPedido(), "C003", "2024-01-17", "Carlos López", 200.00));
        }
    }
    
    public boolean crearPedido(Pedidos pedido) {
        if(contadorPedidos<MAX&& buscarPedidoCodigo(pedido.getCodigo())==null){
            pedidos[contadorPedidos]=pedido;
            contadorPedidos++;
            guardarEnArchivo();
            return true;
        }
        return false;
    }
    
    public Pedidos buscarPedidoCodigo(String codigo){
        for (int i=0; i<contadorPedidos;i++){
            if(pedidos[i].getCodigo().equals(codigo)){
                return pedidos[i];
            }
        }
        return null;
    }
    
    
     public boolean confirmarPedido(String codigoPedido){
        Pedidos pedido =buscarPedidoCodigo(codigoPedido);
        if(pedido!=null &&!pedido.esConfirmado()) {
            pedido.setConfirmacion(true);
            guardarEnArchivo();
            return true;
        }
        return false;
    }
     
    public Pedidos[] obtenerPedidosPendientes(){
        int contador=0;
        for (int i=0; i<contadorPedidos;i++){
            if (!pedidos[i].esConfirmado()){
                contador++;
            }
        }
        
        Pedidos[] pedidosPendientes=new Pedidos[contador];
        int iterador=0;
        for(int i=0; i<contadorPedidos; i++){
            if(!pedidos[i].esConfirmado()){
                pedidosPendientes[iterador]=pedidos[i];
                iterador++;
            }
        }
        return pedidosPendientes;
    }
    
    public Pedidos[] obtenerTodosPedidos(){
        Pedidos[] todosPedidos=new Pedidos[contadorPedidos];
        for(int i=0;i<contadorPedidos;i++){
            todosPedidos[i]=pedidos[i];
        }
        return todosPedidos;
    }
    
    public String generarCodigoPedido() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return "PED" + sdf.format(new Date());
    }
    
    private void guardarEnArchivo(){
        try(ObjectOutputStream salida=new ObjectOutputStream(new FileOutputStream(Archivo_pedidos))) {
            Pedidos[] pedidosGuardar=new Pedidos[contadorPedidos];
            for(int i=0;i<contadorPedidos; i++){
                pedidosGuardar[i]=pedidos[i];
            }
            salida.writeObject(pedidosGuardar);
            salida.writeInt(contadorPedidos);
        } 
        catch (IOException e) {
        }
    }
    
    @SuppressWarnings("unchecked")
    private void cargarDesdeArchivo() {
        try(ObjectInputStream entrada=new ObjectInputStream(new FileInputStream(Archivo_pedidos))) {
            Pedidos[] pedidosCargados = (Pedidos[]) entrada.readObject();
            contadorPedidos = entrada.readInt();

            for (int i=0; i<contadorPedidos && i<MAX;i++) {
                pedidos[i] = pedidosCargados[i];
            }
        }
        catch (IOException | ClassNotFoundException e){
            System.out.println("Archivo de pedidos no encontrado, se creará uno nuevo");
        }
    }
    
    
}
