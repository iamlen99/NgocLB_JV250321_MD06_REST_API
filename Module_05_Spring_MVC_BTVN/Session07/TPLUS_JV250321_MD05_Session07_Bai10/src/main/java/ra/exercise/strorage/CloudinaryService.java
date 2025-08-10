package ra.exercise.strorage;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Component
public class CloudinaryService {
    private final Cloudinary cloudinary;
    private static final String cloudName = "dwfphppwr";
    private static final String apiKey = "871545392476297";
    private static final String apiSecret = "LjQnJtC9G1LCMb1LM-wvpML4lmg";

    public CloudinaryService() {
        this.cloudinary = getCloudinary();
    }

    public Cloudinary getCloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudName,
                "api_key", apiKey,
                "api_secret", apiSecret,
                "secure", true
        ));
    }

//    @Bean
    public String uploadImage(MultipartFile file) throws IOException {
        Map upload = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap(
                "folder", "app_images",
                "resource_type", "image"
        ));
        return upload.get("secure_url").toString();
    }
}
