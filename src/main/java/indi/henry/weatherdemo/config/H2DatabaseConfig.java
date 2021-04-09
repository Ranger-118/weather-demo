package indi.henry.weatherdemo.config;

import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Database config for H2
 * 
 * @author Henry Hu
 */
@Configuration
@ConfigurationProperties(prefix = "app")
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = "indi.henry.weatherdemo",
    entityManagerFactoryRef = "entityManagerFactory",
    transactionManagerRef = "h2TransactionManager"
)
public class H2DatabaseConfig {

    private List<String> scriptsToExecute;

    private boolean showSql;

    @Bean
    public DataSource dataSource() {
        if (scriptsToExecute != null) {
            return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
                        .setName("APP_DB_EM")
                        .addScripts(scriptsToExecute.toArray(new String[scriptsToExecute.size()]))
                        .build();
        }
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
        .setName("APP_DB_EM")
        .build();
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();

        adapter.setDatabase(Database.H2);
        adapter.setShowSql(showSql);
        adapter.setGenerateDdl(true);

        return adapter;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource());
        emf.setJpaVendorAdapter(jpaVendorAdapter());
        emf.setPersistenceUnitName("APP_DB_EM");
        Properties properties = new Properties();
        properties.put("hibernate.physical_naming_strategy", "indi.henry.weatherdemo.config.CustomPhysicalNamingStrategy");
        emf.setJpaProperties(properties);
        emf.setPackagesToScan(
            new String[] {
            "indi.henry.weatherdemo"
            });
        return emf;
    }

    @Bean
    public PlatformTransactionManager h2TransactionManager() {
        return new JpaTransactionManager(entityManagerFactory().getObject());
    }


    
    public List<String> getScriptsToExecute() {
        return scriptsToExecute;
    }

    public void setScriptsToExecute(List<String> scriptsToExecute) {
        this.scriptsToExecute = scriptsToExecute;
    }

    public boolean isShowSql() {
        return showSql;
    }

    public void setShowSql(boolean showSql) {
        this.showSql = showSql;
    }
}