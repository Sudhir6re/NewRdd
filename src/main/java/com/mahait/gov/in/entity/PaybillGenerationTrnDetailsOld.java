package com.mahait.gov.in.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "PAYBILL_GENERATION_TRN_DETAILS1", schema = "public")
public class PaybillGenerationTrnDetailsOld {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PAYBILL_GNT_TRN_DETAIL_ID")
	private Long paybillGenerationTrnDetailId;

	@Column(name = "PAYBILL_GENERATION_TRN_ID")
	private Long paybillGenerationTrnId;

	@Column(name = "SEVAARTH_ID")
	private String sevaarthId;

	@Column(name = "BASIC_PAY")
	private Double basicPay;

	@Column(name = "DED_ADJUST")
	private Double dedAdjust;

	@Column(name = "GROSS_ADJUST")
	private Double grossAdjust;

	@Column(name = "GROSS_TOTAL_AMT")
	private Double grossTotalAmt;

	@Column(name = "TOTAL_NET_AMT")
	private Double totalNetAmt;

	@Column(name = "GROSS_AMT")
	private Double grossAmt;

	@Column(name = "created_user_id")
	private Integer createdUserId;

	@Column(name = "CREATED_DATE")
	private Date createdDate;

	@Column(name = "IS_ACTIVE")
	private Double isActive;

	@Column(name = "DA")
	private Double da;

	@Column(name = "HRA")
	private Double hra;

	@Column(name = "HRA5th")
	private Double hra5th;

	@Column(name = "HRA6th")
	private Double hra6th;

	@Column(name = "PT")
	private Double pt;

	@Column(name = "NPS_EMPR_ALLOW")
	private Double npsEmployerAllow;

	@Column(name = "NPS_EMPR_DEDUCT")
	private Double npsEmployerDeduct;

	@Column(name = "NPS_EMP_CONTRI")
	private Double npsEmployeeContri;

	@Column(name = "DCPS")
	private Double dcps;

	@Column(name = "TOTAL_DEDUCTION")
	private Double totalDeduction;

	@Column(name = "GIS")
	private Double gis;

	@Column(name = "GPF_GRP_ABC")
	private Double gpfGrpABC;

	@Column(name = "GPF_GRP_D")
	private Double gpfGrpD;

	@Column(name = "TA")
	private Double ta;

	@Column(name = "TA5th")
	private Double ta5th;

	@Column(name = "TA6th")
	private Double ta6th;

	@Column(name = "ADD_PAY")
	private Double addPay;

	@Column(name = "SPECIAL_PAY")
	private Double specialPay;

	@Column(name = "PERSONAL_PAY")
	private Double personalPay;

	@Column(name = "CONTRI_PROV_FUND")
	private Double contriProvFund;

	@Column(name = "OTHER_DEDUCT_BY_TREASURY")
	private Double otherDeductByTreasury;

	@Column(name = "HBA_HOUSE_INT_AMT")
	private Double hbaHouseIntAmt;

	@Column(name = "HBA_HOUSE")
	private Double hbaHouse;

	@Column(name = "COMP_ADV")
	private Double computerAdv;

	@Column(name = "SERV_CHARG")
	private Double servCharge;

	@Column(name = "PERMANENT_TRAVEL_ALLOW")
	private Double permanentTravelAllow;

	@Column(name = "Hill_Station_Allow")
	private Double hillStatAllow;

	@Column(name = "FAMILY_PLAN_ALLOW")
	private Double familyPlanAllow;

	@Column(name = "NON_COMP_HRA")
	private Double nonCompHRA;

	@Column(name = "NON_PRACT_ALLOW")
	private Double nonPractAllow;

	@Column(name = "OTA")
	private Double OTA;

	@Column(name = "TRANS_ALLOW_ARR")
	private Double transAllowArr;

	@Column(name = "TRIBAL_ALLOW")
	private Double tribalAllow;

	@Column(name = "UNIFORM_ALLOW")
	private Double uniformAllow;

	@Column(name = "WA")
	private Double wa;

	@Column(name = "CONVEY_ALLOW")
	private Double conveyAllow;

	@Column(name = "RECURRING_DEP")
	private Double recurringDep;

	@Column(name = "COP_Bank")
	private Double copBank;

	@Column(name = "CREDIT_SOC")
	private Double creditSoc;

	@Column(name = "CO_HSG_SOC")
	private Double coHsgSoc;

	@Column(name = "DCPS_ARR")
	private Double dcpsArr;

	@Column(name = "PAYBILL_MONTH")
	private Integer paybillMonth;

	@Column(name = "PAYBILL_YEAR")
	private Integer paybillYear;

	@Column(name = "PAY_COMMISSION_CODE")
	private Long payCommissionCode;

	@Column(name = "seven_pc_level")
	private Long sevenPcLevel;

	@Column(name = "PAY_BAND")
	private Long pay_band;

	@Column(name = "DA_ARR")
	private Double daArr;

	@Column(name = "LIC")
	private Double LIC;

	@Column(name = "OTHER_REC")
	private Double otherRec;

	@Column(name = "OTHER_DEDUCT")
	private Double otherDeduct;

	@Column(name = "OTHER_ALLOW")
	private Double otherAllow;

	@Column(name = "SVN_PC_DA")
	private Double svnDA;

	@Column(name = "HRR")
	private Double hrr;

	@Column(name = "CLA")
	private Double cla;

	@Column(name = "DEARNESS_PAY")
	private Double dearnessPay;

	@Column(name = "DA_ON_TA")
	private Double daOnTA;

	@Column(name = "INCOME_TAX")
	private Double it;

	@Column(name = "DEDUCT_ADJ_TRY")
	private Double dedAdjTry;

	@Column(name = "DEDUCT_ADJ_AG")
	private Double dedAdjAg;

	@Column(name = "DEDUCT_ADJ_OTR")
	private Double dedAdjOtr;

	@Column(name = "OTHER_DED")
	private Double otherDed;

	@Column(name = "MISC")
	private Double misc;

	@Column(name = "GPF_ADV_GRP_ABC")
	private Double gpfAdvGrpAbc;

	@Column(name = "GPF_ADV_GRP_D")
	private Double gpfAdvGrpD;

	@Column(name = "GPF_ADVANCE")
	private Double gpfAdvance;

	@Column(name = "revenue_stamp")
	private Double revenueStamp;

	@Column(name = "Excess_payment")
	private Double excessPayment;
	@Column(name = "OTHER_VEH_ADV")
	private Double otherVehAdv;

	@Column(name = "FA")
	private Double festivalAdv;

	@Column(name = "Arrears")
	private Double arrears;

	@Column(name = "Deputation_Allow")
	private Double deputationAllow;

	@Column(name = "HBA")
	private Double hba;

	@Column(name = "GPF_Arrears")
	private Double gpfArrears;

	@Column(name = "Mantralaya_Bank")
	private Double mantralayaBank;

	@Column(name = "NPS")
	private Double nps;
	@Column(name = "Excess_Pay_Rec")
	private Double excessPayrec;

	@Column(name = "excess_pay_rec_int")
	private String excessPayrecint;

	@Column(name = "Flag_Day")
	private Double flagDay;

	@Column(name = "GPF_INST")
	private Double gpfInst;

	@Column(name = "FA_INST")
	private String faInst;

	@Column(name = "OTHER_VEH_ADV_INST")
	private String othrVehAdvInst;

	@Column(name = "HBA_HOUSE_INST")
	private String hbaHouseInst;

	@Column(name = "COMP_ADV_INST")
	private String compAdvInst;

	@Column(name = "GPF_ADVANCE_INST")
	private String gpfAdvInst;

	@Column(name = "PAY_FIXADV_Diff_INST")
	private String payFixAdvDiffInst;

	@Column(name = "GPF_Loan_REC")
	private Double gpfLoanRec;

	@Column(name = "Accidential_Policy")
	private Double accidentPolicy;

	@Column(name = "Recovery")
	private Double recovery;

	@Column(name = "sub_department_id")
	private Integer subDeptId;

	@Column(name = "desg_code")
	private Long desgCode;

	@Column(name = "GPF_Advance_II")
	private Double gpfAdvII;

	@Column(name = "GPF_Advance_II_Inst")
	private Double gpfAdvIIInst;

	@Column(name = "CON_STORE")
	private Double conStore;

	@Column(name = "MTR_CO_OP_SOC")
	private Double mtrCoOpSoc;

	@Column(name = "MIS")
	private Double mis;

	@Column(name = "ATS_Incentive_50")
	private Double atsInc50;

	@Column(name = "ATS_Incentive_30")
	private Double atsInc30;

	@Column(name = "Force1_Incentive_25")
	private Double force1Inc25;

	@Column(name = "Force1_Incentive_100")
	private Double force1Inc100;

	@Column(name = "PG_Allow")
	private Double pgAllow;

	@Column(name = "CPF_Contri")
	private Double cpfContri;

	@Column(name = "ta_Arrear")
	private Double taArr;

	@Column(name = "GPF_Arrears_deduc")
	private Double gpfArrDeduc;

	@Column(name = "HBA_For_Land")
	private Double hbaforLand;

	@Column(name = "Pay_adv")
	private Double payAdv;

	@Column(name = "TA_ADV")
	private Double taAdv;

	@Column(name = "MCA")
	private Double mca;

	@Column(name = "svn_pc_ta")
	private Double svnPcTa;

	@Column(name = "GPF_Rec")
	private Double gpfRec;

	@Column(name = "DCPS_SVNPC_Recovery")
	private Double dcpsRec;

	@Column(name = "OTHER_VEH_ADV_INT")
	private Double othrVehAdvInt;

	@Column(name = "Motor_Veh_Adv_Inst_Amt")
	private Double motorVehAdvInstAmt;

	@Column(name = "DCPS_REGULAR")
	private Double dcpsRegular;

	@Column(name = "DCPS_DELAYED")
	private Double dcpsDelayed;

	@Column(name = "DCPS_PAY_ARR")
	private Double dcpsPayArr;

	@Column(name = "DCPS_DA_ARR")
	private Double dcpsDaArr;

	@Column(name = "PLI")
	private Double pli;



	@Column(name = "HRA_Percentage")
	private Integer hraPercent;

	@Column(name = "DA_Percentage")
	private Integer daPercent;

	@Column(name = "bank_id")
	private Long bankId;

	@Column(name = "bank_branch_id")
	private Long bankBranchId;

	@Column(name = "bank_account_no")
	private Long bankAccNo;

}
