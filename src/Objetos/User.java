package Objetos;

public class User {
    private String nombre;
    private String tipo;
    private String logan;
    private int valoraciones;
    private String busca;

    // Constructor, getters y setters
    public User(String nombre, String tipo, String logan, int valoraciones, String busca) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.logan = logan;
        this.valoraciones = valoraciones;
        this.busca = busca;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getLogan() {
        return logan;
    }

    public void setLogan(String logan) {
        this.logan = logan;
    }

    public int getValoraciones() {
        return valoraciones;
    }

    public void setValoraciones(int valoraciones) {
        this.valoraciones = valoraciones;
    }

    public String getBusca() {
        return busca;
    }

    public void setBusca(String busca) {
        this.busca = busca;
    }
}