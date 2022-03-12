package web.repos;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import web.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT p FROM User p JOIN FETCH p.roles WHERE p.username = (:username)")
    User getUserByName(String username);

    @Query("select u from User u where u.id = ?1")
    User getUserById(Long id);
}
