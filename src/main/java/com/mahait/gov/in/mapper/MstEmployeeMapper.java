package com.mahait.gov.in.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mahait.gov.in.entity.MstEmployeeEntity;
import com.mahait.gov.in.model.MstEmployeeModel;

@Component
public class MstEmployeeMapper {

    private final ObjectMapper objectMapper;

    @Autowired
    public MstEmployeeMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public MstEmployeeEntity converMstEmployeeModelToEntity(MstEmployeeModel mstEmployeeModel) {
        return objectMapper.convertValue(mstEmployeeModel, MstEmployeeEntity.class);
    }

    public MstEmployeeModel convertEntityToModel(MstEmployeeEntity mstEmployeeModel) {
        return objectMapper.convertValue(mstEmployeeModel, MstEmployeeModel.class);
    }

}
