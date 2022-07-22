
package Modelo;

public class vdetalle_venta {
    private int iddetalle_venta;
    private int idventa;
    private int idproducto;
    private int cantidad;
    private Double precio_venta;
    private Double monto;

    public vdetalle_venta(int iddetalle_venta, int idventa, int idproducto, int cantidad, Double precio_venta, Double monto) {
        this.iddetalle_venta = iddetalle_venta;
        this.idventa = idventa;
        this.idproducto = idproducto;
        this.cantidad = cantidad;
        this.precio_venta = precio_venta;
        this.monto = monto;
    }
    
    public vdetalle_venta() {
        
    }

    public int getIddetalle_venta() {
        return iddetalle_venta;
    }

    public void setIddetalle_venta(int iddetalle_venta) {
        this.iddetalle_venta = iddetalle_venta;
    }

    public int getIdventa() {
        return idventa;
    }

    public void setIdventa(int idventa) {
        this.idventa = idventa;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(Double precio_venta) {
        this.precio_venta = precio_venta;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }
    
    
    
    

    
    
    
}
