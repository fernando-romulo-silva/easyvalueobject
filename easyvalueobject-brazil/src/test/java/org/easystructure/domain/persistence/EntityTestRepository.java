package org.easystructure.domain.persistence;

import org.easystructure.dao.ObjectDao;
import org.easystructure.domain.repository.ObjectRepository;

public class EntityTestRepository extends ObjectRepository<EntityTest> {

    public EntityTestRepository(final ObjectDao dao) {
        super(dao, EntityTest.class);
    }
}
