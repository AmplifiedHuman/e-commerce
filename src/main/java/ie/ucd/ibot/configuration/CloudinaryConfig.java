package ie.ucd.ibot.configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
@RequiredArgsConstructor
public class CloudinaryConfig {
    @Bean
    public Cloudinary initCloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "internationalbots",
                "api_key", "528727767427379",
                "api_secret", "hLhqEaOqCWbv_kAGKugTwiJ3hNw"));
    }
}
