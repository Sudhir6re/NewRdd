package com.mahait.gov.in.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mahait.gov.in.common.StringHelperUtils;
import com.mahait.gov.in.model.ConsolidatedReportModel;
import com.mahait.gov.in.repository.ConsolidatedReportRepo;

@Service
@Transactional
public class ConsolidatedReportServiceImpl implements ConsolidatedReportService {
	@Autowired
	ConsolidatedReportRepo 	consolidatedReportRepo;
	public String getOffice(String ddoCode) {
		return consolidatedReportRepo.getOffice(ddoCode);
	}
	
	public List getReportHeaderDetails(String bill_no) {
		return consolidatedReportRepo.getReportHeaderDetails(bill_no);
	}
	public List<ConsolidatedReportModel> findBillDescription(String ddoCode,int month,int year){
		return consolidatedReportRepo.findBillDescription(ddoCode,month,year);
	}
	public int getTotalDeduction(double billno) {
		return consolidatedReportRepo.getTotalDeduction(billno);
	}
	public List<ConsolidatedReportModel> getAllDataForOuternew(String ddocode){
		List<Object[]> lstprop = consolidatedReportRepo.getAllDataForOuternew(ddocode);
		
		List<ConsolidatedReportModel> lstObj = new ArrayList<>();
		int i=1;
        if (!lstprop.isEmpty()) {
            for (Object[] objLst : lstprop) {
            	ConsolidatedReportModel obj = new ConsolidatedReportModel();
            	if(i==1) {
            		obj.setDeptalldetNm("GROSS_AMT");
//            		obj.setDeptalldetNm("total_net_amt");
            	}
            	else if(i==2) {
                		obj.setDeptalldetNm("PF");
//                		obj.setDeptalldetNm("total_deduction");
            		}
            	else{
            		obj.setDeptalldetNm(StringHelperUtils.isNullString(objLst[0]));
            	}
                obj.setType(StringHelperUtils.isNullInt(objLst[1]));
                obj.setDeptallowdeducid(StringHelperUtils.isNullInt(objLst[2]));
               
                obj.setHeadAccountCode(StringHelperUtils.isNullString(objLst[3]));
                lstObj.add(obj);
                i++;
            }
        }
		return lstObj;
	}
	public List<Map<String,Object>> getempDetails(String bill_no){
		return consolidatedReportRepo.getempDetails(bill_no);
	}

	@Override
	public List<Object[]> findbillcrateornot(int monthName, int yearName, String ddocode, String billnumber) {
		return consolidatedReportRepo.getempDetails(monthName,yearName,ddocode,billnumber);
	}

	@Override
	public List<ConsolidatedReportModel> findAllSchemeBillGroupByDDOCode(String DDOCode, int roleid) {
		return consolidatedReportRepo.findAllSchemeBillGroupByDDOCode(DDOCode,roleid);
	}

	@Override
	public List getReportHeaderCOnsolidateDetails(String valueOf) {
		return consolidatedReportRepo.getReportHeaderCOnsolidateDetails(valueOf);
	}

	@Override
	public List<Object[]> findbillBillNumber(Long consolidatePayBillTrnId) {
		// TODO Auto-generated method stub
		return consolidatedReportRepo.findbillBillNumber(consolidatePayBillTrnId);
	}

	@Override
	public Date findbillCreateConsolidateDate(Long consolidatePayBillTrnId) {
		// TODO Auto-generated method stub
		return consolidatedReportRepo.findbillCreateConsolidateDate(consolidatePayBillTrnId);
	}
	
	
}
