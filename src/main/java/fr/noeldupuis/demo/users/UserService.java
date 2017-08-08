package fr.noeldupuis.demo.users;

import fr.noeldupuis.demo.DTO.UserDTO;
import fr.noeldupuis.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public UserEntity createUser(String name, String firstName, String password, String email, String classe) {
        UserEntity user = new UserEntity(name, firstName, passwordEncoder.encode(password), email, classe);
        userRepository.save(user);
        return user;
    }

    public void changerMail(String ancienMail, String nouveauMail) {
        userRepository.findAllByEmail(ancienMail).stream().forEach(user -> {
            user.setEmail(nouveauMail);
            userRepository.save(user);
        });
    }

    public void changerMotDePasse(String mail, String nouveauMDP) {
        userRepository.findAllByEmail(mail).stream().forEach(user -> {
            user.setPassword(passwordEncoder.encode(nouveauMDP));
            userRepository.save(user);
        });
    }

    public UserDTO getUser(String mail) {
        UserEntity user = userRepository.findAllByEmail(mail).get(0);
        return createDTO(user);
    }

    public void approveUser(String mail) {
        UserEntity user = userRepository.findAllByEmail(mail).get(0);
        user.setDroits(Droits.ROLE_VALIDE);
        userRepository.save(user);
    }

    private UserDTO createDTO(UserEntity user) {
        return new UserDTO(user.getName(), user.getFirstName(), user.getEmail(), user.getClasse(), user.getDroits());
    }
}
