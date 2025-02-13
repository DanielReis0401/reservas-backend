package com.reservasapi.model.reservation;

import java.time.LocalDate;
import java.util.List;

import com.reservasapi.model.passenger.Passenger;
import com.reservasapi.model.servico.Servico;

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
    private List<Servico> servicos;

    @Column(name = "total_price")
    private Double totalPrice;
    
    public void calculateTotalPrice(){
        if(servicos != null && passengers != null){     //Verifica se as listas nao sao nulas
            this.totalPrice = servicos.stream()         //Percorre a lista de servicos
                .mapToDouble(Servico::getPrice)        //Obtem o preco de cada servico
                .sum() * passengers.size();             //Soma todos os precos e multiplica pelo numero de passageiros
        } else {
            this.totalPrice = 0.0;                      //Se nao houver servicos ou passageiros define o total como zero
        }
    }

    @PrePersist
    @PreUpdate
    private void preSave(){
        calculateTotalPrice();
    }
}
