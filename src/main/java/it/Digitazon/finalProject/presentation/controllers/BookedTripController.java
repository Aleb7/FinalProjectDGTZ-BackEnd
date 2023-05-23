package it.Digitazon.finalProject.presentation.controllers;

import it.Digitazon.finalProject.persistence.entities.BookedTrip;
import it.Digitazon.finalProject.persistence.entities.Trip;
import it.Digitazon.finalProject.persistence.entities.User;
import it.Digitazon.finalProject.presentation.dto.BookedTripDTO;
import it.Digitazon.finalProject.presentation.dto.TripDTO;
import it.Digitazon.finalProject.presentation.dto.UserDTO;
import it.Digitazon.finalProject.services.BookedTripService;
import it.Digitazon.finalProject.services.TripService;
import it.Digitazon.finalProject.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController  //gestisce le richieste http e restituisce un json
@RequestMapping("/bookedtrips")      //specifica il percorso di base per tutte le richieste
@CrossOrigin(origins = "http://localhost:3000")     //consente alle richieste proveniente da localhost:3000 di accedere al controller

public class BookedTripController {

    @Autowired      //iniezione delle dipendenze
    private UserService userService;    //inietta il servizio userService per la gestione degli utenti
    @Autowired
    private TripService tripService;    //inietta il servizio userTrip per la gestione dei viaggi
    @Autowired
    private BookedTripService bookedTripService;    //inietta il servizio userBT per la gestione dei viaggi prenotati
    @Autowired
    private ModelMapper modelMapper;   // offre funzionalità per la mappatura degli oggetti tra diverse classi e ne semplifica la conversione.

    //trasformazione in DTO serve per adattare l'oggetto che noi andiamo a ricercare attraverso la logica che immettiamo nel service
    // per poterlo eseguire e richiamare in base alle nostre esigenze

    @GetMapping
    public List<BookedTripDTO> getBookedTrips() {   //restituisce una lista di oggetti BookedTripDTO
        return bookedTripService.getAll()   //chiama il metodo getAll del BookedTripService per avere tutte le prenotazioni dei viaggi da DB
                .stream() //rappresenta una sequenza di elementi su cui possono essere eseguite operazioni di trasformazione e filtraggio
                .map(this::convertToDto)    //conversione in DTO(data trasform object) per eseguire CRUD
                .toList(); //raccogliere tutti i nuovi elementi trasformati in una nuova lista
    }

    @GetMapping ("/{id}")   //richiama il @RequestMapping quindi /bookedtrips + /{id}
    public BookedTripDTO getById( @PathVariable long id ) {     //prende come parametro l'id (grazie ad useParams su VS
        return convertToDto(bookedTripService.getById(id)) ;    //per ottenere una specifica prenotazione di viaggio dal database
    }

    @PostMapping        //non viene specificato un map specifico perchè viene creata nella stessa pagina del @RequestMapping
    public BookedTripDTO createBookedTrip(@RequestBody BookedTripDTO newBookedTrip){    //Requestbody permette di estrarre i dati necessari da mappare nell'oggetto dto
        BookedTrip bookedTrip = convertToEntity(newBookedTrip); //viene trasformato il DTO in base ai dati e alla struttura dell'entità che serve a noi che verrà salvata nel DB
        bookedTrip =  bookedTripService.create(bookedTrip); //metodo create per aggiungere un nuovo viaggio nel db
        return convertToDto(bookedTrip);    //adatta l'oggetto bookedtrip ai requisiti di comunicazione specifici
    }

    @PutMapping("/update/{id}") //endpoint con richiamo al parametro id
    public  BookedTripDTO updateBookedTrip ( @PathVariable long id, @RequestBody BookedTripDTO updateBookedTrip ){ //prende come input id e oggetto viaggio selezionato da modificare
        BookedTrip bookedTrip  = convertToEntity(updateBookedTrip); //trasforma l'oggetto per la modifica nel db
        bookedTrip = bookedTripService.update(id,bookedTrip); //aggiorna l'oggetto nel database attraverso la logica del service
        return  convertToDto(bookedTrip);   //ritrasformazione in un oggetto con i dati aggiornati
    }

    @DeleteMapping("/delete/{id}") //endpoint con richiamo id
    public BookedTripDTO deleteBookedTrip (@PathVariable long id){  //prende come oggetto solo l'id (tanto non sono necessarie modifiche, l'oggetto verrà cancellato)
        return convertToDto(bookedTripService.delete(id));  //ritorna l'oggetto che è stato eliminato da db
    }

    //per richiamare tutti gli user che hanno prenotato il viaggio con {id}
    @GetMapping("/trip/{id}")
    public List<UserDTO> getUsersByTrip(@PathVariable long id) {    //restituisce una lista di user prendendo come parametro l'id
        Trip trip = tripService.getById(id);        //per ottenere l'oggetto trip corrispondente all'id fornito
        List<BookedTrip> bookedTrips = trip.getBookedTrips();       //restituisce la lista delle prenotazioni associate al {id}trip
        return bookedTrips.stream()                 //restituisce elementi da elaborare
                .map(BookedTrip::getUser)       //per ottenere ciascun user associato al id viaggio
                .map(this::convertToUserDTO)      //trasformare i dati recepiti da DB in un oggetto DTO
                .toList();                      // raccolti in una nuova lista
    }

    //sezione modelMapper per trasformare elementi da entità a dto e viceversa
    private BookedTripDTO convertToDto(BookedTrip bookedTrip){      //trasformazione elementi bookedtrip in un oggetto bookedtripDTO
        return modelMapper.map(bookedTrip, BookedTripDTO.class);

    }

    private BookedTrip convertToEntity(BookedTripDTO dto){      //trasformazione da bookedtripdto a oggetto bookedtrip
        return modelMapper.map(dto,BookedTrip.class);
    }


    private TripDTO convertToTripDTO (Trip dto) {           //trasforma oggetto trip in tripDTO con i dati oggetto trip
        return modelMapper.map(dto, TripDTO.class);
    }

    private UserDTO convertToUserDTO (User user) {      //uguale per user
        return modelMapper.map(user, UserDTO.class);
    }





}
