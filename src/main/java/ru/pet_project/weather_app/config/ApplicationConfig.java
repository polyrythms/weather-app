package ru.pet_project.weather_app.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;
import ru.pet_project.weather_app.service.WeathermapClient;

@Configuration
public class ApplicationConfig {

    private static final int WEB_CLIENT_TIMEOUT = 15 * 60 * 1000;

    @Value("${openweathermap.base-url}")
    private String weathermapBaseUrl;

    @Bean
    public WeathermapClient weathermapClient() {
        return new WeathermapClient(webClient(weathermapBaseUrl));
    }


    private WebClient webClient(String host) {
        TcpClient tcpClient = TcpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, WEB_CLIENT_TIMEOUT)
                .doOnConnected(connection ->
                        connection.addHandlerLast(new ReadTimeoutHandler(WEB_CLIENT_TIMEOUT))
                                .addHandlerLast(new WriteTimeoutHandler(WEB_CLIENT_TIMEOUT)));

        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)))
                .baseUrl(host)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
