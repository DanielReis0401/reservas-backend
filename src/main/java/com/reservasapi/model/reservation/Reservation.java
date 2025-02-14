package com.reservasapi.model.reservation;

import com.reservasapi.model.passenger.Passenger;
import com.reservasapi.model.servico.ReservationService;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reservations")
@Builder
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

    @Column(name = "creation_date", nullable = false)
    private LocalDate creationDate;

    @Column(name = "total_price", nullable = false)
    @Setter(AccessLevel.NONE)
    private double totalPrice;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}
    )
    @JoinTable(
            name = "PASSENGER_RESERVATION_MAPPING",
            joinColumns = @JoinColumn(name = "passenger_id"),
            inverseJoinColumns = @JoinColumn(name = "reservation_id")
    )
    private List<Passenger> passengers = new ArrayList<>();

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "reservation_id")
    private List<ReservationService> services = new ArrayList<>();

    /**
     * Adds a passenger to the reservation.
     *
     * @param passenger the passenger to be added
     */
    public void addPassenger(Passenger passenger) {
        passengers.add(passenger);
    }

    /**
     * Removes a passenger from the reservation.
     *
     * @param passenger the passenger to be removed
     */
    public void removePassenger(Passenger passenger) {
        passengers.remove(passenger);
    }

    /**
     * Adds a service to the reservation.
     *
     * @param service the service to be added
     */
    public void addService(ReservationService service) {
        services.add(service);
    }

    /**
     * Removes a service from the reservation.
     *
     * @param service the service to be removed
     */
    public void removeService(ReservationService service) {
        services.remove(service);
    }

    /**
     * Calculates the total price of the reservation based on the services and the number of passengers.
     */
    public double getTotalPrice() {
        return services.stream().mapToDouble(ReservationService::getPrice).sum()
                * passengers.size();
    }

    /**
     * Updates the total price of the reservation before saving it.
     */
    @PrePersist
    @PreUpdate
    private void setTotalPrice() {
        this.totalPrice = getTotalPrice();
    }

}
