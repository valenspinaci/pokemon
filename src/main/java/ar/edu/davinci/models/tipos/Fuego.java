package ar.edu.davinci.models.tipos;

import ar.edu.davinci.models.Pokemon;

public class Fuego extends Tipo {
    public Fuego() {
        super("Fuego");
    }

    @Override
    public int danio(Pokemon atacante, Pokemon defensor) {
        return defensor.serAtacadoPorFuego(atacante);
    }

    @Override
    public int serAtacadoPorAgua(Pokemon atacante) {
        return (int) (atacante.getPoder()*1.25);
    }
}
