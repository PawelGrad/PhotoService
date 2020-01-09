package photoGallery.model.PhotoFile;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import photoGallery.exceptions.PhotoNotFoundException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


@Service
public class PhotoFileService {

    private PhotoFileRepository photoFileRepository;

    public PhotoFileService(PhotoFileRepository photoFileRepository) {
        this.photoFileRepository = photoFileRepository;
    }

    public void storeFile(MultipartFile file) {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            PhotoFile photoFile = new PhotoFile();
            photoFile.setFileName(fileName);
            photoFile.setFileType(file.getContentType());
            photoFile.setData(file.getBytes());
            photoFile.setRating(0L);
            photoFile.setVoteCounter(0L);
            photoFileRepository.save(photoFile);
        } catch (IOException ex) {
            throw new PhotoNotFoundException("File not found");
        }

    }

    public PhotoFile getFile(Long fileId) {
        return photoFileRepository.findById(fileId).orElseThrow(() -> new PhotoNotFoundException("File not found with id " + fileId));
    }

    public void deleteFile(Long id) {
        photoFileRepository.deleteById(id);
    }

    public void update(PhotoFileDTO file){
        System.out.println(file.getComments().size());
        photoFileRepository.save(convertToEntity(file));
    }

    public List<PhotoFileDTO> findAll(){
        List<PhotoFile> photos = photoFileRepository.findAll();
        List<PhotoFileDTO> convertedPhotos = new ArrayList<>();
        photos.forEach(photo -> {
            convertedPhotos.add(convertToDTO(photo));
        });
        return convertedPhotos;
    }


    public void changeRating(Long rating, PhotoFile photoFile){
        System.out.println(rating);
        photoFile.setVoteCounter(photoFile.getVoteCounter()+1L);
        photoFile.setRating(photoFile.getRating()+rating);
        photoFileRepository.save(photoFile);
    }



    public String photoToString(byte[] data) {
        byte[] encodeBase64 = Base64.encodeBase64(data);
        return new String(encodeBase64);
    }

    public PhotoFileDTO convertToDTO(PhotoFile photofile){
        PhotoFileDTO photoDTO = new PhotoFileDTO();
        photoDTO.setId(photofile.getId());
        photoDTO.setData(photoToString(photofile.getData()));
        photoDTO.setRating(photofile.getRating());
        photoDTO.setFileName(photofile.getFileName());
        photoDTO.setFileType(photofile.getFileType());
        photoDTO.setVoteCounter(photofile.getVoteCounter());
        photoDTO.setComments(photofile.getComments());
        return photoDTO;
    }

    public PhotoFile convertToEntity(PhotoFileDTO photoDTO){
        PhotoFile photoEntity = new PhotoFile();
        photoEntity.setId(photoDTO.getId());
        photoEntity.setData(Base64.decodeBase64(photoDTO.getData()));
        photoEntity.setRating(photoDTO.getRating());
        photoEntity.setFileName(photoDTO.getFileName());
        photoEntity.setFileType(photoDTO.getFileType());
        photoEntity.setVoteCounter(photoDTO.getVoteCounter());
        photoEntity.setComments(photoDTO.getComments());
        return photoEntity;
    }

}
