package pruebaAdeA.Controllers;

import org.springframework.web.bind.annotation.*;
import pruebaAdeA.Dtos.Request.AreaRequestDto;
import pruebaAdeA.Dtos.Response.AreaResponseDto;
import pruebaAdeA.Services.Areas.AreaService;

import java.util.List;

@RestController
@RequestMapping("/api/areas")
@CrossOrigin(origins = "*")
public class AreaController {

    private final AreaService areaService;

    public AreaController (AreaService areaService){
        this.areaService = areaService;
    }

    @PostMapping
    public AreaResponseDto createArea(@RequestBody AreaRequestDto request) {
        return areaService.createArea(request);
    }

    @PutMapping("/{id}")
    public AreaResponseDto upateArea(@PathVariable Integer id, @RequestBody AreaRequestDto request) {
        return areaService.updateArea(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteArea(@PathVariable Integer id) {
        areaService.deleteArea(id);
    }

    @GetMapping("/{id}")
    public AreaResponseDto getAreaById(@PathVariable Integer id) {
        return areaService.getAreaById(id);
    }

    @GetMapping
    public List<AreaResponseDto> listArea() {
        return areaService.listArea();
    }
}
