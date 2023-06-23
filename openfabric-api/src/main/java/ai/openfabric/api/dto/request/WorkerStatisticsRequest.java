package ai.openfabric.api.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class WorkerStatisticsRequest {
    @NotNull(message = "This field should not be null")
    private String workerName;
    @NotNull(message = "This field should not  be null")
    private Double cpuUsage;
    @NotNull(message = "This field should not be null")
    private Double memoryUsage;
}
