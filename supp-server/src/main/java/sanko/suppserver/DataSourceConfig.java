package sanko.suppserver;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.session.jdbc.config.annotation.SpringSessionDataSource;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
    @Bean @Primary @ConfigurationProperties("spring.datasource") @Qualifier("data")
    public DataSourceProperties dataProperties() {
        return new DataSourceProperties();
    }

    @Bean @Primary
    public DataSource data(@Qualifier("data") DataSourceProperties data) {
        return data.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean @ConfigurationProperties("session.datasource") @Qualifier("session")
    public DataSourceProperties sessionProperties() {
        return new DataSourceProperties();
    }

    @Bean @SpringSessionDataSource
    public DataSource session(@Qualifier("session") DataSourceProperties session) {
        return session.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }
}
