package ru.rutmiit.models.enums;

public enum OrderStatus {
    PENDING,    // Ожидает одобрения (сразу после заказа)
    APPROVED,   // Одобрен модератором
    ASSEMBLING, // Подготовка/Сборка ракеты
    IN_FLIGHT,  // Ракета в космосе
    COMPLETED,  // Груз доставлен
    CANCELED    // Отменен
}