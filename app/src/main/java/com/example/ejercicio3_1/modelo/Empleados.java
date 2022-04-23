package com.example.ejercicio3_1.modelo;

public class Empleados {
    private int id;
    private String nombre;
    private String apellidos;
    private String anios;
    private String direccion;
    private String puesto;

    public Empleados() {}

    public Empleados(String nombre, String apellidos, String anios, String direccion, String puesto) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.anios = anios;
        this.direccion = direccion;
        this.puesto = puesto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEdad() {
        return anios;
    }

    public void setEdad(String anios) {
        this.anios = anios;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

}
