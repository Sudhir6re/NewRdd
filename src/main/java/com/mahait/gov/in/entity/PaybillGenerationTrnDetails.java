package com.mahait.gov.in.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "PAYBILL_GENERATION_TRN_DETAILS", schema = "public")
public class PaybillGenerationTrnDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PAYBILL_GNT_TRN_DETAIL_ID")
	private Long paybillGenerationTrnDetailId;

	@Column(name = "PAYBILL_GENERATION_TRN_ID")
	private Long paybillGenerationTrnId;

	@Column(name = "EMP_ID")
	private Long empId;

	@Column(name = "SPL_PAY")
	private Double splPay;

	@Column(name = "PO")
	private Double po;

	@Column(name = "D_PAY")
	private Double dPay;

	@Column(name = "DA")
	private Double da;

	@Column(name = "HRA")
	private Double hra;

	@Column(name = "CLA")
	private Double cla;

	@Column(name = "MA")
	private Double ma;

	@Column(name = "WA")
	private Double wa;

	@Column(name = "TRANS_ALL")
	private Double transAll;

	@Column(name = "PAY_RECOVER")
	private Double payRecover;

	@Column(name = "GROSS_AMT")
	private Double grossAmt;

	@Column(name = "IT")
	private Double it;

	@Column(name = "HRR")
	private Double hrr;

	@Column(name = "PLI")
	private Double pli;

	@Column(name = "PT")
	private Double pt;

	@Column(name = "HBA")
	private Double hba;

	@Column(name = "FAN_ADV")
	private Double fanAdv;

	@Column(name = "JEEP_R")
	private Double jeepR;

	@Column(name = "GPF_IV")
	private Double gpfIv;

	@Column(name = "TOTAL_DED")
	private Double totalDed;

	@Column(name = "NET_TOTAL")
	private Double netTotal;

	@Column(name = "CREATED_BY")
	private Long createdBy;

	@Column(name = "CREATED_BY_POST")
	private Long createdByPost;

	@Column(name = "CREATED_DATE")
	private Timestamp createdDate;

	@Column(name = "DB_ID")
	private Short dbId;

	@Column(name = "LOC_ID")
	private Long locId;

	@Column(name = "PER_PAY")
	private Double perPay;

	@Column(name = "PE")
	private Double pe;

	@Column(name = "OTHER_ALLOW")
	private Double otherAllow;

	@Column(name = "BONUS")
	private Double bonus;

	@Column(name = "SURCHARGE")
	private Double surcharge;

	@Column(name = "RENT_B")
	private Double rentB;

	@Column(name = "GPF_ADV")
	private Double gpfAdv;

	@Column(name = "MISC")
	private Double misc;

	@Column(name = "TRN_COUNTER")
	private Double trnCounter;

	@Column(name = "DP_GAZZETED")
	private Double dpGazzeted;

	/*
	 * @Column(name = "PAYBILL_GRP_ID",columnDefinition = "double default 0.0")
	 * private Long paybillGrpId;
	 */

	@Column(name = "GPF_IV_ADV")
	private Double gpfIvAdv;

	@Column(name = "POST_ID")
	private Long postId;

	@Column(name = "APPROVE_REJECT_DATE")
	private Timestamp approveRejectDate;

	@Column(name = "DCPS")
	private Integer dcps;

	@Column(name = "PSR_NO")
	private Double psrNo;

	@Column(name = "DA_GPF")
	private Double daGpf;

	@Column(name = "DA_GPFIV")
	private Double daGpfiv;

	@Column(name = "PAYBILL_MONTH")
	private Integer paybillMonth;

	@Column(name = "PAYBILL_YEAR")
	private Integer paybillYear;

	@Column(name = "OTHER_ID")
	private Double otherId;

	@Column(name = "OTHER_TRN_CNTR")
	private Double otherTrnCntr;

	@Column(name = "GPAY", columnDefinition = "double default 0.0")
	private Double gpay;

	@Column(name = "TECH_ALLOW")
	private Double techAllow;

	@Column(name = "HILLY_ALLOWANCE")
	private Double hillyAllowance;

	@Column(name = "ATS_INCENTIVE_30")
	private Double atsIncentive30;

	@Column(name = "ATS_INCENTIVE_50")
	private Double atsIncentive50;

	@Column(name = "PG_ALLOWANCE")
	private Double pgAllowance;

	@Column(name = "TAA")
	private Double taa;

	@Column(name = "FORCE_1_100")
	private Double force1100;

	@Column(name = "FORCE_1_25")
	private Double force125;

	@Column(name = "ARM_ALLOWANCE")
	private Double armAllowance;

	@Column(name = "ARMOURER")
	private Double armourer;

	@Column(name = "BMI")
	private Double bmi;

	@Column(name = "CASH_ALLOWANCE")
	private Double cashAllowance;

	@Column(name = "CID")
	private Double cid;

	@Column(name = "CONVEYANCE")
	private Double conveyance;

	@Column(name = "EMERGENCY_ALLOW")
	private Double emergencyAllow;

	@Column(name = "ESIS")
	private Double esis;

	@Column(name = "ELA")
	private Double ela;

	@Column(name = "FITNESS_ALLOW")
	private Double fitnessAllow;

	@Column(name = "GALLANTRY_AWARDS")
	private Double gallantryAwards;

	@Column(name = "KIT_MAINTENANCE")
	private Double kitMaintenance;

	@Column(name = "LISENCE_FEE")
	private Double lisenceFee;

	@Column(name = "MECHANICAL_ALLOW")
	private Double mechanicalAllow;

	@Column(name = "MEDICAL_EDUCATION_ALLOW")
	private Double medicalEducationAllow;

	@Column(name = "MESS_ALLOW")
	private Double messAllow;

	@Column(name = "NAXEL_AREA_ALLOW")
	private Double naxalAreaAllow;

	@Column(name = "NON_PRAC_ALLOW")
	private Double nonPracAllow;

	@Column(name = "SUMPTUARY")
	private Double sumptuary;

	@Column(name = "PROJECT_ALLOW")
	private Double projectAllow;

	@Column(name = "SDA")
	private Double sda;

	@Column(name = "ADD_PAY")
	private Double addPay;

	@Column(name = "UNIFORM_ALLOW")
	private Double uniformAllow;

	@Column(name = "FAMILY_PALNNING")
	private Double familyPalnning;

	@Column(name = "GIS")
	private Double gis;

	@Column(name = "CENTRAL_GIS")
	private Double centralGis;

	@Column(name = "GIS_IFS")
	private Double gisIfs;

	@Column(name = "GIS_IAS")
	private Double gisIas;

	@Column(name = "GIS_IPS")
	private Double gisIps;

	@Column(name = "GPF_IAS_OTHER", columnDefinition = "int default 0")
	private Integer gpfIasOther;

	@Column(name = "GPF_IAS", columnDefinition = "int default 0")
	private Integer gpfIas;

	@Column(name = "GPF_IPS", columnDefinition = "int default 0")
	private Integer gpfIps;

	@Column(name = "GPF_IFS", columnDefinition = "int default 0")
	private Integer gpfIfs;

	@Column(name = "GPF_GRP_ABC", columnDefinition = "int default 0")
	private Integer gpfGrpAbc;

	@Column(name = "GPF_GRP_D", columnDefinition = "int default 0")
	private Integer gpfGrpD;

	@Column(name = "SERVICE_CHARGE", columnDefinition = "int default 0")
	private Integer serviceCharge;

	@Column(name = "OTHER_DEDUCTION", columnDefinition = "int default 0")
	private Integer otherDeduction;

	@Column(name = "MAHA_STATE_LIFE_INSURANCE", columnDefinition = "int default 0")
	private Integer mahaStateLifeInsurance;

	@Column(name = "LTC")
	private Double ltc;

	@Column(name = "HBA_CONSTRUCTION", columnDefinition = "int default 0")
	private Integer hbaConstruction;

	@Column(name = "HBA_LAND", columnDefinition = "int default 0")
	private Integer hbaLand;

	@Column(name = "PAY_ADVANCE", columnDefinition = "int default 0")
	private Integer payAdvance;

	@Column(name = "FESTIVAL_ADVANCE", columnDefinition = "int default 0")
	private Integer festivalAdvance;

	@Column(name = "TRAVEL_ADVANCE", columnDefinition = "int default 0")
	private Integer travelAdvance;

	@Column(name = "GPF_ADV_GRP_ABC", columnDefinition = "int default 0")
	private Integer gpfAdvGrpAbc;

	@Column(name = "GPF_ADV_GRP_D", columnDefinition = "int default 0")
	private Integer gpfAdvGrpD;

	@Column(name = "MOTOR_CYCLE_ADV", columnDefinition = "int default 0")
	private Integer motorCycleAdv;

	@Column(name = "OTHER_VEH_ADV", columnDefinition = "int default 0")
	private Integer otherVehAdv;

	@Column(name = "COMPUTER_ADV", columnDefinition = "int default 0")
	private Integer computerAdv;

	@Column(name = "HBA_CONSTRUCTION_INT", columnDefinition = "int default 0")
	private Integer hbaConstructionInt;

	@Column(name = "HBA_LAND_INT", columnDefinition = "int default 0")
	private Integer hbaLandInt;

	@Column(name = "PAY_ADVANCE_INT", columnDefinition = "int default 0")
	private Integer payAdvanceInt;

	@Column(name = "TRAVEL_ADVANCE_INT", columnDefinition = "int default 0")
	private Integer travelAdvanceInt;

	@Column(name = "GPF_ADV_GRP_ABC_INT", columnDefinition = "int default 0")
	private Integer gpfAdvGrpAbcInt;

	@Column(name = "GPF_ADV_GRP_D_INT", columnDefinition = "int default 0")
	private Integer gpfAdvGrpDInt;

	@Column(name = "MOTOR_CYCLE_ADV_INT", columnDefinition = "int default 0")
	private Integer motorCycleAdvInt;

	@Column(name = "OTHER_VEH_ADV_INT", columnDefinition = "int default 0")
	private Integer otherVehAdvInt;

	@Column(name = "COMPUTER_ADV_INT", columnDefinition = "int default 0")
	private Integer computerAdvInt;

	@Column(name = "GIS_ZP")
	private Integer gisZp;

	@Column(name = "GPF_ABC_ARR_MR")
	private Integer gpfAbcArrMr;

	@Column(name = "GPF_D_ARR_MR")
	private Integer gpfDArrMr;

	@Column(name = "GPF_IAS_ARR_MR")
	private Integer gpfIasArrMr;

	@Column(name = "GPF_IFS_ARR_MR")
	private Integer gpfIfsArrMr;

	@Column(name = "GPF_IPS_ARR_MR")
	private Integer gpfIpsArrMr;

	@Column(name = "HRR_ARR")
	private Integer hrrArr;

	@Column(name = "JANJULGISARR")
	private Integer janjulgisarr;

	@Column(name = "OTHER_REC")
	private Integer otherRec;

	@Column(name = "PT_ARR")
	private Integer ptArr;

	@Column(name = "OTHER_DED_TRY")
	private Double otherDedTry;

	@Column(name = "OTHER_ADV_INT")
	private Double otherAdvInt;

	@Column(name = "MCA_LAND_INT")
	private Double mcaLandInt;

	@Column(name = "MCA_LAND")
	private Double mcaLand;

	@Column(name = "ADD_DA")
	private Integer addDa;

	@Column(name = "ADD_HRA")
	private Integer addHra;

	@Column(name = "DA_ARR")
	private Integer daArr;

	@Column(name = "TEMP_CLA_5THPAY")
	private Integer tempCla5thpay;

	@Column(name = "FRANKING_ALLOW")
	private Integer frankingAllow;

	@Column(name = "TEMP_HRA_5THPAY")
	private Integer tempHra5thpay;

	@Column(name = "LEAVE_TRAVEL_ASSISTANCE")
	private Integer leaveTravelAssistance;

	@Column(name = "MEDICAL_STUDY_ALLOW")
	private Integer medicalStudyAllow;

	@Column(name = "OTHER_ALLOWANCES")
	private Integer otherAllowances;

	@Column(name = "PERMANENT_TRAVELLING")
	private Integer permanentTravelling;

	@Column(name = "TEMP_TA_5THPAY")
	private Integer tempTa5thpay;

	@Column(name = "WASH_ALLOW")
	private Integer washAllow;

	@Column(name = "WRITER_PAY_ALLOW")
	private Double writerPayAllow;

	@Column(name = "TRIBAL_ALLOW")
	private Integer tribalAllow;

	@Column(name = "CO_HSG_SOC", columnDefinition = "int default 0")
	private Integer coHsgSoc;

	@Column(name = "CO_HSG_SOC_INT", columnDefinition = "int default 0")
	private Integer coHsgSocInt;

	@Column(name = "COMP_AIS", columnDefinition = "int default 0")
	private Integer compAis;

	@Column(name = "COMP_AIS_INT", columnDefinition = "int default 0")
	private Integer compAisInt;

	@Column(name = "EXC_PAYRC", columnDefinition = "int default 0")
	private Integer excPayrc;

	@Column(name = "GPF_OTHER_STATE", columnDefinition = "int default 0")
	private Integer gpfOtherState;

	@Column(name = "HBA_AIS", columnDefinition = "int default 0")
	private Integer hbaAis;

	@Column(name = "HBA_AIS_INT", columnDefinition = "int default 0")
	private Integer hbaAisInt;

	@Column(name = "HBA_HOUSE", columnDefinition = "int default 0")
	private Integer hbaHouse;

	@Column(name = "HBA_HOUSE_INT", columnDefinition = "int default 0")
	private Integer hbaHouseInt;

	@Column(name = "MCA_AIS", columnDefinition = "int default 0")
	private Integer mcaAis;

	@Column(name = "MCA_AIS_INT", columnDefinition = "int default 0")
	private Integer mcaAisInt;

	@Column(name = "OTHER_ADV", columnDefinition = "int default 0")
	private Integer otherAdv;

	@Column(name = "GPF_IAS_LOAN")
	private Integer gpfIasLoan;

	@Column(name = "DCPS_DELAY")
	private Double dcpsDelay;

	@Column(name = "DCPS_PAY")
	private Double dcpsPay;

	@Column(name = "DCPS_DA")
	private Double dcpsDa;

	@Column(name = "REFRESHMENT_ALLOW")
	private Double refreshmentAllow;

	@Column(name = "JANJULGIS")
	private Double janjulgis;

	@Column(name = "CDA")
	private Double cda;

	@Column(name = "CTA")
	private Double cta;

	@Column(name = "PEON_ALLOWANCE")
	private Double peonAllowance;

	@Column(name = "INCENTIVE_BDDS")
	private Double incentiveBdds;

	@Column(name = "RT_PILOT")
	private Double rtPilot;

	@Column(name = "CHPL_PILOT")
	private Double chplPilot;

	@Column(name = "KIT_PILOT")
	private Double kitPilot;

	@Column(name = "FLYING_PAY_PILOT")
	private Double flyingPayPilot;

	@Column(name = "INSTRUCTIONAL_PILOT")
	private Double instructionalPilot;

	@Column(name = "QUALIFICATION_PILOT")
	private Double qualificationPilot;

	@Column(name = "INSPECTION_PILOT")
	private Double inspectionPilot;

	@Column(name = "FLYING_ALLOW_PILOT")
	private Double flyingAllowPilot;

	@Column(name = "OUTFIT_PILOT")
	private Double outfitPilot;

	@Column(name = "MILITERY_PILOT")
	private Double militeryPilot;

	@Column(name = "SPECIAL_PAY_PILOT")
	private Double specialPayPilot;

	@Column(name = "CPF")
	private Double cpf;

	@Column(name = "EMP_LNAME", length = 25)
	private String empLname;

	@Column(name = "BASIC_ARR")
	private Double basicArr;

	@Column(name = "DA_ON_TA", columnDefinition = "double default 0.0")
	private Double daOnTa;

	@Column(name = "SCALE_ID", columnDefinition = "double default 0.0")
	private Long scaleId;

	@Column(name = "TRANS_ARREAR", columnDefinition = "double default 0.0")
	private Double transArrear;

	@Column(name = "OVERTIME_ALLOW", columnDefinition = "double default 0.0")
	private Double overtimeAllow;

	@Column(name = "CPF_CONTRIBUTION", columnDefinition = "double default 0.0")
	private Double cpfContribution;

	@Column(name = "CPF_EMPLOYEE", columnDefinition = "double default 0.0")
	private Double cpfEmployee;

	@Column(name = "CPF_EMPLOYER", columnDefinition = "double default 0.0")
	private Double cpfEmployer;

	@Column(name = "ACC_POLICY", columnDefinition = "double default 0.0")
	private Double accPolicy;

	@Column(name = "SVNPC_DA", columnDefinition = "double default 0.0")
	private Double svnpcDa;

	@Column(name = "GROSS_NEW", columnDefinition = "double default 0.0")
	private Double grossNew;

	@Column(name = "TOTAL_DED_NEW", columnDefinition = "double default 0.0")
	private Double totalDedNew;

	@Column(name = "GROSS_SAL", columnDefinition = "double default 0.0")
	private Double grossSal;

	@Column(name = "SVNPC_TA", columnDefinition = "double default 0.0")
	private Double svnpcTa;

	@Column(name = "SVNPC_GPF_ARR", columnDefinition = "double default 0.0")
	private Double svnpcGpfArr;

	@Column(name = "SVNPC_DCPS_ARR", columnDefinition = "double default 0.0")
	private Double svnpcDcpsArr;

	@Column(name = "SVNPC_TA_ARR", columnDefinition = "double default 0.0")
	private Double svnpcTaArr;

	@Column(name = "SVNPC_GPF_ARR_DEDU", columnDefinition = "double default 0.0")
	private Double svnpcGpfArrDedu;

	@Column(name = "SVNPC_GPF_RECO", columnDefinition = "double default 0.0")
	private Double svnpcGpfReco;

	@Column(name = "SVNPC_DCPS_RECO", columnDefinition = "double default 0.0")
	private Double svnpcDcpsReco;

	@Column(name = "NPS_EMPLR", columnDefinition = "double default 0.0")
	private Double npsEmplr;

	@Column(name = "NPS_EMPLR_CONTRI_DED", columnDefinition = "double default 0.0")
	private Double npsEmplrContriDed;

	@Column(name = "REVENUE_STAMP", columnDefinition = "double default 0.0")
	private Double revenueStamp;

	@Column(name = "desg_code")
	private Long desgCode;

	@Column(name = "sevaarth_id")
	private String sevaarthId;

	@Column(name = "bank_account_no")
	private String bankAccNo;

	@Column(name = "bank_id")
	private Long bankId;

	@Column(name = "bank_branch_id")
	private Long bankBranchId;

	@Column(name = "basic_pay")
	private Double basicPay;

	@Column(name = "pay_commission_code")
	private Long payCommissionCode;

	@Column(name = "seven_pc_lvl")
	private Long SevenPcLevel;

	@Column(name = "pay_band")
	private Long payBand;

	@Column(name = "deduc_adj_ag")
	private Double dedAdjAg;

	@Column(name = "DEDUCT_ADJ_OTR")
	private Double dedAdjOtr;

	@Column(name = "lic")
	private Double lic;

	@Column(name = "COP_Bank")
	private Double copBank;

	@Column(name = "Recurring_deposite")
	private Double reccDeposit;

	@Column(name = "credit_soc")
	private Double creditSoc;

	@Column(name = "payslip_deduction")
	private Double payslipDeduc;

	@Column(name = "payslip_net")
	private Double payslipNet;

	@Column(name = "payslip_total_deduction")
	private Double paysliptotalDeduc;

	// Non-govrrn

	@Column(name = "con_store")
	private Double conStore;

	@Column(name = "mantralaya_bank")
	private Double mantralayaBank;

	@Column(name = "mis")
	private Double mis;

	@Column(name = "mrt_cop_soc")
	private Double mrtcopSoc;

	@Column(name = "other_Deduc")
	private Double otherDeduc;

	@Column(name = "other_Recovery")
	private Double otherRecovery;

	@Column(name = "DA_PERCENT")
	private Integer daPercent;

	@Column(name = "HRA_PERCENT")
	private Integer hraPercent;

	@Column(name = "remark")
	private String remark;

}
