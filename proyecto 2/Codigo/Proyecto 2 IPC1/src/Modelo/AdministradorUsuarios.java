
package Modelo;

public class AdministradorUsuarios implements OperacionesUsuarios {
    private Usuario[] usuarios;
    private int contadorUsuarios;
    private int MAX;

    public AdministradorUsuarios(){
        this.MAX = 100;
        this.usuarios = new Usuario[MAX];
        this.contadorUsuarios = 0;
    }
    
    public boolean crearUsuario(Usuario usuario){
        if(contadorUsuarios<MAX){
            usuarios[contadorUsuarios]=usuario;
            contadorUsuarios++;
            return true;    
        }
        return false;
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

    @Override
    public Usuario buscarUsuarioCodigo(String codigo) {
       for(int i=0; i<contadorUsuarios; i++){
           if(usuarios[i].getCodigo().equalsIgnoreCase(codigo)){
               return usuarios[i];
           }
       }
       return null;
    }

    @Override
    public boolean autenticacion(String codigo, String contraseña) {
        Usuario usuario = buscarUsuarioCodigo(codigo);
        if(usuario !=null){
             return usuario.getContraseña().equals(contraseña);
        
        }
        return false;
       
    }
    
    @Override
    public Usuario[] obtenerTodosUsuarios() {
        Usuario[] todosUsuarios = new Usuario[contadorUsuarios];
        for (int i = 0; i < contadorUsuarios; i++) {
            todosUsuarios[i] = usuarios[i];
        }
        return todosUsuarios;
    }

    @Override
    public boolean actualizar() {
        return true;
        
    }

    @Override
    public boolean eliminar() {
       return false;
    }

    @Override
    public boolean crear() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
