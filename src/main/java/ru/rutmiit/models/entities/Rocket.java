package ru.rutmiit.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "rockets")
public class Rocket extends BaseEntity {
    private String name;
    private String description;
    private Double payloadCapacity;
    private Double price;
    private String imageUrl;
    private boolean isAvailable = true;

    public Rocket() {}

    @Column(unique = true, nullable = false)
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Column(columnDefinition = "TEXT")
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    @Column(nullable = false)
    public Double getPayloadCapacity() { return payloadCapacity; }
    public void setPayloadCapacity(Double payloadCapacity) { this.payloadCapacity = payloadCapacity; }

    @Column(nullable = false)
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    @Column(nullable = false)
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }
}