package it.Digitazon.finalProject.persistence.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
@Table(name = "bookedTrips")
public class BookedTrip {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_user")
    @OnDelete(action= OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_trip")
    @OnDelete(action= OnDeleteAction.CASCADE)
    private Trip trip;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }


}
