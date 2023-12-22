package com.uleowz.crudspring.model;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data // equivale ao getter, setter, constructor(com todos os atributos), ToString, Equals e HashCode.
@Entity // significa que estamos especificando essa classe como uma entidade que vai fazer mapeamento com o DB.
// @Table(name = "cursos") // mudando o nome da tabela, que por padrão seria "Course"
@SQLDelete(sql = "UPDATE Course SET status = 'Inativo' WHERE id = ?")
@Where(clause = "status = 'Ativo'")
public class Course {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // avisando ao jakarta que os IDs serão gerados em ordem sequencial(1, 2, [...]).
    @JsonProperty("_id") // muda o nome na hora de transformar de objeto java para JSON.
    private Long id;


    @NotNull // Não nulo e não vazio.
    @NotBlank // Ao menos 1 caracter que não seja espaço.
    @Length(min = 5, max = 200)
    @Column(length = 200, nullable = false) // Se tao tiver essa anotacão, o DB vai gerar o String com maior capacidade de caracteres possiveis.
    private String name;
    // o argumento 'length' diz o tamanho maximo, que neste caso por padrao seria 255.
    //  o argumento 'nullable' é para dizer que o DB nao ira aceitar valores nulos - que por padrao, ele aceita.


    @NotNull
    @NotBlank
    @Pattern(regexp = "Back-end|Front-end")
    @Length(max = 10)
    @Column(length = 20, nullable = false)
    private String category;


    @NotNull
    @Pattern(regexp = "Ativo|Inativo")
    @Length(max = 10)
    @Column(length = 10, nullable = false)
    private String status = "Ativo";
}
