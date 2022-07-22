
package Modelo;

public class vconsumo {
    private int idconsumo;
    private int idalquiler;
    private int idproducto;
    private int cantidad;
    private Double precio_venta;
    private Double monto;

    public vconsumo() {
    }

    public vconsumo(int idconsumo, int idalquiler, int idproducto, int cantidad, Double precio_venta, Double monto) {
        this.idconsumo = idconsumo;
        this.idalquiler = idalquiler;
        this.idproducto = idproducto;
        this.cantidad = cantidad;
        this.precio_venta = precio_venta;
        this.monto = monto;
    }

    public int getIdconsumo() {
        return idconsumo;
    }

    public void setIdconsumo(int idconsumo) {
        this.idconsumo = idconsumo;
    }

    public int getIdalquiler() {
        return idalquiler;
    }

    public void setIdalquiler(int idalquiler) {
        this.idalquiler = idalquiler;
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
