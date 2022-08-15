package com.victor.noloosecoins.configuration;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;

@Configuration
@Profile("prod")
public class ConfigDb {
    String url;
    String user;
    String password;

    @Bean
    public DataSource getDataSource() {

        setDbData();
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.postgresql.Driver");
        dataSourceBuilder.url(url);
        dataSourceBuilder.username(user);
        dataSourceBuilder.password(password);
        return dataSourceBuilder.build();
    }

    private void setDbData() {
        try {
            String uriString = System.getenv("DATABASE_URL");
            URI uri = new URI(uriString);
            String[] userAndPassword = uri.getUserInfo().split(":");
            user = userAndPassword[0];
            password = userAndPassword[1];
            url = String.format("jdbc:postgresql://%s:%d/%s", uri.getHost(), uri.getPort(), uri.getPath().substring(1));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
