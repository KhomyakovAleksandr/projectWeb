package ru.rutmiit.services;

import ru.rutmiit.dto.RocketAddDto;
import ru.rutmiit.dto.RocketDTO;

import java.util.List;

public interface RocketService {
    // Получить список всех ракет для каталога
    List<RocketDTO> getAllRockets();

    // Сохранить новую ракету (для модератора)
    void addRocket(RocketAddDto rocketAddDto);

    // Найти одну ракету по ID (для страницы деталей)
    RocketDTO getRocketById(Long id);

    void deleteRocket(Long id);

    List<RocketDTO> searchRockets(String keyword);
}