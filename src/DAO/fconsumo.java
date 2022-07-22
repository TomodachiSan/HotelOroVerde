
package DAO;

import Modelo.vconsumo;
import Modelo.vhabitacion;
import Modelo.vproducto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class fconsumo {
    
   private conexion mysql=new conexion();
   private Connection cn=mysql.conectar();
   private String sSQL="";
   public Integer totalregistros;
   public Double totalconsumo;
   
   
   public DefaultTableModel mostrar(String buscar){
       DefaultTableModel modelo;
       
       String [] titulos = {"ID","idalquiler","idproducto","Producto","Cantidad","Precio Venta","Monto"};
       
       String [] registro =new String [7];
       
       totalregistros=0;
       totalconsumo=0.0;
       modelo = new DefaultTableModel(null,titulos);
       
       sSQL="select c.idconsumo,c.idalquiler,c.idproducto,p.nombre,c.cantidad,c.precio_venta "
               + ",c.monto from consumo c inner join producto p on c.idproducto=p.idproducto"
               + " where c.idalquiler ="+ buscar + " order by c.idconsumo desc";
       
       try {
           Statement st= cn.createStatement();
           ResultSet rs=st.executeQuery(sSQL);
           
           while(rs.next()){
               registro [0]=rs.getString("idconsumo");
               registro [1]=rs.getString("idalquiler");
               registro [2]=rs.getString("idproducto");
               registro [3]=rs.getString("nombre");
               registro [4]=rs.getString("cantidad");
               registro [5]=rs.getString("precio_venta");
               registro [6]=rs.getString("monto");
               
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
   
   public boolean insertar (vconsumo dts){
       sSQL="insert into consumo (idalquiler,idproducto,cantidad,precio_venta,monto)" +
               "values (?,?,?,?,?)";
       try {
           
           PreparedStatement pst=cn.prepareStatement(sSQL);
           pst.setInt(1, dts.getIdalquiler());
           pst.setInt(2, dts.getIdproducto());
           pst.setInt(3, dts.getCantidad());
           pst.setDouble(4, dts.getPrecio_venta());
           pst.setDouble(5, dts.getMonto());
           
           
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
   
   /*public boolean editar (vconsumo dts){
       sSQL="update consumo set idreserva=?,idproducto=?,cantidad=?,precio_venta=?,estado=?"+
               " where idconsumo=?";
           
       
       try {
           PreparedStatement pst=cn.prepareStatement(sSQL);
           pst.setInt(1, dts.getIdreserva());
           pst.setInt(2, dts.getIdproducto());
           pst.setDouble(3, dts.getCantidad());
           pst.setDouble(4, dts.getPrecio_venta());
           pst.setString(5, dts.getEstado());
           
           pst.setInt(6, dts.getIdconsumo());
           
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
   }*/
  
   public boolean eliminar (vconsumo dts){
       sSQL="delete from consumo where idconsumo=?";
       
       try {
           
           PreparedStatement pst=cn.prepareStatement(sSQL);
           
           pst.setInt(1, dts.getIdconsumo());
           
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
   
   public void DisminuirProds(String buscar) {
        Integer cantidad = 0;
        Integer idproducto = 0;
        fproducto func = new fproducto();
        sSQL = "select p.idproducto,c.cantidad"
                + " from consumo c inner join producto p on c.idproducto=p.idproducto"
                + " where c.idalquiler =" + buscar + " order by c.idalquiler desc";

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
