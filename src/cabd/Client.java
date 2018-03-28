package cabd;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.MsgServer.ExampleServer;
import com.MsgServer.ExampleServerRemote;

@WebServlet("/Client")
public class Client extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Client() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			PrintWriter out = response.getWriter();
			System.out.println("Client App Started");
			Properties props = new Properties();
			props.put("java.naming.factory.url.pkgs","org.jboss.ejb.client.naming");
			InitialContext context = new InitialContext(props);	        
	        String appName = "";        	 
	        String moduleName = "MsgFromServer";
	        String distinctName = "";        	 
	        String beanName = ExampleServer.class.getSimpleName();        	 
	        String interfaceName = ExampleServerRemote.class.getName();
	        String name = "ejb:" + appName + "/" + moduleName + "/" +  distinctName    + "/" + beanName + "!" + interfaceName;
	        System.out.println(name);
	        ExampleServerRemote bean = (ExampleServerRemote)context.lookup(name);
	        String msg = bean.getMsg();
	        System.out.println(msg);
			out.print("{\"test\":\""+msg+"\"}");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}