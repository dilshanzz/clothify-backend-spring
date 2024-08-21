package org.example.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.ProductDto;
import org.example.dto.StockDto;
import org.example.entity.Product;
import org.example.entity.Stock;
import org.example.repository.StockRepository;
import org.example.service.ProductService;
import org.example.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class  StockServiceImpl implements StockService {
    @Autowired
    ObjectMapper mapper;

    @Autowired
    StockRepository stockRepository;
    @Autowired
    ProductService productService;

    @Override
    public Boolean addStock(StockDto stockDto) {
        ProductDto product =productService.getProductByName(stockDto.getProduct().getName());
        Stock stock=mapper.convertValue(stockDto,Stock.class);
        stock.setProduct(Product.builder().id(product.getId()).name(product.getName()).build());
        Stock saved = stockRepository.save(stock);
        return saved.getId() != null;
    }

    @Override
    public StockDto updateStock(Long id, StockDto stockDto) {
            Optional<Stock> stockRepositoryById=stockRepository.findById(id);
            if (stockRepositoryById.isPresent()) {
                Stock stock=stockRepositoryById.get();
                stockRepository.deleteById(stock.getId());
                addStock(stockDto);
            }
            return null;
    }

    @Override
    public Boolean deleteStock(Long id) {
        if (stockRepository.existsById(id)){
            stockRepository.deleteById(id);
            return true;
        }else {
            return false;
        }

    }
    public List<StockDto> getStockAccordingToSizeAndProduct(String size, Long id){
        List<Stock> stockList=stockRepository.findBySizeAndProductId(size,id);
        if (stockList.isEmpty()) {
            return Collections.emptyList();
        } else {
            return convertStockListToDto(stockList);
        }
    }

    private List<StockDto> convertStockListToDto(List<Stock> stock) {
        List<StockDto> newList= new ArrayList<>();
        for(Stock stockB:stock){
            StockDto stockDto = new StockDto();
            Product product = new Product();
            product.setId(stockB.getProduct().getId());
            product.setPrice(stockB.getProduct().getPrice());
            product.setName(stockB.getProduct().getName());

            stockDto.setPrice(stockB.getPrice());
            stockDto.setSize(stockB.getSize());
            stockDto.setQty(stockB.getQty());
            stockDto.setId(stockB.getId());
            stockDto.setColor(stockB.getColor());
            stockDto.setProduct(product);
            newList.add(stockDto);
        }
        return newList;
    }

    @Override
    public List<StockDto> listStock(Long id) {
        Optional<Stock> stockOptional = stockRepository.findById(id);
        if (stockOptional.isPresent()) {
            Stock stock = stockOptional.get();
            Long productId=stock.getProduct().getId();
            ProductDto productDto=productService.getProductById(productId);
            StockDto convertedStock=convertStockToDTO(stock);
            convertedStock.setProduct(Product.builder().id(productDto.getId()).name(productDto.getName()).build());
            return Collections.singletonList(convertedStock);
        } else {

            return Collections.emptyList();
        }
    }

    @Override
    public StockDto getStockById(long id){
        Optional<Stock> stockOptional = stockRepository.findById(id);

        if (stockOptional.isPresent()) {
            Stock stock = stockOptional.get();
            Long productId = stock.getProduct().getId();
            ProductDto productDto = productService.getProductById(productId);
            StockDto stockDto=mapper.convertValue(stock,StockDto.class);
            stockDto.setProduct(Product.builder().id(productDto.getId()).name(productDto.getName()).build());
            return stockDto;
        }
        return null;
    }


    private StockDto convertStockToDTO(Stock stock) {
        StockDto stockDTO = new StockDto();
        stockDTO.setId(stock.getId());
        stockDTO.setColor(stock.getColor());
        stockDTO.setSize(stock.getSize());
        stockDTO.setPrice(stock.getPrice());
        stockDTO.setQty(stock.getQty());
        return stockDTO;
    }

}