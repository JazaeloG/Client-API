package com.generation.clientapi.service;

import com.generation.clientapi.dtos.ClienteCreateDTO;
import com.generation.clientapi.dtos.ClienteUpdateDTO;
import com.generation.clientapi.model.Cliente;
import com.generation.clientapi.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> obtenerClientes() {
        return clienteRepository.findAll();
    }

    public Cliente obtenerClientePorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El cliente no existe"));
    }

    public Cliente crearCliente(ClienteCreateDTO clienteNuevo) {
        Cliente cliente = new Cliente();
        cliente.setNombre(clienteNuevo.getNombre());
        cliente.setEmail(clienteNuevo.getEmail());
        cliente.setTelefono(clienteNuevo.getTelefono());
        return clienteRepository.save(cliente);
    }


    public Cliente actualizarCliente(Long id, ClienteUpdateDTO cliente) {
        Cliente existente = obtenerClientePorId(id);

        if (cliente.getNombre() != null) {
            existente.setNombre(cliente.getNombre());
        }

        if (cliente.getTelefono() != null) {
            existente.setTelefono(cliente.getTelefono());
        }

        return clienteRepository.save(existente);
    }

    public void eliminarCliente(Long id) {
        Cliente existente = obtenerClientePorId(id);
        clienteRepository.delete(existente);
    }
}