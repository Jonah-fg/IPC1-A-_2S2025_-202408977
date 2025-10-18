package Controlador;

import Modelo.AdministradorProductos;
import Modelo.Productos;
import Modelo.ProductosAlimenticios;
import Modelo.ProductosGenerales;
import Modelo.ProductosTecnologicos;
import Vista.VistaAdministrador;
import Vista.VistaCreacionProductos;
import javax.swing.JOptionPane;

public class controladorAdministrador {
    private VistaAdministrador vista;
    private AdministradorProductos adminProductos;

    public controladorAdministrador(VistaAdministrador vista){
        this.vista = vista;
        this.adminProductos=new AdministradorProductos();
    }   
    
    private void cargarProductosEnTabla(){
        Productos[] productos= adminProductos.obtenerTodosProductos();
        System.out.println("Total productos: "+productos.length); 
    }
    
    public boolean crearProducto(String nombre, String codigo, String categoria, double precio, String atributoEspecial){  
        try{
     
            Productos nuevoProducto =null;
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
    
    private void cargarProductosTabla(){
        Productos[] productos=adminProductos.obtenerTodosProductos();
    }
    
    private void actualizarTablaProductos(){
        cargarProductosTabla();     
    } 
    
    private void crearProductoDesdeVentana(VistaCreacionProductos ventana){
        try{
            String codigo=ventana.getCodigo();
            String nombre=ventana.getNombre();
            String categoria=ventana.getCategoria();
            String atributo= ventana.getAtributo();
            
            if(codigo.isEmpty()||nombre.isEmpty() ||atributo.isEmpty()){
                JOptionPane.showMessageDialog(ventana, "Todos los camps son obligatoros");
                return;
            }
            double precio = 0.0;
            boolean exito = crearProducto(nombre, codigo, categoria, precio, atributo);
            if (exito){
                JOptionPane.showMessageDialog(ventana, "Producto creado exitosamente");
                ventana.dispose();
                actualizarTablaProductos();
            } else{
                JOptionPane.showMessageDialog(ventana, "Error: Código ya existe o datos inválidos");
            }
            
        } catch (Exception e){
            JOptionPane.showMessageDialog(ventana,"Error en los datos: "+ e.getMessage());
            e.printStackTrace();
        }
    }
      
    private void abrirCrearProducto(){
        VistaCreacionProductos vistaCreacion=new VistaCreacionProductos();
        vistaCreacion.setLocationRelativeTo(vista); 
        vistaCreacion.setVisible(true); 
        vistaCreacion.getBtonCrear().addActionListener(new java.awt.event.ActionListener(){
            
        public void actionPerformed(java.awt.event.ActionEvent ev){
            crearProductoDesdeVentana(vistaCreacion);
            }
       }); 
    }

    public boolean eliminarProducto(String codigo){
        boolean exito=adminProductos.eliminarProducto(codigo);
        if(exito){
            actualizarTablaProductos();
        }
        return exito;
    }   
}
