package com.mahait.gov.in.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ComponentMstModel {

	
	Long id;
	boolean typeAllowance;
	boolean typeOfComponet;
	boolean payCommision;
	boolean amount;
	boolean startDate;
	boolean endDate;
	boolean percentage;
	boolean minBasic;
	boolean maxBasic;
	boolean cityClass;
	boolean gradePayorSevenPcLevelLow;
	boolean grdePayorSevenPcLevelhigh;
	boolean premiumAmount;
	boolean cityGroup;
}
