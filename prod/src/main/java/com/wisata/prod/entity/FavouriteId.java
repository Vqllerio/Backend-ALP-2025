package com.wisata.prod.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.criteria.CriteriaBuilder.In;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class FavouriteId implements Serializable {
    private Integer idUser;
    private Integer idDestination;


    // Constructor
    // Lombok's @AllArgsConstructor already generates this constructor.

    // Getters and setters
    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdDestination() {
        return idDestination;
    }

    public void setIdDestination(Integer idDestination) {
        this.idDestination = idDestination;
    }

    // equals() and hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        FavouriteId that = (FavouriteId) o;
        return Objects.equals(idUser, that.idUser) &&
                Objects.equals(idDestination, that.idDestination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, idDestination);
    }
}
