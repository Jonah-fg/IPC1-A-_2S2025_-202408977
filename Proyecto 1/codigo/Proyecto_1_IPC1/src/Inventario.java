
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
            if (productos[i].equals(codigo)){
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
    
    }
}
