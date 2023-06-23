package ai.openfabric.api.service;

import ai.openfabric.api.dto.request.WorkerRequest;
import ai.openfabric.api.exceptions.WorkerNotFoundException;
import ai.openfabric.api.model.Worker;
import ai.openfabric.api.repository.WorkerRepository;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.ExposedPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static ai.openfabric.api.model.Status.RUNNING;
import static ai.openfabric.api.model.Status.STOPPED;

@Service
@RequiredArgsConstructor
public class WorkerServiceImpl implements WorkerService{
    private final WorkerRepository workerRepository;
    private final DockerClient dockerClient;
    @Override
    public Worker startWorker(WorkerRequest workerRequest) {
        String containerID = dockerClient.createContainerCmd("my_image")
                .withName(workerRequest.getName())
                .withExposedPorts(ExposedPort.tcp(workerRequest.getPort()))
                .exec().getId();
        dockerClient.startContainerCmd(containerID).exec();
        Worker worker = new Worker(workerRequest.getName(), workerRequest.getPort(), RUNNING);
        workerRepository.save(worker);
        return worker;
    }
    @Override
    public void stopWorker(String workerName) {
        dockerClient.stopContainerCmd(workerName).exec();
        Optional<Worker> savedWorker = workerRepository.findByName(workerName);
        if(savedWorker.isPresent()){
            Worker worker = savedWorker.get();
            worker.setWorkerStatus(STOPPED);
            workerRepository.save(worker);
        } else{
            throw new WorkerNotFoundException("Worker does not exist");
        }
    }

    @Override
    public List<Worker> findAllWorkers(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Worker> workersPage = workerRepository.findAll(pageable);
        return workersPage.getContent();
    }

    @Override
    public Worker obtainWorkerDetails(String workerName) {
        Optional<Worker> foundWorker = workerRepository.findByName(workerName);
        if(!foundWorker.isPresent()){
            throw new WorkerNotFoundException("Worker does not exist");
        }
        return foundWorker.get();
    }
}
