package services;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import main.Pago;

public class JsonService {
	
	public String ObjToJson(Pago pag){
    	
    	ObjectMapper mapper = new ObjectMapper();
    	String ret = null;
    	
    	try {
			String jsonInString = mapper.writeValueAsString(pag);
			ret = jsonInString;
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return ret; 
    }
	
	public Pago JsonToObj (String jsonInString) throws JsonParseException {
    	
		ObjectMapper mapper = new ObjectMapper();
		Pago pag = null;

		try {
		
			pag = mapper.readValue(jsonInString, Pago.class);

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pag;
    }

}
