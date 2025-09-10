package proyecto.pkg1.ipc1; 
public class productos {
    private String nombre;
    private String ID;
    private int cantidad;
    private double precio;
    private String categoria;
    
    //constructor
    public productos(String nombre, String categoria, String ID, double precio, int cantidad){
    
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.ID = ID;
        this.cantidad = cantidad;     
    }
    //ggetings
    public String getNombre() {
        return nombre;
    }
    public String getID() {
        return ID;
    }
    public int getCantidad() {
        return cantidad;
    }
    public double getPrecio() {
        return precio;
    }
    public String getCategoria() {
        return categoria;
    }
    
    //settings
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setID(String ID) {
        this.ID = ID;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    public void MostrarProducto(){
        System.out.println("codigo: " + ID);
        System.out.println("nombre del producto: "+ nombre);
        System.out.println("cantidad: " + cantidad);
        System.out.println("precio: Q"+ precio);
        System.out.println("categoria: "+ categoria);
    }
    
}
