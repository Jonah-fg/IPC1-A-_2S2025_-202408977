
package arenausac.modelo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class batallasPokemon {
    private String fechayhora;
    private int numeroBatallas;
    private String pokemonGanador;
    private String pokemon1;
    private String pokemon2;
    private String bitacoraCombates;

    public batallasPokemon(int numeroBatallas, String pokemonGanador, String pokemon1, String pokemon2, String bitacoraCombates){
        this.fechayhora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        this.numeroBatallas = numeroBatallas;
        this.pokemonGanador = pokemonGanador;
        this.pokemon1 = pokemon1;
        this.pokemon2 = pokemon2;
        this.bitacoraCombates = bitacoraCombates;
    }
    
    //Getters
    public String getFechayhora() {
        return fechayhora;
    }

    public int getNumeroBatallas() {
        return numeroBatallas;
    }

    public String getPokemonGanador() {
        return pokemonGanador;
    }

    public String getPokemon1() {
        return pokemon1;
    }

    public String getPokemon2() {
        return pokemon2;
    }

    public String getBitacoraCombates() {
        return bitacoraCombates;
    }
    
    //Setters
    public void setFechayhora(String fechayhora) {
        this.fechayhora = fechayhora;
    }

    public void setNumeroBatallas(int numeroBatallas) {
        this.numeroBatallas = numeroBatallas;
    }

    public void setPokemonGanador(String pokemonGanador) {
        this.pokemonGanador = pokemonGanador;
    }

    public void setPokemon1(String pokemon1) {
        this.pokemon1 = pokemon1;
    }

    public void setPokemon2(String pokemon2) {
        this.pokemon2 = pokemon2;
    }

    public void setBitacoraCombates(String bitacoraCombates) {
        this.bitacoraCombates = bitacoraCombates;
    }
    
    //ToString

    @Override
    public String toString() {
        return "batallasPokemon{" + "numeroBatallas=" + numeroBatallas + ", pokemonGanador=" + pokemonGanador + ", pokemon1=" + pokemon1 + ", pokemon2=" + pokemon2 + '}';
    }
    
    
}
