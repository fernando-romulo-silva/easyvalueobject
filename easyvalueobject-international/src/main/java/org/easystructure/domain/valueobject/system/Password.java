package org.easystructure.domain.valueobject.system;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.easystructure.domain.valueobject.AbstractValueObject;

import com.google.common.base.Preconditions;

/**
 * Classe que representa uma senha.
 * 
 * @author Fernando Romulo Da Silva
 */
// https://stackoverflow.com/questions/36692333/regex-validate-password-with-conditional-statement-java
public final class Password extends AbstractValueObject<String> {

    private static final String REGEX_PASSWORD = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // ^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\S+$).{8,}$
    // ^                 # start-of-string
    // (?=.*[0-9])       # a digit must occur at least once
    // (?=.*[a-z])       # a lower case letter must occur at least once
    // (?=.*[A-Z])       # an upper case letter must occur at least once
    // (?=.*[@#$%^&+=])  # a special character must occur at least once
    // (?=\S+$)          # no whitespace allowed in the entire string
    // .{8,}             # anything, at least eight places though
    // $                 # end-of-string
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    /**
     * Construtor padrao.
     * 
     * @param value
     *            o valor da senha
     */
    public Password(final String value) {
        super(new PasswordHandler(), value);
    }

    /*
     * (non-Javadoc)
     * @see org.easystructure.domain.valueobject.AbstractValueObject#validate(java.lang.Object)
     */
    @Override
    protected void validate(final String value) {

        Preconditions.checkArgument(!StringUtils.isBlank(value), "Empty password.");

        final Pattern pattern = Pattern.compile(REGEX_PASSWORD);

        final Matcher matcher = pattern.matcher(value);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("Illegal password.");
        }
    }

    /*
     * (non-Javadoc)
     * @see org.easystructure.domain.valueobject.AbstractValueObject#toShow()
     */
    @Override
    public String toShow() {
        final int valueSize = value.length();
        return StringUtils.repeat('*', valueSize);
    }
    
    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!this.getClass().equals(obj.getClass())) {
            return false;
        }

        final Password other = (Password) obj;
        
        return new EqualsBuilder() //
            .append(descrypt(), other.descrypt()) //
            .isEquals();
    }

    private String descrypt() {
        return new PasswordHandler().unHandle(value);
    }    
}
