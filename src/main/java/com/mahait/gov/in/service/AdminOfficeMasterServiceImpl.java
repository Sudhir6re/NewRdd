package com.mahait.gov.in.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.entity.ZpAdminOfficeMst;
import com.mahait.gov.in.mapper.ZpAdminOfficeMstMapper;
import com.mahait.gov.in.model.ZpAdminOfficeMstModel;
import com.mahait.gov.in.repository.ZpAdminNameMstRepository;
import com.mahait.gov.in.repository.ZpAdminOfficeMstRepository;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class AdminOfficeMasterServiceImpl implements AdminOfficeMasterService {

    private final ZpAdminOfficeMstRepository zpAdminOfficeMstRepository;
    private final ZpAdminNameMstRepository zpAdminNameMstRepository;
    private final ZpAdminOfficeMstMapper zpAdminOfficeMstMapper;

    @Autowired
    public AdminOfficeMasterServiceImpl(ZpAdminOfficeMstRepository zpAdminOfficeMstRepository,
                                        ZpAdminNameMstRepository zpAdminNameMstRepository,
                                        ZpAdminOfficeMstMapper zpAdminOfficeMstMapper) {
        this.zpAdminOfficeMstRepository = zpAdminOfficeMstRepository;
        this.zpAdminNameMstRepository = zpAdminNameMstRepository;
        this.zpAdminOfficeMstMapper = zpAdminOfficeMstMapper;
    }

    @Override
    public List<ZpAdminOfficeMstModel> findAllZpAdminOfficeMstModel() {
        List<ZpAdminOfficeMst> offices = zpAdminOfficeMstRepository.findAll();
        return zpAdminOfficeMstMapper.convertEntityListToModelList(offices);
    }

    @Override
    public ZpAdminOfficeMst createOffice(ZpAdminOfficeMstModel zpAdminOfficeMstModel) {
        ZpAdminOfficeMst office = zpAdminOfficeMstMapper.converModelToEntity(zpAdminOfficeMstModel);
        //office.setOfcId(null);
        return zpAdminOfficeMstRepository.save(office);
    }

    @Override
    public ZpAdminOfficeMstModel findOfficebyId(Long officeId) {
        return zpAdminOfficeMstRepository.findById(officeId)
                .map(zpAdminOfficeMstMapper::convertEntityToModel)
                .orElse(null);
    }

    @Override
    public Integer deleteOfficeById(Long officeId, OrgUserMst orgUserMst) {
        return zpAdminOfficeMstRepository.findById(officeId).map(zpAdminOfficeMst -> {
            if (zpAdminOfficeMst.getIsActive() == 0) {
                zpAdminOfficeMst.setIsActive(1);
            } else {
                zpAdminOfficeMst.setIsActive(0);
            }
       //     zpAdminOfficeMst.setUpdatedBy(orgUserMst.getUserId());
            zpAdminOfficeMstRepository.save(zpAdminOfficeMst);
            return 1;
        }).orElse(0);
    }

    @Override
    public String generateOfficeCode() {
        Long maxAdminCode =zpAdminNameMstRepository.findMaxAdminCode();
        return String.valueOf(maxAdminCode != null ? maxAdminCode + 1 : 1);
    }

	@Override
	public boolean officeNameExists(String officeName) {
		// TODO Auto-generated method stub
		return zpAdminOfficeMstRepository.existsByOfficeName(officeName);
	}
}
