package com.softwood.domain.customUserTypes

/*import org.hibernate.HibernateException
import org.hibernate.engine.spi.SessionImplementor
import org.hibernate.type.BigDecimalType

import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException
*

/*  - wont compile with latest
class IpAddressUserType implements org.hibernate.usertype.UserType {
    @Override
    int[] sqlTypes() {

        return [BigDecimalType.INSTANCE.sqlType()] as int[]
    }

    @Override
    Class returnedClass() {
        return IpAddress.class
    }

    @Override
    Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner) throws HibernateException, SQLException {

        assert names.length == 1
        log.dubug (">>nullSafeget (name=${names}")
        BigDecimal ip = BigDecimalType.INSTANCE.get (rs, names[0], session)
        return ip //new BigDecimal (ip)
    }

    @Override
    void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session) throws HibernateException, SQLException {
        if (value == null) {
            BigDecimalType.INSTANCE.set (st, 0, index)
        } else {
            final IpAddress ipAddress = value as IpAddress

            BigDecimalType.INSTANCE.set(st, ipAddress.getAddress(), index, session)
        }
    }

    @Override
    boolean equals(Object x, Object y) throws HibernateException {
        return x.equals(y)
    }

    @Override
    int hashCode(Object x) throws HibernateException {
        assert (x != null)
        return x.hashCode()
    }

    @Override
    Object deepCopy(Object value) throws HibernateException {
        return value
    }

    @Override
    boolean isMutable() {
        return false
    }

    @Override
    Serializable disassemble(Object value) throws HibernateException {
        return (Serializable) value
    }

    @Override
    Object assemble(Serializable cached, Object owner) throws HibernateException {
        return cached
    }

    @Override
    Object replace(Object original, Object target, Object owner) throws HibernateException {
        return original
    }
}
*/
