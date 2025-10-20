package Controlador;

import Modelo.AdministradorProductos;
import Modelo.Productos;
import Modelo.ProductosAlimenticios;
import Modelo.ProductosGenerales;
import Modelo.ProductosTecnologicos;
import Vista.VistaActualizarProducto;
import Vista.VistaAdministrador;
import Vista.VistaCreacionProductos;
import Vista.VistaEliminarProducto;
import javax.swing.JOptionPane;

public class controladorAdministrador {
    private VistaAdministrador vista;
    private AdministradorProductos adminProductos;

    public controladorAdministrador(VistaAdministrador vista){
        this.adminProductos = new AdministradorProductos();
        this.vista = vista; 
        if(vista!= null){   
           configurarEventos();
           cargarProductosTabla(); 
        }
    }
    
    private void configurarEventos(){
        vista.getBtonCrear_ProductosMA().addActionListener(e -> abrirVentanaCrearProducto());
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
    catch (Exception e) {
        JOptionPane.showMessageDialog(ventana, "Error: " + e.getMessage());
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
        detalle += "Código: "+ producto.getCodigoProducto() + "\n";
        detalle += "Nombre: "+producto.getNombreProducto() + "\n";
        detalle += "Categoría: "+producto.getCategoria() + "\n";
        
        if(producto instanceof ProductosTecnologicos){
            int mesesGarantia=((ProductosTecnologicos) producto).getMesesGarantia();
            detalle +="Meses de garantía: "+ mesesGarantia;
        } 
        else if(producto instanceof ProductosAlimenticios){
            String fechaCaducidad=((ProductosAlimenticios) producto).getFechaCaducidad();
            detalle+="Fecha de caducidad: " + fechaCaducidad;
        } 
        else if(producto instanceof ProductosGenerales) {
            String material=((ProductosGenerales) producto).getMaterialProducto();
            detalle += "Material: " +material;
        }
        return detalle;
    }
     
//----------------------------------------------------------------------------------------------
    
    private void cargarProductosTabla(){
        try{
            Productos[] productos=adminProductos.obtenerTodosProductos();   
            javax.swing.JTable tabla=vista.getTblProductosMA();
            javax.swing.table.DefaultTableModel modelo=(javax.swing.table.DefaultTableModel)tabla.getModel();
            modelo.setRowCount(0);
        
            for(Productos producto:productos){
                Object[] fila={producto.getCodigoProducto(),producto.getNombreProducto(), producto.getCategoria(), "Ver detalles"};
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
