package ai.openfabric.api.controller;

import ai.openfabric.api.dto.request.WorkerStatisticsRequest;
import ai.openfabric.api.service.WorkerStatisticsService;
import ai.openfabric.api.utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.ZonedDateTime;

@RestController
@RequiredArgsConstructor
public class WorkerStatisticsController {
    private final WorkerStatisticsService workerStatisticsService;

    @GetMapping("/{workerName}")
    public ResponseEntity<ApiResponse> findWorkerStatistics(@PathVariable String workerName,
                                                                       HttpServletRequest httpServletRequest){
        ApiResponse response = ApiResponse.builder()
                .data(workerStatisticsService.findWorkerStatistics(workerName))
                .timeStamp(ZonedDateTime.now())
                .path(httpServletRequest.getRequestURI())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/worker_statistics_update")
    public  ResponseEntity<ApiResponse> updateWorkerStatistics(@RequestBody WorkerStatisticsRequest workerStatisticsRequest,
                                                               HttpServletRequest httpServletRequest){
        ApiResponse response = ApiResponse.builder()
                .data(workerStatisticsService.updateWorkerStatistics(workerStatisticsRequest))
                .timeStamp(ZonedDateTime.now())
                .path(httpServletRequest.getRequestURI())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
