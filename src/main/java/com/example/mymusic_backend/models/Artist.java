package com.example.mymusic_backend.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;


    @OneToMany(cascade = CascadeType.REMOVE)
    private Set<Album> albums = new HashSet<>();

    @OneToOne
    private Image logo;







}
