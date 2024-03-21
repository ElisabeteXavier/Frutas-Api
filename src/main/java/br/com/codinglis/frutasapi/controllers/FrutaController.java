package br.com.codinglis.frutasapi.controllers;

import br.com.codinglis.frutasapi.dtos.FrutaRequest;
import br.com.codinglis.frutasapi.dtos.FrutaResponse;
import br.com.codinglis.frutasapi.models.FrutaModel;
import br.com.codinglis.frutasapi.services.FrutaService;
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

    @PostMapping
    public ResponseEntity<FrutaResponse> criarFrutas(@RequestBody FrutaRequest request) {
        var frutaCriada = frutaService.criarFruta(convertToModel(request));
        FrutaResponse response = convertToResponse(frutaCriada);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @GetMapping("/{frutaId}")
    public ResponseEntity<FrutaResponse> buscarFrutaPeloId(@PathVariable Long frutaId) {
        var fruta = frutaService.buscarFrutaPeloId(frutaId);
        FrutaResponse response = convertToResponse(fruta);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<List<FrutaResponse>> buscarFrutas() {
        List<FrutaResponse> frutas = frutaService.buscarFrutas()
                .stream()
                .map((FrutaModel frutaModel) -> convertToResponse(frutaModel))
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
        FrutaModel frutaCriada = frutaService.editarFruta(frutaId, convertToModel(request));
        FrutaResponse fruta = convertToResponse(frutaCriada);
        return ResponseEntity.status(HttpStatus.OK).body(fruta);
    }


    // SEPARAR EM UMA CLASSE CHAMADA: FrutaConverter e anotar ela com @Component
    private FrutaResponse convertToResponse(FrutaModel model) {
        return new FrutaResponse(model.getId(), model.getNome(), model.getPreco());
    }

    private FrutaModel convertToModel(FrutaRequest req) {
        return new FrutaModel(req.nome(), req.preco());
    }
}
