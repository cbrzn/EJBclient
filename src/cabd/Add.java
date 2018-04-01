package cabd;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/New")
public class Add extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Add() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
			
			System.out.println("Client App Started");
	        CartRemote bean = (CartRemote)request.getSession().getAttribute("id");
	        if (bean != null) {
	        	bean.addBook("test");
	        }
        System.out.println((CartRemote)request.getSession().getAttribute("id"));
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		doGet(request, response);
	}
}