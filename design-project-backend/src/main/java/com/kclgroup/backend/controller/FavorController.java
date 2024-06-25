package com.kclgroup.backend.controller;

import com.kclgroup.backend.pojo.entity.Favor;
import com.kclgroup.backend.pojo.entity.Result;
import com.kclgroup.backend.pojo.entity.StockInfo;
import com.kclgroup.backend.service.FavorService;
import com.kclgroup.backend.service.StockInfoService;
import com.kclgroup.backend.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/favor")
@Validated
public class FavorController {

    @Autowired
    private StockInfoService stockInfoService;
    @Autowired
    private FavorService favorService;

    @PostMapping
    public Result favor(@RequestParam String stockCode) {
        //1.校验参数
        if (stockCode == null) {
            return Result.error("缺少必要的参数");
        }
        //2.检验，调用service完成收藏
        StockInfo stockInfo = stockInfoService.getByStockCode(stockCode);
        if(stockInfo == null)return Result.error("股票代码不存在");

        Map<String,Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");

        Favor favor = favorService.getFavor(username,stockCode);
        if(favor != null)return Result.error("该股票已收藏过");

        favorService.favor(username,stockCode);
        return Result.success();
    }
}
