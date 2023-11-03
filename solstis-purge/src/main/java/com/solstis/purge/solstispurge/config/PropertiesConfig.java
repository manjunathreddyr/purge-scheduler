package com.solstis.purge.solstispurge.config;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "solstis.purge.folder")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Configuration
public class PropertiesConfig {

    private String path;

    private String cron;

    private Integer deleteCount;

    private Integer hoursToKeep;

    private Integer partitionCount;
}
