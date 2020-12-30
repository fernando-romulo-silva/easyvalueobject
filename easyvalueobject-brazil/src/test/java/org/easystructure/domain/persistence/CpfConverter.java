package org.easystructure.domain.persistence;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.easystructure.domain.valueobject.ValueObjectUtil;
import org.easystructure.domain.valueobject.personal.Cpf;

@Converter
public class CpfConverter implements AttributeConverter<Cpf, String> {

    /*
     * (non-Javadoc)
     * @see javax.persistence.AttributeConverter#convertToDatabaseColumn(java.lang.Object)
     */
    @Override
    public String convertToDatabaseColumn(final Cpf attribute) {
        return attribute.getValue();
    }

    /*
     * (non-Javadoc)
     * @see javax.persistence.AttributeConverter#convertToEntityAttribute(java.lang.Object)
     */
    @Override
    public Cpf convertToEntityAttribute(final String dbData) {
        return ValueObjectUtil.createValueObject(Cpf.class, dbData);
    }
}
