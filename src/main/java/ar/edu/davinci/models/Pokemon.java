package ar.edu.davinci.models;

public class Pokemon {
    private int id;
    private String tipo;
    private String especie;
    private int poder;
    private int energia;
    private int vida;

    public Pokemon(String tipo, String especie, int poder, int energia) {
        this.tipo = tipo;
        this.especie = especie;
        this.poder = poder;
        this.energia = energia;
        this.vida = 100;
    }

    //Getters & Setters
    public int getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public String getEspecie() {
        return especie;
    }

    public int getPoder() {
        return poder;
    }

    public int getEnergia() {
        return energia;
    }

    public int getVida() {
        return vida;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public void setPoder(int poder) {
        this.poder = poder;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public void atacar(Pokemon otroPokemon){
        if (this.energia >= 10) {
            otroPokemon.restarVida(this.poder);
            this.energia -= 10;
        } else {
            System.out.println(this.especie + " no tiene suficiente energía para atacar.");
        }
    }

    public void restarVida(int cantidad){
        if (cantidad > 0) {
            this.vida -= cantidad;
            if (this.vida < 0) {
                this.vida = 0;
            }
        } else {
            System.out.println("La cantidad a restar debe ser mayor que 0.");
        }
    }

    public void aumentarVida(int cantidad){
        if (cantidad > 0) {
            this.vida += cantidad;
            if (this.vida > 100) {
                this.vida = 100;
            }
        } else {
            System.out.println("La cantidad a aumentar debe ser mayor que 0.");
        }
    }
}
