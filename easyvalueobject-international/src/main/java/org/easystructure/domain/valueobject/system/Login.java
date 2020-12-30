package org.easystructure.domain.valueobject.system;

import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.easystructure.domain.valueobject.AbstractValueObject;

import com.google.common.base.Preconditions;

// https://stackoverflow.com/questions/12018245/regular-expression-to-validate-username
/**
 * Classe que representa um value object de Login.
 * 
 * @author Fernando Romulo da Silva
 */
public class Login extends AbstractValueObject<String> {

    private static final String USERNAME_PATTERN = "^(?=.{8,30}$)(?![_.])(?![0-9])(?!.*[_.]{2})[a-z0-9._-]+(?<![_.])$";
    // "^[a-z0-9_-.]{3,15}$";
    // ^ # Start of the line
    // [a-z0-9_-] # Match characters and symbols in the list, a-z, 0-9, underscore, hyphen
    // {3,15} # Length at least 3 characters and maximum length of 15
    // $ # End of the line

    public Login(final String value) {
        super(value);
    }

    @Override
    protected void validate(final String value) {
        Preconditions.checkArgument(!StringUtils.isBlank(value), "Empty login.");

        final Pattern pattern = Pattern.compile(USERNAME_PATTERN);

        final Matcher matcher = pattern.matcher(value);

        if (!matcher.matches()) {
            throw new IllegalArgumentException(MessageFormat.format("Login {0} invalid.", value));
        }
    }

    @Override
    public String toShow() {
        return value;
    }
}
