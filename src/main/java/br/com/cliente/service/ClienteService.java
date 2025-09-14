package br.com.cliente.service;

import br.com.cliente.controller.dto.filtro.ClienteFiltro;
import br.com.cliente.entity.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ClienteService {

    Cliente salvar(Cliente cliente);

    Page<Cliente> listaCliente(ClienteFiltro clienteFiltro, Pageable pageable);

    Optional<Cliente> buscarPorId(Long id);

    void removerPorId(Long id);
    

}
