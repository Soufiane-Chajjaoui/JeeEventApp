package com.example.demo.Utils;

import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.services.AuthService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class StartUpServlet implements ServletContextListener {

    @EJB
    private AuthService authService;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        User admin = new User("adminName" , Role.ADMIN , "admin@gmail.com" , "admin221");
        // Initialization tasks when the servlet context is initialized
        if (this.authService.findByEmail("admin@gmail.com") == null)
            this.authService.register(admin);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Cleanup tasks when the servlet context is destroyed
        System.out.println("Server stopped. Performing cleanup tasks...");
        // Call your cleanup method here
        // myCleanupMethod();
    }
}
