package Social;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ElementListener;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import General.PdfData;

public class Generate_pdf {
	public final long leftLimit = 1;
	public final long rightLimit = 1;
	public Generate_pdf()
	{
	
	}
	
	public boolean generatePDF(String path,PdfData pd) throws FileNotFoundException, DocumentException {
		boolean state = false;
		
		System.out.println("Hii"+pd.getRank());
		System.out.println(pd.getRm1());
		System.out.println(pd.getMarks());
//		Document document =new Document(PageSize.A4);
//		try {
//			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(path));
//			document.open();
//			PdfPTable table=null;
//			PdfPCell cell=null;
//			table = new PdfPTable(1);
//			cell = new PdfPCell();
//			Font bold = new Font(FontFamily.TIMES_ROMAN,18,Font.BOLD);
//			Chunk chunk = new Chunk("Test", bold);
//			cell.setColspan(1);
//			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//			cell.addElement(chunk);
//			table = new PdfPTable(1);
//			table.setWidthPercentage(100);
//			table.addCell(cell);
//			document.add(table);
//			document.close();
//			state=true;
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (DocumentException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(path));
        document.open();
        Font font = new Font(FontFamily.HELVETICA, 18, Font.BOLD);
        Font italic = new Font(FontFamily.TIMES_ROMAN,12,Font.ITALIC);
        Font normal = new Font(FontFamily.TIMES_ROMAN,12,Font.NORMAL);        
        
        
        String collegeName = "College of Engineering, Pune\n\n";
        String collegeAddress = "Wellesley Rd, Shivajinagar, Pune, Maharashtra 411005\n"+
        				"Application Form for Addmission to Hostel";
        
        Chunk para1 = new Chunk(collegeName, font);
        Chunk para2 = new Chunk(collegeAddress, italic);
        Paragraph comb=new Paragraph(); 
        comb.add(new Chunk(para1));
        comb.add(new Chunk(para2)); 
        
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(110);
        
        PdfPCell cell = new PdfPCell(comb);
        cell.setColspan(4);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        

        long generatedLong = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
        String applicationId = "Application ID : "+pd.getAppid();
        Chunk c = new Chunk(applicationId,normal);
        Paragraph para = new Paragraph(c);
        PdfPCell cell1 = new PdfPCell(para);
        cell1.setColspan(4);
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell1);
        
        
        cell = new PdfPCell(new Phrase("Personal Details",font));
        cell.setColspan(4);
        table.addCell(cell);
   
        table.addCell("MIS NO : ");
        table.addCell(pd.getAppid()+"");
        table.addCell("Candidate's Name : ");
        table.addCell(pd.getName());
        table.addCell("Candidate's Rank : ");
        table.addCell(pd.getRank()+"");
        table.addCell("Candidate's Room No : ");
        table.addCell(pd.getRoomid()+"");
        table.addCell("Roommate's MIS NO : ");
        table.addCell(pd.getRm1()+"");
        table.addCell("Roommate's MIS NO : ");
        table.addCell(pd.getRm2()+"");
        table.addCell("Roommate's MIS NO : ");
        table.addCell(pd.getRm3()+"");
        table.addCell("Roommate's MIS NO : ");
        table.addCell(pd.getRm4()+"");

        cell = new PdfPCell(new Phrase("Qualification Details",font));
        cell.setColspan(4);
        table.addCell(cell);
        document.add(table);
        
        PdfPTable table2  = new PdfPTable(3);
        table2.setWidthPercentage(110);
        table2.addCell("Qualification");
        table2.addCell("Marks Obtained");
        table2.addCell("Marks OutOf");
        cell = new PdfPCell(new Phrase("CET"));
        cell.setColspan(1);
        table2.addCell(cell);
        cell = new PdfPCell(new Phrase(pd.getMarks()+""));
        cell.setColspan(1);
        table2.addCell(cell);
        cell = new PdfPCell(new Phrase("200"));
        cell.setColspan(1);
        table2.addCell(cell);
//        cell = new PdfPCell(new Phrase("100/200"));
//        cell.setColspan(1);
//        table2.addCell(cell);
        document.add(table2);
        
        
        table2 = new PdfPTable(4);
        table2.setWidthPercentage(110);
        cell = new PdfPCell(new Phrase("List of Documents needed : ",font));
        cell.setColspan(4);
        table2.addCell(cell);
        cell = new PdfPCell();
        cell.setColspan(1);
        cell.addElement(new Phrase("1."));
        table2.addCell(cell);
        cell = new PdfPCell();
        cell.setColspan(3);
        cell.addElement(new Phrase("Addmission Letter of College"));
        table2.addCell(cell);
        cell = new PdfPCell();
        cell.setColspan(1);
        cell.addElement(new Phrase("2."));
        table2.addCell(cell);
        cell = new PdfPCell();
        cell.setColspan(3);
        cell.addElement(new Phrase("Score Card of previous year"));
        table2.addCell(cell);
        cell = new PdfPCell();
        cell.setColspan(1);
        cell.addElement(new Phrase("3."));
        table2.addCell(cell);
        cell = new PdfPCell();
        cell.setColspan(3);
        cell.addElement(new Phrase("Copy of Leaving Certificate"));
        table2.addCell(cell);
        cell = new PdfPCell(new Phrase("Rules and Regulation",font));
        cell.setColspan(4);
        table2.addCell(cell);
        cell = new PdfPCell(new Paragraph("Ragging is strictly prohibited. The Maharashtra Legislative Council has passed a bill (L C Bill Number ix of 1999) to prohibit ragging in educational institutions in the State of Maharashtra on 7th April 1999. As per this bill - \n" + 
        		"Ragging within or outside of any educational institution is strictly prohibited. \n" + 
        		"Whosoever directly or indirectly commits, participates in, abets or progagates ragging within or outside any educational institution shall on conviction, be punished, as per the rules. \n" + 
        		"Any student convicted of an offence of ragging shall be dismissed from the educational institution and such student shall not be admitted to any other educational institution for a period of five years from the date of order of such dismissal."));
        cell.setColspan(4);
        table2.addCell(cell);
        document.add(table2);
        document.close();
		return true;
	}
}
