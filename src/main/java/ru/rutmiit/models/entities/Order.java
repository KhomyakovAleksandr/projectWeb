package ru.rutmiit.models.entities;

import jakarta.persistence.*;
import ru.rutmiit.models.enums.OrderStatus;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {
    private User user;
    private Rocket rocket;
    private String cargoName;
    private Double cargoWeight;
    private String orbitType;
    private OrderStatus status;
    private LocalDateTime createdAt;

    public Order() { this.createdAt = LocalDateTime.now(); }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rocket_id", referencedColumnName = "id", nullable = false)
    public Rocket getRocket() { return rocket; }
    public void setRocket(Rocket rocket) { this.rocket = rocket; }

    @Column(nullable = false)
    public String getCargoName() { return cargoName; }
    public void setCargoName(String cargoName) { this.cargoName = cargoName; }

    @Column(nullable = false)
    public Double getCargoWeight() { return cargoWeight; }
    public void setCargoWeight(Double cargoWeight) { this.cargoWeight = cargoWeight; }

    public String getOrbitType() { return orbitType; }
    public void setOrbitType(String orbitType) { this.orbitType = orbitType; }

    @Enumerated(EnumType.STRING)
    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}