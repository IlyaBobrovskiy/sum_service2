package com.example.sumservice.conroller;

import com.example.sumservice.RequestModel;
import com.example.sumservice.ResponseModel;
import com.example.sumservice.exception.SumNotFoundException;
import com.example.sumservice.model.SumModel;
import com.example.sumservice.service.SumService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class SumController {

    private final SumService sumService;
    private RequestModel sumModel;

    public SumController(final SumService sumService) {
        this.sumService = sumService;
    }

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody RequestModel requestModel) {
        try {
            sumService.add(sumModel.getName(), sumModel.getValue());
            return ResponseEntity.ok(new ResponseModel());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseModel(ResponseModel.Response.ERROR));
        }
    }

    @PostMapping("/remove")
    public ResponseEntity delete(@RequestBody RequestModel requestModel) {
        try {
            sumService.delete(requestModel.name);
            return ResponseEntity.ok(new ResponseModel());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseModel(ResponseModel.Response.ERROR));
        }
    }

    @PostMapping("/sum")
    public ResponseEntity calcSum(@RequestBody RequestModel requestModel) throws SumNotFoundException {
        SumModel firstModel = sumService.getByName(requestModel.getFirst());
        SumModel secondModel = sumService.getByName(requestModel.getSecond());

        if (firstModel == null || secondModel == null) {
            throw new SumNotFoundException("Сущности не найдены");
        }

        Integer firstValue = firstModel.getValue();
        Integer secondValue = secondModel.getValue();

        if (firstValue == null || secondValue == null) {
            throw new SumNotFoundException("Значения не найдены");
        }

        try {
            return ResponseEntity.ok(new ResponseModel(firstValue + secondValue));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseModel(ResponseModel.Response.ERROR));
        }
    }
}

