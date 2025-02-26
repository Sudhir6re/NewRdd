package com.mahait.gov.in.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.mahait.gov.in.model.DisplayBrokenPeriodReportModel;
import com.mahait.gov.in.model.Partition;
import com.mahait.gov.in.repository.BrokenPeriodReportRepo;

import jakarta.transaction.Transactional;


@Transactional
@Service
public class BrokenPeriodReportServiceImpl implements BrokenPeriodReportService {

	
	@Autowired
	BrokenPeriodReportRepo 	brokenPeriodReportRepo;
	
	@Autowired
	CommonHomeMethodsService 	commonHomeMethodsService;
	
	
	@Override
	public Page<DisplayBrokenPeriodReportModel> findPaginated(PageRequest pageable, Long billNumber, String ddoCode,
			Long billno) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<DisplayBrokenPeriodReportModel> list;
        final  List<DisplayBrokenPeriodReportModel> lstinnerreports =getAllDataForinnernew(billNumber,ddoCode,billno);
        if (lstinnerreports.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, lstinnerreports.size());
            list = lstinnerreports.subList(startItem, toIndex);
        }
 
        Page<DisplayBrokenPeriodReportModel> innerPage
          = new PageImpl<DisplayBrokenPeriodReportModel>(list, PageRequest.of(currentPage, pageSize), lstinnerreports.size());
 
        return innerPage;
    }
	
	public List<DisplayBrokenPeriodReportModel> getAllDataForinnernew(Long billNumber,String strddo,Long billno){
		List<DisplayBrokenPeriodReportModel> allowEdpList = new ArrayList<DisplayBrokenPeriodReportModel>();// edpDao.getAllowCompoMpgData(locId);
		List<DisplayBrokenPeriodReportModel> deducAgEdpList = new ArrayList<DisplayBrokenPeriodReportModel>();// edpDao.getAGDeducCompoMpgData(locId);
		List<DisplayBrokenPeriodReportModel> deducTyEdpList = new ArrayList<DisplayBrokenPeriodReportModel>();// edpDao.getTRDeducCompoMpgData(locId);
		List<DisplayBrokenPeriodReportModel> deducOthEdpList = new ArrayList<DisplayBrokenPeriodReportModel>();// changes for other (nps)
		
		//Dynamic process start
				Integer month=null;
				Integer year=null;
				List<Object[]> monthyear = commonHomeMethodsService.findDetailsBillNumber(billno);
				for (Object[] objects : monthyear) {
					month=Integer.parseInt(objects[0].toString());
					year=Integer.parseInt(objects[1].toString());
				}
				List<DisplayBrokenPeriodReportModel> allEdpList = brokenPeriodReportRepo.getAllDataForinnernew(strddo,month,year,billNumber);
				List<Map<String,Object>> lstempdetails1=brokenPeriodReportRepo.getbrokenPeriodDetails(month,year,strddo,billNumber);
				
				
				Partition partition=new Partition(lstempdetails1, 8);
				int partitionsize=partition.size();
				List<DisplayBrokenPeriodReportModel> returnresult=new ArrayList<DisplayBrokenPeriodReportModel>();
				for(int k=0;k<partitionsize;k++) {
					List<Map<String,Object>> lstempdetails=partition.get(k);
				
				int empsize=lstempdetails.size();
				for (int i = 0; i < allEdpList.size(); i++) {
						if (allEdpList.get(i).getType() == 1) { // allowance
							allowEdpList.add(allEdpList.get(i));
						}else {
							deducAgEdpList.add(allEdpList.get(i));
						}
				}
				// Dynamic Process end
		
		
		
		ArrayList DataList = new ArrayList();
		
		String officeDetails = commonHomeMethodsService.getOffice(strddo);
		ArrayList headerRow= new ArrayList();  //  header row
		String description="Employee Details";
//		headerRow.add("");
		headerRow.add("SECTION OF ESTABLISH.");
		Iterator iteratordsgn = lstempdetails.iterator();
		for (int i = 1; i <= empsize; i++) {
			//	allowance.add(row);
				if(i<=empsize) {
			Map<String, Object> map = (Map<String, Object>) iteratordsgn.next();
			headerRow.add("");
				}else {
					headerRow.add("");
				}
		}
//		headerRow.add("");
		
		//Employee description row started  
		ArrayList orderdataList = new ArrayList();
//		orderdataList.add(" ");
		ArrayList row = new ArrayList();
		row.add("Sevarth Id");
		row.add("Employee Name");
		row.add("From Date ");
		row.add("To Date");
		row.add("No.Of Days");
		row.add("BASIC PAY");
		row.add("Reason");
		row.add("Remarks");
		orderdataList.add(row);
		Iterator iteratorempdtls = lstempdetails.iterator(); 
		for (int i = 1; i <= empsize; i++) {
			//	allowance.add(row);
				if(i<=empsize) {
					
						Map<String, Object> map = (Map<String, Object>) iteratorempdtls.next();
						row = new ArrayList();
						row.add(map.get("sevaarth_id").toString());
						row.add(map.get("employee_full_name_en").toString());
						row.add(map.get("from_date").toString());
						row.add(map.get("to_date").toString());
						row.add(map.get("no_of_days").toString());
						row.add(map.get("basic_pay").toString());
						row.add(map.get("reason").toString());
						if(map.get("remarks")!=null) {
						row.add(map.get("remarks").toString());
						}else {
							row.add("-");
						}
						row.add("");
						orderdataList.add(row);
				
				}else {
					orderdataList.add("");
				}
				
		}
		
		//allowance started##########################################
	
		ArrayList allowance =new ArrayList();
		row = new ArrayList();
		for (int i = 1; i <= allowEdpList.size(); i++) {
//			row.add(String.valueOf(i));
		}
//		allowance.add(row);
		row = new ArrayList();
		for (Iterator iterator = allowEdpList.iterator(); iterator.hasNext();) {
			DisplayBrokenPeriodReportModel object = (DisplayBrokenPeriodReportModel) iterator.next();
			row.add(object.getDeptalldetNm());
		}
		allowance.add(row);
		
		Iterator iterator1 = lstempdetails.iterator();
		
		Map<String,Double> mapAllowanceSum = new HashMap<>();

		for (int i = 1; i <= empsize; i++) {
			if(i<=empsize) {
				Map<String, Object> map = (Map<String, Object>) iterator1.next();
				row = new ArrayList();
				List <Object[]> lstempbrokendata=brokenPeriodReportRepo.getbrokenPeriodvalue((Long) map.get("broken_period_id"),map.get("sevaarth_id").toString());
				Map<Integer,Double> mapAllowanceCodeToAmount = new HashMap<>();
				for (Object[] broken : lstempbrokendata) {
					mapAllowanceCodeToAmount.put(Integer.parseInt(broken[0].toString()), Double.parseDouble(broken[1].toString()));
				}
				
				for (Iterator iterator = allowEdpList.iterator(); iterator.hasNext();) {
					DisplayBrokenPeriodReportModel object = (DisplayBrokenPeriodReportModel) iterator.next();
					try {
						if(mapAllowanceCodeToAmount.containsKey(object.getDeptallowdeducid())) {
							row.add(mapAllowanceCodeToAmount.get(object.getDeptallowdeducid()));
						}else {
							row.add("0");
						}
					} catch (Exception e) {
						row.add("0");
					}
				}
			}else {
				
			}
			allowance.add(row);
		}
		
		Double sum;
		row = new ArrayList();
		for (int i = 1; i <= allowEdpList.size(); i++) {
//			row.add(String.valueOf(i));
		}
//		allowance.add(row);
		
		//allowance ended##########################################
		
		//ded AG started##########################################
		ArrayList lstdedectionag =new ArrayList();
		Map<String,Double> maplstdedectionagSum = new HashMap<>();
		row = new ArrayList();
		for (int i = 1; i <= deducAgEdpList.size(); i++) {
//			row.add(String.valueOf(i));
		}
//		lstdedectionag.add(row);
		row = new ArrayList();
		for (Iterator iterator = deducAgEdpList.iterator(); iterator.hasNext();) {
			DisplayBrokenPeriodReportModel object = (DisplayBrokenPeriodReportModel) iterator.next();
			row.add(object.getDeptalldetNm());
		}
		lstdedectionag.add(row);
		Iterator iterator2 = lstempdetails.iterator();
		for (int i = 1; i <= empsize; i++) {
		row = new ArrayList();
		if(i<=empsize) {
			Map<String, Object> map = (Map<String, Object>) iterator2.next();
			row = new ArrayList();
			List <Object[]> lstempbrokendata=brokenPeriodReportRepo.getbrokenPeriodvalue((Long) map.get("broken_period_id"),map.get("sevaarth_id").toString());
			Map<Integer,Double> mapAllowanceCodeToAmount = new HashMap<>();
			for (Object[] broken : lstempbrokendata) {
				mapAllowanceCodeToAmount.put(Integer.parseInt(broken[0].toString()), Double.parseDouble(broken[1].toString()));
			}
			
			for (Iterator iterator = deducAgEdpList.iterator(); iterator.hasNext();) {
				DisplayBrokenPeriodReportModel object = (DisplayBrokenPeriodReportModel) iterator.next();
				try {
					if(mapAllowanceCodeToAmount.containsKey(object.getDeptallowdeducid())) {
						row.add(mapAllowanceCodeToAmount.get(object.getDeptallowdeducid()));
					}else {
						row.add("0");
					}
				} catch (Exception e) {
					row.add("0");
				}
			}
			
		}else {/*
				
		*/}
		lstdedectionag.add(row);
		}
		row = new ArrayList();
		for (int i = 1; i <= deducAgEdpList.size(); i++) {
//			row.add(String.valueOf(i));
		}
//		lstdedectionag.add(row);
		//ded AG ended##########################################
		
				
				//net total row started  
			ArrayList alnetamt = new ArrayList();
				alnetamt.add("NET");
				Iterator iteratornetamt = lstempdetails.iterator(); 
				for (int i = 1; i <= empsize; i++) {
						if(i<=empsize) {
								Map<String, Object> map = (Map<String, Object>) iteratornetamt.next();
								alnetamt.add(map.get("net_pay").toString());
						}else {
							alnetamt.add(" ");
						}
				}
//				alnetamt.add(" ");
				
		//############################################################################################################
				DisplayBrokenPeriodReportModel displyinnrpt=new DisplayBrokenPeriodReportModel();
				displyinnrpt.setDescription(description);
				displyinnrpt.setHeaderRow(headerRow);
				displyinnrpt.setOrderdataList(orderdataList);
				displyinnrpt.setSlno(null);
				displyinnrpt.setAllowance(allowance);
				displyinnrpt.setLstdedectionag(lstdedectionag);
				displyinnrpt.setAlnetamt(alnetamt);
				returnresult.add(displyinnrpt);
				}
				return returnresult;
	}

	@Override
	public Long findbillsize(int monthName, int yearName, Long billNumber, String ddoCode) {
		// TODO Auto-generated method stub
		return brokenPeriodReportRepo.findbillsize(monthName,yearName,billNumber,ddoCode);
	}


}
