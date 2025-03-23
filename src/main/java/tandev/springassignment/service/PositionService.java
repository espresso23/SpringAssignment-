package tandev.springassignment.service;

import tandev.springassignment.dto.PositionDTO;
import java.util.List;

public interface PositionService {
    List<PositionDTO> getAllPositions();
    PositionDTO getPositionById(Long id);
} 