package br.com.concrete.desafio.repository;

import br.com.concrete.desafio.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    User findByEmail(String email);

//    @Query(" select u from User u where u.email = :email and u.password = :password ")
//    User findEmailAndSenha(@Param("email") String id, @Param("password") String password);
}
