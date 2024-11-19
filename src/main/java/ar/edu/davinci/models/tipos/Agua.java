package ar.edu.davinci.models.tipos;

import ar.edu.davinci.models.Pokemon;

public class Agua extends Tipo {
    public Agua() {
        super("Agua");
    }

    @Override
    public Float danio(Pokemon atacante, Pokemon defensor) {
        return defensor.serAtacadoPorAgua(atacante);
    };

    @Override
    public Float serAtacadoPorElectrico(Pokemon atacante){
        atacante.setDanio(atacante.getDanio()*0.95F);
        return atacante.getPoder()*1.5F;
    }
}
