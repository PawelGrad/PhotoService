package photoGallery.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import photoGallery.model.PhotoFile.PhotoFile;
import photoGallery.model.PhotoFile.PhotoFileDTO;
import photoGallery.model.PhotoFile.PhotoFileService;


import java.util.List;

@Controller
@RestController
public class HomeController {

    private PhotoFileService photoFileService;

    public HomeController(PhotoFileService photoFileService) {
        this.photoFileService = photoFileService;
    }

    @GetMapping("/photo/show/{id}")
    public PhotoFileDTO getConvertedPhoto(@PathVariable Long id){
        return photoFileService.convertToDTO(photoFileService.getFile(id));

    }

    @GetMapping("/photo/getEntity/{id}")
    public PhotoFile getEntityPhoto(@PathVariable Long id){
        return photoFileService.getFile(id);
    }

    @GetMapping("/photo/getDTO/{id}")
    public PhotoFileDTO getDTOPhoto(@PathVariable Long id){
        return photoFileService.convertToDTO(photoFileService.getFile(id));
    }

    @PutMapping("/photo/rate/{id}")
    public void ratePhoto(@PathVariable("id") Long id, @RequestBody Long rating) {
        photoFileService.changeRating(rating,photoFileService.getFile(id));
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

    @PutMapping("/photo/update")
    public void updatePhoto(@RequestBody PhotoFileDTO file) {
        photoFileService.update(file);
    }

    @GetMapping("/photo/all")
    public List<PhotoFileDTO> findAll(){
        return photoFileService.findAll();
    }


}
