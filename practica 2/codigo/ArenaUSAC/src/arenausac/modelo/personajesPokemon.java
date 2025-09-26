
package arenausac.modelo;

public class personajesPokemon {
    private int ID;
    private String nombrePokemon;
    private String tipoArma;
    private int velocidad;
    private int puntosVida;
    private int defensa;
    private int agilidad;
    private int nivelAtaque;
    private int totalBatallas;
    private int batallasGanadas;
    private int batallasPerdidas;

    public personajesPokemon(int ID, String nombrePokemon, String tipoArma, int velocidad, int puntosVida, int defensa, int agilidad, int nivelAtaque) {
        this.ID = ID++;
        this.nombrePokemon = nombrePokemon;
        this.tipoArma = tipoArma;
        this.velocidad = velocidad;
        this.puntosVida = puntosVida;
        this.defensa = defensa;
        this.agilidad = agilidad;
        this.nivelAtaque = nivelAtaque;
        this.batallasGanadas=0;
        this.batallasPerdidas=0;
    }
    //Getters
    public int getID() {
        return ID;
    }

    public String getNombrePokemon() {
        return nombrePokemon;
    }

    public String getTipoArma() {
        return tipoArma;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public int getPuntosVida() {
        return puntosVida;
    }

    public int getDefensa() {
        return defensa;
    }

    public int getAgilidad() {
        return agilidad;
    }

    public int getNivelAtaque() {
        return nivelAtaque;
    }
    
    public int getBatallasPerdidas(){
        return batallasPerdidas;
    }
    
    public int getBatallasGanadas(){
        return batallasPerdidas;
    }
    
    public void incrementarBatallasPerdidas() {
        this.batallasPerdidas++;
        this.totalBatallas++;
    }
    
    public void incrementarBatallasGanadas(){
        this.batallasGanadas++;
        this.totalBatallas++;
    }
    
    //Setters
    public void setID(int ID) {
        this.ID = ID;
    }

    public void setNombrePokemon(String nombrePokemon) {
        this.nombrePokemon = nombrePokemon;
    }

    public void setTipoArma(String tipoArma) {
        this.tipoArma = tipoArma;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public void setPuntosVida(int puntosVida) {
        this.puntosVida = puntosVida;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public void setAgilidad(int agilidad) {
        this.agilidad = agilidad;
    }

    public void setNivelAtaque(int nivelAtaque) {
        this.nivelAtaque = nivelAtaque;
    }

}
