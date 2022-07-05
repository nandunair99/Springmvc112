package com.narola.spring.repository;

import com.narola.spring.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findByFirstName(String fname);

    List<User> findByFirstNameAndLastName(String fname, String lastName);

    @Query("select u from User u where u.email = ?1 and u.firstName = ?2")
    List<User> findByFName(String emailId, String fname);

    @Query("select u from User u where u.email = :emailId and u.firstName = :fname")
    List<User> findByFNameV2(@Param("fname") String fname, @Param("emailId") String emailId);

}
