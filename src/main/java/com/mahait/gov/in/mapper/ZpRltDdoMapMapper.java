package com.mahait.gov.in.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mahait.gov.in.entity.ZpRltDdoMap;
import com.mahait.gov.in.model.ZpRltDdoMapModel;

@Component
public class ZpRltDdoMapMapper {
	
    private final ObjectMapper objectMapper;

    @Autowired
    public ZpRltDdoMapMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public ZpRltDdoMap converModelToEntity(ZpRltDdoMapModel zpRltDdoMapModel) {
        return objectMapper.convertValue(zpRltDdoMapModel, ZpRltDdoMap.class);
    }

    public ZpRltDdoMapModel convertEntityToModel(ZpRltDdoMap zpRltDdoMap) {
        return objectMapper.convertValue(zpRltDdoMap, ZpRltDdoMapModel.class);
    }

    public List<ZpRltDdoMapModel> convertEntityListToModelList(List<ZpRltDdoMap> lstZpRltDdoMap) {
        return lstZpRltDdoMap.stream()
                .map(zpRltDdoMap -> objectMapper.convertValue(zpRltDdoMap, ZpRltDdoMapModel.class))
                .collect(Collectors.toList());
    }



}
