package ru.rutmiit.dto;

import jakarta.validation.constraints.*;

public class RocketAddDto {
    @NotBlank(message = "Название не может быть пустым")
    private String name;

    @NotBlank(message = "Описание обязательно")
    private String description;

    @NotNull(message = "Укажите цену")
    @Positive(message = "Цена должна быть положительной")
    private Double price;

    @NotNull(message = "Укажите грузоподъемность")
    private Double payloadCapacity;

    public RocketAddDto() {}

    // Геттеры и сеттеры
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    public Double getPayloadCapacity() { return payloadCapacity; }
    public void setPayloadCapacity(Double payloadCapacity) { this.payloadCapacity = payloadCapacity; }
}