package ai.openfabric.api.controller;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/container/")
public class DockerController {
    private final DockerClient dockerClient;

    @PostMapping("/create")
    public void createContainer(){
        CreateContainerResponse containerResponse = dockerClient.createContainerCmd("/home/image").exec();
        dockerClient.startContainerCmd(containerResponse.getId()).exec();
    }
}
