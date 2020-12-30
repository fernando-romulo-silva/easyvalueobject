package org.easystructure.domain.valueobject.communication;

import java.text.MessageFormat;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.easystructure.domain.valueobject.AbstractValueObject;

import com.google.common.base.Preconditions;

/**
 * Classe valor de email.
 * 
 * @author Fernando Romulo da Silva
 */
public class Email extends AbstractValueObject<String> {

    /**
     * Construtor padrao.
     * 
     * @param value
     *            O valor do email
     */
    public Email(final String value) {
        super(value);
    }

    /*
     * (non-Javadoc)
     * @see org.easystructure.domain.valueobject.AbstractValueObject#valid(java.lang.Object)
     */
    @Override
    protected void validate(final String value) {

        final EmailValidator emailValidator = EmailValidator.getInstance();
        Preconditions.checkArgument(emailValidator.isValid(value), MessageFormat.format("Email {0} invalido.", value));
    }

    /*
     * (non-Javadoc)
     * @see org.easystructure.domain.valueobject.AbstractValueObject#toShow()
     */
    @Override
    public String toShow() {
        return value;
    }

    /*
     * (non-Javadoc)
     * @see org.easystructure.domain.valueobject.AbstractValueObject#handle(java.lang.Object)
     */
    @Override
    protected String handle(final String value) {
        return StringUtils.lowerCase(StringUtils.deleteWhitespace(value));
    }
}
