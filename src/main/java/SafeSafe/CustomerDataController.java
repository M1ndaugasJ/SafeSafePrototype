package SafeSafe;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by Mindaugo on 2015-05-13.
 */
@RestController
public class CustomerDataController {

    @RequestMapping(value = "/customerFile", method = RequestMethod.POST)
    public
    @ResponseBody
    String handleFileUpload(@RequestParam("myFile") MultipartFile file, @RequestParam("email") String email) {
        System.out.println(email);

        if (!file.isEmpty()) {
            String name = file.getName();
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(name + "-uploaded")));
                stream.write(bytes);
                stream.close();
                return "You successfully uploaded " + name + " into " + name + "-uploaded !";
            } catch (Exception e) {
                return "You failed to upload " + name + " => " + e.getMessage();
            }
        } else {
            return "The selected file was empty and could not be uploaded.";
        }
    }

}
