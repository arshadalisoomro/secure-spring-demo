package pk.edu.suk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pk.edu.suk.model.User;

/**
 * Created by Arshay on 10/27/2018.
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    User findByUserEmail(String userEmail);
}
