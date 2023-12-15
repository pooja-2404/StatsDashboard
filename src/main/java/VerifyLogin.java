import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/VerifyLogin")
public class VerifyLogin extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			boolean flag = false;
			// Retrieve the connection from the shared context
			Connection conn = (Connection) getServletContext().getAttribute("dbConnection");
			String username = request.getParameter("user").trim();//pass name of input field
			String password = request.getParameter("pass").trim();
			String query = "SELECT UserName, UserPassword FROM loginDetail";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String var1 = rs.getString("UserName").trim();
				String var2 = rs.getString("UserPassword").trim();
				if ((var1.equals(username)) && (var2.equals(password))) {
					flag = true;
					break;
				}
			}
			if (flag) {
				response.sendRedirect("index.jsp");

			} else {
				response.sendRedirect("Login.jsp");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
