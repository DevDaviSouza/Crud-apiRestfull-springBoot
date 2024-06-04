package com.products.springboot.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.products.springboot.dtos.ProductRecordDto;
import com.products.springboot.models.ProductModel;
import com.products.springboot.repositories.ProductRepository;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * Controlador REST para manipulação de produtos.
 */
@RestController
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    /**
     * Endpoint para salvar um novo produto.
     * @param productRecordDto DTO contendo os dados do produto a ser salvo.
     * @return ResponseEntity contendo o objeto do produto recém-salvo e o status HTTP 201 (Created).
     */
    @PostMapping("/products")
    public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductRecordDto productRecordDto) {
        var productModel = new ProductModel();
        BeanUtils.copyProperties(productRecordDto, productModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(productModel));
    }

    /**
     * Endpoint para buscar todos os produtos.
     * @return ResponseEntity contendo uma lista de objetos ProductModel e o status HTTP 200 (OK).
     */
    @GetMapping("/products")
    public ResponseEntity<List<ProductModel>> getAllProducts(){
        return ResponseEntity.status(HttpStatus.OK).body(productRepository.findAll());
    }

    /**
     * Endpoint para buscar um único produto pelo ID.
     * @param id ID do produto a ser buscado.
     * @return ResponseEntity contendo o objeto do produto encontrado ou uma mensagem de erro e o status HTTP correspondente.
     */
    @GetMapping("/products/{id}")
    public ResponseEntity<Object> getOneProduct(@PathVariable(value="id") UUID id) {
        Optional<ProductModel> product0 = productRepository.findById(id);
        if (product0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(product0.get());
    }

    /**
     * Endpoint para atualizar um produto existente.
     * @param id ID do produto a ser atualizado.
     * @param productRecordDto DTO contendo os novos dados do produto.
     * @return ResponseEntity contendo o objeto do produto atualizado ou uma mensagem de erro e o status HTTP correspondente.
     */
    @PutMapping("products/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(value = "id") UUID id, @RequestBody @Valid ProductRecordDto productRecordDto) {
        Optional<ProductModel> product0 = productRepository.findById(id);
        if (product0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        var productModel = product0.get();
        BeanUtils.copyProperties(productRecordDto, productModel);

        return ResponseEntity.status(HttpStatus.OK).body(productRepository.save(productModel));
    }

    /**
     * Endpoint para deletar um produto existente.
     * @param id ID do produto a ser deletado.
     * @return ResponseEntity contendo uma mensagem de sucesso ou uma mensagem de erro e o status HTTP correspondente.
     */
    @DeleteMapping("/products/{id}") 
    public ResponseEntity<Object> deleteProduct(@PathVariable(value="id") UUID id) {
        Optional<ProductModel> product0 = productRepository.findById(id);
        if (product0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }

        productRepository.delete(product0.get());
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully");
    }
}