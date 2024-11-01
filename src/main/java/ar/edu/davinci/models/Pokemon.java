package ar.edu.davinci.models;

public class Pokemon {
    private Tipo tipo;
    private String especie;
    private int poder;
    private int energia;
    private int vida;

    public Pokemon(Tipo tipo, String especie, int poder) {
        this.tipo = tipo;
        this.especie = especie;
        this.poder = poder;
        this.energia = energia;
        this.vida = 100;
    }

    public void atacar(Pokemon otroPokemon){
        /////////////////////////////////////
    }

    public void restarVida(float cantidad){
        ///////////////////////////////////
    }

    public void aumentarVida(float cantidad){
        /////////////////////////////////////
    }
}
