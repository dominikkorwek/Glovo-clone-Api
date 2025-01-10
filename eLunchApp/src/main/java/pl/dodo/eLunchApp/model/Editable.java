package pl.dodo.eLunchApp.model;

public interface Editable<T extends Editable<T>> {
    void edit(T other);
}

