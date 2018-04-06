package cabd;

import java.io.File;

import javax.servlet.ServletException;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;


public class Main {

	static Show show = new Show();
	
	public static void main(String[] args) throws LifecycleException, ServletException {
		
		Integer port = 8200;
		Tomcat tomcat = new Tomcat();
	    tomcat.setPort(port);
		Context ctxt = tomcat.addContext("/", new File(".").getAbsolutePath());
		tomcat.addWebapp("/App", new File(".").getAbsolutePath() + "/WebContent");
		tomcat.enableNaming();
		Tomcat.addServlet(ctxt, "All", show);
		ctxt.addServletMappingDecoded("/cart", "All");
		tomcat.start();
		tomcat.getServer().await();
		
	}
	
}


