package pk.edu.suk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pk.edu.suk.model.User;
import pk.edu.suk.repository.UserRepository;
import pk.edu.suk.service.util.CrudOperation;

import java.util.List;

/**
 * Created by Arshay on 10/27/2018.
 */

@Service
public class UserService implements CrudOperation<User>{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public User findByUserEmail(String userName) {
        return userRepository.findByUserEmail(userName);
    }

    @Override
    public boolean save(User user) {
        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        userRepository.save(user);
        return userRepository.findById(user.getUserId()).isPresent();
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).isPresent() ? userRepository.findById(id).get() : null;
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
