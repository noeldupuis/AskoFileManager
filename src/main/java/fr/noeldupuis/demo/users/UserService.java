package fr.noeldupuis.demo.users;

import fr.noeldupuis.demo.DTO.DTOService;
import fr.noeldupuis.demo.DTO.UserDTO;
import fr.noeldupuis.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    DTOService dtoService;

    @Autowired
    PasswordEncoder passwordEncoder;

    public UserEntity createUser(String name, String firstName, String password, String email, String classe) {
        UserEntity user = new UserEntity(name, firstName, passwordEncoder.encode(password), email, classe);
        userRepository.save(user);
        return user;
    }

    public void changerMail(String ancienMail, String nouveauMail) {
        UserEntity user = userRepository.findFirstByEmail(ancienMail);
        user.setEmail(nouveauMail);
        userRepository.save(user);
    }

    public void changerMotDePasse(String mail, String nouveauMDP) {
        UserEntity user = userRepository.findFirstByEmail(mail);
        user.setPassword(passwordEncoder.encode(nouveauMDP));
        userRepository.save(user);
    }

    public UserDTO getUser(String mail) {
        UserEntity user = userRepository.findFirstByEmail(mail);
        return dtoService.createDTO(user);
    }

    public void approveUser(String mail) {
        UserEntity user = userRepository.findFirstByEmail(mail);
        user.setDroits(Droits.ROLE_VALIDE);
        userRepository.save(user);
    }

    public List<UserDTO> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();
        return users.stream().map(userEntity -> dtoService.createDTO(userEntity)).collect(Collectors.toList());
    }
}
