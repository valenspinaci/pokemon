package ar.edu.davinci.models.tipos;

import ar.edu.davinci.models.Pokemon;
import ar.edu.davinci.models.Tipo;

public class Electrico extends Tipo {
    public Electrico() {
        super("Electrico");
    }

    @Override
    public int danio(Pokemon atacante, Pokemon defensor) {
        return defensor.serAtacadoPorElectrico(atacante);
    }
}
