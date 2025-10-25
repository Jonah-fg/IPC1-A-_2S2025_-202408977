
package Controlador;

import Modelo.AdministradorPedidos;
import Modelo.AdministradorProductos;
import Modelo.Cliente;
import Modelo.Pedidos;
import Modelo.Productos;
import Vista.VistaCliente;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ControladorCliente{
    private Productos[] carrito;
    private VistaCliente vista;
    private Cliente clienteActual;
    private int[] carritoCantidades; 
    private int contadorCarrito;
    private AdministradorProductos adminProductos;
    private AdministradorPedidos adminPedidos;
    private static final int MAX=100;
    
    public ControladorCliente(VistaCliente vista, Cliente clienteActual) {
        this.vista = vista;
        this.clienteActual =clienteActual;
        this.adminProductos = new AdministradorProductos();
        this.adminPedidos = new AdministradorPedidos();
        this.carritoCantidades = new int[MAX];
        this.carrito =new Productos[MAX];
        this.contadorCarrito= 0;
        cargarHistorialCompras();
        configurarEventos();
        cargarProductosTabla();
    }   
        
    public void configurarEventos(){
        vista.getBtonRealizarPedido().addActionListener(e->realizarPedido());
        vista.getTblProductosMC().addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseClicked(java.awt.event.MouseEvent evt){
                JTable tabla=vista.getTblProductosMC();
                int fila=tabla.getSelectedRow();
                int columna=tabla.getSelectedColumn(); 
        
                if(fila>=0 && columna==4){
                    String codigoProducto=tabla.getValueAt(fila,0).toString();
                    agregarProductoCarrito(codigoProducto);
                }
            }
        });  
        
        vista.getTblCarritoCompraMC().addMouseListener(new java.awt.event.MouseAdapter(){
        public void mouseClicked(java.awt.event.MouseEvent evt){
            JTable tabla = vista.getTblCarritoCompraMC();
            int fila = tabla.getSelectedRow();
            int columna = tabla.getSelectedColumn();
        
            if (fila>=0&& columna==5){ 
            eliminarProductoCarrito(fila);
            }
        }
        });
    }
    
    private double calcularTotalCarrito(){
        double total=0;
        for(int i=0;i< contadorCarrito;i++){
            total +=carrito[i].getPrecio();
        }
        return total;
    }

    private void limpiarCarrito(){
        for(int i=0; i<contadorCarrito; i++){
            carrito[i]=null;
        }
        contadorCarrito=0;
        actualizarCarrito();
    }
    
    
    public void realizarPedido() {
        try{
            if(contadorCarrito==0){
                JOptionPane.showMessageDialog(vista,"Carrito vacío");
                return;
            }  
            double total=0;
            for(int i=0; i<contadorCarrito;i++){
                total+=carrito[i].getPrecio()*carritoCantidades[i];
            }
            String codigoPedido=adminPedidos.generarCodigoPedido();
            String fechaActual=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            Pedidos nuevoPedido=new Pedidos(codigoPedido, clienteActual.getCodigo(),fechaActual, clienteActual.getNombre(), total);
            boolean exito=adminPedidos.crearPedido(nuevoPedido);
            if (exito){
                JOptionPane.showMessageDialog(vista, "Pedido realizado exitosamente");
                limpiarCarrito();
            } 
            else{
                JOptionPane.showMessageDialog(vista,"Error al crear pedido");
            }
        } 
        catch (Exception e){
            JOptionPane.showMessageDialog(vista,"Error: " + e.getMessage());
        }
    }    
    
    private void cargarProductosTabla(){
        try{
            Productos[] productos=adminProductos.obtenerTodosProductos();
            JTable tabla =vista.getTblProductosMC();
            DefaultTableModel modelo=(DefaultTableModel)tabla.getModel();
            modelo.setRowCount(0);
            
            for(Productos producto:productos){
                Object[] fila ={producto.getCodigoProducto(),producto.getNombreProducto(), producto.getCategoria(),producto.getStock(), "Agregar al carrito"};
                modelo.addRow(fila);
            }
        }
       catch (Exception e){
        }
    }
    
    private void actualizarCarrito(){
        try{
            JTable tablaCarrito=vista.getTblCarritoCompraMC(); 
            DefaultTableModel modelo=(DefaultTableModel) tablaCarrito.getModel();
            modelo.setRowCount(0); 
            double total =0;
            for(int i=0;i<contadorCarrito; i++){
                Productos producto= carrito[i];
                int cantidad=carritoCantidades[i];
                double subtotal=producto.getPrecio()*cantidad;
                total+=subtotal;
                
                Object[] fila={producto.getCodigoProducto(),producto.getNombreProducto(), cantidad, producto.getPrecio(),subtotal, "Eliminar"};
                modelo.addRow(fila);
            }
        } 
        catch(Exception e){
        }
    }    
     
    public void agregarProductoCarrito(String codigoProducto){
        try{
            if(contadorCarrito>= MAX){
                JOptionPane.showMessageDialog(vista,"Carrito lleno");
                return;
            }
            Productos producto=adminProductos.buscarProductoCodigo(codigoProducto);
            if(producto !=null&& producto.getStock()>0){
                String cantidadProducto=JOptionPane.showInputDialog(vista, "¿Cuántas unidades de " + producto.getNombreProducto() + "?\n" +"Precio: $" + producto.getPrecio() + "\n" +"Stock disponible: " + producto.getStock(),"1" );
                if(cantidadProducto==null|| cantidadProducto.trim().isEmpty()){
                    return;
                }            
                int cantidad=Integer.parseInt(cantidadProducto);
                carrito[contadorCarrito]=producto;
                carritoCantidades[contadorCarrito]=cantidad;
                contadorCarrito++;
                JOptionPane.showMessageDialog(vista, "Agregado al carrito");
                actualizarCarrito();     
            }
            else{
            JOptionPane.showMessageDialog(vista, "Producto no encontrado");
            }
        } 
        catch (Exception e){
            JOptionPane.showMessageDialog(vista, "Error");
        }
    }
    
    private void eliminarProductoCarrito(int indice){
        try{
            String nombreProducto=carrito[indice].getNombreProducto();
            int confirmacion=JOptionPane.showConfirmDialog(vista, "¿Eliminar " +nombreProducto + " del carrito?","Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        
            if(confirmacion==JOptionPane.YES_OPTION){
                for(int i=indice; i<contadorCarrito -1;i++){
                    carrito[i] = carrito[i +1];
                    carritoCantidades[i] =carritoCantidades[i+ 1];
                }
                contadorCarrito--;
                actualizarCarrito();
                JOptionPane.showMessageDialog(vista,"Producto eliminado del carito");
            }
        } 
        catch(Exception e){
        JOptionPane.showMessageDialog(vista, "Error al eliminar producto");
        }
    } 
    
//--------------------------------------------------------------------------------------------------------------------------
    
    private void cargarHistorialCompras() {
        try{
            Pedidos[] pedidosCliente=adminPedidos.obtenerPedidosPorCliente(clienteActual.getCodigo());
            JTable tablaHistorial=vista.getTblHistorialComprasMC();
            DefaultTableModel modelo =(DefaultTableModel) tablaHistorial.getModel();
            modelo.setRowCount(0);
        
            for(Pedidos pedido: pedidosCliente){
                Object[] fila={pedido.getCodigo(),pedido.getFechaGeneracion(),pedido.getTotal()};
                modelo.addRow(fila);
            }
        
        System.out.println("Historial cargado: " + pedidosCliente.length + " pedidos");
        
        } 
        catch (Exception e){
        System.out.println("Error al cargar historial: " + e.getMessage());
        }
    }    
}
