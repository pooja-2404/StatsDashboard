import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/Main")
public class Main extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String jdbcUrl = "jdbc:mysql://localhost:3306/db1";
			String dbUser = "root";
			String dbPassword = "Pooja@240494";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
			getServletContext().setAttribute("dbConnection", conn);
			response.sendRedirect("Login.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
