package CreadordePersonajes_1;
import java.io.InputStream;
import java.util.Scanner; 

public class CreadorPersonajes {
         static int ContadordePersonajes=0;
        static Scanner scanner = new Scanner(System.in);
          static String[] NombresPersonajes = new String[100]; 
         static String[][] habilidades = new String[100][5];
        static String[] Armas = new String[100];
         static short[] nivelPower = new short [100];

    public static void main(String[] args) {
     int opcioness=0;
        do { //menu principal :)
             System.out.println("=== Menú de Gestión de Personajes ===");
            System.out.println("Elige una opción: ");
            System.out.println("1. Agregar Personaje");
             System.out.println("2. Modificar Personaje");
            System.out.println("3. Eliminar Personaje");
             System.out.println("4. Ver Datos de un Personaje");
            System.out.println("5. Ver Listado de Persoajes");
            System.out.println("6. Realizar Pelea entre Personajes");
            System.out.println("7. Ver Historial de Peleas");
             System.out.println("8. Ver Datos del Estudiane");
              System.out.println("9. Salir");
              opcioness=scanner.nextInt();
              scanner.nextLine();
     
            
            switch(opcioness) {
                
                case 1:
                agregarunPersonaje();
                break;
                
                case 2:
                modificarelPersonaje(); 
                break;
                
                case 3:
                eliminarPersonajes();
                break;
                
                case 4:
                verdatosdeunPersonaje();
                break;
                
                case 5:
                versiltadodePersonajes();
                break;
                
                case 6: 
                peleasdePersonajes();
                break;
                
                case 7:
                historialdePeleas();
                break;
                
                case 8:
                DatosdeEstudiante();
                break;
                
                case 9:
                System.out.println("usted esat saliendo");
                break;
                
                default:
                System.out.println("opcion no disponible, ingrese una opcion valida :( ");  
                
            } 
        } 
        while (opcioness !=9);
    }  
//funcion para agregar personajes
    public static void agregarunPersonaje() {

       System.out.println("agrega un personaje");
       if(ContadordePersonajes>=100){
           System.out.println("ha excedido el numero de persnajes :( "); 
       return;
       }
       
       String nombress;
       boolean RepiteNombre;
       
       do {
        RepiteNombre = false;
        System.out.print("agraga el nombre de este personje: ");
        nombress = scanner.nextLine();
       
        for(int j = 0; j < ContadordePersonajes; j++) {
            if(NombresPersonajes[j] != null && NombresPersonajes[j].equalsIgnoreCase(nombress)) {
                System.out.println("este nombre ya exste, ingrese otro :3");
                RepiteNombre = true;
                break;
            }
        }
        } while(RepiteNombre);
        NombresPersonajes[ContadordePersonajes] = nombress;
       
          System.out.println("ingrese un arma: ");
        Armas[ContadordePersonajes]=scanner.nextLine();
        
        System.out.println("ingrese las habilidades del personaje:");
        for(int iterador=0; iterador<5;iterador++){
            boolean habilidadAceptada = false; 
            while(!habilidadAceptada){
                System.out.println("Habilidad " +(iterador+1)+": ");
            String habilidad = scanner.nextLine();
            
            if(habilidad.isEmpty()) {
                System.out.println("Hsbiidad vacia, ingrese una habilidad");
            }
            else {habilidades[ContadordePersonajes][iterador] = habilidad;
                habilidadAceptada = true;
            }
       }
       }
         System.out.println("ingrese el nivel de oder de 1-100: ");
        nivelPower[ContadordePersonajes]=scanner.nextShort();
        scanner.nextLine();
        ContadordePersonajes++;
          System.out.println("Personaje creado con exito :) ");
  
   }
          
//Funcion para modifica personajes
    public static void modificarelPersonaje() {
        if(ContadordePersonajes==0){
        System.out.println("no hay ningun personaje para modificar");
        return;
        }
                        
    }

    public static void eliminarPersonajes() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
//Funcion para ver datos de un personae
    public static void verdatosdeunPersonaje() {
        if(ContadordePersonajes==0){ 
            System.out.println("Aun no hay personajes creados");
            return;   
        }
        System.out.println("lista de personajes disponibels: ");
         for(int iterador=0;iterador<ContadordePersonajes;iterador++){ 
            System.out.println((iterador+1)+" "+NombresPersonajes[iterador]);
            }
        int seleccion=0;
         System.out.println("ingrese el ID del personajes para vers sus caracteristicas del (1-"+ContadordePersonajes+"): ");
        seleccion=scanner.nextInt()-1;
          scanner.nextLine();
          
          if(seleccion<0 || seleccion >= ContadordePersonajes){
              System.out.println("esta opcion no existe, ingrese una opcion valida");
              return;
          }
            System.out.println("Caracteristicas del personajes");
            System.out.println("nombre del personaje: " + NombresPersonajes[seleccion]);
           System.out.println("nivel de poder: " + nivelPower[seleccion]);
            System.out.println("arma del personaje: " + Armas[seleccion]);
            
            System.out.println("habilidades del personaje: ");       
            for(int j=0; j<5; ++j){
                if(habilidades[seleccion][j]!= null){
                    System.out.println("-"+habilidades[seleccion][j]);    
                }
            }
      }

    public static void peleasdePersonajes() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
//funcion para ver el listado de personajes
    public static void versiltadodePersonajes() {
        if(ContadordePersonajes==0){
        System.out.println("Aun no hay personajes en esat lista");
        return;
       }
        
        System.out.println("Lista de personajes");
        for(int iterador=0; iterador<ContadordePersonajes; iterador++){ 
            System.out.println("num.ID "+(iterador+1));
             System.out.println("nombre del personaje: " + NombresPersonajes[iterador]);
           System.out.println("nivel de poder: " + nivelPower[iterador]); //nota despues
            System.out.println("arma del personaje: " + Armas[iterador]);
            System.out.println("habilidades del personaje: ");
            for(int j=0; j<5; ++j){
                if(habilidades[iterador][j]!= null){
                    System.out.println("-"+ habilidades[iterador][j]);
                    System.out.println(" ");
                }
            }
            
            
        }
              
    }

    public static void historialdePeleas() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
// datos sobre mi
    public static void DatosdeEstudiante() {
        System.out.println("nombre: Jonathan Eduardo fuentes Garcia");
        System.out.println("carne: 20240897");
        System.out.println("carrera: Ingenieria en ciencias y sistemas");
        System.out.println("curso: Introduccion a la programacion y computacion");
        System.out.println("seccion: A");
        
    }

  
}
