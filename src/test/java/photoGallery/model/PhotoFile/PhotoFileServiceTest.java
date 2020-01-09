package photoGallery.model.PhotoFile;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import org.apache.tomcat.util.codec.binary.Base64;
import photoGallery.model.photoComment.PhotoComment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;


@RunWith(MockitoJUnitRunner.class)
public class PhotoFileServiceTest {


    @Mock
    PhotoFileRepository photoFileRepository;

    @InjectMocks
    private PhotoFileService photoFileService;




    @Test
    public void changeRatingTest() {
        PhotoFile photo = new PhotoFile();
        photo.setRating(0L);
        photo.setVoteCounter(0L);

        photoFileService.changeRating(5L,photo);

        Assert.assertEquals(Long.valueOf(5),photo.getRating());
        Assert.assertEquals(Long.valueOf(1),photo.getVoteCounter());
    }

    @Test
    public void photoToStringTest() {

        byte[] bytes = new byte[20];
        new Random().nextBytes(bytes);

        String result = photoFileService.photoToString(bytes);

        assertTrue(Arrays.equals(bytes,Base64.decodeBase64(result)));

    }

    @Test
    public void convertersTest() {
        PhotoComment comment = new PhotoComment();
        comment.setCommentAuthor("123");
        comment.setCommentText("456");
        comment.setId(1L);
        List<PhotoComment> comments = new ArrayList<>();
        comments.add(comment);

        byte[] bytes = new byte[20];
        new Random().nextBytes(bytes);

        PhotoFile photo = new PhotoFile();
        photo.setRating(0L);
        photo.setVoteCounter(0L);
        photo.setFileName("hurr");
        photo.setFileType("durr");
        photo.setId(1L);
        photo.setComments(comments);
        photo.setData(bytes);

        PhotoFile convertedPhoto = photoFileService.convertToEntity(photoFileService.convertToDTO(photo));

        assertTrue(photo.equals(convertedPhoto));



    }


}