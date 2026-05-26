package org.example.b2.service;

import org.example.b2.entity.Product;
import org.example.b2.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productService = new ProductService(productRepository);
    }

    @Test
    void addStockSuccess() {

        Product product = new Product("P001", 100);

        when(productRepository.findById("P001"))
                .thenReturn(Optional.of(product));

        int result = productService.updateStock("P001", 20);

        assertThat(result).isEqualTo(120);

        verify(productRepository).save(product);
    }

    @Test
    void subtractStockSuccess() {

        Product product = new Product("P001", 100);

        when(productRepository.findById("P001"))
                .thenReturn(Optional.of(product));

        int result = productService.updateStock("P001", -30);

        assertThat(result).isEqualTo(70);

        verify(productRepository).save(product);
    }

    @Test
    void throwExceptionWhenStockNegative() {

        Product product = new Product("P001", 5);

        when(productRepository.findById("P001"))
                .thenReturn(Optional.of(product));

        assertThatThrownBy(() ->
                productService.updateStock("P001", -10))
                .isInstanceOf(IllegalArgumentException.class);

        verify(productRepository, never()).save(any());
    }

    @Test
    void throwExceptionWhenProductNotFound() {

        when(productRepository.findById("P001"))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() ->
                productService.updateStock("P001", 10))
                .isInstanceOf(IllegalArgumentException.class);

        verify(productRepository, never()).save(any());
    }

    @Test
    void verifySaveCalled() {

        Product product = new Product("P001", 50);

        when(productRepository.findById("P001"))
                .thenReturn(Optional.of(product));

        productService.updateStock("P001", 10);

        verify(productRepository, times(1))
                .save(product);
    }
}