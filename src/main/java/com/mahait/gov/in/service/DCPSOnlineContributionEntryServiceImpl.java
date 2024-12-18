package com.mahait.gov.in.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mahait.gov.in.entity.CmnLocationMst;
import com.mahait.gov.in.entity.MstDcpsBillGroup;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.repository.DCPSOnlineContributionEntryRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DCPSOnlineContributionEntryServiceImpl implements DCPSOnlineContributionEntryService {
	
	@Autowired
	DCPSOnlineContributionEntryRepo dCPSOnlineContributionEntryRepo;
	

	@Override
	public List<MstDcpsBillGroup> findAllBillGroup(OrgUserMst messages) {
		// TODO Auto-generated method stub
		return dCPSOnlineContributionEntryRepo.findAllBillGroup(messages);
	}


	@Override
	public List<CmnLocationMst> findTreasuryByDdoCode(OrgUserMst messages) {
		return dCPSOnlineContributionEntryRepo.findTreasuryByDdoCode(messages);
	}

}
