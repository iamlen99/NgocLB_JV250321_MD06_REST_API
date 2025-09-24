package ra.edu.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JacksonConfig {
//    Jackson hiểu LocalDateTime và các date/time khác.
//
//    Quyết định format ngày giờ (ISO hay custom).
//
//    Quản lý JSON serialization/deserialization nhất quán cho toàn project.
//
//    Đảm bảo JSON trả về đúng encoding (UTF-8), tránh lỗi hiển thị tiếng Việt bị sai ký tự.

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper;
    }
}

