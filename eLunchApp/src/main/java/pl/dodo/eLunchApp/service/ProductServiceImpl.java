package pl.dodo.eLunchApp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import pl.dodo.eLunchApp.dto.Product.ProductDTOBasic;
import pl.dodo.eLunchApp.dto.Product.ProductDTOExtended;
import pl.dodo.eLunchApp.exceptions.Result;
import pl.dodo.eLunchApp.mapper.ProductMapper;
import pl.dodo.eLunchApp.model.Product;
import pl.dodo.eLunchApp.repository.ProductRepository;

import java.util.List;
import java.util.UUID;

@Service
@CacheConfig(cacheNames = "products")
@RequiredArgsConstructor
public class ProductServiceImpl extends BaseService implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public List<ProductDTOBasic> getAll() {
        return productRepository.findAll().stream()
                .map(productMapper::mapToDtoBasic)
                .toList();
    }

    @Override
    @CacheEvict(cacheNames = "products", allEntries = true)
    public void put(UUID uuid, ProductDTOExtended productDTOExtended) {
       //todo
    }

    @Override
    @CacheEvict(cacheNames = "products", key = "#uuid")
    public Result<Void> delete(UUID uuid) {
        return deleteEntity(uuid,productRepository);
    }

    @Override
    public Result<ProductDTOExtended> getByUuid(UUID uuid) {
        return getEntityByUuid(uuid,productRepository,productMapper::mapToDtoExtended, Product.class);
    }
}