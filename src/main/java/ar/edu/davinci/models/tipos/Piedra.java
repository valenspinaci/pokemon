package ar.edu.davinci.models.tipos;

import ar.edu.davinci.models.Pokemon;

public class Piedra extends Tipo {
    public Piedra() {
        super("Piedra");
    }

    @Override
    public int danio(Pokemon atacante, Pokemon defensor) {
        return defensor.serAtacadoPorPiedra(atacante);
    }

    @Override
    public int serAtacadoPorPlanta(Pokemon atacante) {
        return 0;
    }

}
