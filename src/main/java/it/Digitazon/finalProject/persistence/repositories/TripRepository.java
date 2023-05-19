package it.Digitazon.finalProject.persistence.repositories;

import it.Digitazon.finalProject.persistence.entities.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepository extends JpaRepository<Trip,Long> {
}
