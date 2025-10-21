package Controlador;

import Modelo.AdministradorProductos;
import Modelo.AdministradorUsuarios;
import Modelo.Productos;
import Modelo.ProductosAlimenticios;
import Modelo.ProductosGenerales;
import Modelo.ProductosTecnologicos;
import Modelo.Usuario;
import Modelo.Vendedor;
import Vista.VistaActualizarProducto;
import Vista.VistaActualizarVendedor;
import Vista.VistaAdministrador;
import Vista.VistaCreacionProductos;
import Vista.VistaCrearVendedor;
import Vista.VistaEliminarProducto;
import Vista.VistaEliminarVendedor;
import javax.swing.JOptionPane;

public class controladorAdministrador {
    private VistaAdministrador vista;
    private AdministradorProductos adminProductos;
    private AdministradorUsuarios adminUsuarios;

    public controladorAdministrador(VistaAdministrador vista){
        this.adminProductos = new AdministradorProductos();
        this.vista = vista; 
        this.adminUsuarios = new AdministradorUsuarios();
        if(vista!= null){   
           configurarEventos();
           actualizarTablaVendedores();
           cargarProductosTabla(); 
        }
    }
    
    private void configurarEventos(){
        vista.getBtonCrear_ProductosMA().addActionListener(e -> abrirVentanaCrearProducto());
        vista.getBtonCrearVendedoresMA().addActionListener(e -> abrirVentanaCrearVendedor());
        vista.getBtonActualizarVendedoresMA().addActionListener(e -> abrirVentanaActualizarVendedor());
        vista.getBtonEliminarVendedoresMA().addActionListener(e -> abrirVentanaEliminarVendedor());
    }
    
    public boolean crearProducto(String nombre, String codigo, String categoria, double precio, String atributoEspecial){  
        try{  
            Productos nuevoProducto=null;
            switch(categoria) {
                case "Tecnología":
                    int mesesGarantia = Integer.parseInt(atributoEspecial);
                    nuevoProducto=new ProductosTecnologicos(nombre, codigo, precio, mesesGarantia);
                    break;
                    
                case "Alimento":
                    nuevoProducto=new ProductosAlimenticios(nombre, codigo, precio, atributoEspecial);
                    break;
                    
                case "Generales":
                    nuevoProducto =new ProductosGenerales(nombre, codigo, precio, atributoEspecial);
                    break;   
                default:
                    return false;
            }  
            if (nuevoProducto!=null){
                return adminProductos.crearProductos(nuevoProducto);
            }
            
        } 
        catch (NumberFormatException e){
        }
        return false;
    }
    
    public void crearProductoDesdeVentana(VistaCreacionProductos ventana){
        try{
            String codigo=ventana.getTxtCodigo();
            String nombre=ventana.getTxtNombre();
            String categoria=ventana.getComboCategoria();
            String atributo=ventana.getTxtAtributo();
            
            if(codigo.isEmpty()||nombre.isEmpty()||atributo.isEmpty()){
                JOptionPane.showMessageDialog(ventana,"Todos los camps son obligatoros");
                return;
            }
            double precio=100.0;
            boolean exito = crearProducto(nombre, codigo,categoria, precio,atributo);
            if (exito){
                JOptionPane.showMessageDialog(ventana,"Producto creado exitosamente");
                ventana.dispose();
                actualizarTablaProductos();
            } 
            else{
                JOptionPane.showMessageDialog(ventana,"Error: Código ya existe o datos inválids");
            }
            
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(ventana,"Error en los datos: "+ e.getMessage());
            e.printStackTrace();
        }
    }
      
    public void abrirVentanaCrearProducto(){
        VistaCreacionProductos vistaCreacion=new VistaCreacionProductos();
        vistaCreacion.setLocationRelativeTo(vista); 
        vistaCreacion.setVisible(true);
        vistaCreacion.setControlador(this);
    }
  
 //--------------------------------------------------------------------------------------------------+
    
    public boolean actualizarProducto(String codigo, String nuevoNombre, String nuevoAtributo){
        try{
        Productos producto=adminProductos.buscarProductoCodigo(codigo);
        if (producto==null){
            return false; 
        }
        producto.setNombreProducto(nuevoNombre);
        if(producto instanceof ProductosTecnologicos){
            int mesesGarantia=Integer.parseInt(nuevoAtributo);
            ((ProductosTecnologicos) producto).setMesesGarantia(mesesGarantia);
        }
        else if(producto instanceof ProductosAlimenticios){
            ((ProductosAlimenticios) producto).setFechaCaducidad(nuevoAtributo);
        } 
        else if(producto instanceof ProductosGenerales){
            ((ProductosGenerales) producto).setMaterialProducto(nuevoAtributo);
        }   
        return true; 
        
        }
        catch(NumberFormatException e){
            return false; 
        }
    }
    
    public void actualizarProductoDesdeVentana(VistaActualizarProducto ventana){
    try {
        String codigo=ventana.getTxtCodigoBuscar();
        String nombreNuevo=ventana.getTxtNuevoNombre();
        String atributoNuevo =ventana.getTxtNuevoAtributo();
        
        if (codigo.isEmpty()||nombreNuevo.isEmpty()|| atributoNuevo.isEmpty()){
            JOptionPane.showMessageDialog(ventana, "Todos los campos son obligatorios");
            return;
        }
        boolean exito=actualizarProducto(codigo,nombreNuevo, atributoNuevo);
        
        if (exito){
            JOptionPane.showMessageDialog(ventana,"Producto actualizado exitosamente");
            ventana.dispose();
            actualizarTablaProductos();
        } 
        else{
            JOptionPane.showMessageDialog(ventana, "Error: Producto no encontrado o datos inválidos");
        }
        
    } 
    catch (Exception e){
        JOptionPane.showMessageDialog(ventana,"Error: "+ e.getMessage());
    }
   }

 //--------------------------------------------------------------------------------------------------------
    
    public void eliminarProductoDesdeVentana(VistaEliminarProducto ventana){
        
        try{
            String codigo=ventana.getTxtCodigoEliminar();
            if(codigo.isEmpty()){
                JOptionPane.showMessageDialog(ventana,"Debes ingrese un cdigo");
                return;
            }
        
            int confirmacion=JOptionPane.showConfirmDialog(ventana,"¿Está seguro de eliminar el producto "+codigo+ "?", "Confirmar Eliminación", 
            JOptionPane.YES_NO_OPTION);
        
            if(confirmacion==JOptionPane.YES_OPTION){
               boolean exito=eliminarProductoAdministrador(codigo);
            
            if(exito){
                JOptionPane.showMessageDialog(ventana,"Producto eliminado exitosamente");
                ventana.dispose();
                actualizarTablaProductos();
            }
            else{
                JOptionPane.showMessageDialog(ventana,"Error: Producto no encontrado");
            }
            }
        } 
        catch (Exception e){
            JOptionPane.showMessageDialog(ventana,"Error: " + e.getMessage());
        }
    }
    
    public boolean eliminarProductoAdministrador(String codigo){
        boolean exito=adminProductos.eliminarProducto(codigo);
        if(exito){
            actualizarTablaProductos();
        }
        return exito;
    }  
    
//---------------------------------------------------------------------------------------------  
    
    public String obtenerDetallesProducto(String codigo){
        Productos producto=adminProductos.buscarProductoCodigo(codigo);
        if (producto==null){
           return "Producto no encontrado";
        }
    
        String detalle = "Detalle del Producto\n\n";
        detalle +="Código: "+ producto.getCodigoProducto() + "\n";
        detalle +="Nombre: "+producto.getNombreProducto() + "\n";
        detalle += "Categoría: "+producto.getCategoria() + "\n";
        
        if(producto instanceof ProductosTecnologicos){
            int mesesGarantia=((ProductosTecnologicos) producto).getMesesGarantia();
            detalle +="Meses de garantía: "+ mesesGarantia;
        } 
        else if(producto instanceof ProductosAlimenticios){
            String fechaCaducidad=((ProductosAlimenticios) producto).getFechaCaducidad();
            detalle+="Fecha de caducidad: " +fechaCaducidad;
        } 
        else if(producto instanceof ProductosGenerales) {
            String material=((ProductosGenerales) producto).getMaterialProducto();
            detalle += "Material: " +material;
        }
        return detalle;
    }
     
//----------------------------------------------------------------------------------------------
    
    public boolean crearVendedor(String codigo, String nombre, String genero, String contraseña) {
        try{
            Vendedor nuevoVendedor=new Modelo.Vendedor(nombre, codigo, genero, contraseña);
            return adminUsuarios.crearUsuario(nuevoVendedor);
        }
        catch (Exception e) {
            System.out.println("Error al crear vendedor: " + e.getMessage());
            return false;
        }
    }
    
    public void crearVendedorDesdeVentana(VistaCrearVendedor ventana){
        try{
            String codigo=ventana.getTxtCodigoVendedor();
            String nombre=ventana.getTxtNombreVendedor();
            String genero=ventana.getComboGenero();
            String contraseña=ventana.getTxtContraseñaVendedor();        
            if(codigo.isEmpty()||nombre.isEmpty() || contraseña.isEmpty()){
                JOptionPane.showMessageDialog(ventana,"Todos los campos son obligatorios");
                return;
            }
        
            boolean exito=crearVendedor(codigo, nombre, genero, contraseña);     
            if (exito){
                JOptionPane.showMessageDialog(ventana,"Vendedor creado exitosamente");
                ventana.dispose();
                actualizarTablaVendedores(); 
            }
            else{
                JOptionPane.showMessageDialog(ventana,"Error: Código ya existe o datos inválidos");
            }
        
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(ventana, "Error en los datos: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void actualizarTablaVendedores(){
        try{
            Vendedor[] vendedores=adminUsuarios.getVendedores();
            javax.swing.JTable tabla= vista.getTblVendedoresMA();
            javax.swing.table.DefaultTableModel modelo=(javax.swing.table.DefaultTableModel)tabla.getModel();
            modelo.setRowCount(0);
        
            for(Modelo.Vendedor vendedor : vendedores){
                Object[] fila={vendedor.getCodigo(),vendedor.getNombre(),vendedor.getGenero(),vendedor.getVentasConfirmadas() };
                modelo.addRow(fila);
            }
        } 
        catch (Exception e){
            System.out.println("Error al cargar vendedores en tabla: " + e.getMessage());
        }
    }
    
    public void abrirVentanaCrearVendedor(){
        VistaCrearVendedor vistaCrear=new VistaCrearVendedor();
        vistaCrear.setLocationRelativeTo(vista);
        vistaCrear.setControlador(this);
        vistaCrear.setVisible(true);
    }
    
//-----------------------------------------------------------------------------------------------------------

    public Vendedor buscarVendedor(String codigo){
        try{
            Usuario usuario=adminUsuarios.buscarUsuarioCodigo(codigo);
            if(usuario instanceof Vendedor){
                return (Vendedor) usuario;
            }
        } 
        catch (Exception e){
        }
      return null;
    }
    
    public boolean actualizarVendedor(String codigo, String nuevoNombre,String nuevaContraseña){
        try{
            return adminUsuarios.actualizarUsuario(nuevoNombre, codigo, nuevaContraseña);
        } 
        catch (Exception e){
            return false;
        }
    }
    
    public void actualizarVendedorDesdeVentana(VistaActualizarVendedor ventana) {
        try{
            String codigo= ventana.getTxtCodigoBuscar();
            String nuevoNombre=ventana.getTxtNuevoNombre();
            String nuevaContraseña =ventana.getTxtNuevaContraseña();      
            if(codigo.isEmpty()|| nuevoNombre.isEmpty()||nuevaContraseña.isEmpty()){
                JOptionPane.showMessageDialog(ventana, "Todos los campos son obligatoros");
                return;
            }
            boolean exito=actualizarVendedor(codigo,nuevoNombre,nuevaContraseña);  
            if (exito){
                JOptionPane.showMessageDialog(ventana,"Vendedor actualizao exitosamente");
                ventana.dispose();
                actualizarTablaVendedores();
            }
            else{
                JOptionPane.showMessageDialog(ventana,"Error:Vendedor no enconrado o datos inválidos");
            }
        
        } 
        catch (Exception e){
            JOptionPane.showMessageDialog(ventana,"Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void abrirVentanaActualizarVendedor(){
        VistaActualizarVendedor vistaActualizar=new VistaActualizarVendedor();
        vistaActualizar.setLocationRelativeTo(vista);
        vistaActualizar.setControlador(this);
        vistaActualizar.setVisible(true);
    }

//--------------------------------------------------------------------------------------------------------------
    
    public boolean eliminarVendedor(String codigo) {
        try{
            return adminUsuarios.eliminarUsuario(codigo);
        }
        catch(Exception e){
            return false;
        }
    }
    
    public void eliminarVendedorDesdeVentana(VistaEliminarVendedor ventana) {
        try{
            String codigo =ventana.getTxtCodigoEliminar();
            if(codigo.isEmpty()){
                JOptionPane.showMessageDialog(ventana, "Debes ingresar un código obligatorio :(");
                return;
            }
            int confirmacion=JOptionPane.showConfirmDialog(ventana,"¿Está seguro de eliminar al vendedor "+ codigo+ "?","Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
            if (confirmacion==JOptionPane.YES_OPTION){
                boolean exito=eliminarVendedor(codigo);    
                if (exito){
                    JOptionPane.showMessageDialog(ventana,"Vendedor eliminado exitosamente");
                    ventana.dispose();
                    actualizarTablaVendedores();
                } 
                else{
                    JOptionPane.showMessageDialog(ventana,"Error: El Vendedor no encontrado");
                }
            }
        
        } 
        catch (Exception e){
        JOptionPane.showMessageDialog(ventana, "Error: " + e.getMessage());
        }
    }
    
    public void abrirVentanaEliminarVendedor() {
        VistaEliminarVendedor vistaEliminar=new VistaEliminarVendedor();
        vistaEliminar.setLocationRelativeTo(vista);
        vistaEliminar.setControlador(this);
        vistaEliminar.setVisible(true);
    }
    
//---------------------------------------------------------------------------------------------------------------
    
    private void cargarProductosTabla(){
        try{
            Productos[] productos=adminProductos.obtenerTodosProductos();   
            javax.swing.JTable tabla=vista.getTblProductosMA();
            javax.swing.table.DefaultTableModel modelo=(javax.swing.table.DefaultTableModel)tabla.getModel();
            modelo.setRowCount(0);
        
            for(Productos producto:productos){
                Object[] fila={producto.getCodigoProducto(),producto.getNombreProducto(), producto.getCategoria(),"Ver detalles"};
                modelo.addRow(fila);
           }   
        } 
        catch (Exception e){
        System.out.println("Error al cargar productos en tabla: " + e.getMessage());
        e.printStackTrace();
        }
    }

    public void actualizarTablaProductos(){
        cargarProductosTabla();      
    }
}
