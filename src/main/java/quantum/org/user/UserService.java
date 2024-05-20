package quantum.org.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired private UserRepository userRepository;

    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public User get(Integer id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        }
        throw new UserNotFoundException("Could not found any user with the " + id);
    }


    public void delete(Integer id) throws UserNotFoundException {
        Long count = userRepository.countById(id);
        if (count == null || count == 0) {
            throw new UserNotFoundException("Could not found any user with the " + id);
        }
        userRepository.deleteById(id);
    }

}
