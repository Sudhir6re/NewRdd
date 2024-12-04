package com.mahait.gov.in.common;

import java.util.ArrayList;
import java.util.List;

import com.mahait.gov.in.model.LstGenerateBillDetailsModel;

public class CommonHelper {
	
	public List<LstGenerateBillDetailsModel> getBillsForConsolidationDataHelper(List<Object[]> lstprop) {
		List<LstGenerateBillDetailsModel> lstObj = new ArrayList<>();
		if (!lstprop.isEmpty()) {
			for (Object[] objLst : lstprop) {
				LstGenerateBillDetailsModel obj = new LstGenerateBillDetailsModel();
				obj.setPaybillGenerationTrnId(objLst[0].toString());
				obj.setBillDescription(StringHelperUtils.isNullString(objLst[1]));
				obj.setSchemeCode(StringHelperUtils.isNullString(objLst[2]));
				obj.setSchemeName(StringHelperUtils.isNullString(objLst[3]));
				obj.setBillGrossAmt(StringHelperUtils.isNullDouble(Double.parseDouble(objLst[4].toString())));
				obj.setBillNetAmt(StringHelperUtils.isNullDouble(Double.parseDouble(objLst[5].toString())));
				obj.setIsActive(StringHelperUtils.isNullInt(Integer.parseInt(String.valueOf(objLst[6]))));
				obj.setDdoCode(StringHelperUtils.isNullString((String.valueOf(objLst[7]))));
				lstObj.add(obj);
			}
		}
		return lstObj;
	
	}
}
