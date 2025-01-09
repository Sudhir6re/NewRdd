package com.mahait.gov.in.nps.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mahait.gov.in.common.CommonConstants;
import com.mahait.gov.in.controller.BaseController;
import com.mahait.gov.in.entity.CmnLookupMst;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.nps.entity.NSDLBHDtlsEntity;
import com.mahait.gov.in.nps.entity.NSDLDHDtlsEntity;
import com.mahait.gov.in.nps.entity.NSDLSDDtlsEntity;
import com.mahait.gov.in.nps.model.DcpsLegacyModel;
import com.mahait.gov.in.nps.service.NSDLDetailsService;
import com.mahait.gov.in.nps.service.NpsLegacyFileGenerationService;
import com.mahait.gov.in.service.CommonHomeMethodsService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/ddo")
@Controller
public class NpsLegacyFileGenerationController extends BaseController {

	@Autowired
	NpsLegacyFileGenerationService npsLegacyFileGenerationService;

	@Autowired
	CommonHomeMethodsService commonHomeMethodsService;

	@Autowired
	NSDLDetailsService nsdlDetailsService;

	@RequestMapping("/legacyFileGeneration")
	public String legacyFileGeneration(Model model, Locale locale, HttpSession session,
			@ModelAttribute("dcpsLegacyModel") DcpsLegacyModel dcpsLegacyModel) {
		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		addMenuAndSubMenu(model, messages);

		List<CmnLookupMst> lLstLegValidatePeriod = commonHomeMethodsService.getLookupValues("validatePeriod",
				CommonConstants.Languages.English);
		model.addAttribute("lLstLegValidatePeriod", lLstLegValidatePeriod);

		if (dcpsLegacyModel.getAction() != null && dcpsLegacyModel.getAction().equals("search")) {
			String locationId = (String) session.getAttribute("locationId");

			List<DcpsLegacyModel> lstDcpsLegacyModel = npsLegacyFileGenerationService
					.findLegacyEmployeeList(dcpsLegacyModel, messages, locationId);
			dcpsLegacyModel.setLstDcpsLegacyModel(lstDcpsLegacyModel);

			if (lstDcpsLegacyModel.size() == 0) {
				model.addAttribute("noDataFoundMsg", "No Data found for searched period ");
			}

		}
		model.addAttribute("dcpsLegacyModel", dcpsLegacyModel);
		return "/views/nps/legacy-file-generation";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/genLegacyTxtFile", method = RequestMethod.POST)
	public String genLegacyTxtFile(Model model, Locale locale, HttpSession session,
			@RequestBody DcpsLegacyModel dcpsLegacyModel, HttpServletResponse response) throws IOException {

		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");

		Long Num = null;
		BufferedReader br = null;
		String empname = null;
		String dcpsid = null;
		String pranno = null;
		String govEmpContri = null;
		String subempContri = null;
		String Contritype = null;

		int countsum = 0;
		String govcontiSum = null;
		String subcontiSum = null;
		String TotalContri = null;
		String TotalContrisum = null;
		String extn = null;

		StringBuilder Strbr = new StringBuilder();
		StringBuilder Strbr1 = new StringBuilder();
		String extnFlag = null;

		String dtoRegNo = null;
		String ddoRegNo = null;
		String strDDOCode = null;
		String prvDdoReg = "AAA";
		String treasuryyno = null;
		String locId = null;
		try {

			treasuryyno = dcpsLegacyModel.getPeriod();
			extn = dcpsLegacyModel.getExtn();
			extnFlag = dcpsLegacyModel.getExtnFlag();
			addMenuAndSubMenu(model, messages);

			strDDOCode = messages.getDdoCode();

			String locationCode = messages.getLocId().toString();

			locId = npsLegacyFileGenerationService.findLocId(messages.getLocId().toString());

			Object[] lyrObj = (Object[]) null;

			Date date = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			int year1 = cal.get(1);
			int month = cal.get(2) + 1;
			int day1 = cal.get(5);

			DecimalFormat df = new DecimalFormat("00");
			String month1 = df.format(month);
			System.out.println(month1);

			List<DcpsLegacyModel> employeeList = npsLegacyFileGenerationService.findLegacyEmployeeList(dcpsLegacyModel,
					messages, locationCode);

			List nsdlData = null;
			String BatchId = null;
			Long nwbatchId = null;
			String nwTranBatchId = null;
			String[] fyYrsplit = (String[]) null;
			String tranId = null;
			List<Long> finalNpdId = new ArrayList<Long>();
			String ddoCode = null;
			String lStrFileName = "";

			if (employeeList.size() != 0) {
				BatchId = npsLegacyFileGenerationService.getBatchId(locId);
				if ((BatchId == null) || (BatchId.equalsIgnoreCase("0"))) {
					BatchId = locId + month1 + year1 + "001";
					lStrFileName = "R" + locId + month1 + year1 + "001";
				} else {
					lStrFileName = "R".concat(BatchId);
				}

				int totalsize = employeeList.size();
				String lEmpTotalContri = "0.0";
				String lEmpTotalContriInt = "0.0";

				String lEmplrTotalContri = "0.0";
				String lEmplrTotalContriInt = "0.0";
				if ((employeeList != null) && (!employeeList.isEmpty())) {

					int count = 0;
					int i = 2;
					int j = 0;
					int empCount = 1;

					List<Object[]> lstTotal = npsLegacyFileGenerationService.findDdoWiseTotalAmnt(locId, treasuryyno,
							strDDOCode);
					if (lstTotal.size() > 0) {
						Object[] object = lstTotal.get(0);
						if (object.length > 0) {
							lEmpTotalContri = object[0] != null ? object[0].toString() : "0.0";
						}
						if (object.length > 1) {
							lEmpTotalContriInt = object[1] != null ? object[1].toString() : "0.0";
						}
						if (object.length > 2) {
							lEmplrTotalContri = object[2] != null ? object[2].toString() : "0.0";
						}
						if (object.length > 3) {
							lEmplrTotalContriInt = object[3] != null ? object[3].toString() : "0.0";
						}
					}

					String totalEmplyContri = null;
					Double EmpleeContri = null;
					Double EmployeerContri = null;

					String totalEmplyerContri = null;
					Double totalEContri = null;
					String intEmpl = null;
					String intEmplr = null;

					String[] intEmp = (String[]) null;
					String[] intEmployee = (String[]) null;
					String[] intEmployer = (String[]) null;
					String[] contriEmployee = (String[]) null;
					String[] emplrContriintEmployer = (String[]) null;
					String[] totalDhsumsplit = (String[]) null;
					String[] totalDhemplrsumsplit = (String[]) null;
					Double totalContribution = null;
					Double GovContributionSum = null;
					Double subcontributionSum = null;
					Double TotalContributionsum = null;
					Double totalEmpContribution = Double.valueOf(0);
					Double totalEmplrContribution = Double.valueOf(0);
					String[] overallAmt = (String[]) null;
					String[] totalempoverallAmt = (String[]) null;
					String[] totalemplroverallAmt = (String[]) null;
					Long lLngPkIdForDh = null;
					Long lLngPkIdForBh = null;
					Long lLngPkIdForSd = null;

					if ((lEmpTotalContri != null) && (lEmpTotalContriInt != null)) {

						EmpleeContri = Double
								.valueOf(Double.parseDouble(lEmpTotalContri) + Double.parseDouble(lEmpTotalContriInt));

						EmployeerContri = Double.valueOf(
								Double.parseDouble(lEmplrTotalContri) + Double.parseDouble(lEmplrTotalContriInt));

						totalEmplyContri = nosci(EmpleeContri.doubleValue());

						totalEmplyerContri = nosci(EmployeerContri.doubleValue());

						totalEContri = Double.valueOf(Double.parseDouble(totalEmplyerContri))
								+ Double.valueOf(Double.parseDouble(totalEmplyContri));
					}

					String totaloverallAmt = nosci(totalEContri.doubleValue());

					overallAmt = totaloverallAmt.toString().split("\\.");

					if (totaloverallAmt.equals("0")) {
						totaloverallAmt = "0.00";

					}

					else if (overallAmt[0].length() == 1) {
						totaloverallAmt = totaloverallAmt + "0";
					} else if (overallAmt[0].length() > 2) {

						totaloverallAmt = decRoundOff(totaloverallAmt);
					}
					/* END */
					else if (overallAmt[1].length() == 1) {
						totaloverallAmt = totaloverallAmt + "0";

					} else if (overallAmt[1].length() > 2) {
						totaloverallAmt = decRoundOff(totaloverallAmt);
					}

					totalempoverallAmt = totalEmplyContri.toString().split("\\.");

					if (totalEmplyContri.equals("0")) {
						totalEmplyContri = "0.00";

					}

					else if (totalempoverallAmt[0].length() == 1) {
						totalEmplyContri = totalEmplyContri + "0";

					} else if (overallAmt[0].length() > 2) {

						totalEmplyContri = decRoundOff(totalEmplyContri);
					}
					/* END */
					else if (totalempoverallAmt[1].length() == 1) {
						totalEmplyContri = totalEmplyContri + "0";

					} else if (overallAmt[1].length() > 2) {

						totalEmplyContri = decRoundOff(totalEmplyContri);
					}

					totalemplroverallAmt = totalEmplyerContri.toString().split("\\.");

					if (totalEmplyerContri.equals("0")) {
						totalEmplyerContri = "0.00";

					} else if (totalemplroverallAmt[0].length() == 1) {
						totalEmplyerContri = totalEmplyerContri + "0";
					} else if (overallAmt[0].length() > 2) {
						totalEmplyerContri = decRoundOff(totalEmplyerContri);
					}
					/* END */
					else if (totalemplroverallAmt[1].length() == 1) {
						totalEmplyerContri = totalEmplyerContri + "0";
					} else if (overallAmt[1].length() > 2) {

						totalEmplyerContri = decRoundOff(totalEmplyerContri);
					}

					int temp = 0;
					int emprecCount = 0;

					PrintWriter outputfile = response.getWriter();
					boolean isContriZero = false;
					boolean isInterestZero = false;
					int totalContriCount = 0;
					int ddoCount = 0;

					for (DcpsLegacyModel dcpsLegacyModel2 : employeeList) {

						finalNpdId.add(dcpsLegacyModel2.getNpsId());

						count++;

						empname = dcpsLegacyModel2.getEmployeeFullName() != null
								? dcpsLegacyModel2.getEmployeeFullName()
								: "NA";

						dcpsid = dcpsLegacyModel2.getDcpsNo() != null ? dcpsLegacyModel2.getDcpsNo() : "";

						pranno = dcpsLegacyModel2.getPranNo() != null ? dcpsLegacyModel2.getPranNo() : "";

						dtoRegNo = dcpsLegacyModel2.getDtoRegNo() != null ? dcpsLegacyModel2.getDtoRegNo() : "";

						ddoRegNo = dcpsLegacyModel2.getDdoRegNo() != null ? dcpsLegacyModel2.getDdoRegNo() : "";

						ddoCode = dcpsLegacyModel2.getDdoCode() != null ? dcpsLegacyModel2.getDdoCode() : "";

						govEmpContri = dcpsLegacyModel2.getEmployerContri() != null
								? dcpsLegacyModel2.getEmployerContri().toString()
								: "";

						subempContri = dcpsLegacyModel2.getEmpContri() != null
								? dcpsLegacyModel2.getEmpContri().toString()
								: "";

						intEmpl = dcpsLegacyModel2.getEmpInterest() != null
								? dcpsLegacyModel2.getEmpInterest().toString()
								: "";

						intEmplr = dcpsLegacyModel2.getEmployerInterest() != null
								? dcpsLegacyModel2.getEmployerInterest().toString()
								: "";

						String TotalIntContri = null;

						Double totalIntContribution = Double
								.valueOf(Double.parseDouble(intEmpl) + Double.parseDouble(intEmplr));

						TotalIntContri = totalIntContribution.toString();

						intEmp = TotalIntContri.toString().split("\\.");

						if (TotalIntContri.equals("0")) {
							TotalIntContri = "0.00";

						} else if (intEmp[1].length() == 1) {
							TotalIntContri = TotalIntContri + "0";
						}

						intEmployee = intEmpl.toString().split("\\.");
						if ((intEmpl != null) && (!"".equalsIgnoreCase(intEmpl))) {
							if (Double.parseDouble(intEmpl) > 0) {
								isInterestZero = false;
							} else {
								isInterestZero = true;
							}
						}

						if (intEmpl.equals("0")) {
							intEmpl = "0.00";

						} else if (intEmployee.length > 1) {
							if (intEmployee[1].length() == 1) {
								intEmpl = intEmpl + "0";
							}
						} else if (intEmployee.length == 1) {
							intEmpl = intEmpl + ".00";
						}

						intEmployer = intEmplr.toString().split("\\.");

						if ((intEmplr != null) && (!"".equalsIgnoreCase(intEmplr))) {
							if (Double.parseDouble(intEmplr) > 0) {
								isInterestZero = false;
							} else {
								isInterestZero = true;
							}
						}

						if (intEmplr.equals("0")) {
							intEmplr = "0.00";

						} else if (intEmployer.length > 1) {
							if (intEmployer[1].length() == 1) {
								intEmplr = intEmplr + "0";
							}
						} else if (intEmployer.length == 1) {
							intEmplr = intEmplr + ".00";
						}

						contriEmployee = govEmpContri.toString().split("\\.");
						if ((govEmpContri != null) && (!"".equalsIgnoreCase(govEmpContri))) {
							if ((Double.parseDouble(govEmpContri) > 0) || (Double.parseDouble(govEmpContri) < 0)) {
								isContriZero = false;
							} else {
								isContriZero = true;
							}
						}

						if (govEmpContri.equals("0")) {
							govEmpContri = "0.00";

						} else if (contriEmployee.length > 1) {
							if (contriEmployee[1].length() == 1) {
								govEmpContri = govEmpContri + "0";
							}
						} else if (contriEmployee.length == 1) {
							govEmpContri = govEmpContri + ".00";
						}

						emplrContriintEmployer = subempContri.toString().split("\\.");
						if ((subempContri != null) && (!"".equalsIgnoreCase(subempContri))) {
							if ((Double.parseDouble(subempContri) > 0) || (Double.parseDouble(subempContri) < 0)) {
								isContriZero = false;
							} else {
								isContriZero = true;
							}
						}

						if (subempContri.equals("0")) {
							subempContri = "0.00";

						} else if (emplrContriintEmployer.length > 1) {
							if (emplrContriintEmployer[1].length() == 1) {
								subempContri = subempContri + "0";
							}
						} else if (emplrContriintEmployer.length == 1) {
							subempContri = subempContri + ".00";
						}
						DecimalFormat df1 = new DecimalFormat("0.00");
						totalContribution = Double.valueOf(govEmpContri != null
								? Double.parseDouble(govEmpContri) + Double.parseDouble(subempContri)
								: +Double.parseDouble(subempContri));

						TotalContri = String.format("%.2f", totalContribution);

						countsum += count;

						GovContributionSum = Double.valueOf(
								govcontiSum != null ? Double.parseDouble(govcontiSum) + Double.parseDouble(govEmpContri)
										: +Double.parseDouble(govEmpContri));

						govcontiSum = GovContributionSum.toString();

						subcontributionSum = Double.valueOf(
								subcontiSum != null ? Double.parseDouble(subcontiSum) + Double.parseDouble(subempContri)
										: +Double.parseDouble(subempContri));

						subcontiSum = subcontributionSum.toString();

						TotalContributionsum = Double.valueOf(
								govcontiSum != null ? Double.parseDouble(govcontiSum) + Double.parseDouble(subcontiSum)
										: +Double.parseDouble(subcontiSum));

						TotalContrisum = String.format("%.2f", TotalContributionsum);

						if (((!prvDdoReg.equals(ddoRegNo)) && (!isContriZero))
								|| ((!prvDdoReg.equals(ddoRegNo)) && (!isInterestZero))) {
							i++;
							j++;
							empCount = 1;
							ddoCount++;
							Strbr.append(i + "^");
							Strbr.append("DH^");
							Strbr.append("1^");
							Strbr.append(j + "^");
							Strbr.append(ddoRegNo + "^");

							String lEmpRowContrDdo = npsLegacyFileGenerationService
									.getEmployeeRecordCountDdoregNsdl(locId, treasuryyno, ddoRegNo);
							long rowCnt = Long.valueOf(0L);
							if (lEmpRowContrDdo != null) {
								rowCnt = Long.valueOf(Long.parseLong(lEmpRowContrDdo));
							}

							Strbr.append(rowCnt + "^");

							String lEmpTotalContrDdo = "0.0";
							String lEmpTotalContrDdoInt = "0.0";

							String lEmplrTotalContrDdo = "0.0";
							String lEmplrTotalContrDdoInt = "0.0";

							List<Object[]> dtoRegWiseAmnt = npsLegacyFileGenerationService.findDtoRegWiseAmnt(locId,
									treasuryyno, ddoRegNo);
							if (dtoRegWiseAmnt.size() > 0) {
								Object[] object = dtoRegWiseAmnt.get(0);
								if (object.length > 0) {
									lEmpTotalContrDdo = object[0] != null ? object[0].toString() : "0.0";
								}
								if (object.length > 1) {
									lEmpTotalContrDdoInt = object[1] != null ? object[1].toString() : "0.0";
								}
								if (object.length > 2) {
									lEmplrTotalContrDdo = object[2] != null ? object[2].toString() : "0.0";
								}
								if (object.length > 3) {
									lEmplrTotalContrDdoInt = object[3] != null ? object[3].toString() : "0.0";
								}
							}

							// ende By Naveen
							String totalDhsum = "";
							String totalEmplrhsum = "";
							if (lEmpTotalContrDdo == null) {
								lEmpTotalContrDdo = "0.0";
							}
							if (lEmpTotalContrDdoInt == null) {
								lEmpTotalContrDdoInt = "0.0";
							}

							if (lEmplrTotalContrDdo == null) {
								lEmplrTotalContrDdo = "0.0";
							}
							if (lEmplrTotalContrDdoInt == null) {
								lEmplrTotalContrDdoInt = "0.0";
							}

							if ((lEmpTotalContrDdo != null) && (lEmpTotalContrDdoInt != null)) {
								Double total = Double.valueOf(Double.parseDouble(lEmpTotalContrDdo)
										+ Double.parseDouble(lEmpTotalContrDdoInt));
								lEmpTotalContrDdo = total.toString();
								totalDhsum = lEmpTotalContrDdo;

								Double totalemplr = Double.valueOf(Double.parseDouble(lEmplrTotalContrDdo)
										+ Double.parseDouble(lEmplrTotalContrDdoInt));
								lEmplrTotalContrDdo = totalemplr.toString();
								totalEmplrhsum = lEmplrTotalContrDdo;
							}

							totalDhsumsplit = totalDhsum.toString().split("\\.");

							if (totalDhsum.equals("0")) {
								totalDhsum = "0.00";
							} else if (totalDhsumsplit.length > 1) {
								if (totalDhsumsplit[1].length() == 1) {
									totalDhsum = totalDhsum + "0";
								}
							} else if (totalDhsumsplit.length == 1) {
								totalDhsum = totalDhsum + ".00";
							}

							totalDhemplrsumsplit = totalEmplrhsum.toString().split("\\.");

							if (totalEmplrhsum.equals("0")) {
								totalEmplrhsum = "0.00";
							} else if (totalDhemplrsumsplit.length > 1) {
								if (totalDhemplrsumsplit[1].length() == 1) {
									totalEmplrhsum = totalEmplrhsum + "0";
								}
							} else if (totalDhemplrsumsplit.length == 1) {
								totalEmplrhsum = totalEmplrhsum + ".00";
							}

							String temp2 = nosci(Double.parseDouble(totalEmplrhsum));
							String temp1 = nosci(Double.parseDouble(totalDhsum));

							Strbr.append(decRoundOff(temp2) + "^");
							Strbr.append(decRoundOff(temp1) + "^");
							Strbr.append("^");

							lLngPkIdForDh = 0l;

							/*
							 * lObjAlIndSer.insertDHDetails(lLngPkIdForDh, i, "DH", "1", j, ddoRegNo,
							 * rowCnt, decRoundOff(temp1), decRoundOff(temp2), lStrFileName);
							 */

							NSDLDHDtlsEntity nSDLDHDtlsEntity = new NSDLDHDtlsEntity();
							nSDLDHDtlsEntity.setSrno(i);
							nSDLDHDtlsEntity.setHeaderName("DH");
							nSDLDHDtlsEntity.setDhDDORegno(ddoRegNo); // SVG
							nSDLDHDtlsEntity.setBhSDCnt(BigInteger.valueOf(employeeList.size()));
							nSDLDHDtlsEntity.setDhCol2(String.valueOf(j));
							nSDLDHDtlsEntity.setDhEmpAmt(Double.valueOf(temp1));
							nSDLDHDtlsEntity.setDhEmplrAmt(Double.valueOf(temp2));
							nSDLDHDtlsEntity.setDhNo(String.valueOf(j));
							nSDLDHDtlsEntity.setDhStatus("1");
							nSDLDHDtlsEntity.setFileName(lStrFileName);
							nSDLDHDtlsEntity.setIsLegacyData('Y');
							nSDLDHDtlsEntity.setDhNo("1");

							nsdlDetailsService.saveDHDetail(nSDLDHDtlsEntity);

							Strbr.append("\r\n");
							temp++;
							emprecCount++;
							prvDdoReg = ddoRegNo;
						} else if ((!prvDdoReg.equals(ddoRegNo)) && (isContriZero) && (isInterestZero)) {
							temp++;
							emprecCount++;
						} else {
							prvDdoReg = ddoRegNo;
						}

						if ((employeeList != null) && (!employeeList.equals(""))) {
							if (!isContriZero) {
								i++;
								Strbr.append(i + "^");
								Strbr.append("SD^");
								Strbr.append("1^");
								Strbr.append(j + "^");
								Strbr.append(empCount + "^");
								Strbr.append(pranno + "^");
								Strbr.append(govEmpContri + "^");
								Strbr.append(subempContri + "^");
								Strbr.append("^");
								Strbr.append(TotalContri + "0" + "^");
								Strbr.append("A^^^");

								if (treasuryyno.equalsIgnoreCase("10001198212")) {
									Strbr.append("Dcps Contribution Phase - 1^");
								} else {
									Strbr.append("Dcps Contribution Phase - 2^");
								}
								Strbr.append("\r\n");

								/*
								 * lObjAlIndSer.insertSDDetails(lLngPkIdForSd, i, "SD", "1", j, empCount,
								 * pranno, subempContri,govEmpContri, TotalContri // + "0", "A^" + month1 + "^"
								 * , "A^" + month1 + "^" + year1, "Dcps Contribution Phase - 1 for " + month1 +
								 * "/" + year1, lStrFileName, ddoRegNo);
								 */

								NSDLSDDtlsEntity nSDLSDDtlsEntity = new NSDLSDDtlsEntity();
								nSDLSDDtlsEntity.setSrno(i);
								nSDLSDDtlsEntity.setDdoRegNo(dtoRegNo);
								nSDLSDDtlsEntity.setFileRemark(lStrFileName);
								nSDLSDDtlsEntity.setHeaderName("SD");
								nSDLSDDtlsEntity.setIsLegacyData('Y');
								nSDLSDDtlsEntity.setSdEmpAmt(Double.valueOf(subempContri));
								nSDLSDDtlsEntity.setSdEmplrAmt(Double.valueOf(govEmpContri));
								nSDLSDDtlsEntity.setSdNo(Long.valueOf(1));
								nSDLSDDtlsEntity.setSdNo2(Long.valueOf(1));
								nSDLSDDtlsEntity.setSdNo3(Long.valueOf(j));
								nSDLSDDtlsEntity.setSdPranNo(pranno);
								nSDLSDDtlsEntity.setStatus("1");
								nSDLSDDtlsEntity.setSdStatus("A" + "^" + month1 + "^" + year1);
								nSDLSDDtlsEntity.setDdoRegNo(messages.getUserName());

								String empCont = subempContri;
								String emprCont = govEmpContri;

								Double total = Double.parseDouble(empCont) + Double.parseDouble(emprCont);
								nSDLSDDtlsEntity
										.setSdRemark("Dcps Contribution Phase - 1 for " + month1 + " and " + year1);
								nSDLSDDtlsEntity.setSdTotalAmt(total);
								Integer saveId = nsdlDetailsService.saveSDDetail(nSDLSDDtlsEntity);

								empCount++;
								totalContriCount++;
							}
							if (!isInterestZero) {
								i++;
								Strbr.append(i + "^");
								Strbr.append("SD^");
								Strbr.append("1^");
								Strbr.append(j + "^");
								Strbr.append(empCount + "^");
								Strbr.append(pranno + "^");
								intEmpl = nosci(Double.parseDouble(intEmpl));
								intEmplr = nosci(Double.parseDouble(intEmplr));
								Strbr.append(decRoundOff(intEmplr) + "^");
								Strbr.append(decRoundOff(intEmpl) + "^");

								Strbr.append("^");
								TotalIntContri = nosci(Double.parseDouble(TotalIntContri));
								Strbr.append(decRoundOff(TotalIntContri) + "^");

								Strbr.append("A^^^");

								if (treasuryyno.equalsIgnoreCase("10001198212")) {
									Strbr.append("Dcps Interest Phase - 1^");
								} else {
									Strbr.append("Dcps Interest Phase - 2^");
								}
								Strbr.append("\r\n");
								totalContriCount++;

								String array1[] = TotalIntContri.toString().split("\\.");

								if (array1[1].length() == 2) {
									/*
									 * lObjAlIndSer.insertSDDetails(lLngPkIdForSd, i, "SD", "1", j, empCount,
									 * pranno, decRoundOff(intEmpl), decRoundOff(intEmplr), TotalIntContri, "A^" +
									 * month1 + "^" + year1, "Dcps Interest Phase - 1 for " + month1 + "/" + year1,
									 * lStrFileName, ddoRegNo);
									 */

									NSDLSDDtlsEntity nSDLSDDtlsEntity = new NSDLSDDtlsEntity();
									nSDLSDDtlsEntity.setSrno(i);
									nSDLSDDtlsEntity.setDdoRegNo(dtoRegNo);
									nSDLSDDtlsEntity.setFileRemark(lStrFileName);
									nSDLSDDtlsEntity.setHeaderName("SD");
									nSDLSDDtlsEntity.setIsLegacyData('Y');
									nSDLSDDtlsEntity.setSdEmpAmt(Double.valueOf(subempContri));
									nSDLSDDtlsEntity.setSdEmplrAmt(Double.valueOf(govEmpContri));
									nSDLSDDtlsEntity.setSdNo(Long.valueOf(1));
									nSDLSDDtlsEntity.setSdNo2(Long.valueOf(1));
									nSDLSDDtlsEntity.setSdNo3(Long.valueOf(j));
									nSDLSDDtlsEntity.setSdPranNo(pranno);
									nSDLSDDtlsEntity.setStatus("1");
									nSDLSDDtlsEntity.setSdStatus("A" + "^" + month1 + "^" + year1);
									nSDLSDDtlsEntity.setDdoRegNo(ddoRegNo);

									String empCont = subempContri;
									String emprCont = govEmpContri;

									Double total = Double.parseDouble(empCont) + Double.parseDouble(emprCont);
									nSDLSDDtlsEntity
											.setSdRemark("Dcps Contribution Phase - 1 for " + month1 + " and " + year1);
									nSDLSDDtlsEntity.setSdTotalAmt(total);
									Integer saveId = nsdlDetailsService.saveSDDetail(nSDLSDDtlsEntity);

								} else {
									/*
									 * lObjAlIndSer.insertSDDetails(lLngPkIdForSd, i, "SD", "1", j, empCount,
									 * pranno, decRoundOff(intEmpl), decRoundOff(intEmplr), TotalIntContri + "0",
									 * "A^" + month1 // TotalIntContri , "A^" + month1 + "^" + year1,
									 * "Dcps Interest Phase - 1 for " + month1 + "/" + year1, lStrFileName,
									 * ddoRegNo);
									 */

									NSDLSDDtlsEntity nSDLSDDtlsEntity = new NSDLSDDtlsEntity();
									nSDLSDDtlsEntity.setSrno(i);
									nSDLSDDtlsEntity.setDdoRegNo(dtoRegNo);
									nSDLSDDtlsEntity.setFileRemark(lStrFileName);
									nSDLSDDtlsEntity.setHeaderName("SD");
									nSDLSDDtlsEntity.setIsLegacyData('Y');
									nSDLSDDtlsEntity.setSdEmpAmt(Double.valueOf(subempContri));
									nSDLSDDtlsEntity.setSdEmplrAmt(Double.valueOf(govEmpContri));
									nSDLSDDtlsEntity.setSdNo(Long.valueOf(1));
									nSDLSDDtlsEntity.setSdNo2(Long.valueOf(1));
									nSDLSDDtlsEntity.setSdNo3(Long.valueOf(j));
									nSDLSDDtlsEntity.setSdPranNo(pranno);
									nSDLSDDtlsEntity.setStatus("1");
									nSDLSDDtlsEntity.setSdStatus("A" + "^" + month1 + "^" + year1);
									nSDLSDDtlsEntity.setDdoRegNo(ddoRegNo);

									String empCont = subempContri;
									String emprCont = govEmpContri;

									Double total = Double.parseDouble(empCont) + Double.parseDouble(emprCont);
									nSDLSDDtlsEntity
											.setSdRemark("Dcps Contribution Phase - 1 for " + month1 + " and " + year1);
									nSDLSDDtlsEntity.setSdTotalAmt(total);
									Integer saveId = nsdlDetailsService.saveSDDetail(nSDLSDDtlsEntity);

								}
								empCount++;
							}
						}

					}

					if (finalNpdId.size() > 0) {
						 nsdlDetailsService.updateBatchId(BatchId, finalNpdId);
					}

					String lineSeperator = "\r\n";

					String os = System.getProperty("os.name");

					if (os.toLowerCase().indexOf("unix") > 0) {
						lineSeperator = "\n";
					} else if (os.toLowerCase().indexOf("windows") > 0) {
						lineSeperator = "\r\n";
					}

					model.addAttribute("dcpsLegacyModel", new DcpsLegacyModel());

					PrintWriter outputfile1 = response.getWriter();

					if ((extn != null) && (!extn.equals("")) && (Long.parseLong(extnFlag) == 1L)) {

						getFileHeader(outputfile, dtoRegNo);

						getBatchHeader(lLngPkIdForBh, outputfile, totalContriCount, ddoCount,totalEmplyerContri, totalEmplyContri
								,totaloverallAmt, BatchId, dtoRegNo, month1, year1, lStrFileName,
								ddoCode);
					}

					if (extn.equals("txt")) {
						try {
							String fileName = lStrFileName + ".txt";
							response.setContentType("text/plain;charset=UTF-8");

							response.addHeader("Content-disposition", "attachment; filename=" + fileName);
							response.setCharacterEncoding("UTF-8");

							outputfile.write(Strbr.toString());
							outputfile.flush();
						} catch (Exception e) {
							e.printStackTrace();
						} finally {
							if (outputfile != null) {
								outputfile.close();
							}

						}

					} else if (extn.equals("fpu")) {
						try {
							String fileName = lStrFileName + ".fpu";
							response.setContentType("text/plain;charset=UTF-8");

							response.addHeader("Content-disposition", "attachment; filename=" + fileName);
							response.setCharacterEncoding("UTF-8");

							outputfile1.write(Strbr1.toString());
							outputfile1.flush();
						} catch (Exception e) {
							e.printStackTrace();
						} finally {
							if (outputfile1 != null) {
								outputfile1.close();
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getBatchId", method = RequestMethod.POST)
	public ResponseEntity<String> getBatchId(Model model, Locale locale, HttpSession session,
			@RequestBody DcpsLegacyModel dcpsLegacyModel, HttpServletResponse response) {

		OrgUserMst messages = (OrgUserMst) session.getAttribute("MY_SESSION_MESSAGES");
		String locId = null;

		locId = npsLegacyFileGenerationService.findLocId(messages.getLocId().toString());

		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year1 = cal.get(1);
		int month = cal.get(2) + 1;
		int day1 = cal.get(5);

		DecimalFormat df = new DecimalFormat("00");
		String month1 = df.format(month);
		System.out.println(month1);

		String BatchId = null;
		String lStrFileName = "";
		BatchId = npsLegacyFileGenerationService.getBatchId(locId);
		if ((BatchId == null) || (BatchId.equalsIgnoreCase("0"))) {
			BatchId = locId + month1 + year1 + "001";
			lStrFileName = "R" + locId + month1 + year1 + "001";
		} else {
			lStrFileName = "R".concat(BatchId);
		}

		return ResponseEntity.ok(lStrFileName);
	}

	private static String nosci(double d) {
		if (d < 0) {
			return "-" + nosci(-d);
		}
		String javaString = String.valueOf(d);
		int indexOfE = javaString.indexOf("E");
		if (indexOfE == -1) {
			return javaString;
		}
		StringBuffer sb = new StringBuffer();
		if (d > 1.0D) {
			int exp = Integer.parseInt(javaString.substring(indexOfE + 1));
			String sciDecimal = javaString.substring(2, indexOfE);
			int sciDecimalLength = sciDecimal.length();
			if (exp == sciDecimalLength) {
				sb.append(javaString.charAt(0));
				sb.append(sciDecimal);
			} else if (exp > sciDecimalLength) {
				sb.append(javaString.charAt(0));
				sb.append(sciDecimal);
				for (int i = 0; i < exp - sciDecimalLength; i++) {
					sb.append('0');
				}
			} else if (exp < sciDecimalLength) {
				sb.append(javaString.charAt(0));
				sb.append(sciDecimal.substring(0, exp));
				sb.append('.');
				for (int i = exp; i < sciDecimalLength; i++) {
					sb.append(sciDecimal.charAt(i));
				}
			}

			return sb.toString();
		}

		return javaString;
	}

	public static String decRoundOff(String number) {
		String s = String.format("%.2f", new Object[] { Double.valueOf(Double.parseDouble(number)) });
		return s;
	}

	private void getFileHeader(PrintWriter br, String dtoRegNo) throws IOException {
		br.write("1^");
		br.write("FH^");
		br.write("P^");
		br.write(dtoRegNo + "^");
		br.write("1^");
		br.write("^");
		br.write("A");
		br.write("^");
		br.write("^");
		br.write("^");
		br.write("^");
		br.write("^");
		br.write("\r\n");
	}

	private void getBatchHeader(Long lLngPkIdForBh, PrintWriter br, int count, long ddoCount, String govContri,
			String SubContri, String Total, String batchId, String dtoRegNo, String month1, int year1,
			String lStrFileName, String ddoCode) throws IOException {
		Calendar cal = Calendar.getInstance(TimeZone.getDefault());
		String date = "ddMMyyyy";
		SimpleDateFormat sdf = new SimpleDateFormat(date);
		sdf.setTimeZone(TimeZone.getDefault());
		String currentdate = sdf.format(cal.getTime());
		
		/*INSERT INTO "IFMS"."NSDL_BH_DTLS" (BH_PK,SR_NO,HEADER_NAME,BH_NO,BH_COL2,BH_FIX_NO,BH_DATE,
				BH_BATCH_FIX_ID,BH_DDO_COUNT,BH_PRAN_COUNT,BH_EMP_AMOUNT,BH_EMPLR_AMOUNT,BH_TOTAL_AMT,FILE_NAME,
				YEAR,MONTH,STATUS,NSDL_FILE_HASH,FILE_STATUS,TRANSACTION_ID,ERROR_DATA,OLD_TRANSACTION_ID,REASON_FOR_TRAN_ID_UPDATEVAR,
				REMARK_FOR_TRAN_ID_UPDATE,FRN_NO,IS_LEGACY_DATA,DDO_CODE,FILE_CREATED_DATE,FILE_UPLOAD_CREATED_DATE,CHALLAN_RECEIVED_CREATED_DATE,
				VOUCHER_DATE,BANK_REFNO,BDS_NO,VOUCHER_NO) VALUES()*/
		

		br.write("2^");
		br.write("BH^");
		br.write("1^");
		br.write("R^");
		br.write(dtoRegNo + "^");
		br.write(currentdate + "^");
		br.write(dtoRegNo + batchId);
		br.write("^^");
		br.write(ddoCount + "^");
		br.write(count + "^");
		br.write(govContri + "^");
		br.write(SubContri + "^");
		br.write("^");
		br.write(Total + "^");
		br.write("\r\n");
		
		

		NSDLBHDtlsEntity nSDLBHDtlsEntity = new NSDLBHDtlsEntity();
		nSDLBHDtlsEntity.setSrno(2);
		nSDLBHDtlsEntity.setHeaderName("BH");
		nSDLBHDtlsEntity.setBhNo('1');
		nSDLBHDtlsEntity.setBhCol2('R');
		nSDLBHDtlsEntity.setBhFixNo(dtoRegNo);
		nSDLBHDtlsEntity.setBhDate(currentdate);
		nSDLBHDtlsEntity.setBhBatchFixId(dtoRegNo + batchId);
		nSDLBHDtlsEntity.setBhddoCount(Integer.parseInt(String.valueOf(ddoCount)));
		nSDLBHDtlsEntity.setBhPRANCount(String.valueOf(count));
		nSDLBHDtlsEntity.setBhEmpAmt(Double.valueOf(SubContri));
		nSDLBHDtlsEntity.setBhEmplrAmt(Double.valueOf(govContri));
		nSDLBHDtlsEntity.setIsLegacyData("Y");
		
		nSDLBHDtlsEntity.setBhDate(currentdate);
		nSDLBHDtlsEntity.setFileStatus("0");
		nSDLBHDtlsEntity.setFileName(lStrFileName);
		nSDLBHDtlsEntity.setDdoCode(ddoCode);
		Double total = Double.parseDouble(Total);
		nSDLBHDtlsEntity.setBhTotalAmt(total);
		nSDLBHDtlsEntity.setStatus("0");
		nSDLBHDtlsEntity.setFrnNo("0");
		nSDLBHDtlsEntity.setMonth(Integer.valueOf(month1));
		nSDLBHDtlsEntity.setYear(year1);
		nSDLBHDtlsEntity.setYearName(Integer.valueOf(year1));
		Integer saveId1 = nsdlDetailsService.saveBHDetail(nSDLBHDtlsEntity);
	}

}
