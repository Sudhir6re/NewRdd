package com.mahait.gov.in.fileupload;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;

import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.Part;

/* Added by Sudhir */
public class FileSecurityHandler {

	public static boolean isValidFile(String fileType, MultipartFile[] files) {

	    boolean isSafe = true;

	    for (Integer i = 0; i < files.length; i++) {
	        byte[] bytes = null;

	        try {
	            bytes = files[i].getBytes();
	        } catch (Exception e1) {
	            e1.printStackTrace();
	        }

	        if (bytes == null || bytes.length == 0) {
	            return true;
	        }

	        InputStream in = new ByteArrayInputStream(bytes);
	        File tmpFile = null;
	        Path tmpPath = null;

	        try {
	            // Check if fileType is valid
	            if (fileType == null || fileType.trim().isEmpty()) {
	                throw new IllegalArgumentException("Unknown file type specified!");
	            }

	        
	            tmpFile = File.createTempFile("uploaded-", null);
	            tmpPath = tmpFile.toPath();

	            long copiedBytesCount = Files.copy(in, tmpPath, StandardCopyOption.REPLACE_EXISTING);

	            if (copiedBytesCount != bytes.length) {
	                isSafe = false;
	                return isSafe;
	            }

	            DocumentDetector documentDetector;
	            DocumentSanitizer documentSanitizer;
	            switch (fileType) {
	                case "PDF":
	                    documentDetector = new PdfDocumentDetectorImpl();
	                    isSafe = documentDetector.isSafe(tmpFile);
	                    break;
	                case "IMAGE":
	                    documentSanitizer = new ImageDocumentSanitizerImpl();
	                    isSafe = documentSanitizer.madeSafe(tmpFile);
	                    break;
	                default:
	                    isSafe = false;
	                    return isSafe;
	            }

	            if (!isSafe) {
	                System.out.println("Detection of an unsafe file upload or unable to sanitize uploaded document!");
	                safelyRemoveFile(tmpPath);
	                return false;
	            }
	        } catch (Exception e) {
	            safelyRemoveFile(tmpPath);
	            isSafe = false;
	            return isSafe;
	        }

	        return isSafe;
	    }

	    return isSafe;
	}


	private static void safelyRemoveFile(Path p) {
		try {
			if (p != null) {
				if (!Files.deleteIfExists(p)) {
					Files.write(p, "-".getBytes("utf8"), StandardOpenOption.CREATE);
				}
			}
		} catch (Exception e) {
			System.out.println("Cannot safely remove file !" + e);
		}
	}

	private static String extractSubmittedFileName(Part part) {
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
	}

}
