/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control.controlador.util;

import java.util.HashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.primefaces.model.DefaultStreamedContent;
import com.control.dao.VentasFacade;
import com.control.dto.ReporteFactura;
import com.control.dto.ReporteFacturaPar;
import java.io.File;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Andres
 */
@ManagedBean
@ViewScoped
public class ReportController {
    @EJB
    VentasFacade ventasFacade;
    private String factura;
    private String mesero;
    private String cliente;
    private String forma_pago;
    private String fecha;
    private String subtotal;
    private String total;
    private String iva;
    private String rutaJasper;

    /**
     * Creates a new instance of ReportController
     */
    public ReportController() {
    }
    
    @PostConstruct
    public void iniciar(){
        ServletContext servContx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        rutaJasper = (String) servContx.getRealPath("/");
    }
    /**
     *
     */
    public void setReporte() throws JRException{
        int codigo=5;
        System.out.println("cod"+codigo);
        ReporteFacturaPar parametros = new ReporteFacturaPar();
        List<ReporteFactura> detalles = ventasFacade.facturaConsulta(codigo);
        parametros = ventasFacade.facturaConsultaPar(codigo).get(0);
        Map parameters = new HashMap();
        parameters.put("factura", parametros.getFactura());
        parameters.put("mesero", parametros.getMesero());
        parameters.put("cliente", parametros.getCliente());
        parameters.put("forma_pago", parametros.getForma_pago());
        parameters.put("fecha", parametros.getFecha());
        parameters.put("subtotal", parametros.getSubtotal());
        parameters.put("total", parametros.getTotal());
        parameters.put("iva", parametros.getIva());
        JRBeanCollectionDataSource lista = new JRBeanCollectionDataSource(detalles);
        File fichero = new File(rutaJasper+"resources/reportes/factura.jasper");
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(fichero);
        JasperPrint print = JasperFillManager.fillReport(jasperReport,parameters, lista);
        byte[] bytes = JasperExportManager.exportReportToPdf(print);
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) context
                .getExternalContext().getResponse();

        response.addHeader("Content-disposition",
                "attachment;filename=reporte.pdf");
        response.setContentLength(bytes.length);
        try {
            response.getOutputStream().write(bytes);
            response.setContentType("application/pdf");
            context.responseComplete();
        } catch (Exception e) {
        }
    }
    public void facturar(){
        
    }
}
