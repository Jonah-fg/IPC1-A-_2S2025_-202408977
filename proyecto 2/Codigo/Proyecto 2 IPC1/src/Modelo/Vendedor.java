package Modelo;

public class Vendedor extends Usuario {
    private int ventasConfirmadas;

    public Vendedor(String nombre, String codigo, String genero, String contaseña) {
        super(nombre, codigo, genero, contaseña);
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
