package com.hospital.gestorcitas.dto;


import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class ConsultaDTO {

    private long id;
    private String fechaConsulta;
    private String informe;
    private CitaDTO citaDTO;

    public Date getFechaConsultaAsDate() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.parse(this.fechaConsulta);
    }

    public void setFechaConsultaFromDate(){
        SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.fechaConsulta = sdt.format(fechaConsulta);
    }


}
