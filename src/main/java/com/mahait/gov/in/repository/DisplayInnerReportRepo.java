package com.mahait.gov.in.repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.mahait.gov.in.entity.PaybillGenerationTrnEntity;
import com.mahait.gov.in.model.DisplayInnerReportModel;

public  interface DisplayInnerReportRepo {
	public List<DisplayInnerReportModel> getAllDataForinnernew(String strddo,Long billNumber);
	public List<Map<String,Object>> getempDetails(String bill_no);
	public Date findbillCreateDate(Long billNumber);
	public String getLoanDtls(String empId,Long mon,int year);
	public String getDcpsGpfNoDtls(String sevaarthid);
	public String getGpfNoDtls(String sevaarthid);
	public String getfaLoanDtls(String sevaarthid, Long billNumber);
	public String getcaLoanDtls(String sevaarthid, Long billNumber);
	public String getvaLoanDtls(String sevaarthid, Long billNumber);
	public String gethbaLoanDtls(String sevaarthid, Long billNumber);
	public String getgpfLoanDtls(String sevaarthid, Long billNumber);
	public String getgpfIILoanDtls(String sevaarthid, Long billNumber);
	public String getbillDetails(Long billNumber);
	public String getPayFixDiffLoanDtls(String string, Long billNumber);
	public String gethbaLoanIntsDtls(String string, Long billNumber);
	public String getexPayRecDtls(String string, Long billNumber);
	public PaybillGenerationTrnEntity findPayBilldetailByPaybillid(Long billNumber);
}
