package scik.modelo;

import inventario.modelo.Usuario;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class UsuarioTest
{
    
    public UsuarioTest()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
    }
    
    @AfterClass
    public static void tearDownClass()
    {
    }
    
    @Before
    public void setUp()
    {
    }
    
    @After
    public void tearDown()
    {
    }   
    @Test
    public void testValidar()
    {
        System.out.println("validar");
        String usr = "";
        String pass = "";
        Usuario expResult = null;
        Usuario result = Usuario.validar(usr, pass);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testInsertar()
    {
        System.out.println("insertar");
        String pass = "";
        Usuario instance = new Usuario();
        String expResult = "";
        String result = instance.insertar(pass);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testModificar()
    {
        System.out.println("modificar");
        String pass = "";
        Usuario instance = new Usuario();
        String expResult = "";
        String result = instance.modificar(pass);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testEliminar()
    {
        System.out.println("eliminar");
        Usuario instance = new Usuario();
        String expResult = "";
        String result = instance.eliminar();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetLista()
    {
        System.out.println("getLista");
        ArrayList<Usuario> expResult = null;
        ArrayList<Usuario> result = Usuario.getLista();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testBuscar()
    {
        System.out.println("buscar");
        String codigo = "";
        Usuario expResult = null;
        Usuario result = Usuario.buscar(codigo);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSgteCodigo()
    {
        System.out.println("sgteCodigo");
        String expResult = "";
        String result = Usuario.sgteCodigo();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
    
}
