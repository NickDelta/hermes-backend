package org.hua.hermes.data;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.UUID;

/**
 * Custom generator implementation that generates a UUID string.
 * @author <a href="mailto:nikosdelta@protonmail.com">Nick Dimitrakopoulos</a>
 */
public class UUIDGenerator implements IdentifierGenerator
{
    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o)
            throws HibernateException
    {
        return UUID.randomUUID().toString();
    }
}
