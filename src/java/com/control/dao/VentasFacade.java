/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control.dao;

import com.control.entidad.Ventas;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.control.dto.ReporteFactura;
import com.control.dto.ReporteFacturaPar;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Asus
 */
@Stateless
public class VentasFacade extends AbstractFacade<Ventas> {

    @PersistenceContext(unitName = "ControlPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VentasFacade() {
        super(Ventas.class);
    }

    public List<ReporteFactura> facturaConsulta(int codigo) {
        try {
            System.out.println("funcion");
            Query query = getEntityManager().createNativeQuery("select p.nombre,vd.iva,vd.cantidad,vd.descuento"
                    + " from venta_detalle vd,producto p where venta = ? and p.id = vd.producto");
            query.setParameter(1, codigo);
            List<ReporteFactura> reporteFactura = new ArrayList<ReporteFactura>();
            List<Object[]> consulta = query.getResultList();
            System.out.println("paso0:" + consulta.size());
            for (Object[] registro : consulta) {
                ReporteFactura r = new ReporteFactura();
                r.setProducto(registro[0].toString());
                r.setIva(registro[1].toString());
                r.setCantidad(registro[2].toString());
                r.setDcto(registro[3].toString());
                reporteFactura.add(r);
                System.out.println(r.getProducto());
            }
            return reporteFactura;
        } catch (Exception e) {
            System.out.println("error:" + e.toString());
        }
        return null;
    }

    public List<ReporteFacturaPar> facturaConsultaPar(int codigo) {
        try {
            Query query = getEntityManager().createNativeQuery("select factura,fecha,cliente,mesero,subtotal,total,iva,modo_pago"
                    + " from ventas where id_venta = ?");
            query.setParameter(1, codigo);
            List<ReporteFacturaPar>listaR=new  ArrayList<ReporteFacturaPar>();
            List<Object[]> consulta1 = query.getResultList();
            for (Object[]consulta:consulta1 ) {
                ReporteFacturaPar reporteFacturaPar = new ReporteFacturaPar();
                System.out.println("paso1:" + consulta.length);
                reporteFacturaPar.setFactura(consulta[0].toString());
                reporteFacturaPar.setFecha(consulta[1].toString());
                reporteFacturaPar.setCliente(consulta[0].toString());
                reporteFacturaPar.setMesero(consulta[0].toString());
                reporteFacturaPar.setSubtotal(consulta[0].toString());
                reporteFacturaPar.setTotal(consulta[0].toString());
                reporteFacturaPar.setIva(consulta[0].toString());
                listaR.add(reporteFacturaPar);
                System.out.println(reporteFacturaPar.getFactura());
            }
        

            return listaR;
        } catch (Exception e) {
            System.out.println("error:" + e.toString());
        }
        return null;
    }
    
    public List<Ventas> findByRango(Date fechaIng,Date fechaSal){
        try{
            Query query = getEntityManager().createNamedQuery("Ventas.finByRango");
            query.setParameter("fechaIng",fechaIng);
            query.setParameter("fechaSal", fechaSal);
            return query.getResultList();
        }catch(Exception e){}
        return null;
        
    }
}
