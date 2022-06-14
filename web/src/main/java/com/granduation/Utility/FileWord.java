package com.granduation.Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class FileWord {
	public static String uploadDir =System.getProperty("user.dir") + "/src/main/resources/static/fileUploads/";

	public static String WrirteFile(String namefile, String content) throws IOException{
		new File(uploadDir).mkdir();
		XWPFDocument document = new XWPFDocument();
	    // Create new Paragraph
	    XWPFParagraph paragraph1 = document.createParagraph();
	    XWPFRun run = paragraph1.createRun();
	    run.setText(content);
	    
	    // Write the Document in file system
	    FileOutputStream out = new FileOutputStream(new File(uploadDir + namefile +".docx"));
	    document.write(out);
	    out.close();
	    document.close();
		return "Uploaded";
	}
	
	public static String ReadFile(String namefile) throws IOException {
		XWPFWordExtractor wordExtractor = null;
		try {
		      FileInputStream fis = new FileInputStream(uploadDir + namefile +".docx");
		      XWPFDocument document = new XWPFDocument(OPCPackage.open(fis));
		      List<XWPFParagraph> paragraphList = document.getParagraphs();
		      for (XWPFParagraph paragraph : paragraphList) {
		        System.out.println(paragraph.getText());
		      }
		      wordExtractor = new XWPFWordExtractor(document);
		      wordExtractor.close();
		      document.close();
		    } catch (Exception ex) {
		      ex.printStackTrace();
		    }
		return wordExtractor.getText();
	}
}
