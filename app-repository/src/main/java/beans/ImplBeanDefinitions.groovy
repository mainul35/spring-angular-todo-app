package beans

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.jdbc.datasource.DriverManagerDataSource

import javax.sql.DataSource

@Configuration
@PropertySource("classpath*:application.properties")
class ImplBeanDefinitions {

    @Value("${spring.datasource.url}")
    private String datasourceUrl

    @Value("${spring.datasource.username}")
    private String datasourceUsername

    @Value("${spring.datasource.password}")
    private String datasourcePassword

    @Value("${postgres.driverClassName}")
    private String driverClassName

    @Bean
    DataSource dataSource() {
        DataSource dataSource = new DriverManagerDataSource()
        dataSource.setDriverClassName(driverClassName)
        dataSource.setUrl(datasourceUrl)
        dataSource.setUsername(datasourceUsername)
        dataSource.setPassword(datasourcePassword)
        return dataSource;
    }
}
