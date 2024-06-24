package com.kclgroup.backend.controller;

import com.kclgroup.backend.pojo.entity.Result;
import com.kclgroup.backend.pojo.entity.TopText;
import com.kclgroup.backend.service.TopTextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Validated
public class OverviewController {
    @Autowired
    private TopTextService topTextService;
    @GetMapping
    public String overview() {
        return "overview";
    }
    @GetMapping("/top_text")
    public Result<TopText> topText() {
        TopText topText = topTextService.getTopText();
        return Result.success(topText);
    }

}
