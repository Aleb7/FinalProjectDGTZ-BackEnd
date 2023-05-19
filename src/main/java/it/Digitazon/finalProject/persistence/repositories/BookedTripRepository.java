package it.Digitazon.finalProject.persistence.repositories;

import it.Digitazon.finalProject.persistence.entities.BookedTrip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookedTripRepository extends JpaRepository <BookedTrip,Long> {
}
