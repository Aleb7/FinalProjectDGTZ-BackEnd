package it.Digitazon.finalProject.services;

import it.Digitazon.finalProject.persistence.entities.BookedTrip;
import it.Digitazon.finalProject.persistence.entities.User;
import it.Digitazon.finalProject.persistence.repositories.BookedTripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.List;
import java.util.Optional;

@Service
public class BookedTripService {

    @Autowired
    private BookedTripRepository bookedTripRepository;

    public List<BookedTrip> getAll() {
        return bookedTripRepository.findAll();
    }

    public BookedTrip getById (long id) {
        Optional<BookedTrip> optionalBookedTrip = bookedTripRepository.findById(id);
        if (optionalBookedTrip.isEmpty()) {
            throw new IllegalStateException("Il viaggio che stai cercando non è disponibile");
        }
        return optionalBookedTrip.get();
    }

    public BookedTrip create(BookedTrip newBookedTrip) {
        if (newBookedTrip.getUser()==null || newBookedTrip.getTrip()==null){
            throw new IllegalStateException("User and Trip must not be null");
        }
        newBookedTrip= bookedTripRepository.save(newBookedTrip);
        return newBookedTrip;  //restituisco al controller il dto con il nuovo id
    }

    public BookedTrip update(long id, BookedTrip updateBookedTrip) {
        if (updateBookedTrip.getUser() == null || updateBookedTrip.getTrip() == null) {
            throw new IllegalStateException("User and Trip must not be null");
        }
        Optional<BookedTrip> optionalBookedTrip = bookedTripRepository.findById(id);
        if (optionalBookedTrip.isEmpty()) {
            throw new IllegalStateException("Entity not found");
        }
        BookedTrip entityToUpdate = optionalBookedTrip.get();
        updateBookedTrip.setId(entityToUpdate.getId());
        updateBookedTrip = bookedTripRepository.save(updateBookedTrip);
        return updateBookedTrip;


    }

    public BookedTrip delete (long id) {
        Optional<BookedTrip> optionalBookedTrip =bookedTripRepository.findById(id);

        if (optionalBookedTrip.isEmpty()) {
            throw new IllegalStateException("Il viaggio che stai cercando non è disponibile");
        }

        BookedTrip entityToDelete = optionalBookedTrip.get();
        bookedTripRepository.delete(entityToDelete);
        return entityToDelete;
    }


}
