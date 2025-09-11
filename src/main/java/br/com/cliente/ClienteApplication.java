package br.com.cliente;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ClienteApplication {

    @Bean //toda vez que a app for executada, essa config sera executada
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true); //faz com que o modelMapper desconsidere valores nulos
        return modelMapper;
    }

    public static void main(String[] args) {
        SpringApplication.run(ClienteApplication.class, args);
    }
}