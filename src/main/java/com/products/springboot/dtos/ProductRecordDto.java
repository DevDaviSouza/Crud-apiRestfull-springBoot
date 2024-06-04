package com.products.springboot.dtos;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Data Transfer Object (DTO) para representar os dados de um produto.
 */
public record ProductRecordDto(@NotBlank String name, @NotNull BigDecimal valor) {
}
