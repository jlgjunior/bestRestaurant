package bestRestaurants;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebAppInitializer implements WebApplicationInitializer {

public void onStartup(ServletContext servletContext) throws ServletException {
    AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
    applicationContext.setConfigLocations("com.mycompany.wp.web.config.WebConfig");
    servletContext.addListener(new ContextLoaderListener(applicationContext));

    servletContext.setInitParameter("contextConfigLocation", "/WEB-INF/applicationContext.xml");

    ServletRegistration.Dynamic dispatcherServlet = servletContext.addServlet("dispatcher", new DispatcherServlet(applicationContext));
    dispatcherServlet.setLoadOnStartup(1);
    dispatcherServlet.addMapping("/");
}

}