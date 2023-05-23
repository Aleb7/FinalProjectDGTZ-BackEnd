package it.Digitazon.finalProject.persistence.repositories;

import it.Digitazon.finalProject.persistence.entities.BookedTrip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookedTripRepository extends JpaRepository <BookedTrip,Long> {
}

//per fornire operazioni di accesso e gestione dei dati
//per l'entità BookedTrip utilizzando un'implementazione predefinita.
