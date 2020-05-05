package org.globsframework.utils;

import org.globsframework.metamodel.Field;
import org.globsframework.metamodel.fields.*;
import org.globsframework.model.MutableGlob;

public class StringConverter {
    public static FromStringConverter createConverter(Field field, String arraySeparator) {
        return field.safeVisit(new FieldVisitor.AbstractWithErrorVisitor() {
                        FromStringConverter fromStringConverter1;

                        public void visitInteger(IntegerField field1) throws Exception {
                            fromStringConverter1 = new ToIntegerConverter(field1);
                        }

                        public void visitString(StringField field1) throws Exception {
                            fromStringConverter1 = new ToStringConverter(field1);
                        }

                        public void visitStringArray(StringArrayField field1) throws Exception {
                            fromStringConverter1 = new ToStringArrayConverter(field1, arraySeparator);
                        }

                        public void visitLong(LongField field1) throws Exception {
                            fromStringConverter1 = new ToLongConverter(field1);
                        }
                    }).fromStringConverter1;
    }

    public interface FromStringConverter {
        void convert(MutableGlob glob, String str);
    }

    public static class ToStringConverter implements FromStringConverter {
        final StringField field;

        public ToStringConverter(StringField field) {
            this.field = field;
        }

        public void convert(MutableGlob glob, String str) {
            if (str != null) {
                glob.set(field, str);
            }
        }
    }

    public static class ToIntegerConverter implements FromStringConverter {
        final IntegerField field;

        public ToIntegerConverter(IntegerField field) {
            this.field = field;
        }

        public void convert(MutableGlob glob, String str) {
            if (str != null) {
                glob.set(field, Integer.parseInt(str));
            }
        }
    }

    public static class ToLongConverter implements FromStringConverter {
        final LongField field;

        public ToLongConverter(LongField field) {
            this.field = field;
        }

        public void convert(MutableGlob glob, String str) {
            if (str != null) {
                glob.set(field, Long.parseLong(str));
            }
        }
    }

    public static class ToStringArrayConverter implements FromStringConverter {
        final StringArrayField field;
        private String arraySeparator;

        public ToStringArrayConverter(StringArrayField field, String arraySeparator) {
            this.field = field;
            this.arraySeparator = arraySeparator;
        }

        public void convert(MutableGlob glob, String str) {
            if (str != null) {
                String[] data;
                if (arraySeparator != null) {
                    data = str.split(arraySeparator);
                }
                else {
                    data = new String[]{str};
                }
                String[] actual = glob.getOrEmpty(field);
                String[] newValue = new String[actual.length + data.length];
                System.arraycopy(actual, 0, newValue, 0, actual.length);
                for (int i = 0; i < data.length; i++) {
                    String d = data[i];
                    newValue[actual.length + i] = d;
                }
                glob.set(field, newValue);
            }
        }
    }
}
