
package Modelo;

public class Cliente extends Usuario{
    private String cumpleañosCliente;
    
    public Cliente(String nombre, String codigo, String genero, String contaseña) {
        super(nombre, codigo, genero, contaseña);
        this.cumpleañosCliente=cumpleañosCliente;
    }

    public String getCumpleañoscliente() {
        return cumpleañosCliente;
    }

    public void setCumpleañoscliente(String cumpleañoscliente) {
        this.cumpleañosCliente = cumpleañoscliente;
    }
}
