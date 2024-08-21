package org.example.service;

import org.example.dto.ProductDto;
import org.example.entity.Product;

import java.util.List;

public interface ProductService {

    public Boolean addProduct(ProductDto productDto);

    public List<ProductDto> getAllProducts();

    ProductDto getProductById(long id);

    List<ProductDto> getProductByCategory(String categoryName);

    ProductDto getProductByName(String name);

    List<ProductDto> getProductBySubCategory(String subCategory);

    Product getProductByStockId(long id);
}