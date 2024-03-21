package br.com.codinglis.frutasapi.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity(name = "frutas")
public class FrutaModel {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column(nullable = false, length = 60)
    private String nome;
    @Column(nullable = false)
    private Double preco;

    public FrutaModel() {}

    public FrutaModel(String nome, Double preco){
        this.nome = nome;
        this.preco = preco;
    }
}
