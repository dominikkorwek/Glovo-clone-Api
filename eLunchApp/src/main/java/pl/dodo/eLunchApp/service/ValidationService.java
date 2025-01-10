package pl.dodo.eLunchApp.service;

import pl.dodo.eLunchApp.exceptions.Result;

public interface ValidationService<T> {
    Result<T> validate(T user);

}
