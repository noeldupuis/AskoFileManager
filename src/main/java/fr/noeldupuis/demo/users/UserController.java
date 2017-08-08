package fr.noeldupuis.demo.users;

import fr.noeldupuis.demo.DTO.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user/")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="inscription", method = RequestMethod.POST, produces = "application/json")
    public void inscrireUnUtilisateur(@RequestParam(value="name") String userName,
                            @RequestParam(value="firstname") String userFirstname,
                            @RequestParam(value="password") String password,
                            @RequestParam(value="email") String email,
                            @RequestParam(value="class") String classe) {
        userService.createUser(userName, userFirstname, password, email, classe);
    }

    @RequestMapping(value="changerEmail", method = RequestMethod.POST, produces = "application/json")
    public void ChangerEmail(@RequestParam(value="ancienMail") String ancienMail,
                                @RequestParam(value="nouveauMail") String nouveauMail) {
        userService.changerMail(ancienMail, nouveauMail);
    }

    @RequestMapping(value="changerMotDePasse", method = RequestMethod.POST, produces = "application/json")
    public void ChangerMotDePasse(@RequestParam(value="mail") String mail,
                             @RequestParam(value="nouveauMotDePasse") String nouveauMDP){
        userService.changerMotDePasse(mail, nouveauMDP);
    }

    @RequestMapping(value="utilisateur", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getUser(@RequestParam(value="mail") String mail){
        UserDTO user = userService.getUser(mail);
        return user == null? new ResponseEntity(HttpStatus.BAD_REQUEST):new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value="approuver", method = RequestMethod.POST, produces = "application/json")
    public void approveUser(@RequestParam(value="mail") String mail){
        userService.approveUser(mail);
    }

}
