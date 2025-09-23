
package arenausac.controlador;
import arenausac.modelo.personajesPokemon;

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
            return "Error: HP debe estar entre 100 y 500";
        }
        if (ataque < 10 || ataque > 100) {
            return "Error: Ataque debe estar entre 10 y 100";
        }
        if (velocidad < 1 || velocidad > 10) {
            return "Error: Velocidad debe estar entre 1 y 10";
        }
        if (agilidad < 1 || agilidad > 10) {
            return "Error: Agilidad debe estar entre 1 y 10";
        }
        if (defensa < 1 || defensa > 50) {
            return "Error: Defensa debe estar entre 1 y 50";
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
      
      
}
