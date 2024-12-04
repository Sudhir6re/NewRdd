
package com.mahait.gov.in.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PayslipReportModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String empCode;
	private String empFullNameEng;
	private String empLastNameEng;
	private String designationName;
	private int BankCode;
	private String departmentName;
	private BigDecimal esic;
	private BigDecimal Deductions_Other;
	
	private String bankAccNo;
	private String doj;
	private Date salarydate;
	private String gpf;
    private String schemeBillgroupId;
	private String ddoCode;
	
	private int empClass;
	private String panNo;
	
	private BigInteger uidNo;
	private String deptalldetNm;  // edp desc
	private String installment;  // edp desc
	private int type;
	private int deptallowdeducid;
	private String tempvalue; // insted of subdetail head and headofaccountcode
	private String tempempty;

	
	private int payBillGenerationTrnDtlId;
	private int payBillGenerationTrnId;
	private String sevaarthId;
	private int billgorupid;
	private String billgourp;
	
	/*private int payBillMonth;
	private int payBillYear;*/
	
	
	
	
	private BigDecimal basicPay;
	
	private BigDecimal da;
	private BigDecimal hra;
	private BigDecimal cla;
	private BigDecimal pt;
	private BigDecimal gis;
	private BigDecimal ta;
	private BigDecimal it;
	private BigDecimal hrr;
	private BigDecimal pli;
	private BigDecimal gpfAdv;
	private BigDecimal lta;
	private BigDecimal uniformAllow;
	private BigDecimal specialPay;
	private BigDecimal specialAllowance;
	private BigDecimal foodMealAllowance;
	private BigDecimal cityAllowance;
	private BigDecimal performancePay;
	private BigDecimal personalAllowance;
	private BigDecimal deputationAllowance;
	private BigDecimal salaryArrears;
	private BigDecimal grossSalary;
	private BigDecimal providentFund;
	private BigDecimal tds;
	private BigDecimal salaryAdvance;
	private BigDecimal hraRecovery;
	private BigDecimal totalDeduction;
	private BigDecimal grossAmt;
	private BigDecimal netPay;
	private BigDecimal variablePay;
	private BigDecimal telephoneAllowances;
	private BigDecimal allowanceOther;
	private BigDecimal peon;
	private BigDecimal arrears;
	private BigDecimal hrar;
	private int totalPrsentDays;
	private int totalDays;
	private int totalAbsentDays;
	
	
	private BigDecimal totalAllowanceForMonth;
	
	private BigDecimal totalDeductionForMonth;
	
	private BigDecimal totalAllowanceForYear;
	private BigDecimal totalDeductionForYear;
	
	
	private String emailId;
	private int employeeId;
	private String username;
    private int userId;
    List<PayslipReportModel> lstPayslipModel;
    private boolean checkboxid;
    private BigDecimal incomeTax; 
    private BigDecimal hba;
    private BigDecimal gpfIvAdv;
    private BigDecimal gPay;
    private BigDecimal dcps;
    private BigDecimal motarCycleAdv;
    private BigDecimal JanJulGis;
    private BigDecimal grossAdjust;
    private Double CreatedUserId;
    private String createdDate;
    private int isActive;
    private String approverejectDate;
    private double dcpsDaArr;
    private BigDecimal revenueStamp;
    private BigDecimal ComputerAdv;
    private BigDecimal festivalAdvance;
    private BigDecimal familyPlanAllow;
    private double servCharge;
    private double specialDutyAllow;
    private double paybillGrpId;
    private double paybillId;
    private Integer isType;

	public Integer getIsType() {
		return isType;
	}
	public void setIsType(Integer isType) {
		this.isType = isType;
	}


	private String componentname;
	private Double componentValue;
    
    
    private List lstdedectionag = new ArrayList();
    public List getLstdedectionag() {
		return lstdedectionag;
	}
	public void setLstdedectionag(List lstdedectionag) {
		this.lstdedectionag = lstdedectionag;
	}
	public void setSevaarthId(String sevaarthId) {
		this.sevaarthId = sevaarthId;
	}
	public BigDecimal getHba() {
		return hba;
	}
	public void setHba(BigDecimal hba) {
		this.hba = hba;
	}
	public BigDecimal getIncomeTax() {
		return incomeTax;
	}
	public void setIncomeTax(BigDecimal incomeTax) {
		this.incomeTax = incomeTax;
	}
	
    
    public List<PayslipReportModel> getLstPayslipModel() {
		return lstPayslipModel;
	}
	public void setLstPayslipModel(List<PayslipReportModel> lstPayslipModel) {
		this.lstPayslipModel = lstPayslipModel;
	}
	public boolean isCheckboxid() {
		return checkboxid;
	}
	public void setCheckboxid(boolean checkboxid) {
		this.checkboxid = checkboxid;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getBankAccNo() {
		return bankAccNo;
	}
	public void setBankAccNo(String bankAccNo) {
		this.bankAccNo = bankAccNo;
	}
	public int getTotalDays() {
		return totalDays;
	}
	public void setTotalDays(int totalDays) {
		this.totalDays = totalDays;
	}
	public int getTotalAbsentDays() {
		return totalAbsentDays;
	}
	public void setTotalAbsentDays(int totalAbsentDays) {
		this.totalAbsentDays = totalAbsentDays;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public String getEmpFullNameEng() {
		return empFullNameEng;
	}
	public void setEmpFullNameEng(String empFullNameEng) {
		this.empFullNameEng = empFullNameEng;
	}
	public String getEmpLastNameEng() {
		return empLastNameEng;
	}
	public void setEmpLastNameEng(String empLastNameEng) {
		this.empLastNameEng = empLastNameEng;
	}
	public String getDesignationName() {
		return designationName;
	}
	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}
	

	
	public String getDoj() {
		return doj;
	}
	public void setDoj(String doj) {
		this.doj = doj;
	}
	public int getEmpClass() {
		return empClass;
	}
	public void setEmpClass(int empClass) {
		this.empClass = empClass;
	}
	public String getPanNo() {
		return panNo;
	}
	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}
	public BigInteger getUidNo() {
		return uidNo;
	}
	public void setUidNo(BigInteger uidNo) {
		this.uidNo = uidNo;
	}
	public int getPayBillGenerationTrnDtlId() {
		return payBillGenerationTrnDtlId;
	}
	public void setPayBillGenerationTrnDtlId(int payBillGenerationTrnDtlId) {
		this.payBillGenerationTrnDtlId = payBillGenerationTrnDtlId;
	}
	public int getPayBillGenerationTrnId() {
		return payBillGenerationTrnId;
	}
	public void setPayBillGenerationTrnId(int payBillGenerationTrnId) {
		this.payBillGenerationTrnId = payBillGenerationTrnId;
	}
	
	
	public BigDecimal getBasicPay() {
		return basicPay;
	}
	public void setBasicPay(BigDecimal basicPay) {
		this.basicPay = basicPay;
	}
	public BigDecimal getDa() {
		return da;
	}
	public void setDa(BigDecimal da) {
		this.da = da;
	}
	public BigDecimal getHra() {
		return hra;
	}
	public void setHra(BigDecimal hra) {
		this.hra = hra;
	}
	public BigDecimal getCla() {
		return cla;
	}
	public void setCla(BigDecimal cla) {
		this.cla = cla;
	}
	public BigDecimal getPt() {
		return pt;
	}
	public void setPt(BigDecimal pt) {
		this.pt = pt;
	}
	public BigDecimal getGis() {
		return gis;
	}
	public void setGis(BigDecimal gis) {
		this.gis = gis;
	}
	public BigDecimal getTa() {
		return ta;
	}
	public void setTa(BigDecimal ta) {
		this.ta = ta;
	}
	public BigDecimal getIt() {
		return it;
	}
	public void setIt(BigDecimal it) {
		this.it = it;
	}
	public BigDecimal getHrr() {
		return hrr;
	}
	public void setHrr(BigDecimal hrr) {
		this.hrr = hrr;
	}
	public BigDecimal getPli() {
		return pli;
	}
	public void setPli(BigDecimal pli) {
		this.pli = pli;
	}
	
	public BigDecimal getUniformAllow() {
		return uniformAllow;
	}
	public void setUniformAllow(BigDecimal uniformAllow) {
		this.uniformAllow = uniformAllow;
	}
	public BigDecimal getSpecialPay() {
		return specialPay;
	}
	public void setSpecilPay(BigDecimal specialPay) {
		this.specialPay = specialPay;
	}
	public BigDecimal getFoodMealAllowance() {
		return foodMealAllowance;
	}
	public void setFoodMealAllowance(BigDecimal foodMealAllowance) {
		this.foodMealAllowance = foodMealAllowance;
	}
	public BigDecimal getCityAllowance() {
		return cityAllowance;
	}
	public void setCityAllowance(BigDecimal cityAllowance) {
		this.cityAllowance = cityAllowance;
	}
	public BigDecimal getPerformancePay() {
		return performancePay;
	}
	public void setPerformancePay(BigDecimal performancePay) {
		this.performancePay = performancePay;
	}
	public BigDecimal getPersonalAllowance() {
		return personalAllowance;
	}
	public void setPersonalAllowance(BigDecimal personalAllowance) {
		this.personalAllowance = personalAllowance;
	}
	public BigDecimal getDeputationAllowance() {
		return deputationAllowance;
	}
	public void setDeputationAllowance(BigDecimal deputationAllowance) {
		this.deputationAllowance = deputationAllowance;
	}
	public BigDecimal getSalaryArrears() {
		return salaryArrears;
	}
	public void setSalaryArrears(BigDecimal salaryArrears) {
		this.salaryArrears = salaryArrears;
	}
	public BigDecimal getGrossSalary() {
		return grossSalary;
	}
	public void setGrossSalary(BigDecimal grossSalary) {
		this.grossSalary = grossSalary;
	}
	public BigDecimal getProvidentFund() {
		return providentFund;
	}
	public void setProvidentFund(BigDecimal providentFund) {
		this.providentFund = providentFund;
	}
	public BigDecimal getTds() {
		return tds;
	}
	public void setTds(BigDecimal tds) {
		this.tds = tds;
	}
	public BigDecimal getSalaryAdvance() {
		return salaryAdvance;
	}
	public void setSalaryAdvance(BigDecimal salaryAdvance) {
		this.salaryAdvance = salaryAdvance;
	}
	public BigDecimal getHraRecovery() {
		return hraRecovery;
	}
	public void setHraRecovery(BigDecimal hraRecovery) {
		this.hraRecovery = hraRecovery;
	}
	public BigDecimal getTotalDeduction() {
		return totalDeduction;
	}
	public void setTotalDeduction(BigDecimal totalDeduction) {
		this.totalDeduction = totalDeduction;
	}
	public BigDecimal getGrossAmt() {
		return grossAmt;
	}
	public void setGrossAmt(BigDecimal grossAmt) {
		this.grossAmt = grossAmt;
	}
	public BigDecimal getNetPay() {
		return netPay;
	}
	public void setNetPay(BigDecimal netPay) {
		this.netPay = netPay;
	}
	public BigDecimal getVariablePay() {
		return variablePay;
	}
	public void setVariablePay(BigDecimal variablePay) {
		this.variablePay = variablePay;
	}
	public int getTotalPrsentDays() {
		return totalPrsentDays;
	}
	public void setTotalPrsentDays(int totalPrsentDays) {
		this.totalPrsentDays = totalPrsentDays;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public BigDecimal getTotalAllowanceForMonth() {
		return totalAllowanceForMonth;
	}
	public void setTotalAllowanceForMonth(BigDecimal totalAllowanceForMonth) {
		this.totalAllowanceForMonth = totalAllowanceForMonth;
	}
	public BigDecimal getTotalDeductionForMonth() {
		return totalDeductionForMonth;
	}
	public void setTotalDeductionForMonth(BigDecimal totalDeductionForMonth) {
		this.totalDeductionForMonth = totalDeductionForMonth;
	}
	public BigDecimal getTotalAllowanceForYear() {
		return totalAllowanceForYear;
	}
	public void setTotalAllowanceForYear(BigDecimal totalAllowanceForYear) {
		this.totalAllowanceForYear = totalAllowanceForYear;
	}
	public BigDecimal getTotalDeductionForYear() {
		return totalDeductionForYear;
	}
	public void setTotalDeductionForYear(BigDecimal totalDeductionForYear) {
		this.totalDeductionForYear = totalDeductionForYear;
	}
	public String getDeptalldetNm() {
		return deptalldetNm;
	}
	public void setDeptalldetNm(String deptalldetNm) {
		this.deptalldetNm = deptalldetNm;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getDeptallowdeducid() {
		return deptallowdeducid;
	}
	public void setDeptallowdeducid(int deptallowdeducid) {
		this.deptallowdeducid = deptallowdeducid;
	}
	public String getTempvalue() {
		return tempvalue;
	}
	public void setTempvalue(String tempvalue) {
		this.tempvalue = tempvalue;
	}
	public String getTempempty() {
		return tempempty;
	}
	public void setTempempty(String tempempty) {
		this.tempempty = tempempty;
	}
	
	public BigDecimal getEsic() {
		return esic;
	}
	public void setEsic(BigDecimal esic) {
		this.esic = esic;
	}
	public BigDecimal getDeductions_Other() {
		return Deductions_Other;
	}
	public void setDeductions_Other(BigDecimal deductions_Other) {
		Deductions_Other = deductions_Other;
	}
	public BigDecimal getLta() {
		return lta;
	}
	public void setLta(BigDecimal lta) {
		this.lta = lta;
	}
	public BigDecimal getSpecialAllowance() {
		return specialAllowance;
	}
	public void setSpecialAllowance(BigDecimal specialAllowance) {
		this.specialAllowance = specialAllowance;
	}
	public BigDecimal getTelephoneAllowances() {
		return telephoneAllowances;
	}
	public void setTelephoneAllowances(BigDecimal telephoneAllowances) {
		this.telephoneAllowances = telephoneAllowances;
	}
	public BigDecimal getAllowanceOther() {
		return allowanceOther;
	}
	public void setAllowanceOther(BigDecimal allowanceOther) {
		this.allowanceOther = allowanceOther;
	}

	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getSalarydate() {
		return salarydate;
	}
	public void setSalarydate(Date date) {
		this.salarydate = date;
	}
	public String getGpf() {
		return gpf;
	}
	public void setGpf(String gpf) {
		this.gpf = gpf;
	}
	public BigDecimal getPeon() {
		return peon;
	}
	public void setPeon(BigDecimal peon) {
		this.peon = peon;
	}
	public BigDecimal getArrears() {
		return arrears;
	}
	public void setArrears(BigDecimal arrears) {
		this.arrears = arrears;
	}
	public BigDecimal getHrar() {
		return hrar;
	}
	public void setHrar(BigDecimal hrar) {
		this.hrar = hrar;
	}
	public String getSevaarthId() {
		return sevaarthId;
	}
	public void setSevaarthID(String sevaarthID) {
		sevaarthID = sevaarthId;
	}
	
	
	private Double dcpsEmployer;
	
	private Double adjustDcpsEmployer;
	private Double dcpsDelay;
	private Double dcpsDa;
	private Double dcpsPayArr;
	private Double dcpsRegularRecovery;
	private Double gpfGrpABC;
	private BigDecimal gpfGrpD;
	private Double addPay;
	private Double personalPay;
	
	private Double contriProvFund;
	private BigDecimal otherDeductByTreasury;
	private Double hbaHouseInt;
	private Double hbaHouse;
	private Double permanentTravelAllow;
	private Double hillyAllow;
	
	private Double nonPractAllow;
	private Double overTime;
	
	private Double transAllowArr;
	private Double tribalAllow;
	private Double washAllow;
	
	private Double gisZp;
	private Double gpfAbcArr;
	
	private Double gpfDArr;
	private Double conveyAllow;
	private Double groupAccPolicy;
	private Double recurringDep;
	private Double copBank;
	private Double creditSoc;
	private Double coHsgSoc;
	private Double basicArr;
	private Double dcpsArr;
	private Integer paybillMonth;
	private Integer paybillYear;
	private String pay_commision;
	private String pay_band;
	private Double daArr;
	private Double addHRA;
	private Double naxalAreaAllow;
	private Double LIC;
	private Double otherRec;
	private Double ptArr;
	private Double otherDeduct;
	private Double otherAllow;
	private BigDecimal svnDA;
	private Double spclDutyAllow;
	private Double dearnessPay;
	private Double daOnTA;
	private BigDecimal dedAdjTry;
	private BigDecimal dedAdjAg;
	private BigDecimal dedAdjOtr;
	private Double otherDed;
	private Double misc;
	private Double gpfAdvGrpAbc;
	
	private Double gpfAdvGrpD;
	
	
    private Double excPayRc;

	public Double getExcPayRc() {
		return excPayRc;
	}

	public void setExcPayRc(Double excPayRc) {
		this.excPayRc = excPayRc;
	}

	public Double getGpfAdvGrpD() {
		return gpfAdvGrpD;
	}

	public void setGpfAdvGrpD(Double gpfAdvGrpD) {
		this.gpfAdvGrpD = gpfAdvGrpD;
	}

	public Double getGpfAdvGrpAbc() {
		return gpfAdvGrpAbc;
	}

	public void setGpfAdvGrpAbc(Double gpfAdvGrpAbc) {
		this.gpfAdvGrpAbc = gpfAdvGrpAbc;
	}

	public Double getMisc() {
		return misc;
	}

	public void setMisc(Double misc) {
		this.misc = misc;
	}

	public Double getOtherDed() {
		return otherDed;
	}

	public void setOtherDed(Double otherDed) {
		this.otherDed = otherDed;
	}

	
	


	public Double getDcpsEmployer() {
		return dcpsEmployer;
	}

	public void setDcpsEmployer(Double dcpsEmployer) {
		this.dcpsEmployer = dcpsEmployer;
	}

	public Double getAdjustDcpsEmployer() {
		return adjustDcpsEmployer;
	}

	public void setAdjustDcpsEmployer(Double adjustDcpsEmployer) {
		this.adjustDcpsEmployer = adjustDcpsEmployer;
	}

	

	public Double getDcpsDelay() {
		return dcpsDelay;
	}

	public void setDcpsDelay(Double dcpsDelay) {
		this.dcpsDelay = dcpsDelay;
	}

	public Double getDcpsDa() {
		return dcpsDa;
	}

	public void setDcpsDa(Double dcpsDa) {
		this.dcpsDa = dcpsDa;
	}

	public Double getDcpsPayArr() {
		return dcpsPayArr;
	}

	public void setDcpsPayArr(Double dcpsPayArr) {
		this.dcpsPayArr = dcpsPayArr;
	}

	public Double getDcpsRegularRecovery() {
		return dcpsRegularRecovery;
	}

	public void setDcpsRegularRecovery(Double dcpsRegularRecovery) {
		this.dcpsRegularRecovery = dcpsRegularRecovery;
	}

	
	public Double getGpfGrpABC() {
		return gpfGrpABC;
	}

	public void setGpfGrpABC(Double gpfGrpABC) {
		this.gpfGrpABC = gpfGrpABC;
	}

	public BigDecimal getGpfGrpD() {
		return gpfGrpD;
	}

	public void setGpfGrpD(BigDecimal gpfGrpD) {
		this.gpfGrpD = gpfGrpD;
	}

	public Double getAddPay() {
		return addPay;
	}

	public void setAddPay(Double addPay) {
		this.addPay = addPay;
	}

	

	public Double getPersonalPay() {
		return personalPay;
	}

	public void setPersonalPay(Double personalPay) {
		this.personalPay = personalPay;
	}

	public Double getContriProvFund() {
		return contriProvFund;
	}

	public void setContriProvFund(Double contriProvFund) {
		this.contriProvFund = contriProvFund;
	}

	public BigDecimal getOtherDeductByTreasury() {
		return otherDeductByTreasury;
	}

	public void setOtherDeductByTreasury(BigDecimal otherDeductByTreasury) {
		this.otherDeductByTreasury = otherDeductByTreasury;
	}

	public Double getHbaHouseInt() {
		return hbaHouseInt;
	}

	public void setHbaHouseInt(Double hbaHouseInt) {
		this.hbaHouseInt = hbaHouseInt;
	}

	public Double getHbaHouse() {
		return hbaHouse;
	}

	public void setHbaHouse(Double hbaHouse) {
		this.hbaHouse = hbaHouse;
	}


	public Double getPermanentTravelAllow() {
		return permanentTravelAllow;
	}

	public void setPermanentTravelAllow(Double permanentTravelAllow) {
		this.permanentTravelAllow = permanentTravelAllow;
	}

	public Double getHillyAllow() {
		return hillyAllow;
	}

	public void setHillyAllow(Double hillyAllow) {
		this.hillyAllow = hillyAllow;
	}


	public Double getNonPractAllow() {
		return nonPractAllow;
	}

	public void setNonPractAllow(Double nonPractAllow) {
		this.nonPractAllow = nonPractAllow;
	}

	public Double getOverTime() {
		return overTime;
	}

	public void setOverTime(Double overTime) {
		this.overTime = overTime;
	}

	public Double getTransAllowArr() {
		return transAllowArr;
	}

	public void setTransAllowArr(Double transAllowArr) {
		this.transAllowArr = transAllowArr;
	}

	public Double getTribalAllow() {
		return tribalAllow;
	}

	public void setTribalAllow(Double tribalAllow) {
		this.tribalAllow = tribalAllow;
	}

	public Double getWashAllow() {
		return washAllow;
	}

	public void setWashAllow(Double washAllow) {
		this.washAllow = washAllow;
	}

	public Double getGisZp() {
		return gisZp;
	}

	public void setGisZp(Double gisZp) {
		this.gisZp = gisZp;
	}

	public Double getGpfAbcArr() {
		return gpfAbcArr;
	}

	public void setGpfAbcArr(Double gpfAbcArr) {
		this.gpfAbcArr = gpfAbcArr;
	}

	public Double getGpfDArr() {
		return gpfDArr;
	}

	public void setGpfDArr(Double gpfDArr) {
		this.gpfDArr = gpfDArr;
	}

	public Double getConveyAllow() {
		return conveyAllow;
	}

	public void setConveyAllow(Double conveyAllow) {
		this.conveyAllow = conveyAllow;
	}

	public Double getGroupAccPolicy() {
		return groupAccPolicy;
	}

	public void setGroupAccPolicy(Double groupAccPolicy) {
		this.groupAccPolicy = groupAccPolicy;
	}

	public Double getRecurringDep() {
		return recurringDep;
	}

	public void setRecurringDep(Double recurringDep) {
		this.recurringDep = recurringDep;
	}

	public Double getCopBank() {
		return copBank;
	}

	public void setCopBank(Double copBank) {
		this.copBank = copBank;
	}

	public Double getCreditSoc() {
		return creditSoc;
	}

	public void setCreditSoc(Double creditSoc) {
		this.creditSoc = creditSoc;
	}

	public Double getCoHsgSoc() {
		return coHsgSoc;
	}

	public void setCoHsgSoc(Double coHsgSoc) {
		this.coHsgSoc = coHsgSoc;
	}

	public Double getBasicArr() {
		return basicArr;
	}

	public void setBasicArr(Double basicArr) {
		this.basicArr = basicArr;
	}

	public Double getDcpsArr() {
		return dcpsArr;
	}

	public void setDcpsArr(Double dcpsArr) {
		this.dcpsArr = dcpsArr;
	}

	public Integer getPaybillMonth() {
		return paybillMonth;
	}

	public void setPaybillMonth(Integer paybillMonth) {
		this.paybillMonth = paybillMonth;
	}

	public Integer getPaybillYear() {
		return paybillYear;
	}

	public void setPaybillYear(Integer paybillYear) {
		this.paybillYear = paybillYear;
	}

	public String getPay_commision() {
		return pay_commision;
	}

	public void setPay_commision(String pay_commision) {
		this.pay_commision = pay_commision;
	}

	public String getPay_band() {
		return pay_band;
	}

	public void setPay_band(String pay_band) {
		this.pay_band = pay_band;
	}

	public Double getDaArr() {
		return daArr;
	}

	public void setDaArr(Double daArr) {
		this.daArr = daArr;
	}

	public Double getAddHRA() {
		return addHRA;
	}

	public void setAddHRA(Double addHRA) {
		this.addHRA = addHRA;
	}

	public Double getNaxalAreaAllow() {
		return naxalAreaAllow;
	}

	public void setNaxalAreaAllow(Double naxalAreaAllow) {
		this.naxalAreaAllow = naxalAreaAllow;
	}

	public Double getLIC() {
		return LIC;
	}

	public void setLIC(Double lIC) {
		LIC = lIC;
	}

	public Double getOtherRec() {
		return otherRec;
	}

	public void setOtherRec(Double otherRec) {
		this.otherRec = otherRec;
	}

	public Double getPtArr() {
		return ptArr;
	}

	public void setPtArr(Double ptArr) {
		this.ptArr = ptArr;
	}

	public Double getOtherDeduct() {
		return otherDeduct;
	}

	public void setOtherDeduct(Double otherDeduct) {
		this.otherDeduct = otherDeduct;
	}

	public Double getOtherAllow() {
		return otherAllow;
	}

	public void setOtherAllow(Double otherAllow) {
		this.otherAllow = otherAllow;
	}

	public BigDecimal getSvnDA() {
		return svnDA;
	}

	public void setSvnDA(BigDecimal svnDA) {
		this.svnDA = svnDA;
	}

	public Double getSpclDutyAllow() {
		return spclDutyAllow;
	}

	public void setSpclDutyAllow(Double spclDutyAllow) {
		this.spclDutyAllow = spclDutyAllow;
	}


	public Double getDearnessPay() {
		return dearnessPay;
	}

	public void setDearnessPay(Double dearnessPay) {
		this.dearnessPay = dearnessPay;
	}

	public Double getDaOnTA() {
		return daOnTA;
	}

	public void setDaOnTA(Double daOnTA) {
		this.daOnTA = daOnTA;
	}

	public BigDecimal getDedAdjTry() {
		return dedAdjTry;
	}

	public void setDedAdjTry(BigDecimal dedAdjTry) {
		this.dedAdjTry = dedAdjTry;
	}

	public BigDecimal getDedAdjAg() {
		return dedAdjAg;
	}

	public void setDedAdjAg(BigDecimal dedAdjAg) {
		this.dedAdjAg = dedAdjAg;
	}

	public BigDecimal getDedAdjOtr() {
		return dedAdjOtr;
	}

	public void setDedAdjOtr(BigDecimal dedAdjOtr) {
		this.dedAdjOtr = dedAdjOtr;
	}
	public BigDecimal getGpfAdv() {
		return gpfAdv;
	}
	public void setGpfAdv(BigDecimal bigDecimal) {
		this.gpfAdv = bigDecimal;
	}
	public BigDecimal getGpfIvAdv() {
		return gpfIvAdv;
	}
	public void setGpfIvAdv(BigDecimal gpfIvAdv) {
		this.gpfIvAdv = gpfIvAdv;
	}
	public BigDecimal getgPay() {
		return gPay;
	}
	public void setgPay(BigDecimal gPay) {
		this.gPay = gPay;
	}
	public BigDecimal getDcps() {
		return dcps;
	}
	public void setDcps(BigDecimal dcps) {
		this.dcps = dcps;
	}
	public BigDecimal getMotarCycleAdv() {
		return motarCycleAdv;
	}
	public void setMotarCycleAdv(BigDecimal motarCycleAdv) {
		this.motarCycleAdv = motarCycleAdv;
	}
	public BigDecimal getJanJulGis() {
		return JanJulGis;
	}
	public void setJanJulGis(BigDecimal janJulGis) {
		JanJulGis = janJulGis;
	}
	public BigDecimal getGrossAdjust() {
		return grossAdjust;
	}
	public void setGrossAdjust(BigDecimal grossAdjust) {
		this.grossAdjust = grossAdjust;
	}
	public Double getCreatedUserId() {
		return CreatedUserId;
	}
	public void setCreatedUserId(Double createdUserId) {
		CreatedUserId = createdUserId;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	public String getApproverejectDate() {
		return approverejectDate;
	}
	public void setApproverejectDate(String approverejectDate) {
		this.approverejectDate = approverejectDate;
	}
	public double getDcpsDaArr() {
		return dcpsDaArr;
	}
	public void setDcpsDaArr(double dcpsDaArr) {
		this.dcpsDaArr = dcpsDaArr;
	}
	public BigDecimal getRevenueStamp() {
		return revenueStamp;
	}
	public void setRevenueStamp(BigDecimal revenueStamp) {
		this.revenueStamp = revenueStamp;
	}
	public BigDecimal getComputerAdv() {
		return ComputerAdv;
	}
	public void setComputerAdv(BigDecimal computerAdv) {
		ComputerAdv = computerAdv;
	}
	public BigDecimal getFestivalAdvance() {
		return festivalAdvance;
	}
	public void setFestivalAdvance(BigDecimal festivalAdvance) {
		this.festivalAdvance = festivalAdvance;
	}
	public BigDecimal getFamilyPlanAllow() {
		return familyPlanAllow;
	}
	public void setFamilyPlanAllow(BigDecimal familyPlanAllow) {
		this.familyPlanAllow = familyPlanAllow;
	}
	public double getServCharge() {
		return servCharge;
	}
	public void setServCharge(double servCharge) {
		this.servCharge = servCharge;
	}
	public double getSpecialDutyAllow() {
		return specialDutyAllow;
	}
	public void setSpecialDutyAllow(double specialDutyAllow) {
		this.specialDutyAllow = specialDutyAllow;
	}
	public double getPaybillGrpId() {
		return paybillGrpId;
	}
	public void setPaybillGrpId(double paybillGrpId) {
		this.paybillGrpId = paybillGrpId;
	}
	public double getPaybillId() {
		return paybillId;
	}
	public void setPaybillId(double paybillId) {
		this.paybillId = paybillId;
	}
	public int getBankCode() {
		return BankCode;
	}
	public void setBankCode(int bankCode) {
		BankCode = bankCode;
	}
	public String getSchemeBillgroupId() {
		return schemeBillgroupId;
	}
	public void setSchemeBillgroupId(String schemeBillgroupId) {
		this.schemeBillgroupId = schemeBillgroupId;
	}
	public String getDdoCode() {
		return ddoCode;
	}
	public void setDdoCode(String ddoCode) {
		this.ddoCode = ddoCode;
	}
	public String getComponentname() {
		return componentname;
	}
	public void setComponentname(String componentname) {
		this.componentname = componentname;
	}
	public Double getComponentValue() {
		return componentValue;
	}
	public void setComponentValue(Double componentValue) {
		this.componentValue = componentValue;
	}
	public void setSpecialPay(BigDecimal specialPay) {
		this.specialPay = specialPay;
	}
	public String getInstallment() {
		return installment;
	}
	public void setInstallment(String installment) {
		this.installment = installment;
	}
	public int getBillgorupid() {
		return billgorupid;
	}
	public void setBillgorupid(int billgorupid) {
		this.billgorupid = billgorupid;
	}
	public String getBillgourp() {
		return billgourp;
	}
	public void setBillgourp(String billgourp) {
		this.billgourp = billgourp;
	}
	
	
	
}
	
