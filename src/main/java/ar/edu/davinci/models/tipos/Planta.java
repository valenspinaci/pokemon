package ar.edu.davinci.models.tipos;

import ar.edu.davinci.models.Pokemon;

public class Planta extends Tipo {
    public Planta() {
        super("Planta");
    }

    @Override
    public int danio(Pokemon atacante, Pokemon defensor) {
        return defensor.serAtacadoPorPlanta(atacante);
    }

    @Override
    public int serAtacadoPorFuego(Pokemon atacante) {
        return (int) (atacante.getPoder()*1.2);
    }
}
