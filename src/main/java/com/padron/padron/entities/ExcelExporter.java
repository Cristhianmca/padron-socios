package com.padron.padron.entities;

import java.io.IOException;
import java.util.List;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;


public class ExcelExporter {
    private XSSFWorkbook workbook;
    private Sheet sheet;
    private List<Socios> listSocios;

    public ExcelExporter(List<Socios> listSocios) {
        this.listSocios = listSocios;
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("Socios");
    }

    private void writeHeaderRow() {
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("ID");
        cell = row.createCell(1);
        cell.setCellValue("DNI");
        cell = row.createCell(2);
        cell.setCellValue("NOMBRES");
        cell = row.createCell(3);
        cell.setCellValue("APELLIDO P.");
        cell = row.createCell(4);
        cell.setCellValue("APELLIDO M.");
        cell = row.createCell(5);
        cell.setCellValue("CORREO");
        cell = row.createCell(6);
        cell.setCellValue("TELEFONO");
        cell = row.createCell(7);
        cell.setCellValue("DIRECCION");
        cell = row.createCell(8);
        cell.setCellValue("F. NACIMIENTO");
        cell = row.createCell(9);
        cell.setCellValue("OCUPACION");
        cell = row.createCell(10);
        cell.setCellValue("GENERO");
        cell = row.createCell(11);
        cell.setCellValue("F. AFILIACION");
        cell = row.createCell(12);
        cell.setCellValue("ESTADO");
        cell = row.createCell(13);
        cell.setCellValue("TIPO");
    }

    private void writeDataRows() {
        int rowCount = 1;
        for (Socios socio : listSocios) {
            Row row = sheet.createRow(rowCount++);
            Cell cell = row.createCell(0);
            cell.setCellValue(socio.getIdsocio());
            cell = row.createCell(1);
            cell.setCellValue(socio.getDni());
            cell = row.createCell(2);
            cell.setCellValue(socio.getNombre());
            cell = row.createCell(3);
            cell.setCellValue(socio.getApellidoP());
            cell = row.createCell(4);
            cell.setCellValue(socio.getApellidoM());
            cell = row.createCell(5);
            cell.setCellValue(socio.getCorreo());
            cell = row.createCell(6);
            cell.setCellValue(socio.getTelefono());
            cell = row.createCell(7);
            cell.setCellValue(socio.getDireccion());
            cell = row.createCell(8);
            cell.setCellValue(socio.getFechaNacimiento().toString());
            cell = row.createCell(9);
            cell.setCellValue(socio.getOcupacion());
            cell = row.createCell(10);
            cell.setCellValue(String.valueOf(socio.getGenero()));
            cell = row.createCell(11);
            cell.setCellValue(socio.getFechaAfiliacion().toString());
            cell = row.createCell(12);
            cell.setCellValue(socio.getEstado() == 1 ? "Activo" : (socio.getEstado() == 2 ? "Inactivo" : "Desconocido"));
            cell = row.createCell(13);
            cell.setCellValue(socio.getTipo() == 1 ? "Admin" : socio.getTipo() == 2 ? "User" : "otro");
        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderRow();
        writeDataRows();
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}