
package Modelo;

import java.sql.Date;


public class vpago {
    private int idpago;
    private int idalquiler;
    private String tipo_comprobante;
    private Double igv;
    private Double total_pago;
    private Date fecha_pago;
    private String hora_pago;
    private Double pago_inicial;
    
    public vpago() {
    }

    public vpago(int idpago, int idalquiler, String tipo_comprobante, Double igv, Double total_pago, Date fecha_pago, String hora_pago, Double pago_inicial) {
        this.idpago = idpago;
        this.idalquiler = idalquiler;
        this.tipo_comprobante = tipo_comprobante;
        this.igv = igv;
        this.total_pago = total_pago;
        this.fecha_pago = fecha_pago;
        this.hora_pago = hora_pago;
        this.pago_inicial = pago_inicial;
    }

    public int getIdpago() {
        return idpago;
    }

    public void setIdpago(int idpago) {
        this.idpago = idpago;
    }

    public int getIdalquiler() {
        return idalquiler;
    }

    public void setIdalquiler(int idalquiler) {
        this.idalquiler = idalquiler;
    }

    public String getTipo_comprobante() {
        return tipo_comprobante;
    }

    public void setTipo_comprobante(String tipo_comprobante) {
        this.tipo_comprobante = tipo_comprobante;
    }

    public Double getIgv() {
        return igv;
    }

    public void setIgv(Double igv) {
        this.igv = igv;
    }

    public Double getTotal_pago() {
        return total_pago;
    }

    public void setTotal_pago(Double total_pago) {
        this.total_pago = total_pago;
    }

    public Date getFecha_pago() {
        return fecha_pago;
    }

    public void setFecha_pago(Date fecha_pago) {
        this.fecha_pago = fecha_pago;
    }

    public String getHora_pago() {
        return hora_pago;
    }

    public void setHora_pago(String hora_pago) {
        this.hora_pago = hora_pago;
    }

    public Double getPago_inicial() {
        return pago_inicial;
    }

    public void setPago_inicial(Double pago_inicial) {
        this.pago_inicial = pago_inicial;
    }
    
    

    
    
    
    
}
