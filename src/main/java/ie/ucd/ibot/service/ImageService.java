package ie.ucd.ibot.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.cloudinary.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final Cloudinary cloudinary;

    @SuppressWarnings("unchecked")
    public String processImage(MultipartFile multipartFile, String productId) throws IOException {
        File file = Files.createTempFile("temp", "product-" + productId).toFile();
        multipartFile.transferTo(file);
        JSONObject response = new JSONObject(cloudinary.uploader()
                .upload(file, ObjectUtils.asMap("public_id", "product-" + productId)));
        return response.getString("url");
    }
}
