package com.mahait.gov.in.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mahait.gov.in.common.StringHelperUtils;
import com.mahait.gov.in.model.DisplayOuterReportModel;
import com.mahait.gov.in.model.RegularReportModel;
import com.mahait.gov.in.repository.DisplayOuterReportRepo;

@Service
@Transactional
public class DisplayOuterReportServiceImpl implements DisplayOuterReportService {
	@Autowired
	DisplayOuterReportRepo 	displayOuterReportRepo;
	public String getOffice(String locId) {
		return displayOuterReportRepo.getOffice(locId);
	}
	
	public List<RegularReportModel> getReportHeaderDetails(String bill_no) {
		

		
		List<Object[]> lstprop = displayOuterReportRepo.getReportHeaderDetails(bill_no);
		
		List<RegularReportModel> lstObj = new ArrayList<>();
        if (!lstprop.isEmpty()) {
        	int i=1;
            for (Object[] objLst : lstprop) {
            	RegularReportModel obj = new RegularReportModel();
            	
            	obj.setDdoCode(StringHelperUtils.isNullString(objLst[0]));
                obj.setSchemeName(StringHelperUtils.isNullString(objLst[1]));
                obj.setSchemeCode(StringHelperUtils.isNullString(objLst[2]));
                //BigInteger gross = (BigInteger) objLst[3];
                obj.setGrossAmount(StringHelperUtils.isNullDouble(objLst[3]));
                obj.setMonthId(StringHelperUtils.isNullInt(objLst[4]));
                obj.setBillCreatedDate(StringHelperUtils.isNullDate(objLst[5]));
                obj.setDemandCode(StringHelperUtils.isNullString(objLst[6]));
                obj.setMajorHead(StringHelperUtils.isNullString(objLst[7]));
                obj.setSubMajorHead(StringHelperUtils.isNullString(objLst[8]));
                obj.setMinorHead(StringHelperUtils.isNullString(objLst[9]));
                obj.setSubMinorHead(StringHelperUtils.isNullString(objLst[10]));
                obj.setSubHead(StringHelperUtils.isNullString(objLst[11]));
                obj.setNetAmount(StringHelperUtils.isNullDouble(objLst[12]));
                obj.setYearId(StringHelperUtils.isNullInt(objLst[13]));
                obj.setDesgName(StringHelperUtils.isNullString(objLst[14]));
                lstObj.add(obj);
                i++;
            }
        }
		
		return lstObj;
	
		
	}
	public List<DisplayOuterReportModel> findBillDescription(String ddoCode,int month,int year){
		return displayOuterReportRepo.findBillDescription(ddoCode,month,year);
	}
	public int getTotalDeduction(double billno) {
		return displayOuterReportRepo.getTotalDeduction(billno);
	}
	public List<DisplayOuterReportModel> getAllDataForOuternew(String ddocode,Long billNumber){
		
		List<Object[]> lstprop = displayOuterReportRepo.getAllDataForOuternew(ddocode,billNumber);
		
		List<DisplayOuterReportModel> lstObj = new ArrayList<>();
        if (!lstprop.isEmpty()) {
        	int i=1;
            for (Object[] objLst : lstprop) {
            	DisplayOuterReportModel obj = new DisplayOuterReportModel();
            	obj.setDeptalldetNm(StringHelperUtils.isNullString(objLst[0]));
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
		return displayOuterReportRepo.getempDetails(bill_no);
	}

	@Override
	public List<Object[]> findbillcrateornot(int monthName, int yearName, String ddocode, String billnumber) {
		return displayOuterReportRepo.getempDetails(monthName,yearName,ddocode,billnumber);
	}

	@Override
	public List<DisplayOuterReportModel> findAllSchemeBillGroupByDDOCode(String DDOCode, int roleid) {
		return displayOuterReportRepo.findAllSchemeBillGroupByDDOCode(DDOCode,roleid);
	}

	@Override
	public List<Object[]> getcardecode(String strddo) {
		List<Object[]> obj = displayOuterReportRepo.getcardecode(strddo);
		return obj;
	}

	@Override
	public Double gettotalNetAmount(String billNumber) {
		// TODO Auto-generated method stub
		return displayOuterReportRepo.gettotalNetAmount(billNumber);
	}
}

