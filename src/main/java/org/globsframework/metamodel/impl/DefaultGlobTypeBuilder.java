package org.globsframework.metamodel.impl;

import org.globsframework.metamodel.Annotations;
import org.globsframework.metamodel.GlobType;
import org.globsframework.metamodel.GlobTypeBuilder;
import org.globsframework.metamodel.annotations.*;
import org.globsframework.metamodel.fields.*;
import org.globsframework.metamodel.fields.impl.*;
import org.globsframework.metamodel.utils.MutableAnnotations;
import org.globsframework.model.Glob;
import org.globsframework.utils.exceptions.InvalidState;

import java.util.Collection;

public class DefaultGlobTypeBuilder implements GlobTypeBuilder {
    private DefaultGlobType type;
    private DefaultFieldFactory factory;
    private int index;
    private int keyIndex;
    private Boolean isKeyFromGlob;

    public static GlobTypeBuilder init(String typeName) {
        return new DefaultGlobTypeBuilder(typeName);
    }

    public DefaultGlobTypeBuilder(String typeName) {
        type = new DefaultGlobType(typeName);
        factory = new DefaultFieldFactory(type);
    }

    public GlobTypeBuilder addIntegerKey(String fieldName, Glob... globAnnotations) {
        return this;
    }

    private MutableAnnotations adaptAnnotation(Collection<Glob> annotations) {
        DefaultAnnotations defaultAnnotations = new DefaultAnnotations();
        for (Glob annotation : annotations) {
            defaultAnnotations.addAnnotation(annotation);
        }
        return defaultAnnotations;
    }

    public GlobTypeBuilder addAnnotation(Glob annotation) {
        type.addAnnotation(annotation);
        return this;
    }

    public GlobTypeBuilder addStringField(String fieldName, Collection<Glob> annotations) {
        createStringField(fieldName, annotations);
        return this;
    }

    private DefaultStringField createStringField(String fieldName, Collection<Glob> globAnnotations) {
        MutableAnnotations annotations = adaptAnnotation(globAnnotations);
        String defaultValue = annotations.getValueOrDefault(DefaultStringAnnotationType.UNIQUE_KEY,
                                                            DefaultStringAnnotationType.DEFAULT_VALUE, null);
        boolean isKey = annotations.hasAnnotation(KeyAnnotationType.UNIQUE_KEY);
        DefaultStringField field = factory.addString(fieldName, isKey, keyIndex, index, defaultValue);
        if (isKey) {
            updateKeyIndex(annotations);
        }
        field.addAnnotations(annotations.streamAnnotations());
        index++;
        return field;
    }

    public GlobTypeBuilder addStringArrayField(String fieldName, Collection<Glob> annotations) {
        createStringArrayField(fieldName, annotations);
        return this;
    }

    private DefaultStringArrayField createStringArrayField(String fieldName, Collection<Glob> globAnnotations) {
        MutableAnnotations annotations = adaptAnnotation(globAnnotations);
        boolean isKey = annotations.hasAnnotation(KeyAnnotationType.UNIQUE_KEY);
        DefaultStringArrayField field = factory.addStringArray(fieldName, isKey, keyIndex, index);
        if (isKey) {
            updateKeyIndex(annotations);
        }
        field.addAnnotations(annotations.streamAnnotations());
        index++;
        return field;
    }


    public GlobTypeBuilder addIntegerField(String fieldName, Collection<Glob> globAnnotations) {
        createIntegerField(fieldName, globAnnotations);
        return this;
    }

    private DefaultIntegerField createIntegerField(String fieldName, Collection<Glob> globAnnotations) {
        DefaultIntegerField field;
        MutableAnnotations annotations = adaptAnnotation(globAnnotations);
        Integer defaultValue = annotations.getValueOrDefault(DefaultIntegerAnnotationType.UNIQUE_KEY, DefaultIntegerAnnotationType.DEFAULT_VALUE, null);
        boolean isKey = annotations.hasAnnotation(KeyAnnotationType.UNIQUE_KEY);
        field = factory.addInteger(fieldName, isKey, keyIndex, index, defaultValue);
        if (isKey) {
            updateKeyIndex(annotations);
        }
        field.addAnnotations(annotations.streamAnnotations());
        index++;
        return field;
    }

    private DefaultIntegerArrayField createIntegerArrayField(String fieldName, Collection<Glob> globAnnotations) {
        MutableAnnotations annotations = adaptAnnotation(globAnnotations);
        boolean isKey = annotations.hasAnnotation(KeyAnnotationType.UNIQUE_KEY);
        DefaultIntegerArrayField field = factory.addIntegerArray(fieldName, isKey, keyIndex, index);
        if (isKey) {
            updateKeyIndex(annotations);
        }
        field.addAnnotations(annotations.streamAnnotations());
        index++;
        return field;
    }

    public GlobTypeBuilder addDoubleField(String fieldName, Collection<Glob> globAnnotations) {
        createDoubleField(fieldName, globAnnotations);
        return this;
    }

    public GlobTypeBuilder addDoubleArrayField(String fieldName, Collection<Glob> globAnnotations) {
        createDoubleArrayField(fieldName, globAnnotations);
        return this;
    }

    public GlobTypeBuilder addIntegerArrayField(String fieldName, Collection<Glob> globAnnotations) {
        createIntegerArrayField(fieldName, globAnnotations);
        return this;
    }

    public GlobTypeBuilder addLongArrayField(String fieldName, Collection<Glob> globAnnotations) {
        createLongArrayField(fieldName, globAnnotations);
        return this;
    }

    public GlobTypeBuilder addBigDecimalField(String fieldName, Collection<Glob> globAnnotations) {
        createBigDecimalField(fieldName, globAnnotations);
        return this;
    }

    public GlobTypeBuilder addBigDecimalArrayField(String fieldName, Collection<Glob> globAnnotations) {
        createBigDecimalArrayField(fieldName, globAnnotations);
        return this;
    }

    public GlobTypeBuilder addDateField(String fieldName, Collection<Glob> globAnnotations) {
        createDateField(fieldName, globAnnotations);
        return this;
    }

    public GlobTypeBuilder addDateTimeField(String fieldName, Collection<Glob> globAnnotations) {
        createDateTimeField(fieldName, globAnnotations);
        return this;
    }

    private DefaultDoubleField createDoubleField(String fieldName, Collection<Glob> globAnnotations) {
        MutableAnnotations annotations = adaptAnnotation(globAnnotations);
        Double defaultValue = annotations.getValueOrDefault(DefaultDoubleAnnotationType.UNIQUE_KEY, DefaultDoubleAnnotationType.DEFAULT_VALUE, null);
        boolean isKey = annotations.hasAnnotation(KeyAnnotationType.UNIQUE_KEY);
        DefaultDoubleField doubleField = factory.addDouble(fieldName, isKey, keyIndex, index, defaultValue);
        if (isKey) {
            updateKeyIndex(annotations);
        }
        doubleField.addAnnotations(annotations.streamAnnotations());
        index++;
        return doubleField;
    }

    private DefaultDoubleArrayField createDoubleArrayField(String fieldName, Collection<Glob> globAnnotations) {
        MutableAnnotations annotations = adaptAnnotation(globAnnotations);
        boolean isKey = annotations.hasAnnotation(KeyAnnotationType.UNIQUE_KEY);
        DefaultDoubleArrayField field = factory.addDoubleArray(fieldName, isKey, keyIndex, index);
        if (isKey) {
            updateKeyIndex(annotations);
        }
        field.addAnnotations(annotations.streamAnnotations());
        index++;
        return field;
    }

    private DefaultBigDecimalField createBigDecimalField(String fieldName, Collection<Glob> globAnnotations) {
        MutableAnnotations annotations = adaptAnnotation(globAnnotations);
        boolean isKey = annotations.hasAnnotation(KeyAnnotationType.UNIQUE_KEY);
        DefaultBigDecimalField bigDecimalField = factory.addBigDecimal(fieldName, isKey, keyIndex, index);
        if (isKey) {
            updateKeyIndex(annotations);
        }
        bigDecimalField.addAnnotations(annotations.streamAnnotations());
        index++;
        return bigDecimalField;
    }

    private DefaultBigDecimalArrayField createBigDecimalArrayField(String fieldName, Collection<Glob> globAnnotations) {
        MutableAnnotations annotations = adaptAnnotation(globAnnotations);
        boolean isKey = annotations.hasAnnotation(KeyAnnotationType.UNIQUE_KEY);
        DefaultBigDecimalArrayField bigDecimalArrayField = factory.addBigDecimalArray(fieldName, isKey, keyIndex, index);
        if (isKey) {
            updateKeyIndex(annotations);
        }
        bigDecimalArrayField.addAnnotations(annotations.streamAnnotations());
        index++;
        return bigDecimalArrayField;
    }

    private DefaultDateTimeField createDateTimeField(String fieldName, Collection<Glob> globAnnotations) {
        MutableAnnotations annotations = adaptAnnotation(globAnnotations);
        boolean isKey = annotations.hasAnnotation(KeyAnnotationType.UNIQUE_KEY);
        DefaultDateTimeField dateTimeField = factory.addDateTime(fieldName, isKey, keyIndex, index);
        if (isKey) {
            updateKeyIndex(annotations);
        }
        dateTimeField.addAnnotations(annotations.streamAnnotations());
        index++;
        return dateTimeField;
    }

    private DefaultDateField createDateField(String fieldName, Collection<Glob> globAnnotations) {
        MutableAnnotations annotations = adaptAnnotation(globAnnotations);
        boolean isKey = annotations.hasAnnotation(KeyAnnotationType.UNIQUE_KEY);
        DefaultDateField dateField = factory.addDate(fieldName, isKey, keyIndex, index);
        if (isKey) {
            updateKeyIndex(annotations);
        }
        dateField.addAnnotations(annotations.streamAnnotations());
        index++;
        return dateField;
    }


    public GlobTypeBuilder addLongField(String fieldName, Collection<Glob> globAnnotations) {
        createLongField(fieldName, globAnnotations);
        return this;
    }

    private DefaultLongField createLongField(String fieldName, Collection<Glob> globAnnotations) {
        MutableAnnotations annotations = adaptAnnotation(globAnnotations);
        Long defaultValue = annotations.getValueOrDefault(DefaultLongAnnotationType.UNIQUE_KEY,
                                                          DefaultLongAnnotationType.DEFAULT_VALUE, null);
        boolean isKey = annotations.hasAnnotation(KeyAnnotationType.UNIQUE_KEY);
        DefaultLongField longField = factory.addLong(fieldName, isKey, keyIndex, index, defaultValue);
        if (isKey) {
            updateKeyIndex(annotations);
        }
        longField.addAnnotations(annotations.streamAnnotations());
        index++;
        return longField;
    }

    private DefaultLongArrayField createLongArrayField(String fieldName, Collection<Glob> globAnnotations) {
        MutableAnnotations annotations = adaptAnnotation(globAnnotations);
        boolean isKey = annotations.hasAnnotation(KeyAnnotationType.UNIQUE_KEY);
        DefaultLongArrayField field = factory.addLongArray(fieldName, isKey, keyIndex, index);
        if (isKey) {
            updateKeyIndex(annotations);
        }
        field.addAnnotations(annotations.streamAnnotations());
        index++;
        return field;
    }

    public GlobTypeBuilder addBooleanArrayField(String fieldName, Collection<Glob> globAnnotations) {
        createBooleanArrayField(fieldName, globAnnotations);
        return this;
    }

    private DefaultBooleanArrayField createBooleanArrayField(String fieldName, Collection<Glob> globAnnotations) {
        MutableAnnotations annotations = adaptAnnotation(globAnnotations);
        boolean isKey = annotations.hasAnnotation(KeyAnnotationType.UNIQUE_KEY);
        DefaultBooleanArrayField field = factory.addBooleanArray(fieldName, isKey, keyIndex, index);
        if (isKey) {
            updateKeyIndex(annotations);
        }
        field.addAnnotations(annotations.streamAnnotations());
        index++;
        return field;
    }

    public GlobTypeBuilder addBooleanField(String fieldName, Collection<Glob> globAnnotations) {
        createBooleanField(fieldName, globAnnotations);
        return this;
    }

    private DefaultBooleanField createBooleanField(String fieldName, Collection<Glob> globAnnotations) {
        MutableAnnotations annotations = adaptAnnotation(globAnnotations);
        Boolean defaultValue = annotations.getValueOrDefault(DefaultBooleanAnnotationType.UNIQUE_KEY,
                                                             DefaultBooleanAnnotationType.DEFAULT_VALUE, null);
        boolean isKey = annotations.hasAnnotation(KeyAnnotationType.UNIQUE_KEY);
        DefaultBooleanField field = factory.addBoolean(fieldName, isKey, keyIndex, index, defaultValue);
        if (isKey) {
            updateKeyIndex(annotations);
        }
        field.addAnnotations(annotations.streamAnnotations());
        index++;
        return field;
    }

    private void updateKeyIndex(MutableAnnotations annotations) {
        Glob annotation = annotations.getAnnotation(KeyAnnotationType.UNIQUE_KEY);
        int tmpKeyIndex = -1;
        if (annotation != null) {
            tmpKeyIndex = annotation.get(KeyAnnotationType.INDEX, -1);
        }
        if (tmpKeyIndex == -1) {
            if (isKeyFromGlob == null) {
                isKeyFromGlob = false;
            }
            else if (isKeyFromGlob) {
                throw new InvalidState("Forbidden to mix keyIndex from annotation and default");
            }
            annotations.addAnnotation(KeyAnnotationType.create(keyIndex));
            keyIndex++;
        }
        else {
            keyIndex = tmpKeyIndex;
            if (isKeyFromGlob == null) {
                isKeyFromGlob = true;
            }
            else {
                if (!isKeyFromGlob) {
                    throw new InvalidState("Forbidden to mix keyIndex from annotation and default");
                }
            }
        }
    }

    public GlobTypeBuilder addBlobField(String fieldName, Collection<Glob> globAnnotations) {
        createBlobField(fieldName, globAnnotations);
        return this;
    }

    private DefaultBlobField createBlobField(String fieldName, Collection<Glob> globAnnotations) {
        Annotations annotations = adaptAnnotation(globAnnotations);
        DefaultBlobField field = factory.addBlob(fieldName, index);
        field.addAnnotations(annotations.streamAnnotations());
        index++;
        return field;
    }

    public StringField declareStringField(String fieldName, Collection<Glob> globAnnotations) {
        return createStringField(fieldName, globAnnotations);
    }

    public StringArrayField declareStringArrayField(String fieldName, Collection<Glob> globAnnotations) {
        return createStringArrayField(fieldName, globAnnotations);
    }

    public IntegerField declareIntegerField(String fieldName, Collection<Glob> annotations) {
        return createIntegerField(fieldName, annotations);
    }

    public IntegerArrayField declareIntegerArrayField(String fieldName, Collection<Glob> annotations) {
        return createIntegerArrayField(fieldName, annotations);
    }

    public DoubleField declareDoubleField(String fieldName, Collection<Glob> annotations) {
        return createDoubleField(fieldName, annotations);
    }

    public DoubleArrayField declareDoubleArrayField(String fieldName, Collection<Glob> annotations) {
        return createDoubleArrayField(fieldName, annotations);
    }

    public BigDecimalField declareBigDecimalField(String fieldName, Collection<Glob> annotations) {
        return createBigDecimalField(fieldName, annotations);
    }

    public BigDecimalArrayField declareBigDecimalArrayField(String fieldName, Collection<Glob> annotations) {
        return createBigDecimalArrayField(fieldName, annotations);
    }

    public BooleanField declareBooleanField(String fieldName, Collection<Glob> annotations) {
        return createBooleanField(fieldName, annotations);
    }

    public BooleanArrayField declareBooleanArrayField(String fieldName, Collection<Glob> annotations) {
        return createBooleanArrayField(fieldName, annotations);
    }

    public DateField declareDateField(String fieldName, Collection<Glob> annotations) {
        return createDateField(fieldName, annotations);
    }

    public DateTimeField declareDateTimeField(String fieldName, Collection<Glob> annotations) {
        return createDateTimeField(fieldName, annotations);
    }

    public LongField declareLongField(String fieldName, Collection<Glob> annotations) {
        return createLongField(fieldName, annotations);
    }

    public LongArrayField declareArrayLongField(String fieldName, Collection<Glob> annotations) {
        return createLongArrayField(fieldName, annotations);
    }

    public BlobField declareBlobField(String fieldName, Collection<Glob> annotations) {
        return createBlobField(fieldName, annotations);
    }

    public <T> void register(Class<T> klass, T t) {
        type.register(klass, t);
    }

    public GlobType get() {
        type.completeInit();
        return type;
    }

    public boolean isKnown(String fieldName) {
        return type.hasField(fieldName);
    }
}
