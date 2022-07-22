
package DAO;

import Modelo.vconsumo;
import Modelo.vdetalle_venta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class fdetalle_venta {
   private conexion mysql=new conexion();
   private Connection cn=mysql.conectar();
   private String sSQL="";
   public Integer totalregistros;
   public Double totalconsumo;
   
   
   public DefaultTableModel mostrar(String buscar){
       DefaultTableModel modelo;
       
       String [] titulos = {"ID","idventa","idproducto","producto","cantidad","Precio Venta"};
       
       String [] registro =new String [6];
       
       totalregistros=0;
       totalconsumo=0.0;
       modelo = new DefaultTableModel(null,titulos);
       
       sSQL="select d.iddetalle_venta,d.idventa,d.idproducto,p.nombre,d.cantidad,d.precio_venta "
               + " from detalle_venta d inner join producto p on d.idproducto=p.idproducto"
               + " where d.idventa ="+ buscar + " order by d.iddetalle_venta desc";
       
       try {
           Statement st= cn.createStatement();
           ResultSet rs=st.executeQuery(sSQL);
           
           while(rs.next()){
               registro [0]=rs.getString("iddetalle_venta");
               registro [1]=rs.getString("idventa");
               registro [2]=rs.getString("idproducto");
               registro [3]=rs.getString("nombre");
               registro [4]=rs.getString("cantidad");
               registro [5]=rs.getString("precio_venta");
               
               totalregistros=totalregistros+1;
               totalconsumo=totalconsumo + (rs.getDouble("cantidad") * rs.getDouble("precio_venta") );
               
               modelo.addRow(registro);
               
           }
           return modelo;
           
       } catch (Exception e) {
           JOptionPane.showConfirmDialog(null, e);
           return null;
       }
       
       
       
   } 
   
   public boolean insertar (vdetalle_venta dts){
       sSQL="insert into detalle_venta (idventa,idproducto,cantidad,precio_venta)" +
               "values (?,?,?,?)";
       try {
           
           PreparedStatement pst=cn.prepareStatement(sSQL);
           pst.setInt(1, dts.getIdventa());
           pst.setInt(2, dts.getIdproducto());
           pst.setDouble(3, dts.getCantidad());
           pst.setDouble(4, dts.getPrecio_venta());
           
           
           int n=pst.executeUpdate();
           
           if (n!=0){
               return true;
           }
           else {
               return false;
           }
           
           
           
       } catch (Exception e) {
           JOptionPane.showConfirmDialog(null, e);
           return false;
       }
   }
   
   public boolean editar (vdetalle_venta dts){
       sSQL="update detalle_venta set idventa=?,idproducto=?,cantidad=?,precio_venta=?"+
               " where iddetalle_venta=?";
           
       
       try {
           PreparedStatement pst=cn.prepareStatement(sSQL);
           pst.setInt(1, dts.getIdventa());
           pst.setInt(2, dts.getIdproducto());
           pst.setDouble(3, dts.getCantidad());
           pst.setDouble(4, dts.getPrecio_venta());
           
           pst.setInt(5, dts.getIddetalle_venta());
           
           int n=pst.executeUpdate();
           
           if (n!=0){
               return true;
           }
           else {
               return false;
           }
           
       } catch (Exception e) {
           JOptionPane.showConfirmDialog(null, e);
           return false;
       }
   }
  
   public boolean eliminar (vdetalle_venta dts){
       sSQL="delete from detalle_venta where iddetalle_venta=?";
       
       try {
           
           PreparedStatement pst=cn.prepareStatement(sSQL);
           
           pst.setInt(1, dts.getIddetalle_venta());
           
           int n=pst.executeUpdate();
           
           if (n!=0){
               return true;
           }
           else {
               return false;
           }
           
       } catch (Exception e) {
           JOptionPane.showConfirmDialog(null, e);
           return false;
       }
   }
    
}
