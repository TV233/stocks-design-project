package com.kclgroup.backend.mapper;

import com.kclgroup.backend.pojo.entity.StockInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
* @author 张小明
* @description 针对表【stock_info】的数据库操作Mapper
* @createDate 2024-06-25 13:58:37
* @Entity com.kclgroup.backend.pojo.entity.StockInfo
*/
@Mapper
public interface StockInfoMapper extends BaseMapper<StockInfo> {
    @Select("select * from stock_info where stock_code=#{stockCode}")
    StockInfo getByStockCode(String stockCode);
}




