package semesterafslutning;



import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/glazier"})
public class GameServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String roomString = request.getParameter("room");
            String directionString = request.getParameter("direction");
            int room = Integer.parseInt(roomString);
            int direction = Integer.parseInt(directionString);
            
            //Controller controller = new Controller();
            //Room room = controller.getRoom(room, direction);
            
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Dungeon Game</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Dungeon Game</h1>");
            out.println("</body>");
            out.println("</html>");
        } catch (Exception ex) {
            response.sendRedirect("error.html");
        }
    }
}
