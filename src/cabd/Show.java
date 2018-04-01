package cabd;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/All")
public class Show extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Show() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		CartRemote bean = (CartRemote)request.getSession().getAttribute("test");
		try {
			if (bean == null) {
				System.out.println("Client App Started");
				Properties props = new Properties();
				props.put("java.naming.factory.url.pkgs","org.jboss.ejb.client.naming");
				InitialContext context = new InitialContext(props);	        
		        String appName = "";        	 
		        String moduleName = "ShopCart";
		        String distinctName = "";        	 
		        String beanName = Cart.class.getSimpleName();        	 
		        String interfaceName = CartRemote.class.getName();
		        String name = "ejb:" + appName + "/" + moduleName + "/" +  distinctName    + "/" + beanName + "!" + interfaceName + "?stateful";
		        bean = (CartRemote)context.lookup(name);		   
		       	request.getSession().setAttribute("test", bean);
		       	System.out.println(request.getSession().getAttribute("test"));
			}
	        ArrayList<String> msg = (ArrayList<String>) bean.getContents();
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