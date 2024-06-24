package com.kclgroup.backend.service;

import com.kclgroup.backend.pojo.entity.Predict;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 张小明
* @description 针对表【predict】的数据库操作Service
* @createDate 2024-06-24 21:18:07
*/
public interface PredictService extends IService<Predict> {

    List<Predict> getPredict();
}
