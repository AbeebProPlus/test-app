package ai.openfabric.api.config;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.core.DockerClientImpl;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import com.github.dockerjava.transport.DockerHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;


@Configuration
public class DockerConfiguration {
    @Value("${email}")
    private String email;
    @Value("${username}")
    private String username;
    @Value("${url}")
    private String url;
    @Value("${password}")
    private String password;


    @Bean
    public DockerClient dockerClient(){
        DockerClientConfig  config = DefaultDockerClientConfig.createDefaultConfigBuilder()
                .withDockerHost("tcp://localhost:8080")
                .withDockerTlsVerify(true)
                .withDockerCertPath("/home/position")
                .withRegistryUsername(username)
                .withRegistryPassword(password)
                .withRegistryEmail(email)
                .withRegistryUrl(url)
                .build();
        DockerHttpClient clientConfig = new ApacheDockerHttpClient.Builder()
                .dockerHost(config.getDockerHost())
                .sslConfig(config.getSSLConfig())
                .maxConnections(100)
                .connectionTimeout(Duration.ofSeconds(30))
                .responseTimeout(Duration.ofSeconds(45))
                .build();
        return DockerClientImpl.getInstance(config, clientConfig);
    }
}
