package org.easystructure.domain.persistence;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.UserType;

public class EstadoConverter implements UserType {

    public EstadoConverter() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public int[] sqlTypes() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Class returnedClass() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean equals(final Object x, final Object y) throws HibernateException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int hashCode(final Object x) throws HibernateException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Object nullSafeGet(final ResultSet rs, final String[] names, final SessionImplementor session, final Object owner) throws HibernateException, SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void nullSafeSet(final PreparedStatement st, final Object value, final int index, final SessionImplementor session) throws HibernateException, SQLException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Object deepCopy(final Object value) throws HibernateException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isMutable() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Serializable disassemble(final Object value) throws HibernateException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object assemble(final Serializable cached, final Object owner) throws HibernateException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object replace(final Object original, final Object target, final Object owner) throws HibernateException {
        // TODO Auto-generated method stub
        return null;
    }

}
