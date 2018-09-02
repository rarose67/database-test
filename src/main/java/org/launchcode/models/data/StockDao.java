package org.launchcode.models.data;

import org.launchcode.models.Stock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface StockDao extends CrudRepository<Stock, Integer> {

    public List<Stock> findBySymbol(String symbol);

    public List<Stock> findAll();

    @Query("SELECT s FROM Stock s WHERE s.name LIKE %:name%")
    public List<Stock> findByName(@Param("name") String name);

    @Query("SELECT s FROM Stock s WHERE s.price < :price")
    public List<Stock> frindPriceLessThanValue(@Param("price") double price);

    @Query("SELECT s FROM Stock s WHERE s.dividend < :dividend")
    public List<Stock> findDividendLessThanValue(@Param("dividend") double dividend);

    @Query("SELECT s FROM Stock s WHERE s.dividend > :dividend")
    public List<Stock> findDividendGreaterThanValue(@Param("dividend") double dividend);


    @Query("SELECT DISTINCT s.symbol FROM Stock s")
    public List<String> findSymbols();
}
