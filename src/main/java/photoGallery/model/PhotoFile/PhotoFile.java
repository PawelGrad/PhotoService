package photoGallery.model.PhotoFile;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import photoGallery.model.photoComment.PhotoComment;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "photos")
public class PhotoFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;

    private String fileType;

    private Long voteCounter;

    private Long rating;

    @Lob
    private byte[] data;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "photo", orphanRemoval = true)
    private List<PhotoComment> comments = new ArrayList<>();

    public PhotoFile() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Long getVoteCounter() {
        return voteCounter;
    }

    public void setVoteCounter(Long voteCounter) {
        this.voteCounter = voteCounter;
    }

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public List<PhotoComment> getComments() {
        return comments;
    }

    public void setComments(List<PhotoComment> comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhotoFile photoFile = (PhotoFile) o;
        return id.equals(photoFile.id) &&
                fileName.equals(photoFile.fileName) &&
                fileType.equals(photoFile.fileType) &&
                voteCounter.equals(photoFile.voteCounter) &&
                rating.equals(photoFile.rating) &&
                Arrays.equals(data, photoFile.data) &&
                comments.equals(photoFile.comments);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, fileName, fileType, voteCounter, rating, comments);
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }
}
