package DAO;

import Modelo.valquiler;
import Modelo.vdetalle_venta;
import Modelo.vventa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class fventa {

    private conexion mysql = new conexion();
    private Connection cn = mysql.conectar();
    private String sSQL = "";
    private String sSQL2 = "";
    public Integer totalregistros;
    public Double totalconsumo;

    public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel modelo;

        String[] titulos = {"ID","idtrabajador", "Trabajador", "Fecha", "Comprobante", "Momto", "IGV","Monto Total"};

        String[] registro = new String[10];

        totalregistros = 0;
        modelo = new DefaultTableModel(null, titulos);

        sSQL = "select v.idventa,"
                + "v.idtrabajador,(select nombre from persona where idpersona=v.idtrabajador)as trabajadorn,"
                + "(select apaterno from persona where idpersona=v.idtrabajador)as trabajadorap,"
                + "v.fecha,"
                + "v.tipo_comprobante,v.monto,v.igv,v.monto_total from venta v where v.idventa like '%" + buscar + "%' order by v.idventa desc limit 0,200";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {
                registro[0] = rs.getString("idventa");
                registro[1] = rs.getString("idtrabajador");
                registro[2] = rs.getString("trabajadorn") + " " + rs.getString("trabajadorap");
                registro[3] = rs.getString("fecha");
                registro[4] = rs.getString("tipo_comprobante");
                registro[5] = rs.getString("monto");
                registro[6] = rs.getString("igv");
                registro[7] = rs.getString("monto_total");
                totalregistros = totalregistros + 1;
                modelo.addRow(registro);

            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }

    }

    public DefaultTableModel mostrarDV(String buscar) {
        DefaultTableModel modelo;

        String[] titulos = {"ID", "Idventa", "Idproducto", "Producto", "Cantidad", "Precio Venta", "Monto"};

        String[] registro = new String[7];

        totalregistros = 0;
        totalconsumo = 0.0;
        modelo = new DefaultTableModel(null, titulos);

        sSQL = "select d.iddetalle_venta,d.idventa,d.idproducto,p.nombre,d.cantidad,d.precio_venta,d.monto "
                + " from detalle_venta d inner join producto p on d.idproducto=p.idproducto"
                + " where d.idventa =" + buscar + " order by d.iddetalle_venta desc";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {
                registro[0] = rs.getString("iddetalle_venta");
                registro[1] = rs.getString("idventa");
                registro[2] = rs.getString("idproducto");
                registro[3] = rs.getString("nombre");
                registro[4] = rs.getString("cantidad");
                registro[5] = rs.getString("precio_venta");
                registro[6] = rs.getString("monto");

                totalregistros = totalregistros + 1;
                totalconsumo = totalconsumo + (rs.getDouble("cantidad") * rs.getDouble("precio_venta"));

                modelo.addRow(registro);

            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }

    }

    public boolean insertar(vventa dts, vdetalle_venta dts2) {
        String fecha, hora;
        fecha = funciones.getFechaActual();
        hora = funciones.getHoraActual();

        sSQL = "insert into venta (fecha,idtrabajador,tipo_comprobante,monto,igv,monto_total)"
                + "values (?,?,?,?,?,?)";

        sSQL2 = "insert into detalle_venta (idventa,idproducto,cantidad,precio_venta,monto)"
                + "values ((select idventa from venta order by idventa desc limit 1),?,?,?,?)";
        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);
            pst.setString(1, fecha);
            pst.setInt(2, dts.getIdtrabajador());
            pst.setString(3, "Boleta");
            pst.setDouble(4, dts.getMonto());
            pst.setDouble(5, dts.getIgv());
            pst.setDouble(6, dts.getMonto_total());

            pst2.setInt(1, dts2.getIdproducto());
            pst2.setDouble(2, dts2.getCantidad());
            pst2.setDouble(3, dts2.getPrecio_venta());
            pst2.setDouble(4, dts2.getMonto());

            int n = pst.executeUpdate();

            if (n != 0) {
                int n2 = pst2.executeUpdate();

                if (n2 != 0) {
                    return true;

                } else {
                    return false;
                }

            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }

    public boolean insertar2(vdetalle_venta dts2) {
        String fecha, hora;
        fecha = funciones.getFechaActual();
        hora = funciones.getHoraActual();

        sSQL = "insert into detalle_venta (idventa,idproducto,cantidad,precio_venta,monto)"
                + "values (?,?,?,?,?)";
        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);

            pst.setInt(1, dts2.getIdventa());
            pst.setInt(2, dts2.getIdproducto());
            pst.setDouble(3, dts2.getCantidad());
            pst.setDouble(4, dts2.getPrecio_venta());
            pst.setDouble(5, dts2.getMonto());

            int n = pst.executeUpdate();

            if (n != 0) {
                return true;

            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }

    public Integer UltimoIdVenta() {

        Integer id = null;
        sSQL = "select idventa from venta order by idventa desc limit 1";

        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    id = rs.getInt(1);
                } else {
                    id = null;
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "ERROR al consultar el id..." + e);
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
        }
        return id;
    }

    public Double SumaMontosDV(Integer idventa) {

        Double id = null;
        sSQL = "select sum(monto) from detalle_venta WHERE idventa = ?;";

        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, idventa);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    id = rs.getDouble(1);
                } else {
                    id = null;
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "ERROR al consultar la suma.." + e);
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
        }
        return id;
    }

    public boolean editarDV(vdetalle_venta dts) {
        sSQL = "update detalle_venta set idproducto=?,cantidad=?,precio_venta=?,monto=?"
                + " where iddetalle_venta=?";

        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, dts.getIdproducto());
            pst.setDouble(2, dts.getCantidad());
            pst.setDouble(3, dts.getPrecio_venta());
            pst.setDouble(4, dts.getMonto());
            pst.setInt(5, dts.getIddetalle_venta());

            int n = pst.executeUpdate();

            if (n != 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }

    /*public boolean editar(vventa dts) {
        sSQL = "update venta set idtrabajador=?,idcliente=?,tipo_comprobante=?,num_comprobante=?"
                + " where idventa=?";

        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, dts.getIdtrabajador());
            pst.setInt(2, dts.getIdcliente());
            pst.setString(3, dts.getTipo_comprobante());
            pst.setString(4, dts.getNum_comprobante());

            pst.setInt(5, dts.getIdventa());

            int n = pst.executeUpdate();

            if (n != 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }*/
    public boolean editarMontos(vventa dts) {
        sSQL = "update venta set monto=?,igv=?, monto_total=?"
                + " where idventa=?";

        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setDouble(1, dts.getMonto());
            pst.setDouble(2, dts.getIgv());
            pst.setDouble(3, dts.getMonto_total());
            pst.setInt(4, dts.getIdventa());

            int n = pst.executeUpdate();

            if (n != 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }

    public boolean eliminarDV(vdetalle_venta dts) {
        sSQL = "delete from detalle_venta where iddetalle_venta=?";

        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);

            pst.setInt(1, dts.getIddetalle_venta());

            int n = pst.executeUpdate();

            if (n != 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }

    public boolean eliminarTodo(Integer id) {
        sSQL = "delete from detalle_venta where idventa=?;";
        sSQL2 = "delete from venta where idventa=?;";
        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);
            pst.setInt(1, id);
            pst2.setInt(1, id);
            int n = pst.executeUpdate();

            if (n != 0) {
                int n2 = pst2.executeUpdate();

                if (n2 != 0) {
                    return true;

                } else {
                    return false;
                }

            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }

    public void DisminuirProds(String buscar) {
        Integer cantidad = 0;
        Integer idproducto = 0;
        fproducto func = new fproducto();
        sSQL = "select p.idproducto,d.cantidad"
                + " from detalle_venta d inner join producto p on d.idproducto=p.idproducto"
                + " where d.idventa =" + buscar + " order by d.iddetalle_venta desc";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {
                idproducto = rs.getInt("idproducto");
                cantidad = rs.getInt("cantidad");
                func.disminuir(idproducto, cantidad);
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
        }

    }

}
