package put.io.selenium.atm;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class SimpleServer {

    private static final boolean TEST_MODE = true;

    public static void main(String[] args) throws Exception {
        Server server = new Server(9090);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);

        context.addServlet(new ServletHolder(new AtmServlet(TEST_MODE)), "/*");

        server.start();
        server.join();
    }
}