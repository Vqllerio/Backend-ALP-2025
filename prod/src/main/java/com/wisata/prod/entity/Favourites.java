package com.wisata.prod.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "favourites")
@IdClass(FavouriteId.class)
public class Favourites {
    @Id
    @Column(name = "iduser")
    private Integer idUser;

    @Id
    @Column(name = "iddestination")
    private Integer idDestination;

    @Column(name = "timefavoritedat", insertable = false, updatable = false)
    private String timeFavoritedAt; // Just for reading the value

    // Constructors (exclude timestamp from constructor)
    public Favourites(Integer idUser, Integer idDestination) {
        this.idUser = idUser;
        this.idDestination = idDestination;
    }

    public void setUser(User user) {
        if (user != null) {
            this.idUser = user.getIdUser();
        } else {
            this.idUser = null;
        }
    }

    public void setDestination(Destination destination) {
        if (destination != null) {
            this.idDestination = destination.getIdDestination();
        } else {
            this.idDestination = null;
        }
    }

}