package com.mahait.gov.in.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.FileCopyUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import jakarta.servlet.http.HttpServletResponse;

@Component
public class PdfGenaratorUtil {
//	protected final Log logger = LogFactory.getLog(getClass());
	@Autowired
	private TemplateEngine templateEngine;
	
	public void createPdf(String templateName, Map map,HttpServletResponse response) throws Exception {
		Assert.notNull(templateName, "The templateName can not be null");
		Context ctx = new Context();
		if (map != null) {
		     Iterator itMap = map.entrySet().iterator();
		       while (itMap.hasNext()) {
			  Map.Entry pair = (Map.Entry) itMap.next();
		          ctx.setVariable(pair.getKey().toString(), pair.getValue());
			}
		}
		
		String processedHtml = templateEngine.process(templateName, ctx);
		
		  FileOutputStream os = null;
		  String fileName = UUID.randomUUID().toString();
	        try {
	            final File outputFile = File.createTempFile(fileName, ".pdf");
	            os = new FileOutputStream(outputFile);

	            ITextRenderer renderer = new ITextRenderer();
	            renderer.setDocumentFromString(processedHtml);
	            renderer.layout();
	            renderer.createPDF(os, false);
	            renderer.finishPDF();
//	            logger.info("PDF created successfully");
	            
	            /*File file = new File(path);
				String fileName = file.getName();*/
				FileInputStream inputStream = new FileInputStream(outputFile);
				response.setContentType("application/pdf");
				response.setContentLength((int) outputFile.length());
//				response.setHeader("Content-Disposition", "application;filename=\"" + fileName + "\"");
				response.setHeader("Content-Disposition", "inline;filename=\"" + fileName + "\"");
				FileCopyUtils.copy(inputStream, response.getOutputStream());
	        }
	        finally {
	            if (os != null) {
	                try {
	                    os.close();
	                } catch (IOException e) { /*ignore*/ }
	            }
	        }
	}
}

