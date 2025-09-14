package br.com.cliente.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor //construtor com as propriedades
@NoArgsConstructor //construtor vazio
@Builder //cria objeto clientes
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome", nullable = false) // nullable = obriga enviar um campo nome
    private String nome;

    @Column(name = "email")
    private String email;

    @Column(name = "cpf")
    private String cpf;

}
