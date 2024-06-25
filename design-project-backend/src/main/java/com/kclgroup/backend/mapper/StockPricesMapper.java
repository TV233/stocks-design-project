package com.kclgroup.backend.mapper;

import com.kclgroup.backend.pojo.entity.StockPrices;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kclgroup.backend.pojo.vo.StockPriceVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author 张小明
* @description 针对表【stock_prices】的数据库操作Mapper
* @createDate 2024-06-25 13:36:30
* @Entity com.kclgroup.backend.pojo.entity.StockPrices
*/
@Mapper
public interface StockPricesMapper extends BaseMapper<StockPrices> {
    @Select("select p.stock_code,i.stock_name, latest_price, price_change_rate, price_change, rise_speed from stock_prices p,stock_info i where p.stock_code = i.stock_code order by price_change_rate DESC limit 20")
    List<StockPriceVo> getStockPrices();
}




