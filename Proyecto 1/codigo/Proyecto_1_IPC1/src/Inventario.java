
import java.util.Scanner;


public class Inventario {
    private productos[] productos;
    private ventas[] ventas;
    private String Usuario= "estudiante";
    private int max=100;
    private bitacora[] bitacora;
    private int contadorVentas;
    private int contadorProductos;
    private int contadorBitacora;

    //constructor
    public Inventario() {
        productos = new productos[max];
        ventas = ventas = new ventas[max];
        contadorVentas = 0; 
        bitacora = bitacora = new bitacora[max];
        contadorProductos = 0;
        contadorBitacora = 0;
    }
    private void agregarBitacora(String nombreAccion, String estadoResultado){
        if(contadorBitacora<max){
            bitacora[contadorBitacora]=new bitacora(nombreAccion, estadoResultado, Usuario);
            contadorBitacora++;
        }
    }
    
    public void agragarunProducto(Scanner scanner){
        System.out.println("\n--- Agrega producto ---");
        System.out.println("ingrese el Codigo del producto nuevo: ");
        String codigo = scanner.nextLine();
        
        for(int i=0; i<contadorProductos; i++){
            if (productos[i].getID().equals(codigo)){
                System.out.println("El codigo ya existe, ingrese uno valido");
                agregarBitacora("agregar producto", "Error del sistema; el codigo esta duplicado");
                return;
            }
        }
    
        System.out.print("Nombre del producto: ");
        String nombreProducto = scanner.nextLine();
        
        System.out.println("precio del producto: ");      
        double precioProducto = scanner.nextDouble();
        
        System.out.print("Cantidad: ");
        int cantidad = scanner.nextInt();
        
        if(precioProducto <= 0 || cantidad < 0){
            System.out.println("Error: El precio y la cantidad deben ser positivos :( ");
            agregarBitacora("Agregar Producto", "Error: Datos no validos");
            return;
        }
        System.out.println("Categoria del producto: ");
        String categoriaProducto = scanner.nextLine();   
        
        productos[contadorProductos] = new productos(nombreProducto, categoriaProducto, codigo, precioProducto, cantidad);  
            contadorProductos++;
            
            System.out.println("Producto agregado exitosamente");
            agregarBitacora("Agregar Producto", "Éxito");
              
    }
    
    public void eliminarProducto(Scanner scanner){
        System.out.println("-----Eliminar producto-----");
        System.out.println("Ingrese el codigo del producto: ");
        String codigo=scanner.nextLine(); 
        
        int posicionEncontrada = -2;
        
        for(int i =0; i<contadorProductos; i++){
            if(productos[i].getID().equals(codigo)){
                posicionEncontrada =  i;
                break;
            }
        }
        if(posicionEncontrada== -2){
            System.out.println("no se encontro el producto :( ");
            agregarBitacora("Eliminar producto", "Error: preoducto no encontrado :( ");
            return;       
        }
        
        System.out.println("El producto fue encontrado");
        productos[posicionEncontrada].MostrarProducto();
        
        System.out.println("¿deseas eliminar este producto? (SI/NO)");
        String confirmacion = scanner.nextLine();
        
        if(confirmacion.equalsIgnoreCase("SI")){
            for(int indice = posicionEncontrada+1; indice<contadorProductos; indice++){
                productos[indice - 1] = productos[indice];
            }
            contadorProductos--;
            System.out.println("El producto fue eliminado exitosamente :) ");
            agregarBitacora("Eliminar preoduct", "Exito: el producto fue elimi ado" );
        }
        else{
            System.out.println("Se cancelo la elimnacion del producto :( ");
            agregarBitacora("Elimknar producto", "Error: se cancelo la eliminacion");
        }           
    }
    
    
    public void registroVentas(Scanner scanner){
         System.out.println("--- REGISTRAR VENTA ---");
         System.out.println("ingrese el codigo del producto: ");
         String codigo = scanner.nextLine(); 
         
        int posicionEncontradaventa = -2;
        for (int i =0; i<contadorProductos; i++){
             if(productos[i].getID().equals(codigo)){
                posicionEncontradaventa =  i;
                break;
             }
        }
        if (posicionEncontradaventa==-2){
        System.out.println("El producto no fue encontrado :( ");
        agregarBitacora("Registrar venta", "Error: el producto no fue encontrado");
            return;
        }
        
        System.out.println("igrese la cantidad a vender: ");
        int cantidad = scanner.nextInt();
        scanner.nextLine();
        
        if(cantidad>productos[posicionEncontradaventa].getCantidad()){
            System.out.println("no hay suficiente Stock para ete producto. disponible: "+ productos[posicionEncontradaventa].getCantidad());
            agregarBitacora("registrar venta", "Error: stock insuficiente para este producto");
            return;
        } 
        System.out.println("calculando el total");
        double total = cantidad * productos[posicionEncontradaventa].getPrecio();
        System.out.println("venta registrada exitosamente");
        System.out.println("el total a pagar es de: Q"+ total);
        agregarBitacora("Registrar venta", "Exito: venta registrada");
        
         productos[posicionEncontradaventa].setCantidad(productos[posicionEncontradaventa].getCantidad()-cantidad);
         ventas[contadorVentas]= new ventas(codigo, cantidad, total);
         contadorVentas++;
    }
    if
                     
}
