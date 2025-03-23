package tandev.springassignment.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tandev.springassignment.dto.PositionDTO;
import tandev.springassignment.entity.EipMPosition;
import tandev.springassignment.repository.EipMPositionRepository;
import tandev.springassignment.service.PositionService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PositionServiceImpl implements PositionService {

    @Autowired
    private EipMPositionRepository positionRepository;

    @Override
    public List<PositionDTO> getAllPositions() {
        return positionRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PositionDTO getPositionById(Long id) {
        return positionRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    private PositionDTO convertToDTO(EipMPosition position) {
        PositionDTO dto = new PositionDTO();
        dto.setPositionId(position.getPositionId());
        dto.setPositionName(position.getPositionName());
        dto.setSort(position.getSort());
        return dto;
    }
} 