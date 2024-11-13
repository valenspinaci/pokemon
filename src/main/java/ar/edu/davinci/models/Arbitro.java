package ar.edu.davinci.models;

public class Arbitro {

    public Entrenador verificarGanador(Entrenador yo, Entrenador rival, int miIndice){
        if(miIndice < yo.cantPokemons()){
            System.out.println(yo.getNombre() + " gana la pelea!");
            return yo;
        }else{
            System.out.println(rival.getNombre() + " gana la pelea!");
            return rival;
        }
    }
}
