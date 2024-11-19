package ar.edu.davinci.models.tipos;

import ar.edu.davinci.models.Pokemon;

public abstract class Tipo {
    private String nombre;

    public Tipo(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public static Tipo crearTipoPorNombre(String tipoNombre) {
        switch (tipoNombre) {
            case "Agua": return new Agua();
            case "Fuego": return new Fuego();
            case "Electrico": return new Electrico();
            //Excepcion
            default: return null;
        }
    }

    //Daño por defecto
    public abstract Float danio(Pokemon atacante, Pokemon defensor);

        public Float serAtacadoPorAgua(Pokemon atacante){
        return atacante.getDanio();
    };

    public Float serAtacadoPorFuego(Pokemon atacante){
        return atacante.getDanio();
    };

    public Float serAtacadoPorElectrico(Pokemon atacante){
        return atacante.getDanio();
    };

    public Float serAtacadoPorPlanta(Pokemon atacante){
        return atacante.getDanio();
    };

    public Float serAtacadoPorPiedra(Pokemon atacante){
        return atacante.getDanio();
    };
}
