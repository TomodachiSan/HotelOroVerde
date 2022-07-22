package inventario.controlador;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import static inventario.KardexIni.user;
import static inventario.KardexIni.con;
import inventario.modelo.Usuario;
import inventario.vista.UILogin;




public class CLogin implements ILogin
{
    private UILogin ventana;
    
    public CLogin()
    {
        this.ventana = new UILogin(this);
    }
    
    @Override
    public void validar(JTextField txtUsuario, JPasswordField txtPass)
    {
        Usuario u = Usuario.validar(txtUsuario.getText(), String.valueOf(txtPass.getPassword()));
        if(u != null)
        {
            user = u;
            new CKardexMenu();
            ventana.dispose();
        }
        else
            JOptionPane.showMessageDialog(null, "Nombre de usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE, null);
    }
    
    @Override
    public void configuracion()
    {
        new CConfiguracion(true);
        ventana.dispose();
    }
    
    @Override
    public void salir()
    {
        ventana.dispose();
        con.desconectar();
    }
}
