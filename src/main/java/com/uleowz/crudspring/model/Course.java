package com.uleowz.crudspring.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data // equivale ao getter, setter, constructor(com todos os atributos), ToString, Equals e HashCode.
@Entity // significa que estamos especificando essa classe como uma entidade que vai fazer mapeamento com o DB.
// @Table(name = "cursos") // mudando o nome da tabela, que por padrão seria "Course"
public class Course {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // avisando ao jakarta que os IDs serão gerados em ordem sequencial(1, 2, [...]).
    private Long id;

    @Column(length = 200, nullable = false) // Se tao tiver essa anotacão, o DB vai gerar o String com maior capacidade de caracteres possiveis.
    // o argumento 'length' diz o tamanho maximo, que neste caso por padrao seria 255.
    //  o argumento 'nullable' é para dizer que o DB nao ira aceitar valores nulos - que por padrao, ele aceita.
    private String name;

    @Column(length = 20, nullable = false)
    private String category;
}
