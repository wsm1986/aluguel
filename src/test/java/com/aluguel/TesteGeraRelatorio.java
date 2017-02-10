package com.aluguel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.postgresql.core.ConnectionFactory;

import com.mysql.jdbc.Connection;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

public class TesteGeraRelatorio {
	public static void main(String[] args) throws SQLException, JRException, FileNotFoundException {

		JasperCompileManager.compileReportToFile("contratoAluguel.jrxml");

		Map<String, Object> parametros = new HashMap<String, Object>();

		JasperPrint print = JasperFillManager.fillReport("contratoAluguel.jasper", parametros);

		JRExporter exporter = new JRPdfExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, new FileOutputStream("financas.pdf"));
		exporter.exportReport();

	}
}
