package com.mahait.gov.in.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.mahait.gov.in.common.StringHelperUtils;
import com.mahait.gov.in.model.DisplayPageWiseAbstractReportModel;
import com.mahait.gov.in.model.Partition;
import com.mahait.gov.in.repository.PageWiseAbstractReportRepo;

import jakarta.transaction.Transactional;



@Transactional
@Service
public class PageWiseAbstractReportServiceImpl implements PageWiseAbstractReportService{

	@Autowired
	PageWiseAbstractReportRepo 	displayPageWiseAbstractReportRepo;
	@Autowired
	DisplayOuterReportService displayOuterReportService;
	@Autowired
	CommonHomeMethodsService commonHomeMethodsService;
	
	@Override
	public Page<DisplayPageWiseAbstractReportModel> findPaginated(PageRequest pageable, Long billNumber, String ddoCode) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<DisplayPageWiseAbstractReportModel> list;
        final  List<DisplayPageWiseAbstractReportModel> lstinnerreports =getAllDataForinnernew(billNumber,ddoCode);
 
        if (lstinnerreports.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, lstinnerreports.size());
            list = lstinnerreports.subList(startItem, toIndex);
        }
 
        Page<DisplayPageWiseAbstractReportModel> innerPage
          = new PageImpl<DisplayPageWiseAbstractReportModel>(list, PageRequest.of(currentPage, pageSize), lstinnerreports.size());
 
        return innerPage;
    }
	public List<DisplayPageWiseAbstractReportModel> getAllDataForinnernew(Long billNumber,String strddo){
		List<DisplayPageWiseAbstractReportModel> allowEdpList = new ArrayList<DisplayPageWiseAbstractReportModel>();// edpDao.getAllowCompoMpgData(locId);
		List<DisplayPageWiseAbstractReportModel> deducAgEdpList = new ArrayList<DisplayPageWiseAbstractReportModel>();// edpDao.getAGDeducCompoMpgData(locId);
		List<DisplayPageWiseAbstractReportModel> deducTyEdpList = new ArrayList<DisplayPageWiseAbstractReportModel>();// edpDao.getTRDeducCompoMpgData(locId);
		List<DisplayPageWiseAbstractReportModel> deducOthEdpList = new ArrayList<DisplayPageWiseAbstractReportModel>();// changes for other (nps)
		
		//Dynamic process start
				List<DisplayPageWiseAbstractReportModel> allEdpList = displayPageWiseAbstractReportRepo.getAllDataForinnernew(strddo,billNumber);
				List<Map<String,Object>> lstempdetails1=displayPageWiseAbstractReportRepo.getempDetails(String.valueOf(billNumber));
				Partition partition=new Partition(lstempdetails1, 8);
				int partitionsize=partition.size();
				List<DisplayPageWiseAbstractReportModel> returnresult=new ArrayList<DisplayPageWiseAbstractReportModel>();
				for(int k=0;k<partitionsize;k++) {
					List<Map<String,Object>> lstempdetails=partition.get(k);
					allowEdpList.clear();
					deducAgEdpList.clear();
					deducOthEdpList.clear();
					deducTyEdpList.clear();
					DisplayPageWiseAbstractReportModel obj = new DisplayPageWiseAbstractReportModel();
	            	obj.setDeptalldetNm(StringHelperUtils.isNullString("basic_pay"));
	                obj.setType(1);
	                obj.setDeptallowdeducid(1);
	                obj.setTempvalue("0");
	                obj.setTempempty("0");
	                allowEdpList.add(obj);
				int empsize=lstempdetails.size();
				for (int i = 0; i < allEdpList.size(); i++) {
						if (allEdpList.get(i).getType() == 1) { // allowance
							
							allowEdpList.add(allEdpList.get(i));
						}else
						if (allEdpList.get(i).getType() == 2) { 
							
							if(allEdpList.get(i).getDeptallowdeducid()==36||allEdpList.get(i).getDeptallowdeducid()==37||allEdpList.get(i).getDeptallowdeducid()==38||allEdpList.get(i).getDeptallowdeducid()==39)
							deducAgEdpList.add(allEdpList.get(i)); //Deductions Adj. By CAFO/Supri./Admin.
							else
							deducTyEdpList.add(allEdpList.get(i)); //Adjust by Treasury
						}else {
							deducOthEdpList.add(allEdpList.get(i));
						}
				}
				// Dynamic Process end
		
		
		
		ArrayList DataList = new ArrayList();
		
		String officeDetails = displayOuterReportService.getOffice(strddo);
		List<Object[]> cardecode = displayOuterReportService.getcardecode(strddo);
		String carde="";
		for (Object[] objects : cardecode) {
			if(carde != "") {
				carde+=","+objects[1].toString();;
			}else {
				carde=objects[1].toString();
			}
		}
			
		
		Date createdate = commonHomeMethodsService.findbillCreateDate(billNumber);
		ArrayList headerRow= new ArrayList();  //  header row
		String description="Bill Id ("+billNumber+") Detail Bill - "+officeDetails+"-["+carde+"]";
		/*headerRow.add("");
		headerRow.add("SECTION OF ESTABLISH.");
		Iterator iteratordsgn = lstempdetails.iterator();
		for (int i = 1; i <= 9; i++) {
			//	allowance.add(row);
				if(i<=empsize) {
			Map<String, Object> map = (Map<String, Object>) iteratordsgn.next();
			headerRow.add(map.get("designation_name").toString());
				}else {
					headerRow.add("");
				}
		}
		headerRow.add("");
		*/
		//Employee description row started  
		ArrayList orderdataList = new ArrayList();
		orderdataList.add(" ");
		ArrayList row = new ArrayList();
		row.add("NAME");
		/*row.add("CODE");
		row.add(" ");
		row.add("PayInPB+GP");
		row.add("BASIC PAY");*/
		orderdataList.add(row);
		Iterator iteratorempdtls = lstempdetails.iterator(); 
		for (int i = 1; i <= 9; i++) {
			//	allowance.add(row);
				if(i<=empsize) {
					
						Map<String, Object> map = (Map<String, Object>) iteratorempdtls.next();
						row = new ArrayList();
						row.add("Page");
						row.add(i);
						orderdataList.add(row);
						}else {
					if(empsize+1 == i) {
						row = new ArrayList();
						row.add("Total");
						orderdataList.add(row);
					}else {
						orderdataList.add("");
					}
				}
				
		}
		orderdataList.add("");
		
				
		//Employee description row ended  
		
		//SL No and Remark start
		ArrayList slno =new ArrayList();
		slno.add("SL NO");
		slno.add("REMARKS");
		slno.add(" ");
		slno.add(" ");
		slno.add(" ");
		slno.add(" ");
		slno.add(" ");
		slno.add(" ");
		slno.add(" ");
		slno.add(" ");
		slno.add("SL NO");
		//SL No and Remark end
		
		//allowance started##########################################
	
		ArrayList allowance =new ArrayList();
		row = new ArrayList();
		for (int i = 1; i <= allowEdpList.size(); i++) {
			row.add(String.valueOf(i));
		}
		allowance.add(row);
		row = new ArrayList();
		for (Iterator iterator = allowEdpList.iterator(); iterator.hasNext();) {
			DisplayPageWiseAbstractReportModel object = (DisplayPageWiseAbstractReportModel) iterator.next();
			row.add(object.getDeptalldetNm());
		}
		allowance.add(row);
		
		Iterator iterator1 = lstempdetails.iterator();
		
		Map<String,Double> mapAllowanceSum = new HashMap<>();

		for (int i = 1; i <= 9; i++) {
			if(i<=empsize) {
				Map<String, Object> map = (Map<String, Object>) iterator1.next();
				
				System.out.println("------------------abstract page"+map);
				row = new ArrayList();
				
				for (Iterator iterator = allowEdpList.iterator(); iterator.hasNext();) {
					
					DisplayPageWiseAbstractReportModel object = (DisplayPageWiseAbstractReportModel) iterator.next();
					try {
						String allname=object.getDeptalldetNm();
						
						
						if(allname.equals("wash_allow") || allname.equals("wa")  || allname.equals("wash_allowance")  ) {

							System.out.println("------------------abstract page"+map);
						}
						
						row.add(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
						if(mapAllowanceSum.get(object.getDeptalldetNm().toLowerCase().trim()) !=null){
							Double amount = mapAllowanceSum.get(object.getDeptalldetNm().toLowerCase().trim()) + Double.parseDouble(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
							mapAllowanceSum.put(object.getDeptalldetNm().toLowerCase().trim(), amount);
						}else {
							mapAllowanceSum.put(object.getDeptalldetNm().toLowerCase().trim(),  Double.parseDouble(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString()));
						}
					} catch (Exception e) {
						row.add("0");
						if(mapAllowanceSum.get(object.getDeptalldetNm().toLowerCase().trim()) !=null){
							Double amount = mapAllowanceSum.get(object.getDeptalldetNm().toLowerCase().trim());
							mapAllowanceSum.put(object.getDeptalldetNm().toLowerCase().trim(), amount);
						}else {
							mapAllowanceSum.put(object.getDeptalldetNm().toLowerCase().trim(),  0D);
						}
					}
					
				}
			}else {
				if(empsize+1 == i) {
					row = new ArrayList();
					for(DisplayPageWiseAbstractReportModel object:allowEdpList) {
						row.add(mapAllowanceSum.get(object.getDeptalldetNm().toLowerCase().trim()));
					}
				}else {
					row = new ArrayList();
					for (int j = 1; j <= allowEdpList.size(); j++) {
						row.add("");
					}
				}
			}
			allowance.add(row);
		}
		
		Double sum;
		row = new ArrayList();
		for (int i = 1; i <= allowEdpList.size(); i++) {
			row.add(String.valueOf(i));
		}
		allowance.add(row);
		//allowance ended##########################################
		
		//ded AG started##########################################
		ArrayList lstdedectionag =new ArrayList();
		Map<String,Double> maplstdedectionagSum = new HashMap<>();
		row = new ArrayList();
		for (int i = 1; i <= deducAgEdpList.size(); i++) {
			row.add(String.valueOf(i));
		}
		lstdedectionag.add(row);
		row = new ArrayList();
		for (Iterator iterator = deducAgEdpList.iterator(); iterator.hasNext();) {
			DisplayPageWiseAbstractReportModel object = (DisplayPageWiseAbstractReportModel) iterator.next();
			row.add(object.getDeptalldetNm());
		}
		lstdedectionag.add(row);
		Iterator iterator2 = lstempdetails.iterator();
		for (int i = 1; i <= 9; i++) {
		row = new ArrayList();
		if(i<=empsize) {
			Map<String, Object> map = (Map<String, Object>) iterator2.next();
			row = new ArrayList();
			for (Iterator iterator = deducAgEdpList.iterator(); iterator.hasNext();) {
				DisplayPageWiseAbstractReportModel object = (DisplayPageWiseAbstractReportModel) iterator.next();
				try {
					String allname=object.getDeptalldetNm();
					row.add(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
					if(maplstdedectionagSum.get(object.getDeptalldetNm().toLowerCase().trim()) !=null){
						Double amount = maplstdedectionagSum.get(object.getDeptalldetNm().toLowerCase().trim()) + Double.parseDouble(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
						maplstdedectionagSum.put(object.getDeptalldetNm().toLowerCase().trim(), amount);
					}else {
						maplstdedectionagSum.put(object.getDeptalldetNm().toLowerCase().trim(),  Double.parseDouble(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString()));
					}
				} catch (Exception e) {
					row.add("0");
					if(maplstdedectionagSum.get(object.getDeptalldetNm().toLowerCase().trim()) !=null){
						Double amount = maplstdedectionagSum.get(object.getDeptalldetNm().toLowerCase().trim());
						maplstdedectionagSum.put(object.getDeptalldetNm().toLowerCase().trim(), amount);
					}else {
						maplstdedectionagSum.put(object.getDeptalldetNm().toLowerCase().trim(),  0D);
					}
				}
				
			}
		}else {
					if(empsize+1 == i) {
						row = new ArrayList();
						for(DisplayPageWiseAbstractReportModel object:deducAgEdpList) {
							row.add(maplstdedectionagSum.get(object.getDeptalldetNm().toLowerCase().trim()));
						}
						}else {
						row = new ArrayList();
					for (int j = 1; j <= deducAgEdpList.size(); j++) {
						row.add("");
					}
			}
		}
		lstdedectionag.add(row);
		}
		row = new ArrayList();
		for (int i = 1; i <= deducAgEdpList.size(); i++) {
			row.add(String.valueOf(i));
		}
		lstdedectionag.add(row);
		//ded AG ended##########################################
		
		//ded OTHER started##########################################
		ArrayList lstdeducother =new ArrayList();
		Map<String,Double> maplstdeducotherSum = new HashMap<>();
		row = new ArrayList();
		for (int i = 1; i <= deducOthEdpList.size(); i++) {
			row.add(String.valueOf(i));
		}
		lstdeducother.add(row);
		row = new ArrayList();
		for (Iterator iterator = deducOthEdpList.iterator(); iterator.hasNext();) {
			DisplayPageWiseAbstractReportModel object = (DisplayPageWiseAbstractReportModel) iterator.next();
			row.add(object.getDeptalldetNm());
		}
		lstdeducother.add(row);
		Iterator iterator3 = lstempdetails.iterator();
		for (int i = 1; i <= 9; i++) {
	//	allowance.add(row);
			//	allowance.add(row);
			if(i<=empsize) {
				Map<String, Object> map = (Map<String, Object>) iterator3.next();
				row = new ArrayList();
				for (Iterator iterator = deducOthEdpList.iterator(); iterator.hasNext();) {
					
					DisplayPageWiseAbstractReportModel object = (DisplayPageWiseAbstractReportModel) iterator.next();
					try {
						String allname=object.getDeptalldetNm();
						row.add(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
						
						
						
						if(maplstdeducotherSum.get(object.getDeptalldetNm().toLowerCase().trim()) !=null){
							Double amount = maplstdeducotherSum.get(object.getDeptalldetNm().toLowerCase().trim()) + Double.parseDouble(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
							maplstdeducotherSum.put(object.getDeptalldetNm().toLowerCase().trim(), amount);
						}else {
							maplstdeducotherSum.put(object.getDeptalldetNm().toLowerCase().trim(),  Double.parseDouble(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString()));
						}
					} catch (Exception e) {
						row.add("0");
						if(maplstdeducotherSum.get(object.getDeptalldetNm().toLowerCase().trim()) !=null){
							Double amount = maplstdeducotherSum.get(object.getDeptalldetNm().toLowerCase().trim());
							maplstdeducotherSum.put(object.getDeptalldetNm().toLowerCase().trim(), amount);
						}else {
							maplstdeducotherSum.put(object.getDeptalldetNm().toLowerCase().trim(),  0D);
						}
					}
					
				}
			}else {
				if(empsize+1 == i) {
					row = new ArrayList();
					for(DisplayPageWiseAbstractReportModel object:deducOthEdpList) {
						row.add(maplstdeducotherSum.get(object.getDeptalldetNm().toLowerCase().trim()));
					}
				}else {
					row = new ArrayList();
						for (int j = 1; j <= deducOthEdpList.size(); j++) {
							row.add("");
						}
					}	
				}
			lstdeducother.add(row);
		}
		row = new ArrayList();
		for (int i = 1; i <= deducOthEdpList.size(); i++) {
			row.add(String.valueOf(i));
		}
		lstdeducother.add(row);
		//ded OTHER ended##########################################
		
		//ded TY started##########################################
		ArrayList lstdeductrsy =new ArrayList();
		Map<String,Double> maplstdeductrsySum = new HashMap<>();
		row = new ArrayList();
		for (int i = 1; i <= deducTyEdpList.size(); i++) {
			row.add(String.valueOf(i));
		}
		lstdeductrsy.add(row);
		row = new ArrayList();
		for (Iterator iterator = deducTyEdpList.iterator(); iterator.hasNext();) {
			DisplayPageWiseAbstractReportModel object = (DisplayPageWiseAbstractReportModel) iterator.next();
			row.add(object.getDeptalldetNm());
		}
		lstdeductrsy.add(row);
		Iterator iterator4 = lstempdetails.iterator();
		for (int i = 1; i <= 9; i++) {
			if(i<=empsize) {
				Map<String, Object> map = (Map<String, Object>) iterator4.next();
				row = new ArrayList();
				for (Iterator iterator = deducTyEdpList.iterator(); iterator.hasNext();) {
					DisplayPageWiseAbstractReportModel object = (DisplayPageWiseAbstractReportModel) iterator.next();
					try {
						String allname=object.getDeptalldetNm();
						row.add(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
						if(maplstdeductrsySum.get(object.getDeptalldetNm().toLowerCase().trim()) !=null){
							Double amount = maplstdeductrsySum.get(object.getDeptalldetNm().toLowerCase().trim()) + Double.parseDouble(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
							maplstdeductrsySum.put(object.getDeptalldetNm().toLowerCase().trim(), amount);
						}else {
							maplstdeductrsySum.put(object.getDeptalldetNm().toLowerCase().trim(),  Double.parseDouble(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString()));
						}
					} catch (Exception e) {
						row.add("0");
						if(maplstdeductrsySum.get(object.getDeptalldetNm().toLowerCase().trim()) !=null){
							Double amount = maplstdeductrsySum.get(object.getDeptalldetNm().toLowerCase().trim());
							maplstdeductrsySum.put(object.getDeptalldetNm().toLowerCase().trim(), amount);
						}else {
							maplstdeductrsySum.put(object.getDeptalldetNm().toLowerCase().trim(),  0D);
						}
					}
					
				}
			}else {
				if(empsize+1 == i) {
					row = new ArrayList();
					for(DisplayPageWiseAbstractReportModel object:deducTyEdpList) {
						row.add(maplstdeductrsySum.get(object.getDeptalldetNm().toLowerCase().trim()));
					}
				}else {
					row = new ArrayList();
					for (int j = 1; j <= deducTyEdpList.size(); j++) {
						row.add("");
					}
				}	
			}
			
		lstdeductrsy.add(row);
		}
		row = new ArrayList();
		for (int i = 1; i <= deducTyEdpList.size(); i++) {
			row.add(String.valueOf(i));
		}
		lstdeductrsy.add(row);
		//ded TY ended##########################################
		//gross total row started  
				ArrayList algrossamt = new ArrayList();
				algrossamt.add("");
				algrossamt.add("TOTAL");
				Iterator iteratorgrossamt = lstempdetails.iterator();
				Double grossTotalSum =0d;
				
				for (int i = 1; i <= 9; i++) {
					if(i<=empsize) {
						Map<String, Object> map = (Map<String, Object>) iteratorgrossamt.next();
						algrossamt.add(map.get("gross_amt").toString());
						grossTotalSum += Double.parseDouble(map.get("gross_amt").toString());
					}else {
						algrossamt.add(" ");
					}
					if(empsize == i) {
						algrossamt.add(Math.round(grossTotalSum));
					}
				}
				
//				algrossamt.add(" ");
				//gross total row ended  
				
				//gross sale row started  
				ArrayList algrosssale = new ArrayList();
				algrosssale.add("");
				algrosssale.add("GROSS SAL");
				Iterator iteratorgrossale = lstempdetails.iterator(); 
				Double algrosssaleSum =0d;
				for (int i = 1; i <= 9; i++) {
						if(i<=empsize) {
								Map<String, Object> map = (Map<String, Object>) iteratorgrossale.next();
								algrosssale.add(map.get("gross_amt").toString());
								algrosssaleSum += Double.parseDouble(map.get("gross_amt").toString());
						}else {
							algrosssale.add("");
						}
						if(empsize == i) {
							algrosssale.add(Math.round(algrosssaleSum));
						}
				}
//				algrosssale.add("");
				//gross sale row ended  
				
				//gross total row started  
				ArrayList algrosstotal = new ArrayList();
				algrosstotal.add("");
				algrosstotal.add("GROSS TOT");
				Iterator iteratorgrosstotal = lstempdetails.iterator(); 
				Double algrosstotalSum =0d;
				for (int i = 1; i <= 9; i++) {
						if(i<=empsize) {
								Map<String, Object> map = (Map<String, Object>) iteratorgrosstotal.next();
								algrosstotal.add(map.get("gross_amt").toString());
								algrosstotalSum += Double.parseDouble(map.get("gross_amt").toString());
						}else {
							algrosstotal.add("");
						}
						if(empsize == i) {
							algrosstotal.add(Math.round(algrosstotalSum));
						}
				}
//				algrosstotal.add("");
				//gross total row ended  
				
				//net total row started  
				ArrayList alnetamt = new ArrayList();
				alnetamt.add("");
				alnetamt.add("NET");
				Iterator iteratornetamt = lstempdetails.iterator(); 
				Double alnetamtSum =0d;
				for (int i = 1; i <= 9; i++) {
						if(i<=empsize) {
								Map<String, Object> map = (Map<String, Object>) iteratornetamt.next();
								alnetamt.add(map.get("net_total").toString());
								alnetamtSum += Double.parseDouble(map.get("net_total").toString());
						}else {
							alnetamt.add(" ");
						}
						if(empsize == i) {
							alnetamt.add(Math.round(alnetamtSum));
						}
				}
//				alnetamt.add(" ");
				//net total row ended  
		
				//DEDUCTIONS ADJUSTABLE BY AG row started  
				ArrayList altotal_deduction = new ArrayList();
				altotal_deduction.add("");
				altotal_deduction.add("Total AG Ded.");
				Iterator iteratortotal_deduction = lstempdetails.iterator(); 
				Double altotal_deductionSum =0d;
				for (int i = 1; i <= 9; i++) {
						if(i<=empsize) {
								Map<String, Object> map = (Map<String, Object>) iteratortotal_deduction.next();
								if(map.get("deduc_adj_ag")!=null) {
									altotal_deduction.add(map.get("deduc_adj_ag").toString());
									altotal_deductionSum += Double.parseDouble(map.get("deduc_adj_ag").toString());
								}
						}else {
							altotal_deduction.add(" ");
						}
						if(empsize == i) {
							altotal_deduction.add(Math.round(altotal_deductionSum));
						}
				}
//				alnetamt.add(" ");
				//DEDUCTIONS ADJUSTABLE BY AG row ended 
				
				//DEDUCTIONS ADJUSTABLE BY TRY started  
				ArrayList aldeduct_adj_try = new ArrayList();
				aldeduct_adj_try.add("");
				aldeduct_adj_try.add("Tot.TRY.Ded");
				Iterator iteratoraldeduct_adj_try = lstempdetails.iterator(); 
				Double aldeduct_adj_trySum =0d;
				for (int i = 1; i <= 9; i++) {
					if(i<=empsize) {
						Map<String, Object> map = (Map<String, Object>) iteratoraldeduct_adj_try.next();
						if(map.get("deduct_adj_try")!=null) {
							aldeduct_adj_try.add(map.get("deduct_adj_try").toString());
							aldeduct_adj_trySum += Double.parseDouble(map.get("deduct_adj_try").toString());
						}
					}else {
						aldeduct_adj_try.add(" ");
					}
					if(empsize == i) {
						aldeduct_adj_try.add(Math.round(aldeduct_adj_trySum));
					}
				}
//				alnetamt.add(" ");
				//DEDUCTIONS ADJUSTABLE BY TRY ended 
				
				//DEDUCTIONS ADJUSTABLE BY OTHERS row started  
				ArrayList aldeduct_adj_otr = new ArrayList();
				aldeduct_adj_otr.add("");
				aldeduct_adj_otr.add("Tot. Ded.");
				Iterator iteratoraldeduct_adj_otr = lstempdetails.iterator(); 
				Double aldeduct_adj_otrSum =0d;
				for (int i = 1; i <= 9; i++) {
					if(i<=empsize) {
						Map<String, Object> map = (Map<String, Object>) iteratoraldeduct_adj_otr.next();
						if(map.get("deduct_adj_otr")!=null) {
							aldeduct_adj_otr.add(map.get("deduct_adj_otr").toString());
							aldeduct_adj_otrSum += Double.parseDouble(map.get("deduct_adj_otr").toString());
						}
					}else {
						aldeduct_adj_otr.add(" ");
					}
					if(empsize == i) {
						aldeduct_adj_otr.add(Math.round(aldeduct_adj_otrSum));
					}
				}
		//############################################################################################################
				DisplayPageWiseAbstractReportModel displyinnrpt=new DisplayPageWiseAbstractReportModel();
				displyinnrpt.setDescription(description);
				displyinnrpt.setHeaderRow(headerRow);
				displyinnrpt.setOrderdataList(orderdataList);
				displyinnrpt.setSlno(slno);
				displyinnrpt.setAllowance(allowance);
				displyinnrpt.setLstdedectionag(lstdedectionag);
				displyinnrpt.setLstdeducother(lstdeducother);
				displyinnrpt.setLstdeductrsy(lstdeductrsy);
				displyinnrpt.setAlnetamt(alnetamt);
				displyinnrpt.setAlgrossamt(algrossamt);
				displyinnrpt.setAlgrosssale(algrosssale);
				displyinnrpt.setAlgrosstotal(algrosstotal);
//				displyinnrpt.setDeductionamt(deductionamt);
				displyinnrpt.setDeductionamt(altotal_deduction);
				displyinnrpt.setAldeduct_adj_try(aldeduct_adj_try);
				displyinnrpt.setAldeduct_adj_otr(aldeduct_adj_otr);
				returnresult.add(displyinnrpt);
				}
				return returnresult;
	}

}
