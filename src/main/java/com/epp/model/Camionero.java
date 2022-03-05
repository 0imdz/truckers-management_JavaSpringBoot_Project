package com.epp.model;

import javax.validation.constraints.*;

public class Camionero {

    int id;

    @NotEmpty(message="El nombre no puede estar vacío")
    @Size(max=20, message="El nombre no puede exceder los 20 carácteres")
    String nombre;

    @NotEmpty(message="Los apellidos no pueden estar vacíos")
    @Size(max=40, message="Los apellidos no pueden exceder los 40 carácteres")
    String apellidos;

    @NotEmpty(message="El correo no puede estar vacío")
	@Pattern(regexp="^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$",message="Correo electrónico inválido")
    String correo;

    @NotEmpty(message="El número de móvil no puede estar vacío")
	@Pattern (regexp = "\\s?(?:6[0-9]|7[1-9])[0-9]\\s?[0-9]{3}\\s?[0-9]{3}$",message="Introduzca su número de móvil sin espacios, por favor")
    String telefono;

    @NotEmpty(message="La fecha de nacimiento no puede estar vacía")
    @Pattern(regexp="^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$",message="La fecha ha de tener el formato YYYY-MM-DD")

    String nacimiento;

    @NotEmpty(message="El género no puede estar vacío")
    String genero;

    @NotEmpty(message="Las mercancías a transportar no pueden estar vacías")
    String transporteMercancias;

    @Size(max=250, message="El comentario no puede exceder los 250 carácteres")
    String comentarios;

    @NotEmpty(message="El estado actual de contratación no puede estar vacío")
    String contratadoActualmente;

    public Camionero() {
    }

    public Camionero(int id, String nombre, String apellidos, String correo, String telefono, String nacimiento, String genero, String transporteMercancias, String comentarios, String contratadoActualmente) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
        this.telefono = telefono;
        this.nacimiento = nacimiento;
        this.genero = genero;
        this.transporteMercancias = transporteMercancias;
        this.comentarios = comentarios;
        this.contratadoActualmente = contratadoActualmente;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(String nacimiento) {
        this.nacimiento = nacimiento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTransporteMercancias() {
        return transporteMercancias;
    }

    public void setTransporteMercancias(String transporteMercancias) {
        this.transporteMercancias = transporteMercancias;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public String getContratadoActualmente() {
        return contratadoActualmente;
    }

    public void setContratadoActualmente(String contratadoActualmente) {
        this.contratadoActualmente = contratadoActualmente;
    }
}
