package fr.m2i.spring.repository;

import fr.m2i.spring.model.Client;
import fr.m2i.spring.model.Prestation;
import fr.m2i.spring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //@Query("SELECT u FROM User WHERE u.email = :p")
    public User getUserByEmail(String email);

    public User findByEmail(String email);

}
