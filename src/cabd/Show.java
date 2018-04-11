package cabd;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

@WebServlet("/All")
public class Show extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CartRemote bean;

    public Show() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		try {
			if (bean == null) {
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
			}
	        json.put("session", bean);
			out.print(json);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		JSONObject reqBody = new JSONObject(request.getReader().lines().collect(Collectors.joining(System.lineSeparator())));
		int choice = reqBody.getInt("choice");
		switch (choice) {
			case 1:
				String title = reqBody.getString("title");
    			bean.addBook(title);
    			out.print("{\"test\":\"book added\"}");
    	    break;
			case 2:
				ArrayList<String> msg = (ArrayList<String>) bean.getContents();
				out.print("{\"test\":\""+msg+"\"}");
			break;
			case 3:
				bean = null;
				out.print("{\"test\":\"session closed\"}");
			break;
		}
	}
}