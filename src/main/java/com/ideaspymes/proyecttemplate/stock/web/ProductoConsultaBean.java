/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.web;

import com.ideaspymes.proyecttemplate.generico.AbstractDAO;
import com.ideaspymes.proyecttemplate.generico.ConsultaGenerico;
import com.ideaspymes.proyecttemplate.stock.model.Producto;
import com.ideaspymes.proyecttemplate.stock.servicio.interfaces.IProductoDAO;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.Barcode;
import com.lowagie.text.pdf.BarcodeEAN;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Christian
 */
@Named
@ViewScoped
public class ProductoConsultaBean extends ConsultaGenerico<Producto> {

    @EJB
    private IProductoDAO ejb;

    @Override
    public Class<Producto> getClazz() {
        return Producto.class;
    }

    @Override
    public AbstractDAO<Producto> getEjb() {
        return ejb;
    }

    public String createPdf() throws IOException, DocumentException {

        if (hayParaImprimir()) {

            HttpServletResponse response
                    = (HttpServletResponse) FacesContext.getCurrentInstance()
                    .getExternalContext().getResponse();
            response.setContentType("application/x-pdf");
            response.setHeader("Content-Disposition", "attachment; filename=\"etiquetas.pdf\"");

            // step 1
            Document document = new Document(new Rectangle(86, 35));
            // step 2

            document.setMargins(0f, 0f, 0f, 0f);

            PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
            // step 3
            document.open();

            // step 4
            PdfContentByte cb = writer.getDirectContent();

            for (Producto p : getLista()) {
                if (p.getCantidadEtiquetas() > 0) {

                    BarcodeEAN codeEAN = new BarcodeEAN();
                    codeEAN.setCode(p.getCodigo());
                    codeEAN.setCodeType(Barcode.EAN13);
                    codeEAN.setBarHeight(10f);
                    codeEAN.setX(0.7f);
                    codeEAN.setSize(4f);

                    NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("es", "py"));
                    Font fontbold = FontFactory.getFont("Times-Roman", 5, Font.NORMAL);
                    Chunk productTitle = new Chunk("HC - "+p.getNombre(), fontbold);

                    // EAN 13
                    Paragraph pTitile = new Paragraph(productTitle);
                    pTitile.setAlignment(Element.ALIGN_CENTER);
                    pTitile.setLeading(0, 1);

                    PdfPTable table = new PdfPTable(1);

                    table.setWidthPercentage(96);
                    PdfPCell cell = new PdfPCell();
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorder(Rectangle.NO_BORDER);
                    cell.addElement(codeEAN.createImageWithBarcode(cb, null, Color.BLACK));

                    table.addCell(cell);

                    PdfPCell cell2 = new PdfPCell();
                    cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell2.setBorder(Rectangle.NO_BORDER);
                    cell2.addElement(pTitile);

                    table.addCell(cell2);

                    for (int i = 0; i < p.getCantidadEtiquetas(); i++) {
                        document.add(table);
                        document.newPage();
                    }
                }
            }

            // step 5
            document.close();

            response.getOutputStream().flush();
            response.getOutputStream().close();
            FacesContext.getCurrentInstance().responseComplete();
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No hay nada que imprimir", ""));
        }
        return null;
    }

    private boolean hayParaImprimir() {
        boolean R = false;
        for (Producto p : getLista()) {
            if (p.getCantidadEtiquetas() > 0) {
                R = true;
                break;
            }
        }
        return R;
    }
}
