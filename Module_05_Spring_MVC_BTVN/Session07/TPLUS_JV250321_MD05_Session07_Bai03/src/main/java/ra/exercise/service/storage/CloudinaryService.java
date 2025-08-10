package ra.exercise.service.storage;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import java.util.Map;


public class CloudinaryService implements SimpleStorage {
    private final Cloudinary cloudinary;

    public CloudinaryService(String cloudName, String apiKey, String apiSecret) {
        this.cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudName,
                "api_key", apiKey,
                "api_secret", apiSecret,
                "secure", true
        ));
    }

    public CloudinaryService(String cloudName, String apiKey, String apiSecret, boolean secure) {
        this.cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudName,
                "api_key", apiKey,
                "api_secret", apiSecret,
                "secure", secure
        ));
    }

    @Override
    public String upload(byte[] file) throws Exception {
        Map upload = cloudinary.uploader().upload(file, ObjectUtils.asMap(
            "folder", "app_images",
            "resource_type", "image"
        ));
        return upload.get("secure_url").toString();
    }
}


//@Service
//public class UploadService implements SimpleStorage {
//    @Autowired
//    private Cloudinary cloudinary;
//
//    public String uploadFileWithCloud(MultipartFile file){
//        if(file!=null && !file.isEmpty()){
//            try {
//                Map upload = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap(
//                        "folder", "app_images",
//                        "resource_type", "image"
//                ));
//                return upload.get("secure_url").toString();
//            } catch (IOException e) {
//                System.err.println("Upload lỗi: " + e.getMessage());
//                return null;
//            }
//        }
//        return null;
//    }
//
//    @Override
//    public String upload() {
//        return "";
//    }
//}