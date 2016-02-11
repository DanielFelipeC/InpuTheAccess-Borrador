/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.util.List;
import modelo.dto.Cuenta;
import modelo.dto.CuentaPK;

/**
 *
 * @author Juan
 */
public interface CuentaDAO {
    public List<Cuenta> findAll();
    public void insert(Cuenta dto);
    public void update(CuentaPK llaveDto, Cuenta dto);
    public void updatePk(CuentaPK llaveVieja, CuentaPK llaveNueva);
    public void delete(CuentaPK dto);
    public Cuenta findByPK(CuentaPK dto);
    public int count();
}
