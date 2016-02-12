/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dto;

import java.io.Serializable;

/**
 *
 * @author duvan
 */
public class PropietarioFK implements Serializable {

    private String cuentaTipoDocumento;
    private String cuentaNumDocumento;
    private String equipoCodigoBarras;

    public PropietarioFK() {
    }

    public PropietarioFK(String cuentaTipoDocumento, String cuentaNumDocumento, String equipoCodigoBarras) {
        this.cuentaTipoDocumento = cuentaTipoDocumento;
        this.cuentaNumDocumento = cuentaNumDocumento;
        this.equipoCodigoBarras = equipoCodigoBarras;
    }

    public String getCuentaTipoDocumento() {
        return cuentaTipoDocumento;
    }

    public void setCuentaTipoDocumento(String cuentaTipoDocumento) {
        this.cuentaTipoDocumento = cuentaTipoDocumento;
    }

    public String getCuentaNumDocumento() {
        return cuentaNumDocumento;
    }

    public void setCuentaNumDocumento(String cuentaNumDocumento) {
        this.cuentaNumDocumento = cuentaNumDocumento;
    }

    public String getEquipoCodigoBarras() {
        return equipoCodigoBarras;
    }

    public void setEquipoCodigoBarras(String equipoCodigoBarras) {
        this.equipoCodigoBarras = equipoCodigoBarras;
    }

    @Override
    public String toString() {
        return "PropietarioFK{" + "cuentaTipoDocumento=" + cuentaTipoDocumento + ", cuentaNumDocumento=" + cuentaNumDocumento + ", equipoCodigoBarras=" + equipoCodigoBarras + '}';
    }

}
