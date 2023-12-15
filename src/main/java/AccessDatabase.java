import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import javax.servlet.ServletException;
import data.DataItem;
import data.DataItemHour;

@WebServlet("/AccessDatabase")
public class AccessDatabase extends HttpServlet {

	LocalDate localDatestart;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String pattern = "yyyy-MM-dd";
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			if (request.getParameter("selectedDate") != "") {
				String date = request.getParameter("selectedDate");
				Date parsedDate = dateFormat.parse(date);
				localDatestart = parsedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			}
			DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy_MM_dd");
			String formattedDate1 = localDatestart.format(formatter1);
			Connection conn = (Connection) getServletContext().getAttribute("dbConnection");
			String sql = "SELECT ProcessedDate, Hour,Min,Min_5,Min_15,Del_count,Fail_count FROM  statsDetail_"
					+ formattedDate1;
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);

			ArrayList<DataItem> data = new ArrayList<>();
			ArrayList<DataItemHour> dataHour = new ArrayList<>();

			while (resultSet.next()) {
				Date ProcessedDate = resultSet.getDate("ProcessedDate");
				int Hour = resultSet.getInt("Hour");
				int Min = resultSet.getInt("Min");
				int Min_5 = resultSet.getInt("Min_5");
				int Min_15 = resultSet.getInt("Min_15");
				int Del_count = resultSet.getInt("Del_count");
				int Fail_count = resultSet.getInt("Fail_count");

				data.add(new DataItem(ProcessedDate, Hour, Min, Min_5, Min_15, Del_count, Fail_count));
			}
			String sql_hour = "select hour,sum(Del_count) AS Del_count , sum(Fail_count) AS Fail_count from statsDetail_"
					+ formattedDate1 + " group by Hour order by hour";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql_hour);
			while (rs.next()) {
				int Hour = rs.getInt("Hour");
				int Del_count = rs.getInt("Del_count");
				int Fail_count = rs.getInt("Fail_count");

				dataHour.add(new DataItemHour(Hour, Del_count, Fail_count));

			}
			response.setContentType("application/json");
			response.getWriter().write("{\"dataList\":" + new Gson().toJson(data) + ", \"dataListHour\":"
					+ new Gson().toJson(dataHour) + "}");

			resultSet.close();
			statement.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}