package org.example.service;



import org.example.dto.StockDto;

import java.util.List;

public interface StockService {

    Boolean addStock(StockDto stockDto);

    public StockDto updateStock(Long id, StockDto stockDto);
    Boolean deleteStock(Long id);

    List<StockDto> listStock(Long id);

    StockDto getStockById(long id);
    List<StockDto> getStockAccordingToSizeAndProduct(String size, Long id);
}