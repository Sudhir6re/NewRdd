package com.mahait.gov.in.controller;

import java.math.BigInteger;
import java.text.DecimalFormat;
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
import org.springframework.data.domain.Pageable;

import com.mahait.gov.in.common.StringHelperUtils;
import com.mahait.gov.in.entity.PaybillGenerationTrnEntity;
import com.mahait.gov.in.model.DisplayInnerReportModel;
import com.mahait.gov.in.model.Partition;
import com.mahait.gov.in.repository.DisplayInnerReportRepo;
import com.mahait.gov.in.service.CommonHomeMethodsService;
import com.mahait.gov.in.service.DisplayOuterReportService;

public class DisplayInnerReportServiceImpl_bkp {


	@Autowired
	DisplayInnerReportRepo displayInnerReportRepo;
	@Autowired
	DisplayOuterReportService displayOuterReportService;
	@Autowired
	CommonHomeMethodsService commonHomeMethodsService;
	
	

	public Page<DisplayInnerReportModel> findPaginated(Pageable pageable, Long billNumber, String strddo) {
		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		List<DisplayInnerReportModel> list;
		final List<DisplayInnerReportModel> lstinnerreports = getAllDataForinnernew(billNumber, strddo, currentPage);

		if (lstinnerreports.size() < startItem) {
			list = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, lstinnerreports.size());
			list = lstinnerreports.subList(startItem, toIndex);
		}

		Page<DisplayInnerReportModel> innerPage = new PageImpl<DisplayInnerReportModel>(list,
				PageRequest.of(currentPage, pageSize), lstinnerreports.size());

		return innerPage;
	}

	public List<DisplayInnerReportModel> getAllDataForinnernew(Long billNumber, String strddo, int currentPage) {

		List<DisplayInnerReportModel> allowEdpList = new ArrayList<DisplayInnerReportModel>();// edpDao.getAllowCompoMpgData(locId);
		List<DisplayInnerReportModel> deducAgEdpList = new ArrayList<DisplayInnerReportModel>();// edpDao.getAGDeducCompoMpgData(locId);

		List<DisplayInnerReportModel> deducTyEdpList = new ArrayList<DisplayInnerReportModel>();// edpDao.getTRDeducCompoMpgData(locId);
		List<DisplayInnerReportModel> deducOthEdpList = new ArrayList<DisplayInnerReportModel>();// changes for other
																									// (nps)
		String split[] = strddo.split("_");
		strddo = split[0];
		// Dynamic process start
		System.out.println("strddo----" + strddo);
		List<DisplayInnerReportModel> allEdpList = displayInnerReportRepo.getAllDataForinnernew(strddo, billNumber);
		List<Map<String, Object>> lstempdetails1 = displayInnerReportRepo.getempDetails(String.valueOf(billNumber));

		/*
		 * DisplayInnerReportModel orElse =
		 * allEdpList.stream().filter(displayInnerReportModel->"BLWF".equals(
		 * displayInnerReportModel.getDeptalldetNm())).findAny().orElse(null);
		 * if(orElse==null) { DisplayInnerReportModel obj1 = new
		 * DisplayInnerReportModel();
		 * obj1.setDeptalldetNm(StringHelperUtils.isNullString("BLWF"));
		 * obj1.setType(2); obj1.setDeptallowdeducid(2); obj1.setTempvalue("0.00");
		 * obj1.setTempempty("0.00"); allEdpList.add(obj1); }
		 */

		Partition partition = new Partition(lstempdetails1, 8);
		int partitionsize = partition.size();
		List<DisplayInnerReportModel> returnresult = new ArrayList<DisplayInnerReportModel>();
		for (int k = 0; k < partitionsize; k++) {
			List<Map<String, Object>> lstempdetails = partition.get(k);
			allowEdpList.clear();
			deducAgEdpList.clear();
			deducOthEdpList.clear();
			deducTyEdpList.clear();
			DisplayInnerReportModel obj = new DisplayInnerReportModel();
			obj.setDeptalldetNm(StringHelperUtils.isNullString("Basic_Pay"));
			obj.setType(1);
			obj.setDeptallowdeducid(1);
			obj.setTempvalue("0.00");
			obj.setTempempty("0.00");
			allowEdpList.add(obj);

			int empsize = lstempdetails.size();
			for (int i = 0; i < allEdpList.size(); i++) {
				if (allEdpList.get(i).getType() == 1) { // allowance

					allowEdpList.add(allEdpList.get(i));
				} else if (allEdpList.get(i).getType() == 2) {

					System.out.println("gpf 2-----" + allEdpList.get(i).getDeptalldetNm());
					/*
					 * if(allEdpList.get(i).getDeptalldetNm().contains("GPF_Advance_II")){
					 * System.out.println("gpf 2-----"+allEdpList.get(i).getDeptalldetNm()); }
					 */

					if (allEdpList.get(i).getCompoType() == 2) {
						deducAgEdpList.add(allEdpList.get(i)); // Deductions Adj. By CAFO/Supri./Admin.
					} else if (allEdpList.get(i).getCompoType() == 4) {
						deducOthEdpList.add(allEdpList.get(i));
					} else {
						deducTyEdpList.add(allEdpList.get(i)); // Adjust by Treasury
					}

				} else if(allEdpList.get(i).getType() == 4) {
					if (allEdpList.get(i).getCompoType() == 2) {
						deducAgEdpList.add(allEdpList.get(i)); // Deductions Adj. By CAFO/Supri./Admin.
					} else if (allEdpList.get(i).getCompoType() == 4) {
						deducOthEdpList.add(allEdpList.get(i));
					} else {
						deducTyEdpList.add(allEdpList.get(i)); // Adjust by Treasury
					}
				}
			}

			// Dynamic Process end

			ArrayList DataList = new ArrayList();
			String billDetails = commonHomeMethodsService.findbillGrpname(billNumber);
			String officeDetails = displayOuterReportService.getOffice(strddo);
			List<Object[]> cardecode = displayOuterReportService.getcardecode(strddo);
			String carde = "";
			for (Object[] objects : cardecode) {
				if (carde != "") {
					carde += "," + objects[1].toString();
				} else {
					carde = objects[1].toString();
				}
			}

			Date createdate = displayInnerReportRepo.findbillCreateDate(billNumber);
			ArrayList headerRow = new ArrayList(); // header row
			String description = "Bill Id (" + billNumber + ") Detail Bill - " + billDetails + "-[" + carde + "]";
			headerRow.add("");
			headerRow.add("SECTION OF ESTABLISH.");
			Iterator iteratordsgn = lstempdetails.iterator();
			for (int i = 1; i <= 9; i++) {
				// allowance.add(row);
				if (i <= empsize) {
					Map<String, Object> map = (Map<String, Object>) iteratordsgn.next();
					headerRow.add(map.get("designation_name").toString());
				} else {
					headerRow.add("");
				}
			}
			headerRow.add("");

			// Employee description row started
			ArrayList orderdataList = new ArrayList();
			orderdataList.add(" ");
			ArrayList row = new ArrayList();
			row.add("NAME");
			row.add("CODE");
			row.add(" ");
			row.add("PayInPB+GP");
			row.add("BASIC PAY");
			orderdataList.add(row);
			Iterator iteratorempdtls = lstempdetails.iterator();
			for (int i = 1; i <= 9; i++) {
				// allowance.add(row);
				if (i <= empsize) {

					Map<String, Object> map = (Map<String, Object>) iteratorempdtls.next();
					row = new ArrayList();
					row.add(map.get("employee_full_name_en").toString());
					row.add(map.get("sevaarth_id").toString());
					if (map.get("pay_commission_code") != null) {
						if (map.get("pay_commission_code").equals("700005")) {
							row.add("7th Pay Commission");
						} else {
							row.add(map.get("pay_commission_code").toString());
						}
					} else {
						row.add("-");
					}
					if (map.get("payband") != null) {
						row.add(map.get("payband").toString());
					} else {
						row.add("-");// found
					}
					row.add(map.get("basic_pay").toString());
					orderdataList.add(row);

				} else {
					if (empsize + 1 == i) {
						row = new ArrayList();
						row.add("Page");
						row.add(currentPage + 1);
						row.add("Total");
						orderdataList.add(row);
					} else {
						orderdataList.add("");
					}
				}

			}
			orderdataList.add("");

			// Employee description row ended

			// SL No and Remark start
			ArrayList slno = new ArrayList();
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
			// SL No and Remark end

			// allowance started##########################################

			ArrayList allowance = new ArrayList();
			row = new ArrayList();
			for (int i = 1; i <= allowEdpList.size(); i++) {
				row.add(String.valueOf(i));
			}
			allowance.add(row);
			row = new ArrayList();
			for (Iterator iterator = allowEdpList.iterator(); iterator.hasNext();) {
				DisplayInnerReportModel object = (DisplayInnerReportModel) iterator.next();
				row.add(object.getDeptalldetNm());
			}
			allowance.add(row);

			Iterator iterator1 = lstempdetails.iterator();

			Map<String, Double> mapAllowanceSum = new HashMap<>();

			for (int i = 1; i <= 9; i++) {
				if (i <= empsize) {
					Map<String, Object> map = (Map<String, Object>) iterator1.next();
					row = new ArrayList();
					/*
					 * if(map.get("sevaarth_id").toString().equals("MJPPRTM8401")) {
					 * System.out.println("---------"+map.get("sevaarth_id").toString()); }
					 */

					for (Iterator iterator = allowEdpList.iterator(); iterator.hasNext();) {

						DisplayInnerReportModel object = (DisplayInnerReportModel) iterator.next();
						try {
							String allname = object.getDeptalldetNm();
							row.add(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
							if (mapAllowanceSum.get(object.getDeptalldetNm().toLowerCase().trim()) != null) {
								Double amount = mapAllowanceSum.get(object.getDeptalldetNm().toLowerCase().trim())
										+ Double.parseDouble(
												map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
								String pattern = "##.##";
								DecimalFormat decimalFormat = new DecimalFormat(pattern);
								Double amt = Double.valueOf(decimalFormat.format(amount));
								mapAllowanceSum.put(object.getDeptalldetNm().toLowerCase().trim(), amt.doubleValue());
							} else {
								mapAllowanceSum.put(object.getDeptalldetNm().toLowerCase().trim(), Double.parseDouble(
										map.get(object.getDeptalldetNm().toLowerCase().trim()).toString()));
							}
						} catch (Exception e) {
							row.add("0.00");
							if (mapAllowanceSum.get(object.getDeptalldetNm().toLowerCase().trim()) != null) {
								Double amount = mapAllowanceSum.get(object.getDeptalldetNm().toLowerCase().trim());
								String pattern = "##.##";
								DecimalFormat decimalFormat = new DecimalFormat(pattern);
								Double amt = Double.valueOf(decimalFormat.format(amount));
								mapAllowanceSum.put(object.getDeptalldetNm().toLowerCase().trim(), amt.doubleValue());
							} else {
								mapAllowanceSum.put(object.getDeptalldetNm().toLowerCase().trim(), 0D);
							}
						}

					}
				} else {
					if (empsize + 1 == i) {
						row = new ArrayList();
						for (DisplayInnerReportModel object : allowEdpList) {
							row.add(mapAllowanceSum.get(object.getDeptalldetNm().toLowerCase().trim()));
						}
					} else {
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
			// allowance ended##########################################

			// ded AG started##########################################
			ArrayList lstdedectionag = new ArrayList();
			Map<String, Double> maplstdedectionagSum = new HashMap<>();
			row = new ArrayList();
			for (int i = 1; i <= deducAgEdpList.size(); i++) {
				row.add(String.valueOf(i + allowEdpList.size()));
			}
			lstdedectionag.add(row);
			row = new ArrayList();
			for (Iterator iterator = deducAgEdpList.iterator(); iterator.hasNext();) {
				DisplayInnerReportModel object = (DisplayInnerReportModel) iterator.next();
				row.add(object.getDeptalldetNm());
			}
			lstdedectionag.add(row);
			Iterator iterator2 = lstempdetails.iterator();
			for (int i = 1; i <= 9; i++) {
				row = new ArrayList();
				if (i <= empsize) {
					Map<String, Object> map = (Map<String, Object>) iterator2.next();
					row = new ArrayList();
					for (Iterator iterator = deducAgEdpList.iterator(); iterator.hasNext();) {
						DisplayInnerReportModel object = (DisplayInnerReportModel) iterator.next();
						try {
							String allname = object.getDeptalldetNm();
							row.add(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
							if (maplstdedectionagSum.get(object.getDeptalldetNm().toLowerCase().trim()) != null) {
								Double amount = maplstdedectionagSum.get(object.getDeptalldetNm().toLowerCase().trim())
										+ Double.parseDouble(
												map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
								String pattern = "##.##";
								DecimalFormat decimalFormat = new DecimalFormat(pattern);
								Double amt = Double.valueOf(decimalFormat.format(amount));
								maplstdedectionagSum.put(object.getDeptalldetNm().toLowerCase().trim(),
										amt.doubleValue());
							} else {
								maplstdedectionagSum.put(object.getDeptalldetNm().toLowerCase().trim(),
										Double.parseDouble(
												map.get(object.getDeptalldetNm().toLowerCase().trim()).toString()));
							}
						} catch (Exception e) {
							row.add("0.00");
							if (maplstdedectionagSum.get(object.getDeptalldetNm().toLowerCase().trim()) != null) {
								Double amount = maplstdedectionagSum.get(object.getDeptalldetNm().toLowerCase().trim());
								String pattern = "##.##";
								DecimalFormat decimalFormat = new DecimalFormat(pattern);
								Double amt = Double.valueOf(decimalFormat.format(amount));
								maplstdedectionagSum.put(object.getDeptalldetNm().toLowerCase().trim(),
										amt.doubleValue());
							} else {
								maplstdedectionagSum.put(object.getDeptalldetNm().toLowerCase().trim(), 0D);
							}
						}

					}
				} else {
					if (empsize + 1 == i) {
						row = new ArrayList();
						for (DisplayInnerReportModel object : deducAgEdpList) {
							row.add(maplstdedectionagSum.get(object.getDeptalldetNm().toLowerCase().trim()));
						}
					} else {
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
				row.add(String.valueOf(i + allowEdpList.size()));
			}
			lstdedectionag.add(row);
			// ded AG ended##########################################

			// ded OTHER started##########################################
			ArrayList lstdeducother = new ArrayList();
			Map<String, Double> maplstdeducotherSum = new HashMap<>();
			Double fasum = 0d;
			Double vasum = 0d;
			Double casum = 0d;
			Double hbasum = 0d;
			Double hbaintamtsum = 0d;
			Double gpasum = 0d;
			Double gpasumII = 0d;
			Double payfixsum = 0d;
			row = new ArrayList();
			for (int i = 1; i <= deducOthEdpList.size(); i++) {
				row.add(String.valueOf(i + allowEdpList.size() + deducAgEdpList.size() + deducTyEdpList.size()));
			}
			lstdeducother.add(row);
			row = new ArrayList();
			for (Iterator iterator = deducOthEdpList.iterator(); iterator.hasNext();) {
				DisplayInnerReportModel object = (DisplayInnerReportModel) iterator.next();
				row.add(object.getDeptalldetNm());
			}
			lstdeducother.add(row);
			Iterator iterator3 = lstempdetails.iterator();

			for (int i = 1; i <= 9; i++) {
				// allowance.add(row);
				// allowance.add(row);
				if (i <= empsize) {
					Map<String, Object> map = (Map<String, Object>) iterator3.next();
					row = new ArrayList();
					for (Iterator iterator = deducOthEdpList.iterator(); iterator.hasNext();) {
						String allname = "";
						DisplayInnerReportModel object = (DisplayInnerReportModel) iterator.next();
						try {
							allname = object.getDeptalldetNm();

							if (allname.contains("GPF_Advance_II")) {
								System.out.println("gpf 2-----");
							}

							if (allname.equals("festival_advance")) {
								String lstfaLoan = null;
								if (map.get(object.getDeptalldetNm().toLowerCase().trim()).toString() != null) {
									Double amount = Double.parseDouble(
											map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
									// Double amount =
									// maplstdeducotherSum.get(object.getDeptalldetNm().toLowerCase().trim()) +
									// Double.parseDouble(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
									if (amount > 0d) {
										lstfaLoan = displayInnerReportRepo
												.getfaLoanDtls(map.get("sevaarth_id").toString(), billNumber);
									}
								}

								Double amount = 0d;
								if (lstfaLoan != null) {
									row.add(lstfaLoan);
									maplstdeducotherSum.put(object.getDeptalldetNm().toLowerCase().trim(), amount);
									if (maplstdeducotherSum
											.get(object.getDeptalldetNm().toLowerCase().trim()) != null) {
										amount = Double.parseDouble(
												map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
										String pattern = "##.##";
										DecimalFormat decimalFormat = new DecimalFormat(pattern);
										Double amt = Double.valueOf(decimalFormat.format(amount));
										fasum = fasum + amt;
										maplstdeducotherSum.put(object.getDeptalldetNm().toLowerCase().trim(),
												fasum.doubleValue());
									} else {
										maplstdeducotherSum.put(object.getDeptalldetNm().toLowerCase().trim(),
												Double.parseDouble(
														map.get(object.getDeptalldetNm().toLowerCase().trim())
																.toString()));
									}

								} else {
									row.add("0.00");
									amount = Double.parseDouble(
											map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
									if (amount > 0d) {
										maplstdeducotherSum.put(object.getDeptalldetNm().toLowerCase().trim(),
												Double.parseDouble(
														map.get(object.getDeptalldetNm().toLowerCase().trim())
																.toString()));
									} else {
										maplstdedectionagSum.put(object.getDeptalldetNm().toLowerCase().trim(), 0D);
									}
								}

							}

							else if (allname.equals("OTHER_REC")) {
								String lstvaLoan = null;
								if (map.get(object.getDeptalldetNm().toLowerCase().trim()).toString() != null) {
									Double amount = Double.parseDouble(
											map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
									// Double amount =
									// maplstdeducotherSum.get(object.getDeptalldetNm().toLowerCase().trim()) +
									// Double.parseDouble(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
									if (amount > 0d)
										lstvaLoan = displayInnerReportRepo
												.getvaLoanDtls(map.get("sevaarth_id").toString(), billNumber);
								}
								Double amount = 0d;
								if (lstvaLoan != null) {
									row.add(lstvaLoan);
									maplstdeducotherSum.put(object.getDeptalldetNm().toLowerCase().trim(), amount);
									if (maplstdeducotherSum
											.get(object.getDeptalldetNm().toLowerCase().trim()) != null) {
										amount = Double.parseDouble(
												map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
										String pattern = "##.##";
										DecimalFormat decimalFormat = new DecimalFormat(pattern);
										Double amt = Double.valueOf(decimalFormat.format(amount));
										vasum = vasum + amt;
										maplstdeducotherSum.put(object.getDeptalldetNm().toLowerCase().trim(),
												vasum.doubleValue());
									} else {
										maplstdeducotherSum.put(object.getDeptalldetNm().toLowerCase().trim(),
												Double.parseDouble(
														map.get(object.getDeptalldetNm().toLowerCase().trim())
																.toString()));
									}
								} else {
									row.add("0.00");
									amount = Double.parseDouble(
											map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
									if (amount > 0d) {
										maplstdeducotherSum.put(object.getDeptalldetNm().toLowerCase().trim(),
												Double.parseDouble(
														map.get(object.getDeptalldetNm().toLowerCase().trim())
																.toString()));
									} else {
										maplstdedectionagSum.put(object.getDeptalldetNm().toLowerCase().trim(), 0D);
									}
								}
							} else if (allname.equals("COMP_ADV")) {
								String lstcaLoan = null;
								if (map.get(object.getDeptalldetNm().toLowerCase().trim()).toString() != null) {
									Double amount = Double.parseDouble(
											map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
									// Double amount =
									// maplstdeducotherSum.get(object.getDeptalldetNm().toLowerCase().trim()) +
									// Double.parseDouble(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
									if (amount > 0d)
										lstcaLoan = displayInnerReportRepo
												.getcaLoanDtls(map.get("sevaarth_id").toString(), billNumber);
								}
								Double amount = 0d;
								if (lstcaLoan != null) {
									row.add(lstcaLoan);
									maplstdeducotherSum.put(object.getDeptalldetNm().toLowerCase().trim(), amount);
									if (maplstdeducotherSum
											.get(object.getDeptalldetNm().toLowerCase().trim()) != null) {
										amount = Double.parseDouble(
												map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
										String pattern = "##.##";
										DecimalFormat decimalFormat = new DecimalFormat(pattern);
										Double amt = Double.valueOf(decimalFormat.format(amount));
										casum = casum + amt;
										maplstdeducotherSum.put(object.getDeptalldetNm().toLowerCase().trim(),
												casum.doubleValue());
									} else {
										maplstdeducotherSum.put(object.getDeptalldetNm().toLowerCase().trim(),
												Double.parseDouble(
														map.get(object.getDeptalldetNm().toLowerCase().trim())
																.toString()));
									}
								} else {
									row.add("0.00");
									amount = Double.parseDouble(
											map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
									if (amount > 0d) {
										maplstdeducotherSum.put(object.getDeptalldetNm().toLowerCase().trim(),
												Double.parseDouble(
														map.get(object.getDeptalldetNm().toLowerCase().trim())
																.toString()));
									} else {
										maplstdedectionagSum.put(object.getDeptalldetNm().toLowerCase().trim(), 0D);
									}
								}
							} else if (allname.equals("HBA_HOUSE")) {
								String lsthbaLoan = null;
								if (map.get(object.getDeptalldetNm().toLowerCase().trim()).toString() != null) {
									Double amount = Double.parseDouble(
											map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
									// Double amount =
									// maplstdeducotherSum.get(object.getDeptalldetNm().toLowerCase().trim()) +
									// Double.parseDouble(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
									if (amount > 0d)
										lsthbaLoan = displayInnerReportRepo
												.gethbaLoanDtls(map.get("sevaarth_id").toString(), billNumber);
								}
								System.out.println("-------lsthbaLoan----" + lsthbaLoan);
								Double amount = 0d;
								if (lsthbaLoan != null) {
									row.add(lsthbaLoan);
									System.out.println("-------lsthbaLoan" + lsthbaLoan);
									maplstdeducotherSum.put(object.getDeptalldetNm().toLowerCase().trim(), amount);
									if (maplstdeducotherSum
											.get(object.getDeptalldetNm().toLowerCase().trim()) != null) {
										amount = Double.parseDouble(
												map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
										String pattern = "##.##";
										DecimalFormat decimalFormat = new DecimalFormat(pattern);
										Double amt = Double.valueOf(decimalFormat.format(amount));
										hbasum = hbasum + amt;
										maplstdeducotherSum.put(object.getDeptalldetNm().toLowerCase().trim(),
												hbasum.doubleValue());
									} else {
										maplstdeducotherSum.put(object.getDeptalldetNm().toLowerCase().trim(),
												Double.parseDouble(
														map.get(object.getDeptalldetNm().toLowerCase().trim())
																.toString()));
									}
								} else {
									row.add("0.00");
									amount = Double.parseDouble(
											map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
									if (amount > 0d) {
										maplstdeducotherSum.put(object.getDeptalldetNm().toLowerCase().trim(),
												Double.parseDouble(
														map.get(object.getDeptalldetNm().toLowerCase().trim())
																.toString()));
									} else {
										maplstdedectionagSum.put(object.getDeptalldetNm().toLowerCase().trim(), 0D);
									}
								}
							} else if (allname.equals("HBA_HOUSE_INT_AMT")) {
								String lsthbaLoan = null;
								if (map.get(object.getDeptalldetNm().toLowerCase().trim()).toString() != null) {
									Double amount = Double.parseDouble(
											map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
									// Double amount =
									// maplstdeducotherSum.get(object.getDeptalldetNm().toLowerCase().trim()) +
									// Double.parseDouble(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
									if (amount > 0d)
										lsthbaLoan = displayInnerReportRepo
												.gethbaLoanIntsDtls(map.get("sevaarth_id").toString(), billNumber);
								}
								System.out.println("-------lsthbaLoan----" + lsthbaLoan);
								Double amount = 0d;
								if (lsthbaLoan != null) {
									row.add(lsthbaLoan);
									System.out.println("-------lsthbaLoan" + lsthbaLoan);
									maplstdeducotherSum.put(object.getDeptalldetNm().toLowerCase().trim(), amount);
									if (maplstdeducotherSum
											.get(object.getDeptalldetNm().toLowerCase().trim()) != null) {
										amount = Double.parseDouble(
												map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
										String pattern = "##.##";
										DecimalFormat decimalFormat = new DecimalFormat(pattern);
										Double amt = Double.valueOf(decimalFormat.format(amount));
										hbaintamtsum = hbaintamtsum + amt;
										maplstdeducotherSum.put(object.getDeptalldetNm().toLowerCase().trim(),
												hbaintamtsum.doubleValue());
									} else {
										maplstdeducotherSum.put(object.getDeptalldetNm().toLowerCase().trim(),
												Double.parseDouble(
														map.get(object.getDeptalldetNm().toLowerCase().trim())
																.toString()));
									}
								} else {
									row.add("0.00");
									amount = Double.parseDouble(
											map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
									if (amount > 0d) {
										maplstdedectionagSum.put(object.getDeptalldetNm().toLowerCase().trim(),
												Double.parseDouble(
														map.get(object.getDeptalldetNm().toLowerCase().trim())
																.toString()));
									} else {
										maplstdedectionagSum.put(object.getDeptalldetNm().toLowerCase().trim(), 0D);
									}
								}
							} else if (allname.equals("Excess_Pay_Rec")) {
								String lsthbaLoan = null;
								if (map.get(object.getDeptalldetNm().toLowerCase().trim()).toString() != null) {
									Double amount = Double.parseDouble(
											map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
									// Double amount =
									// maplstdeducotherSum.get(object.getDeptalldetNm().toLowerCase().trim()) +
									// Double.parseDouble(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
									if (amount > 0d)
										lsthbaLoan = displayInnerReportRepo
												.getexPayRecDtls(map.get("sevaarth_id").toString(), billNumber);
								}
								System.out.println("-------lsthbaLoan----" + lsthbaLoan);
								Double amount = 0d;
								if (lsthbaLoan != null) {
									row.add(lsthbaLoan);
									System.out.println("-------lsthbaLoan" + lsthbaLoan);
									maplstdeducotherSum.put(object.getDeptalldetNm().toLowerCase().trim(), amount);
									if (maplstdeducotherSum
											.get(object.getDeptalldetNm().toLowerCase().trim()) != null) {
										amount = Double.parseDouble(
												map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
										String pattern = "##.##";
										DecimalFormat decimalFormat = new DecimalFormat(pattern);
										Double amt = Double.valueOf(decimalFormat.format(amount));
										hbaintamtsum = hbaintamtsum + amt;
										maplstdeducotherSum.put(object.getDeptalldetNm().toLowerCase().trim(),
												hbaintamtsum.doubleValue());
									} else {
										maplstdeducotherSum.put(object.getDeptalldetNm().toLowerCase().trim(),
												Double.parseDouble(
														map.get(object.getDeptalldetNm().toLowerCase().trim())
																.toString()));
									}
								} else {
									row.add("0.00");
									amount = Double.parseDouble(
											map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
									if (amount > 0d) {
										maplstdedectionagSum.put(object.getDeptalldetNm().toLowerCase().trim(),
												Double.parseDouble(
														map.get(object.getDeptalldetNm().toLowerCase().trim())
																.toString()));
									} else {
										maplstdedectionagSum.put(object.getDeptalldetNm().toLowerCase().trim(), 0D);
									}
								}
							} else if (allname.equals("GPF_Advance")) {
								String lstgpfLoan = null;
								if (map.get(object.getDeptalldetNm().toLowerCase().trim()).toString() != null) {
									Double amount = Double.parseDouble(
											map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
									// Double amount =
									// maplstdeducotherSum.get(object.getDeptalldetNm().toLowerCase().trim()) +
									// Double.parseDouble(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
									if (amount > 0d)
										lstgpfLoan = displayInnerReportRepo
												.getgpfLoanDtls(map.get("sevaarth_id").toString(), billNumber);
								}
								Double amount = 0d;
								if (lstgpfLoan != null) {
									row.add(lstgpfLoan);
									maplstdeducotherSum.put(object.getDeptalldetNm().toLowerCase().trim(), amount);
									if (maplstdeducotherSum
											.get(object.getDeptalldetNm().toLowerCase().trim()) != null) {
										amount = Double.parseDouble(
												map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
										String pattern = "##.##";
										DecimalFormat decimalFormat = new DecimalFormat(pattern);
										Double amt = Double.valueOf(decimalFormat.format(amount));
										gpasum = gpasum + amt;
										maplstdeducotherSum.put(object.getDeptalldetNm().toLowerCase().trim(),
												gpasum.doubleValue());
									} else {
										maplstdeducotherSum.put(object.getDeptalldetNm().toLowerCase().trim(),
												Double.parseDouble(
														map.get(object.getDeptalldetNm().toLowerCase().trim())
																.toString()));
									}
								} else {
									row.add("0.00");
									amount = Double.parseDouble(
											map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
									if (amount > 0d) {
										maplstdeducotherSum.put(object.getDeptalldetNm().toLowerCase().trim(),
												Double.parseDouble(
														map.get(object.getDeptalldetNm().toLowerCase().trim())
																.toString()));
									} else {
										maplstdedectionagSum.put(object.getDeptalldetNm().toLowerCase().trim(), 0D);
									}
								}
							} else if (allname.equals("GPF_Advance_II")) {
								String lstgpfLoanII = null;
								if (map.get(object.getDeptalldetNm().toLowerCase().trim()).toString() != null) {
									Double amount = Double.parseDouble(
											map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
									// Double amount =
									// maplstdeducotherSum.get(object.getDeptalldetNm().toLowerCase().trim()) +
									// Double.parseDouble(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
									if (amount > 0d)
										lstgpfLoanII = displayInnerReportRepo
												.getgpfIILoanDtls(map.get("sevaarth_id").toString(), billNumber);
								}
								Double amount = 0d;
								if (lstgpfLoanII != null) {
									row.add(lstgpfLoanII);
									maplstdeducotherSum.put(object.getDeptalldetNm().toLowerCase().trim(), amount);
									if (maplstdeducotherSum
											.get(object.getDeptalldetNm().toLowerCase().trim()) != null) {
										amount = Double.parseDouble(
												map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
										String pattern = "##.##";
										DecimalFormat decimalFormat = new DecimalFormat(pattern);
										Double amt = Double.valueOf(decimalFormat.format(amount));
										gpasumII = gpasumII + amt;
										maplstdeducotherSum.put(object.getDeptalldetNm().toLowerCase().trim(),
												gpasumII.doubleValue());
									} else {
										maplstdeducotherSum.put(object.getDeptalldetNm().toLowerCase().trim(),
												Double.parseDouble(
														map.get(object.getDeptalldetNm().toLowerCase().trim())
																.toString()));
									}
								} else {
									row.add("0.00");
									amount = Double.parseDouble(
											map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
									if (amount > 0d) {
										maplstdeducotherSum.put(object.getDeptalldetNm().toLowerCase().trim(),
												Double.parseDouble(
														map.get(object.getDeptalldetNm().toLowerCase().trim())
																.toString()));
									} else {
										maplstdedectionagSum.put(object.getDeptalldetNm().toLowerCase().trim(), 0D);
									}
								}
							} else if (allname.equals("Pay_Fix_Diff")) {// Pay_Fix_Diff
								String lstpayfixdiffLoan = null;
								if (map.get(object.getDeptalldetNm().toLowerCase().trim()).toString() != null) {
									Double amount = Double.parseDouble(
											map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
									// Double amount =
									// maplstdeducotherSum.get(object.getDeptalldetNm().toLowerCase().trim()) +
									// Double.parseDouble(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
									if (amount > 0d)
										lstpayfixdiffLoan = displayInnerReportRepo
												.getPayFixDiffLoanDtls(map.get("sevaarth_id").toString(), billNumber);
								}
								Double amount = 0d;
								if (lstpayfixdiffLoan != null) {
									row.add(lstpayfixdiffLoan);
									maplstdeducotherSum.put(object.getDeptalldetNm().toLowerCase().trim(), amount);
									if (maplstdeducotherSum
											.get(object.getDeptalldetNm().toLowerCase().trim()) != null) {
										amount = Double.parseDouble(
												map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
										String pattern = "##.##";
										DecimalFormat decimalFormat = new DecimalFormat(pattern);
										Double amt = Double.valueOf(decimalFormat.format(amount));
										payfixsum = payfixsum + amt;
										maplstdeducotherSum.put(object.getDeptalldetNm().toLowerCase().trim(),
												payfixsum.doubleValue());
									} else {
										maplstdeducotherSum.put(object.getDeptalldetNm().toLowerCase().trim(),
												Double.parseDouble(
														map.get(object.getDeptalldetNm().toLowerCase().trim())
																.toString()));
									}
								} else {
									row.add("0.00");
									amount = Double.parseDouble(
											map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
									if (amount > 0d) {
										maplstdeducotherSum.put(object.getDeptalldetNm().toLowerCase().trim(),
												Double.parseDouble(
														map.get(object.getDeptalldetNm().toLowerCase().trim())
																.toString()));
									} else {
										maplstdedectionagSum.put(object.getDeptalldetNm().toLowerCase().trim(), 0D);
									}
								}
							} else if (!allname.equals("FA") || !allname.equals("OTHER_VEH_ADV")
									|| !allname.equals("COMP_ADV") || !allname.equals("HBA_HOUSE")
									|| !allname.equals("GPF_Advance") || !allname.equals("OTHER_DEDUCTION")
									|| !allname.equals("HBA_HOUSE_INT") || !allname.equals("OTHER_REC")
									|| !allname.equals("OTHER_VEH_ADV_INT") || !allname.equals("Pay_Fix_Diff")
									|| !allname.equals("GPF_Advance")) {
								row.add(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
								if (maplstdeducotherSum.get(object.getDeptalldetNm().toLowerCase().trim()) != null) {
									Double amount = maplstdeducotherSum
											.get(object.getDeptalldetNm().toLowerCase().trim())
											+ Double.parseDouble(
													map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
									String pattern = "##.##";
									DecimalFormat decimalFormat = new DecimalFormat(pattern);
									Double amt = Double.valueOf(decimalFormat.format(amount));
									maplstdeducotherSum.put(object.getDeptalldetNm().toLowerCase().trim(),
											amt.doubleValue());
								} else {
									maplstdeducotherSum.put(object.getDeptalldetNm().toLowerCase().trim(),
											Double.parseDouble(
													map.get(object.getDeptalldetNm().toLowerCase().trim()).toString()));
								}
							}

						} catch (Exception e) {
							if (!allname.equals("FA") || !allname.equals("OTHER_VEH_ADV") || !allname.equals("COMP_ADV")
									|| !allname.equals("HBA_HOUSE") || !allname.equals("GPF_Advance")
									|| !allname.equals("OTHER_DEDUCTION") || !allname.equals("HBA_HOUSE_INT")
									|| !allname.equals("OTHER_REC") || !allname.equals("OTHER_VEH_ADV_INT")
									|| !allname.equals("Pay_Fix_Diff") || !allname.equals("GPF_Advance")) {
								row.add("0.00");
							} else {
							}
							if (maplstdeducotherSum.get(object.getDeptalldetNm().toLowerCase().trim()) != null) {
								Double amount = maplstdeducotherSum.get(object.getDeptalldetNm().toLowerCase().trim());
								String pattern = "##.##";
								DecimalFormat decimalFormat = new DecimalFormat(pattern);
								Double amt = Double.valueOf(decimalFormat.format(amount));
								maplstdeducotherSum.put(object.getDeptalldetNm().toLowerCase().trim(),
										amt.doubleValue());
							} else {
								maplstdeducotherSum.put(object.getDeptalldetNm().toLowerCase().trim(), 0D);
							}
						}

					}
				} else {
					if (empsize + 1 == i) {
						row = new ArrayList();
						for (DisplayInnerReportModel object : deducOthEdpList) {
							if (maplstdeducotherSum.get(object.getDeptalldetNm().toLowerCase().trim()) != null) {
								row.add(maplstdeducotherSum.get(object.getDeptalldetNm().toLowerCase().trim()));
							} else {
								row.add("0.00");
							}
						}
					} else {
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
				row.add(String.valueOf(i + allowEdpList.size() + deducAgEdpList.size() + deducTyEdpList.size()));
			}
			lstdeducother.add(row);
			// ded OTHER ended##########################################

			// ded TY started##########################################

			ArrayList lstdeductrsy = new ArrayList();
			Map<String, Double> maplstdeductrsySum = new HashMap<>();
			row = new ArrayList();
			for (int i = 1; i <= deducTyEdpList.size(); i++) {
				row.add(String.valueOf(i + allowEdpList.size() + deducAgEdpList.size()));
			}
			lstdeductrsy.add(row);
			row = new ArrayList();
			for (Iterator iterator = deducTyEdpList.iterator(); iterator.hasNext();) {
				DisplayInnerReportModel object = (DisplayInnerReportModel) iterator.next();
				row.add(object.getDeptalldetNm());
			}
			lstdeductrsy.add(row);
			Iterator iterator4 = lstempdetails.iterator();
			for (int i = 1; i <= 9; i++) {
				if (i <= empsize) {
					Map<String, Object> map = (Map<String, Object>) iterator4.next();
					row = new ArrayList();
					for (Iterator iterator = deducTyEdpList.iterator(); iterator.hasNext();) {
						DisplayInnerReportModel object = (DisplayInnerReportModel) iterator.next();
						try {
							String allname = object.getDeptalldetNm();

							row.add(map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
							if (maplstdeductrsySum.get(object.getDeptalldetNm().toLowerCase().trim()) != null) {
								Double amount = maplstdeductrsySum.get(object.getDeptalldetNm().toLowerCase().trim())
										+ Double.parseDouble(
												map.get(object.getDeptalldetNm().toLowerCase().trim()).toString());
								String pattern = "##.##";
								DecimalFormat decimalFormat = new DecimalFormat(pattern);
								Double amt = Double.valueOf(decimalFormat.format(amount));

								maplstdeductrsySum.put(object.getDeptalldetNm().toLowerCase().trim(),
										amt.doubleValue());
							} else {
								maplstdeductrsySum.put(object.getDeptalldetNm().toLowerCase().trim(),
										Double.parseDouble(
												map.get(object.getDeptalldetNm().toLowerCase().trim()).toString()));
							}
						} catch (Exception e) {
							row.add("0.00");
							if (maplstdeductrsySum.get(object.getDeptalldetNm().toLowerCase().trim()) != null) {
								Double amount = maplstdeductrsySum.get(object.getDeptalldetNm().toLowerCase().trim());
								String pattern = "##.##";
								DecimalFormat decimalFormat = new DecimalFormat(pattern);
								Double amt = Double.valueOf(decimalFormat.format(amount));
								maplstdeductrsySum.put(object.getDeptalldetNm().toLowerCase().trim(),
										amt.doubleValue());
							} else {
								maplstdeductrsySum.put(object.getDeptalldetNm().toLowerCase().trim(), 0D);
							}
						}

					}
				} else {
					if (empsize + 1 == i) {
						row = new ArrayList();
						for (DisplayInnerReportModel object : deducTyEdpList) {
							row.add(maplstdeductrsySum.get(object.getDeptalldetNm().toLowerCase().trim()));
						}
					} else {
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
				row.add(String.valueOf(i + allowEdpList.size() + deducAgEdpList.size()));
			}
			lstdeductrsy.add(row);
			// ded TY ended##########################################
			// gross total row started
			ArrayList algrossamt = new ArrayList();
			algrossamt.add("");
			algrossamt.add("TOTAL");
			Iterator iteratorgrossamt = lstempdetails.iterator();
			Double grossTotalSum = 0d;

			for (int i = 1; i <= 9; i++) {
				if (i <= empsize) {
					Map<String, Object> map = (Map<String, Object>) iteratorgrossamt.next();
					algrossamt.add(map.get("gross_amt").toString());
					grossTotalSum += Double.parseDouble(map.get("gross_amt").toString());
				} else {
					algrossamt.add(" ");
				}
				if (empsize == i) {
					algrossamt.add(Math.round(grossTotalSum));
				}
			}

			// algrossamt.add(" ");
			// gross total row ended

			// gross sale row started
			ArrayList algrosssale = new ArrayList();
			algrosssale.add("");
			algrosssale.add("GROSS SAL");
			Iterator iteratorgrossale = lstempdetails.iterator();
			Double algrosssaleSum = 0d;
			for (int i = 1; i <= 9; i++) {
				if (i <= empsize) {
					Map<String, Object> map = (Map<String, Object>) iteratorgrossale.next();
					algrosssale.add(map.get("gross_amt").toString());
					algrosssaleSum += Double.parseDouble(map.get("gross_amt").toString());
				} else {
					algrosssale.add("");
				}
				if (empsize == i) {
					algrosssale.add(Math.round(algrosssaleSum));
				}
			}
			// algrosssale.add("");
			// gross sale row ended

			// gross total row started
			ArrayList algrosstotal = new ArrayList();
			algrosstotal.add("");
			algrosstotal.add("GROSS TOT");
			Iterator iteratorgrosstotal = lstempdetails.iterator();
			Double algrosstotalSum = 0d;
			for (int i = 1; i <= 9; i++) {
				if (i <= empsize) {
					Map<String, Object> map = (Map<String, Object>) iteratorgrosstotal.next();
					algrosstotal.add(map.get("gross_amt").toString());
					algrosstotalSum += Double.parseDouble(map.get("gross_amt").toString());
				} else {
					algrosstotal.add("");
				}
				if (empsize == i) {
					algrosstotal.add(Math.round(algrosstotalSum));
				}
			}
			// algrosstotal.add("");
			// gross total row ended

			
			// net total row started
			ArrayList alnetamt = new ArrayList();
			alnetamt.add("");
			alnetamt.add("NET");
			Iterator iteratornetamt = lstempdetails.iterator();
			Double alnetamtSum = 0d;
			for (int i = 1; i <= 9; i++) {
				if (i <= empsize) {
					Map<String, Object> map = (Map<String, Object>) iteratornetamt.next();
					alnetamt.add(map.get("net_total").toString());
					alnetamtSum += Double.parseDouble(map.get("net_total").toString());
				} else {
					alnetamt.add(" ");
				}
				if (empsize == i) {
					alnetamt.add(Math.round(alnetamtSum));
				}
			}
			// alnetamt.add(" ");
			// net total row ended

			// DEDUCTIONS ADJUSTABLE BY AG row started
			ArrayList altotal_deduction = new ArrayList();
			altotal_deduction.add("");
			altotal_deduction.add("Total AG Ded.");
			Iterator iteratortotal_deduction = lstempdetails.iterator();
			Double altotal_deductionSum = 0d;
			for (int i = 1; i <= 9; i++) {
				if (i <= empsize) {
					Map<String, Object> map = (Map<String, Object>) iteratortotal_deduction.next();
					if (map.get("deduc_adj_ag") != null) {
						altotal_deduction.add(map.get("deduc_adj_ag").toString());
						altotal_deductionSum += Double.parseDouble(map.get("deduc_adj_ag").toString());
					}
				} else {
					altotal_deduction.add(" ");
				}
				if (empsize == i) {
					altotal_deduction.add(Math.round(altotal_deductionSum));
				}
			}
			// alnetamt.add(" ");
			// DEDUCTIONS ADJUSTABLE BY AG row ended

			// DEDUCTIONS ADJUSTABLE BY TRY started
			ArrayList aldeduct_adj_try = new ArrayList();
			aldeduct_adj_try.add("");
			aldeduct_adj_try.add("Tot.TRY.Ded");
			Iterator iteratoraldeduct_adj_try = lstempdetails.iterator();
			Double aldeduct_adj_trySum = 0d;
			for (int i = 1; i <= 9; i++) {
				if (i <= empsize) {
					Map<String, Object> map = (Map<String, Object>) iteratoraldeduct_adj_try.next();
					if (map.get("other_ded_try") != null) {
						aldeduct_adj_try.add(map.get("other_ded_try").toString());
						aldeduct_adj_trySum += Double.parseDouble(map.get("other_ded_try").toString());
					}
				} else {
					aldeduct_adj_try.add(" ");
				}
				if (empsize == i) {
					aldeduct_adj_try.add(Math.round(aldeduct_adj_trySum));
				}
			}
			// alnetamt.add(" ");
			// DEDUCTIONS ADJUSTABLE BY TRY ended

			// DEDUCTIONS ADJUSTABLE BY OTHERS row started
			ArrayList aldeduct_adj_otr = new ArrayList();
			aldeduct_adj_otr.add("");
			aldeduct_adj_otr.add("Tot.Ded.Adj");
			Iterator iteratoraldeduct_adj_otr = lstempdetails.iterator();
			Double aldeduct_adj_otrSum = 0d;
			for (int i = 1; i <= 9; i++) {
				if (i <= empsize) {
					Map<String, Object> map = (Map<String, Object>) iteratoraldeduct_adj_otr.next();
					if (map.get("deduct_adj_otr") != null) {
						aldeduct_adj_otr.add(map.get("deduct_adj_otr").toString());
						aldeduct_adj_otrSum += Double.parseDouble(map.get("deduct_adj_otr").toString());
					}
				} else {
					aldeduct_adj_otr.add(" ");
				}
				if (empsize == i) {
					aldeduct_adj_otr.add(Math.round(aldeduct_adj_otrSum));
				}
			}
			// alnetamt.add(" ");
			// DEDUCTIONS ADJUSTABLE BY OTHERS row ended

			// DEDUCTIONS ADJUSTABLE BY TRY ended

			// DEDUCTIONS ADJUSTABLE BY OTHERS row started
			ArrayList abcloans = new ArrayList();
			abcloans.add("");
			abcloans.add("");
			Iterator iteratorabcloans = lstempdetails.iterator();
			for (int i = 1; i <= 9; i++) {
				if (i <= empsize) {
					Map<String, Object> map = (Map<String, Object>) iteratorabcloans.next();
					if (map.get("gpf_adv_grp_abc") != null || map.get("gpf_adv_grp_d") != null
							|| map.get("EXC_PAYRC") != null) {

						String loanints = "";
						Long mon = 0l;
						Long yer = 0l;
						List<Object[]> createdatenew = commonHomeMethodsService.findDetailsBillNumber(billNumber);
						for (Object[] objects : createdatenew) {
							mon = Long.parseLong(objects[12].toString());
							yer = Long.parseLong(objects[13].toString());
						}
						BigInteger monthcurr = BigInteger.valueOf(mon);
						BigInteger yearcurr = BigInteger.valueOf(yer);

						String monname = "";
						int curryear = 0;

						List<Object[]> yearinfo = commonHomeMethodsService.findyearinfo(yearcurr);
						for (Object[] yearLst : yearinfo) {
							curryear = (Integer) yearLst[9];
						}
						String lstLoan = displayInnerReportRepo.getLoanDtls(map.get("sevaarth_id").toString(), mon,
								curryear);
						abcloans.add(lstLoan);
					} else {
						abcloans.add("0.00");
					}
				} else {
					abcloans.add("");
				}

			}
			// alnetamt.add(" ");
			// DEDUCTIONS ADJUSTABLE BY OTHERS row ended

			// GPF/DCPS A.C.Number row started
			ArrayList dcpsgpsno = new ArrayList();
			dcpsgpsno.add("");
			/* dcpsgpsno.add("GPF/DCPS A.C.Number"); */
			dcpsgpsno.add("GPF/DCPS");
			Iterator iteratordcpsgpsno = lstempdetails.iterator();
			for (int i = 1; i <= 10; i++) {
				if (i <= empsize) {
					Map<String, Object> map = (Map<String, Object>) iteratordcpsgpsno.next();
					String dcpsno = "";
					String gpfNo = "";
					dcpsno = displayInnerReportRepo.getDcpsGpfNoDtls(map.get("sevaarth_id").toString());

					if (dcpsno != null) {
						dcpsgpsno.add("DCPS/" + dcpsno);
						// dcpsgpsno.add("DCPS");
					} else {
						gpfNo = displayInnerReportRepo.getGpfNoDtls(map.get("sevaarth_id").toString());
						dcpsgpsno.add("GPF/" + gpfNo);
						// dcpsgpsno.add("GPF");
					}

				} else {
					dcpsgpsno.add(" ");
				}

			}
			// GPF/DCPS A.C.Number row ended

			// ############################################################################################################
			DisplayInnerReportModel displyinnrpt = new DisplayInnerReportModel();
			displyinnrpt.setDescription(description);
			displyinnrpt.setHeaderRow(headerRow);
			displyinnrpt.setOrderdataList(orderdataList);
			displyinnrpt.setSlno(slno);
			displyinnrpt.setAllowance(allowance);
			displyinnrpt.setLstdedectionag(lstdedectionag);
			displyinnrpt.setAbcloans(abcloans);
			displyinnrpt.setLstdeducother(lstdeducother);

			displyinnrpt.setLstdeductrsy(lstdeductrsy);

			displyinnrpt.setAlnetamt(alnetamt);
			displyinnrpt.setDcpsgpsno(dcpsgpsno);
			displyinnrpt.setAlgrossamt(algrossamt);
			displyinnrpt.setAlgrosssale(algrosssale);
			displyinnrpt.setAlgrosstotal(algrosstotal);
			// displyinnrpt.setDeductionamt(deductionamt);
			displyinnrpt.setDeductionamt(altotal_deduction);
			displyinnrpt.setAldeduct_adj_try(aldeduct_adj_try);
			displyinnrpt.setAldeduct_adj_otr(aldeduct_adj_otr);
			returnresult.add(displyinnrpt);
		}
		return returnresult;
	}

	public List<Map<String, Object>> getempDetails(String bill_no) {
		return displayInnerReportRepo.getempDetails(bill_no);
	}

	public Date findbillCreateDate(Long billNumber) {
		return displayInnerReportRepo.findbillCreateDate(billNumber);
	}

	public String getbillDetails(Long billNumber) {
		return displayInnerReportRepo.getbillDetails(billNumber);
	}

	public PaybillGenerationTrnEntity findPayBilldetailByPaybillid(Long billNumber) {
		return displayInnerReportRepo.findPayBilldetailByPaybillid(billNumber);
	}


}
