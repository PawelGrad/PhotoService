package photoGallery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import photoGallery.controllers.HomeController;

@SpringBootApplication
public class PhotoApp {

    public static void main(String[] args) {
        SpringApplication.run(PhotoApp.class, args);
    }

}
