package photoGallery.controllers;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import photoGallery.model.PhotoFile.PhotoFile;
import photoGallery.model.PhotoFile.PhotoFileService;

@Controller
@RestController
public class HomeController {

    private PhotoFileService photoFileService;

    public HomeController(PhotoFileService photoFileService) {
        this.photoFileService = photoFileService;
    }

    @PostMapping("/uploadFile")
    public void uploadFile(@RequestParam("file") MultipartFile file) {
        photoFileService.storeFile(file);
    }

    @GetMapping("/photo/{id}")
    public PhotoFile getFile(@PathVariable Long id) {
        return photoFileService.getFile(id);
    }

    @DeleteMapping("/photo/delete/{id}")
    public void deletePhoto(@PathVariable Long id) {
        photoFileService.deleteFile(id);
    }

    @PutMapping("photo/update")
    public void updatePhoto(@RequestBody PhotoFile file) {
        photoFileService.update(file);
    }
}
