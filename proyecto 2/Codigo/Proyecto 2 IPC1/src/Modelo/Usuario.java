
package Modelo;
public class Usuario {
    private String nombre;
    private String codigo;
    private String contraseña;
    private String genero;

    public Usuario(String nombre, String codigo, String genero, String contraseña) { //XD
        this.nombre = nombre;
        this.codigo = codigo;
        this.contraseña = contraseña;
        this.genero = genero;
    }
    
    public String getNombre() {
        return nombre;
    }
    public String getCodigo() {
        return codigo;
    }
    public String getContraseña() {
        return contraseña;
    }
    public String getGenero() {
        return genero;
    }
    //Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }
     
}
