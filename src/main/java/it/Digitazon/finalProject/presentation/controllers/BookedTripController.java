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

@RestController
@RequestMapping("/bookedtrips")
@CrossOrigin(origins = "http://localhost:3000")

public class BookedTripController {

    @Autowired
    private UserService userService;
    @Autowired
    private TripService tripService;
    @Autowired
    private BookedTripService bookedTripService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<BookedTripDTO> getBookedTrips() {
        return bookedTripService.getAll()
                .stream()
                .map(this::convertToDto).toList();
    }

    @GetMapping ("/{id}")
    public BookedTripDTO getById( @PathVariable long id ) {
        return convertToDto(bookedTripService.getById(id)) ;
    }

    @PostMapping
    public BookedTripDTO createBookedTrip(@RequestBody BookedTripDTO newBookedTrip){
        BookedTrip bookedTrip = convertToEntity(newBookedTrip);
        bookedTrip =  bookedTripService.create(bookedTrip);
        return convertToDto(bookedTrip);
    }

    @PutMapping("/update/{id}")
    public  BookedTripDTO updateBookedTrip ( @PathVariable long id, @RequestBody BookedTripDTO updateBookedTrip ){
        BookedTrip bookedTrip  = convertToEntity(updateBookedTrip);
        bookedTrip = bookedTripService.update(id,bookedTrip);
        return  convertToDto(bookedTrip);
    }

    @DeleteMapping("/delete/{id}")
    public BookedTripDTO deleteBookedTrip (@PathVariable long id){
        return convertToDto(bookedTripService.delete(id));
    }

    //per richiamare tutti gli user che hanno prenotato il viaggio con {id}
    @GetMapping("/trip/{id}")
    public List<UserDTO> getUsersByTrip(@PathVariable long id) {
        Trip trip = tripService.getById(id);
        List<BookedTrip> bookedTrips = trip.getBookedTrips();
        return bookedTrips.stream()
                .map(BookedTrip::getUser)
                .map(this::convertToUserDTO)
                .toList();
    }
    private BookedTripDTO convertToDto(BookedTrip bookedTrip){
        return modelMapper.map(bookedTrip, BookedTripDTO.class);

    }

    private BookedTrip convertToEntity(BookedTripDTO dto){
        return modelMapper.map(dto,BookedTrip.class);
    }


    private TripDTO convertToTripDTO (Trip dto) {
        return modelMapper.map(dto, TripDTO.class);
    }

    private UserDTO convertToUserDTO (User user) {
        return modelMapper.map(user, UserDTO.class);
    }





}
