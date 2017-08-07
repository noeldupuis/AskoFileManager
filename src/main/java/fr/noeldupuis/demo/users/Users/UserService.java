package fr.noeldupuis.demo.users.Users;

import fr.noeldupuis.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserEntity createUser(String name, String firstName, String password, String email, String classe) {
        UserEntity user = new UserEntity(name, firstName, password, email, classe);
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
            user.setPassword(nouveauMDP);
            userRepository.save(user);
        });
    }

    public UserEntity getUser(String mail, String motDePasse) {
        UserEntity user = userRepository.findAllByEmail(mail).get(0);
        if (user.getPassword().equals(motDePasse)) {
            return user;
        } else {
            return null;
        }
    }

    public void approveUser(String mail) {
        UserEntity user = userRepository.findAllByEmail(mail).get(0);
        user.setDroits(Droits.VALIDE);
        userRepository.save(user);
    }
}
