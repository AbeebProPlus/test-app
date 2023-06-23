package ai.openfabric.api.service;

import ai.openfabric.api.dto.request.WorkerRequest;
import ai.openfabric.api.model.Worker;

import java.util.List;

public interface WorkerService {
    Worker startWorker(WorkerRequest workerRequest);
    void stopWorker(String workerName);
    List<Worker> findAllWorkers(int pageNumber, int size);
    Worker obtainWorkerDetails(String workerName);
}