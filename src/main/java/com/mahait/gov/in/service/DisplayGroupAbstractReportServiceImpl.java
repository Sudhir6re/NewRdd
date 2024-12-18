package com.mahait.gov.in.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mahait.gov.in.common.StringHelperUtils;
import com.mahait.gov.in.model.DisplayGroupAbstractReportModel;
import com.mahait.gov.in.repository.DisplayGroupAbstractReportRepo;

@Service
@Transactional
public class DisplayGroupAbstractReportServiceImpl implements DisplayGroupAbstractReportService {
	@Autowired
	DisplayGroupAbstractReportRepo 	displayGroupAbstractReportRepo;

	public List getReportHeaderDetails(String bill_no) {
		return displayGroupAbstractReportRepo.getReportHeaderDetails(bill_no);
	}
	public List<DisplayGroupAbstractReportModel> findBillDescription(String ddoCode,int month,int year){
		return displayGroupAbstractReportRepo.findBillDescription(ddoCode,month,year);
	}
	public int getTotalDeductionGroupAbstract(double billno) {
		return displayGroupAbstractReportRepo.getTotalDeductionGroupAbstract(billno);
	}
	public List<DisplayGroupAbstractReportModel> getAllDataForAbstractnew(String ddocode,Long billNumber){
		
		
		List<Object[]> lstprop = displayGroupAbstractReportRepo.getAllDataForAbstractnew(ddocode,billNumber);
		
		List<DisplayGroupAbstractReportModel> lstObj = new ArrayList<>();
        if (!lstprop.isEmpty()) {
        	int i=1;
            for (Object[] objLst : lstprop) {
            	DisplayGroupAbstractReportModel obj = new DisplayGroupAbstractReportModel();
            	/*if(i==1) {
            		obj.setDeptalldetNm("Basic_Pay");
            	}else {*/
            		obj.setDeptalldetNm(StringHelperUtils.isNullString(objLst[0]));
            	//}
                obj.setType(StringHelperUtils.isNullInt(objLst[1]));
                obj.setDeptallowdeducid(StringHelperUtils.isNullInt(objLst[2]));
                obj.setHeadAccountCode(StringHelperUtils.isNullString(objLst[3]));
                lstObj.add(obj);
                i++;
            }
        }
		return lstObj;
	}
	public List<Map<String,Object>> getempDetailsGroupAbstract(String bill_no){
		return displayGroupAbstractReportRepo.getempDetailsGroupAbstract(bill_no);
	}
	public List<Map<String,Object>> getempDetailsGroupAbstractA(String bill_no){
		return displayGroupAbstractReportRepo.getempDetailsGroupAbstractA(bill_no);
	}
	public List<Map<String,Object>> getempDetailsGroupAbstractB(String bill_no){
		return displayGroupAbstractReportRepo.getempDetailsGroupAbstractB(bill_no);
	}
	public List<Map<String,Object>> getempDetailsGroupAbstractC(String bill_no){
		return displayGroupAbstractReportRepo.getempDetailsGroupAbstractC(bill_no);
	}
	public List<Map<String,Object>> getempDetailsGroupAbstractD(String bill_no){
		return displayGroupAbstractReportRepo.getempDetailsGroupAbstractD(bill_no);
	}
	public List<Map<String,Object>> getempDetailsGroupAbstractE(String bill_no){
		return displayGroupAbstractReportRepo.getempDetailsGroupAbstractE(bill_no);
	}

	
}

