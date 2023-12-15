
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/InsertServlet")
public class InsertServlet extends HttpServlet {
	LocalDate localDatestart;
	int Hour, Min, Min_5, Min_15, Del_count, Fail_count;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ProcessedDate = request.getParameter("ProcessedDate");
		if (request.getParameter("Hour") != " ") {
			Hour = Integer.parseInt(request.getParameter("Hour"));
		}
		if (request.getParameter("Min") != "") {
			Min = Integer.parseInt(request.getParameter("Min"));
		}
		if (request.getParameter("Min_5") != "") {
			Min_5 = Integer.parseInt(request.getParameter("Min_5"));
		}
		if (request.getParameter("Min_15") != "") {
			Min_15 = Integer.parseInt(request.getParameter("Min_15"));
		}
		if (request.getParameter("Del_count") != "") {
			Del_count = Integer.parseInt(request.getParameter("Del_count"));
		}
		if (request.getParameter("Fail_count") != "") {
			Fail_count = Integer.parseInt(request.getParameter("Fail_count"));
		}
		try {
			String pattern = "yyyy-MM-dd";

			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			if (request.getParameter("ProcessedDate") != "") {
				String date = request.getParameter("ProcessedDate");
				Date parsedDate = dateFormat.parse(date);
				localDatestart = parsedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				System.out.println("Date: " + localDatestart);
			}
			DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy_MM_dd");
			String formattedDate1 = localDatestart.format(formatter1);
			Connection conn = (Connection) getServletContext().getAttribute("dbConnection");
			String action = request.getParameter("action"); // Assuming you send an "action" parameter in the AJAX call

			if (action != null) {
				if (action.equals("insert")) {
					String sql = "INSERT INTO statsDetail_" + formattedDate1 + " VALUES (?, ?, ?, ?, ?, ?, ?)";
					PreparedStatement preparedStatement = conn.prepareStatement(sql);
					preparedStatement.setString(1, ProcessedDate);
					if (Hour < 0 || Hour > 23) {
						System.out.println("Range of Hour should be: 0 to 23");
					} else {
						preparedStatement.setInt(2, Hour);
					}
					if (Min < 0 || Min > 59) {
						System.out.println("Range of Min should be: 0 to 59");
					} else {
						preparedStatement.setInt(3, Min);
					}
					preparedStatement.setInt(4, Min_5);
					preparedStatement.setInt(5, Min_15);
					preparedStatement.setInt(6, Del_count);
					preparedStatement.setInt(7, Fail_count);
					preparedStatement.executeUpdate();
					response.getWriter().write("Success");
				}
			}
			if (action.equals("delete")) {
				if (Hour < 0 || Hour > 23) {
					System.out.println("Range of Hour should be: 0 to 23");
				} else {
					String sql = "Delete from statsDetail_" + formattedDate1 + " WHERE Hour=" + Hour;
					PreparedStatement preparedStatement = conn.prepareStatement(sql);
					preparedStatement.executeUpdate();
				}
				response.getWriter().write("Success");
			}
			if (action.equals("update")) {
				if (Hour < 0 || Hour > 23) {
					System.out.println("Range of Hour should be: 0 to 23");
				} else {
					String sql = "UPDATE statsDetail_" + formattedDate1 + " SET Del_count=" + Del_count
							+ ", Fail_count=" + Fail_count + " WHERE Hour=" + Hour;
					PreparedStatement preparedStatement = conn.prepareStatement(sql);
					preparedStatement.executeUpdate();
				}
				response.getWriter().write("Success");
			}
		} catch (ParseException e) {
			e.printStackTrace();
			response.getWriter().write("Error: Data insertion failed");
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

		} catch (SQLException e) {
			e.printStackTrace();
			response.getWriter().write("Error: Data insertion failed");
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

		}
	}

}
