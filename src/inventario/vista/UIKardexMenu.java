package inventario.vista;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import inventario.controlador.IKardexMenu;


public class UIKardexMenu extends javax.swing.JFrame
{
    private IKardexMenu interfaz;

    public UIKardexMenu(IKardexMenu interfaz)
    {
        initComponents();
        this.setVisible(true);
        this.setTitle("Sistema de Control de Inventarios UTP-SJL");
        setLocationRelativeTo(null);
        
        this.interfaz = interfaz;
        interfaz.cargar(
                        this.txtNombre,
                        this.txtDni,
                        this.lblPermisos,
                        this.btnUsuario,
                        this.btnExistencia,
                        this.btnEntrada,
                        this.btnSalida
        );
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnAlmacen = new javax.swing.JButton();
        btnUnidad = new javax.swing.JButton();
        btnUsuario = new javax.swing.JButton();
        btnDocumento = new javax.swing.JButton();
        btnProducto = new javax.swing.JButton();
        btnKardex = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        btnEntrada = new javax.swing.JButton();
        btnSalida = new javax.swing.JButton();
        btnExistencia = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtDni = new javax.swing.JTextField();
        lblPermisos = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        itmConfig = new javax.swing.JMenuItem();
        itmCerrar = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        itmAcerca = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(0));

        btnAlmacen.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnAlmacen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/scik/recursos/almacen.png"))); // NOI18N
        btnAlmacen.setText("PROVEEDOR");
        btnAlmacen.setBorder(javax.swing.BorderFactory.createBevelBorder(0));
        btnAlmacen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlmacenActionPerformed(evt);
            }
        });

        btnUnidad.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnUnidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/scik/recursos/unidad.png"))); // NOI18N
        btnUnidad.setText("UNIDAD");
        btnUnidad.setBorder(javax.swing.BorderFactory.createBevelBorder(0));
        btnUnidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUnidadActionPerformed(evt);
            }
        });

        btnUsuario.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnUsuario.setIcon(new javax.swing.ImageIcon("C:\\Users\\Administrador\\Documents\\1000-iconos-formato-png\\user_suit.png")); // NOI18N
        btnUsuario.setText("USUARIO");
        btnUsuario.setBorder(javax.swing.BorderFactory.createBevelBorder(0));
        btnUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuarioActionPerformed(evt);
            }
        });

        btnDocumento.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnDocumento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/scik/recursos/documento.png"))); // NOI18N
        btnDocumento.setText("DOCUMENTO");
        btnDocumento.setBorder(javax.swing.BorderFactory.createBevelBorder(0));
        btnDocumento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDocumentoActionPerformed(evt);
            }
        });

        btnProducto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/scik/recursos/producto.png"))); // NOI18N
        btnProducto.setText("PRODUCTO");
        btnProducto.setBorder(javax.swing.BorderFactory.createBevelBorder(0));
        btnProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductoActionPerformed(evt);
            }
        });

        btnKardex.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnKardex.setIcon(new javax.swing.ImageIcon(getClass().getResource("/scik/recursos/kardex.png"))); // NOI18N
        btnKardex.setText("REPORTE");
        btnKardex.setBorder(javax.swing.BorderFactory.createBevelBorder(0));
        btnKardex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKardexActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnUnidad, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnKardex, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUnidad, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnKardex, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 620, 1070, 80));

        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(0));

        btnEntrada.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnEntrada.setIcon(new javax.swing.ImageIcon("C:\\Users\\Administrador\\Documents\\1000-iconos-formato-png\\application_form_magnify.png")); // NOI18N
        btnEntrada.setText("ENTRADA DE PRODUCTOS POR FECHA");
        btnEntrada.setBorder(new javax.swing.border.SoftBevelBorder(0));
        btnEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntradaActionPerformed(evt);
            }
        });

        btnSalida.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnSalida.setIcon(new javax.swing.ImageIcon("C:\\Users\\Administrador\\Documents\\1000-iconos-formato-png\\application_form_magnify.png")); // NOI18N
        btnSalida.setText("SALIDA DE PRODUCTOS POR FECHA");
        btnSalida.setBorder(new javax.swing.border.SoftBevelBorder(0));
        btnSalida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalidaActionPerformed(evt);
            }
        });

        btnExistencia.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnExistencia.setIcon(new javax.swing.ImageIcon("C:\\Users\\Administrador\\Documents\\1000-iconos-formato-png\\zoom.png")); // NOI18N
        btnExistencia.setText("EXISTENCIA DE PRODUCTOS");
        btnExistencia.setBorder(new javax.swing.border.SoftBevelBorder(0));
        btnExistencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExistenciaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEntrada, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnExistencia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSalida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnExistencia, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 250, 190));

        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(0));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Bienvenido(a):");

        txtNombre.setEditable(false);
        txtNombre.setColumns(20);

        txtDni.setEditable(false);

        lblPermisos.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblPermisos.setForeground(new java.awt.Color(102, 102, 102));
        lblPermisos.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPermisos.setText(" ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblPermisos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtDni)
                    .addComponent(lblPermisos, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 249, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\Administrador\\Desktop\\imagenes\\wall.jpg")); // NOI18N
        jLabel2.setText("jLabel2");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, -1));

        jMenu1.setText("Archivo");

        itmConfig.setIcon(new javax.swing.ImageIcon("C:\\Users\\Administrador\\Documents\\1000-iconos-formato-png\\cog.png")); // NOI18N
        itmConfig.setText("Configuración");
        itmConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmConfigActionPerformed(evt);
            }
        });
        jMenu1.add(itmConfig);

        itmCerrar.setIcon(new javax.swing.ImageIcon("C:\\Users\\Administrador\\Documents\\1000-iconos-formato-png\\user_go.png")); // NOI18N
        itmCerrar.setText("Cerrar Sesión");
        itmCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmCerrarActionPerformed(evt);
            }
        });
        jMenu1.add(itmCerrar);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Ayuda");

        itmAcerca.setIcon(new javax.swing.ImageIcon("C:\\Users\\Administrador\\Documents\\iconos-16x16-gif-transparente-1\\info.gif")); // NOI18N
        itmAcerca.setText("Acerca de");
        itmAcerca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmAcercaActionPerformed(evt);
            }
        });
        jMenu2.add(itmAcerca);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAlmacenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlmacenActionPerformed
        interfaz.almacen();
    }//GEN-LAST:event_btnAlmacenActionPerformed

    private void btnKardexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKardexActionPerformed
        interfaz.kardex();
    }//GEN-LAST:event_btnKardexActionPerformed

    private void btnDocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDocumentoActionPerformed
        interfaz.documento();
    }//GEN-LAST:event_btnDocumentoActionPerformed

    private void btnProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductoActionPerformed
        interfaz.producto();
    }//GEN-LAST:event_btnProductoActionPerformed

    private void btnUnidadActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnUnidadActionPerformed
    {//GEN-HEADEREND:event_btnUnidadActionPerformed
        interfaz.unidad();
    }//GEN-LAST:event_btnUnidadActionPerformed

    private void btnExistenciaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnExistenciaActionPerformed
    {//GEN-HEADEREND:event_btnExistenciaActionPerformed
        interfaz.existenciaProducto();
    }//GEN-LAST:event_btnExistenciaActionPerformed

    private void btnSalidaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnSalidaActionPerformed
    {//GEN-HEADEREND:event_btnSalidaActionPerformed
        interfaz.salida();
    }//GEN-LAST:event_btnSalidaActionPerformed

    private void btnEntradaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnEntradaActionPerformed
    {//GEN-HEADEREND:event_btnEntradaActionPerformed
        interfaz.entrada();
    }//GEN-LAST:event_btnEntradaActionPerformed

    private void itmCerrarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_itmCerrarActionPerformed
    {//GEN-HEADEREND:event_itmCerrarActionPerformed
        interfaz.cerrarSesion();
    }//GEN-LAST:event_itmCerrarActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt)//GEN-FIRST:event_formWindowClosing
    {//GEN-HEADEREND:event_formWindowClosing
        interfaz.cerrarSesion();
    }//GEN-LAST:event_formWindowClosing

    private void btnUsuarioActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnUsuarioActionPerformed
    {//GEN-HEADEREND:event_btnUsuarioActionPerformed
        interfaz.usuario();
    }//GEN-LAST:event_btnUsuarioActionPerformed

    private void itmAcercaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_itmAcercaActionPerformed
    {//GEN-HEADEREND:event_itmAcercaActionPerformed
        
       JOptionPane.showMessageDialog(null, "Sistema de Control de Inventarios\nUTP - 2019\nTOMODACHI", "Acerca de", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_itmAcercaActionPerformed

    private void itmConfigActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_itmConfigActionPerformed
    {//GEN-HEADEREND:event_itmConfigActionPerformed
        interfaz.configuracion();
    }//GEN-LAST:event_itmConfigActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlmacen;
    private javax.swing.JButton btnDocumento;
    private javax.swing.JButton btnEntrada;
    private javax.swing.JButton btnExistencia;
    private javax.swing.JButton btnKardex;
    private javax.swing.JButton btnProducto;
    private javax.swing.JButton btnSalida;
    private javax.swing.JButton btnUnidad;
    private javax.swing.JButton btnUsuario;
    private javax.swing.JMenuItem itmAcerca;
    private javax.swing.JMenuItem itmCerrar;
    private javax.swing.JMenuItem itmConfig;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lblPermisos;
    private javax.swing.JTextField txtDni;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
