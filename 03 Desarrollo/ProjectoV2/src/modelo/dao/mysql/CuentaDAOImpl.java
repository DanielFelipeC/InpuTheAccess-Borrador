/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.dao.CuentaDAO;
import modelo.dto.Cuenta;
import modelo.dto.CuentaPK;

/**
 *
 * @author Juan
 */
public class CuentaDAOImpl implements CuentaDAO {

    private Connection conexion;

    private final String SQL_SELECT = "SELECT * FROM " + getTableName() + "";

    private final String SQL_INSERT = "INSERT INTO " + getTableName() + "\n"
            + "(DOCUMENTO_tipo_Documento,\n"
            + "DOCUMENTO_num_Documento,\n"
            + "cargo,\n"
            + "FECHA_TERMINACION,\n"
            + "p_Nombre,\n"
            + "s_Nombre,\n"
            + "p_Apellido,\n"
            + "a_Apellido)\n"
            + "VALUES\n"
            + "(?,?,?,?,?,?,?,?)";

    private final String SQL_UPDATE = "UPDATE " + getTableName() + "\n"
            + "SET\n"
            + "cargo = ?,\n"
            + "FECHA_TERMINACION = ?,\n"
            + "p_Nombre = ?,\n"
            + "s_Nombre = ?,\n"
            + "p_Apellido = ?,\n"
            + "a_Apellido = ?\n"
            + "WHERE DOCUMENTO_tipo_Documento = ? "
            + "AND DOCUMENTO_num_Documento = ?;";

    private final String SQL_UPDATEPK = "UPDATE " + getTableName() + "\n"
            + "SET\n"
            + "DOCUMENTO_tipo_Documento = ?,\n"
            + "DOCUMENTO_num_Documento = ?\n"
            + "WHERE DOCUMENTO_tipo_Documento = ? "
            + "AND DOCUMENTO_num_Documento = ?;";
    private final String SQL_DELETE = "DELETE FROM " + getTableName() + "\n"
            + "WHERE DOCUMENTO_tipo_Documento = ? and DOCUMENTO_num_Documento = ?";

    private final String SQL_SELECT_COUNT = "SELECT count(*) FROM " + getTableName() + " AS COUNT";

    public String getTableName() {
        return "PRO.CUENTA";
    }

    @Override
    public List<Cuenta> findAll() {

        final boolean estaConectado = (conexion != null);
        Connection conex = null;
        PreparedStatement prstmt = null;
        ResultSet rs = null;
        List<Cuenta> lt = new ArrayList<>();

        try {
            if (estaConectado) {
                conex = conexion;
            } else {
                conex = ResourceManager.getConeccion();
            }
            final String SQL = SQL_SELECT;

            System.out.println("Se ejecuto " + SQL);
            prstmt = conex.prepareStatement(SQL);
            rs = prstmt.executeQuery();

            if (!rs.wasNull()) {
                while (rs.next()) {
                    Cuenta ct = new Cuenta();
                    ct.setTipoDocumento(rs.getString(1));
                    ct.setNumeroDocumento(rs.getString(2));
                    ct.setCargo(rs.getString(3));
                    ct.setFechaTerminacion(rs.getString(4));
                    ct.setPrimerNombre(rs.getString(5));
                    ct.setSegundoNombre(rs.getString(6));
                    ct.setPrimerApellido(rs.getString(7));
                    ct.setSegundoApellido(rs.getString(8));
                    lt.add(ct);
                }
            }

        } catch (Exception e) {
            System.out.println("Error dentro del FindAll: " + e.getMessage());
        } finally {
            ResourceManager.closeResultSet(rs);
            ResourceManager.closePreparedStatement(prstmt);
            if (!estaConectado) {
                ResourceManager.closeConnection(conex);
            }
        }
        return lt;
    }

    @Override
    public void insert(Cuenta dto) {
        final boolean estaConectado = (conexion != null);
        Connection conex = null;
        PreparedStatement prstmt = null;
        int rs;
        try {
            if (estaConectado) {
                conex = conexion;
            } else {
                conex = ResourceManager.getConeccion();
            }
            final String SQL = SQL_INSERT;
            int indice = 1;
            System.out.println("Se ejecuto " + SQL);
            prstmt = conex.prepareStatement(SQL);
            prstmt.setString(indice++, dto.getTipoDocumento());
            prstmt.setString(indice++, dto.getNumeroDocumento());
            prstmt.setString(indice++, dto.getCargo());
            prstmt.setString(indice++, dto.getFechaTerminacion());
            prstmt.setString(indice++, dto.getPrimerNombre());
            prstmt.setString(indice++, dto.getSegundoNombre());
            prstmt.setString(indice++, dto.getPrimerApellido());
            prstmt.setString(indice++, dto.getSegundoApellido());

            rs = prstmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error dentro del Insert: " + e.getMessage());
        } finally {
            ResourceManager.closePreparedStatement(prstmt);
            if (!estaConectado) {
                ResourceManager.closeConnection(conex);
            }
        }

    }

    @Override
    public void update(CuentaPK llaveDto, Cuenta dto) {
        final boolean estaConectado = (conexion != null);
        Connection conex = null;
        PreparedStatement prstmt = null;
        int rs;

        try {
            if (estaConectado) {
                conex = conexion;
            } else {
                conex = ResourceManager.getConeccion();
            }
            final String SQL = SQL_UPDATE;
            int indice = 1;
            System.out.println("Se ejecuto " + SQL);
            prstmt = conex.prepareStatement(SQL);

            prstmt.setString(indice++, dto.getCargo());
            prstmt.setString(indice++, dto.getFechaTerminacion());
            prstmt.setString(indice++, dto.getPrimerNombre());
            prstmt.setString(indice++, dto.getSegundoNombre());
            prstmt.setString(indice++, dto.getPrimerApellido());
            prstmt.setString(indice++, dto.getSegundoApellido());

            prstmt.setString(indice++, llaveDto.getTipoDocumento());
            prstmt.setString(indice++, llaveDto.getNumeroDocumento());

            rs = prstmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error dentro del Update: " + e.getMessage());
        } finally {
            ResourceManager.closePreparedStatement(prstmt);
            if (!estaConectado) {
                ResourceManager.closeConnection(conex);
            }
        }

    }

    @Override
    public void updatePk(CuentaPK llaveVieja, CuentaPK llaveNueva) {
        final boolean estaConectado = (conexion != null);
        Connection conex = null;
        PreparedStatement prstmt = null;
        int rs;

        try {
            if (estaConectado) {
                conex = conexion;
            } else {
                conex = ResourceManager.getConeccion();
            }
            final String SQL = SQL_UPDATEPK;
            int indice = 1;
            System.out.println("Se ejecuto " + SQL);
            prstmt = conex.prepareStatement(SQL);
            prstmt.setString(indice++, llaveNueva.getTipoDocumento());
            prstmt.setString(indice++, llaveNueva.getNumeroDocumento());
            prstmt.setString(indice++, llaveVieja.getTipoDocumento());
            prstmt.setString(indice++, llaveVieja.getNumeroDocumento());

            rs = prstmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error dentro del UpdatePK: " + e.getMessage());
        } finally {
            ResourceManager.closePreparedStatement(prstmt);
            if (!estaConectado) {
                ResourceManager.closeConnection(conex);
            }
        }

    }

    @Override
    public void delete(CuentaPK dto) {
        final boolean estaConectado = (conexion != null);
        Connection conex = null;
        PreparedStatement prstmt = null;
        int rs;
        try {
            if (estaConectado) {
                conex = conexion;
            } else {
                conex = ResourceManager.getConeccion();
            }
            final String SQL = SQL_DELETE;
            int indice = 1;
            System.out.println("Se ejecuto " + SQL);
            prstmt = conex.prepareStatement(SQL);
            prstmt.setString(indice++, dto.getTipoDocumento());
            prstmt.setString(indice++, dto.getNumeroDocumento());

            rs = prstmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error dentro del Delete: " + e.getMessage());
        } finally {
            ResourceManager.closePreparedStatement(prstmt);
            if (!estaConectado) {
                ResourceManager.closeConnection(conex);
            }
        }
    }

    @Override
    public Cuenta findByPK(CuentaPK dto) {
        final boolean estaConectado = (conexion != null);
        Connection conex = null;
        PreparedStatement prstmt = null;
        ResultSet rs = null;
        Cuenta ct = new Cuenta();

        try {
            if (estaConectado) {
                conex = conexion;
            } else {
                conex = ResourceManager.getConeccion();
            }
            final String SQL = SQL_SELECT;

            System.out.println("Se ejecuto " + SQL);
            prstmt = conex.prepareStatement(SQL);
            rs = prstmt.executeQuery();

            if (!rs.wasNull()) {
                while (rs.next()) {
                    ct.setTipoDocumento(rs.getString(1));
                    ct.setNumeroDocumento(rs.getString(2));
                    ct.setCargo(rs.getString(3));
                    ct.setFechaTerminacion(rs.getString(4));
                    ct.setPrimerNombre(rs.getString(5));
                    ct.setSegundoNombre(rs.getString(6));
                    ct.setPrimerApellido(rs.getString(7));
                    ct.setSegundoApellido(rs.getString(8));
                }
            }

        } catch (Exception e) {
            System.out.println("Error dentro del FindByPK: " + e.getMessage());
        } finally {
            ResourceManager.closeResultSet(rs);
            ResourceManager.closePreparedStatement(prstmt);
            if (!estaConectado) {
                ResourceManager.closeConnection(conex);
            }
        }
        return ct;
    }

    @Override
    public int count() {
        final boolean estaConectado = (conexion != null);
        Connection conex = null;
        PreparedStatement prstmt = null;
        ResultSet rs = null;
        int rowsCount = 0;

        try {
            if (estaConectado) {
                conex = conexion;
            } else {
                conex = ResourceManager.getConeccion();
            }
            final String SQL = SQL_SELECT_COUNT;

            System.out.println("Se ejecuto " + SQL);
            prstmt = conex.prepareStatement(SQL);
            rs = prstmt.executeQuery();
            while (rs.next()) {                
                rs.getInt(rowsCount);
            }
            

        } catch (Exception e) {
            System.out.println("Error dentro del SelectCount: " + e.getMessage());
        } finally {
            ResourceManager.closeResultSet(rs);
            ResourceManager.closePreparedStatement(prstmt);
            if (!estaConectado) {
                ResourceManager.closeConnection(conex);
            }
        }
        return rowsCount;
        
    }

}
