
package arenausac.controlador;
import arenausac.modelo.batallasPokemon;
import arenausac.modelo.personajesPokemon;
import javax.swing.JTextArea;

public class hilosPokemones implements Runnable {
    private personajesPokemon oponente;
    private personajesPokemon peleador;
    private JTextArea bitacoraBatalla;
    private boolean batallaActiva;

    public hilosPokemones(personajesPokemon oponente, personajesPokemon peleador, JTextArea bitacoraBatalla) {
        this.oponente = oponente;
        this.peleador = peleador;
        this.batallaActiva=true;
        this.bitacoraBatalla = bitacoraBatalla;
    }
    @Override
    public void run() {
       while(batallaActiva && oponente.getPuntosVida()>0 && peleador.getPuntosVida()>0){
           try{
               Thread.sleep(1000 / peleador.getVelocidad());
               if(!batallaActiva || oponente.getPuntosVida()<=0){
                   break;   
               }
           }
           catch(InterruptedException e){
               break;
           }
           ataquePokemon();
       }
    }
    public void bitacoraBatallasPokemones(String mensaje){
        if(bitacoraBatalla != null){
           bitacoraBatalla.append(mensaje + "\n");
        }
    }
    
     public void detenerBatallaPokemones(){
     this.batallaActiva = false;
     }
       
    public void ataquePokemon(){
        double problabilidadEsquive=oponente.getAgilidad()*0.1;
        boolean pokemonEsquivo = Math.random() < problabilidadEsquive;
        
        if(pokemonEsquivo){
            bitacoraBatallasPokemones(peleador.getNombrePokemon()+"ataca al pokemon: "+oponente.getNombrePokemon()+"-Esquive fallido jajaja");
       }
        else{
            int dañoPokemon = Math.max(1, peleador.getNivelAtaque() - (oponente.getDefensa() / 2));
            oponente.setPuntosVida(oponente.getPuntosVida() - dañoPokemon);
            bitacoraBatallasPokemones(peleador.getNombrePokemon() + " ataca al popkemon: " + oponente.getNombrePokemon() + " - " + dañoPokemon + " de daño");
            
            if(oponente.getPuntosVida()<=0){
                bitacoraBatallasPokemones("El pokemon: "+oponente.getNombrePokemon() + "ha sido derrtado");
                detenerBatallaPokemones();
                
            }
        }
       
    }
    
    
}
