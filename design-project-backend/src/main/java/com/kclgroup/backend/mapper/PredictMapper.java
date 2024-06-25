package com.kclgroup.backend.mapper;

import com.kclgroup.backend.pojo.entity.Predict;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kclgroup.backend.pojo.vo.PredictVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author 张小明
* @description 针对表【predict】的数据库操作Mapper
* @createDate 2024-06-24 21:18:07
* @Entity com.kclgroup.backend.pojo.entity.Predict
*/
@Mapper
public interface PredictMapper extends BaseMapper<Predict> {
    @Select("SELECT p.stock_code,s.stock_name FROM predict p,stock_info s where p.stock_code = s.stock_code order by RATING_ORG_NUM DESC limit 20")
    List<PredictVo> getPredict();
}




