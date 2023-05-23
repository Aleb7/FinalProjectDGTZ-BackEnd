package it.Digitazon.finalProject.persistence.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "trips")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)         //PK autoincrementale
    private long id;

    @Column(name = "name" )
    private String name;

    @Column(name = "description" )
    private String description;



    @Column(name = "departure")
    private Date departure;

    @Column(name = "arrival" )
    private Date arrival;


    @Column(name = "travel_price", columnDefinition = "DECIMAL(5,2)") //mette 5 cifre totali e 2 cifre decimali
    private double travelPrice;

    @Column(name = "flight_price", columnDefinition = "DECIMAL(5,2)")
    private double flightPrice;

    @Column(name = "imgSource", columnDefinition = "TEXT")
    private String imgSource;

    @OneToMany(mappedBy = "trip")             //  indica che l'entità corrente ha una relazione di uno a molti con l'entità BookedTrip,
    private List<BookedTrip>bookedTrips;         //per ogni viaggio posso avere un istanza della classe prenotazione viaggio collegata al viaggio specifico






    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDeparture() {
        return departure;
    }

    public void setDeparture(Date departure) {
        this.departure = departure;
    }

    public Date getArrival() {
        return arrival;
    }

    public void setArrival(Date arrival) {
        this.arrival = arrival;
    }



    public double getTravelPrice() {
        return travelPrice;
    }

    public void setTravelPrice(double travelPrice) {
        this.travelPrice = travelPrice;
    }

    public double getFlightPrice() {
        return flightPrice;
    }

    public void setFlightPrice(double flightPrice) {
        this.flightPrice = flightPrice;
    }

    public List<BookedTrip> getBookedTrips() {
        return bookedTrips;
    }

    public void setBookedTrips(List<BookedTrip> bookedTrips) {
        this.bookedTrips = bookedTrips;
    }

    public String getImgSource() {
        return imgSource;
    }

    public void setImgSource(String imgSource) {
        this.imgSource = imgSource;
    }
}
