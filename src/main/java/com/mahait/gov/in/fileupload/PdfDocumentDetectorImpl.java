package com.mahait.gov.in.fileupload;
import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itextpdf.text.pdf.PdfArray;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfReader;

/* Added by Sudhir */
public class PdfDocumentDetectorImpl implements DocumentDetector {

	private static final Logger LOG = LoggerFactory.getLogger(PdfDocumentDetectorImpl.class);

	@Override
	public boolean isSafe(File f) {
		boolean safeState = false;
		try {
			if ((f != null) && f.exists()) {
				PdfReader reader = new PdfReader(f.getAbsolutePath());
				String jsCode = reader.getJavaScript();
				if (jsCode == null) {
					PdfDictionary root = reader.getCatalog();
					PdfDictionary names = root.getAsDict(PdfName.NAMES);
					PdfArray namesArray = null;
					if (names != null) {
						PdfDictionary embeddedFiles = names.getAsDict(PdfName.EMBEDDEDFILES);
						namesArray = embeddedFiles.getAsArray(PdfName.NAMES);
					}
					safeState = ((namesArray == null) || namesArray.isEmpty());
				}
			}
		} catch (Exception e) {
			safeState = false;
			LOG.warn("Error during Pdf file analysis !", e);
		}
		return safeState;
	}

}