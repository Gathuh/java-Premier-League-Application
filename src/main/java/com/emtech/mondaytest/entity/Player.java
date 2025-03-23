package com.emtech.mondaytest.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "player_static")
//@Data
//@ToString(exclude = "name") // Customize as needed
public class Player {
    @Id
    @Column(name = "name",unique = true)
    private String name;
    private String pos;
    private String nation;
    private Integer mp;
    private Integer age;

    private Integer starts;
    private Double min;
    private Double gls;
    private Double pk;
    private Double crdy;
    private Double crdr;
    private Double xg;
    private Double xag;
    private String team;


}
