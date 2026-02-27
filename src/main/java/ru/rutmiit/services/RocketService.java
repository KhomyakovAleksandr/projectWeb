package ru.rutmiit.services;

import ru.rutmiit.dto.RocketAddDto;
import ru.rutmiit.dto.RocketDTO;

import java.util.List;

public interface RocketService {

    List<RocketDTO> getAllRockets();

    void addRocket(RocketAddDto rocketAddDto);

    RocketDTO getRocketById(Long id);

    void deleteRocket(Long id);

    List<RocketDTO> searchRockets(String keyword);
}