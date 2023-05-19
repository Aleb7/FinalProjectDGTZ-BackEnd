package it.Digitazon.finalProject.persistence.repositories;

import it.Digitazon.finalProject.persistence.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
