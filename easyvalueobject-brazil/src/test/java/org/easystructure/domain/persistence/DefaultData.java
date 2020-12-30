package org.easystructure.domain.persistence;

import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

import org.myembedded.rdbms.MyEmbeddedRdbms;

public class DefaultData {

    public static final String DATA_BASE_JDBC_HOST = "jdbc:hsqldb:hsql://localhost:";

    public static final String APP_TEST_NAME = "ObjectRepository";

    public static final AtomicInteger APPLICATION_COUNT = new AtomicInteger(1);

    public static final AtomicInteger DATA_BASE_SERVER_PORT = new AtomicInteger(2537);

    public static final String DATA_BASE_SERVER_DRIVER = "org.hsqldb.jdbcDriver";

    public static final String DATA_BASE_SERVER_LOGIN = "root";

    public static final String DATA_BASE_SERVER_PASSWORD = "root";

    public static synchronized MyEmbeddedRdbms startHsqldbServer(final int localDataBaseServerPort, final String dataBaseName) {
        final MyEmbeddedRdbms embeddedHsqldbServer = new MyEmbeddedRdbms();
        embeddedHsqldbServer.addDataBase(dataBaseName, DATA_BASE_SERVER_LOGIN, DATA_BASE_SERVER_PASSWORD);
        embeddedHsqldbServer.start(localDataBaseServerPort);
        return embeddedHsqldbServer;
    }

    public static synchronized Properties createProperties(final String dataBaseUrl, final String dataBaseName) {

        final Properties properties = new Properties();

        // JPA
        properties.put("javax.persistence.transactionType", "RESOURCE_LOCAL");
        properties.put("javax.persistence.mappingResources", "META-INF/orm.xml");
        properties.put("javax.persistence.jdbc.url", dataBaseUrl);
        properties.put("javax.persistence.jdbc.user", DATA_BASE_SERVER_LOGIN);
        properties.put("javax.persistence.jdbc.password", DATA_BASE_SERVER_PASSWORD);
        properties.put("javax.persistence.jdbc.driver", DATA_BASE_SERVER_DRIVER);

        // Hibernate
        properties.put("javax.persistence.provider", "org.hibernate.jpa.HibernatePersistenceProvider");
        properties.put("transaction.factory_class", "org.hibernate.transaction.JBossTSStandaloneTransactionManagerLookup");
        properties.put("hibernate.connection.release_mod", "after_transaction");
        properties.put("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
        properties.put("hibernate.current_session_context_class", "org.hibernate.context.internal.ThreadLocalSessionContext");
        properties.put("hibernate.hbm2ddl.auto", "create-drop");
        properties.put("hibernate.connection.autocommit", "false");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");

        return properties;
    }
}
