package se.goran.raspberry.templogger.client;

import org.apache.log4j.Logger;

import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import se.goran.raspberry.templogger.model.TempData;

public class TemperatureRestClient {
	
	private static final Logger LOGGER = Logger.getLogger(TemperatureRestClient.class.getName());
	private static final String timeoutDefault = "1000";
	
	
	public void sendTempData(Long sensorId, TempData tempData) {        
        try {                            
            RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());            

            String hostUrl = System.getProperty("rest.templogger.endpoint", "http://172.17.0.2:8080");
            String methodPath = System.getProperty("rest.templogger.methodpath", "OpenShiftTest/rest/temp/saveTempData");
            restTemplate.postForObject(hostUrl + "/" + methodPath + "/" + sensorId, tempData, Object.class);

        } catch (RestClientException e) {
            LOGGER.error("Failed to connect to RAM For Item : " + tempData.getTemperature() + " Error : " + e.getMessage());
         
        }        
    }
	
	private ClientHttpRequestFactory getClientHttpRequestFactory() {
        int ramTimeout = Integer.parseInt(System.getProperty("rest.timeout.milliseconds", timeoutDefault));
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(ramTimeout);
        factory.setConnectTimeout(ramTimeout);
        return factory;
    }

}
