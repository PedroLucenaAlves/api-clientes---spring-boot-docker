package br.com.cliente.service.impl;

import br.com.cliente.controller.dto.filtro.ClienteFiltro;
import br.com.cliente.entity.Cliente;
import br.com.cliente.repository.ClienteRepository;
import br.com.cliente.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    //Iot via construtor é boa prática pois deixa explicito que essa classe não pode existir sem um clienteRepository
    //outra vantagem é na hora de realizar testes unitarios. Com um  construtor basta usarmos o new
    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }


    @Override
    public Cliente salvar(Cliente cliente) { //na classe dservice, implementamos os contratos da nossa interface
        return clienteRepository.save(cliente);
    }

    @Override
    public Page<Cliente> listaCliente(ClienteFiltro clienteFiltro, Pageable pageable) {

        //preparamos o cliente com os valores atraves do builder para setarmos ele no parametro de example
        Cliente cliente = Cliente.builder()
                .id(clienteFiltro.getId())
                .nome(clienteFiltro.getNome())
                .email(clienteFiltro.getEmail())
                .cpf(clienteFiltro.getCpf())
                .build();

        //Para que o filtro reconheça apenas uma parte do valor e entenda do que se trata, criamos esse exampleMAthcer
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        //example funciona como um filtro e neste caso filtro de cliente
        //na simulacao a chamada ao endpoint, em params passamos a filtragem que desejamos (example nome, email, etc)
        Example example = Example.of(cliente, exampleMatcher);

        return clienteRepository.findAll(example, pageable); //A paginacao e um tipo de filtragem que nos possibilita em massas muito grandes, passar apenas a quantidade que desejamos
        //exp: se eu filtrar pelo nome Silva em uma base de dados, provavelmente mais de 20 usuarios salvos apareceram. Para evitar que seja exibido todos de uma vez, usamos o pageable
        //Para usarmos a filtragem, setamos nos parametros do Postman (ou ferramenta de requisicao), os campos size (tanto de dados que serao exibidos em uma unica pagina)
        //e o page (começando pelo indice 0 que representa a primeira pagina)
    }

    @Override
    public Optional<Cliente> buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }

    @Override
    public void removerPorId(Long id) {
        clienteRepository.deleteById(id);
    }
}
