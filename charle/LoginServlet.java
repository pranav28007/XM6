
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LoginServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String uname = request.getParameter("uname");
        String pass = request.getParameter("pass");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/test", "root", "password");

            PreparedStatement ps = con.prepareStatement(
                    "select * from users where username=? and password=?");

            ps.setString(1, uname);
            ps.setString(2, pass);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                out.println("<h2>Login Successful</h2>");
            } else {
                out.println("<h2>Login Failed</h2>");
            }

            con.close();

        } catch (Exception e) {
            out.println(e);
        }
    }
}
4m

Add an Emoji, Sticker, or GIF
