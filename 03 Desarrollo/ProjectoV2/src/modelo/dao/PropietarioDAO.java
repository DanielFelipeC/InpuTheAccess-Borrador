/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.util.List;
import modelo.dto.CuentaPK;
import modelo.dto.Propietario;
import modelo.dto.PropietarioFK;

/**
 *
 * @author duvan
 */
public interface PropietarioDAO {
    public List<Propietario> findAll();
    public void insert(Propietario dto);
    public void update(PropietarioFK llaveDto, Propietario dto);
    public void updatePk(PropietarioFK llaveVieja, PropietarioFK llaveNueva);
    public void delete(PropietarioFK dto);
    public Propietario findByPK(PropietarioFK dto);
    public int count();
}
