package ai.openfabric.api.dto.request;

import lombok.Data;

@Data
public class WorkerRequest {
    private int port;
    private String name;
}
