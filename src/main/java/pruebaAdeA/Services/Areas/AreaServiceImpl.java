package pruebaAdeA.Services.Areas;

import org.springframework.stereotype.Service;
import pruebaAdeA.Dtos.Request.AreaRequestDto;
import pruebaAdeA.Dtos.Response.AreaResponseDto;
import pruebaAdeA.Models.AreaModel;
import pruebaAdeA.Repositories.AreaRepository;

import java.time.LocalDate;
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
        area.setNombreArea(request.getNombre());
        area.setCodigoInterno(request.getDescripcion());
        area.setFechaAlta(LocalDate.now());

        // Guardar en BD
        AreaModel savedArea = areaRepository.save(area);

        System.out.println("savedArea = " + savedArea);
        return mapToDTO(savedArea);
    }

    @Override
    public AreaResponseDto updateArea(Integer id, AreaRequestDto request) {
        AreaModel area = areaRepository.findById(id)
                .orElseThrow(()->new RuntimeException("El area no existe"));

        area.setNombreArea(request.getNombre());
        area.setCodigoInterno(request.getDescripcion());

        AreaModel upload = areaRepository.save(area);

        return mapToDTO(upload);
    }

    @Override
    public void deleteArea(Integer id) {
        if (areaRepository.existsById(id)){
            areaRepository.deleteById(id);
        }else{
            throw new RuntimeException("Area: " + id + "no encontrada");
        }
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

    // Match data whit request and response dto
    private AreaResponseDto mapToDTO(AreaModel area){
        AreaResponseDto dto = new AreaResponseDto();
        dto.setId(area.getArea_id());
        dto.setNombreArea(area.getNombreArea());
        dto.setCodigoInterno(area.getCodigoInterno());
        dto.setFechaAlta(area.getFechaAlta());
        return dto;
    }
}
