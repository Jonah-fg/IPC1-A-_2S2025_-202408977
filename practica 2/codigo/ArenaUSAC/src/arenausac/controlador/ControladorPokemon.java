
package arenausac.controlador;
import arenausac.modelo.personajesPokemon;
import java.io.BufferedReader;
import java.io.FileReader;
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

        String[] armasValido = {"eléctrico", "agua", "planta", "fuego", "roca", "normal"};
        boolean tipoArmaValido = false;
        for (int i = 0; i < armasValido.length; i++) {
            if (armasValido[i].equalsIgnoreCase(arma)) {
                tipoArmaValido = true;
                break;
            }
        }
        if (!tipoArmaValido) {
            return "Tipo '" + arma + "' no es válido. Use las siintes opciones de aras: fuego, agua, planta, eléctrico, roca o normal";
        }

        if (contadorPokemones >= pokemones.length) {
            return "No hay espacio para más Pokémons";
        }

        personajesPokemon nuevoPokemon = new personajesPokemon(siguienteID++, nombre, arma.toLowerCase(), HP, ataque, velocidad, agilidad, defensa);
        pokemones[contadorPokemones] = nuevoPokemon;
        contadorPokemones++;
        
        return "Éxito: Pokémon '" + nombre + "' agregado corrctamente";
    }

    public void modificarPokemon(int ID, String arma, int HP, int ataque, int velocidad, int agilidad, int defensa) {
        personajesPokemon pokemon = buscarPokemonID(ID);
        if (pokemon == null) {
            return;
        }

        if(HP < 100 || HP > 500){
            return;
        }       
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

        String[] armasValido = {"eléctrico", "agua", "planta","fuego","normal", "roca"};
        boolean tipoArmaValido = false;
        for (int i = 0; i < armasValido.length; i++) {
            if (armasValido[i].equalsIgnoreCase(arma)) {
                tipoArmaValido = true;
                break;
            }
        }
        if (!tipoArmaValido){
            return;
        }
        
        pokemon.setTipoArma(arma);
        
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
    
    public void guardarEstadoPokemones(String archivoxtx) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(archivoxtx))) {
            writer.println("pokemones");
            for (int i = 0; i < contadorPokemones; i++) {
                personajesPokemon poke = pokemones[i];
                writer.println(poke.getID() + "," + poke.getNombrePokemon() + "," + poke.getTipoArma() + "," + poke.getPuntosVida() + "," + poke.getNivelAtaque() + "," +   poke.getVelocidad() + "," + poke.getAgilidad() + "," + poke.getDefensa() + "," +poke.getBatallasGanadas() + "," + poke.getBatallasPerdidas());
            }
        }
        catch (IOException e) {
        }
    }
        public void cargarEstadoPokemones(String archivo) {
        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            siguienteID = 1;
            String linea;
            contadorPokemones = 0;
            boolean ubicacionPokemones = false;
            
            while ((linea = lector.readLine()) != null) {
                if (linea.equals("pokemones")) {
                    ubicacionPokemones = true;
                    
                } else if (linea.equals("batallas")) {
                    break;
                }
                
                if (ubicacionPokemones && !linea.trim().isEmpty()) {
                    String[] datosPokemones = linea.split(";");
                    
                    if (datosPokemones.length >= 8) {
                        int ID = Integer.parseInt(datosPokemones[0]);
                         String nombrePokemon = datosPokemones[1];
                        String arma = datosPokemones[2];
                        int HP = Integer.parseInt(datosPokemones[3]);
                        int ataque = Integer.parseInt(datosPokemones[4]);
                        int velocidad = Integer.parseInt(datosPokemones[5]);
                         int agilidad = Integer.parseInt(datosPokemones[6]);
                        int defensa = Integer.parseInt(datosPokemones[7]);
                         personajesPokemon poke = new personajesPokemon(ID, nombrePokemon, arma, HP, ataque, velocidad, agilidad, defensa);
                        
                        if (datosPokemones.length >= 10) {
                            int ganadas = Integer.parseInt(datosPokemones[8]);
                             int perdidas = Integer.parseInt(datosPokemones[9]);
                            
                            for (int j = 0; j < ganadas; j++){
                                poke.incrementarBatallasGanadas();
                            }
                            for (int j = 0; j < perdidas; j++){
                                poke.incrementarBatallasPerdidas();
                            }
                        }
                        if (contadorPokemones < pokemones.length) {
                            pokemones[contadorPokemones] = poke;
                            contadorPokemones++;
                            
                            if (ID >= siguienteID) {
                                siguienteID = ID + 1;
                            }
                        }
                    }
                }
            }
        }
        catch (IOException e) {
        }
    }
}
