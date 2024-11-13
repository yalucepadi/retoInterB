package com.reto.product.config.dataBase;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
@Data
@ConfigurationProperties(prefix = "database")
public class DatabaseProperties {
    private String url;
    private String username;
    private String password;
    private String driverClassName;

    // Propiedades adicionales de conexión
    private int maxPoolSize;
    private int minIdle;
    private long idleTimeout;

}
