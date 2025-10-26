package Controlador;

import Modelo.AdministradorPedidos;
import Modelo.AdministradorProductos;
import Modelo.AdministradorUsuarios;
import Modelo.Cliente;
import Modelo.Pedidos;
import Modelo.Productos;
import Modelo.Usuario;
import Modelo.Vendedor;
import Vista.VistaActualizarCliente;
import Vista.VistaAgregarStock;
import Vista.VistaCrearCliente;
import Vista.VistaEliminarCliente;
import Vista.VistaVendedor;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ControladorVendedor{
    private VistaVendedor vista;
    private Vendedor vendedorActual;
    private AdministradorProductos adminProductos;
    private AdministradorUsuarios adminUsuarios;
    private AdministradorPedidos adminPedidos;

    public ControladorVendedor(VistaVendedor vista, Vendedor vendedorActual){
        this.vista = vista;
        this.vendedorActual = vendedorActual;
        this.adminUsuarios = new AdministradorUsuarios();
        this.adminProductos=new AdministradorProductos();
        this.adminPedidos = new AdministradorPedidos();
        
        if(vista!= null){   
        configurarEventos();  
        cargarProductosTabla(); 
        cargarClientesTabla();
        cargarProductosTabla();
        cargarPedidosTabla();
        vista.getTblPedidosMV().addMouseListener(new java.awt.event.MouseAdapter(){
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            tablaPedidosClickInteraccion(evt);
        }
    });
        }
    }
    
    public void configurarEventos(){
        vista.getBtonAgregarStock_ProductosMV().addActionListener(e -> abrirVentanaAgregarStock());
        vista.getBtonCrear_Clientes().addActionListener(e -> abrirVentanaCrearCliente());
        vista.getBtonEliminarCliente().addActionListener(e -> abrirVentanaEliminarCliente());
        vista.getBtonActualizarCliente().addActionListener(e -> abrirVentanaActualizarCliente());
        vista.getBtonCargar_ClientesMV().addActionListener(e->cargarClientesDesdeCSV());
    }
    
    
    public boolean agregarStockProducto(String codigoProducto, int cantidad){
        try{
            Productos producto=adminProductos.buscarProductoCodigo(codigoProducto);
            if (producto!= null){
                producto.agregarStock(cantidad);
                adminProductos.guardarEnArchivo();
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
    
//--------------------------------------------------------------------------------------------------------------------------
    
        public void crearClienteDesdeVentana(VistaCrearCliente ventana) {
        try{
            String codigo=ventana.getTxtCodigo().getText();
            String nombre=ventana.getTxtNombreCliente().getText();
            String genero=ventana.getComboGenero().getSelectedItem().toString();
            String cumpleaños=ventana.getTxtCumpleañosCliente().getText();
            String contraseña=ventana.getTxtContraseñaCliente().getText(); 
            if(codigo.isEmpty()||nombre.isEmpty()||contraseña.isEmpty()||cumpleaños.isEmpty()) {
                JOptionPane.showMessageDialog(ventana, "Todos los campos son obligatrios");
                return;
            }         
            boolean exito= adminUsuarios.crearCliente(nombre, codigo, genero, contraseña, cumpleaños);
            if(exito){
                JOptionPane.showMessageDialog(ventana, "Cliente creado exitosamente");
                ventana.dispose();
            } 
            else{
                JOptionPane.showMessageDialog(ventana, "Error:Código ya existe");
            }
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(ventana, "Error: " + e.getMessage());
        }
    }
     
    private void cargarClientesTabla(){
        try{
        Cliente[] clientes=adminUsuarios.getClientes();
        javax.swing.JTable tabla=vista.getTblClientesMV();
        javax.swing.table.DefaultTableModel modelo =(javax.swing.table.DefaultTableModel)tabla.getModel();
        modelo.setRowCount(0);         
        for(Modelo.Cliente cliente : clientes) {
            Object[] fila={cliente.getCodigo(), cliente.getNombre(),cliente.getGenero(),cliente.getCumpleañoscliente()};
            modelo.addRow(fila);
            }
        }
        catch (Exception e){
       }
    }      
     
    public void abrirVentanaCrearCliente(){
        VistaCrearCliente vistaCrear=new VistaCrearCliente();
        vistaCrear.setLocationRelativeTo(vista);
        vistaCrear.setControlador(this);
        vistaCrear.setVisible(true);
    }
    
//----------------------------------------------------------------------------------------------------------------------------------
    
    public void eliminarClienteDesdeVentana(VistaEliminarCliente ventana) {
        try{
            String codigo=ventana.getTxtCodigoEliminar().getText();
            if(codigo.isEmpty()){
                JOptionPane.showMessageDialog(ventana, "Se debe de ingresar un código");
                return;
            }
        
            int confirmacion=JOptionPane.showConfirmDialog(ventana,"¿Está seguro de eliminar este cliente " + codigo + "?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION );  
            if (confirmacion ==JOptionPane.YES_OPTION){
                boolean exito=adminUsuarios.eliminarUsuario(codigo);    
                if (exito){
                    JOptionPane.showMessageDialog(ventana, "Cliente eliminado exitosamente");
                    ventana.dispose();
                    cargarClientesTabla();
                } 
                else{
                JOptionPane.showMessageDialog(ventana, "Error: Cliente no encontrado");
                }
            }
        } 
    catch(Exception e){
        JOptionPane.showMessageDialog(ventana, "Error: "+ e.getMessage());
        } 
    }
    
    public void abrirVentanaEliminarCliente(){
        VistaEliminarCliente vistaEliminar=new VistaEliminarCliente();
        vistaEliminar.setLocationRelativeTo(vista);
        vistaEliminar.setControlador(this);
        vistaEliminar.setVisible(true);
    }
    
//----------------------------------------------------------------------------------------------------------------------------
    
    public Cliente buscarClienteCodigo(String codigo){
        try{
            Modelo.Usuario usuario=adminUsuarios.buscarUsuarioCodigo(codigo);
            if(usuario instanceof Cliente){
            return (Cliente) usuario;
            }
        } 
        catch(Exception e){
        }
        return null;
    }
    
    public void buscarClienteDesdeVentana(VistaActualizarCliente ventana) {
        try{
            String codigo=ventana.getTxtCodigo().getText();
            if (codigo.isEmpty()){
            JOptionPane.showMessageDialog(ventana,"Ingresa un código para buscar");
            return;
            }
        
            Cliente cliente=buscarClienteCodigo(codigo);
            if (cliente!=null){
                ventana.getTxtNuevoNombre().setText(cliente.getNombre());
                ventana.getTxtNuevoGenero().setText(cliente.getGenero());
                ventana.getTxtNuevoCumpleaños().setText(cliente.getCumpleañoscliente());
                ventana.getTxtNuevaContraseña().setText(cliente.getContraseña());          
                JOptionPane.showMessageDialog(ventana,"Cliente encontrado");
            } 
            else{
                JOptionPane.showMessageDialog(ventana, "Cliente no encontrado");
            }
        } 
        catch(Exception e){
            JOptionPane.showMessageDialog(ventana,"Error al buscar cliente: "+e.getMessage());
        }
    }
    
    public void actualizarClienteDesdeVentana(VistaActualizarCliente ventana) {
        try{
            String codigo=ventana.getTxtCodigo().getText();
            String nuevoNombre=ventana.getTxtNuevoNombre().getText();
            String nuevoGenero=ventana.getTxtNuevoGenero().getText();
            String nuevoCumpleaños= ventana.getTxtNuevoCumpleaños().getText();
            String nuevaContraseña =ventana.getTxtNuevaContraseña().getText();
            if(codigo.isEmpty()|| nuevoNombre.isEmpty()||nuevaContraseña.isEmpty()) {
                JOptionPane.showMessageDialog(ventana, "El codigo, nombre y contraseña son obligatorios");
                return;
            }     
            Cliente cliente =buscarClienteCodigo(codigo);
            if (cliente!=null){
                cliente.setNombre(nuevoNombre);
                cliente.setGenero(nuevoGenero);
                cliente.setContraseña(nuevaContraseña);
                cliente.setCumpleañoscliente(nuevoCumpleaños);
            
                adminUsuarios.guardarUsuariosEnArchivo();
                JOptionPane.showMessageDialog(ventana, "Cliente actualizado exitosamente");
                ventana.dispose();
                cargarClientesTabla();
            } 
            else{
                JOptionPane.showMessageDialog(ventana, "Error: Cliente no encontrado");
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(ventana, "Error: " + e.getMessage());
        }
    }
    
    public void abrirVentanaActualizarCliente() {
        VistaActualizarCliente vistaActualizar = new VistaActualizarCliente();
        vistaActualizar.setLocationRelativeTo(vista);
        vistaActualizar.setControlador(this);
        vistaActualizar.setVisible(true);
    }
    
//-----------------------------------------------------------------------------------------------------------------------
     
    
     private void cargarPedidosTabla(){
        try{
            Pedidos[] pedidos =adminPedidos.obtenerTodosPedidos();
            JTable tabla=vista.getTblPedidosMV();
            DefaultTableModel modelo=(DefaultTableModel) tabla.getModel();
            modelo.setRowCount(0);
        
            for(Pedidos pedido:pedidos){
                String estado=pedido.esConfirmado()? "Confirmado":"Pendiente";
                Object[] fila ={pedido.getCodigo(), pedido.getFechaGeneracion(), pedido.getCodigoCliente(),pedido.getNombreCliente(), pedido.getTotal(), estado};
                modelo.addRow(fila);
            }
        } 
        catch (Exception e){
        }
    }  
     
    public void confirmarPedido(String codigoPedido){
        try{
            boolean pedidoConfirmado = adminPedidos.confirmarPedido(codigoPedido);      
            if(pedidoConfirmado){

            Usuario vendedorEnSistema = adminUsuarios.buscarUsuarioCodigo(vendedorActual.getCodigo());
            if(vendedorEnSistema instanceof Vendedor){
                Vendedor vendedorReal=(Vendedor) vendedorEnSistema;
                vendedorReal.incrementarVentas();
            }
            adminUsuarios.guardarUsuariosEnArchivo();   
            JOptionPane.showMessageDialog(vista,"Pedido confirmado exitosamente");
            cargarPedidosTabla();
            } 
            else{
                JOptionPane.showMessageDialog(vista, "Error: Pedido no encontrado o ya confirmado");
            }
        } 
        catch(Exception e){
        JOptionPane.showMessageDialog(vista, "Error al confirmar pedido: " + e.getMessage());
        }
    }
      
    private void tablaPedidosClickInteraccion(java.awt.event.MouseEvent evt){
        JTable tabla=vista.getTblPedidosMV();
        int fila=tabla.getSelectedRow();
        int columna=tabla.getSelectedColumn();
    
        if(fila>=0 && columna>=0){
            if(columna==5){
                String codigoPedido=tabla.getValueAt(fila,0).toString();
                String estado=tabla.getValueAt(fila,5).toString();  
            
                if("Pendiente".equals(estado)){
                    int confirmacion=JOptionPane.showConfirmDialog(vista,"¿Confirmar pedido " + codigoPedido + "?", "Confirmar Pedido", JOptionPane.YES_NO_OPTION);   
                    if(confirmacion==JOptionPane.YES_OPTION){
                    confirmarPedido(codigoPedido);
                    }
                } 
                else{
                    JOptionPane.showMessageDialog(vista,"Este pedido ya está cofirmado");
                }
            }
        }
     
    }
    
//----------------------------------------------------------------------------------------------------------------------------------------------------------

    public void cargarClientesDesdeCSV(){
        try{
            JFileChooser fileChooser=new JFileChooser(); 
            if(fileChooser.showOpenDialog(vista)==JFileChooser.APPROVE_OPTION){
                File archivo=fileChooser.getSelectedFile();
                BufferedReader lector=new BufferedReader(new FileReader(archivo));
                String linea;
                int contadorClientes =0;
                lector.readLine();
            
                while((linea=lector.readLine())!=null&& contadorClientes<100){
                    String[] datos = linea.split(",");
                    if(datos.length>=5) {
                        String codigo=datos[0].trim();
                        String nombre=datos[1].trim();
                        String genero =datos[2].trim();
                        String cumpleaños= datos[3].trim();
                        String contraseña=datos[4].trim();
 
                       if(adminUsuarios.buscarUsuarioCodigo(codigo)==null) {
                            boolean exito=adminUsuarios.crearCliente(nombre, codigo, genero, contraseña, cumpleaños);
                            if(exito){
                               contadorClientes++;
                            }
                        }
                    }
                }
                lector.close();
               cargarClientesTabla();
                JOptionPane.showMessageDialog(vista,"Cargados "+ contadorClientes+" clientes desde CSV");
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(vista,"Error al cargar clietes: " + e.getMessage());
        }
    
    }
        
}
