package ai.openfabric.api.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity()
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkerStatistics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "of-uuid")
    @GenericGenerator(name = "of-uuid", strategy = "ai.openfabric.api.model.IDGenerator")
    public String id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "worker_id")
    private Worker worker;
    @Column(nullable = false)
    private Double memoryUsage;
    @Column(nullable = false)
    private Double cpuUsage;
    @Column(nullable = false)
    private LocalDateTime timestamp;
}