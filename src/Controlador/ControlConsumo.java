package Controlador;

import DAO.fconsumo;
import DAO.fproducto;
import Modelo.vconsumo;
import Vistas.frmConsumo;
import Vistas.frminicio;
import Vistas.frmvistaproducto;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.table.DefaultTableModel;

public class ControlConsumo implements ActionListener, MouseListener, ChangeListener,InternalFrameListener {

    private frmConsumo vista;
    public static String idalquiler;
    public static String cliente;

    public ControlConsumo(frmConsumo vista) {
        this.vista = vista;
        this.vista.addInternalFrameListener(this);
        ComponentesInicio();
    }

    public void ComponentesInicio() {
        
        this.vista.txtcliente.setText(cliente);
        this.vista.txtidalquiler.setText(idalquiler);
        
        this.vista.btnbuscarproducto.addActionListener(this);
        this.vista.btneliminar.addActionListener(this);
        this.vista.btncancelar.addActionListener(this);
        this.vista.btnnuevo.addActionListener(this);
        this.vista.btnguardar.addActionListener(this);
        this.vista.tablalistado.addMouseListener(this);
        this.vista.jspnCantidad.addChangeListener(this);
        mostrar(idalquiler);
        inhabilitar();
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {

        if (arg0.getSource() == vista.btnbuscarproducto) {
            btnbuscarproductoActionPerformed();
        }
        if (arg0.getSource() == vista.btnguardar) {
            btnguardarActionPerformed();
        }

        if (arg0.getSource() == vista.btneliminar) {
            btneliminarActionPerformed();
        }

        if (arg0.getSource() == vista.btncancelar) {
            inhabilitar();
        }

        if (arg0.getSource() == vista.btnnuevo) {
            habilitar();
            this.vista.btnguardar.setText("Guardar");
            accion = "guardar";
        }
    }

    private void btnguardarActionPerformed() {
        // TODO add your handling code here:
        if (this.vista.txtidproducto.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Debes Seleccionar un producto");
            this.vista.btnbuscarproducto.requestFocus();
            return;
        }

        vconsumo dts = new vconsumo();
        fconsumo func = new fconsumo();

        dts.setIdalquiler(Integer.parseInt(this.vista.txtidalquiler.getText()));
        
        int cant1, stock1;
        cant1 = Integer.parseInt(this.vista.jspnCantidad.getValue().toString());
        stock1 = Integer.parseInt(this.vista.txtStock.getText());
        dts.setIdproducto(Integer.parseInt(this.vista.txtidproducto.getText()));
        dts.setCantidad(cant1);
        dts.setPrecio_venta(Double.parseDouble(this.vista.txtprecio_venta.getText()));
        dts.setMonto(Double.parseDouble(this.vista.txtmontoProd.getText()));
        

        if (stock1 >= cant1) {
            if (accion.equals("guardar")) {
                if (func.insertar(dts)) {
                    mostrar(idalquiler);
                    fproducto func2 = new fproducto();
                    func2.disminuir(dts.getIdproducto(),dts.getCantidad());
                    inhabilitar();
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
//                mostrar(idalquiler);
//                inhabilitar();
//            }
//        }
    }

    private void btnbuscarproductoActionPerformed() {
        frmvistaproducto form = new frmvistaproducto();
        form.toFront();
        //Centrar en el escritorio
        Dimension desktopSize = frminicio.escritorio.getSize();
        Dimension FrameSize = form.getSize();
        form.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        form.setVisible(true);
        ControlVistaProducto control = new ControlVistaProducto(form);
        control.op = 0;
    }

    private void btneliminarActionPerformed() {
        // TODO add your handling code here:
        if (!this.vista.txtidconsumo.getText().equals("")) {
            int confirmacion = JOptionPane.showConfirmDialog(null, "Estás seguro de Eliminar el Producto?", "Confirmar", 2);

            if (confirmacion == 0) {
                fconsumo func = new fconsumo();
                vconsumo dts = new vconsumo();

                dts.setIdconsumo(Integer.parseInt(this.vista.txtidconsumo.getText()));
                func.eliminar(dts);
                fproducto func2 = new fproducto();
                int idpr = Integer.parseInt(this.vista.txtidproducto.getText());
                int cant = Integer.parseInt(this.vista.jspnCantidad.getValue().toString());
                func2.aumentar(idpr,cant);
                mostrar(idalquiler);
                inhabilitar();

            }

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
        this.vista.txtidconsumo.setVisible(false);
        this.vista.txtidalquiler.setVisible(false);
        this.vista.txtcliente.setEnabled(false);
        this.vista.txtidproducto.setVisible(false);
        this.vista.txtproducto.setEnabled(false);
        
        
        this.vista.jspnCantidad.setEnabled(false);
        this.vista.btnbuscarproducto.setEnabled(false);
        this.vista.txtprecio_venta.setEnabled(false);
        
        this.vista.txtStock.setEnabled(false);
        
        this.vista.txtmontoProd.setEnabled(false);

        
        this.vista.btnguardar.setEnabled(false);
        this.vista.btncancelar.setEnabled(false);
        this.vista.btneliminar.setEnabled(false);
        
        this.vista.txtmonto.setEnabled(false);

        this.vista.txtidconsumo.setText("");
        this.vista.txtprecio_venta.setText("");
        this.vista.txtidproducto.setText("");
        this.vista.txtproducto.setText("");
        this.vista.txtStock.setText("");
        vista.jspnCantidad.setValue(1);

    }
    
    void habilitar() {
        this.vista.txtidconsumo.setVisible(false);
        this.vista.txtidalquiler.setVisible(false);
        this.vista.txtcliente.setEnabled(false);
        this.vista.txtidproducto.setVisible(false);
        this.vista.txtproducto.setEnabled(false);
        
        
        this.vista.jspnCantidad.setEnabled(true);
        this.vista.btnbuscarproducto.setEnabled(true);
        this.vista.txtprecio_venta.setEnabled(false);
        
        this.vista.txtStock.setEnabled(false);
        
        this.vista.txtmontoProd.setEnabled(false);

        
        this.vista.btnguardar.setEnabled(true);
        this.vista.btncancelar.setEnabled(true);
        //this.vista.btneliminar.setEnabled(false);
        
        this.vista.txtmonto.setEnabled(false);

        this.vista.txtidconsumo.setText("");
        this.vista.txtprecio_venta.setText("");
        this.vista.txtidproducto.setText("");
        this.vista.txtproducto.setText("");
        this.vista.txtStock.setText("");
        vista.jspnCantidad.setValue(1);

    }
    
    
    
    

    

    void mostrar(String buscar) {
        try {
            DefaultTableModel modelo;
            fconsumo func = new fconsumo();
            modelo = func.mostrar(buscar);
            this.vista.tablalistado.setModel(modelo);
            ocultar_columnas();
            this.vista.lbltotalregistros.setText("Total Registros " + Integer.toString(func.totalregistros));
            this.vista.lblconsumo.setText("Consumo Total S/. " + func.totalconsumo);
            this.vista.txtmonto.setText(func.totalconsumo.toString());
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
        }
    }

    private void tablalistadoMouseClicked(MouseEvent evt) {
        // TODO add your handling code here:
        //btnguardar.setText("Editar");
        //habilitar();
        this.vista.btneliminar.setEnabled(true);
        //accion="editar";

        int fila = this.vista.tablalistado.rowAtPoint(evt.getPoint());
        if (fila >= 0) {
          this.vista.txtidconsumo.setText(this.vista.tablalistado.getValueAt(fila, 0).toString());
        this.vista.txtidalquiler.setText(this.vista.tablalistado.getValueAt(fila, 1).toString());
        this.vista.txtidproducto.setText(this.vista.tablalistado.getValueAt(fila, 2).toString());
        this.vista.txtproducto.setText(this.vista.tablalistado.getValueAt(fila, 3).toString());
        this.vista.jspnCantidad.setValue(Integer.parseInt(this.vista.tablalistado.getValueAt(fila, 4).toString()));
        this.vista.txtprecio_venta.setText(this.vista.tablalistado.getValueAt(fila, 5).toString());  
        this.vista.txtmontoProd.setText(this.vista.tablalistado.getValueAt(fila, 6).toString());
        }
        


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
            if (this.vista.txtprecio_venta.getText().equalsIgnoreCase("")) {
                this.vista.txtmontoProd.setText("");
            } else {
                double precio_product = Double.parseDouble(this.vista.txtprecio_venta.getText());
                int cant = Integer.parseInt(vista.jspnCantidad.getValue().toString());
                double monto = precio_product * cant;
                String montof = String.valueOf(monto);
                this.vista.txtmontoProd.setText(montof);
            }
        }
    }

    @Override
    public void internalFrameOpened(InternalFrameEvent ife) {
        
    }

    @Override
    public void internalFrameClosing(InternalFrameEvent ife) {
        int confirmacion = JOptionPane.showConfirmDialog(null, "Estás seguro que desea salir", "SALIR", 2);

            if (confirmacion == 0) {
                this.vista.dispose();

            }
    }

    @Override
    public void internalFrameClosed(InternalFrameEvent ife) {
         
    }

    @Override
    public void internalFrameIconified(InternalFrameEvent ife) {
        
    }

    @Override
    public void internalFrameDeiconified(InternalFrameEvent ife) {
        
    }

    @Override
    public void internalFrameActivated(InternalFrameEvent ife) {
        
    }

    @Override
    public void internalFrameDeactivated(InternalFrameEvent ife) {
        
    }
}

// VER LO DEL JSPINNER
//ACTUALIZAR EL STOCK DE LOS PRODUCTOS
