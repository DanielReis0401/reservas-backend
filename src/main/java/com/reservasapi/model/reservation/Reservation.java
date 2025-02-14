package com.reservasapi.model.reservation;

import java.time.LocalDate;
import java.util.List;

import com.reservasapi.model.passenger.Passenger;
import com.reservasapi.model.servico.ReservationService;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "reservations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @Column(name = "reservation_date", nullable = false)
    private LocalDate reservationDate;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Passenger> passengers;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReservationService> reservationServices;

    @Column(name = "total_price")
    private double totalPrice = 0.0;

    /**
     * Calculates the total price of the reservation based on the services and the number of passengers.
     */
    public void calculateTotalPrice() {
        if (reservationServices == null || passengers == null)
            return;

        this.totalPrice = reservationServices.stream().mapToDouble(ReservationService::getPrice).sum() // Sum of the prices of all services
                * passengers.size();
    }

    @PrePersist
    @PreUpdate
    private void preSave() {
        calculateTotalPrice();
    }
}
