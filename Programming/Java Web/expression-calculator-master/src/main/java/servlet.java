import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/calc")
public class servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String string;
        string = req.getParameter("expression");
        PrintWriter printWriter;
        printWriter = resp.getWriter();
        if ("a+b/c".equals(string)) {
            int a = Integer.parseInt(req.getParameter("a"));
            int b = Integer.parseInt(req.getParameter("b"));
            int c = Integer.parseInt(req.getParameter("c"));
            printWriter.println(a + b / c);
        } else if ("x+y/z".equals(string)) {
            int x = Integer.parseInt(req.getParameter("x"));
            int y = Integer.parseInt(req.getParameter("y"));
            String z = req.getParameter("z");
            if (z.charAt(0) >= 48 && z.charAt(0) <= 57) {
                printWriter.println(x + y / Integer.parseInt(z));
            } else {
                printWriter.println(x + y / Integer.parseInt(req.getParameter(z)));
            }
        } else if ("(f + k)*(h - g)/f".equals(string)) {
            int f = Integer.parseInt(req.getParameter("f"));
            int k = Integer.parseInt(req.getParameter("k"));
            int h = Integer.parseInt(req.getParameter("h"));
            int g = Integer.parseInt(req.getParameter("g"));
            printWriter.println((f + k) * (h - g) / f);
        } else {
            int a = Integer.parseInt(req.getParameter("a"));
            int b = Integer.parseInt(req.getParameter("b"));
            if ("a/b/c/d".equals(string)) {
                int c = Integer.parseInt(req.getParameter("c"));
                int d = Integer.parseInt(req.getParameter("d"));
                printWriter.println(a / b / c / d);
            } else {
                String c = req.getParameter("c");
                if (c.charAt(0) >= 48 && c.charAt(0) <= 57) {
                    printWriter.println((Integer.parseInt(c) * (a - b) / b) * a);
                } else if ("a".equals(c)) {
                    printWriter.println((a * (a - b) / b) * a);
                } else {
                    printWriter.println((b * (a - b) / b) * a);
                }
            }
        }
    }
}
