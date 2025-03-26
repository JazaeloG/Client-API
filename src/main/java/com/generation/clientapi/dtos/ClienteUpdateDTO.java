package com.generation.clientapi.dtos;

import jakarta.validation.constraints.Size;

public class ClienteUpdateDTO {
    private String nombre;

    @Size(min = 10, max = 10, message = "El teléfono debe tener 10 caracteres")
    private String telefono;

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
