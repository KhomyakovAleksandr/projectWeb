package ru.rutmiit.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rutmiit.dto.RocketAddDto;
import ru.rutmiit.dto.RocketDTO;
import ru.rutmiit.models.entities.Rocket;
import ru.rutmiit.repositories.RocketRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RocketServiceImpl implements RocketService {

    private final RocketRepository rocketRepository;
    private final ModelMapper modelMapper;

    public RocketServiceImpl(RocketRepository rocketRepository, ModelMapper modelMapper) {
        this.rocketRepository = rocketRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<RocketDTO> getAllRockets() {
        return rocketRepository.findAll()
                .stream()
                .map(rocket -> modelMapper.map(rocket, RocketDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void addRocket(RocketAddDto rocketAddDto) {
        // Превращаем DTO из формы в Entity для базы
        Rocket rocket = modelMapper.map(rocketAddDto, Rocket.class);

        // Устанавливаем доступность по умолчанию
        rocket.setAvailable(true);

        rocketRepository.save(rocket);
    }

    @Override
    public RocketDTO getRocketById(Long id) {
        return rocketRepository.findById(id)
                .map(rocket -> modelMapper.map(rocket, RocketDTO.class))
                .orElseThrow(() -> new RuntimeException("Ракета с ID " + id + " не найдена"));
    }

    @Override
    @Transactional
    public void deleteRocket(Long id) {
        if (!rocketRepository.existsById(id)) {
            throw new RuntimeException("Ракета с ID " + id + " не найдена");
        }
        rocketRepository.deleteById(id);
    }

    @Override
    public List<RocketDTO> searchRockets(String keyword) {
        return rocketRepository.findAllByNameContainingIgnoreCase(keyword)
                .stream()
                .map(r -> modelMapper.map(r, RocketDTO.class))
                .collect(Collectors.toList());
    }
}