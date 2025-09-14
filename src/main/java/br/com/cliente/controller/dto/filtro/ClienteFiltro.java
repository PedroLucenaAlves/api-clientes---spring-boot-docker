package br.com.cliente.controller.dto.filtro;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteFiltro {

    private Long id;
    private String nome;
    private String email;
    private String cpf;

}
