package ar.edu.davinci.models.tipos;

import ar.edu.davinci.models.Pokemon;

public class Fuego extends Tipo {
    public Fuego() {
        super("Fuego");
    }

    @Override
    public Float danio(Pokemon atacante, Pokemon defensor) {
        return defensor.serAtacadoPorFuego(atacante);
    }

    @Override
    public Float serAtacadoPorAgua(Pokemon atacante) {
        return atacante.getPoder()*1.25F;
    }
}
