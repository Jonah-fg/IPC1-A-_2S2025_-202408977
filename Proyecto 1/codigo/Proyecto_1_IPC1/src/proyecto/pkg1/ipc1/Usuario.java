
package proyecto.pkg1.ipc1;

public class Usuario {
    private String nombre;
    private int ID;

    public Usuario(String nombre, int ID) {
        this.nombre = nombre;
        this.ID = ID;
    }
    
   //getters

    public String getNombre() {
        return nombre;
    }

    public int getID() {
        return ID;
    }
    
    //setters

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    
    public void mostrarDatosUsuario(){
        System.out.println("------Mostrar datos usuario-------");
        System.out.println("Nombre: "+nombre);
        System.out.println("ID: "+ID);
    }
}

