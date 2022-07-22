
package Modelo;

import java.sql.Date;

public class valquiler {
    private int idalquiler;
    private int idhabitacion;
    private int idcliente;
    private int idtrabajador;
    private Date fecha_ingresa;
    private String hora_ingresa;
    private Date fecha_salida;
    private String hora_salida;
    private Double costo_Dia;
    private Double costo_alojamiento;

    public valquiler() {
    }

    public valquiler(int idalquiler, int idhabitacion, int idcliente, int idtrabajador, Date fecha_ingresa,String hora_ingresa, Date fecha_salida,String hora_salida,Double costo_Dia, Double costo_alojamiento) {
        this.idalquiler = idalquiler;
        this.idhabitacion = idhabitacion;
        this.idcliente = idcliente;
        this.idtrabajador = idtrabajador;
        this.fecha_ingresa = fecha_ingresa;
        this.hora_ingresa=hora_ingresa;
        this.fecha_salida = fecha_salida;
        this.hora_salida=hora_salida;
        this.costo_Dia=costo_Dia;
        this.costo_alojamiento = costo_alojamiento;
    }

    public int getIdalquiler() {
        return idalquiler;
    }

    public void setIdalquiler(int idalquiler) {
        this.idalquiler = idalquiler;
    }

    public int getIdhabitacion() {
        return idhabitacion;
    }

    public void setIdhabitacion(int idhabitacion) {
        this.idhabitacion = idhabitacion;
    }

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public int getIdtrabajador() {
        return idtrabajador;
    }

    public void setIdtrabajador(int idtrabajador) {
        this.idtrabajador = idtrabajador;
    }

    public Date getFecha_ingresa() {
        return fecha_ingresa;
    }

    public void setFecha_ingresa(Date fecha_ingresa) {
        this.fecha_ingresa = fecha_ingresa;
    }
    public String getHora_ingresa() {
        return hora_ingresa;
    }

    public void setHora_ingresa(String hora_ingresa) {
        this.hora_ingresa = hora_ingresa;
    }
    public Date getFecha_salida() {
        return fecha_salida;
    }

    public void setFecha_salida(Date fecha_salida) {
        this.fecha_salida = fecha_salida;
    }
    public String getHora_salida() {
        return hora_salida;
    }

    public void setHora_salida(String hora_salida) {
        this.hora_salida = hora_salida;
    }

    public Double getCosto_Dia() {
        return costo_Dia;
    }

    public void setCosto_Dia(Double costo_Dia) {
        this.costo_Dia = costo_Dia;
    }
    

    public Double getCosto_alojamiento() {
        return costo_alojamiento;
    }

    public void setCosto_alojamiento(Double costo_alojamiento) {
        this.costo_alojamiento = costo_alojamiento;
    }

    
    
    
}
