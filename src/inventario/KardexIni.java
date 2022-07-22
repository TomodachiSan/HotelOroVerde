package inventario;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.UIManager;

import inventario.controlador.CLogin;
import inventario.modelo.Conexion;
import inventario.modelo.Usuario;



public class KardexIni
{
    
    public static Conexion con;
    
    
    public static Usuario user;
    
    
    public KardexIni()
    {
        try
        {
            UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
            
            String [] conexion_data = new String[3];
            FileReader fr = new FileReader("conexion.dat");
            BufferedReader br = new BufferedReader(fr);
            String linea = "";
            int number = 0;
            
            while((linea = br.readLine()) != null)
            {
                conexion_data[number] = linea.substring(linea.indexOf("=") + 1, linea.length());
                number++;
                if(number > 2)
                    break;
            }
                
            con = new Conexion(conexion_data[0], "BD_KARDEX", conexion_data[1], conexion_data[2]);
            con.conectar(false);
            user = new Usuario();
            
            new CLogin();
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException | FileNotFoundException ex)
        {
        }
        catch (IOException ex)
        {
        }
    }
    
    public static void main(String[] args)
    {
        new KardexIni();
    }
}
