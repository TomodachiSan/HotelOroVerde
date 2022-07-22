package Controlador;

import DAO.conexion;
import DAO.fdetalle_venta;
import DAO.fproducto;
import DAO.funciones;
import DAO.fventa;
import Modelo.vdetalle_venta;
import Modelo.vventa;
import Vistas.frmDetalle_Venta;
import static Vistas.frmDetalle_Venta.txtStock;
import static Vistas.frmDetalle_Venta.txtidproducto;
import static Vistas.frmDetalle_Venta.txtprecio_venta;
import static Vistas.frmDetalle_Venta.txtproducto;
import static Vistas.frmDetalle_Venta.txtmontoProd;
import Vistas.frminicio;
import Vistas.frmvistaproducto;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class ControlDetalle_Venta implements ActionListener, MouseListener, ChangeListener,InternalFrameListener {

    private frmDetalle_Venta vista;
    public static String cliente;
    public String idV = "0";
    private Connection connection = new conexion().conectar();
    //public String idTrab;

    public ControlDetalle_Venta(frmDetalle_Venta vista) {
        this.vista = vista;
        this.vista.addInternalFrameListener(this);
        //this.vista.addWindowListener(this);
        ComponentesInicio();
    }

    public void ComponentesInicio() {
        this.vista.btnnuevo.addActionListener(this);
        this.vista.btncancelar.addActionListener(this);
        this.vista.btneliminar.addActionListener(this);
        this.vista.btnguardar.addActionListener(this);
        this.vista.btnnuevo.addActionListener(this);
        this.vista.tablalistado.addMouseListener(this);
        this.vista.btnbuscarproducto.addActionListener(this);
        this.vista.btnGenerarVenta.addActionListener(this);
        this.vista.jspnCantidad.addChangeListener(this);
        mostrar(idV);
        this.vista.txtidventa.setText(idV);
        inhabilitar();
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if (arg0.getSource() == vista.btnbuscarproducto) {
            btnbuscarproductoActionPerformed(arg0);
        }
        if (arg0.getSource() == vista.btncancelar) {
            btncancelarActionPerformed(arg0);
        }
        if (arg0.getSource() == vista.btneliminar) {
            btneliminarActionPerformed(arg0);
        }
        if (arg0.getSource() == vista.btnguardar) {
            btnguardarActionPerformed(arg0);
        }
        if (arg0.getSource() == vista.btnnuevo) {
            btnnuevoActionPerformed(arg0);
        }
        
        if (arg0.getSource() == vista.btnGenerarVenta) {
            btnGenerarVentaActionPerformed(arg0);
        }
    }

    private String accion = "guardar";

    void ocultar_columnas() {
        this.vista.tablalistado.getColumnModel().getColumn(0).setMaxWidth(0);
        this.vista.tablalistado.getColumnModel().getColumn(0).setMinWidth(0);
        this.vista.tablalistado.getColumnModel().getColumn(0).setPreferredWidth(0);

        this.vista.tablalistado.getColumnModel().getColumn(1).setMaxWidth(0);
        this.vista.tablalistado.getColumnModel().getColumn(1).setMinWidth(0);
        this.vista.tablalistado.getColumnModel().getColumn(1).setPreferredWidth(0);

        this.vista.tablalistado.getColumnModel().getColumn(2).setMaxWidth(0);
        this.vista.tablalistado.getColumnModel().getColumn(2).setMinWidth(0);
        this.vista.tablalistado.getColumnModel().getColumn(2).setPreferredWidth(0);
    }

    void inhabilitar() {
        this.vista.txtiddetalle_venta.setVisible(false);
        this.vista.txtidventa.setVisible(false);
        txtidproducto.setVisible(false);
        txtproducto.setEnabled(false);
        this.vista.txtidtrabajador.setVisible(false);
        this.vista.jspnCantidad.setEnabled(false);
        txtStock.setEnabled(false);
        txtprecio_venta.setEnabled(false);
        txtmontoProd.setEnabled(false);

        this.vista.btnbuscarproducto.setEnabled(false);
        this.vista.btnguardar.setEnabled(false);
        this.vista.btncancelar.setEnabled(false);
        this.vista.btneliminar.setEnabled(false);
        this.vista.txtmontoTotal.setEnabled(false);
        this.vista.txtmonto.setEnabled(false);

        this.vista.txtiddetalle_venta.setText("");
        txtprecio_venta.setText("");
        txtidproducto.setText("");
        txtproducto.setText("");
        txtStock.setText("");
        vista.jspnCantidad.setValue(1);

    }

    void habilitar() {
        this.vista.txtiddetalle_venta.setVisible(false);
        this.vista.txtidventa.setVisible(false);
        this.vista.txtidtrabajador.setVisible(false);
        txtidproducto.setVisible(false);
        txtproducto.setEnabled(false);
        this.vista.jspnCantidad.setEnabled(true);
        txtStock.setEnabled(false);
        txtprecio_venta.setEnabled(false);
        txtmontoProd.setEnabled(false);

        this.vista.btnbuscarproducto.setEnabled(true);
        this.vista.btnguardar.setEnabled(true);
        this.vista.btncancelar.setEnabled(true);
        //this.vista.btneliminar.setEnabled(true);
        this.vista.txtmontoTotal.setEnabled(false);
        this.vista.txtmonto.setEnabled(false);

        this.vista.txtiddetalle_venta.setText("");
        txtprecio_venta.setText("");
        txtidproducto.setText("");
        txtproducto.setText("");
        txtStock.setText("");
        vista.jspnCantidad.setValue(1);

    }

    void mostrar(String buscar) {
        try {
            DefaultTableModel modelo;
            fventa func = new fventa();
            modelo = func.mostrarDV(buscar);

            this.vista.tablalistado.setModel(modelo);
            ocultar_columnas();
            this.vista.lbltotalregistros.setText("Total Registros " + Integer.toString(func.totalregistros));
            this.vista.lblconsumo.setText("Venta Total S/. " + func.totalconsumo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void txtidventaActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        this.vista.txtidventa.transferFocus();
    }

    private void txtprecio_ventaActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        txtprecio_venta.transferFocus();
    }

    private void btnnuevoActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        habilitar();
        this.vista.btnguardar.setText("Guardar");
        accion = "guardar";
    }

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        if (txtidproducto.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Debes Seleccionar un producto");
            this.vista.btnbuscarproducto.requestFocus();
            return;
        }

        vdetalle_venta dts = new vdetalle_venta();
        vventa dts2 = new vventa();
        fventa func = new fventa();

        //dts.setIdventa(Integer.parseInt(this.vista.txtidventa.getText()));
        int cant1, stock1;
        cant1 = Integer.parseInt(vista.jspnCantidad.getValue().toString());
        stock1 = Integer.parseInt(txtStock.getText());
        dts.setIdproducto(Integer.parseInt(txtidproducto.getText()));
        dts.setCantidad(cant1);
        dts.setPrecio_venta(Double.parseDouble(txtprecio_venta.getText()));
        dts.setMonto(Double.parseDouble(txtmontoProd.getText()));

        dts2.setIdtrabajador(Integer.parseInt(this.vista.txtidtrabajador.getText()));
        dts2.setMonto(0d);
        dts2.setIgv(0d);
        dts2.setMonto_total(0d);

        if (stock1 >= cant1) {
            if (accion.equals("guardar")) {
                if (Integer.parseInt(this.vista.txtidventa.getText()) == 0) {
                    if (func.insertar(dts2, dts)) {
                        Integer UltIdV = func.UltimoIdVenta();
                        Double montoProduct = func.SumaMontosDV(UltIdV);
                        this.vista.txtidventa.setText(UltIdV.toString());
                        Double igv = montoProduct * 0.18;
                        Double Monto_Tot = montoProduct + igv;
                        vventa dts3 = new vventa();
                        dts3.setMonto(montoProduct);
                        dts3.setIgv(igv);
                        dts3.setMonto_total(Monto_Tot);
                        dts3.setIdventa(UltIdV);
                        func.editarMontos(dts3);
                        this.vista.txtmonto.setText(montoProduct.toString());
                        this.vista.txtmontoTotal.setText(Monto_Tot.toString());
                        mostrar(this.vista.txtidventa.getText());
                        inhabilitar();
                    }
                } else {
                    dts.setIdventa(Integer.parseInt(this.vista.txtidventa.getText()));
                    if (func.insertar2(dts)) {
                        Integer UltIdV = func.UltimoIdVenta();
                        Double montoProduct = func.SumaMontosDV(UltIdV);
                        Double igv = montoProduct * 0.18;
                        Double Monto_Tot = montoProduct + igv;
                        vventa dts3 = new vventa();
                        dts3.setMonto(montoProduct);
                        dts3.setIgv(igv);
                        dts3.setMonto_total(Monto_Tot);
                        dts3.setIdventa(UltIdV);
                        func.editarMontos(dts3);
                        this.vista.txtmonto.setText(montoProduct.toString());
                        this.vista.txtmontoTotal.setText(Monto_Tot.toString());
                        mostrar(this.vista.txtidventa.getText());
                        inhabilitar();
                    }
                }

            }
        } else {
            JOptionPane.showMessageDialog(null,
                    "No hay suficiente Stock", "Ingrese otra cantidad del producto", JOptionPane.ERROR_MESSAGE);
        }

        //        else if (accion.equals("editar")){
        //            dts.setIdconsumo(Integer.parseInt(txtidconsumo.getText()));
        //
        //            if (func.editar(dts)) {
        //                JOptionPane.showMessageDialog(rootPane, "El consumo del cliente "
        //                        + txtcliente.getText() + " ha sido modificado correctamente ");
        //                mostrar(idreserva);
        //                inhabilitar();
        //            }
        //        }
    }

    private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        inhabilitar();

    }

    private void txtidproductoActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void btnbuscarproductoActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        frmvistaproducto form = new frmvistaproducto();
        form.toFront();
        //Centrar en el escritorio
        Dimension desktopSize = frminicio.escritorio.getSize();
        Dimension FrameSize = form.getSize();
        form.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        form.setVisible(true);
        ControlVistaProducto control = new ControlVistaProducto(form);
        control.op = 1;
    }

    private void tablalistadoMouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        //btnguardar.setText("Editar");
        //habilitar();
        this.vista.btneliminar.setEnabled(true);
        //accion="editar";

        int fila = this.vista.tablalistado.getSelectedRow();

        if (fila >= 0) {
            this.vista.txtiddetalle_venta.setText(this.vista.tablalistado.getValueAt(fila, 0).toString());
            this.vista.txtidventa.setText(this.vista.tablalistado.getValueAt(fila, 1).toString());
            txtidproducto.setText(this.vista.tablalistado.getValueAt(fila, 2).toString());
            txtproducto.setText(this.vista.tablalistado.getValueAt(fila, 3).toString());
            vista.jspnCantidad.setValue(Integer.parseInt(this.vista.tablalistado.getValueAt(fila, 4).toString()));
            txtprecio_venta.setText(this.vista.tablalistado.getValueAt(fila, 5).toString());
            txtmontoProd.setText(this.vista.tablalistado.getValueAt(fila, 6).toString());
        }
    }

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        if (!this.vista.txtiddetalle_venta.getText().equals("")) {
            int confirmacion = JOptionPane.showConfirmDialog(null, "Estás seguro de Eliminar el Producto?", "Confirmar", 2);

            if (confirmacion == 0) {
                fventa func = new fventa();
                vdetalle_venta dts = new vdetalle_venta();

                dts.setIddetalle_venta(Integer.parseInt(this.vista.txtiddetalle_venta.getText()));
                func.eliminarDV(dts);
                EditarMontos();
                mostrar(this.vista.txtidventa.getText());
                inhabilitar();

            }

        }
    }
    
    private void btnGenerarVentaActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        fventa func = new fventa();
        func.DisminuirProds(this.vista.txtidventa.getText());
        JOptionPane.showMessageDialog(null, "La venta ha sido realizada");
        GenerarBoleta();
        this.vista.dispose();
    }
    
    private void GenerarBoleta() {
        Map p = new HashMap();

        p.put("idventa", vista.txtidventa.getText());

        JasperReport report;
        JasperPrint print;
        try {

            report = JasperCompileManager.compileReport(new File("").getAbsolutePath() + "/src/Reportes/rptComprobanteVenta.jrxml");
            print = JasperFillManager.fillReport(report, p, connection);
            JasperViewer view = new JasperViewer(print, false);
            view.setTitle("Comprobante VentaT");
            view.setVisible(true);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }

    public void EditarMontos() {
        fventa func = new fventa();
        Integer UltIdV = func.UltimoIdVenta();
        Double montoProduct = func.SumaMontosDV(UltIdV);
        Double igv = montoProduct * 0.18;
        Double Monto_Tot = montoProduct + igv;
        vventa dts3 = new vventa();
        dts3.setMonto(montoProduct);
        dts3.setIgv(igv);
        dts3.setMonto_total(Monto_Tot);
        dts3.setIdventa(UltIdV);
        func.editarMontos(dts3);
        this.vista.txtmonto.setText(montoProduct.toString());
        this.vista.txtmontoTotal.setText(Monto_Tot.toString());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == vista.tablalistado) {

            tablalistadoMouseClicked(e);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void stateChanged(ChangeEvent ce) {
        if (ce.getSource() == vista.jspnCantidad) {
            if (txtprecio_venta.getText().equalsIgnoreCase("")) {
                txtmontoProd.setText("");
            } else {
                double precio_product = Double.parseDouble(txtprecio_venta.getText());
                int cant = Integer.parseInt(vista.jspnCantidad.getValue().toString());
                double monto = precio_product * cant;
                String montof = String.valueOf(monto);
                txtmontoProd.setText(montof);
            }
        }
    }
    

    @Override
    public void internalFrameOpened(InternalFrameEvent ife) {
        //
    }

    @Override
    public void internalFrameClosing(InternalFrameEvent ife) {
        int confirmacion = JOptionPane.showConfirmDialog(null, "Estás seguro que desea salir", "SALIR", 2);

            if (confirmacion == 0) {
                Integer id = Integer.parseInt(this.vista.txtidventa.getText());
                if (id != 0) {
                    fventa func = new fventa();
                    func.eliminarTodo(id);
                }
                
                this.vista.dispose();

            }
    }

    @Override
    public void internalFrameClosed(InternalFrameEvent ife) {
        
    }

    @Override
    public void internalFrameIconified(InternalFrameEvent ife) {
        //
    }

    @Override
    public void internalFrameDeiconified(InternalFrameEvent ife) {
        //
    }

    @Override
    public void internalFrameActivated(InternalFrameEvent ife) {
        //
    }

    @Override
    public void internalFrameDeactivated(InternalFrameEvent ife) {
        //
    }
}
