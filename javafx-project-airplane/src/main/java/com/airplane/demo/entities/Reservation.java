package com.airplane.demo.entities;

import javafx.beans.property.SimpleIntegerProperty;

public class Reservation {

    public SimpleIntegerProperty idReservation;
    public personne client;
    public Vol vol;
    public Reservation(SimpleIntegerProperty idReservation, personne client, Vol vol) {
        this.idReservation = idReservation;
        this.client = client;
        this.vol = vol;
    }

    public int getIdReservation() {
        return idReservation.get();
    }

    public SimpleIntegerProperty idReservationProperty() {
        return idReservation;
    }

    public void setIdReservation(int idReservation) {
        this.idReservation.set(idReservation);
    }

    public personne getClient() {
        return client;
    }

    public void setClient(personne client) {
        this.client = client;
    }

    public Vol getVol() {
        return vol;
    }

    public void setVol(Vol vol) {
        this.vol = vol;
    }
}