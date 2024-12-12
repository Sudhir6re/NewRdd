package com.mahait.gov.in.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mahait.gov.in.entity.ZpAdminOfficeMst;
import com.mahait.gov.in.model.ZpAdminOfficeMstModel;

@Component
public class ZpAdminOfficeMstMapper {

    private final ObjectMapper objectMapper;

    @Autowired
    public ZpAdminOfficeMstMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public ZpAdminOfficeMst converModelToEntity(ZpAdminOfficeMstModel zpAdminOfficeMstModel) {
        return objectMapper.convertValue(zpAdminOfficeMstModel, ZpAdminOfficeMst.class);
    }

    public ZpAdminOfficeMstModel convertEntityToModel(ZpAdminOfficeMst zpAdminOfficeMst) {
        return objectMapper.convertValue(zpAdminOfficeMst, ZpAdminOfficeMstModel.class);
    }

    public List<ZpAdminOfficeMstModel> convertEntityListToModelList(List<ZpAdminOfficeMst> lstZpAdminOfficeMst) {
        return lstZpAdminOfficeMst.stream()
                .map(zpAdminOfficeMst -> objectMapper.convertValue(zpAdminOfficeMst, ZpAdminOfficeMstModel.class))
                .collect(Collectors.toList());
    }

}
