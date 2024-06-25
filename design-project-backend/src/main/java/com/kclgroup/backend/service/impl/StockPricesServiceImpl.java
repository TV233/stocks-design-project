package com.kclgroup.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kclgroup.backend.pojo.entity.StockPrices;
import com.kclgroup.backend.pojo.vo.StockPriceVo;
import com.kclgroup.backend.service.StockPricesService;
import com.kclgroup.backend.mapper.StockPricesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 张小明
* @description 针对表【stock_prices】的数据库操作Service实现
* @createDate 2024-06-25 13:36:30
*/
@Service
public class StockPricesServiceImpl extends ServiceImpl<StockPricesMapper, StockPrices>
    implements StockPricesService{
    @Autowired
    private StockPricesMapper stockPricesMapper;
    @Override
    public List<StockPriceVo> getStockPrices() {
        List<StockPriceVo> stockPrices = stockPricesMapper.getStockPrices();
        return stockPrices;
    }
}




