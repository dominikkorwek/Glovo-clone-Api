package pl.dodo.eLunchApp.service;

import pl.dodo.eLunchApp.dto.Product.ProductDTOBasic;
import pl.dodo.eLunchApp.dto.Product.ProductDTOExtended;
import pl.dodo.eLunchApp.exceptions.eLunchError;
import pl.dodo.eLunchApp.model.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService extends ValidationService<Product> {
    List<ProductDTOBasic> getAll();
    void add(ProductDTOExtended dtoExtended);
    void edit(UUID uuid, ProductDTOExtended dtoExtended) throws eLunchError.InvalidUuid, eLunchError.ObjectNotFound;
    void delete(UUID uuid) throws eLunchError.ObjectNotFound;
    ProductDTOExtended getByUuid(UUID uuid) throws eLunchError.ObjectNotFound;
}
