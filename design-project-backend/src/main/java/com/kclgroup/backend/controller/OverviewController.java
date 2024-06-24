package com.kclgroup.backend.controller;

import com.kclgroup.backend.pojo.entity.*;
import com.kclgroup.backend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@Validated
public class OverviewController {
    @Autowired
    private TopTextService topTextService;
    @Autowired
    private EmotionIndexService emotionIndexService;
    @Autowired
    private SectorsAndStocksService sectorsAndStocksService;
    @Autowired
    private MarketStyleService marketStyleService;
    @Autowired
    private PredictService predictService;
    @GetMapping
    public String overview() {
        return "overview";
    }
    @GetMapping("/top_text")
    public Result<TopText> topText() {
        TopText topText = topTextService.getTopText();
        return Result.success(topText);
    }
    @GetMapping("/emotion_index")
    public Result<EmotionIndex> emotionIndex() {
        EmotionIndex emotionIndex = emotionIndexService.getEmotionIndex();
        return Result.success(emotionIndex);
    }
    @GetMapping("/sectors")
    public Result<List<SectorsAndStocks>> sectors() {
        List<SectorsAndStocks> sectorsAndStocks = sectorsAndStocksService.getSectorsAndStocks();
        return Result.success(sectorsAndStocks);
    }
    @GetMapping("/market_style")
    public Result<List<MarketStyle>> marketStyle() {
        List<MarketStyle> marketStyles = marketStyleService.getMarketStyle();
        return Result.success(marketStyles);
    }

    @GetMapping("/predict")
    public Result<List<Predict>> predict() {
        List<Predict> predicts = predictService.getPredict();
        return Result.success(predicts);
    }


}
