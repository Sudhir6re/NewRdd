package com.mahait.gov.in.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressWarnings("unchecked")
public class BrokenPeriodPayCustomModel
{
	private Date fromDate;
	private Date toDate;
	private Long noOfDays;
	private Long basicPay;
	private Long netPay;
	private String reason;
	private String remarks;
	private List allowList;
	private List deductList;
	private Double arrears;
	
	List<BrokenPeriodPayCustomModel> brokenPeriodPayCustomModel = new ArrayList<>();

	
	private String basicForCalculation;
	
	public Date getFromDate()
	{
		return fromDate;
	}

	public void setFromDate(Date fromDate)
	{
		this.fromDate = fromDate;
	}

	public Date getToDate()
	{
		return toDate;
	}

	public void setToDate(Date toDate)
	{
		this.toDate = toDate;
	}

	public Long getNoOfDays()
	{
		return noOfDays;
	}

	public void setNoOfDays(Long noOfDays)
	{
		this.noOfDays = noOfDays;
	}

	public Long getBasicPay()
	{
		return basicPay;
	}

	public void setBasicPay(Long basicPay)
	{
		this.basicPay = basicPay;
	}

	public Long getNetPay()
	{
		return netPay;
	}

	public void setNetPay(Long netPay)
	{
		this.netPay = netPay;
	}

	public String getReason()
	{
		return reason;
	}

	public void setReason(String reason)
	{
		this.reason = reason;
	}

	public String getRemarks()
	{
		return remarks;
	}

	public void setRemarks(String remarks)
	{
		this.remarks = remarks;
	}

	public List getAllowList()
	{
		return allowList;
	}

	public void setAllowList(List allowList)
	{
		this.allowList = allowList;
	}

	public List<BrokenPeriodPayCustomModel> getBrokenPeriodPayCustomModel() {
		return brokenPeriodPayCustomModel;
	}

	public void setBrokenPeriodPayCustomModel(List<BrokenPeriodPayCustomModel> brokenPeriodPayCustomModel) {
		this.brokenPeriodPayCustomModel = brokenPeriodPayCustomModel;
	}

	public List getDeductList()
	{
		return deductList;
	}

	public void setDeductList(List deductList)
	{
		this.deductList = deductList;
	}
	

	public String getBasicForCalculation() {
		return basicForCalculation;
	}

	public void setBasicForCalculation(String basicForCalculation) {
		this.basicForCalculation = basicForCalculation;
	}
	
	

	public Double getArrears() {
		return arrears;
	}

	public void setArrears(Double arrears) {
		this.arrears = arrears;
	}
	
	

	@Override
	public String toString() {
		return "BrokenPeriodPayCustomModel [fromDate=" + fromDate + ", toDate=" + toDate + ", noOfDays=" + noOfDays
				+ ", basicPay=" + basicPay + ", netPay=" + netPay + ", reason=" + reason + ", remarks=" + remarks
				+ ", allowList=" + allowList + ", deductList=" + deductList + ", arrears=" + arrears
				+ ", basicForCalculation=" + basicForCalculation + "]";
	}

	

}
