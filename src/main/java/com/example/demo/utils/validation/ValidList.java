package com.example.demo.utils.validation;

import com.google.common.collect.ForwardingList;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

public class ValidList<T> extends ForwardingList<T> {
    private final List<@Valid T> list;

    public ValidList() {
        this(new ArrayList<>());
    }

    public ValidList(List<@Valid T> list) {
        this.list = list;
    }

    @Override
    protected List<T> delegate() {
        return list;
    }

    /**
     * Exposed for the {@link javax.validation.Validator} to access the list path
     */
    public List<T> getList() {
        return list;
    }
}

