/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.dao.PropietarioDAO;
import modelo.dto.Propietario;
import modelo.dto.PropietarioFK;

/**
 *
 * @author duvan
 */
public class PropietarioDAOImpl implements PropietarioDAO {

    private Connection conexion;

    private final String SQL_SELECT = "SELECT * FROM " + getTableName() + "";
    private final String SQL_INSERT = "INSERT INTO" + getTableName() + "\n"
            + "(Cuenta_Tipo_Documento,\n"
            + "Cuenta_Num_Documento,\n"
            + "Equipo_Codigo_Barras)\n"
            + "VALUES\n"
            + "(?,\n"
            + "?,\n"
            + "?);";
    private final String SQL_UPDATE = "UPDATE" + getTableName() + "\n"
            + "SET\n"
            + "Cuenta_Tipo_Documento = ?,\n"
            + "Cuenta_Num_Documento = ?,\n"
            + "Equipo_Codigo_Barras = ?\n"
            + "WHERE Cuenta_Tipo_Documento = ? "
            + "AND Cuenta_Num_Documento = ? "
            + "AND Equipo_Codigo_Barras = ?;";
    private final String SQL_UPDATEFK = "UPDATE" + getTableName() + "\n"
            + "SET\n"
            + "Cuenta_Tipo_Documento = ?,\n"
            + "Cuenta_Num_Documento = ?,\n"
            + "Equipo_Codigo_Barras = ?\n"
            + "WHERE Cuenta_Tipo_Documento = ? "
            + "AND Cuenta_Num_Documento = ? "
            + "AND Equipo_Codigo_Barras = ?;";
    private final String SQL_DELETE = "DELETE FROM" + getTableName() + "\n"
            + "WHERE Cuenta_Tipo_Documento = ? "
            + "AND Cuenta_Num_Documento = ? "
            + "AND Equipo_Codigo_Barras = ?;";
    private final String SQL_SELECT_COUNT = "SELECT count(*) FROM " + getTableName() + " AS COUNT";

    public String getTableName() {
        return "PRO.PROPIETARIO";
    }

    @Override
    public List<Propietario> findAll() {
        final boolean estaConectado = (conexion != null);
        Connection conex = null;
        PreparedStatement prstmt = null;
        ResultSet rs = null;
        List<Propietario> lt = new ArrayList<>();

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
                    Propietario pro = new Propietario();
                    pro.setCuentaTipoDocumento(rs.getString(1));
                    pro.setCuentaNumeroDocumento(rs.getString(2));
                    pro.setEquipoCodigoBarras(rs.getString(3));
                    lt.add(pro);
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
    public void insert(Propietario dto) {
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
            prstmt.setString(indice++, dto.getCuentaTipoDocumento());
            prstmt.setString(indice++, dto.getCuentaNumeroDocumento());
            prstmt.setString(indice++, dto.getEquipoCodigoBarras());

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
    public void update(PropietarioFK llaveDto, Propietario dto) {
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

            prstmt.setString(indice++, dto.getCuentaTipoDocumento());
            prstmt.setString(indice++, dto.getCuentaNumeroDocumento());
            prstmt.setString(indice++, dto.getEquipoCodigoBarras());

            prstmt.setString(indice++, llaveDto.getCuentaTipoDocumento());
            prstmt.setString(indice++, llaveDto.getCuentaNumDocumento());
            prstmt.setString(indice++, llaveDto.getEquipoCodigoBarras());

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
    public void updatePk(PropietarioFK llaveVieja, PropietarioFK llaveNueva) {
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
            final String SQL = SQL_UPDATEFK;
            int indice = 1;
            System.out.println("Se ejecuto " + SQL);
            prstmt = conex.prepareStatement(SQL);
            prstmt.setString(indice++, llaveNueva.getCuentaTipoDocumento());
            prstmt.setString(indice++, llaveNueva.getCuentaNumDocumento());
            prstmt.setString(indice++, llaveVieja.getEquipoCodigoBarras());

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
    public void delete(PropietarioFK dto) {
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
            prstmt.setString(indice++, dto.getCuentaTipoDocumento());
            prstmt.setString(indice++, dto.getCuentaNumDocumento());
            prstmt.setString(indice++, dto.getEquipoCodigoBarras());

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
    public Propietario findByPK(PropietarioFK dto) {
        final boolean estaConectado = (conexion != null);
        Connection conex = null;
        PreparedStatement prstmt = null;
        ResultSet rs = null;
        Propietario pro = new Propietario();

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
                    pro.setCuentaTipoDocumento(rs.getString(1));
                    pro.setCuentaNumeroDocumento(rs.getString(2));
                    pro.setEquipoCodigoBarras(rs.getString(3));
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
        return pro;
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
