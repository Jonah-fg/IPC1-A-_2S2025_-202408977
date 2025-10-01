
package arenausac.controlador;

import arenausac.modelo.batallasPokemon;
import arenausac.modelo.personajesPokemon;
import javax.swing.JTextArea;

public class ControladorBatallasPokemon {
    private int contadorBatallas;
    private batallasPokemon[] historialBatallas;
    private int numeroBatallas;
    private int batallasMax;
    
    public ControladorBatallasPokemon(int capacidad){
        this.contadorBatallas = 0;
        this.historialBatallas = new batallasPokemon[capacidad];
        this.numeroBatallas = 1;
        this.batallasMax = capacidad;
    }

    public void simulacionPeleasPokemon(personajesPokemon pokemon1, personajesPokemon pokemon2, JTextArea Texto){
        if(pokemon1==null || pokemon2 == null){
            if(Texto != null){
                Texto.append("Debes seleccionar 2 Pokemones validos");
            }
            return;
        }
        if(pokemon1.getPuntosVida()<= 0 || pokemon2.getPuntosVida() <= 0){
            if(Texto != null){
                Texto.append("Ambos Pokemones deben estar vivos para pelear");
            }
            return;
        }
        if(Texto != null){
            Texto.append("BATALLA POKEMOOOOOOOOOOOOOOOONNsdhj9esdhfcusfhcsd98u");
            Texto.append(pokemon1.getNombrePokemon()+"vs"+pokemon2.getNombrePokemon());
        }
             hilosPokemones primerHilo=new hilosPokemones(pokemon1, pokemon2, Texto);
            hilosPokemones segundoHilo=new hilosPokemones(pokemon1, pokemon2, Texto);
            Thread hilo1= new Thread(primerHilo);
            Thread hilo2=new Thread(segundoHilo);
            hilo1.start();
            hilo2.start();
    }        
    
    public void finalizarBatallaPokemones(personajesPokemon pokemon1, personajesPokemon pokemon2, JTextArea Texto){
        String ganador;
        
        if(pokemon1.getPuntosVida() > 0 && pokemon2.getPuntosVida() <= 0){
            ganador = pokemon1.getNombrePokemon();
            pokemon1.incrementarBatallasGanadas();
            pokemon2.incrementarBatallasPerdidas();
        }
        else if(pokemon2.getPuntosVida() > 0 && pokemon1.getPuntosVida() <= 0){
            ganador = pokemon2.getNombrePokemon();
            pokemon2.incrementarBatallasGanadas();
            pokemon1.incrementarBatallasPerdidas();
        } 
        else {
            ganador = "Hubo un empateeeededeffkeif";
        }
        if(Texto != null){
            Texto.append("El pokemon ganador es: " + ganador + "\n");
        }
        if(contadorBatallas<batallasMax){
            String bitacora;
            if(Texto != null) {
            bitacora = Texto.getText();  
            } 
            else {
            bitacora = "Bitacora inexistente";      
            }
            batallasPokemon nuevaBatallaPokemon = new batallasPokemon(numeroBatallas, ganador, pokemon1.getNombrePokemon(), pokemon2.getNombrePokemon(), bitacora);
            historialBatallas[contadorBatallas] = nuevaBatallaPokemon;
            contadorBatallas++;
            numeroBatallas++;
    }
            
    }
}  
