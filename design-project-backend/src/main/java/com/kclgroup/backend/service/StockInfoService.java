package com.kclgroup.backend.service;

import com.kclgroup.backend.pojo.entity.StockInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 张小明
* @description 针对表【stock_info】的数据库操作Service
* @createDate 2024-06-25 13:58:37
*/

public interface StockInfoService extends IService<StockInfo> {

    StockInfo getByStockCode(String stockCode);
}
