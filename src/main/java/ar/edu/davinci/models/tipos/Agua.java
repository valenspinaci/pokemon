package ar.edu.davinci.models.tipos;

import ar.edu.davinci.models.Pokemon;
import ar.edu.davinci.models.Tipo;

public class Agua extends Tipo {
    public Agua() {
        super("Agua");
    }

    @Override
    public int danio(Pokemon atacante, Pokemon defensor) {
        return defensor.serAtacadoPorAgua(atacante);
    };

    @Override
    public int serAtacadoPorElectrico(Pokemon atacante){
        atacante.setDanio((int) (atacante.getDanio()*0.95));
        return (int) (atacante.getPoder()*1.5);
    }
}
