package ra.edu.config;

import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.ServletRegistration;
import lombok.NonNull;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebInitial extends AbstractAnnotationConfigDispatcherServletInitializer {
    private final String TMP_PATH = System.getProperty("java.io.tmpdir");

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    @Override
    @org.springframework.lang.NonNull
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected void customizeRegistration(@NonNull ServletRegistration.Dynamic registration) {
        super.customizeRegistration(registration);
        long MAX_FILE_SIZE = 50 * 1024 * 1024L;
        registration.setMultipartConfig(new MultipartConfigElement(TMP_PATH, MAX_FILE_SIZE, 4 * MAX_FILE_SIZE, 50 * 1024 * 1024));
    }
}
