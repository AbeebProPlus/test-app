package ai.openfabric.api.service;

import ai.openfabric.api.dto.request.WorkerStatisticsRequest;
import ai.openfabric.api.exceptions.WorkerNotFoundException;
import ai.openfabric.api.model.Worker;
import ai.openfabric.api.model.WorkerStatistics;
import ai.openfabric.api.repository.WorkerRepository;
import ai.openfabric.api.repository.WorkerStatisticsRepository;
import com.github.dockerjava.api.DockerClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WorkerStatisticsServiceImpl implements WorkerStatisticsService{

    private final WorkerRepository workerRepository;
    private final DockerClient dockerClient;
    private final WorkerStatisticsRepository workerStatisticsRepository;
    @Override
    public String updateWorkerStatistics(WorkerStatisticsRequest workerStatisticsRequest) {
        Optional<Worker> foundWorker = workerRepository.findByName(workerStatisticsRequest.getWorkerName());
        if(!foundWorker.isPresent())
            throw new WorkerNotFoundException(String.format("Worker with name: %s not found", workerStatisticsRequest.getWorkerName()));
        WorkerStatistics workerStatistics = WorkerStatistics.builder()
                    .worker(foundWorker.get())
                    .memoryUsage(workerStatisticsRequest.getMemoryUsage())
                    .cpuUsage(workerStatisticsRequest.getCpuUsage())
                    .timestamp(LocalDateTime.now())
                    .build();
            workerStatisticsRepository.save(workerStatistics);
        return "Worker statistics updated successfully";
    }

    @Override
    public List<WorkerStatistics> findWorkerStatistics(String workerName) {
        Optional<Worker> foundWorker = workerRepository.findByName(workerName);
        if(!foundWorker.isPresent()) throw new WorkerNotFoundException("Worker not found");
        else return workerStatisticsRepository.findWorkerOrderByTimestampDesc(foundWorker.get());
    }
}
