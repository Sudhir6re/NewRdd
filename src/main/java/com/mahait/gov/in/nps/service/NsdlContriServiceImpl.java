package com.mahait.gov.in.nps.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mahait.gov.in.common.StringHelperUtils;
import com.mahait.gov.in.nps.model.NSDLDetailsModel;
import com.mahait.gov.in.nps.repository.NsdlContriRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class NsdlContriServiceImpl implements NsdlContriService  {
	
	@Autowired
	NsdlContriRepo nsdlContriRepo;

	@Override
	public List<NSDLDetailsModel> getNsdlContriDetails(Integer monthId, Integer yearId, int role_id,
			String userName) {
		// TODO Auto-generated method stub
		List<Object[]> lstprop = nsdlContriRepo.getNsdlContriDetails(monthId,yearId,role_id,userName);
		List<NSDLDetailsModel> lstObj= new ArrayList<>();
		if (!lstprop.isEmpty()) { 
			for (Object[] objLst : lstprop){
				
				NSDLDetailsModel obj = new NSDLDetailsModel();
				obj.setEmpName(StringHelperUtils.isNullString(objLst[0]));
				obj.setPRAN(StringHelperUtils.isNullString(objLst[1]));
				obj.setFileId(StringHelperUtils.isNullString(objLst[2]));
				if((objLst[3])!=null) {
					BigDecimal EmprAmtCont=(BigDecimal) (objLst[3]);
					obj.setAmtEmprContibution(EmprAmtCont.doubleValue());
				}
				if((objLst[4])!=null) {
					BigDecimal EmpAmtCont=(BigDecimal) (objLst[4]);
					obj.setAmtEmpContibution(EmpAmtCont.doubleValue());
				}
				if((objLst[5])!=null) {
					BigDecimal totAmtCont=(BigDecimal) (objLst[5]);
					obj.setTotalAmt(totAmtCont.doubleValue());
				}
				
                
				lstObj.add(obj);
			}
		}
		return lstObj;
	}

	@Override
	public List<NSDLDetailsModel> getNsdlContriDetailsDivisionWise(Integer month, Integer year, int role_id,
			String userName) {
		// TODO Auto-generated method stub
		List<Object[]> lstprop = nsdlContriRepo.getNsdlContriDetailsDivisionWise(month,year,role_id,userName);
		List<NSDLDetailsModel> lstObj= new ArrayList<>();
		if (!lstprop.isEmpty()) { 
			for (Object[] objLst : lstprop){
				
				NSDLDetailsModel obj = new NSDLDetailsModel();
				
				
				
				obj.setDeptName(StringHelperUtils.isNullString(objLst[0]));
				if(objLst[1]!=null) {
					BigDecimal EmprAmtCont=(BigDecimal) (objLst[1]);
					obj.setTotalEmprContri(EmprAmtCont.doubleValue());
				}
				if(objLst[2]!=null) {
					BigDecimal EmpAmtCont=(BigDecimal) (objLst[2]);
					obj.setTotalEmpContri(EmpAmtCont.doubleValue());
				}
				if(objLst[3]!=null) {
					BigDecimal totalAmtCont=(BigDecimal) (objLst[3]);
					obj.setTotalContriAmt(totalAmtCont.doubleValue());
				}
				if(objLst[4]!=null) {
					obj.setDeptCode(StringHelperUtils.isNullInt(objLst[4]));
				}
				
                
				lstObj.add(obj);
			}
		}
		return lstObj;
	}

	@Override
	public List<NSDLDetailsModel> getNsdlContriDetailsDeptWise(int deptCode, int month, int year) {
		// TODO Auto-generated method stub
		List<Object[]> lstprop = nsdlContriRepo.getNsdlContriDetailsDeptWise(deptCode, month, year);
		List<NSDLDetailsModel> lstObj= new ArrayList<>();
		if (!lstprop.isEmpty()) { 
			for (Object[] objLst : lstprop){
				
				NSDLDetailsModel obj = new NSDLDetailsModel();
				obj.setEmpName(StringHelperUtils.isNullString(objLst[0]));
				obj.setPRAN(StringHelperUtils.isNullString(objLst[1]));
				obj.setFileId(StringHelperUtils.isNullString(objLst[2]));
				if((objLst[3])!=null) {
					BigDecimal EmprAmtCont=(BigDecimal) (objLst[3]);
					obj.setAmtEmprContibution(EmprAmtCont.doubleValue());
				}
				if((objLst[4])!=null) {
					BigDecimal EmpAmtCont=(BigDecimal) (objLst[4]);
					obj.setAmtEmpContibution(EmpAmtCont.doubleValue());
				}
				if((objLst[5])!=null) {
					BigDecimal totAmtCont=(BigDecimal) (objLst[5]);
					obj.setTotalAmt(totAmtCont.doubleValue());
				}
				
                
				lstObj.add(obj);
			}
		}
		return lstObj;
	}

}
