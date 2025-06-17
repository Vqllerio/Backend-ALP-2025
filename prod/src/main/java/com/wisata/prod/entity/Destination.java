package com.wisata.prod.entity;

import jakarta.persistence.*;
// import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "destinations")

public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @NotBlank
    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private float rating;

    @Column(nullable = false)
    private int reviews;

    @Column(nullable = false)
    private int userRating;

    @Column(nullable = false)
    private String category;
}
