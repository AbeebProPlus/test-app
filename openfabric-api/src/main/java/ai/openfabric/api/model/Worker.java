package ai.openfabric.api.model;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity()
@Data
@NoArgsConstructor
public class Worker extends Datable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "of-uuid")
    @GenericGenerator(name = "of-uuid", strategy = "ai.openfabric.api.model.IDGenerator")
    @Getter
    @Setter
    public String id;
    @Column(nullable = false)
    public String name;
    @Column(nullable = false)
    private int port;
    @Column(nullable = false)
    private int status;
    private int ipAddress;
    @Enumerated(value = EnumType.STRING)
    private Status workerStatus;

    public Worker(String name, int port, Status status) {
        this.name = name;
        this.port = port;
        this.workerStatus = status;
    }
}
