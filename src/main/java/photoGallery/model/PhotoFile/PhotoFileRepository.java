package photoGallery.model.PhotoFile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface PhotoFileRepository extends JpaRepository<PhotoFile,Long> {
}
