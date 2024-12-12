package com.mahait.gov.in.nps.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.math.BigInteger;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.xml.namespace.QName;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mahait.gov.in.common.StringHelperUtils;
import com.mahait.gov.in.entity.MstEmployeeEntity;
import com.mahait.gov.in.nps.entity.MstEmployeeNPSEntity;
import com.mahait.gov.in.nps.entity.TrnNpsRegFileEntity;
import com.mahait.gov.in.nps.webservice.PerformFileUpload;
import com.mahait.gov.in.nps.webservice.STPWebServicePOJO;
import com.mahait.gov.in.nps.webservice.STPWebServicePOJOService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.xml.ws.BindingProvider;

@RequestMapping("/master")
@PropertySource(value = { "classpath:application.properties" })
@Controller
public class NSDLIntegration {
	private List<String> fileList;

	private static String OUTPUT_ZIP_FILE = "D:/output/HTE/";
	private static String OUTPUT_ZIP_Contri_FILE = "D:/output/HTE/Contribution/";

	@Autowired
	private Environment environment;

	@Autowired
	CSRFFormService csrfFormService;

	public NSDLIntegration() {
		fileList = new ArrayList<String>();
	}

	public String sendFile(HttpServletResponse response, String ddoCode, String batchId, String dtoUserId,
			String dtoRegNo)
			throws IOException, NoSuchAlgorithmException, KeyManagementException, SQLException, ClassNotFoundException {
		String str = "";
		try {
			System.out.println("creating ws");
			
			
			
			final QName SERVICE_NAME = new QName("http://webservice.core.stp.cra.com/", "STPWebServicePOJOService");
			URL wsdlURL = STPWebServicePOJOService.WSDL_LOCATION;
			STPWebServicePOJOService ss = new STPWebServicePOJOService(wsdlURL);
			System.out.println("creating ws1");
			STPWebServicePOJO port = ss.getSTPWebServicePOJOPort();
			System.out.println("https://www.npscan-cra.com/STPWeb/STPWebServicePOJOPort?wsdl");
			System.out.println("creating ws2");
			BindingProvider bindingProvider = (BindingProvider) port;
			
			
			 String[] activeProfiles = environment.getActiveProfiles();
		        if (activeProfiles.length > 0 && activeProfiles[0].equalsIgnoreCase("prod")) {
		           // return activeProfiles[0];
		        	bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
							 "https://www.npscan-cra.com/STPWeb/STPWebServicePOJOPort?wsdl");
		        }else {
		        	bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
							// "https://cra-nsdl.com/STPWeb/STPWebServicePOJOPort?wsdl");
							"http://121.240.64.236/STPWeb/STPWebServicePOJOPort");
		        }
			
			

			System.out.println("creating ws3");
			// {
			System.out.println("Invoking performFileUpload...");
			java.util.List<byte[]> _performFileUpload_arg0 = new java.util.ArrayList<byte[]>();
			// String filePath = env.getProperty("jasper.pdffiles.path").concat(path);
			// File downloadFile = new File("E:/Test/subscriberRegistration.txt");
			// File downloadFile = new File("/disk1/nps/subscriberRegistration.txt");

			String key = "";
			String rootPath = "";
			String strOSName = System.getProperty("os.name");
			boolean test = strOSName.contains("Windows");
			if (strOSName.contains("Windows")) {
				// key = "serverempconfigimagepath";
			} else {
				key = "npsfilepathinLinusOS";
				OUTPUT_ZIP_FILE = environment.getRequiredProperty(key);
			}

			String directoryName = OUTPUT_ZIP_FILE + ddoCode + "/" + batchId + "/Upload/subreg_after_fuv";
			File directory = new File(directoryName);
			if (!directory.exists()) {
				directory.mkdir();
				// If you require it to make the entire directory path including
				// parents,
				// use directory.mkdirs(); here instead.
			}
			System.out.println("hello every one " + ddoCode);
			String zipFolderName = "subreg_after_fuv.zip";
			// String zipFileName = "/disk1/dto_sign/"+ddoCode+".sig";
			// String fileUpload="C:/Test/sign/"+ddoCode+".sig";
			String fileUpload = OUTPUT_ZIP_FILE + "/DTO_SIGN/" + dtoUserId + ".sig"; // 43

			// String fileUpload= "/disk2/disk1/DTO_SIGN/"+ddoCode+".sig"; //tomcat9

			// String fileUpload="/disk2/disk1/dto_sign/"+ddoCode+".sig";
			// OUTPUT_ZIP_FILE = "/disk1/nps/"+zipFolderName;
			// String SOURCE_FOLDER = "/disk1/nps/"+ddoCode;
			// String[] inputParamaeters = {"/disk1/nps/"+ddoCode};
			File source = new File(fileUpload);
			// File dest = new
			// File("E:/Test/"+ddoCode+"/"+batchId+"/Upload/contri/"+ddoCode+".sig");
			File dest = new File(
					OUTPUT_ZIP_FILE + ddoCode + "/" + batchId + "/Upload/subreg_after_fuv/" + dtoUserId + ".sig");
			FileUtils.copyFile(source, dest);

			File sourceFile = new File(
					OUTPUT_ZIP_FILE + ddoCode + "/" + batchId + "/Upload/subscriberRegNsdl_verified.txt");
			File destFile = new File(OUTPUT_ZIP_FILE + ddoCode + "/" + batchId
					+ "/Upload/subreg_after_fuv/SubReg_Verified_After_FUV.sub");

			if (sourceFile.renameTo(destFile)) {
				System.out.println("File moved successfully");
			} else {
				System.out.println("Failed to move file");
			}

			// craFVUpaosubcontr.main(inputParamaeters);
			// OUTPUT_ZIP_FILE = "E:/Test/"+zipFolderName;
			// String SOURCE_FOLDER = "E:/Test/"+ddoCode; // SourceFolder path
			// NSDLIntegration appZip = new NSDLIntegration();
			String outputzipFile = OUTPUT_ZIP_FILE + ddoCode + "/" + batchId + "/" + zipFolderName;

			String[] srcFiles = {
					OUTPUT_ZIP_FILE + ddoCode + "/" + batchId
							+ "/Upload/subreg_after_fuv/SubReg_Verified_After_FUV.sub",
					OUTPUT_ZIP_FILE + ddoCode + "/" + batchId + "/Upload/subreg_after_fuv/" + dtoUserId + ".sig" };
			try {

				// create byte buffer
				byte[] buffer = new byte[1024];
				FileOutputStream fos = new FileOutputStream(outputzipFile);
				ZipOutputStream zos = new ZipOutputStream(fos);

				for (int i = 0; i < srcFiles.length; i++) {

					File srcFile = new File(srcFiles[i]);
					FileInputStream fis = new FileInputStream(srcFile);
					// begin writing a new ZIP entry, positions the stream to the start of the entry
					// data
					zos.putNextEntry(new ZipEntry(srcFile.getName()));

					int length;
					while ((length = fis.read(buffer)) > 0) {
						zos.write(buffer, 0, length);
					}
					zos.closeEntry();
					// close the InputStream
					fis.close();

				}
				// close the ZipOutputStream
				zos.close();

			} catch (Exception e) {

				e.printStackTrace();
			}

			// OUTPUT_ZIP_FILE = "E:/Test/contri/"+zipFolderName;
			// String SOURCE_FOLDER = "E:/Test/"+ddoCode; // SourceFolder path
			// appZip.generateFileList(new File(SOURCE_FOLDER),SOURCE_FOLDER);
			// appZip.zipIt(OUTPUT_ZIP_FILE,SOURCE_FOLDER);

			// HttpServletResponse response = (HttpServletResponse)
			// inputMap.get("responseObj");
			// ZipUtil.pack(new File("D:\\reports\\january\\"), new
			// File("D:\\reports\\january.zip"));

			// response.setContentType("APPLICATION/OCTET-STREAM");

			// response.setHeader("Content-disposition","attachment; filename=\""
			// +zipFolderName+"\""); // Used to name the download file and its format
			// File my_file = new File("E://outputtext.txt"); // We are downloading .txt
			// file, in the format of doc with name check - check.doc

			// OutputStream out = response.getOutputStream();
			// FileInputStream in = new FileInputStream(OUTPUT_ZIP_FILE);
			/*
			 * byte[] buffer = new byte[4096];40707061000000399 int length; while ((length =
			 * in.read(buffer)) > 0){ out.write(buffer, 0, length); } in.close();
			 * out.flush();
			 */// OUTPUT_ZIP_FILE = "E:/Test/"+zipFolderName;
				// File sourceFile = new
				// File("E:/Test/"+ddoCode+"/9910000236/Upload/subscriberRegistration_verified.txt");
				// File destFile = new
				// File("E:/Test/"+ddoCode+"/9910000236/Upload/contri/"+ddoCode+"/subscriberRegistration_verified.sub");

			if (sourceFile.renameTo(destFile)) {
				System.out.println("File moved successfully");
			} else {
				System.out.println("Failed to move file");
			}
			System.out.println("--------" + ddoCode);
			// String zipFileName = OUTPUT_ZIP_FILE;//"E:/nsdl/"+ddoCode+".zip";
			String zipFileName = OUTPUT_ZIP_FILE + ddoCode + "/" + batchId + "/" + zipFolderName;
			PerformFileUpload fileUploadd = new PerformFileUpload();
			byte[] fileByte;
			File zipFile = new File(outputzipFile);
			FileInputStream fis = new FileInputStream(zipFileName);

			fileByte = new byte[(int) zipFile.length()];
			fis.read(fileByte);
			System.out.println("Hello every one");
			System.out.println("--" + fileByte.toString());
			byte[][] _performFileUpload_arg0Val1 = new byte[3][0];
			_performFileUpload_arg0Val1[0] = dtoUserId.getBytes();
			_performFileUpload_arg0Val1[1] = "Upload SubscriberRegistration-DSC".getBytes(); // ye wala
			_performFileUpload_arg0Val1[2] = fileByte; // zip file byte
			_performFileUpload_arg0.add(_performFileUpload_arg0Val1[0]);
			_performFileUpload_arg0.add(_performFileUpload_arg0Val1[1]);
			_performFileUpload_arg0.add(_performFileUpload_arg0Val1[2]);
			java.lang.String _performFileUpload__return = port.performFileUpload(_performFileUpload_arg0);
			System.out.println("performFileUpload.result=" + _performFileUpload__return);
			str = _performFileUpload__return.substring(
					_performFileUpload__return.indexOf("<file-reference-number>") + "<file-reference-number>".length(),
					_performFileUpload__return.indexOf("</file-reference-number>"));
		} finally {

		}
		/*
		 * Class.forName("com.ibm.db2.jcc.DB2Driver"); connectionDB=
		 * DriverManager.getConnection(connectionUrl, username, password);
		 * connectionDB.setAutoCommit(false);
		 * 
		 * String sql="UPDATE ifms.TRN_NPS_REG_FILE SET NSDL_STATUS_CODE='"
		 * +str+"' WHERE BATCH_FIX_ID='"+batchId+"'"; PreparedStatement statement =
		 * connectionDB.prepareStatement(sql); statement.executeUpdate();
		 * statement.close(); connectionDB.commit();
		 */
		return str;

	}

	public String getStatus(HttpServletResponse response, String ddoCode, String refCode, String dtoUserId,
			String dtoRegNo)
			throws IOException, NoSuchAlgorithmException, KeyManagementException, SQLException, ClassNotFoundException {

		final QName SERVICE_NAME = new QName("http://webservice.core.stp.cra.com/", "STPWebServicePOJOService");
		URL wsdlURL = STPWebServicePOJOService.WSDL_LOCATION;

		STPWebServicePOJOService ss = new STPWebServicePOJOService(wsdlURL);
		System.out.println("creating ws1");
		STPWebServicePOJO port = ss.getSTPWebServicePOJOPort();
		System.out.println("https://www.npscan-cra.com/STPWeb/STPWebServicePOJOPort?wsdl");
		System.out.println("creating ws2");
		BindingProvider bindingProvider = (BindingProvider) port;
	/*	bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
				// "https://cra-nsdl.com/STPWeb/STPWebServicePOJOPort?wsdl");
				"http://121.240.64.236/STPWeb/STPWebServicePOJOPort");*/
		
		
		
		 String[] activeProfiles = environment.getActiveProfiles();
	        if (activeProfiles.length > 0 && activeProfiles[0].equalsIgnoreCase("prod")) {
	           // return activeProfiles[0];
	        	bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
						 "https://www.npscan-cra.com/STPWeb/STPWebServicePOJOPort?wsdl");
	        }else {
	        	bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
						// "https://cra-nsdl.com/STPWeb/STPWebServicePOJOPort?wsdl");
						"http://121.240.64.236/STPWeb/STPWebServicePOJOPort");
	        }
		
		System.out.println("creating ws3");

		System.out.println("Invoking perform status enquiry...");
		java.util.List<byte[]> _performStatusInquiry_arg0 = new java.util.ArrayList<byte[]>();

		String key = "";
		String rootPath = "";
		String strOSName = System.getProperty("os.name");
		boolean test = strOSName.contains("Windows");
		if (strOSName.contains("Windows")) {
			// key = "serverempconfigimagepath";
		} else {
			key = "npsfilepathinLinusOS";
			OUTPUT_ZIP_FILE = environment.getRequiredProperty(key);
		}

		String zipFileName = OUTPUT_ZIP_FILE + "DTO_SIGN/" + dtoUserId + ".sig"; // 43

		PerformFileUpload fileUpload = new PerformFileUpload();
		byte[] fileByte;
		File zipFile = new File(zipFileName);
		FileInputStream fis = new FileInputStream(zipFileName);
		fileByte = new byte[(int) zipFile.length()];
		fis.read(fileByte);
		String statusRefCode = refCode;// 347602
		System.out.println(fileByte.toString());
		String userId = dtoUserId.substring(0, dtoUserId.length() - 2);
		byte[][] _performStatusInquiry_arg0Val1 = new byte[5][0];
		_performStatusInquiry_arg0Val1[0] = dtoUserId.getBytes();
		_performStatusInquiry_arg0Val1[1] = statusRefCode.getBytes();
		_performStatusInquiry_arg0Val1[2] = userId.getBytes(); // zip file byte
		_performStatusInquiry_arg0Val1[3] = fileByte; // zip file byte
		_performStatusInquiry_arg0Val1[4] = "File Status-SubscriberRegistration".getBytes();
		; // zip file byte

		_performStatusInquiry_arg0.add(_performStatusInquiry_arg0Val1[0]);
		_performStatusInquiry_arg0.add(_performStatusInquiry_arg0Val1[1]);
		_performStatusInquiry_arg0.add(_performStatusInquiry_arg0Val1[2]);
		_performStatusInquiry_arg0.add(_performStatusInquiry_arg0Val1[3]);
		_performStatusInquiry_arg0.add(_performStatusInquiry_arg0Val1[4]);
		java.lang.String _performStatusInquiry__return = port.performStatusInquiry(_performStatusInquiry_arg0);
		System.out.println("performStatusInquiry.result=" + _performStatusInquiry__return);
		String dataList = null;

		try {
			String error = _performStatusInquiry__return.substring(
					_performStatusInquiry__return.indexOf("<error-code>") + "<error-code>".length(),
					_performStatusInquiry__return.indexOf("</error-code>"));
			if (error.length() == 0) {
				String str = _performStatusInquiry__return.substring(
						_performStatusInquiry__return.indexOf("<pran_list>") + "<pran_list>".length(),
						_performStatusInquiry__return.indexOf("</pran_list>"));
				String total = _performStatusInquiry__return.substring(
						_performStatusInquiry__return.indexOf("<total_records>") + "<total_records>".length(),
						_performStatusInquiry__return.indexOf("</total_records>"));
				ArrayList data = new ArrayList<>();
				String splitChr = "\\^";
				Long dcpsId = null;

				String[] fmgStrng = str.split(splitChr);
				System.out.println("frmString==" + fmgStrng.length);
				int j = 11;
				for (int i = 0; i < fmgStrng.length; i++) {
					if (j < i) {
						System.out.println(
								"frmString==" + j + "frmvalue==" + fmgStrng[j] + "pran= " + fmgStrng[j + 1] + "\r\n");

						// select nps_id from trn_nps_reg_file where ack_no='40000211000000002'

						Integer npsId = 0;
						Integer id = 0;

						List<Object[]> lstobj = csrfFormService.findNpsIdByAckNo(fmgStrng[j + 1]);

						for (Object obj[] : lstobj) {
							npsId = StringHelperUtils.isNullInt(obj[0]);
							id = StringHelperUtils.isNullInt(obj[1]);
						}

						MstEmployeeNPSEntity mstEmployeeNPSEntity = csrfFormService.findEmployeeByNpsid(npsId);
						mstEmployeeNPSEntity.setAckNo(new BigInteger(fmgStrng[j + 1]));
						mstEmployeeNPSEntity.setPranNo(fmgStrng[j]);
						csrfFormService.updatePranNumberByNpsId(mstEmployeeNPSEntity);

						TrnNpsRegFileEntity trnNpsRegFileEntity = csrfFormService.findTrnNpsFileEntityById(id);
						trnNpsRegFileEntity.setAckNo(new BigInteger(fmgStrng[j + 1]));
						trnNpsRegFileEntity.setIsPranGenerated(1);
						csrfFormService.updateTrnNpsFileEntity(trnNpsRegFileEntity);

						MstEmployeeEntity mstEmployeeEntity = csrfFormService
								.findEmployeeBySevaarthId(mstEmployeeNPSEntity.getEmployeeId());
						mstEmployeeEntity.setPranNo(fmgStrng[j]);
						csrfFormService.updateEmployeeByEmpId(mstEmployeeEntity);

						/*
						 * by sudhir Statement stmt = connectionDB.createStatement(); String query =
						 * "SELECT DCPS_EMP_ID FROM ifms.mst_nps_emp where ACK_NO="+fmgStrng[j+1];
						 * ResultSet rs=stmt.executeQuery(query); //Extact result from ResultSet rs
						 * while(rs.next()){
						 * //System.out.println("MAX(user_id)="+rs.getInt("DCPS_EMP_ID")); dcpsId=(long)
						 * rs.getLong("DCPS_EMP_ID"); } // end user
						 * 
						 * 
						 * 
						 * /* by sudhir
						 * 
						 * String sql =
						 * "UPDATE MST_DCPS_EMP SET PRAN_VALIDATED=1,PRAN_NO ='"+fmgStrng[j]
						 * +"' where DCPS_EMP_ID='"+dcpsId+"' "; PreparedStatement statement =
						 * connectionDB.prepareStatement(sql); statement.executeUpdate();
						 * connectionDB.commit();
						 * 
						 * 
						 * String sql1 =
						 * "UPDATE ifms.MST_NPS_EMP SET STATUS=6 WHERE ACK_NO='"+fmgStrng[j+1]
						 * +"' and STATUS =4 "; statement = connectionDB.prepareStatement(sql1);
						 * statement.executeUpdate(); statement.close(); connectionDB.commit();
						 */

						j = j + 10;
					}
				}
				// connectionDB.commit();
				dataList = "Success";

				/*
				 * } else {
				 */ dataList = null;
				String str1 = _performStatusInquiry__return.substring(
						_performStatusInquiry__return.indexOf("<response_html>") + "<response_html>".length(),
						_performStatusInquiry__return.indexOf("</response_html>"));
				System.out.println("length==" + str1.length());
				if (!str1.isEmpty() && str1.length() > 16) {
					StringBuilder htmlStringBuilder = new StringBuilder();
					// append html header and title
					htmlStringBuilder.append(str1);
					WriteToFile(htmlStringBuilder.toString(), "testfile.html", ddoCode);
				} else {
					dataList = "Success";
				}
				// }
			} else {
				dataList = "error";
			}
		} catch (Exception e) {
			dataList = null;
			return dataList;
		}
		return dataList;
	}

	public static void WriteToFile(String fileContent, String fileName, String ddoCode) throws IOException {
		String projectPath = OUTPUT_ZIP_FILE + ddoCode;

		File file1 = new File(projectPath);
		if (!file1.exists()) {
			file1.mkdir();
		}

		String tempFile = projectPath + File.separator + fileName;
		File file = new File(tempFile);
		// if file does exists, then delete and create a new file
		if (file.exists()) {
			try {
				File newFileName = new File(projectPath + File.separator + "backup_" + fileName);
				file.renameTo(newFileName);
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// write to file with OutputStreamWriter
		OutputStream outputStream = new FileOutputStream(file.getAbsoluteFile());
		Writer writer = new OutputStreamWriter(outputStream);
		writer.write(fileContent);
		writer.close();
	}

	public static void WriteToFileTrn(String fileContent, String fileName, String ddoCode, String str, String batchId)
			throws IOException {
		String projectPath = OUTPUT_ZIP_Contri_FILE + ddoCode + "/" + batchId;
		File directory = new File(projectPath);
		if (!directory.exists()) {
			directory.mkdir();
			// If you require it to make the entire directory path including
			// parents,
			// use directory.mkdirs(); here instead.
		}

		String tempFile = projectPath + File.separator + fileName;
		File file = new File(tempFile);
		// if file does exists, then delete and create a new file
		if (file.exists()) {
			try {
				File newFileName = new File(projectPath + File.separator + "backup_" + fileName);
				file.renameTo(newFileName);
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// write to file with OutputStreamWriter
		OutputStream outputStream = new FileOutputStream(file.getAbsoluteFile());
		Writer writer = new OutputStreamWriter(outputStream);
		writer.write(fileContent);
		writer.close();
	}

	public void generateFileList(File node, String SOURCE_FOLDER) {
		// add file only
		System.out.println("file source folder:- " + node.getAbsolutePath());
		if (node.isFile()) {
			fileList.add(generateZipEntry(node.toString(), SOURCE_FOLDER));
		}
		if (node.isDirectory()) {
			String[] subNote = node.list();
			for (String filename : subNote) {
				generateFileList(new File(node, filename), SOURCE_FOLDER);
			}
		}
	}

	private String generateZipEntry(String file, String SOURCE_FOLDER) {
		String filechange = file.replace("\\", "/");
		return filechange.substring(SOURCE_FOLDER.length(), filechange.length());
	}

	public void zipIt(String zipFile, String SOURCE_FOLDER) {
		byte[] buffer = new byte[1024];
		String source = new File(SOURCE_FOLDER).getName();
		FileOutputStream fos = null;
		ZipOutputStream zos = null;
		try {
			fos = new FileOutputStream(zipFile);
			zos = new ZipOutputStream(fos);
			// String relativePath =
			// httpServlet.getServletContext().getRealPath("");
			// System.out.println("relativePath = " + relativePath);
			System.out.println("output to Zip : " + zipFile);
			FileInputStream in = null;
			for (String file : this.fileList) {
				System.out.println("File Added : " + file);
				ZipEntry ze = new ZipEntry(source + File.separator + file);
				zos.putNextEntry(ze);
				try {
					in = new FileInputStream(SOURCE_FOLDER + File.separator + file);
					int len;
					while ((len = in.read(buffer)) > 0) {
						zos.write(buffer, 0, len);
					}
				} finally {
					in.close();
				}
			}
			zos.closeEntry();
			System.out.println("Folder successfully compressed");
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				zos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public String sendContriFile(HttpServletResponse response, String ddoCode, String batchId, String dtoUserId,
			String dtoRegNo)
			throws IOException, NoSuchAlgorithmException, KeyManagementException, ClassNotFoundException, SQLException {
		String str = "";
		try {
			System.out.println("creating ws");
			final QName SERVICE_NAME = new QName("http://webservice.core.stp.cra.com/", "STPWebServicePOJOService");
			URL wsdlURL = STPWebServicePOJOService.WSDL_LOCATION;
			STPWebServicePOJOService ss = new STPWebServicePOJOService(wsdlURL);
			System.out.println("creating ws1");
			STPWebServicePOJO port = ss.getSTPWebServicePOJOPort();
			System.out.println("https://www.npscan-cra.com/STPWeb/STPWebServicePOJOPort?wsdl");
			System.out.println("creating ws2");
			BindingProvider bindingProvider = (BindingProvider) port;
			/*bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
					// "https://www.npscan-cra.com/STPWeb/STPWebServicePOJOPort?wsdl");
					"http://121.240.64.237/STPWeb/STPWebServicePOJOPort");*/
			
			
			 String[] activeProfiles = environment.getActiveProfiles();
		        if (activeProfiles.length > 0 && activeProfiles[0].equalsIgnoreCase("prod")) {
		           // return activeProfiles[0];
		        	bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
							 "https://www.npscan-cra.com/STPWeb/STPWebServicePOJOPort?wsdl");
		        }else {
		        	bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
							// "https://cra-nsdl.com/STPWeb/STPWebServicePOJOPort?wsdl");
							"http://121.240.64.236/STPWeb/STPWebServicePOJOPort");
		        }

			System.out.println("creating ws3");

			// {
			System.out.println("Invoking performFileUpload...");
			java.util.List<byte[]> _performFileUpload_arg0 = new java.util.ArrayList<byte[]>();

			String fileUpload = OUTPUT_ZIP_FILE + "DTO_SIGN\\" + dtoUserId + ".sig";
			
			System.out.println();

			// important code for path start
			// Get Image start
			String key = "";
			String rootPath = "";
			String strOSName = System.getProperty("os.name");
			boolean test = strOSName.contains("Windows");
			if (strOSName.contains("Windows")) {
				// key = "serverempconfigimagepath";
			} else {
				key = "serverContributionFolderPath";
				OUTPUT_ZIP_Contri_FILE = environment.getRequiredProperty(key);
				fileUpload = OUTPUT_ZIP_Contri_FILE + "/DTO_SIGN/"+ dtoUserId + ".sig";

			}

			String directoryName = OUTPUT_ZIP_Contri_FILE + ddoCode + "/" + batchId + "/output/contri_after_fuv";
			File directory = new File(directoryName);
			if (!directory.exists()) {
				directory.mkdir();
				// If you require it to make the entire directory path including
				// parents,
				// use directory.mkdirs(); here instead.
			}
			System.out.println("hello every one " + ddoCode);
			String zipFolderName = "contri_after_fuv.zip";

			File source = new File(fileUpload);
			File dest = new File(
					OUTPUT_ZIP_Contri_FILE + ddoCode + "/" + batchId + "/output/contri_after_fuv/" + dtoUserId + ".sig");
			FileUtils.copyFile(source, dest);

			File sourceFile = new File(OUTPUT_ZIP_Contri_FILE + ddoCode + "/" + batchId + "/" + batchId + ".fvu");
			File destFile = new File(
					OUTPUT_ZIP_Contri_FILE + ddoCode + "/" + batchId + "/output/contri_after_fuv/contributionFile.pao");

			if (!destFile.exists()) {
				destFile.createNewFile();
			}

			FileUtils.copyFile(sourceFile, destFile);

			/*
			 * if (sourceFile.renameTo(destFile)) {
			 * System.out.println("File moved successfully"); } else {
			 * System.out.println("Failed to move file"); }
			 */

			String outputzipFile = OUTPUT_ZIP_Contri_FILE + ddoCode + "/" + batchId + "/" + zipFolderName;

			String[] srcFiles = {
					OUTPUT_ZIP_Contri_FILE + ddoCode + "/" + batchId + "/output/contri_after_fuv/contributionFile.pao",
					OUTPUT_ZIP_Contri_FILE + ddoCode + "/" + batchId + "/output/contri_after_fuv/" + dtoUserId
							+ ".sig" };
			try {

				// create byte buffer
				byte[] buffer = new byte[1024];
				FileOutputStream fos = new FileOutputStream(outputzipFile);
				ZipOutputStream zos = new ZipOutputStream(fos);

				for (int i = 0; i < srcFiles.length; i++) {

					File srcFile = new File(srcFiles[i]);
					FileInputStream fis = new FileInputStream(srcFile);
					// begin writing a new ZIP entry, positions the stream to the start of the entry
					// data
					zos.putNextEntry(new ZipEntry(srcFile.getName()));

					int length;
					while ((length = fis.read(buffer)) > 0) {
						zos.write(buffer, 0, length);
					}
					zos.closeEntry();
					// close the InputStream
					fis.close();

				}
				// close the ZipOutputStream
				zos.close();

			} catch (Exception e) {

				e.printStackTrace();
			}

			System.out.println("--------" + ddoCode);
			// String zipFileName = OUTPUT_ZIP_FILE;//"E:/nsdl/"+ddoCode+".zip";
			String zipFileName = OUTPUT_ZIP_Contri_FILE + ddoCode + "/" + batchId + "/" + zipFolderName;
			PerformFileUpload fileUploadd = new PerformFileUpload();
			byte[] fileByte;
			File zipFile = new File(outputzipFile);
			FileInputStream fis = new FileInputStream(zipFileName);

			fileByte = new byte[(int) zipFile.length()];
			fis.read(fileByte);
			System.out.println("Hello every one 16-11-2021");
			System.out.println("--" + fileByte.toString());
			byte[][] _performFileUpload_arg0Val1 = new byte[3][0];
			_performFileUpload_arg0Val1[0] = dtoUserId.getBytes();
			_performFileUpload_arg0Val1[1] = "Upload SubscriberContribution-DSC".getBytes(); // ye wala
			_performFileUpload_arg0Val1[2] = fileByte; // zip file byte
			_performFileUpload_arg0.add(_performFileUpload_arg0Val1[0]);
			_performFileUpload_arg0.add(_performFileUpload_arg0Val1[1]);
			_performFileUpload_arg0.add(_performFileUpload_arg0Val1[2]);
			java.lang.String _performFileUpload__return = port.performFileUpload(_performFileUpload_arg0);
			System.out.println("performFileUpload.result=" + _performFileUpload__return);
			
			str = _performFileUpload__return.substring(
					_performFileUpload__return.indexOf("<file-reference-number>") + "<file-reference-number>".length(),
					_performFileUpload__return.indexOf("</file-reference-number>"));
		} finally {

		}

		return str;

	}

	public String getContriStatus(HttpServletResponse response, String ddoCode, String refCode, String dtoUserId,
			String batchId, String dtoRegNo)
			throws IOException, NoSuchAlgorithmException, KeyManagementException, ClassNotFoundException, SQLException {

		final QName SERVICE_NAME = new QName("http://webservice.core.stp.cra.com/", "STPWebServicePOJOService");
		URL wsdlURL = STPWebServicePOJOService.WSDL_LOCATION;

		STPWebServicePOJOService ss = new STPWebServicePOJOService(wsdlURL);
		System.out.println("creating ws1");
		STPWebServicePOJO port = ss.getSTPWebServicePOJOPort();
		System.out.println("creating ws2");
		System.out.println("https://www.npscan-cra.com/STPWeb/STPWebServicePOJOPort?wsdl");
		BindingProvider bindingProvider = (BindingProvider) port;
		/*bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
				// "https://www.npscan-cra.com/STPWeb/STPWebServicePOJOPort?wsdl");
				"http://121.240.64.237/STPWeb/STPWebServicePOJOPort");*/

		
		 String[] activeProfiles = environment.getActiveProfiles();
	        if (activeProfiles.length > 0 && activeProfiles[0].equalsIgnoreCase("prod")) {
	           // return activeProfiles[0];
	        	bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
						 "https://www.npscan-cra.com/STPWeb/STPWebServicePOJOPort?wsdl");
	        }else {
	        	bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
						// "https://cra-nsdl.com/STPWeb/STPWebServicePOJOPort?wsdl");
						"http://121.240.64.236/STPWeb/STPWebServicePOJOPort");
	        }
	        
	        
		
		System.out.println("creating ws3");

		System.out.println("Invoking perform status enquiry...16-11");
		java.util.List<byte[]> _performStatusInquiry_arg0 = new java.util.ArrayList<byte[]>();

		// String zipFileName = "/disk2/disk1/dto_sign/"+ddoCode+".sig";
		// String zipFileName="E:/sign/"+ddoCode+".sig";

		// String zipFileName = "/disk2/disk1/DTO_SIGN/"+ddoCode+".sig"; //tomcat9
		// String zipFileName="/disk1/NPS/DTO_SIGN/"+dtoRegNo+".sig"; //43

		// C:\output\Jalsevaarth\Contribution\7101003892\3333202201001\output\contri_after_fuv

		System.out.println("ref code>>>" + refCode);

		String zipFileName = OUTPUT_ZIP_Contri_FILE + ddoCode + "//" + batchId + "//output//contri_after_fuv//"
				+ dtoUserId + ".sig";

		// important code for path start
		// Get Image start
		String key = "";
		String rootPath = "";
		String strOSName = System.getProperty("os.name");
		boolean test = strOSName.contains("Windows");
		if (strOSName.contains("Windows")) {
			// key = "serverempconfigimagepath";
		} else {
			key = "serverContributionFolderPath";
			OUTPUT_ZIP_Contri_FILE = environment.getRequiredProperty(key);
			zipFileName = OUTPUT_ZIP_Contri_FILE + "/DTO_SIGN/" + dtoUserId + ".sig";
			;

		}

		PerformFileUpload fileUpload = new PerformFileUpload();
		byte[] fileByte;
		File zipFile = new File(zipFileName);
		FileInputStream fis = new FileInputStream(zipFileName);
		fileByte = new byte[(int) zipFile.length()];
		fis.read(fileByte);
		String statusRefCode = refCode;// 347602
		System.out.println(fileByte.toString());
		String userId = dtoUserId.substring(0, dtoUserId.length() - 2);
		byte[][] _performStatusInquiry_arg0Val1 = new byte[5][0];
		_performStatusInquiry_arg0Val1[0] = dtoUserId.getBytes();
		_performStatusInquiry_arg0Val1[1] = statusRefCode.getBytes();
		_performStatusInquiry_arg0Val1[2] = userId.getBytes(); // zip file byte
		_performStatusInquiry_arg0Val1[3] = fileByte; // zip file byte
		_performStatusInquiry_arg0Val1[4] = "File Status-SubscriberContribution".getBytes();
		; // zip file byte

		_performStatusInquiry_arg0.add(_performStatusInquiry_arg0Val1[0]);
		_performStatusInquiry_arg0.add(_performStatusInquiry_arg0Val1[1]);
		_performStatusInquiry_arg0.add(_performStatusInquiry_arg0Val1[2]);
		_performStatusInquiry_arg0.add(_performStatusInquiry_arg0Val1[3]);
		_performStatusInquiry_arg0.add(_performStatusInquiry_arg0Val1[4]);
		java.lang.String _performStatusInquiry__return = port.performStatusInquiry(_performStatusInquiry_arg0);
		System.out.println("performStatusInquiry.result=" + _performStatusInquiry__return);

		String str = _performStatusInquiry__return.substring(
				_performStatusInquiry__return.indexOf("<transaction_id>") + "<transaction_id>".length(),
				_performStatusInquiry__return.indexOf("</transaction_id>"));
		if (!str.isEmpty() && !str.equals("0")) {
			String str1 = _performStatusInquiry__return.substring(
					_performStatusInquiry__return.indexOf("<contr_submission_form_html>")
							+ "<contr_submission_form_html>".length(),
					_performStatusInquiry__return.indexOf("</response_html>"));
			StringBuilder htmlStringBuilder = new StringBuilder();
			// append html header and title
			htmlStringBuilder.append(str1);
			WriteToFileTrn(htmlStringBuilder.toString(), str + "_Challan.html", ddoCode, str, batchId);

		}
		if (str.isEmpty()) {

			String str1 = _performStatusInquiry__return.substring(
					_performStatusInquiry__return.indexOf("<response_html>") + "<response_html>".length(),
					_performStatusInquiry__return.indexOf("</response_html>"));
			StringBuilder htmlStringBuilder = new StringBuilder();
			// append html header and title
			htmlStringBuilder.append(str1);
			WriteToFile(htmlStringBuilder.toString(), "testfile.html", ddoCode);

		}
		return str;
	}

}
