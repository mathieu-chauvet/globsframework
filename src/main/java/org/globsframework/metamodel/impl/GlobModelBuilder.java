package org.globsframework.metamodel.impl;

import org.globsframework.metamodel.GlobModel;
import org.globsframework.metamodel.GlobType;

public class GlobModelBuilder {
    DefaultGlobModel globModel = new DefaultGlobModel();
    public GlobModelBuilder create(){
        return new GlobModelBuilder();
    }

    public GlobModelBuilder add(GlobModel model) {
        model.getAll().forEach(globType -> globModel.add(globType));
        return this;
    }

    public GlobModelBuilder add(GlobType globType) {
        globModel.add(globType);
        return this;
    }

    public GlobModel get() {
        return globModel;
    }
}
