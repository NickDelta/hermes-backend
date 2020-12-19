package org.hua.hermes.util;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

/**
 * Static convenience methods that extend Spring Framework's {@link BeanUtils}.
 * Main idea taken from <a href="https://stackoverflow.com/a/19739041/10456481">Stack Overflow</a>
 * and extra functionality was added.
 * @author Nick Dimitrakopoulos
 */
public class CustomBeanUtils extends BeanUtils
{

    /**
     * Copy the property values of the given source bean into the target bean.
     *
     * Note: The source and target classes do not have to match or even be derived from each other, as long as the
     * properties match. Any bean properties that the source bean exposes but the target bean does not will silently
     * be ignored. <b>Any bean properties that the source bean exposes but are null are also ignored.</b>
     *
     * This is just a convenience method. For more complex transfer needs, consider using a full BeanWrapper.
     * @param source the source bean
     * @param target the target bean
     */
    public static void copyNonNullProperties(Object source, Object target) {
        copyProperties(source, target, getNullPropertyNames(source));
    }

    /**
     * Copy the property values of the given source bean into the target bean, ignoring the given "ignoreProperties".
     *
     * Note: The source and target classes do not have to match or even be derived from each other, as long as the
     * properties match. Any bean properties that the source bean exposes but the target bean does not will silently
     * be ignored. <b>Any bean properties that the source bean exposes but are null are also ignored.</b>
     *
     * This is just a convenience method. For more complex transfer needs, consider using a full BeanWrapper.
     * @param source the source bean
     * @param target the target bean
     */
    public static void copyNonNullProperties(Object source, Object target, String... ignoreProperties) {
        copyProperties(source, target,getNullAndIgnoredProperties(source,ignoreProperties));
    }

    private static String[] getNullAndIgnoredProperties(Object source, String... forcedIgnoreProperties)
    {
        String[] nullPropertyNames = getNullPropertyNames(source);
        int aLen = nullPropertyNames.length;
        int bLen = forcedIgnoreProperties.length;
        String[] result = new String[aLen + bLen];

        System.arraycopy(nullPropertyNames, 0, result, 0, aLen);
        System.arraycopy(nullPropertyNames, 0, result, aLen, bLen);

        return result;
    }

    private static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null)
                emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}