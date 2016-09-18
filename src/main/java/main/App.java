package main;

import java.util.List;
import java.util.Scanner;
import org.apache.log4j.Logger;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.net.MalformedURLException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import data.ConceptoPagoDB;
import data.PagoDB;
import data.PensionDB;
import data.ConceptoPagadoDB;
import services.DataService;
import services.JsonService;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.dozer.Mapper;

public class App {
	
	private static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("META-INF/applicationContext.xml");
	private static final Logger logger = Logger.getLogger(App.class);
	private static String Url = "http://calcapf-danycruzmx.rhcloud.com/calculo/";
	//private static String Url = "http://127.0.0.1:8000/calculo/";
	
    public static void main( String[] args )
    {

    	DataService data = (DataService) context.getBean("dataServ");
    	JsonService conv = (JsonService) context.getBean("convServ");
    	
    	System.out.println ("Inicio, introduzca cadena -> ");
        String cadena = "";
        Scanner entrada = new Scanner (System.in); 
        cadena = entrada.nextLine ();
        entrada.close();

        System.out.println ("<- Calculando ");
        
    	List<Pago> listaDeResultados = new ArrayList<Pago>();
    	List<PagoDB> listaDePagos =  data.PagoRFCfind(cadena.trim().toUpperCase() + "%");
    	int enviados = 0;
    	int procesados = 0;
        
		for (PagoDB next : listaDePagos ) {
		
			List<ConceptoPagoDB> conceptos = data.ConceptoPagoRFCfind(next.getRfc());
			next.setConceptos(conceptos);
			List<PensionDB> pensiones = data.PensionRFCfind(next.getRfc());
			next.setPensiones(pensiones);
			List<ConceptoPagadoDB> pagados = null;
			next.setConceptosPagados(pagados);
			
			enviados = enviados + 1;
			Pago p = MapPago(next);
			p.setId(enviados);
			
			String sPago = conv.ObjToJson(p);
			logger.debug("<<<---*** Objeto del lado del cliente ***--->>>");
			logger.debug(sPago);
						
			String calcPago = Evaluate(sPago);
			logger.debug("<<<---*** Respuesta del servidor ***--->>>");
			logger.debug(Evaluate(sPago));
			
			try{
				Pago pres = conv.JsonToObj(calcPago);
				procesados = procesados + 1;
				p.setConceptospagados(pres.getConceptospagados());
				
			} catch (Exception e) {
				logger.error(" Error: En calculo de " + p.to_String());				
			}
			
			listaDeResultados.add(p);
			System.out.print("*");
		}
		
		context.close();
		
		System.out.println("\n --> Enviados " + enviados + " Procesados " + procesados);
		Double percep;
		Double deduc;
		Double liquido;
		for(Pago p: listaDeResultados){
			percep = 0.0;
			deduc = 0.0;
			liquido = 0.0;		
			System.out.println("\n *********** " + p.getId().toString());
			System.out.println(p.to_String());
			for(ConceptoPagado c: p.getConceptospagados()){
				if(c.getTipo().equals("P")){
					percep = percep + c.getValor();
				} else if(c.getTipo().equals("D")) {
					deduc = deduc + c.getValor();
				}	
				System.out.println(c.to_String());
			}
			System.out.println(" Percepciones -> " + Math.round(percep * 100.0)/100.0);
			System.out.println(" Deducciones -> " + Math.round(deduc * 100.0)/100.0);
			liquido = percep - deduc;
			System.out.println(" Liquido -> " + Math.round(liquido * 100.0)/100.0);
		}
    }
    
    public static Pago MapPago(PagoDB pag) {

        Mapper mapper = (Mapper) context.getBean("mapper");
	     
    	Pago pagEnt =  mapper.map(pag, Pago.class);
    	
    	List<ConceptoPago> cptosEnt = new ArrayList<ConceptoPago>();
		List<Pension> pensionEnt = new ArrayList<Pension>();
		List<ConceptoPagado> pagadoEnt = new ArrayList<ConceptoPagado>();

    	// Mapea conceptos de pago
		List<ConceptoPagoDB> cptos = pag.getConceptos();
		for (ConceptoPagoDB cpto : cptos ) {
			ConceptoPago c = mapper.map(cpto, ConceptoPago.class);
			cptosEnt.add(c);
		}
		
		// Mapea Pensiones	
		List<PensionDB> pension = pag.getPensiones();
		for (PensionDB pen : pension ) {
			Pension p = mapper.map(pen, Pension.class);
			pensionEnt.add(p);
		}
		
		// Asigna listas		
		pagEnt.setConceptospago(cptosEnt);
		pagEnt.setPensiones(pensionEnt);
		pagEnt.setConceptospagados(pagadoEnt);
		
		return pagEnt;
    }
    
    public static String Evaluate(String pag) {

    	String ret = "";
    	
    	try {
    		DefaultHttpClient httpClient = new DefaultHttpClient();
    		HttpPost postRequest = new HttpPost(Url);
    		
    		StringEntity input = new StringEntity(pag);
    		input.setContentType("application/json");
    		postRequest.setEntity(input);
    		HttpResponse response = httpClient.execute(postRequest);

    		String output;
    		
    		if (response.getStatusLine().getStatusCode() != 201) {
    			logger.error("Codigo HTTP: " + response.getStatusLine().getStatusCode());
    		}

    		BufferedReader br = new BufferedReader(
    					new InputStreamReader((response.getEntity().getContent())));

    		while ((output = br.readLine()) != null) {
    			ret = ret + output;
    		}

    		httpClient.getConnectionManager().shutdown();

  	  	} catch (MalformedURLException e) {
  	  		e.printStackTrace();
  	  	} catch (IOException e) {
  	  		e.printStackTrace();
  	  	}
    	
    	return ret;	
    }

}