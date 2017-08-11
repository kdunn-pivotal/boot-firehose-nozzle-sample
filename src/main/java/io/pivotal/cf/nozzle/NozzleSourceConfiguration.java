package io.pivotal.cf.nozzle;

import org.cloudfoundry.reactor.ConnectionContext;
import org.cloudfoundry.reactor.DefaultConnectionContext;
import org.cloudfoundry.reactor.TokenProvider;
import org.cloudfoundry.reactor.client.ReactorCloudFoundryClient;
import org.cloudfoundry.reactor.doppler.ReactorDopplerClient;
import org.cloudfoundry.reactor.tokenprovider.PasswordGrantTokenProvider;
import org.cloudfoundry.reactor.uaa.ReactorUaaClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;

@EnableBinding(Source.class)
@EnableConfigurationProperties({NozzleProperties.class})
public class NozzleSourceConfiguration {

    @Autowired
    private NozzleProperties nozzleProperties;

    @Bean
    public DefaultConnectionContext connectionContext() {
        return DefaultConnectionContext.builder()
                .apiHost(nozzleProperties.getHost())
                .skipSslValidation(true)
                .build();
    }

    @Bean
    public PasswordGrantTokenProvider tokenProvider() {
        return PasswordGrantTokenProvider.builder()
                .username(nozzleProperties.getUser())
                .password(nozzleProperties.getPassword())
                .build();
    }

    @Bean
    public ReactorCloudFoundryClient cloudFoundryClient(ConnectionContext connectionContext, TokenProvider tokenProvider) {
        return ReactorCloudFoundryClient.builder()
                .connectionContext(connectionContext)
                .tokenProvider(tokenProvider)
                .build();
    }

    @Bean
    public ReactorDopplerClient dopplerClient(ConnectionContext connectionContext, TokenProvider tokenProvider) {
        return ReactorDopplerClient.builder()
                .connectionContext(connectionContext)
                .tokenProvider(tokenProvider)
                .build();
    }

    @Bean
    public ReactorUaaClient uaaClient(ConnectionContext connectionContext, TokenProvider tokenProvider) {
        return ReactorUaaClient.builder()
                .connectionContext(connectionContext)
                .tokenProvider(tokenProvider)
                .build();
    }

}
