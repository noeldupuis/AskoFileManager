package fr.noeldupuis.demo.DTO;

import fr.noeldupuis.demo.fileManager.CoursEntity;
import fr.noeldupuis.demo.users.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class DTOService {

    public UserDTO createDTO(UserEntity user) {
        return new UserDTO(user.getName(), user.getFirstName(), user.getEmail(), user.getClasse(), user.getDroits());
    }

    public CoursDTO createDTO(CoursEntity cours) {
        return new CoursDTO(cours.getId(), cours.getName(), cours.getDescription(), cours.getPath(), createDTO(cours.getAuteur()), cours.getStatus());
    }
}
