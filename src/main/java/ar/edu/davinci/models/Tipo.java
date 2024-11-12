package ar.edu.davinci.models;

import ar.edu.davinci.models.tipos.Agua;
import ar.edu.davinci.models.tipos.Electrico;
import ar.edu.davinci.models.tipos.Fuego;

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
    public abstract int danio(Pokemon atacante, Pokemon defensor);

    public int serAtacadoPorAgua(Pokemon atacante){
        return atacante.getDanio();
    };

    public int serAtacadoPorFuego(Pokemon atacante){
        return atacante.getDanio();
    };

    public int serAtacadoPorElectrico(Pokemon atacante){
        return atacante.getDanio();
    };

    public int serAtacadoPorPlanta(Pokemon atacante){
        return atacante.getDanio();
    };

    public int serAtacadoPorPiedra(Pokemon atacante){
        return atacante.getDanio();
    };
}
