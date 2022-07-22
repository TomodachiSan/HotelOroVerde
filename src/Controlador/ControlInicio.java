package Controlador;

import Vistas.*;
import java.awt.Dimension;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class ControlInicio implements ActionListener, MouseListener {

    private frminicio vista;

    public ControlInicio(frminicio vista) {
        this.vista = vista;
        this.ComponentesInicio();
    }

    public void ComponentesInicio() {
        this.vista.setExtendedState(frminicio.MAXIMIZED_BOTH);
        this.vista.setTitle("Aplicativo de alquiler de habitaciones - Hotel Oro Verde");
        // this.vista.jMenuItem5.setVisible(false);
        this.vista.aboutMenuItem.addActionListener(this);
        this.vista.jMenuItem1.addActionListener(this);
        this.vista.jMenuItem6.addActionListener(this);
        this.vista.jMenuItem8.addActionListener(this);
        this.vista.jMenuItem2.addActionListener(this);
        this.vista.jMenuItem3.addActionListener(this);
        this.vista.jMenuItem4.addActionListener(this);
        this.vista.jMenuItem7.addActionListener(this);
        this.vista.cutMenuItem.addActionListener(this);
        this.vista.copyMenuItem.addActionListener(this);
        this.vista.contentMenuItem.addActionListener(this);
        this.vista.jMenuItem5.addActionListener(this);
        this.vista.mnusalir.addMouseListener(this);
        // aumentar cargar imagen
        this.vista.jMenuItem5.setVisible(false);

    }

    @Override
    public void actionPerformed(ActionEvent arg0) {

        if (arg0.getSource() == vista.aboutMenuItem) {
            aboutMenuItemActionPerformed();
        }

        if (arg0.getSource() == vista.jMenuItem1) {
            jMenuItem1ActionPerformed();
        }

        if (arg0.getSource() == vista.jMenuItem6) {
            jMenuItem6ActionPerformed();
        }
        
        if (arg0.getSource() == vista.jMenuItem8) {
            jMenuItem8ActionPerformed();
        }

        if (arg0.getSource() == vista.jMenuItem2) {
            jMenuItem2ActionPerformed();
        }

        if (arg0.getSource() == vista.jMenuItem3) {
            jMenuItem3ActionPerformed();
        }

        if (arg0.getSource() == vista.jMenuItem4) {
            jMenuItem4ActionPerformed();
        }

        if (arg0.getSource() == vista.jMenuItem7) {
            jMenuItem7ActionPerformed();
        }

        if (arg0.getSource() == vista.cutMenuItem) {
            cutMenuItemActionPerformed();
        }
        if (arg0.getSource() == vista.copyMenuItem) {
            copyMenuItemActionPerformed();
        }

        if (arg0.getSource() == vista.contentMenuItem) {
            contentMenuItemActionPerformed();
        }

    }

    private void aboutMenuItemActionPerformed() {
        frmcliente form = new frmcliente();
        this.vista.escritorio.add(form);
        form.toFront();
        //Centrar en el escritorio
        Dimension desktopSize = vista.escritorio.getSize();
        Dimension FrameSize = form.getSize();
        form.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        form.setVisible(true);
        ControlCliente control = new ControlCliente(form);
    }

    private void jMenuItem1ActionPerformed() {
        /*frmVenta form = new frmVenta();
        this.vista.escritorio.add(form);
        form.toFront();
        //Centrar en el escritorio
        Dimension desktopSize = vista.escritorio.getSize();
        Dimension FrameSize = form.getSize();
        form.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        form.setVisible(true);

        ControlVenta control = new ControlVenta(form);
        control.idusuario = Integer.parseInt(this.vista.lblidpersona.getText());
        control.vista.txtidtrabajador.setText(this.vista.lblidpersona.getText());
        control.vista.txttrabajador.setText(this.vista.lblnombre.getText() + " " + this.vista.lblapaterno.getText());*/
        frmDetalle_Venta form = new frmDetalle_Venta();
        this.vista.escritorio.add(form);
        form.toFront();
        //Centrar en el escritorio
        Dimension desktopSize = vista.escritorio.getSize();
        Dimension FrameSize = form.getSize();
        form.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        form.setVisible(true);
        ControlDetalle_Venta control = new ControlDetalle_Venta(form);
        //control.idTrab = this.vista.lblidpersona.getText();
        frmDetalle_Venta.txtidtrabajador.setText(this.vista.lblidpersona.getText());

    }
    
    private void jMenuItem8ActionPerformed() {
        frmVenta form = new frmVenta();
        this.vista.escritorio.add(form);
        form.toFront();
        //Centrar en el escritorio
        Dimension desktopSize = vista.escritorio.getSize();
        Dimension FrameSize = form.getSize();
        form.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        form.setVisible(true);

        ControlVenta control = new ControlVenta(form);
    }

    private void jMenuItem6ActionPerformed() {
        // TODO add your handling code here:
        FrmConsultaAlquilerTrabajador form = new FrmConsultaAlquilerTrabajador();
        this.vista.escritorio.add(form);
        form.toFront();
        //Centrar en el escritorio
        Dimension desktopSize = vista.escritorio.getSize();
        Dimension FrameSize = form.getSize();
        form.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        form.setVisible(true);
        ControlConsultaAlquilerTrabajador control = new ControlConsultaAlquilerTrabajador(form);
    }

    private void jMenuItem2ActionPerformed() {
        // TODO add your handling code here:
        frmtrabajador form = new frmtrabajador();
        this.vista.escritorio.add(form);
        form.toFront();
        //Centrar en el escritorio
        Dimension desktopSize = vista.escritorio.getSize();
        Dimension FrameSize = form.getSize();
        form.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        form.setVisible(true);
        ControlTrabajador control = new ControlTrabajador(form);
    }

    private void jMenuItem3ActionPerformed() {
        // TODO add your handling code here:
        frmlogin form = new frmlogin();
        this.vista.escritorio.add(form);
        form.toFront();
        //Centrar en el escritorio
        Dimension desktopSize = vista.escritorio.getSize();
        Dimension FrameSize = form.getSize();
        form.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        form.setVisible(true);
        ControlLogin control = new ControlLogin(form);
    }

    private void jMenuItem4ActionPerformed() {
        // TODO add your handling code here:
        frmperfildatos form = new frmperfildatos();
        this.vista.escritorio.add(form);
        form.toFront();
        //Centrar en el escritorio
        Dimension desktopSize = vista.escritorio.getSize();
        Dimension FrameSize = form.getSize();
        form.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        form.setVisible(true);
        form.txtidpersona.setText(this.vista.lblidpersona.getText());
        ControlPerfilDatos control = new ControlPerfilDatos(form);
    }

    private void jMenuItem7ActionPerformed() {
        // TODO add your handling code here:
        frmperfilcontra form = new frmperfilcontra();
        this.vista.escritorio.add(form);
        form.toFront();
        //Centrar en el escritorio
        Dimension desktopSize = vista.escritorio.getSize();
        Dimension FrameSize = form.getSize();
        form.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        form.setVisible(true);
        form.txtidpersona.setText(this.vista.lblidpersona.getText());
        ControlPerfilContra control = new ControlPerfilContra(form);
    }

    private void cutMenuItemActionPerformed() {
        // TODO add your handling code here:
        frmhabitacion form = new frmhabitacion();
        this.vista.escritorio.add(form);
        form.toFront();
        //Centrar en el escritorio
        Dimension desktopSize = vista.escritorio.getSize();
        Dimension FrameSize = form.getSize();
        form.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        form.setVisible(true);
        ControlHabitacion control = new ControlHabitacion(form);
    }

    private void copyMenuItemActionPerformed() {
        // TODO add your handling code here:
        frmproducto form = new frmproducto();
        this.vista.escritorio.add(form);
        form.toFront();
        //Centrar en el escritorio
        Dimension desktopSize = vista.escritorio.getSize();
        Dimension FrameSize = form.getSize();
        form.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        form.setVisible(true);
        ControlProducto control = new ControlProducto(form);
    }

    private void contentMenuItemActionPerformed() {
        // TODO add your handling code here:
        frmalquiler form = new frmalquiler();
        this.vista.escritorio.add(form);
        form.toFront();
        //Centrar en el escritorio
        Dimension desktopSize = vista.escritorio.getSize();
        Dimension FrameSize = form.getSize();
        form.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        form.setVisible(true);
        frmalquiler.txtidtrabajador.setText(this.vista.lblidpersona.getText());
        //frmalquiler.txttrabajador.setText(this.vista.lblnombre.getText() + " " + this.vista.lblapaterno.getText());

        ControlAlquiler control = new ControlAlquiler(form);
        control.idusuario = Integer.parseInt(this.vista.lblidpersona.getText());
        control.nomusuario =  this.vista.lblnombre.getText() + " " + this.vista.lblapaterno.getText();
    }

    private void mnusalirActionPerformed() {
        System.exit(0);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == vista.mnusalir) {
            mnusalirActionPerformed();
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

    private void setLocationRelativeTo(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void setResizable(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void setSize(int i, int i0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void repaint() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
