package com.example.ejercicio3_1.modelo;

public class Transacciones {
    public static final String NameDatabase = "DB";
    public static final String tablaEmpleado = "empleado";
    public static final String id = "id";
    public static final String nombres = "nombres";
    public static final String apellidos = "apellidos";
    public static final String anios = "anios";
    public static final String direccion = "direccion";
    public static final String puesto = "puesto";
    public static final String createTablePersonas = "CREATE TABLE " + tablaEmpleado +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nombres TEXT, apellidos TEXT, anios INTEGER, direccion TEXT, puesto TEXT)";


    public static final String dropTablePersonas = "DROP TABLE IF EXIST" + tablaEmpleado;
}
