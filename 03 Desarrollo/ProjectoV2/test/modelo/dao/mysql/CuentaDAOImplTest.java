/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao.mysql;

import java.sql.Date;
import java.util.List;
import modelo.dao.CuentaDAO;
import modelo.dto.Cuenta;
import modelo.dto.CuentaPK;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Juan
 */
public class CuentaDAOImplTest {

    public CuentaDAOImplTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of findAll method, of class CuentaDAOImpl.
     */
    @Test
    public void testFindAll() {
        System.out.println("FindAll");
        CuentaDAO instance = new CuentaDAOImpl();
        List<Cuenta> result = instance.findAll();
        for (Cuenta cuentas : result) {
            System.out.println(cuentas.toString());
        }
    }

    @Test
    public void testInsert() {
//        Date fecha = new Date(0300, 02, 10);
        System.out.println("Insert Cuenta");
        Cuenta cu = new Cuenta();

        cu.setTipoDocumento("CC");
        cu.setNumeroDocumento("12345678910");
        cu.setCargo("Aprendiz");
        cu.setFechaTerminacion("2016-02-10");
        cu.setPrimerNombre("Joaquin");
        cu.setSegundoNombre("Joaquin");
        cu.setPrimerApellido("Chapo");
        cu.setSegundoApellido("Chapo");

        CuentaDAO instance = new CuentaDAOImpl();

        instance.insert(cu);
    }

    @Test
    public void testUpdate() {

        System.out.println("Update Cuenta");
        CuentaPK llaveVieja = new CuentaPK("TI", "98935652145");
        Cuenta nuevaCuenta = new Cuenta();
//        Date fecha = new Date(2016, 02, 10);

        nuevaCuenta.setCargo("Aprendiz");
        nuevaCuenta.setFechaTerminacion("2016-02-10");
        nuevaCuenta.setPrimerNombre("Joaquin");
        nuevaCuenta.setSegundoNombre("Jose");
        nuevaCuenta.setPrimerApellido("Chapo");
        nuevaCuenta.setSegundoApellido("Chapo");

        CuentaDAO instance = new CuentaDAOImpl();
        instance.update(llaveVieja, nuevaCuenta);

    }

    @Test
    public void testUpdatePK() {

        System.out.println("UpdatePK Cuenta");

        CuentaPK llaveVieja = new CuentaPK("TI", "98935652145");
        CuentaPK llaveNueva = new CuentaPK("CC", "12345678910");

        CuentaDAO instance = new CuentaDAOImpl();
        instance.updatePk(llaveVieja, llaveNueva);

    }

    @Test
    public void testDelete() {
        System.out.println("Delete Cuenta");
        CuentaPK cu = new CuentaPK("CC", "123456789");

        CuentaDAO instance = new CuentaDAOImpl();

        instance.delete(cu);
    }

    @Test
    public void testFindByPK() {
        System.out.println("FindByPK");
        CuentaDAO instance = new CuentaDAOImpl();
        CuentaPK cPK = new CuentaPK("CC", "123456789");
        
        Cuenta cuent = instance.findByPK(cPK);
        
        System.out.println(cuent.toString());
    }
    
    @Test
    public void testSelectCount() {
        System.out.println("SelectCount");
        CuentaDAO instance = new CuentaDAOImpl();
        
        instance.count();
        
    }

}
