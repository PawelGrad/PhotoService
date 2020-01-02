package photoGallery.controllers;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import photoGallery.model.PhotoFile.PhotoFile;
import photoGallery.model.PhotoFile.PhotoFileService;

@Controller
public class HomeController {

    private PhotoFileService photoFileService;

    public HomeController(PhotoFileService photoFileService) {
        this.photoFileService = photoFileService;
    }


    @GetMapping("/")
    public String uploadFile() {
        return "test";
    }

    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        photoFileService.storeFile(file);
        return "redirect:/";
    }

    @GetMapping("/photo/{id}")
    @ResponseBody
    public PhotoFile getFile(@PathVariable Long id) {
        return photoFileService.getFile(id);
    }

//    @GetMapping("/photo/{id}")
//    public String showFile(@PathVariable Long id, Model model) {
//        PhotoFile photo = photoFileService.getFile(id);
//        byte[] encodeBase64 = Base64.encodeBase64(photo.getData());
//        try {
//            model.addAttribute("photo", new String(encodeBase64, "UTF-8"));
//        } catch (Exception e) {
//
//        }
//        return "image";
//    }
}
