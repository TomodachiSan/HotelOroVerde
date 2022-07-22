
package Modelo;

import java.sql.Date;

public class vventa {

    private int idventa;
    private Date fecha;
    private int idtrabajador; 
    private String tipo_comprobante;
    private Double monto;
    private Double igv;  
    private Double monto_total;  
       
    public vventa() {
        
    }

    public vventa(int idventa, Date fecha, int idtrabajador, String tipo_comprobante, Double monto, Double igv, Double monto_total) {
        this.idventa = idventa;
        this.fecha = fecha;
        this.idtrabajador = idtrabajador;
        this.tipo_comprobante = tipo_comprobante;
        this.monto = monto;
        this.igv = igv;
        this.monto_total = monto_total;
    }
    
    
    public int getIdventa() {
        return idventa;
    }

    public void setIdventa(int idventa) {
        this.idventa = idventa;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIdtrabajador() {
        return idtrabajador;
    }

    public void setIdtrabajador(int idtrabajador) {
        this.idtrabajador = idtrabajador;
    }
    
    
    public String getTipo_comprobante() {
        return tipo_comprobante;
    }

    public void setTipo_comprobante(String tipo_comprobante) {
        this.tipo_comprobante = tipo_comprobante;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Double getIgv() {
        return igv;
    }

    public void setIgv(Double igv) {
        this.igv = igv;
    }

    public Double getMonto_total() {
        return monto_total;
    }

    public void setMonto_total(Double monto_total) {
        this.monto_total = monto_total;
    }
    
    

    

    

    
    
    
}
