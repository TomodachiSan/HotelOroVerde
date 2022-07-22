package Controlador;

import DAO.fproducto;
//import Vistas.frmConsumo;
import Vistas.frmDetalle_Venta;
import Vistas.frmConsumo;
import Vistas.frmvistaproducto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ControlVistaProducto implements ActionListener, MouseListener {

    private frmvistaproducto vista;
    public int op;

    public ControlVistaProducto(frmvistaproducto vista) {
        this.vista = vista;
        ComponentesInicio();
    }

    public void ComponentesInicio() {
        this.vista.btnbuscar.addActionListener(this);
        this.vista.btnsalir.addActionListener(this);

        this.vista.tablalistado.addMouseListener(this);
        mostrar("");
        this.vista.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {

        if (arg0.getSource() == vista.btnbuscar) {
            btnbuscarActionPerformed(arg0);
        }

        if (arg0.getSource() == vista.btnsalir) {
            btnsalirActionPerformed(arg0);
        }
    }

    void ocultar_columnas() {
        this.vista.tablalistado.getColumnModel().getColumn(0).setMaxWidth(0);
        this.vista.tablalistado.getColumnModel().getColumn(0).setMinWidth(0);
        this.vista.tablalistado.getColumnModel().getColumn(0).setPreferredWidth(0);
    }

    void mostrar(String buscar) {
        try {
            DefaultTableModel modelo;
            fproducto func = new fproducto();
            modelo = func.mostrar(buscar);

            this.vista.tablalistado.setModel(modelo);
            ocultar_columnas();
            this.vista.lbltotalregistros.setText("Total Registros " + Integer.toString(func.totalregistros));

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
        }
    }

    private void btnbuscarActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        mostrar(this.vista.txtbuscar.getText());
    }

    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        this.vista.dispose();
    }

    private void tablalistadoMousePressed(java.awt.event.MouseEvent evt) {
        if (op == 0) {
            if (evt.getClickCount() == 2) {
                int fila = this.vista.tablalistado.getSelectedRow();

                String cod, valor1, valor2, valor3;

                cod = this.vista.tablalistado.getValueAt(fila, 0).toString();
                valor1 = this.vista.tablalistado.getValueAt(fila, 1).toString();
                valor2 = this.vista.tablalistado.getValueAt(fila, 4).toString();
                valor3 = this.vista.tablalistado.getValueAt(fila, 5).toString();
                
                frmConsumo.txtidproducto.setText(cod);
                frmConsumo.txtproducto.setText(valor1);
                frmConsumo.txtprecio_venta.setText(valor2);
                frmConsumo.txtStock.setText(valor3);
                frmConsumo.txtmontoProd.setText(valor2);
                
                this.vista.setVisible(false);
            }
        } else if (op == 1) {
            if (evt.getClickCount() == 2) {
                int fila = this.vista.tablalistado.getSelectedRow();

                String cod, valor1, valor2, valor3;

                cod = this.vista.tablalistado.getValueAt(fila, 0).toString();
                valor1 = this.vista.tablalistado.getValueAt(fila, 1).toString();
                valor2 = this.vista.tablalistado.getValueAt(fila, 4).toString();
                valor3 = this.vista.tablalistado.getValueAt(fila, 5).toString();

                frmDetalle_Venta.txtidproducto.setText(cod);
                frmDetalle_Venta.txtproducto.setText(valor1);
                frmDetalle_Venta.txtprecio_venta.setText(valor2);
                frmDetalle_Venta.txtStock.setText(valor3);
                frmDetalle_Venta.txtmontoProd.setText(valor2);
                

                this.vista.setVisible(false);
            }
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == vista.tablalistado) {
            tablalistadoMousePressed(e);
        }
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

}
