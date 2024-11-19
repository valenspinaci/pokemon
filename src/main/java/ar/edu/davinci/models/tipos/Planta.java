package ar.edu.davinci.models.tipos;

import ar.edu.davinci.models.Pokemon;

public class Planta extends Tipo {
    public Planta() {
        super("Planta");
    }

    @Override
    public Float danio(Pokemon atacante, Pokemon defensor) {
        return defensor.serAtacadoPorPlanta(atacante);
    }

    @Override
    public Float serAtacadoPorFuego(Pokemon atacante) {
        return atacante.getPoder()*1.2F;
    }
}
