package com.mahait.gov.in.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mahait.gov.in.entity.MstEmployeeEntity;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.MstEmployeeModel;
import com.mahait.gov.in.repository.ChangeDcpsGpfRepo;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
@Transactional
public class ChangeDcpsGpfServiceImpl implements ChangeDcpsGpfService {
	
	@Autowired
	ChangeDcpsGpfRepo changeDcpsGpfRepo;

	@Override
	public List<Object[]> getDOJ(String sevaarthId) {
		// TODO Auto-generated method stub
		return changeDcpsGpfRepo.getDOJ(sevaarthId);
	}

	@Override
	public Long savechangeDcpsGpf(@Valid MstEmployeeModel mstEmployeeModel, OrgUserMst messages) {
		MstEmployeeEntity lstprop = changeDcpsGpfRepo.getemp(messages.getDdoCode());

		
	
        lstprop.setDoj(mstEmployeeModel.getDoj());
        if(mstEmployeeModel.getDcpsgpfflag()=="D") {
        	lstprop.setDcpsgpfflag("Y");
        } else if(mstEmployeeModel.getDcpsgpfflag()=="G") {
        	lstprop.setDcpsgpfflag("N");
        }
        
       
		
        changeDcpsGpfRepo.savechangeDcpsGpf(lstprop);
        return 10l;
	}

}
