package com.example.springjdbcapi;

public class PersonnesRepository extends CrudRepository<Personnes, Long> {

    @Query("select firstName, lastName from User u where u.emailAddress = :email")
    Personnes findByEmailAddress(@Param("prenom") String prenom);
}