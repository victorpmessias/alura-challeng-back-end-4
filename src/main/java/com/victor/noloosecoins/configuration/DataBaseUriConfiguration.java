package com.victor.noloosecoins.configuration;

import com.victor.noloosecoins.exceptions.EnvironmentUriDataBaseException;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;

@Configuration
@Profile("prod")
public class DataBaseUriConfiguration {


    @Bean
    public DataSource getDataSource() {
        URI uri = getDbUri();

        String[] userAndPassword = uri.getUserInfo().split(":");
        String user = userAndPassword[0];
        String password = userAndPassword[1];
        String url = String.format("jdbc:postgresql://%s:%d/%s", uri.getHost(), uri.getPort(), uri.getPath().substring(1));

        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.postgresql.Driver");
        dataSourceBuilder.url(url);
        dataSourceBuilder.username(user);
        dataSourceBuilder.password(password);
        return dataSourceBuilder.build();
    }

    private URI getDbUri() {
        try {
            String uriString = System.getenv("DATABASE_URL");
            return new URI(uriString);
        } catch (URISyntaxException e) {
            throw new EnvironmentUriDataBaseException("Error trying to get data base uri from environment");
        }
    }
}
