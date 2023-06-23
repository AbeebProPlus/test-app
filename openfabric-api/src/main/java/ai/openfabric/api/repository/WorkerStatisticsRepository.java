package ai.openfabric.api.repository;

import ai.openfabric.api.model.WorkerStatistics;
import org.springframework.data.repository.CrudRepository;

public interface WorkerStatisticsRepository extends CrudRepository<WorkerStatistics, Long> {
}
