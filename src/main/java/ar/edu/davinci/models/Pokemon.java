package ar.edu.davinci.models;

import ar.edu.davinci.exceptions.AtaqueException;
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

    public void atacar(Pokemon otroPokemon) throws AtaqueException {
        if(this.getVida()<=0){
            System.out.println(otroPokemon.getEspecie() + " ha sido derrotado!");
            throw new AtaqueException("El pokemon no tiene vida para atacar");
        }else{
            otroPokemon.restarVida(this.tipo.danio(this, otroPokemon));
            this.energia -= 10;
            System.out.println(this.getEspecie() + " ataca! " + otroPokemon.getEspecie() + " queda con " + this.getVida() + " vida.");
        }

        if(otroPokemon.getVida()<=0){
            System.out.println(this.getEspecie() + " ha sido derrotado!");
            throw new AtaqueException("El pokemon no tiene vida para atacar");
        }else{
            this.restarVida(otroPokemon.tipo.danio(otroPokemon, this));
            this.energia -= 10;
            System.out.println(otroPokemon.getEspecie() + " ataca! " + this.getEspecie() + " queda con " + otroPokemon.getVida() + " vida.");
        }

    }

    public void restarVida(Float cantidad){
        if (cantidad > 0) {
            this.vida -= cantidad;
            if (this.vida < 0) {
                this.vida = 0F;
            }
        } else {
            System.out.println("La cantidad a restar debe ser mayor que 0.");
        }
    }

    public void aumentarVida(Float cantidad){
        if (cantidad > 0) {
            this.vida += cantidad;
            if (this.vida > 100) {
                this.vida = 100F;
            }
        } else {
            System.out.println("La cantidad a aumentar debe ser mayor que 0");
        }
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
