package pruebaAdeA.Services.Areas;

import pruebaAdeA.Dtos.Request.AreaRequestDto;
import pruebaAdeA.Dtos.Response.AreaResponseDto;

import java.util.List;

public interface AreaService {
    // create area
    AreaResponseDto createArea(AreaRequestDto request);

    // update area
    AreaResponseDto updateArea(Integer id, AreaRequestDto request);

    //delete area
    void deleteArea(Integer id);

    // get by Id
    AreaResponseDto getAreaById(Integer id);

    // list areas
    List<AreaResponseDto> listArea();
}
