package SafeSafe;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

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
    ModelAndView handleFileUpload(@RequestParam("myFile") MultipartFile file, @RequestParam("email") String email) {
        System.out.println(email);
        ModelAndView mav = new ModelAndView();

        if (!file.isEmpty()) {
            String name = file.getName();
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(name + "-uploaded")));
                stream.write(bytes);
                stream.close();
                mav.addObject("message", "Uploaded");
                return new ModelAndView(new RedirectView("/home.html"));
            } catch (Exception e) {
                mav.addObject("message", "Not Uploaded");
                return new ModelAndView(new RedirectView("/home.html"));
            }
        } else {
            mav.addObject("message", "Not Uploaded");
            return new ModelAndView(new RedirectView("/home.html"));
        }
    }

}
