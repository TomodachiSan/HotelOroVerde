package DAO;

import Modelo.vhabitacion;
import Modelo.vpago;
import Modelo.vproducto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class fpago {

    private conexion mysql = new conexion();
    private Connection cn = mysql.conectar();
    private String sSQL = "";
    public Integer totalregistros;

    public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel modelo;

        String[] titulos = {"ID", "Idalquiler", "Comprobante", "Pago Inicial", "Igv", "Total", "Fecha Pago", "Hora Pago"};

        String[] registro = new String[8];

        totalregistros = 0;
        modelo = new DefaultTableModel(null, titulos);

        sSQL = "SELECT idpago,idalquiler,tipo_comprobante,pago_inicial,"
                + "igv,total_pago,fecha_pago,hora_pago"
                + " from pago where idalquiler="
                + buscar + " order by idpago desc";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {
                registro[0] = rs.getString("idpago");
                registro[1] = rs.getString("idalquiler");
                registro[2] = rs.getString("tipo_comprobante");
                registro[3] = rs.getString("pago_inicial");
                registro[4] = rs.getString("igv");
                registro[5] = rs.getString("total_pago");
                registro[6] = rs.getString("fecha_pago");
                registro[7] = rs.getString("hora_pago");

                totalregistros = totalregistros + 1;
                modelo.addRow(registro);

            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }

    }

    public boolean insertar(vpago dts) {
        String fecha, hora;
        fecha = funciones.getFechaActual();
        hora = funciones.getHoraActual();

        sSQL = "insert into pago (idalquiler,tipo_comprobante,pago_inicial,igv,total_pago,fecha_pago,hora_pago)"
                + "values (?,?,?,?,?,?,?)";
        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, dts.getIdalquiler());
            pst.setString(2, "Boleta");
            pst.setDouble(3, dts.getPago_inicial());
            pst.setDouble(4, dts.getIgv());
            pst.setDouble(5, dts.getTotal_pago());
            pst.setString(6, fecha);
            pst.setString(7, hora);

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

    public boolean editar(vpago dts) {
        sSQL = "update pago set idalquiler=?,tipo_comprobante=?,pago_inicial=?,igv=?,total_pago=?"
                + " where idpago=?";

        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, dts.getIdalquiler());
            pst.setString(2, dts.getTipo_comprobante());
            pst.setDouble(3, dts.getPago_inicial());
            pst.setDouble(4, dts.getIgv());
            pst.setDouble(5, dts.getTotal_pago());

            pst.setInt(6, dts.getIdpago());

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

    public boolean eliminar(vpago dts) {
        sSQL = "delete from pago where idpago=?";

        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);

            pst.setInt(1, dts.getIdpago());

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

    public Integer UltimoIdPago() {

        Integer id = null;
        sSQL = "select idpago from pago order by idpago desc limit 1";

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

    public Integer CantidadPago(int idalquiler) {

        Integer id = 0;
        sSQL = "SELECT COUNT(idpago) FROM pago where idalquiler = ?";

        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, idalquiler);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    id = rs.getInt(1);
                } else {
                    id = 0;
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "ERROR al consultar el id..." + e);
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
        }
        return id;
    }

    public Integer IdPago(int idalquiler) {

        Integer id = 0;
        sSQL = "SELECT idpago FROM pago where idalquiler = ?";

        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, idalquiler);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    id = rs.getInt(1);
                } else {
                    id = 0;
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "ERROR al consultar el id..." + e);
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
        }
        return id;
    }

    public Integer CountConsumos(int idpago) {

        Integer id = 0;
        sSQL = "SELECT COUNT(idconsumo) FROM consumo c INNER JOIN alquiler a"
                + " ON c.idalquiler = a.idalquiler INNER JOIN pago p ON "
                + "a.idalquiler = p.idalquiler WHERE idpago = ?";

        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, idpago);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    id = rs.getInt(1);
                } else {
                    id = 0;
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "ERROR al consultar el id..." + e);
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
        }
        return id;
    }

}
