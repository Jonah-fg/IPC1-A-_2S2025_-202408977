
package Modelo;

public class Cliente extends Usuario{
    private String cumpleañoscliente;
    
    public Cliente(String nombre, String codigo, String genero, String contaseña) {
        super(nombre, codigo, genero, contaseña);
        this.cumpleañoscliente=cumpleañoscliente;
    }

    public String getCumpleañoscliente() {
        return cumpleañoscliente;
    }

    public void setCumpleañoscliente(String cumpleañoscliente) {
        this.cumpleañoscliente = cumpleañoscliente;
    }
}
