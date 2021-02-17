package br.com.viasoft.statusReceita.controller;

import br.com.viasoft.statusReceita.dto.EstadoConsultaDto;
import br.com.viasoft.statusReceita.dto.EstadoStatusDto;
import br.com.viasoft.statusReceita.entity.Estado;
import br.com.viasoft.statusReceita.repository.EstadoRepository;
import br.com.viasoft.statusReceita.repository.StatusRepository;
import br.com.viasoft.statusReceita.repository.StatusRepositoryImpl;
import com.google.gson.Gson;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private StatusRepositoryImpl statusRepositoryImpl;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private StatusRepository statusRepository;

    @GetMapping(path = "/data")
    public ResponseEntity<List<EstadoStatusDto>> statusEstadosData(@RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date dataConsulta) {
        return ResponseEntity.ok(statusRepositoryImpl.consultaStatusData(dataConsulta));
    }

    @GetMapping(path = "/status")
    public ResponseEntity<?> statusEstados(@RequestParam(required = false) String estado) {
        List<Object[]> lista = statusRepository.consultaSituacaoEstados(estado);
        if (lista.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Gson gson = new Gson();
        List<EstadoConsultaDto> resultados = new ArrayList<>();
        for (int x = 0; x < lista.size(); x++) {
            EstadoConsultaDto estadoConsulta = EstadoConsultaDto.converter(lista.get(x));
            resultados.add(estadoConsulta);
        }

        return ResponseEntity.ok(gson.toJson(resultados));
    }

    @GetMapping
    public ResponseEntity<List<Map<String, String>>> lista() {
        List<Estado> estadoList = estadoRepository.findAll();
        List<Map<String, String>> resultado = new ArrayList<>();
        estadoList.stream().map((n) -> {
            Map<String, String> entity = new HashMap<>();
            entity.put("code", n.getDescricao());
            entity.put("name", n.getDescricao());
            return entity;
        }).forEachOrdered((entity) -> {
            resultado.add(entity);
        });
        return ResponseEntity.ok(resultado);
    }

    @GetMapping(path = "/indisponibilidade")
    public ResponseEntity<List<Map<String, String>>> indisponibilidade() {
        List<Object[]> results = statusRepository.findEstadoMaiorIndisponibilidade();
        List<Map<String, String>> resultado = new ArrayList<>();
        results.stream().map((n) -> {
            Map<String, String> entity = new HashMap<>();
            entity.put("descricao", (String) n[0]);
            entity.put("vezes", n[1].toString());
            return entity;
        }).forEachOrdered((entity) -> {
            resultado.add(entity);
        });
        return ResponseEntity.ok(resultado);
    }

}
