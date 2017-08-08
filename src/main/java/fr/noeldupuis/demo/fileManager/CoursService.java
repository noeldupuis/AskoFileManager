package fr.noeldupuis.demo.fileManager;

import fr.noeldupuis.demo.DTO.CoursDTO;
import fr.noeldupuis.demo.DTO.DTOService;
import fr.noeldupuis.demo.repositories.CoursRepository;
import fr.noeldupuis.demo.repositories.UserRepository;
import fr.noeldupuis.demo.users.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CoursService {

    private static Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    UserRepository userRepository;

    @Autowired
    DTOService dtoService;

    @Autowired
    CoursRepository coursRepository;

    private int fileUpload(MultipartFile file, String name, String path) {
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();

                // Creating the directory to store file
                File dir = new File(path);
                if (!dir.exists())
                    dir.mkdirs();

                // Create the file on server
                File serverFile = new File(dir.getAbsolutePath()
                        + File.separator + name);
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();

                return 0;
            } catch (Exception e) {
                e.printStackTrace();
                return 1;
            }
        } else {
            return 1;
        }
    }

    public ResponseEntity proposer(String email, String name, String description, MultipartFile file) {
        UserEntity user = userRepository.findFirstByEmail(email);
        CoursEntity cours = new CoursEntity(name, description, user);
        coursRepository.save(cours);
        String path = "/opt/spring/docs/" + user.getId();
        String newName = String.format("%07d", cours.getId()) + ".pdf";
        cours.setPath(path + File.separator + newName);
        coursRepository.save(cours);
        if (file.getOriginalFilename().endsWith(".pdf") && fileUpload(file, newName, path) == 0) {
            return new ResponseEntity(HttpStatus.CREATED);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    public List<CoursDTO> listerTousDocuments() {
        List<CoursEntity> cours = coursRepository.findAll();
        return cours.stream().map(coursEntity -> dtoService.createDTO(coursEntity)).collect(Collectors.toList());
    }

    public CoursEntity findCours(long id) {
        CoursEntity cours = coursRepository.findFirstById(id);
        return cours;
    }
}
