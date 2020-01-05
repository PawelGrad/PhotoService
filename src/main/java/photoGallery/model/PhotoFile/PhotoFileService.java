package photoGallery.model.PhotoFile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Service
public class PhotoFileService {

    private PhotoFileRepository photoFileRepository;

    public PhotoFileService(PhotoFileRepository photoFileRepository) {
        this.photoFileRepository = photoFileRepository;
    }

    public void storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                ///throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
                System.out.println("ZROB WYJATEK");
            }
            PhotoFile photoFile = new PhotoFile();
            photoFile.setFileName(fileName);
            photoFile.setFileType(file.getContentType());
            photoFile.setData(file.getBytes());
            photoFile.setRating(0L);
            photoFile.setVoteCounter(0L);
            photoFileRepository.save(photoFile);
        } catch (IOException ex) {
            //throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
            System.out.println("ZROB WYJATEK");
        }
    }

    public PhotoFile getFile(Long fileId) {
        //return PhotoFileRepository.findById(fileId).orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
        return photoFileRepository.findById(fileId).orElse(null);
    }

    public void deleteFile(Long id) {
        photoFileRepository.deleteById(id);
    }

    public void update(PhotoFile file){
        photoFileRepository.save(file);
    }

    public List<PhotoFile> findAll(){
        return photoFileRepository.findAll();
    }

}
