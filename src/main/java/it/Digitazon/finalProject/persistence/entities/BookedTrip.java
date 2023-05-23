package it.Digitazon.finalProject.persistence.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
@Table(name = "bookedTrips")            //nome tabella
public class BookedTrip {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)  //Primary Key autoincrementale
    private long id;

    @ManyToOne                                  //relazione di molti a uno tra due entità
    @JoinColumn(name = "id_user")               //creazione colonna
    @OnDelete(action= OnDeleteAction.CASCADE)    //si usa nel ManyToOne per eliminare tutti gli oggetti correlati
    private User user;                            //(messo perchè mi limitava la possibilità di cancellare un viaggio se era presente un id_user)

    @ManyToOne
    @JoinColumn(name = "id_trip")
    @OnDelete(action= OnDeleteAction.CASCADE)
    private Trip trip;

    //  getter e i setter consentono di accedere ai dati delle entità
    //  e di modificarli in modo controllato

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
