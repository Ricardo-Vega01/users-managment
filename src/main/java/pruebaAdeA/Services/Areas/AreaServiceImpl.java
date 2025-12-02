package pruebaAdeA.Services.Areas;

import org.springframework.stereotype.Service;
import pruebaAdeA.Dtos.Request.AreaRequestDto;
import pruebaAdeA.Dtos.Response.AreaResponseDto;
import pruebaAdeA.Models.AreaModel;
import pruebaAdeA.Repositories.AreaRepository;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class AreaServiceImpl implements AreaService{

    private final AreaRepository areaRepository;

    public AreaServiceImpl(AreaRepository areaRepository){
        this.areaRepository = areaRepository;
    }


    @Override
    public AreaResponseDto createArea(AreaRequestDto request) {
        AreaModel area = new AreaModel();
        area.setNombreArea(request.getNombreArea());

        return mapToDTO(area);
    }

    @Override
    public AreaResponseDto updateArea(Integer id, AreaRequestDto request) {
        AreaModel area = areaRepository.findById(id)
                .orElseThrow(()->new RuntimeException("El area no existe"));

        area.setNombreArea(request.getNombreArea());

        return mapToDTO(area);
    }

    @Override
    public void deleteArea(Integer id) {
        areaRepository.findById(id);
    }

    @Override
    public AreaResponseDto getAreaById(Integer id) {
        AreaModel area = areaRepository.findById(id)
                .orElseThrow(()->new RuntimeException("No se encontraron coincidencias"));

        return mapToDTO(area);
    }

    @Override
    public List<AreaResponseDto> listArea() {
        return areaRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    private AreaResponseDto mapToDTO(AreaModel area){
        AreaResponseDto dto = new AreaResponseDto();
        dto.setId(area.getId());
        dto.setNombreArea(area.getNombreArea());

        return dto;
    }
}
