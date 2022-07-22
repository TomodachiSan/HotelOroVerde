

package DAO;

import Modelo.vhabitacion;
import Modelo.vproducto;
import Modelo.valquiler;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class falquiler {
    
   private conexion mysql=new conexion();
   private Connection cn=mysql.conectar();
   private String sSQL="";
   public Integer totalregistros;
   
   
   public DefaultTableModel mostrar(String buscar){
       DefaultTableModel modelo;
       
       String [] titulos = {"ID","ID Hab.","Numero Hab.","ID Cliente","Cliente","ID Trabajador","Trabajador","Fecha Ingreso","Hora Ingreso","Fecha Salida","Hora Salida","Costo Dia","Costo Final"};
       
       String [] registro =new String [13];
       
       totalregistros=0;
       modelo = new DefaultTableModel(null,titulos);
       
       sSQL="select a.idalquiler,a.idhabitacion,h.numero,a.idcliente,"+
               "(select nombre from persona where idpersona=a.idcliente)as clienten,"+
               "(select apaterno from persona where idpersona=a.idcliente)as clienteap,"+
               "a.idtrabajador,(select nombre from persona where idpersona=a.idtrabajador)as trabajadorn,"+
               "(select apaterno from persona where idpersona=a.idtrabajador)as trabajadorap,"+
               "a.fecha_ingresa,a.hora_ingresa,a.fecha_salida,a.hora_salida,"+ 
               "a.costo_Dia,a.costo_alojamiento from alquiler a inner join habitacion h on a.idhabitacion=h.idhabitacion where h.numero like '%"+ buscar + "%' order by idalquiler desc limit 0,200";
       
       try {
           Statement st= cn.createStatement();
           ResultSet rs=st.executeQuery(sSQL);
           
           while(rs.next()){
               registro [0]=rs.getString("idalquiler");
               registro [1]=rs.getString("idhabitacion");
               registro [2]=rs.getString("numero");
               registro [3]=rs.getString("idcliente");
               registro [4]=rs.getString("clienten") + " " + rs.getString("clienteap");
               registro [5]=rs.getString("idtrabajador");
               registro [6]=rs.getString("trabajadorn") + " " + rs.getString("trabajadorap");
               registro [7]=rs.getString("fecha_ingresa");
               registro [8]=rs.getString("hora_ingresa");
               registro [9]=rs.getString("fecha_salida");
               registro [10]=rs.getString("hora_salida");
               registro [11]=rs.getString("costo_Dia");
               registro [12]=rs.getString("costo_alojamiento");
               
               totalregistros=totalregistros+1;
               modelo.addRow(registro);
               
           }
           return modelo;
           
       } catch (Exception e) {
           JOptionPane.showConfirmDialog(null, e);
           return null;
       }
       
       
       
   } 
   
   public boolean insertar (valquiler dts){
       String fecha,hora;       
       fecha=funciones.getFechaActual();
       hora=funciones.getHoraActual();
       
       sSQL="insert into alquiler (idhabitacion,idcliente,idtrabajador,fecha_ingresa,hora_ingresa,fecha_salida,hora_salida,costo_Dia,costo_alojamiento)" +
               "values (?,?,?,?,?,?,?,?,?)";
       try {
           
           PreparedStatement pst=cn.prepareStatement(sSQL);
           pst.setInt(1, dts.getIdhabitacion());
           pst.setInt(2, dts.getIdcliente());
           pst.setInt(3, dts.getIdtrabajador());
           pst.setDate(4, dts.getFecha_ingresa());
           pst.setString(5, hora);
           pst.setDate(6, dts.getFecha_salida());
           pst.setString(7, hora);
           pst.setDouble(8, dts.getCosto_Dia());
           pst.setDouble(9, dts.getCosto_alojamiento());
           
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
   
   public boolean editar (valquiler dts){
       sSQL="update alquiler set idhabitacion=?,idcliente=?,idtrabajador=?,fecha_ingresa=?,fecha_salida=?,hora_salida=?,costo_Dia=?,costo_alojamiento=?"+
               " where idalquiler=?";
           
       
       try {
           PreparedStatement pst=cn.prepareStatement(sSQL);
             pst.setInt(1, dts.getIdhabitacion());
           pst.setInt(2, dts.getIdcliente());
           pst.setInt(3, dts.getIdtrabajador());
           pst.setDate(4, dts.getFecha_ingresa());
           pst.setDate(5, dts.getFecha_salida());
           pst.setString(6, dts.getHora_salida());
           pst.setDouble(7, dts.getCosto_Dia());
           pst.setDouble(8, dts.getCosto_alojamiento());
           pst.setInt(9, dts.getIdalquiler());
           
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
   
   public boolean editar2 (valquiler dts){
       sSQL="update alquiler set fecha_salida=?,hora_salida=?,costo_alojamiento=?"+
               " where idalquiler=?";
           
       
       try {
           PreparedStatement pst=cn.prepareStatement(sSQL);
           pst.setDate(1, dts.getFecha_salida());
           pst.setString(2, dts.getHora_salida());
           pst.setDouble(3, dts.getCosto_alojamiento());
           pst.setInt(4, dts.getIdalquiler());
           
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
   
   
    public boolean salir (valquiler dts){
        String fecha,hora;       
       fecha=funciones.getFechaActual();
       hora=funciones.getHoraActual();
       
       sSQL="update alquiler set fecha_salida=?,hora_salida=?"+
               " where idalquiler=?";
           //alt + 39
       
       try {
           PreparedStatement pst=cn.prepareStatement(sSQL);
             
           pst.setString(1, fecha);
           pst.setString(2, hora);
           pst.setInt(3, dts.getIdalquiler());
           
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
   /*public boolean eliminar (valquiler dts){
       sSQL="delete from reserva where idreserva=?";
       
       try {
           
           PreparedStatement pst=cn.prepareStatement(sSQL);
           
           pst.setInt(1, dts.getIdreserva());
           
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
    
   public void Fecha(valquiler dts,Calendar cal1,Calendar cal2){
       
        int d, m, a;
        d = cal1.get(Calendar.DAY_OF_MONTH);
        m = cal1.get(Calendar.MONTH);
        a = cal1.get(Calendar.YEAR) - 1900;
        Date dat = new Date(a, m, d);
        dts.setFecha_ingresa(dat);
        
        d = cal2.get(Calendar.DAY_OF_MONTH);
        m = cal2.get(Calendar.MONTH);
        a = cal2.get(Calendar.YEAR) - 1900;
        dat = new Date(a, m, d);
        dts.setFecha_salida(dat);
       
   } 
    
    
    
}
