
package proyecto.pkg1.ipc1;

import java.util.Scanner;
public class Proyecto1IPC1 {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Inventario inv= new Inventario();
        int Opciones;
        do{
            System.out.println("-----SISTEMA------");
            System.out.println("1. Agregar producto ");
            System.out.println("2. Buscar producto");
            System.out.println("3. Eliminar producto");
            System.out.println("4. Registrar venta");
            System.out.println("5. Generar reporte");
            System.out.println("6. Ver datos del estudiante");
            System.out.println("7. Bitacora");
            System.out.println("8. Salir");
            System.out.println("Selecciona una opcion: ");
            
            Opciones=scanner.nextInt();
            scanner.nextLine();
            
            switch(Opciones){
                
                case 1:
                    inv.agragarunProducto(scanner);
                    break;
                    
                case 2: 
                    inv.buscarProducto(scanner);
                    break;
                
                case 3:
                    inv.eliminarProducto(scanner);
                    break;
                
                case 4:
                    inv.registroVentas(scanner);
                    break;
                    
                case 5:
                    inv.generacionReportes();
                    break;
                    
                case 6:
                    datosEstudiante();
                    break;
                    
                case 7:
                    inv.verBitacora();
                    break;
                    
                case 8:
                    System.out.println("Esta saliendo del programa, feliz dia :) ");
                    break;
                    
                default:
                    System.out.println("Esta opcion no es valida, ingrese uno valido");
            }         
        } while(Opciones !=8);
    }
    
    public static void datosEstudiante(){
        System.out.println("----Datos del estudiante----");
        System.out.println("Nombre: Jonathan Eduardo Fuentes Garcia");
        System.out.println("Carnet: 202408977");
        System.out.println("GitHub: ");
    }
   
}
 