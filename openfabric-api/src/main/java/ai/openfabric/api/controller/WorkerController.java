package ai.openfabric.api.controller;

import ai.openfabric.api.dto.request.WorkerRequest;
import ai.openfabric.api.service.WorkerService;
import ai.openfabric.api.utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.ZonedDateTime;

@RestController
@RequestMapping("api/v1/workers")
@RequiredArgsConstructor
public class WorkerController {

    private final WorkerService workerService;
    @PostMapping(path = "/hello")
    public @ResponseBody String hello(@RequestBody String name) {
        return "Hello!" + name;
    }

    @PostMapping("/activation")
    public ResponseEntity<ApiResponse> startWorker(@RequestBody WorkerRequest workerRequest, HttpServletRequest httpServletRequest){
        ApiResponse response = ApiResponse.builder()
                .data(workerService.startWorker(workerRequest))
                .timeStamp(ZonedDateTime.now())
                .path(httpServletRequest.getRequestURI())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/all_workers")
    public ResponseEntity<ApiResponse> findAllWorkers(@RequestParam(defaultValue = "0") int pageNumber,
                                         @RequestParam(defaultValue = "10") int pageSize, HttpServletRequest httpServletRequest){

        ApiResponse response = ApiResponse.builder()
                .data(workerService.findAllWorkers(pageNumber, pageSize))
                .timeStamp(ZonedDateTime.now())
                .path(httpServletRequest.getRequestURI())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/termination/{workerName}")
    public ResponseEntity<Void> stopWorker(@PathVariable String workerName){
        workerService.stopWorker(workerName);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{workerName}")
    public ResponseEntity<ApiResponse> getWorkerInfo(@PathVariable String workerName, HttpServletRequest httpServletRequest){
        ApiResponse response = ApiResponse.builder()
                .data(workerService.obtainWorkerDetails(workerName))
                .timeStamp(ZonedDateTime.now())
                .path(httpServletRequest.getRequestURI())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}