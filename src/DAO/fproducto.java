
package DAO;

import Modelo.vhabitacion;
import Modelo.vproducto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class fproducto {
    
   private conexion mysql=new conexion();
   private Connection cn=mysql.conectar();
   private String sSQL="";
   public Integer totalregistros;
   
   
   public DefaultTableModel mostrar(String buscar){
       DefaultTableModel modelo;
       
       String [] titulos = {"ID","Producto","Descripción","Unidad Medida","Precio Venta","Stock","Estado"};
       
       String [] registro =new String [7];
       
       totalregistros=0;
       modelo = new DefaultTableModel(null,titulos);
       
       sSQL="select * from producto where nombre like '%"+ buscar + "%' order by idproducto desc";
       
       try {
           Statement st= cn.createStatement();
           ResultSet rs=st.executeQuery(sSQL);
           
           while(rs.next()){
               registro [0]=rs.getString("idproducto");
               registro [1]=rs.getString("nombre");
               registro [2]=rs.getString("descripcion");
               registro [3]=rs.getString("unidad_medida");
               registro [4]=rs.getString("precio_venta");
               registro [5]=rs.getString("stock");
               registro [6]=rs.getString("estado");
               
               
               totalregistros=totalregistros+1;
               modelo.addRow(registro);
               
           }
           return modelo;
           
       } catch (Exception e) {
           JOptionPane.showConfirmDialog(null, e);
           return null;
       }
       
       
       
   } 
   
   public boolean insertar (vproducto dts){
       sSQL="insert into producto (nombre,descripcion,unidad_medida,precio_venta,stock,estado)" +
               "values (?,?,?,?,?,?)";
       try {
           
           PreparedStatement pst=cn.prepareStatement(sSQL);
           pst.setString(1, dts.getNombre());
           pst.setString(2, dts.getDescripcion());
           pst.setString(3, dts.getUnidad_medida());
           pst.setDouble(4, dts.getPrecio_venta());
           pst.setDouble(5, dts.getStock());
           pst.setString(6, "A");
           
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
   
   public boolean editar (vproducto dts){
       sSQL="update producto set nombre=?,descripcion=?,unidad_medida=?,precio_venta=?,stock=?"+
               " where idproducto=?";
           
       
       try {
           PreparedStatement pst=cn.prepareStatement(sSQL);
           pst.setString(1, dts.getNombre());
           pst.setString(2, dts.getDescripcion());
           pst.setString(3, dts.getUnidad_medida());
           pst.setDouble(4, dts.getPrecio_venta());
           pst.setDouble(5, dts.getStock());
           
           pst.setInt(6, dts.getIdproducto());
           
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
   
   public boolean disminuir (int idproducto,int cantidad){
       sSQL="update producto set stock=stock-?"+
               " where idproducto=?";
           
       
       try {
           PreparedStatement pst=cn.prepareStatement(sSQL);
           pst.setInt(1, cantidad);
           pst.setInt(2, idproducto);
           
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
   
   public boolean aumentar (int idproducto,int cantidad){
       sSQL="update producto set stock=stock+?"+
               " where idproducto=?";
           
       
       try {
           PreparedStatement pst=cn.prepareStatement(sSQL);
           pst.setInt(1, cantidad);
           pst.setInt(2, idproducto);
           
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
  
   public boolean eliminar (vproducto dts){
       sSQL="delete from producto where idproducto=?";
       
       try {
           
           PreparedStatement pst=cn.prepareStatement(sSQL);
           
           pst.setInt(1, dts.getIdproducto());
           
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
   
   public boolean eliminar2 (vproducto dts, String estado){
       if (estado.equalsIgnoreCase("A")) {
           sSQL = "update producto set estado='D'"
                + " where idproducto=?"; 
        }else if (estado.equalsIgnoreCase("D")){
           sSQL = "update producto set estado='A'"
                + " where idproducto=?";  
        }
        
        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);

            pst.setInt(1, dts.getIdproducto());

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
