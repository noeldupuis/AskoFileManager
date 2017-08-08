package fr.noeldupuis.demo.fileManager;

import fr.noeldupuis.demo.DTO.CoursDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "cours/")
public class CoursController {

    @Autowired
    CoursService coursService;

    @RequestMapping(value = "/proposer", method = RequestMethod.POST)
    public ResponseEntity uploadFileHandler(@RequestParam("file") MultipartFile file,
                                            @RequestParam("email") String email,
                                            @RequestParam("name") String name,
                                            @RequestParam("description") String description) {
        return coursService.proposer(email, name, description, file);
    }

    @RequestMapping(value = "/listerDocuments", method = RequestMethod.GET)
    public ResponseEntity listerTousDocuments() {
        List<CoursDTO> cours = coursService.listerTousDocuments();
        return new ResponseEntity(cours, HttpStatus.CREATED);
    }
}
