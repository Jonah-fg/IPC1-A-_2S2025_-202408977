
package proyecto.pkg1.ipc1;

import java.util.Scanner;

public class Proyecto1IPC1 {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
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
        }
    }
   
}
 