package DAO;

import Modelo.vcliente;
import Modelo.vproducto;
import Modelo.vtrabajador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class flogin {

    private conexion mysql = new conexion();
    private Connection cn = mysql.conectar();
    private String sSQL = "";
    private String sSQL2 = "";
    public Integer totalregistros;

    public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel modelo;

        String[] titulos = {"ID", "Sueldo","Acceso","Login","Clave","Estado"};

        String[] registro = new String[6];

        totalregistros = 0;
        modelo = new DefaultTableModel(null, titulos);

        sSQL = "select idpersona,sueldo,acceso,login,password,estado"
                + " from trabajador"
                + " where idpersona like '%"
                + buscar + "%' AND sueldo!=0 order by idpersona desc";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {
                registro[0] = rs.getString("idpersona");
                registro[1] = rs.getString("sueldo");
                registro[2] = rs.getString("acceso");
                registro[3] = rs.getString("login");
                registro[4] = rs.getString("password");
                registro[5] = rs.getString("estado");

                totalregistros = totalregistros + 1;
                modelo.addRow(registro);

            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }

    }
    
    public String mostrarContraseña(Integer id) {
        String contra = null;
        sSQL = "select password "
                + " from trabajador"
                + " where idpersona = "
                + id;

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {
                contra = rs.getString("password");
            }
            return contra;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }

    }

    public boolean insertar(vtrabajador dts) {
        sSQL = "insert into trabajador (idpersona,sueldo,acceso,login,password,estado)"
                + "values (?,?,?,?,?,?)";
        
        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);

            pst.setInt(1, dts.getIdpersona());
            pst.setDouble(2, dts.getSueldo());
            pst.setString(3, dts.getAcceso());
            pst.setString(4, dts.getLogin());
            pst.setString(5, dts.getPassword());
            pst.setString(6, dts.getEstado());

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

    public boolean editar(vtrabajador dts) {
        sSQL = "update trabajador set sueldo=?,acceso=?,login=?,password=?,estado=?"
                + " where idpersona=?";
        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);

            pst.setDouble(1, dts.getSueldo());
            pst.setString(2, dts.getAcceso());
            pst.setString(3, dts.getLogin());
            pst.setString(4, dts.getPassword());
            pst.setString(5, dts.getEstado());
            pst.setInt(6, dts.getIdpersona());

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
    
    public boolean editarContraseña(Integer id , String contra) {
        sSQL = "update trabajador set password=?"
                + " where idpersona=?";
        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setString(1, contra);
            pst.setInt(2, id);

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

    public boolean eliminar(vtrabajador dts) {
        sSQL = "delete from trabajador where idpersona=?";
        sSQL2 = "delete from persona where idpersona=?";

        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);

            pst.setInt(1, dts.getIdpersona());


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

    public boolean eliminar2(vtrabajador dts, String estado) {
        if (estado.equalsIgnoreCase("A")) {
           sSQL = "update trabajador set estado='D'"
                + " where idpersona=?"; 
        }else if (estado.equalsIgnoreCase("D")){
           sSQL = "update trabajador set estado='A'"
                + " where idpersona=?";  
        }
        
        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);

            pst.setInt(1, dts.getIdpersona());

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

}
