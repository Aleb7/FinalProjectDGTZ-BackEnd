package it.Digitazon.finalProject.services;

import it.Digitazon.finalProject.persistence.entities.Trip;
import it.Digitazon.finalProject.persistence.repositories.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TripService {

    @Autowired
    private TripRepository tripRepository;

    public List<Trip> getAll() {
        return tripRepository.findAll();
    }

    public Trip getById (long id) {
        Optional<Trip> optionalTrip = tripRepository.findById(id);
        if (optionalTrip.isEmpty()) {
            throw new IllegalStateException("Book not found ");
        }
        return optionalTrip.get();
    }

    public Trip create (Trip newTrip) {
        newTrip= tripRepository.save(newTrip);
        return newTrip;
    }

    public Trip update (long id, Trip updateTrip){
        Optional<Trip> optionalTrip = tripRepository.findById(id);
        if (optionalTrip.isEmpty()) {
            throw new IllegalStateException("Trip not found ");
        }
        Trip entityToUpdate = optionalTrip .get();
        updateTrip.setId(entityToUpdate.getId());
        updateTrip= tripRepository.save(updateTrip);
        return updateTrip;

    }

    public Trip delete (long id) {
        Optional<Trip> optionalTrip = tripRepository.findById(id);
        System.out.println(optionalTrip);
        if (optionalTrip.isEmpty()) {
            throw new IllegalStateException("Trip not found ");
        }
        Trip entityToDelete = optionalTrip.get();
        System.out.println(entityToDelete);
        tripRepository.delete(entityToDelete);
        return entityToDelete;

    }
}
