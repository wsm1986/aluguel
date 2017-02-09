package com.aluguel.services;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;


@Service
public class GerarRelatorioDespesas {
	
	public void gerarRelatoio(HttpServletRequest request, HttpServletResponse response) throws Exception{
		  String nome = request.getServletContext().getRealPath("/WEB-INF/jasper/gasto_por_mes.jasper");
          Map<String, Object> parametros = new HashMap<String, Object>();

          geraPDFParaOutputStream(response.getOutputStream(),"contratoAluguel.jasper", parametros);
          response.setContentType("application/x-pdf");
          response.setHeader("Content-disposition", "inline; filename=helloWorldReport.pdf");
          
	}
	public static void main(String[] args) throws Exception {
		JasperCompileManager.compileReport("contratoAluguel.jrxml");
		Map<String,Object> params = new HashMap<>();
		JasperPrint print = JasperFillManager.fillReport("contratoAluguel.jasper",params);
		JRExporter export = new JRPdfExporter();
		export.setParameter(JRExporterParameter.JASPER_PRINT, print);
		export.setParameter(JRExporterParameter.OUTPUT_STREAM, new FileOutputStream("teste.pdf"));
		export.exportReport();
	}
	
	private void geraPDFParaOutputStream(OutputStream outputStream, String nomeArquivo, Map<String, Object> parametros) {

		try {
			JasperPrint jasperPrint = JasperFillManager.fillReport(nomeArquivo, parametros);
			JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
		} catch (JRException e) {
			throw new RuntimeException(e);
		}
	}
}
