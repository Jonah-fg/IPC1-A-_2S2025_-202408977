package Modelo;

import java.io.Serializable;

public class Vendedor extends Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    private int ventasConfirmadas;

    public Vendedor(String nombre, String codigo, String genero, String contraseña) {
        super(nombre, codigo, genero, contraseña);
        this.ventasConfirmadas=0;
    }

    public void setVentasConfirmadas(int ventasConfirmadas) {
        this.ventasConfirmadas = ventasConfirmadas;
    }

    public int getVentasConfirmadas() {
        return ventasConfirmadas;
    }
    public void incrementarVentas(){
        this.ventasConfirmadas++;
    }
  
}
