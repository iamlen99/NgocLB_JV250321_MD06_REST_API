package ra.exercise.config;

import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.ServletRegistration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebInitial extends AbstractAnnotationConfigDispatcherServletInitializer {
    private String TMP_PATH = System.getProperty("java.io.tmpdir");
    private Long MAX_FILE_SIZE = 50 * 1024 * 1024L; //50MB

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        super.customizeRegistration(registration);
        registration.setMultipartConfig(new MultipartConfigElement(TMP_PATH, MAX_FILE_SIZE, MAX_FILE_SIZE * 4, 0));
    }
}
