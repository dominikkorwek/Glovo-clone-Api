package pl.dodo.eLunchApp.service;

import pl.dodo.eLunchApp.exceptions.eLunchError;

public interface ValidationService<T> {
    T validate(T object) throws eLunchError.ObjectNotFound;

}
