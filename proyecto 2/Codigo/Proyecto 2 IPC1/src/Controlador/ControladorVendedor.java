package Controlador;

import Modelo.AdministradorProductos;
import Modelo.AdministradorUsuarios;
import Modelo.Productos;
import Modelo.Vendedor;
import Vista.VistaAgregarStock;
import Vista.VistaVendedor;
import javax.swing.JOptionPane;

public class ControladorVendedor{
    private VistaVendedor vista;
    private Vendedor vendedorActual;
    private AdministradorProductos adminProductos;
    private AdministradorUsuarios adminUsuarios;

    public ControladorVendedor(VistaVendedor vista, Vendedor vendedorActual){
        this.vista = vista;
        this.vendedorActual = vendedorActual;
        this.adminUsuarios = new AdministradorUsuarios();
        this.adminProductos = new AdministradorProductos();;
    }
    
    public void configurarEventos(){
        vista.getBtonAgregarStock_ProductosMV().addActionListener(e -> abrirVentanaAgregarStock());
    }
    
    
    public boolean agregarStockProducto(String codigoProducto, int cantidad){
        try{
            Productos producto=adminProductos.buscarProductoCodigo(codigoProducto);
            if (producto!= null){
                producto.agregarStock(cantidad);
                return true;
            }
            return false;
        } 
        catch (Exception e){
            return false;
        }
    }
    
    
    private void cargarProductosTabla(){
        try{
            Productos[] productos=adminProductos.obtenerTodosProductos();
            javax.swing.JTable tabla=vista.getTblProductosMV();
        
            if(tabla!=null){
                javax.swing.table.DefaultTableModel modelo=(javax.swing.table.DefaultTableModel)tabla.getModel();
                modelo.setRowCount(0); 
                for(Productos producto : productos){
                    Object[] fila={producto.getCodigoProducto(),producto.getNombreProducto(),producto.getCategoria(), producto.getStock(), "Ver historial"};
                    modelo.addRow(fila);
                }
            }
        }
        catch (Exception e) {
        }
    }      
    
    
    public void agregarStockDesdeVentana(VistaAgregarStock ventana) {
        try{
            String codigoProducto=ventana.getTxtCodigoProducto();
            String cantidadStr=ventana.getTxtCantidad();  
            if (codigoProducto.isEmpty()||cantidadStr.isEmpty()){
                JOptionPane.showMessageDialog(ventana, "Todos los campos son obligatorios");
                return;
            }
            int cantidad=Integer.parseInt(cantidadStr);
            if (cantidad <=0){
                JOptionPane.showMessageDialog(ventana, "La cantidad debe ser mayor a 0");
                return;
            }
            boolean exito=agregarStockProducto(codigoProducto, cantidad);
            if (exito){
                JOptionPane.showMessageDialog(ventana, "Stock agregado exitosamente");
                ventana.dispose();
                cargarProductosTabla();
            }
            else{
                JOptionPane.showMessageDialog(ventana, "Error: Producto no encontrado");
            }     
        } 
        catch (NumberFormatException e){
            JOptionPane.showMessageDialog(ventana, "Error: La cantidad debe ser un número válido");
        }
    }
    
    
    public void abrirVentanaAgregarStock(){
        VistaAgregarStock vistaAgregar=new VistaAgregarStock();
        vistaAgregar.setLocationRelativeTo(vista);
        vistaAgregar.setControlador(this);
        vistaAgregar.setVisible(true);
    }   
}

