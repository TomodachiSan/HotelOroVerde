package inventario.controlador;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import inventario.vista.UIKardexMenu;
import inventario.controlador.almacen.CAlmacen;
import inventario.controlador.documento.CDocumento;
import inventario.controlador.unidad.CUnidad;
import inventario.controlador.usuario.CUsuario;
import static inventario.KardexIni.user;
import inventario.controlador.consulta.CEntrada;
import inventario.controlador.consulta.CExistencia;
import inventario.controlador.consulta.CSalida;
import inventario.controlador.kardex.CKardex;
import inventario.controlador.producto.CProducto;




public class CKardexMenu implements IKardexMenu
{
    private UIKardexMenu ventana;
    
    public CKardexMenu()
    {
        ventana = new UIKardexMenu(this);
    }
    
    @Override
    public void almacen()
    {
        new CAlmacen();
        ventana.dispose();
    }
    @Override
    public void cargar(JTextField txtNombre, JTextField txtDni, JLabel lblPermisos, JButton btnUsuario, JButton btnExistencia, JButton btnEntrada, JButton btnSalida)
    {
        txtNombre.setText(user.getUsrNom() + " " + user.getUsrApe());
        txtDni.setText("DNI Nº " + user.getUsrDni());
        String permiso = "";
        if(user.getUsrPer().equals("1"))
            permiso = "Administrador";
        else
            permiso = "Usuario";
        lblPermisos.setText(permiso);
        if(user.getUsrPer().equals("0"))
        {
            btnUsuario.setEnabled(false);
            btnExistencia.setEnabled(false);
            btnEntrada.setEnabled(false);
            btnSalida.setEnabled(false);
        }
    }
    
    @Override
    public void cerrarSesion()
    {
        new CLogin();
        ventana.dispose();
    }
    
    @Override
    public void usuario()
    {
        new CUsuario();
        ventana.dispose();
    }
    
    @Override
    public void unidad()
    {
        new CUnidad();
        ventana.dispose();
    }
    
    @Override
    public void documento()
    {
        new CDocumento();
        ventana.dispose();
    }
    
    @Override
    public void producto()
    {
        new CProducto();
        ventana.dispose();
    }
    
    @Override
    public void configuracion()
    {
        new CConfiguracion(false);
        ventana.dispose();
    }
    
    @Override
    public void kardex()
    {
        new CKardex();
        ventana.dispose();
    }

    @Override
    public void existenciaProducto()
    {
        new CExistencia();
        ventana.dispose();
    }

    @Override
    public void salida()
    {
        new CSalida();
        ventana.dispose();
    }

    @Override
    public void entrada()
    {
        new CEntrada();
        ventana.dispose();
    }
}
