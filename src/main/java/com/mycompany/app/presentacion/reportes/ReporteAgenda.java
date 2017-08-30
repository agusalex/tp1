package com.mycompany.app.presentacion.reportes;

import com.mycompany.app.dto.EtiquetaDTO;
import com.mycompany.app.dto.LocalidadDTO;
import com.mycompany.app.dto.PersonaDTO;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import report.data.PersonaJasper;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ReporteAgenda
{
	private JasperReport reporte;
	private JasperViewer reporteViewer;
	private JasperPrint reporteLleno;

	//Recibe la lista de personas para armar el reporte
    public ReporteAgenda(List<PersonaDTO> personas, String reportToOpen)
    {

		//Hardcodeado
		Map<String, Object> parametersMap = new HashMap<String, Object>();
		borrarNulls(personas);
		List<PersonaJasper> personasJasper = PersonaJasper.getPersonasJasper(personas);
		parametersMap.put("Fecha", new SimpleDateFormat("dd/MM/yyyy").format(new Date()));		
    	try		{
    		
			this.reporte = (JasperReport) JRLoader.loadObjectFromFile( reportToOpen );
			this.reporteLleno = JasperFillManager.fillReport(this.reporte, parametersMap,
					new JRBeanCollectionDataSource(personasJasper));
		}
		catch( JRException ex )
		{
			ex.printStackTrace();
		}
    }       



    public void mostrar()
	{
		this.reporteViewer = new JasperViewer(this.reporteLleno,false);
		this.reporteViewer.setVisible(true);
	}



	public void borrarNulls(List<PersonaDTO> personas){

		for (PersonaDTO persona:personas
				) {


			if(persona.getLocalidad()==null){
				persona.setLocalidad(new LocalidadDTO(""));


			}
			else if(persona.getLocalidad().getNombre()==null){
				persona.setLocalidad(new LocalidadDTO(""));


			}
			else if(persona.getLocalidad().getNombre().equals("null")){
				persona.setLocalidad(new LocalidadDTO(""));


			}


			if(persona.getEtiqueta()==null){
				persona.setEtiqueta(new EtiquetaDTO(""));


			}
			else if(persona.getEtiqueta().getNombre()==null){
				persona.setEtiqueta(new EtiquetaDTO(""));


			}
			else if(persona.getEtiqueta().getNombre().equals("null")){
				persona.setEtiqueta(new EtiquetaDTO(""));


			}


		}


	}
   
}	