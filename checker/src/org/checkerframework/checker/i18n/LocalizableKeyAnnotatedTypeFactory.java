package org.checkerframework.checker.i18n;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import org.checkerframework.checker.i18n.qual.LocalizableKey;
import org.checkerframework.checker.i18n.qual.LocalizableKeyBottom;
import org.checkerframework.checker.i18n.qual.UnknownLocalizableKey;
import org.checkerframework.checker.propkey.PropertyKeyAnnotatedTypeFactory;
import org.checkerframework.common.basetype.BaseTypeChecker;
import org.checkerframework.framework.type.treeannotator.ListTreeAnnotator;
import org.checkerframework.framework.type.treeannotator.TreeAnnotator;

/** A PropertyKeyATF that uses LocalizableKey to annotate the keys. */
public class LocalizableKeyAnnotatedTypeFactory extends PropertyKeyAnnotatedTypeFactory {

    public LocalizableKeyAnnotatedTypeFactory(BaseTypeChecker checker) {
        super(checker);
    }

    @Override
    protected Set<Class<? extends Annotation>> createSupportedTypeQualifiers() {
        return new LinkedHashSet<Class<? extends Annotation>>(
                Arrays.asList(
                        LocalizableKey.class,
                        LocalizableKeyBottom.class,
                        UnknownLocalizableKey.class));
    }

    @Override
    public TreeAnnotator createTreeAnnotator() {
        return new ListTreeAnnotator(
                super.createBasicTreeAnnotator(),
                new KeyLookupTreeAnnotator(this, LocalizableKey.class));
    }
}
