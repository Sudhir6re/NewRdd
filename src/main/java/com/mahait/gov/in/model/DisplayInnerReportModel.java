package com.mahait.gov.in.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class DisplayInnerReportModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private int paybillgenerationtrnid;
	private String billdescription;
	
	//start
	private String deptalldetNm;  // edp desc
	private int type;
	private int compoType;
	private int deptallowdeducid;
	private String tempvalue; // insted of subdetail head and headofaccountcode
	private String tempempty;
	private String description ;
	
	private List headerRow = new ArrayList();
	private List orderdataList = new ArrayList();
	private List slno= new ArrayList();
	private List allowance = new ArrayList();
	private List lstdedectionag = new ArrayList();
	private List lstdeducother = new ArrayList();
	private List lstdeductrsy = new ArrayList();
	private List alnetamt = new ArrayList();
	private List algrosstotal = new ArrayList();
	private List algrosssale = new ArrayList();
	private List algrossamt = new ArrayList();
	private List deductionamt = new ArrayList();
	private List aldeduct_adj_try = new ArrayList();
	private List aldeduct_adj_otr = new ArrayList();
	private List abcloans = new ArrayList();
	private List faloans = new ArrayList();
	private List caloans = new ArrayList();
	private List valoans = new ArrayList();
	private List hbaloans = new ArrayList();
	private List dcpsgpsno = new ArrayList();
	
	
	
    List<DisplayInnerReportModel> lstDisplayInnerReportModel= new ArrayList();
		
	
}
