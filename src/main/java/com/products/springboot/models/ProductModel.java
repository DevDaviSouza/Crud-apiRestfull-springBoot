package com.products.springboot.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Esta classe representa o modelo de produto para mapeamento objeto-relacional.
 */
@Entity
@Table(name = "TB_PRODUCTS") // Define o nome da tabela no banco de dados
public class ProductModel implements Serializable {
    // Número de controle de versão das classes serializadas
    private static final long serialVersionUID = 1L;

    // ID do produto, gerado automaticamente pelo banco de dados
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private UUID idProduct;

    // Nome do produto
    private String name;

    // Valor do produto
    private BigDecimal valor;

    /**
     * Obtém o ID do produto.
     * @return O ID do produto.
     */
    public UUID getIdProduct() {
        return idProduct;
    }

    /**
     * Define o ID do produto.
     * @param idProduct O novo ID do produto.
     */
    public void setIdProduct(UUID idProduct) {
        this.idProduct = idProduct;
    }

    /**
     * Obtém o nome do produto.
     * @return O nome do produto.
     */
    public String getName() {
        return name;
    }

    /**
     * Define o nome do produto.
     * @param name O novo nome do produto.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtém o valor do produto.
     * @return O valor do produto.
     */
    public BigDecimal getValor() {
        return valor;
    }

    /**
     * Define o valor do produto.
     * @param valor O novo valor do produto.
     */
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
