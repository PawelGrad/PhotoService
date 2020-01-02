package photoGallery.model.PhotoFile;

import org.springframework.data.jpa.repository.JpaRepository;

public class PhotoFileService {

    private PhotoFileRepository photoFileRepository;

    public PhotoFileService(PhotoFileRepository photoFileRepository) {
        this.photoFileRepository = photoFileRepository;
    }

}
