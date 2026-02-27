package ru.rutmiit.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderCreateDto {

    @NotNull
    private Long rocketId;

    @NotBlank(message = "Название груза обязательно")
    private String cargoName;

    @NotNull(message = "Укажите вес груза")
    @Positive(message = "Вес должен быть больше 0")
    private Double cargoWeight;

    @NotBlank(message = "Выберите тип орбиты")
    private String orbitType;

    @NotBlank(message = "Номер телефона обязателен для связи")
    private String phoneNumber;

    @Email(message = "Некорректный формат почты")
    @NotBlank(message = "Почта обязательна")
    private String contactEmail;
}