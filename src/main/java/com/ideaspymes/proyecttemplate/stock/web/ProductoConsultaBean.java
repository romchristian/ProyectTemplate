/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.stock.web;

import com.ideaspymes.proyecttemplate.configuracion.model.EtiquetaConf;
import com.ideaspymes.proyecttemplate.configuracion.servicio.interfaces.IEtiquetaConfDAO;
import com.ideaspymes.proyecttemplate.generico.AbstractDAO;
import com.ideaspymes.proyecttemplate.generico.ConsultaGenerico;
import com.ideaspymes.proyecttemplate.stock.enums.TipoRegalo;
import com.ideaspymes.proyecttemplate.stock.model.Deposito;
import com.ideaspymes.proyecttemplate.stock.model.Existencia;
import com.ideaspymes.proyecttemplate.stock.model.Familia;
import com.ideaspymes.proyecttemplate.stock.model.Producto;
import com.ideaspymes.proyecttemplate.stock.model.Ubicacion;
import com.ideaspymes.proyecttemplate.stock.servicio.interfaces.IFamiliaDAO;
import com.ideaspymes.proyecttemplate.stock.servicio.interfaces.IProductoDAO;
import com.ideaspymes.proyecttemplate.stock.web.reporte.pojo.CatalogoProductos;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Utilities;
import com.lowagie.text.pdf.Barcode39;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.SortOrder;
import org.primefaces.model.TreeNode;

/**
 *
 * @author Christian
 */
@Named
@ViewScoped
public class ProductoConsultaBean extends ConsultaGenerico<Producto> {

    @EJB
    private IProductoDAO ejb;
    @EJB
    private IEtiquetaConfDAO etiquetaConfDAO;
    @EJB
    private IFamiliaDAO familiaDAO;

    //Filtros
    private String codigo;
    private String nombre;
    private Familia familia;
    private Deposito deposito;
    private Ubicacion ubicacion;
    private Boolean esRegalo;
    private TipoRegalo tipoRegalo;

    private TreeNode rootNode;
    private TreeNode selectedNode;

    @PostConstruct
    public void init() {

        List<Familia> principales = familiaDAO.findSinPadre();

        rootNode = new DefaultTreeNode("Todos", null);
        Familia fa = new Familia();
        fa.setNombre("Todos");
        TreeNode nodeTodos = new DefaultTreeNode(fa, rootNode);
        nodeTodos.setExpanded(true);

        for (Familia f : principales) {
            TreeNode t1 = createTreeFamilia(f, nodeTodos);
        }

    }

    public TreeNode createTreeFamilia(Familia treeObj, TreeNode rootNode) {
        TreeNode newNode = new DefaultTreeNode(treeObj, rootNode);

        List<Familia> childNodes1 = familiaDAO.findHijos(treeObj);

        for (Familia f : childNodes1) {
            TreeNode newNode2 = createTreeFamilia(f, newNode);
        }

        return newNode;
    }

    public Boolean getEsRegalo() {
        return esRegalo;
    }

    public void setEsRegalo(Boolean esRegalo) {
        this.esRegalo = esRegalo;
    }

    public TipoRegalo getTipoRegalo() {
        return tipoRegalo;
    }

    public void setTipoRegalo(TipoRegalo tipoRegalo) {
        this.tipoRegalo = tipoRegalo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public TreeNode getRootNode() {
        return rootNode;
    }

    public void setRootNode(TreeNode rootNode) {
        this.rootNode = rootNode;
    }

    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Familia getFamilia() {
        return familia;
    }

    public void setFamilia(Familia familia) {
        this.familia = familia;
    }

    public Deposito getDeposito() {
        return deposito;
    }

    public void setDeposito(Deposito deposito) {
        this.deposito = deposito;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    @Override
    public Class<Producto> getClazz() {
        return Producto.class;
    }

    @Override
    public AbstractDAO<Producto> getEjb() {
        return ejb;
    }

    @Override
    public String construyeFilters(String sortField, SortOrder sortOrder) {

        StringBuilder consulta = new StringBuilder("select * from producto WHERE estado = 'ACTIVO' ");
        if (getCredencial().getEmpresa() != null) {
            consulta.append(" and empresa_id = ").append(getCredencial().getEmpresa().getId());
        }
        if (codigo != null && codigo.length() > 0) {
            consulta.append(" and upper(codigo) like '%").append(codigo.toUpperCase()).append("%'");
        }

        if (nombre != null && nombre.length() > 0) {
            consulta.append(" and upper(nombre) like '%").append(nombre.toUpperCase()).append("%'");
        }

        if (esRegalo != null && esRegalo) {
            consulta.append(" and esregalo = true");
        }

        if (esRegalo != null && esRegalo && tipoRegalo != null) {
            consulta.append(" and tiporegalo = '").append(tipoRegalo.toString()).append("'");
        }

        if (selectedNode != null && selectedNode.getData() instanceof Familia && ((Familia) selectedNode.getData()).getId() != null) {
            familia = (Familia) selectedNode.getData();
        } else {
            familia = null;
        }

        if (familia != null) {
            consulta.append(" and familia_id in (");
            List<Familia> lista = creaFiltroFamilia(familia, null);
            for (Familia f : lista) {
                consulta.append(f.getId()).append(",");
            }
            consulta.append("0)");
        }

        if (deposito != null && ubicacion == null) {
            consulta.append(" and id in (SELECT distinct p.id  from producto p");
            consulta.append(" left join existencia e on e.producto_id = p.id");
            consulta.append(" where e.deposito_id = ").append(deposito.getId()).append(")");
        } else if (deposito != null && ubicacion != null) {
            consulta.append(" and id in (SELECT distinct p.id  from producto p");
            consulta.append(" left join existencia e on e.producto_id = p.id");
            consulta.append(" where e.deposito_id = ").append(deposito.getId());
            consulta.append(" and e.ubicacion_id = ").append(ubicacion.getId()).append(")");
        }

        if (sortField != null) {
            consulta.append(" ORDER BY  ");
            consulta.append(sortField);
            consulta.append(SortOrder.ASCENDING.equals(sortOrder) ? " ASC " : " DESC ");
        }

        System.out.println("Contruye Consulta: " + consulta.toString());
        return consulta.toString();
    }

    public List<Familia> creaFiltroFamilia(Familia f, List<Familia> lista) {

        if (lista == null) {
            lista = new ArrayList<>();
        }

        List<Familia> childNodes1 = familiaDAO.findHijos(f);

        lista.add(f);
        for (Familia fa : childNodes1) {
            lista = creaFiltroFamilia(fa, lista);
        }

        return lista;
    }

    @Override
    public String construyeCount() {
        StringBuilder consulta = new StringBuilder("select count(*) from producto WHERE estado = 'ACTIVO' ");
        if (getCredencial().getEmpresa() != null) {
            consulta.append(" and empresa_id = ").append(getCredencial().getEmpresa().getId());
        }
        if (codigo != null && codigo.length() > 0) {
            consulta.append(" and upper(codigo) like '%").append(codigo.toUpperCase()).append("%'");
        }

        if (nombre != null && nombre.length() > 0) {
            consulta.append(" and upper(nombre) like '%").append(nombre.toUpperCase()).append("%'");
        }

        if (esRegalo != null && esRegalo) {
            consulta.append(" and esregalo = true");
        }

        if (esRegalo != null && esRegalo && tipoRegalo != null) {
            consulta.append(" and tiporegalo = '").append(tipoRegalo.toString()).append("'");
        }

        if (selectedNode != null && selectedNode.getData() instanceof Familia && ((Familia) selectedNode.getData()).getId() != null) {
            familia = (Familia) selectedNode.getData();
        } else {
            familia = null;
        }

        if (familia != null) {
            consulta.append(" and familia_id in (");
            List<Familia> lista = creaFiltroFamilia(familia, null);
            for (Familia f : lista) {
                consulta.append(f.getId()).append(",");
            }
            consulta.append("0)");
        }

        if (deposito != null && ubicacion == null) {
            consulta.append(" and id in (SELECT distinct p.id  from producto p");
            consulta.append(" left join existencia e on e.producto_id = p.id");
            consulta.append(" where e.deposito_id = ").append(deposito.getId()).append(")");
        } else if (deposito != null && ubicacion != null) {
            consulta.append(" and id in (SELECT distinct p.id  from producto p");
            consulta.append(" left join existencia e on e.producto_id = p.id");
            consulta.append(" where e.deposito_id = ").append(deposito.getId());
            consulta.append(" and e.ubicacion_id = ").append(ubicacion.getId()).append(")");
        }
        return consulta.toString();
    }

    public String createPdf() throws IOException, DocumentException {

        EtiquetaConf conf = etiquetaConfDAO.getEtiquetaConfDefault();

        if (hayParaImprimir() && conf != null) {

            HttpServletResponse response
                    = (HttpServletResponse) FacesContext.getCurrentInstance()
                    .getExternalContext().getResponse();
            response.setContentType("application/x-pdf");
            response.setHeader("Content-Disposition", "attachment; filename=\"etiquetas.pdf\"");

            float ancho = Utilities.millimetersToPoints(conf.getAnchoHoja().floatValue());
            System.out.println("Ancho: " + ancho);
            float largo = Utilities.millimetersToPoints(conf.getLargoHoja().floatValue());
            System.out.println("Alto: " + largo);

            // step 1
            Document document = new Document(new Rectangle(ancho, largo));
            // step 2

            document.setMargins(0f, 0f, 0f, 0f);

            PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
            // step 3
            document.open();

            // step 4
            PdfContentByte cb = writer.getDirectContent();

            for (Producto p : getLista()) {
                if (p.getCantidadEtiquetas() > 0) {

                    PdfPTable table = new PdfPTable(2);
                    table.setWidthPercentage(96);

                    Font fontbold = FontFactory.getFont("Times-Roman", 8, Font.NORMAL);

                    Chunk productTitle = new Chunk("HC", fontbold);

                    Paragraph pTitile = new Paragraph(productTitle);
                    pTitile.setAlignment(Element.ALIGN_LEFT);
                    pTitile.setLeading(6, 0);

                    PdfPCell cellTitle = new PdfPCell();
                    cellTitle.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cellTitle.setVerticalAlignment(Element.ALIGN_TOP);
                    cellTitle.setBorder(Rectangle.NO_BORDER);
                    cellTitle.addElement(pTitile);

                    Font font2 = FontFactory.getFont("Times-Roman", conf.getTamDescripcion().floatValue(), Font.NORMAL);

                    Chunk productName = new Chunk(p.getNombre(), font2);

                    Paragraph pName = new Paragraph(productName);
                    pName.setAlignment(Element.ALIGN_CENTER);
                    //pTitile.setLeading(0, 1);

                    cellTitle.addElement(pName);

                    table.addCell(cellTitle);

                    Barcode39 code39 = new Barcode39();
                    code39.setCode(p.getCodigo());
                    //code39.setCodeType(Barcode.EAN13);
                    code39.setBarHeight(conf.getAltoCodBarra().floatValue());
                    //codeEan.setX(0.7f);
                    code39.setSize(conf.getTamDescripcion().floatValue());
                    //code39.setAltText("HC - " + p.getNombre());

                    PdfPCell cellBarcode = new PdfPCell();
                    cellBarcode.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellBarcode.setBorder(Rectangle.NO_BORDER);
                    cellBarcode.addElement(code39.createImageWithBarcode(cb, null, Color.BLACK));

                    table.addCell(cellBarcode);

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

    @Override
    public String getPath() {
        return "reportes/stock/CatalogoProducto.jasper";
    }

    @Override
    public String getNombreReporte() {
        return "catalogo";
    }

    @Override
    public Collection getDetalles() {

        ArrayList<CatalogoProductos> detalles = new ArrayList<>();

        List<Producto> lista = ejb.findFilterAll(construyeFilters("nombre", SortOrder.ASCENDING));

        for (Producto p : lista) {

            String ubicaciones = "";
            double stock = 0;
            for (Existencia e : ejb.findExistenciasPorProducto(p)) {
                ubicaciones += e.getCantidad() + " " + e.getUnidadMedida().getNombre() + " en " + e.getDeposito().getNombre() + " - " + (e.getUbicacion() == null ? "" : e.getUbicacion().getNombre()) + "\n";
                stock += e.getCantidad();
            }

            detalles.add(new CatalogoProductos(p.getImagen(),
                    p.getNombre(),
                    p.getDescripcion(),
                    ubicaciones,
                    stock,
                    p.getCodigo(),
                    p.getFamilia() != null ? p.getFamilia().getNombre() : "No definido",
                    (p.getEsRegalo() == null ? false : p.getEsRegalo())
            ));
        }

        Comparator<CatalogoProductos> comp = new Comparator<CatalogoProductos>() {
            @Override
            public int compare(CatalogoProductos o1, CatalogoProductos o2) {
                String x1 = o1.getFamilia();
                String x2 = o2.getFamilia();
                int sComp = x1.compareTo(x2);

                if (sComp != 0) {
                    return sComp;
                } else {
                    String y1 = o1.getProducto();
                    String y2 = o2.getProducto();
                    return y1.compareTo(y2);
                }
            }
        };

        Collections.sort(detalles, comp);

        return detalles;
    }

    @Override
    public void cargaParams() {
        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy", new Locale("es", "PY"));

        getParams().put("fecha", date.format(new Date()));
        getParams().put("ubicacion", (ubicacion != null ? ubicacion.getNombre() : "todos"));
        getParams().put("deposito", (deposito != null ? deposito.getNombre() : "todos"));

    }

    @Override
    public Workbook getWorkBook() {
        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("My Sample Excel");
        List<CatalogoProductos> lista = (List<CatalogoProductos>) getDetalles();

        sheet.setDefaultRowHeight((short) (sheet.getDefaultRowHeight() * new Short("6")));

        int i = 0;
        for (CatalogoProductos cp : lista) {

            int indexFila = i + 1;
            int pictureIdx = wb.addPicture(cp.getImagen(), Workbook.PICTURE_TYPE_PNG);
            CreationHelper helper = wb.getCreationHelper();

            //Creates the top-level drawing patriarch.
            Drawing drawing = sheet.createDrawingPatriarch();

            //Create an anchor that is attached to the worksheet
            ClientAnchor anchor = helper.createClientAnchor();
            //set top-left corner for the image
            anchor.setCol1(1);
            anchor.setRow1(indexFila);

            //Creates a picture
            Picture pict = drawing.createPicture(anchor, pictureIdx);
            //Reset the image to the original size
            pict.resize(0.4);

            Row row1 = sheet.createRow(indexFila);
            Cell cellCol1 = row1.createCell(3);
            cellCol1.setCellValue(cp.getProducto());
            
            Cell cellCol2 = row1.createCell(4);
            cellCol2.setCellValue(cp.getCodigo());
            
            
            Cell cellCol3 = row1.createCell(5);
            cellCol3.setCellValue(cp.getDescripcion());
            
            
            Cell cellCol4 = row1.createCell(6);
            cellCol4.setCellValue(cp.isEsRegalo()?"SI":"NO");
            
            Cell cellCol5 = row1.createCell(7);
            cellCol5.setCellValue(cp.getFamilia());
            
            Cell cellCol6 = row1.createCell(8);
            cellCol6.setCellValue(cp.getUbicaciones());
            
            Cell cellCol7 = row1.createCell(9);
            cellCol7.setCellValue(cp.getStock());

            i++;

        }

        return wb;
    }

}
