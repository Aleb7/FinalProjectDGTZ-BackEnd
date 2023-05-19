package it.Digitazon.finalProject.presentation.dto;

public class BookedTripDTO {
    private long id;
    private long idTrip;
    private long idUser;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public long getIdTrip() {
        return idTrip;
    }

    public void setIdTrip(long idTrip) {
        this.idTrip = idTrip;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }
}
