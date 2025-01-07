package com.mahait.gov.in.common;

import com.mahait.gov.in.entity.OrgUserMst;

public class CommonConstants {

	public interface Message{
		String ADDED_ENGLSH = "Record added successfully !!! ";
		String ADDED_MARATHI = "रेकॉर्ड यशस्वीरित्या जोडले";
		String UPDATED_ENGLSH = "Record updated successfully !!! ";
		String UPDATED_MARATHI = "रेकॉर्ड यशस्वीरित्या अद्यतनित केले";
		String DELETE = "Record delete successfully";
		String INACTIVATED = "Record inactivated successfully !!! ";
		String REJECTED_ENGLSH = "Record rejected successfully !!! ";
		String REJECTED_MARATHI = "रेकॉर्ड यशस्वीरित्या नाकारला";
		String SWR = "Some Thing Went Wrong !!! ";
		String DD_CADRE_GRP ="DD_CADRE_GRP";
		String ALLREADYEXISTS_ENGLISH = "Same record is already present in database !!!";
		String ALLREADYEXISTS_MARATHI = "समान रेकॉर्ड आधीच डेटाबेसमध्ये सादर आहे";
		String SAVEDRAFT="All the details saved successfully";
		String FRWDDDO="Registration form is forwarded successfully";
		
		String DRAFTCASE="Employee details Draft Successfully";
		
		String FRWDCLK="Opening Balance Saved and Forwarded To Next Authority";
		
		String SAVEUPDATEATTACHDETTACH="Bill Group Modified Successfully";
		
		
	}
	
	public interface STATUS{
		String SUCCESS = "SUCCESS";
		String WARNING = "WARNING";
		String ERROR = "ERROR";
		String APPROVED = "APPROVED";
		String REJECTED = "REJECTED";
	}
	
	public interface DEFAULTPASSWORD{
		String DEFAULT_PASSWORD = "ifms123";
	}
	
	public interface LEVELS {
		String LEVEL1 = "DDO_USER_LEVEL1";
		String LEVEL2 = "DDO_USER_LEVEL2";
		String LEVEL3 = "DDO_USER_LEVEL3";
		String LEVEL4 = "DDO_USER_LEVEL4";
		String LEVEL8 = "Inward Level";
		String LEVEL9 = "Clerk Level";
		String LEVEL10 = "Ass Accountant Level";
		String LEVEL11 = "Superintendent";
		String LEVEL12 = "Pension_AST";
		String LEVEL13 = "AG";
		String LEVEL14 = "Inward Clerk";
		String LEVEL15 = "Pension Clerk";
		String LEVEL16 = "AUDITOR";
		String LEVEL17 = "ATO";
		String LEVEL18 = "Sr Clerk";
		String LEVEL19 = "Final Clerk";
		String LEVEL20 = "ROLE OS";
		String LEVEL21 = "ROLE SE";
		String LEVEL22 = "ROLE MS";
		String LEVEL23 = "ROLE JR";
		String LEVEL24 = "ROLE DO";
		String LEVEL25 = "ROLE AEO";
		String LEVEL26 = "CAO";
		String LEVEL29 = "Pen_FC";
		String LEVEL30 = "Pen_AAO";
		String LEVEL31 = "Pen_AO";
		String LEVEL32 = "Pen_SAO";
		String LEVEL33 = "Pen_DYCAO1";
		String LEVEL34 = "Pen_CASHIER";
	}
	public interface DDOUSERPWD {
		String PASSWORD = "Password@123";
		String PASSWORD1 = "ifms123";
	}
	public interface NUMBERS {
		String ZERO = "0";
		String ONE = "1";
		String TWO = "2";
		String THREE = "3";
		String FOUR = "4";
		String FIVE = "5";
	}
	public interface COMMONMSTTABLE{
		String COMMONCODE_STATUS = "STATUS";
		String COMMONCODE_SALUTATION = "SALUTATION";
		String COMMONCODE_GENDER = "GENDER";
		String LOAN_ADVANCE = "LNA";
		String GPFLOAN_ADVANCE = "GPFLOAN";
		String COMPUTER_ADVANCE = "CA";
		String FESTIVAL_ADVANCE = "FA";
		String PAYCOMMISION = "PAYCOMM";
		String VEHICLE_ADVANCE = "VA";
		String MOTOR_VEHICLE_ADVANCE = "MOTORVEHADV";
		String HOUSE_ADVANCE = "HBA";
		String EMPLOYEE_TYPE = "EMPLOYEE_TYPE";
		String BILL_TYPE = "PAYROLL_BILL_TYPE";
		String CADRE_GROUP = "CADRE_GROUP";
		String ADVANCE_PURPOSE = "PURPOSE_OF_WITHDRAWL";
		String DCPS_OFFICE_CLASS = "DCPS_OFFICE_CLASS";
		String COMMONCODE_GIS = "GIS";
		String PENSION_BILL = "PENSION_BILL";
		String EMPSERVICEENDDTREASONS = "EMP_SERVICE_END_DT_REASONS";
		String HEADOFOFFICECODE = "10001198220";
		String COMMONCODE_SALUTATIONS = "Salutation";
		String COMMONCODE_DCPSACCMAINTAINEDBY = "AccountMaintainedByForDCPSEmp";
		String COMMONCODE_GPFACCMAINTAINEDBY = "AccountMaintaindedBy";
		String COMMONCODE_GISAPPLICABLE = "GISDetails";
	}
	
	public interface PAYBILLDETAILS{
		
		// Group A B BnGz Values
		Double COMMONCODE_GROUP_A = (double) 960;
		Double COMMONCODE_GROUP_B = (double) 480;
		Double COMMONCODE_GROUP_BNGZ = (double) 480;
		Double COMMONCODE_GROUP_C = (double) 360;
		Double COMMONCODE_GROUP_D = (double) 240;
		
		// Group A B BnGz C D
		String COMMONCODE_GROUP_GROUP_A = "A";
		String COMMONCODE_GROUP_GROUP_B = "B";
		String COMMONCODE_GROUP_GROUP_BNGZ = "BnGz";
		String COMMONCODE_GROUP_GROUP_C = "C";
		String COMMONCODE_GROUP_GROUP_D = "D";
		
		// DA Rate Six And Seven 
		Double COMMONCODE_SEVENTH_PAY_DA = (double) 10;
		Double COMMONCODE_SIXTH_PAY_DA = (double) 9;
		
		//Added by Brijoy 13012021 PayCommission
		int COMMONCODE_PAYCOMMISSION_6PC = 700016;
		int COMMONCODE_PAYCOMMISSION_5PC = 700015;
		int COMMONCODE_PAYCOMMISSION_7PC = 700005;
		
		// City Wise X Y Z 
		String COMMONCODE_CITY_CLASS_X = "X";
		String COMMONCODE_CITY_CLASS_Y = "Y";
		String COMMONCODE_CITY_CLASS_Z = "Z";
		
		// All Component 
		String COMMONCODE_COMPONENT_DA = "DA";
		String COMMONCODE_COMPONENT_SVN_PC_DA = "SVNPC_DA";
		String COMMONCODE_COMPONENT_CLA = "CLA";
		String COMMONCODE_COMPONENT_DEARNESS_PAY = "D_PAY";
		String COMMONCODE_COMPONENT_FAMILY_PLAN_ALLOW = "FAMILY_PALNNING";
		String COMMONCODE_COMPONENT_TRANSPORT_ALLOWANCE = "TRANS_ALL";
		String COMMONCODE_COMPONENT_HRA = "HRA";
		String COMMONCODE_COMPONENT_PT = "PT";
		String COMMONCODE_COMPONENT_Accidential_Policy = "ACC_POLICY";
		String COMMONCODE_COMPONENT_GIS = "GIS";
		String COMMONCODE_COMPONENT_NPS_EMPR_ALLOW = "NPS_EMPLR";
		String COMMONCODE_COMPONENT_NPS_EMP_CONTRI = "NPS_EMPLR";
		String COMMONCODE_COMPONENT_NPS_EMPR_DEDUCT = "NPS_EMPLR_CONTRI_DED";
		String COMMONCODE_COMPONENT_SVN_DA = "SVNPC_DA";
		String COMMONCODE_COMPONENT_DCPS = "DCPS";
		
		String COMMONCODE_COMPONENT_DCPS_DELAY = "DCPS_DELAY";
		String COMMONCODE_COMPONENT_DCPS_PAY = "DCPS_PAY";
		String COMMONCODE_COMPONENT_DCPS_DA = "DCPS_DA";
		String COMMONCODE_COMPONENT_DCPS_DELAYED_CODE = "700047";
		String COMMONCODE_COMPONENT_DCPS_DA_ARR_CODE = "700048";
		String COMMONCODE_COMPONENT_DCPS_PAY_ARR_CODE = "700049";
		
		// Set Null Vaule
		String COMMONCODE_VALUE_NULL = "Null";
		int COMMONCODE_PAY_AND_ALLOWANCES_ARREARS_CODE = 200;
		
		// Percentage 100
		Double COMMONCODE_PERCENTAGE_100 = (double) 100;
		Double COMMONCODE_PERCENTAGE_50 = (double) 50;
		
		// GradePay Againt Travels Allowance
		
		Double COMMONCODE_GRADE_PAY_AMOUNT_GREATER_THAN_5400_FOR_CLASS_A1 = (double) 2400;
		Double COMMONCODE_GRADE_PAY_AMOUNT_LESS_THAN_5400_FOR_CLASS_A1 = (double) 1200;
		Double COMMONCODE_GRADE_PAY_AMOUNT_LESS_THAN_4400_FOR_CLASS_A1 = (double) 400;
		
		Double COMMONCODE_GRADE_PAY_AMOUNT_GREATER_THAN_5400_CLASS_A1_OTHER = (double) 1200;
		Double COMMONCODE_GRADE_PAY_AMOUNT_LESS_THAN_5400_CLASS_A1_OTHER = (double) 600;
		Double COMMONCODE_GRADE_PAY_AMOUNT_LESS_THAN_4400_CLASS_A1_OTHER = (double) 400;
		
		
		Integer COMMONCODE_GRADE_PAY_AMOUNT_1300 = 1300;
	

		
		///Added for Non-Computation and Non-government deduction components
		int COMMONCODE_COMPONENT_SVN_DA_CODE =124;
		int COMMONCODE_COMPONENT_CLA_CODE =34;
		int COMMONCODE_COMPONENT_DP_CODE =17;
		
		
		//pension allowdeduct com ponent code
		
		int COMPONENT_PENSION_DA=1;
		int COMPONENT_PENSION_RECOVERY=2;
		int COMPONENT_PENSION_ARREARS=3;
		int COMPONENT_PENSION_COMMUTATION=4;
		int COMPONENT_PENSION_PT=5;
		int COMPONENT_PENSION_INCOME_TAX=6;
		int COMPONENT_PENSION_BASIC_ARREARS=7;
		int COMPONENT_PENSION_DA_ARREARS=8;
		int COMPONENT_PENSION_CVP1=9;
		int COMPONENT_PENSION_CVP2=10;
		int COMPONENT_PENSION_CVP3=11;
		
		
		Integer SIXPC_ALLOW_DEDUC_CODE = 10;
		Integer SVNPC_ALLOW_DEDUC_CODE = 161;
		
		
		
	}
	
	public interface PostType {
		int Permanent = 1;
		int Temporary = 2;
	}
	
	public interface Languages {
		int English = 1;
		int Marathi = 2;
	}
	
	
	public interface Headers {
		public static final String CONTENT_DISPOSITION = "Content-Disposition";
	}

	
	
	public static String getUserWiseUrl(OrgUserMst messages) {
		String url = "";

		switch (messages.getMstRoleEntity().getRoleId()) {
		case 1:
			url = "/mdc/";
			break;
		case 2:
			url = "/ddo/";
			break;
		case 3:
			url = "/ddoast/";
			break;
		case 4:
			url = "/user/";
			break;
		case 5:
			url = "/super/";
			break;
		case 6:
			url = "/mdp/";
			break;
		case 7:
			url = "/level3/";
			break;
		case 8:
			url = "/level4/";
			break;
		case 9:
			url = "/developer/";
			break;
		}
		
		return url;
	}
	
	public interface LookUpQuery{
	    static final String GET_LOOK_UP_VALUES = "SELECT O1 FROM CmnLookupMst O1, CmnLookupMst O2 WHERE O1.parentLookupId = O2.lookupId AND O2.lookupName = :lookupName AND O1.cmnLanguageMst.langId = :langId  ORDER BY O1.orderNo desc,O1.lookupId";
	}
	
}
