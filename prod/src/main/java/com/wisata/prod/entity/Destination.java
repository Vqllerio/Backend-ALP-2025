package com.wisata.prod.entity;

import java.math.BigDecimal;

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
    private Integer idDestination;

    // @NotBlank
    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, length = 512)
    private String image;

    @Column(nullable = true, precision = 10, scale = 9)
    private BigDecimal rating;

    @Column(nullable = true)
    private Integer reviews;

    @Column(nullable = false)
    private String category;

    @Column(name = "history_title", length = 255)
    private String historyTitle;

    @Column(name = "history_content", columnDefinition = "TEXT")
    private String historyContent;

    @Column(name = "history_image", length = 512)
    private String historyImage;
}
