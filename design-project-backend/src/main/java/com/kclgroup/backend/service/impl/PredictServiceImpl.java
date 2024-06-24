package com.kclgroup.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kclgroup.backend.pojo.entity.Predict;
import com.kclgroup.backend.service.PredictService;
import com.kclgroup.backend.mapper.PredictMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 张小明
* @description 针对表【predict】的数据库操作Service实现
* @createDate 2024-06-24 21:18:07
*/
@Service
public class PredictServiceImpl extends ServiceImpl<PredictMapper, Predict>
    implements PredictService{

    @Autowired PredictMapper predictMapper;
    @Override
    public List<Predict> getPredict() {
        List<Predict> predict = predictMapper.getPredict();
        return predict;
    }
}




