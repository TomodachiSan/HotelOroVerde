package Controlador;

import DAO.fcliente;
import Vistas.frmVenta;
import Vistas.frmalquiler;
//import Vistas.frmreserva;
import Vistas.frmvistacliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ControlVistaCliente implements ActionListener, MouseListener {

    private frmvistacliente vista;
    public byte op;

    public ControlVistaCliente(frmvistacliente vista) {
        this.vista = vista;
        ComponentesInicio();
    }

    public void ComponentesInicio() {
        this.vista.btnbuscar.addActionListener(this);
        this.vista.btnsalir.addActionListener(this);
        this.vista.tablalistado.addMouseListener(this);
        this.vista.setLocationRelativeTo(null);
        mostrar("");
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
            fcliente func = new fcliente();
            modelo = func.mostrar(buscar);

            this.vista.tablalistado.setModel(modelo);
            ocultar_columnas();
            this.vista.lbltotalregistros.setText("Total Registros " + Integer.toString(func.totalregistros));

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
        }
    }
    
    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        this.vista.dispose();
    }

    void buscar(String buscar) {
        try {
            DefaultTableModel modelo;
            fcliente func = new fcliente();
            modelo = func.buscar(buscar);

            this.vista.tablalistado.setModel(modelo);
            ocultar_columnas();
            this.vista.lbltotalregistros.setText("Total Registros " + Integer.toString(func.totalregistros));

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
        }
    }

    private void btnbuscarActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        buscar(this.vista.txtbuscar.getText());
    }

    private void tablalistadoMousePressed(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:

        if (op == 0) {
            if (evt.getClickCount() == 2) {
                int fila = this.vista.tablalistado.getSelectedRow();
                String cod;
                String valor;

                cod = this.vista.tablalistado.getValueAt(fila, 0).toString();
                valor = this.vista.tablalistado.getValueAt(fila, 1).toString() + " " + this.vista.tablalistado.getValueAt(fila, 2).toString();

                frmalquiler.txtidcliente.setText(cod);
                frmalquiler.txtcliente.setText(valor);
                this.vista.dispose();
            }
        } else if (op == 1) {
            if (evt.getClickCount() == 2) {
                int fila = this.vista.tablalistado.getSelectedRow();
                String cod;
                String valor;

                cod = this.vista.tablalistado.getValueAt(fila, 0).toString();
                valor = this.vista.tablalistado.getValueAt(fila, 1).toString() + " " + this.vista.tablalistado.getValueAt(fila, 2).toString();

                //frmVenta.txtidcliente.setText(cod);
                //frmVenta.txtcliente.setText(valor);
                this.vista.dispose();

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
