package com.mahait.gov.in.nps.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mahait.gov.in.common.StringHelperUtils;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.nps.entity.MstEmployeeNPSEntity;
import com.mahait.gov.in.nps.entity.NSDLBHDtlsEntity;
import com.mahait.gov.in.nps.entity.NSDLDHDtlsEntity;
import com.mahait.gov.in.nps.entity.NSDLSDDtlsEntity;
import com.mahait.gov.in.nps.model.DdoWiseNpsContriModel;
import com.mahait.gov.in.nps.model.NSDLDetailsModel;
import com.mahait.gov.in.nps.repository.NSDLDetailsRepo;

@Service
@Transactional
public class NSDLDetailsServiceImpl implements NSDLDetailsService {

	@Autowired
	private NSDLDetailsRepo nsdlDetailsRepo;

	@Override
	public List<Object[]> getNsdlEmpData(int month, int year, String ddoCode) {
		// TODO Auto-generated method stub
		return nsdlDetailsRepo.getNsdlEmpData(month, year, ddoCode);
	}

	@Override
	public List<Object[]> getNSDLEmpDtlsForGenerate(int month, int year, String ddoCode) {
		// TODO Auto-generated method stub
		return nsdlDetailsRepo.getNSDLEmpDtlsForGenerate(month, year, ddoCode);
	}

	@Override
	public List<NSDLDetailsModel> lstNsdlEmpDtls(String filename) {
		List<Object[]> lstprop = nsdlDetailsRepo.lstNsdlEmpDtls(filename);
		List<NSDLDetailsModel> lstObj = new ArrayList<>();
		if (!lstprop.isEmpty()) {
			for (Object[] objLst : lstprop) {
				NSDLDetailsModel obj = new NSDLDetailsModel();

				obj.setSevaarthId(StringHelperUtils.isNullString(objLst[0]));
				obj.setEmpName(StringHelperUtils.isNullString(objLst[1]));
				obj.setPRAN(StringHelperUtils.isNullString(objLst[2]));
				obj.setDdoCode(StringHelperUtils.isNullString(objLst[3]));
				obj.setDdoRegNo(StringHelperUtils.isNullString(objLst[4]));

				String emprAmt = (String) objLst[5];
				Double sdEmprAmt = Double.valueOf(emprAmt);

				String empAmt = (String) objLst[6];
				Double sdEmpAmt = Double.valueOf(empAmt);

				String totalAmt = (String) objLst[7];
				Double total = Double.valueOf(totalAmt);

				obj.setAmtEmprContibution(sdEmprAmt);
				obj.setAmtEmpContibution(sdEmpAmt);
				obj.setTotalAmt(total);
				obj.setEmpStatus(StringHelperUtils.isNullString(objLst[8]));
				lstObj.add(obj);
			}
		}
		return lstObj;
	}

	@Override
	public List<MstEmployeeNPSEntity> getEmpDataByFileId(String userName, String fileId) {
		// TODO Auto-generated method stub
		List<MstEmployeeNPSEntity> lst = new ArrayList<>();
		List<Object[]> lstprop = nsdlDetailsRepo.getEmpDataByFileId(userName, fileId);

		for (Object[] object : lstprop) {
			MstEmployeeNPSEntity mstEmployeeNPSEntity = new MstEmployeeNPSEntity();
			mstEmployeeNPSEntity.setSevaarthId(StringHelperUtils.isNullString(object[0]));
			mstEmployeeNPSEntity.setEmployeeFullName(StringHelperUtils.isNullString(object[1]));
			mstEmployeeNPSEntity.setPranNo(StringHelperUtils.isNullString(object[2]));
			mstEmployeeNPSEntity.setDdoCode(StringHelperUtils.isNullString(object[3]));
			mstEmployeeNPSEntity.setDdoRegNo(StringHelperUtils.isNullString(object[4]));

			mstEmployeeNPSEntity.setEmpNominee1DOB(new Date());
			mstEmployeeNPSEntity.setEmpNominee1GuardName("demo");
			mstEmployeeNPSEntity.setEmpNominee1InvalidCondn("Y");
			mstEmployeeNPSEntity.setEmpNominee1Name("dd");
			mstEmployeeNPSEntity.setEmpNominee1relationship("SON");
			mstEmployeeNPSEntity.setEmpNominee1Share("100");

			lst.add(mstEmployeeNPSEntity);
		}
		return lst;
	}

	@Override
	public String getbatchIdCount(String batchIdPrefix) {
		// TODO Auto-generated method stub
		return nsdlDetailsRepo.getbatchIdCount(batchIdPrefix);
	}

	@Override
	public List<Object[]> getEmployeeListNsdl(Integer yrCode, Integer month, Integer treasuryyno, String ddoName) {
		// TODO Auto-generated method stub
		return nsdlDetailsRepo.getEmployeeListNsdl(yrCode, month, treasuryyno, ddoName);
	}

	@Override
	public Long getDDoRegCount(Integer yrCode, Integer month, Integer treasuryyno) {
		// TODO Auto-generated method stub
		return nsdlDetailsRepo.getDDoRegCount(yrCode, month, treasuryyno);
	}

	@Override
	public Integer saveDHDetail(NSDLDHDtlsEntity nSDLDHDtlsEntity) {
		// TODO Auto-generated method stub
		return nsdlDetailsRepo.saveDHDetail(nSDLDHDtlsEntity);
	}

	@Override
	public Integer saveSDDetail(NSDLSDDtlsEntity nSDLSDDtlsEntity) {
		// TODO Auto-generated method stub
		return nsdlDetailsRepo.saveSDDetail(nSDLSDDtlsEntity);
	}

	@Override
	public Integer saveBHDetail(NSDLBHDtlsEntity nSDLBHDtlsEntity) {
		// TODO Auto-generated method stub
		return nsdlDetailsRepo.saveBHDetail(nSDLBHDtlsEntity);
	}

	@Override
	public List<NSDLDetailsModel> lstNsdlDDOWiseDtls(String ddoCode) {
		List<Object[]> lstprop = nsdlDetailsRepo.lstNsdlDDOWiseDtls(ddoCode);
		List<NSDLDetailsModel> lstObj = new ArrayList<>();
		if (!lstprop.isEmpty()) {
			for (Object[] objLst : lstprop) {
				NSDLDetailsModel obj = new NSDLDetailsModel();
				obj.setEmpName(StringHelperUtils.isNullString(objLst[0]));
				obj.setDdoCode(StringHelperUtils.isNullString(objLst[1]));
				obj.setSevaarthId(StringHelperUtils.isNullString(objLst[2]));
				obj.setOfficeName(StringHelperUtils.isNullString(objLst[3]));
				obj.setGrossTotal(StringHelperUtils.isNullBigDecimal(objLst[4]).doubleValue());
				obj.setNetTotal(StringHelperUtils.isNullBigDecimal(objLst[5]).doubleValue());
				obj.setAmtEmpContibution(StringHelperUtils.isNullBigDecimal(objLst[6]).doubleValue());
				obj.setAmtEmprContibution(StringHelperUtils.isNullBigDecimal(objLst[7]).doubleValue());
				obj.setDcpsId(StringHelperUtils.isNullString(objLst[8]));
				obj.setPRAN(StringHelperUtils.isNullString(objLst[9]));
				obj.setEmpStatus(StringHelperUtils.isNullString(objLst[10]));
				lstObj.add(obj);
			}
		}
		return lstObj;
	}

	@Override
	public String getBatchData(String fileno) {
		// TODO Auto-generated method stub
		return nsdlDetailsRepo.getBatchData(fileno);
	}

	@Override
	public List getDHData(String fileno) {
		// TODO Auto-generated method stub
		return nsdlDetailsRepo.getDHData(fileno);
	}

	@Override
	public List getSDDtls(String fileno, String ddoRegNo) {
		// TODO Auto-generated method stub
		return nsdlDetailsRepo.getSDDtls(fileno, ddoRegNo);
	}

	@Override
	public void updateFileStatus(int fileStatus, String fileno, String errorData) {
		// TODO Auto-generated method stub
		nsdlDetailsRepo.updateFileStatus(fileStatus, fileno, errorData);
	}

	@Override
	public void updateBatchId(String batchId, List dcpsId) {
		// TODO Auto-generated method stub
		nsdlDetailsRepo.updateBatchId(batchId, dcpsId);
	}

	@Override
	public List<Object[]> getNsdlEmpDataLevelwise(int month, int year, String userName) {

		// TODO Auto-generated method stub
		return nsdlDetailsRepo.getNsdlEmpDataLevelwise(month, year, userName);

	}

	@Override
	public List<NSDLDetailsModel> getEmployeeListNsdl1(Integer year, Integer month, String ddoCode) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		List<Object[]> lstprop = nsdlDetailsRepo.getEmployeeListNsdl1(year, month, ddoCode);
		List<NSDLDetailsModel> lstObj = new ArrayList<>();
		if (!lstprop.isEmpty()) {
			for (Object[] objLst : lstprop) {

				NSDLDetailsModel obj = new NSDLDetailsModel();
				obj.setEmpName(StringHelperUtils.isNullString(objLst[0]));
				obj.setPRAN(StringHelperUtils.isNullString(objLst[9]));
				// obj.setFileId(StringHelperUtils.isNullString(objLst[4]));
				if ((objLst[4]) != null) {
					BigDecimal gross = (BigDecimal) (objLst[4]);
					obj.setGrossTotal(gross.doubleValue());
				}
				if ((objLst[5]) != null) {
					BigDecimal net = (BigDecimal) (objLst[5]);
					obj.setNetTotal(net.doubleValue());
				}
				if ((objLst[6]) != null) {
					BigDecimal EmpAmtCont = (BigDecimal) (objLst[6]);
					obj.setAmtEmpContibution(EmpAmtCont.doubleValue());
				}
				if ((objLst[7]) != null) {
					BigDecimal EmprAmtCont = (BigDecimal) (objLst[7]);
					obj.setAmtEmprContibution(EmprAmtCont.doubleValue());
				}

				lstObj.add(obj);
			}
		}
		return lstObj;

	}

	@Override
	public List<DdoWiseNpsContriModel> searchDdoWiseContribution(int month, int year, OrgUserMst messages) {
		
		List<Object[]> lstprop = nsdlDetailsRepo.searchDdoWiseContribution(year, month, messages);
		List<DdoWiseNpsContriModel> lstDdoWiseNpsContriModel = new ArrayList<>();
		if (!lstprop.isEmpty()) {
			for (Object[] objLst : lstprop) {
				DdoWiseNpsContriModel ddoWiseNpsContriModel = new DdoWiseNpsContriModel();
				
				if(objLst[0]!=null) {
					ddoWiseNpsContriModel.setDdocode(StringHelperUtils.isNullString(objLst[0]));
				}
				
				if(objLst[1]!=null) {
					ddoWiseNpsContriModel.setEmpCountWithNoPran(StringHelperUtils.isNullBigInteger(objLst[1]));
				}
				
				
				if(objLst[2]!=null) {
					ddoWiseNpsContriModel.setEmpCountWithPran(StringHelperUtils.isNullBigInteger(objLst[2]));
				}
				
				
				if(objLst[3]!=null) {
					ddoWiseNpsContriModel.setTotalEmpContri(StringHelperUtils.isNullBigDecimal(objLst[3]).longValue());
				}
				
				
				if(objLst[4]!=null) {
					ddoWiseNpsContriModel.setTotalEmprContri(StringHelperUtils.isNullBigDecimal(objLst[4]).longValue());
				}
				
				
				if(objLst[4]!=null && objLst[3]!=null) {
					ddoWiseNpsContriModel.setTotalAmount(ddoWiseNpsContriModel.getTotalEmpContri()+ddoWiseNpsContriModel.getTotalEmprContri());
				}

				lstDdoWiseNpsContriModel.add(ddoWiseNpsContriModel);
			}
		}
		return lstDdoWiseNpsContriModel;
	}
}
