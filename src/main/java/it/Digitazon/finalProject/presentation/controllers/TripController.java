package it.Digitazon.finalProject.presentation.controllers;

import it.Digitazon.finalProject.persistence.entities.Trip;
import it.Digitazon.finalProject.presentation.dto.TripDTO;
import it.Digitazon.finalProject.services.TripService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trips")
@CrossOrigin(origins = "http://localhost:3000")
public class TripController {

    @Autowired
    private TripService tripService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<TripDTO> getAll() {
        return tripService.getAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public TripDTO getById(@PathVariable long id) {
        return convertToDTO(tripService.getById(id));
    }

    @PostMapping
    public TripDTO createTrip(@RequestBody TripDTO newTrip) {
        Trip trip = convertToEntity(newTrip);
        trip= tripService.create(trip);
        return convertToDTO(trip);
    }

    @PutMapping("/update/{id}")
    public TripDTO updateTrip(@PathVariable long id, @RequestBody TripDTO updateTrip) {
        Trip trip = convertToEntity(updateTrip);
        trip= tripService.update(id, trip);
        return convertToDTO(trip);
    }

    @DeleteMapping("/delete/{id}")
    public TripDTO deleteTrip(@PathVariable long id) {
        return convertToDTO(tripService.delete(id));
    }

    private TripDTO convertToDTO (Trip trip) {
        return modelMapper.map(trip,TripDTO.class);
    }

    private Trip convertToEntity (TripDTO dto) {
        return modelMapper.map(dto, Trip.class);
    }


}
