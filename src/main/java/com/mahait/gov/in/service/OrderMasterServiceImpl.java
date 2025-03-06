package com.mahait.gov.in.service;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.mahait.gov.in.common.StringHelperUtils;
import com.mahait.gov.in.entity.CmnTalukaMst;
import com.mahait.gov.in.entity.GROrderDocumentEntity;
import com.mahait.gov.in.entity.HrPayOrderMst;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.EmpWiseCityClassModel;
import com.mahait.gov.in.model.MstGrOrderModel;
import com.mahait.gov.in.repository.OrderMasterRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderMasterServiceImpl implements OrderMasterService {
	
	
	@Autowired
	OrderMasterRepo orderMasterRepo;
	

	@Autowired
	private Environment environment;

	@Override
	public List<EmpWiseCityClassModel> findAllEmployee(String userName) {
		 List<Object[]> list = orderMasterRepo.findAllEmployee(userName);
			
			List<EmpWiseCityClassModel> listobj = new ArrayList<>();
			if(!list.isEmpty())
			{
				
				//employee_id,sevaarth_id,employee_full_name_en,district_code,taluka_code,city_class
				for(Object[] obj:list ) //for (Object[] objLst : lstprop) {
				{
					EmpWiseCityClassModel obj1 = new EmpWiseCityClassModel();
					obj1.setEmployeeId(StringHelperUtils.isNullInt(obj[0]));
					obj1.setSevaarthId(StringHelperUtils.isNullString(obj[1]));
					obj1.setEmpName(StringHelperUtils.isNullString(obj[2]));
					obj1.setDistrictId(StringHelperUtils.isNullString(obj[3]));
					obj1.setTalukaId(StringHelperUtils.isNullString(obj[4]));
					if(obj[5]!=null)
					obj1.setCityClass(StringHelperUtils.isNullString(obj[5].toString()));
					
					listobj.add(obj1);
				}
			}
			return listobj;
	}

	@Override
	public List<Object[]> getSubGRType(long parentGrpId) {
		// TODO Auto-generated method stub
		return orderMasterRepo.getSubGRType(parentGrpId);
	}

	@Override
	public String getDistrictId(String ddoCode) {
		// TODO Auto-generated method stub
		return orderMasterRepo.getDistrictId(ddoCode);
	}

	@Override
	public List<CmnTalukaMst> gettalukalst(String districtId) {
		// TODO Auto-generated method stub
		return orderMasterRepo.gettalukalst(districtId);
	}

	@Override
	public List<Long> findUsertype(String ddoCode) {
		// TODO Auto-generated method stub
		return orderMasterRepo.findUsertype(ddoCode);
	}

	@Override
	public Long saveMstGrOrder(MstGrOrderModel mstGrOrderModel, MultipartFile[] files,OrgUserMst messages) {
		
		
		HrPayOrderMst payOrderMst = new HrPayOrderMst();
		Long saveId = 0L;
//		logger.info(">>>><<<<< "+ mstGrOrderModel.getOrderDate());
		if(mstGrOrderModel!=null) {
			///payOrderMst.setOrderId(mstGrOrderModel.getGrOrderId());//setting order id
			payOrderMst.setOrderName(mstGrOrderModel.getSanctionOrderNo());
			payOrderMst.setOrderDate(new Timestamp(mstGrOrderModel.getDate().getTime()));
			payOrderMst.setGrType(mstGrOrderModel.getOrderType());
			///payOrderMst.setLocationCode(messages.getUpdatedByPost().getLocationCode());
			///payOrderMst.setCmnLanguageMst(cmnLanguageMst);
			payOrderMst.setTrnCounter(new Integer(1));
			payOrderMst.setDdoCode(mstGrOrderModel.getDdoCode());
			//payOrderMst.setOrgPostMstByCreatedByPost(messages.getCreatedByPost().getPostId());
			///payOrderMst.setOrgUserMstByCreatedBy(orgUserMst);
			payOrderMst.setCreatedDate(new Date());
			payOrderMst.setCreatedBy(messages.getUserId());
			payOrderMst.setCreatedByPost(messages.getCreatedByPost().getPostId());
			
		if(files.length>0) {
		//mstEmployeeNPSEntity.setEmployeeSpouseName(mstEmployeeEntity.getEmployeeSpouseName());
		String filePath = "";
		//mstEmployeeNPSEntity.setSevaarthId(mstEmployeeEntity.getSevaarthId());
		String osName = System.getProperty( "os.name" );
		if(osName.toLowerCase().contains("windows")) {
			filePath = "E:/output/grorder/";
		}else {
			filePath  = "/home/grorder/";
		}
		if (!new File(filePath).exists()) {
			new File(filePath).mkdirs();
		}
		
		
		 Integer saveIdnew=orderMasterRepo.saveGrOrder(payOrderMst,files);
		   for(int i=0;i<files.length;i++) {
	        	String filePathnew=saveAttachment(files,saveIdnew,mstGrOrderModel.getGrOrderId(),i);
	        	GROrderDocumentEntity grOrderDocumentEntity=new GROrderDocumentEntity();
	        	grOrderDocumentEntity.setFilePath(filePathnew);
	        	grOrderDocumentEntity.setCreatedDate(new Date());
	        	grOrderDocumentEntity.setIsActive('1');
	        	grOrderDocumentEntity.setCreatedUserId(messages.getUserId());
	        	//grOrderDocumentEntity.setGrDocId(Long.valueOf(saveIdnew));
	        	grOrderDocumentEntity.setGrOrderId(Long.valueOf(saveIdnew));
	        	Long saveDocid=orderMasterRepo.saveAdvanceDocuments(grOrderDocumentEntity);
	        }
			payOrderMst.setAttachmentId(saveIdnew.longValue());
		   
		/* try {
				File file = new File(filePath + files[0].getOriginalFilename());
				FileCopyUtils.copy(files[0].getBytes(),file);
				payOrderMst.setAttachmentId(saveIdnew.longValue());
			} catch (IOException e) {
				e.printStackTrace();
			}*/
		}
		 saveId = orderMasterRepo.saveMstGrOrder(payOrderMst);
		}
		return saveId;
		
	}
	public String saveAttachment(MultipartFile[] files, Integer saveIdnew, Long long1,Integer fileNo) {
		// department name/photo/employee_id/photo.jpg
		String res =null;
		if (files.length != 0) {
			int width = 963;
			int height = 640;
			try {
				byte[] bytes = files[fileNo].getBytes();
				
				String extension=StringUtils.getFilenameExtension(files[fileNo].getOriginalFilename());
			
				if (bytes.length != 0) {
					BufferedImage image = null;
					File f = null;
					InputStream in = new ByteArrayInputStream(bytes);
					image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
					image = ImageIO.read(in);
				
					String key = "";
					String rootPath = "";
					String strOSName = System.getProperty("os.name");
					boolean test = strOSName.contains("Windows");
					if (strOSName.contains("Windows")) {
						key = "serverGrOrderpath";
					} else {
						key = "serverGrOrderLinuxOS";
					}
					rootPath = environment.getRequiredProperty(key);
					rootPath += saveIdnew.toString();
					File dir = new File(rootPath);
					if (!dir.exists())
						dir.mkdirs();
					
					Date date=new Date();

				//	String name = files[fileNo].getOriginalFilename()+"."+extension;
					//String name = files[fileNo].getOriginalFilename();
					String name = saveIdnew+"."+extension;
					// Create the file on server
					File serverFile = new File(dir.getAbsolutePath() + File.separator + name);
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();

					res = dir.getAbsolutePath() + File.separator + name;

				} else {
					res = "";
				}
			} catch (Exception e) {
				e.printStackTrace();
				res = "";
			}
		}
		return res;
	}

	@Override
	public List<Long> getSubDDOs(Long postId) {
		// TODO Auto-generated method stub
		return orderMasterRepo.getSubDDOs(postId);
	}

	@Override
	public List<MstGrOrderModel> getsancOrderLst(String ddoCode) {
		 List<Object[]> list = orderMasterRepo.getsancOrderLst(ddoCode);
			
			List<MstGrOrderModel> listobj = new ArrayList<>();
			if(!list.isEmpty())
			{
				
				//employee_id,sevaarth_id,employee_full_name_en,district_code,taluka_code,city_class
				for(Object[] obj:list ) //for (Object[] objLst : lstprop) {
				{
					MstGrOrderModel obj1 = new MstGrOrderModel();
					obj1.setSanctionOrderNo(StringHelperUtils.isNullString(obj[0]));
					obj1.setDate(StringHelperUtils.isNullDate(obj[1]));
					obj1.setOfficeName(StringHelperUtils.isNullString(obj[2]));
					obj1.setDdoCode(StringHelperUtils.isNullString(obj[3]));
					
					listobj.add(obj1);
				}
			}
			return listobj;
	}
	@Override
	public List<MstGrOrderModel> getddoOff(String locationcodeArray) {
		 List<Object[]> list = orderMasterRepo.getddoOff(locationcodeArray);
			
			List<MstGrOrderModel> listobj = new ArrayList<>();
			if(!list.isEmpty())
			{
				
				//employee_id,sevaarth_id,employee_full_name_en,district_code,taluka_code,city_class
				for(Object[] obj:list ) //for (Object[] objLst : lstprop) {
				{
					MstGrOrderModel obj1 = new MstGrOrderModel();
					obj1.setDdoCode(StringHelperUtils.isNullString(obj[0]));
					obj1.setOfficeName(StringHelperUtils.isNullString(obj[1]));
					
					listobj.add(obj1);
				}
			}
			return listobj;
	}

	@Override
	public List<MstGrOrderModel> getInstitutionLst(String ddoCode) {
		 List<Object[]> list = orderMasterRepo.getInstitutionLst(ddoCode);
			
			List<MstGrOrderModel> listobj = new ArrayList<>();
			if(!list.isEmpty())
			{
				
				//employee_id,sevaarth_id,employee_full_name_en,district_code,taluka_code,city_class
				for(Object[] obj:list ) //for (Object[] objLst : lstprop) {
				{
					MstGrOrderModel obj1 = new MstGrOrderModel();
					obj1.setDdoCode(StringHelperUtils.isNullString(obj[0]));
					obj1.setOfficeName(StringHelperUtils.isNullString(obj[1]));
					
					listobj.add(obj1);
				}
			}
			return listobj;
	}
}
