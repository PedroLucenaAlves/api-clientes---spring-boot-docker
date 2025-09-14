package br.com.cliente.service;

import br.com.cliente.controller.dto.filtro.ClienteFiltro;
import br.com.cliente.entity.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ClienteService {

    Cliente salvar(Cliente cliente);

    Page<Cliente> listaCliente(ClienteFiltro clienteFiltro, Pageable pageable); //contrato (interface nao tem implementacao, ou seja, o trecho que fica entre os { ... }
    //A paginacao e um tipo de filtragem que nos possibilita em massas muito grandes, passar apenas a quantidade que desejamos
    //exp: se eu filtrar pelo nome Silva em uma base de dados, provavelmente mais de 20 usuarios salvos apareceram. Para evitar que seja exibido todos de uma vez, usamos o pageable
    //Para usarmos a filtragem, setamos nos parametros do Postman (ou ferramenta de requisicao), os campos size (tanto de dados que serao exibidos em uma unica pagina)
    //e o page (começando pelo indice 0 que representa a primeira pagina)

    Optional<Cliente> buscarPorId(Long id);

    void removerPorId(Long id);

    //    public Optional<Cliente> buscarPorId(Long id){ <--contrato
//        return clienteRepository.findById(id);  <--- implementacao
//    }

}
