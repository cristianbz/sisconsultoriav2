/**
@autor proamazonia [Christian Báez]  4 may. 2021

**/
package ec.gob.ambiente.sigma.web.sis.utils;

import java.net.MalformedURLException;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.io.IOException;

public class HeaderFooterPageEvent extends PdfPageEventHelper{
	String valorPieUno;
	String valorPieDos;
	String tipoPie;
	public PdfPTable footer;
	  
	  public HeaderFooterPageEvent(PdfPTable footer, String tipoPie) {
          this.footer = footer;
          this.tipoPie = tipoPie;
      }
	  public HeaderFooterPageEvent(String tipoPie) {
          this.tipoPie = tipoPie;
      }
      /**
	 * 
	 */
	public HeaderFooterPageEvent() {
		// TODO Auto-generated constructor stub
	}
	public void onEndPage(PdfWriter writer, Document document) {
		addHeader(writer);
		addFooter(writer);
      }
//	public void onStartPage(PdfWriter writer, Document document) {
//        writer.addPageDictEntry(PdfName.ROTATE, PdfPage.LANDSCAPE);
//    }	
	 private void addFooter(PdfWriter writer){
			ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
			try{						
		        	PdfPTable footer= new PdfPTable(2);
		        	footer.setWidths(new int[]{24, 14});
		        	footer.setTotalWidth(750);
		        	footer.setLockedWidth(true);
		            footer.getDefaultCell().setFixedHeight(70);
		            footer.getDefaultCell().setBorder(Rectangle.TOP);
		            footer.getDefaultCell().setBorderColor(BaseColor.LIGHT_GRAY);
		        	String logotipoURL = ctx.getRealPath("/") + "/resources/images/maate_2023_izquierda.jpeg";
		        	String logotipoMAE = ctx.getRealPath("/") + "/resources/images/maate_2023_derecha.jpeg";
		        	Image logo = Image.getInstance(logotipoURL);
		        	Image logoMAE = Image.getInstance(logotipoMAE);
		        	logo.setAlignment(Element.ALIGN_CENTER);
		        	logoMAE.setAlignment(Element.ALIGN_CENTER);
		        	
		        	PdfPCell cellImage = new PdfPCell(logo,true);
		        	cellImage.setBorder(Rectangle.NO_BORDER);
					cellImage.setFixedHeight(80);
					cellImage.setPadding(5f);
					cellImage.setHorizontalAlignment(Element.ALIGN_LEFT);
					cellImage.setVerticalAlignment(Element.ALIGN_MIDDLE);				
					cellImage.addElement(logo);
					
					PdfPCell cellImageMAE = new PdfPCell(logoMAE,true);
		        	cellImageMAE.setBorder(Rectangle.NO_BORDER);
					cellImageMAE.setFixedHeight(20);
					cellImageMAE.setPadding(3f);
					cellImageMAE.setHorizontalAlignment(Element.ALIGN_CENTER);
					cellImageMAE.setVerticalAlignment(Element.ALIGN_MIDDLE);				
					cellImageMAE.addElement(logo);
					
					
					footer.addCell(cellImage);
					footer.addCell(cellImageMAE);

	        	
	        	footer.writeSelectedRows(0, -1, 34, 85, writer.getDirectContent());	        
			}catch(DocumentException de) {
	            throw new ExceptionConverter(de);
	        } catch (MalformedURLException e) {
	            throw new ExceptionConverter(e);
	        } catch (IOException e) {
	            throw new ExceptionConverter(e);
	        } catch (java.io.IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 }
	 private void addHeader(PdfWriter writer){
			ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
			try{			
	        	PdfPTable footer= new PdfPTable(2);
	        	footer.setWidths(new int[]{24, 14});
	        	footer.setTotalWidth(750);
	        	footer.setLockedWidth(true);
	            footer.getDefaultCell().setFixedHeight(70);
	            footer.getDefaultCell().setBorder(Rectangle.TOP);
	            footer.getDefaultCell().setBorderColor(BaseColor.LIGHT_GRAY);
	        	String logotipoURL = ctx.getRealPath("/") + "/resources/images/escudoE2023.png";
	        	String logotipoMAE = ctx.getRealPath("/") + "/resources/images/mae.png";
	        	Image logo = Image.getInstance(logotipoURL);
	        	Image logoMAE = Image.getInstance(logotipoMAE);
	        	logo.setAlignment(Element.ALIGN_CENTER);
	        	logoMAE.setAlignment(Element.ALIGN_CENTER);
	        	
	        	PdfPCell cellImage = new PdfPCell(logo,true);
	        	cellImage.setBorder(Rectangle.NO_BORDER);
				cellImage.setFixedHeight(80);
				cellImage.setPadding(5f);
				cellImage.setHorizontalAlignment(Element.ALIGN_LEFT);
				cellImage.setVerticalAlignment(Element.ALIGN_MIDDLE);				
				cellImage.addElement(logo);
				
				PdfPCell cellImageMAE = new PdfPCell(logoMAE,true);
	        	cellImageMAE.setBorder(Rectangle.NO_BORDER);
				cellImageMAE.setFixedHeight(20);
				cellImageMAE.setPadding(3f);
				cellImageMAE.setHorizontalAlignment(Element.ALIGN_CENTER);
				cellImageMAE.setVerticalAlignment(Element.ALIGN_MIDDLE);				
				cellImageMAE.addElement(logo);
				
				
				footer.addCell(cellImage);
				footer.addCell(cellImageMAE);
//	        	footer.addCell(new Phrase(String.format("Pag %d ", writer.getPageNumber()), new Font(Font.FontFamily.HELVETICA, 8)));

	        	
	        	footer.writeSelectedRows(0, -1, 34, 592, writer.getDirectContent());	        
			}catch(DocumentException de) {
	            throw new ExceptionConverter(de);
	        } catch (MalformedURLException e) {
	            throw new ExceptionConverter(e);
	        } catch (IOException e) {
	            throw new ExceptionConverter(e);
	        } catch (java.io.IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 }
}