package org.banque;

import org.banque.rmi.BanqueRMIRemote;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.jaxws.SimpleJaxWsServiceExporter;
import org.springframework.remoting.rmi.RmiServiceExporter;

@Configuration
public class MyConfig {
	@Bean
	public SimpleJaxWsServiceExporter getJWS()
	{
		SimpleJaxWsServiceExporter exporter = new SimpleJaxWsServiceExporter();
		exporter.setBaseAddress("http://localhost:8088/maBanque");
		return exporter;
	}
	@Bean
	public RmiServiceExporter getRmiService(ApplicationContext ctx)
	{
		
		RmiServiceExporter rmiServiceExporter = new  RmiServiceExporter();
		rmiServiceExporter.setService(ctx.getBean("myRmiService"));
		rmiServiceExporter.setRegistryPort(1099);
		rmiServiceExporter.setServiceName("BK");
		rmiServiceExporter.setServiceInterface(BanqueRMIRemote.class);
		return rmiServiceExporter;
	}

}
