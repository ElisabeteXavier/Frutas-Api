package br.com.codinglis.frutasapi.controllers;

import br.com.codinglis.frutasapi.dtos.FrutaRequest;
import br.com.codinglis.frutasapi.dtos.FrutaResponse;
import br.com.codinglis.frutasapi.models.FrutaModel;
import br.com.codinglis.frutasapi.services.FrutaService;
import br.com.codinglis.frutasapi.utils.ConvertToModel;
import br.com.codinglis.frutasapi.utils.ConvertToResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/frutas")
public class FrutaController {
    @Autowired
    private FrutaService frutaService;
    private ConvertToModel convertToModel;
    private ConvertToResponse convertToResponse;

    @PostMapping
    public ResponseEntity<FrutaResponse> criarFrutas(@RequestBody FrutaRequest request) {
        var frutaCriada = frutaService.criarFruta(convertToModel.convert(request));
        FrutaResponse response = convertToResponse.convert(frutaCriada);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @GetMapping("/{frutaId}")
    public ResponseEntity<FrutaResponse> buscarFrutaPeloId(@PathVariable Long frutaId) {
        var fruta = frutaService.buscarFrutaPeloId(frutaId);
        FrutaResponse response = convertToResponse.convert(fruta);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<List<FrutaResponse>> buscarFrutas() {
        List<FrutaResponse> frutas = frutaService.buscarFrutas()
                .stream()
                .map((FrutaModel frutaModel) -> convertToResponse.convert(frutaModel))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(frutas);
    }

    @DeleteMapping("/{frutaId}")
    public ResponseEntity<?> deletarFrutaPeloId(@PathVariable Long frutaId) {
        frutaService.deletarFruta(frutaId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{frutaId}")
    public ResponseEntity<FrutaResponse> editarFruta(@PathVariable Long frutaId, @RequestBody FrutaRequest request) {
        FrutaModel frutaCriada = frutaService.editarFruta(frutaId, convertToModel.convert(request));
        FrutaResponse fruta = convertToResponse.convert(frutaCriada);
        return ResponseEntity.status(HttpStatus.OK).body(fruta);
    }
}
