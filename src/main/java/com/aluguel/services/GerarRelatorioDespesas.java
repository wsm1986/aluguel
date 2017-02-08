package com.aluguel.services;

import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

public class GerarRelatorioDespesas {
	public static void main(String[] args) throws Exception {
		JasperCompileManager.compileReport("contratoAluguel.jrxml");
		Map<String,Object> params = new HashMap<>();
		JasperPrint print = JasperFillManager.fillReport("contratoAluguel.jasper",params);
		JRExporter export = new JRPdfExporter();
		export.setParameter(JRExporterParameter.JASPER_PRINT, print);
		export.setParameter(JRExporterParameter.OUTPUT_STREAM, new FileOutputStream("teste.pdf"));
		export.exportReport();
	}
}
