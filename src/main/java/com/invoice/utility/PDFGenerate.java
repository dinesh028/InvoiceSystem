/*package com.invoice.utility;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Currency;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.invoice.model.Invoice;
import com.invoice.model.InvoiceHistory;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


@Service("pdfserviceimpl")
public class PDFGenerate {

	public void generate(Set<InvoiceHistory> invoiceHistorySet,
			Invoice invoice) {

		
		//Member mem = (Member) session.getAttribute("login");
		String img = "D:/Impetus/Impetus Prj/InvoiceSystem/src/main/webapp/images/" + invoice.getClient().getTenant().getEmail()
				+ ".jpg";
		System.out.println("img" + img);
		String fileName = "C:/prj/pdf/Invoice" + invoice.getInvoice_id()
				+ ".pdf";
		try {
			OutputStream file = new FileOutputStream(new File(fileName));
			Document document = new Document();
			PdfWriter.getInstance(document, file);

			// Inserting Image in PDF
			Image image = Image.getInstance(img);
			image.scaleAbsolute(120f, 60f);// image width,height
			image.setAlignment(image.ALIGN_CENTER);

			// Inserting Table in PDF
			PdfPTable table = new PdfPTable(4);

			PdfPCell cell = new PdfPCell(new Paragraph("Item Purchased"));

			cell.setColspan(4);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setPadding(10.0f);
			cell.setBackgroundColor(new BaseColor(140, 221, 8));

			table.addCell(cell);

			table.addCell("Item");
			table.addCell("Quantity");
			table.addCell("Price");
			table.addCell("Amount");
			Iterator<InvoiceHistory> it = invoiceHistorySet.iterator();
			InvoiceHistory ih = null;
			while (it.hasNext()) {
				ih = (InvoiceHistory) it.next();
				table.addCell("" + ih.getItem());
				table.addCell("" + ih.getQuantity());
				table.addCell("" + ih.getPrice());
				table.addCell("" + ih.getAmount());
			}

			table.setSpacingBefore(30.0f); // Space Before table starts, like
											// margin-top in CSS
			table.setSpacingAfter(30.0f); // Space After table starts, like
											// margin-Bottom in CSS

			// Text formating in PDF

			// Now Insert Every Thing Into PDF Document
			document.open();// PDF document opened........

			document.add(image);

			document.add(Chunk.NEWLINE); // Something like in HTML <img
											// src="http://www.java4s.com/wp-includes/images/smilies/icon_smile.gif"
											// alt=":-)" class="wp-smiley">

			Paragraph para1 = new Paragraph("Invoice", FontFactory.getFont(
					FontFactory.HELVETICA, 22, Font.BOLD, new CMYKColor(0, 0,
							0, 255)));
			para1.setAlignment(Paragraph.ALIGN_CENTER);
			document.add(para1);
			Calendar c = Calendar.getInstance();
			
			Member mem = (Member) session.getAttribute("mem");
			String dtFormat = invoice.getUser().getTenant().getDateFormat();
			
			 dtFormat = dtFormat.contains("yy")?dtFormat.replace("yy", "yyyy"):dtFormat.replace("y", "yy");
			
			 c.set(invoice.getDueDate()); 
			DateFormat formatter = new SimpleDateFormat(dtFormat);
			document.add(new Paragraph("Name:- "
					+ invoice.getClient().getName()));
			document.add(new Paragraph("Due Date:- "
					+ formatter.format(invoice.getDueDate())));

			document.add(table);

			DecimalFormat dtime = new DecimalFormat("#.##");
			double i2 = Double.valueOf(dtime.format(16509.46));
			
			
				      

			 document.add(Chunk.NEWLINE); // Something like in HTML <img
												// src="http://www.java4s.com/wp-includes/images/smilies/icon_smile.gif"
												// alt=":-)" class="wp-smiley">
			System.out.println("in " + invoice.getAmount());
			document.add(new Paragraph("Sum:-  "+getCurrencySymbol(invoice.getTenant().getCurrencyFormat())+" "+ invoice.getAmount()));
			document.add(new Paragraph("Tax:- " + invoice.getTax() + " %"));
			document.add(new Paragraph(
					"Net Total:- "+" "+getCurrencySymbol(invoice.getTenant().getCurrencyFormat())
							+ " "+(Double.valueOf(dtime.format((invoice.getAmount() * ((invoice
									.getTax() + 100) / 100)))))));

			document.close();
			file.close();

			System.out.println("Pdf created successfully..");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public  String getCurrencySymbol(String currencyFormat)
	{
		

		String curFormat[] = currencyFormat.split(",");
		Locale locale=new Locale(curFormat[0], curFormat[1]);
        Currency cur = Currency.getInstance(locale);
        System.out.println("Printing Symbol"+cur.getSymbol());
        return cur.getSymbol();
	}
}
*/