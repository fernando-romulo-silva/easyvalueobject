package org.easystructure.domain.persistence;

import static org.allsetconfigtest.TestingLevels.INTEGRATION;

import javax.persistence.EntityManager;

import org.easystructure.dao.jpa.ObjectDaoJpa;
import org.easystructure.dao.jpa.util.JpaUtil;
import org.easystructure.domain.valueobject.personal.Cpf;
import org.myembedded.rdbms.MyEmbeddedRdbms;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class PersistValueObjectTest {

    @Test(priority = 1, groups = INTEGRATION)
    public void persistValueObjects() {

        // variaveis
        final int localDataBaseServerPort = DefaultData.DATA_BASE_SERVER_PORT.getAndIncrement();
        final String appNameLocal = DefaultData.APP_TEST_NAME + "_" + DefaultData.APPLICATION_COUNT.getAndIncrement();
        final String dataBaseName = appNameLocal + "DB";
        final String dataBaseUrl = DefaultData.DATA_BASE_JDBC_HOST + localDataBaseServerPort + "/" + dataBaseName;

        final String persistenceUnit = appNameLocal + "PU";

        final MyEmbeddedRdbms myEmbeddedRdbms = DefaultData.startHsqldbServer(localDataBaseServerPort, dataBaseName);

        final EntityManager entityManager = JpaUtil.createEntityManager(persistenceUnit, DefaultData.createProperties(dataBaseUrl, dataBaseName));

        final ObjectDaoJpa dao = new ObjectDaoJpa(entityManager);
        final EntityTestRepository employeeRepository = new EntityTestRepository(dao);

        // executa o metodo
        JpaUtil.beginTransaction(persistenceUnit);

        final EntityTest entity = new EntityTest();

        entity.setId(1L);
        entity.setCpf(new Cpf("80961234750"));
        // entity.setInscricaoEstadual(new InscricaoEstadual(Uf.RS , "5046721054"));

        employeeRepository.add(entity);

        JpaUtil.commitTransaction(persistenceUnit);

        final EntityTest entityTest = employeeRepository.get(1L).get();

        JpaUtil.closeEntityManager(persistenceUnit);

        myEmbeddedRdbms.stop();

        Assert.assertEquals("809.612.347-50", entityTest.getCpf().toShow());
    }

}
