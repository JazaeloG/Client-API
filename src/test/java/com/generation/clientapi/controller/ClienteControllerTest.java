package com.generation.clientapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.generation.clientapi.dtos.ClienteCreateDTO;
import com.generation.clientapi.dtos.ClienteUpdateDTO;
import com.generation.clientapi.model.Cliente;
import com.generation.clientapi.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClienteController.class)
class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    @Autowired
    private ObjectMapper objectMapper;

    private Cliente cliente;

    @BeforeEach
    void setup() {
        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNombre("Luis");
        cliente.setEmail("luis@test.com");
        cliente.setTelefono("123456789");
    }

    @Test
    void obtenerTodos_debeRetornarListaDeClientes() throws Exception {
        when(clienteService.obtenerClientes()).thenReturn(List.of(cliente));

        mockMvc.perform(get("/api/clientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(1)))
                .andExpect(jsonPath("$[0].nombre", is("Luis")));
    }

    @Test
    void obtenerPorId_debeRetornarCliente() throws Exception {
        when(clienteService.obtenerClientePorId(1L)).thenReturn(cliente);

        mockMvc.perform(get("/api/clientes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre", is("Luis")));
    }

    @Test
    void crearCliente_debeRetornarClienteCreado() throws Exception {
        ClienteCreateDTO dto = new ClienteCreateDTO();
        dto.setNombre("Nuevo");
        dto.setEmail("nuevo@test.com");
        dto.setTelefono("2731000238");

        when(clienteService.crearCliente(Mockito.any())).thenReturn(cliente);

        mockMvc.perform(post("/api/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre", is("Luis")));
    }

    @Test
    void actualizarCliente_debeRetornarClienteActualizado() throws Exception {
        ClienteUpdateDTO dto = new ClienteUpdateDTO();
        dto.setNombre("Modificado");

        cliente.setNombre("Modificado");
        when(clienteService.actualizarCliente(Mockito.eq(1L), Mockito.any())).thenReturn(cliente);

        mockMvc.perform(patch("/api/clientes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre", is("Modificado")));
    }

    @Test
    void eliminarCliente_debeRetornarNoContent() throws Exception {
        mockMvc.perform(delete("/api/clientes/1"))
                .andExpect(status().isNoContent());
    }
}
