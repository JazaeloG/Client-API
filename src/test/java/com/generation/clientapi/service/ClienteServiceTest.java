package com.generation.clientapi.service;

import com.generation.clientapi.dtos.ClienteCreateDTO;
import com.generation.clientapi.model.Cliente;
import com.generation.clientapi.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    public ClienteServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void obtenerClientes_debeRetornarListaClientes() {
        Cliente cliente = new Cliente();
        cliente.setNombre("Juan");
        cliente.setEmail("juan@example.com");
        cliente.setTelefono("2731000238");

        when(clienteRepository.findAll()).thenReturn(List.of(cliente));

        List<Cliente> resultado = clienteService.obtenerClientes();

        assertEquals(1, resultado.size());
        assertEquals("Juan", resultado.get(0).getNombre());
    }

    @Test
    void obtenerClientePorId_existente() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNombre("Ana");

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        Cliente resultado = clienteService.obtenerClientePorId(1L);

        assertEquals("Ana", resultado.getNombre());
    }

    @Test
    void obtenerClientePorId_noExistente_lanzaExcepcion() {
        when(clienteRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> clienteService.obtenerClientePorId(99L));
    }

    @Test
    void crearCliente_debeGuardarYRetornarCliente() {
        ClienteCreateDTO dto = new ClienteCreateDTO();
        dto.setNombre("Carlos");
        dto.setEmail("carlos@example.com");
        dto.setTelefono("2721000339");

        Cliente cliente = new Cliente();
        cliente.setNombre(dto.getNombre());
        cliente.setEmail(dto.getEmail());
        cliente.setTelefono(dto.getTelefono());

        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        Cliente creado = clienteService.crearCliente(dto);

        assertEquals("Carlos", creado.getNombre());
    }

    @Test
    void eliminarCliente_existente() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        doNothing().when(clienteRepository).delete(cliente);

        assertDoesNotThrow(() -> clienteService.eliminarCliente(1L));
    }
}