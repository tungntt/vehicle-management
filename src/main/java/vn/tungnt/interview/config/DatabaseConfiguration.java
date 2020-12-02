package vn.tungnt.interview.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(value = "vn.tungnt.interview.repository")
@EnableTransactionManagement
public class DatabaseConfiguration {
}
