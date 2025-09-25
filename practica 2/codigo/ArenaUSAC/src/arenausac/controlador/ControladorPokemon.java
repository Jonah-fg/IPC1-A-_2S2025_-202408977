
package arenausac.controlador;
import arenausac.modelo.personajesPokemon;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ControladorPokemon {
    private personajesPokemon[] pokemones;
    private int contadorPokemones;
    private int siguienteID;

    public ControladorPokemon(int capacidad) {
        this.pokemones = new personajesPokemon[capacidad];
        this.contadorPokemones = 0;
        this.siguienteID = 1;
    }
    
    public String agregarPokemon(String nombre, String arma, int HP, int ataque, int velocidad, int agilidad, int defensa){
        
        // Validación 1: Nombre duplicado
        for (int i = 0; i < contadorPokemones; i++) {
            if (pokemones[i].getNombrePokemon().equalsIgnoreCase(nombre)) {
                return "Error: El nombre '" + nombre + "' ya existe";
            }
        }

        if (HP < 100 || HP > 500) {
            return "El HP del Pokemon debe estar entre 100 y 500";
        }
        if (ataque < 10 || ataque > 100) {
            return "El ataque del pokemon debe estar entre 10 y 100";
        }
        if (velocidad < 1 || velocidad > 10) {
            return " La velocidad del pokemn debe estar entre 1 y 10";
        }
        if (agilidad < 1 || agilidad > 10) {
            return " la agilidad del pokemon debe estar entre 1 y 10";
        }
        if (defensa < 1 || defensa > 50) {
            return "La defesa debe estar entre 1 y 50";
        }

        String[] armasValido = {"fuego", "agua", "planta", "eléctrico", "roca", "normal"};
        boolean tipoArmaValido = false;
        for (int i = 0; i < armasValido.length; i++) {
            if (armasValido[i].equalsIgnoreCase(arma)) {
                tipoArmaValido = true;
                break;
            }
        }
        if (!tipoArmaValido) {
            return "Error: Tipo '" + arma + "' no es válido. Use las siguintes opciones de armas: fuego, agua, planta, eléctrico, roca o normal";
        }

        if (contadorPokemones >= pokemones.length) {
            return "Error: No hay espacio para más Pokémons";
        }

        personajesPokemon nuevoPokemon = new personajesPokemon(siguienteID++, nombre, arma.toLowerCase(), HP, ataque, velocidad, agilidad, defensa);
        pokemones[contadorPokemones] = nuevoPokemon;
        contadorPokemones++;
        
        return "Éxito: Pokémon '" + nombre + "' agregado correctamente";
    }

    public void modificarPokemon(int ID, String arma, int HP, int ataque, int velocidad, int agilidad, int defensa) {
        personajesPokemon pokemon = buscarPokemonID(ID);
        if (pokemon == null) return;

        if(HP < 100 || HP > 500){
            return;
        if(ataque < 10 || ataque > 100){
            return;
        }
        if (velocidad < 1 || velocidad > 10){
            return;
        }
        if (agilidad < 1 || agilidad > 10){
            return;
        }
        if(defensa < 1 || defensa > 50){
            return;
        }

        String[] armasValido = {"fuego", "agua", "planta", "eléctrico", "roca", "normal"};
        boolean tipoArmaValido = false;
        for (int i = 0; i < armasValido.length; i++) {
            if (armasValido[i].equalsIgnoreCase(arma)) {
                tipoArmaValido = true;
                break;
            }
        }
        if (!tipoArmaValido) return;
        pokemon.getTipoArma(arma);
        
        pokemon.setPuntosVida(HP);
        
        pokemon.setNivelAtaque(ataque);
        
        pokemon.setVelocidad(velocidad);
        
        pokemon.setAgilidad(agilidad);
        
        pokemon.setDefensa(defensa);
    }

    public void eliminarPokemon(int ID){
        for (int i = 0; i <contadorPokemones; i++) {
            if (pokemones[i].getID()== ID) {
                for (int j = i; j < contadorPokemones - 1; j++) {
                    pokemones[j] = pokemones[j + 1];
                }
                contadorPokemones--;
                pokemones[contadorPokemones] = null;
                return;
            }
        }
    }

    public personajesPokemon buscarPokemonID(int ID) {
        for (int i = 0; i < contadorPokemones; i++) {
            if (pokemones[i].getID() == ID) {
                return pokemones[i];
            }
        }
        return null;
    }

    public personajesPokemon buscarPokemonNombre(String nombre) {
        for (int i = 0; i < contadorPokemones; i++) {
            if (pokemones[i].getNombrePokemon().equalsIgnoreCase(nombre)) {
                return pokemones[i];
            }
        }
        return null;
    }

    public personajesPokemon[] getPokemones() {
        personajesPokemon[] activos = new personajesPokemon[contadorPokemones];
        for (int i = 0; i < contadorPokemones; i++) {
            activos[i] = pokemones[i];
        }
        return activos;
    }     
}
