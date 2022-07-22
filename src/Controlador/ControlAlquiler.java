package Controlador;

import static Controlador.ControlPago.cliente;
import static Controlador.ControlPago.habitacion;
import static Controlador.ControlPago.idhabitacion;
import static Controlador.ControlPago.idalquiler;
import static Controlador.ControlPago.totalalquiler;
import DAO.fconsumo;
import DAO.fhabitacion;
import DAO.falquiler;
import DAO.funciones;
import Modelo.vhabitacion;
import Modelo.valquiler;
import Vistas.frmConsumo;
import Vistas.frmPago;
import Vistas.frmalquiler;
import Vistas.frminicio;
import Vistas.frmalquiler;
import static Vistas.frmalquiler.txtcliente;
import static Vistas.frmalquiler.txtidcliente;
import static Vistas.frmalquiler.txtidhabitacion;
import static Vistas.frmalquiler.txtidtrabajador;
import static Vistas.frmalquiler.txtnumero;
import static Vistas.frmalquiler.txttrabajador;
import Vistas.frmvistacliente;
import Vistas.frmvistahabitacion;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ControlAlquiler implements ActionListener, MouseListener {

    private frmalquiler vista;

    public ControlAlquiler(frmalquiler vista) {
        this.vista = vista;
        ComponentesInicio();
    }

    public void ComponentesInicio() {
        this.vista.btnnuevo.addActionListener(this);
        this.vista.btncancelar.addActionListener(this);
        this.vista.btnguardar.addActionListener(this);
        this.vista.btnnuevo.addActionListener(this);
        this.vista.btnverconsumo.addActionListener(this);
        this.vista.btnsalir.addActionListener(this);
        this.vista.btnactFechaSal.addActionListener(this);
        this.vista.btnCalcCostoTotal.addActionListener(this);
        this.vista.tablalistado.addMouseListener(this);
        this.vista.btnbuscahabitacion.addActionListener(this);
        this.vista.btnbuscacliente.addActionListener(this);
        this.vista.btnbuscar.addActionListener(this);
        this.vista.btnrealizarpagos.addActionListener(this);
        mostrar("");
        inhabilitar();

    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if (arg0.getSource() == vista.btnbuscar) {
            btnbuscarActionPerformed(arg0);
        }

        if (arg0.getSource() == vista.btnverconsumo) {
            btnverconsumoActionPerformed(arg0);
        }

        if (arg0.getSource() == vista.btnrealizarpagos) {
            btnrealizarpagosActionPerformed(arg0);
        }

        if (arg0.getSource() == vista.btnnuevo) {
            btnnuevoActionPerformed(arg0);
        }

        if (arg0.getSource() == vista.btncancelar) {
            btncancelarActionPerformed(arg0);
        }

        if (arg0.getSource() == vista.btnguardar) {
            btnguardarActionPerformed(arg0);
        }

        if (arg0.getSource() == vista.btnCalcCostoTotal) {
            btnCalcCostoTotalActionPerformed(arg0);
        }

        if (arg0.getSource() == vista.btnactFechaSal) {
            btnactFechaSalActionPerformed(arg0);
        }

        if (arg0.getSource() == vista.btnsalir) {
            btnsalirActionPerformed(arg0);
        }
        if (arg0.getSource() == vista.btnbuscahabitacion) {
            btnbuscahabitacionActionPerformed(arg0);
        }
        if (arg0.getSource() == vista.btnbuscacliente) {
            btnbuscaclienteActionPerformed(arg0);
        }
    }

    private String accion = "guardar";
    public static int idusuario;
    public static String nomusuario;

    void ocultar_columnas() {
        this.vista.tablalistado.getColumnModel().getColumn(0).setMaxWidth(0);
        this.vista.tablalistado.getColumnModel().getColumn(0).setMinWidth(0);
        this.vista.tablalistado.getColumnModel().getColumn(0).setPreferredWidth(0);

        this.vista.tablalistado.getColumnModel().getColumn(1).setMaxWidth(0);
        this.vista.tablalistado.getColumnModel().getColumn(1).setMinWidth(0);
        this.vista.tablalistado.getColumnModel().getColumn(1).setPreferredWidth(0);

        this.vista.tablalistado.getColumnModel().getColumn(3).setMaxWidth(0);
        this.vista.tablalistado.getColumnModel().getColumn(3).setMinWidth(0);
        this.vista.tablalistado.getColumnModel().getColumn(3).setPreferredWidth(0);

        this.vista.tablalistado.getColumnModel().getColumn(5).setMaxWidth(0);
        this.vista.tablalistado.getColumnModel().getColumn(5).setMinWidth(0);
        this.vista.tablalistado.getColumnModel().getColumn(5).setPreferredWidth(0);
    }

    void inhabilitar() {
        this.vista.txtidalquiler.setVisible(false);
        txtidhabitacion.setVisible(false);
        txtidcliente.setVisible(false);
        txtidtrabajador.setVisible(false);

        txtnumero.setEnabled(false);
        txtcliente.setEnabled(false);
        txttrabajador.setEnabled(false);
        this.vista.dcfecha_ingresa.setEnabled(false);
        this.vista.dcfecha_salida.setEnabled(false);
        this.vista.txtHoraIngreso.setEnabled(false);
        this.vista.txtHoraSalida.setEnabled(false);
        this.vista.txtcosto_alojamiento.setEnabled(false);
        this.vista.txtcosto_Dia.setEnabled(false);

        this.vista.btnguardar.setEnabled(false);
        this.vista.btnCalcCostoTotal.setEnabled(false);
        this.vista.btncancelar.setEnabled(false);
        this.vista.btnbuscacliente.setEnabled(false);
        this.vista.btnbuscahabitacion.setEnabled(false);
        this.vista.btnactFechaSal.setEnabled(false);
        this.vista.btnactFechaSal.setVisible(false);
        this.vista.lblpagofaltante.setVisible(false);
        this.vista.txtpago_faltante.setEnabled(false);
        this.vista.txtpago_faltante.setVisible(false);

        this.vista.txtidalquiler.setText("");
        txtidcliente.setText("");
        txtnumero.setText("");
        txtidhabitacion.setText("");
        txtcliente.setText("");
        txttrabajador.setText("");

        this.vista.dcfecha_ingresa.setCalendar(null);
        this.vista.dcfecha_salida.setCalendar(null);
        this.vista.txtHoraIngreso.setText("");
        this.vista.txtHoraSalida.setText("");
        this.vista.txtcosto_alojamiento.setText("");
        this.vista.txtcosto_Dia.setText("");

    }

    void habilitar() {
        this.vista.txtidalquiler.setVisible(false);
        txtidhabitacion.setVisible(false);
        txtidcliente.setVisible(false);
        txtidtrabajador.setVisible(false);

        txtnumero.setEnabled(false);
        txtcliente.setEnabled(false);
        txttrabajador.setEnabled(false);

        this.vista.dcfecha_ingresa.setEnabled(false);
        this.vista.dcfecha_salida.setEnabled(true);
        this.vista.txtHoraIngreso.setEnabled(false);
        this.vista.txtHoraSalida.setEnabled(false);
        this.vista.txtcosto_alojamiento.setEnabled(false);
        this.vista.txtcosto_Dia.setEnabled(false);

        this.vista.btnguardar.setEnabled(true);
        this.vista.btncancelar.setEnabled(true);
        this.vista.btnCalcCostoTotal.setEnabled(true);
        this.vista.btnbuscacliente.setEnabled(true);
        this.vista.btnbuscahabitacion.setEnabled(true);
        this.vista.btnactFechaSal.setEnabled(false);
        this.vista.btnactFechaSal.setVisible(false);
        this.vista.lblpagofaltante.setVisible(false);
        this.vista.txtpago_faltante.setEnabled(false);
        this.vista.txtpago_faltante.setVisible(false);

        this.vista.txtidalquiler.setText("");
        txtidcliente.setText("");
        txtidhabitacion.setText("");
        txtidtrabajador.setText("");
        txtnumero.setText("");
        txtcliente.setText("");
        txttrabajador.setText("");
        this.vista.txtcosto_alojamiento.setText("");
        this.vista.txtcosto_Dia.setText("");

        funciones func = new funciones();
        String hora;
        String fecha;
        fecha = func.getFechaActual();
        hora = func.getHoraActual();
        this.vista.txtHoraIngreso.setText(hora);
        this.vista.txtHoraSalida.setText(hora);
        this.vista.txttrabajador.setText(nomusuario);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        Calendar clndr = Calendar.getInstance();;
        try {
            date = sdf.parse(fecha);
            clndr.setTime(date);
        } catch (ParseException ex) {
            JOptionPane.showConfirmDialog(null, ex);
        }
        //colocar la fecha actual en el formato de fecha
        this.vista.dcfecha_ingresa.setCalendar(clndr);

    }

    void mostrar(String buscar) {
        try {
            DefaultTableModel modelo;
            falquiler func = new falquiler();
            modelo = func.mostrar(buscar);

            this.vista.tablalistado.setModel(modelo);
            ocultar_columnas();
            this.vista.lbltotalregistros.setText("Total Registros " + Integer.toString(func.totalregistros));

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
        }
    }

    private void txtidhabitacionActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        //txtidhabitacion.transferFocus();
    }

    private void txtcosto_alojamientoActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        this.vista.txtcosto_Dia.transferFocus();
    }

    private void btnnuevoActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        habilitar();
        this.vista.btnguardar.setText("Guardar");
        accion = "guardar";
    }

    private void btnCalcCostoTotalActionPerformed(java.awt.event.ActionEvent evt) {
        if (this.vista.dcfecha_salida.getCalendar() == null) {
            JOptionPane.showMessageDialog(null, "COLOQUE FECHA DE SALIDA");
        } else if (this.vista.txtcosto_Dia.getText().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(null, "SELECCIONE UNA HABITACIÓN");
        } else {
            Date fecha1 = this.vista.dcfecha_ingresa.getDate();
            Date fecha2 = this.vista.dcfecha_salida.getDate();
            if (!fecha1.before(fecha2)) {
                JOptionPane.showMessageDialog(null, "LA FECHA DE INGRESO ES MAYOR QUE LA FECHA DE SALIDA, CORRIJALA");
            } else {

                LocalTime horaIng = LocalTime.parse(this.vista.txtHoraIngreso.getText());
                LocalTime horaSal = LocalTime.parse(this.vista.txtHoraSalida.getText());
                Integer Comparadador = horaIng.compareTo(horaSal);
                if (Comparadador < 0) {
                    fecha2 = new Date(fecha2.getTime() + (1000 * 60 * 60 * 24));
                }

                long fecha1aux = fecha1.getTime();
                long fecha2aux = fecha2.getTime();

                long fechaDifaux = Math.abs(fecha2aux - fecha1aux);

                long fechaDif = TimeUnit.DAYS.convert(fechaDifaux, TimeUnit.MILLISECONDS);
                Double DifFechas = (double) fechaDif;
                Double costoDia = Double.parseDouble(this.vista.txtcosto_Dia.getText());
                Double CostoAloj = costoDia * DifFechas;
                if (!this.vista.txtidalquiler.getText().equals("")) {
                    Double costo_act = Double.parseDouble(this.vista.txtcosto_alojamiento.getText());
                    Double PagoFaltante = CostoAloj - costo_act;
                    this.vista.txtpago_faltante.setText(PagoFaltante.toString());
                }

                this.vista.txtcosto_alojamiento.setText(CostoAloj.toString());

            }
        }
    }

    private void btnactFechaSalActionPerformed(java.awt.event.ActionEvent evt) {

        funciones func = new funciones();
        String hora;
        String fecha;
        fecha = func.getFechaActual();
        hora = func.getHoraActual();
        this.vista.txtHoraSalida.setText(hora);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        Calendar clndr = Calendar.getInstance();;
        try {
            date = sdf.parse(fecha);
            clndr.setTime(date);
        } catch (ParseException ex) {
            JOptionPane.showConfirmDialog(null, ex);
        }
        //colocar la fecha actual en el formato de fecha
        this.vista.dcfecha_salida.setCalendar(clndr);

    }

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {

        if (txtidhabitacion.getText().trim().length() == 0 || txtidcliente.getText().trim().length() == 0
                || vista.txtcosto_alojamiento.getText().trim().length() == 0 || this.vista.dcfecha_salida.getCalendar() == null) {
            JOptionPane.showMessageDialog(null, "Los Campos no estan llenados completamente",
                    "ERROR AL CARGAR", JOptionPane.ERROR_MESSAGE);
        } else {
            valquiler dts = new valquiler();
            falquiler func = new falquiler();

            dts.setIdhabitacion(Integer.parseInt(txtidhabitacion.getText()));
            dts.setIdcliente(Integer.parseInt(txtidcliente.getText()));
            dts.setIdtrabajador(idusuario);

            Calendar cal1 = vista.dcfecha_ingresa.getCalendar();
            Calendar cal2 = vista.dcfecha_salida.getCalendar();
            func.Fecha(dts, cal1, cal2);
            dts.setHora_ingresa(vista.txtHoraIngreso.getText());
            dts.setHora_salida(vista.txtHoraSalida.getText());
            dts.setCosto_Dia(Double.parseDouble(this.vista.txtcosto_Dia.getText()));
            dts.setCosto_alojamiento(Double.parseDouble(vista.txtcosto_alojamiento.getText()));

            if (accion.equals("guardar")) {
                if (func.insertar(dts)) {
                    JOptionPane.showMessageDialog(null, "El alquiler fue registrado satisfactoriamente");
                    //ocupamos la Habitación alquilada
                    fhabitacion func3 = new fhabitacion();
                    vhabitacion dts3 = new vhabitacion();

                    dts3.setIdhabitacion(Integer.parseInt(txtidhabitacion.getText()));
                    func3.ocupar(dts3);

                    mostrar("");
                    inhabilitar();
                }

            } else if (accion.equals("editar")) {
                dts.setIdalquiler(Integer.parseInt(this.vista.txtidalquiler.getText()));
                if (func.editar2(dts)) {
                    JOptionPane.showMessageDialog(null, "El alquiler fue editado satisfactoriamente");
                    /*fhabitacion func4 = new fhabitacion();
                vhabitacion dts4 = new vhabitacion();

                dts4.setIdhabitacion(Integer.parseInt(txtidhabitacion.getText()));
                func4.desocupar(dts4);*/

                    mostrar("");
                    inhabilitar();
                }
            }
        }

    }

    private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        inhabilitar();
    }

    private void tablalistadoMouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        this.vista.btnguardar.setText("Editar");
        habilitar();
        accion = "editar";
        this.vista.btnactFechaSal.setEnabled(true);

        this.vista.btnactFechaSal.setVisible(true);
        this.vista.lblpagofaltante.setVisible(true);

        this.vista.txtpago_faltante.setVisible(true);
        //this.vista.txtpago_faltante.setEnabled(false);

        vista.dcfecha_salida.setEnabled(false);

        int fila = this.vista.tablalistado.rowAtPoint(evt.getPoint());

        this.vista.txtidalquiler.setText(this.vista.tablalistado.getValueAt(fila, 0).toString());
        txtidhabitacion.setText(this.vista.tablalistado.getValueAt(fila, 1).toString());
        txtnumero.setText(this.vista.tablalistado.getValueAt(fila, 2).toString());
        txtidcliente.setText(this.vista.tablalistado.getValueAt(fila, 3).toString());
        txtcliente.setText(this.vista.tablalistado.getValueAt(fila, 4).toString());
        txtidtrabajador.setText(this.vista.tablalistado.getValueAt(fila, 5).toString());
        txttrabajador.setText(this.vista.tablalistado.getValueAt(fila, 6).toString());
        String fechaIngr = this.vista.tablalistado.getValueAt(fila, 7).toString();
        this.vista.txtHoraIngreso.setText(this.vista.tablalistado.getValueAt(fila, 8).toString());
        String fechaSal = this.vista.tablalistado.getValueAt(fila, 9).toString();
        this.vista.txtHoraSalida.setText(this.vista.tablalistado.getValueAt(fila, 10).toString());
        this.vista.txtcosto_Dia.setText(this.vista.tablalistado.getValueAt(fila, 11).toString());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1;
        Calendar clndr1 = Calendar.getInstance();;
        Date date2;
        Calendar clndr2 = Calendar.getInstance();;
        try {
            date1 = sdf.parse(fechaIngr);
            clndr1.setTime(date1);
            date2 = sdf.parse(fechaSal);
            clndr2.setTime(date2);
        } catch (ParseException ex) {
            JOptionPane.showConfirmDialog(null, ex);
        }
        vista.dcfecha_ingresa.setCalendar(clndr1);
        vista.dcfecha_salida.setCalendar(clndr2);

        this.vista.txtcosto_alojamiento.setText(this.vista.tablalistado.getValueAt(fila, 12).toString());

    }

    private void btnbuscarActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        mostrar(this.vista.txtbuscar.getText());
    }

    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        this.vista.dispose();
    }

    private void btnbuscahabitacionActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        frmvistahabitacion form = new frmvistahabitacion();
        form.toFront();
        //Centrar en el escritorio
        Dimension desktopSize = frminicio.escritorio.getSize();
        Dimension FrameSize = form.getSize();
        form.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        form.setVisible(true);
        ControlVistahabitacion control = new ControlVistahabitacion(form);
    }

    private void btnbuscaclienteActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        frmvistacliente form = new frmvistacliente();
        form.toFront();
        form.setVisible(true);

        ControlVistaCliente control = new ControlVistaCliente(form);
        control.op = 0;
    }

    private void btnverconsumoActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        int fila = this.vista.tablalistado.getSelectedRow();

        if (fila >= 0) {
            frmConsumo form = new frmConsumo();
            frminicio.escritorio.add(form);
            form.toFront();
            //Centrar en el escritorio
            Dimension desktopSize = frminicio.escritorio.getSize();
            Dimension FrameSize = form.getSize();
            form.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
            form.setVisible(true);
            ControlConsumo.cliente = this.vista.tablalistado.getValueAt(fila, 4).toString();
            ControlConsumo.idalquiler = this.vista.tablalistado.getValueAt(fila, 0).toString();
            ControlConsumo control = new ControlConsumo(form);
        }

    }

    private void btnrealizarpagosActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        int fila = this.vista.tablalistado.getSelectedRow();

        if (fila >= 0) {
            frmPago form = new frmPago();
            frminicio.escritorio.add(form);
            form.toFront();
            //Centrar en el escritorio
            Dimension desktopSize = frminicio.escritorio.getSize();
            Dimension FrameSize = form.getSize();
            form.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
            form.setVisible(true);

            ControlPago.idalquiler = this.vista.tablalistado.getValueAt(fila, 0).toString();
            ControlPago.cliente = this.vista.tablalistado.getValueAt(fila, 4).toString();
            ControlPago.totalalquiler = Double.parseDouble(this.vista.tablalistado.getValueAt(fila, 12).toString());
            ControlPago.idhabitacion = this.vista.tablalistado.getValueAt(fila, 1).toString();
            ControlPago.habitacion = this.vista.tablalistado.getValueAt(fila, 2).toString();
            ControlPago control = new ControlPago(form);
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

}
