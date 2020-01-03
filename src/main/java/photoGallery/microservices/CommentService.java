package photoGallery.microservices;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import photoGallery.model.photoComment.PhotoComment;

import javax.xml.ws.Response;

@FeignClient(name = "photoComment-service")
public interface CommentService {


        @GetMapping("/comment/{id}")
        PhotoComment getComment(@PathVariable("id") String id);

        @PostMapping(value="/uploadComment", consumes = "application/json")
        Response postComment(@RequestPart("file") PhotoComment file);

        @DeleteMapping("/comment/delete/{id}")
        Response deleteComment(@PathVariable String id);

        @PutMapping(value = "/comment/update", consumes =  "application/json")
        Response updateComment(@RequestPart("file") PhotoComment file);


}
