
package Modelo;

public class AdministradorUsuarios{
    private Usuario[] usuarios;
    private int contadorUsuarios;
    private int MAX;

    public AdministradorUsuarios(){
        this.MAX = 100;
        this.usuarios = new Usuario[MAX];
        this.contadorUsuarios = 0;
    }
    
    public Usuario buscarUsuarioCodigo(String codigo){
        for(int i=0; i<contadorUsuarios; i++){
            if(usuarios[i].getCodigo().equalsIgnoreCase(codigo)){
               return usuarios[i];
           }
       }
       return null;
    }
    
    public boolean crearUsuario(Usuario usuario){
        if (contadorUsuarios <MAX&& buscarUsuarioCodigo(usuario.getCodigo()) == null) {
            usuarios[contadorUsuarios]= usuario;
            contadorUsuarios++;
            return true;
        }
        return false;
    }
    
    private void administradorDefecto(){
        Administrador admin=new Administrador("Admin", "admin", "M", "IPC1A");
        crearUsuario(admin);
    }
    
    
    public boolean crearUsuario (String nombre, String codigo, String genero, String contraseña){
        Usuario usuario = new Usuario(nombre, codigo, genero, contraseña);
        return crearUsuario(usuario);
    }
    
    public boolean crearVendedor(String nombre, String codigoVendedor, String genero, String contraseña){
        Vendedor vendedor = new Vendedor(nombre, codigoVendedor, genero, contraseña);
        return crearUsuario(vendedor);
    }
    
    public boolean crearCliente(String nombre, String codigoCliente, String genero, String contraseña, String fechaCumpleaños) {
        Cliente cliente = new Cliente(nombre, codigoCliente, genero, contraseña, fechaCumpleaños);
        return crearUsuario(cliente);
    }
 
    public boolean actualizarUsuario(String nombreNuevo, String codigo, String contraseñaNueva) {
        Usuario usuario = buscarUsuarioCodigo(codigo);
        if (usuario != null) {
            usuario.setNombre(nombreNuevo);
            usuario.setContraseña(contraseñaNueva);
            return true;
        }
        return false;
    }
    
    public boolean eliminarUsuario(String codigo) {
        for (int i = 0; i < contadorUsuarios; i++) {
            if (usuarios[i].getCodigo().equals(codigo)) {
                for (int j = i; j < contadorUsuarios - 1; j++) {
                    usuarios[j] = usuarios[j + 1];
                }
                contadorUsuarios--;
                return true;
            }
        }
        return false;
    }

    public boolean autenticacion(String codigo, String contraseña) {
        Usuario usuario = buscarUsuarioCodigo(codigo);
        if(usuario !=null){
             return usuario.getContraseña().equals(contraseña);
        
        }
        return false;   
    }
    
    public Usuario[] obtenerTodosUsuarios() {
        Usuario[] todosUsuarios = new Usuario[contadorUsuarios];
        for (int i = 0; i < contadorUsuarios; i++) {
            todosUsuarios[i] = usuarios[i];
        }
        return todosUsuarios;
    }
}
