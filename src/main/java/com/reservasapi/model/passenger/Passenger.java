package com.reservasapi.model.passenger;

import com.reservasapi.model.reservation.Reservation;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "passengers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Passenger Model")
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique Identifier of the Passenger", required = true, example = "1")
    private Long id;

    @Column(nullable = false)
    @Schema(description = "Name of the Passenger", required = true, example = "Pedro")
    private String name;

    @Column(nullable = false)
    @Schema(description = "Age of the Passenger", required = true, example = "30")
    private int age;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Schema(description = "Passenger type", required = true, example = "ADULT", allowableValues = {"ADULT", "CHILD", "INFANT"})
    private PassengerType type;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinTable(name = "PASSENGER_RESERVATION_MAPPING", joinColumns = @JoinColumn(name = "passenger_id"),
            inverseJoinColumns = @JoinColumn(name = "reservation_id"))
    @Schema(description = "List of reservations associated with the Passenger")
    private List<Reservation> reservation = new ArrayList<>();
}
