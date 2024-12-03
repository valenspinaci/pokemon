package ar.edu.davinci.models;

import ar.edu.davinci.exceptions.AtaqueException;
import ar.edu.davinci.exceptions.VidaException;
import ar.edu.davinci.models.tipos.Tipo;

public class Pokemon {
    private int id;
    private Tipo tipo;
    private String especie;
    private Float poder;
    private Float energia;
    private Float danio;
    private Float vida;
    private int idEntrenador;
    private static BatallaLogger batallaLogger;

    public Pokemon() {};

    public Pokemon(Tipo tipo, String especie, Float poder, Float energia, Float danio, int idEntrenador) {
        this.tipo = tipo;
        this.especie = especie;
        this.poder = poder;
        this.energia = energia;
        this.danio = danio;
        this.idEntrenador = idEntrenador;
        this.vida = 100F;
    }

    public Pokemon(int id, Tipo tipo, String especie, Float poder, Float energia, Float danio, int idEntrenador) {
        this.id = id;
        this.tipo = tipo;
        this.especie = especie;
        this.poder = poder;
        this.energia = energia;
        this.danio = danio;
        this.idEntrenador = idEntrenador;
        this.vida = 100F;
    }

    //Getters & Setters
    public int getId() {
        return id;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public String getEspecie() {
        return especie;
    }

    public Float getPoder() {
        return poder;
    }

    public Float getEnergia() {
        return energia;
    }

    public Float getVida() {
        return vida;
    }

    public Float getDanio() {
        return danio;
    }

    public int getIdEntrenador() {
        return idEntrenador;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public void setPoder(Float poder) {
        this.poder = poder;
    }

    public void setEnergia(Float energia) {
        this.energia = energia;
    }

    public void setVida(Float vida) {
        this.vida = vida;
    }

    public void setDanio(Float danio) {
        this.danio = danio;
    }

    public void setIdEntrenador(int idEntrenador) {
        this.idEntrenador = idEntrenador;
    }

    public void setBatallaLogger(BatallaLogger batallaLogger) {
        this.batallaLogger = batallaLogger;
    }

    public void atacar(Pokemon otroPokemon) throws AtaqueException, VidaException {
        if (this.getVida() <= 0F) {
            throw new AtaqueException(this.getEspecie() + " no puede atacar porque fue derrotado.");
        }

        batallaLogger.mostrarMensaje(this.getEspecie() + " ataca a " + otroPokemon.getEspecie());
        System.out.println(this.getEspecie() + " ataca a " + otroPokemon.getEspecie());
        float danio = this.tipo.danio(this, otroPokemon);
        otroPokemon.restarVida(danio);
        this.energia -= 10F;
        batallaLogger.mostrarMensaje(otroPokemon.getEspecie() + " queda con " + otroPokemon.getVida() + " de vida.");
        System.out.println(otroPokemon.getEspecie() + " queda con " + otroPokemon.getVida() + " de vida.");

        if (otroPokemon.getVida() <= 0F) {
            batallaLogger.mostrarMensaje(this.getEspecie() + " gana la pelea contra " + otroPokemon.getEspecie() + "!");
            System.out.println(this.getEspecie() + " gana la pelea contra " + otroPokemon.getEspecie() + "!");
            return;
        }

        batallaLogger.mostrarMensaje(otroPokemon.getEspecie() + " contraataca!");
        System.out.println(otroPokemon.getEspecie() + " contraataca!");
        float danioContraataque = otroPokemon.tipo.danio(otroPokemon, this);
        this.restarVida(danioContraataque);
        otroPokemon.energia -= 10F;
        batallaLogger.mostrarMensaje(this.getEspecie() + " queda con " + this.getVida() + " de vida.");
        System.out.println(this.getEspecie() + " queda con " + this.getVida() + " de vida.");

        if (this.getVida() <= 0F) {
            batallaLogger.mostrarMensaje(otroPokemon.getEspecie() + " gana la pelea contra " + this.getEspecie() + "!");
            System.out.println(otroPokemon.getEspecie() + " gana la pelea contra " + this.getEspecie() + "!");
            throw new AtaqueException(this.getEspecie() + " ha sido derrotado durante el contraataque.");
        }
    }


    public void restarVida(Float cantidad) throws VidaException {
        if(cantidad <= 0F){
            throw new VidaException("La cantidad de vida a restar debe ser mayor a cero");
        }
        this.vida -= cantidad;
    }

    public void aumentarVida(Float cantidad) throws VidaException {
        if(cantidad <= 0F){
            throw new VidaException("La cantidad de vida a aumentar debe ser mayor a cero");
        }
        this.vida += cantidad;
    }

    public Float serAtacadoPorAgua(Pokemon atacante) {
        return this.tipo.serAtacadoPorAgua(atacante);
    }

    public Float serAtacadoPorElectrico(Pokemon atacante) {
        return this.tipo.serAtacadoPorElectrico(atacante);
    }

    public Float serAtacadoPorFuego(Pokemon atacante) {
        return this.tipo.serAtacadoPorFuego(atacante);
    }

    public Float serAtacadoPorPlanta(Pokemon atacante) {
        return this.tipo.serAtacadoPorPlanta(atacante);
    }

    public Float serAtacadoPorPiedra(Pokemon atacante) {
        return this.tipo.serAtacadoPorPiedra(atacante);
    }
}
