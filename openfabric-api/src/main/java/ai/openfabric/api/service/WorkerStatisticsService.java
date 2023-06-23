package ai.openfabric.api.service;

import ai.openfabric.api.dto.request.WorkerStatisticsRequest;
import ai.openfabric.api.model.WorkerStatistics;

import java.util.List;


public interface WorkerStatisticsService {
    String updateWorkerStatistics(WorkerStatisticsRequest workerStatisticsRequest);
    List<WorkerStatistics> findWorkerStatistics(String workerName);
}