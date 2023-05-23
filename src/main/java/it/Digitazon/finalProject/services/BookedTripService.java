package it.Digitazon.finalProject.services;

import it.Digitazon.finalProject.persistence.entities.BookedTrip;
import it.Digitazon.finalProject.persistence.entities.User;
import it.Digitazon.finalProject.persistence.repositories.BookedTripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.List;
import java.util.Optional;

@Service //componente di servizio all'interno di framework spring
public class BookedTripService {

    @Autowired //per iniezioni dipendenze
    private BookedTripRepository bookedTripRepository; //per accedere ai metodi nel repository e interagire col db

    public List<BookedTrip> getAll() {
        return bookedTripRepository.findAll();  //per ottenere una lista di tutti i record della tabella delle prenotazioni viaggio
    }

    public BookedTrip getById (long id) { //prende in input un parametro id per recuperare uno specifico viaggio
        Optional<BookedTrip> optionalBookedTrip = bookedTripRepository.findById(id);        //cerca nel db un viaggio con l'id fornito e restituisce un oggetto
        if (optionalBookedTrip.isEmpty()) {
            throw new IllegalStateException("Il viaggio che stai cercando non è disponibile");  //se non esiste chiama un eccezione
        }
        return optionalBookedTrip.get(); //se esiste ritorna un istanza di bookedtrip contenente l'oggetto
    }

    public BookedTrip create(BookedTrip newBookedTrip) {        //prende in input un parametro di tipo bookedTrip
        if (newBookedTrip.getUser()==null || newBookedTrip.getTrip()==null){
            throw new IllegalStateException("User and Trip must not be null");      //se una delle proprietà user o trip sono nulle lancia un eccezione
        }                                                                           //nella nuova versione ho tolto tutte le possibilità null quindi cancellabile
        newBookedTrip= bookedTripRepository.save(newBookedTrip);        //sennò crea un nuovo oggetto nel db con id autogenerato tramite pk
        return newBookedTrip;  //restituisco al controller il dto con il nuovo id
    }

    public BookedTrip update(long id, BookedTrip updateBookedTrip) {        //prende 2 parametri: id & oggetto bookedtrip da aggiornare
        if (updateBookedTrip.getUser() == null || updateBookedTrip.getTrip() == null) {
            throw new IllegalStateException("User and Trip must not be null");
        }
        Optional<BookedTrip> optionalBookedTrip = bookedTripRepository.findById(id);    //se l'oggetto è vuoto richiama un eccezione
        if (optionalBookedTrip.isEmpty()) {
            throw new IllegalStateException("Entity not found");
        }
        BookedTrip entityToUpdate = optionalBookedTrip.get();       //richiama l'oggetto tramite "get"
        updateBookedTrip.setId(entityToUpdate.getId());             //viene impostato l'id dell'oggetto con l'id dell'entità esistente
        updateBookedTrip = bookedTripRepository.save(updateBookedTrip);     //salva la modifica
        return updateBookedTrip;                //ritorna l'oggetto modificato


    }

    public BookedTrip delete (long id) {        //prende come parametro l'id
        Optional<BookedTrip> optionalBookedTrip =bookedTripRepository.findById(id);     //cerca nel db l'oggetto in base all'id

        if (optionalBookedTrip.isEmpty()) {
            throw new IllegalStateException("Il viaggio che stai cercando non è disponibile");
        }

        BookedTrip entityToDelete = optionalBookedTrip.get();   //richiama l'oggetto tramite il get
        bookedTripRepository.delete(entityToDelete);        //lo cancella
        return entityToDelete;                  //richiama l'oggetto elimanto, per ulteriori operazioni (come la conferma di cancellazione)
    }


}
