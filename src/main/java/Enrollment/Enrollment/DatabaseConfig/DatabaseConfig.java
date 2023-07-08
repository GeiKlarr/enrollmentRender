package Enrollment.Enrollment.DatabaseConfig;

import lombok.Value;
import org.springframework.stereotype.Component;

@Component
public class DatabaseConfig {
     @Value("${DB_HOST}")
        private String dbHost;

    @Value("${DB_NAME}")
    private String dbName;

    @Value("${DB_PASSWORD}")
    private String dbPass;

    @Value("${DB_PORT}")
    private String dbPort;

    @Value("${DB_USERNAME}")
    private String dbUsername;
    // Rest of your code...
    }
}
