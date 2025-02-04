package pl.dodo.eLunchApp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import pl.dodo.eLunchApp.dto.Product.ProductDTOBasic;
import pl.dodo.eLunchApp.dto.Product.ProductDTOExtended;
import pl.dodo.eLunchApp.exceptions.eLunchError;
import pl.dodo.eLunchApp.mapper.ProductMapper;
import pl.dodo.eLunchApp.model.Dish;
import pl.dodo.eLunchApp.model.Ingredient;
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
    private final IngredientService ingredientService;
    private final DishService dishService;

    @Override
    public List<ProductDTOBasic> getAll() {
        return productRepository.findAll().stream()
                .map(productMapper::mapToDtoBasic)
                .toList();
    }

    @Override
    public void add(ProductDTOExtended dtoExtended) {
        addEntity(dtoExtended, productRepository, productMapper::mapToEntity);
    }

    @Override
    @CacheEvict(cacheNames = "products", allEntries = true)
    public void edit(UUID uuid, ProductDTOExtended productDTOExtended) throws eLunchError.InvalidUuid, eLunchError.ObjectNotFound {
        UUID dtoUuid = productDTOExtended.getProductDTOBasic().getUuid();
        if (!dtoUuid.equals(uuid))
            throw new eLunchError.InvalidUuid(dtoUuid, uuid);

        Product byUuid = productRepository.findByUuid(uuid)
                .orElseThrow(() -> new eLunchError.ObjectNotFound(Product.class));

        Product productNew = productMapper.mapToEntity(productDTOExtended);

        List<Ingredient> ingredients = validateList(productNew.getIngredients(), ingredientService);
        Dish dish = validateObject(productNew.getDish(), dishService);

        productNew.setIngredients(ingredients);
        productNew.setDish(dish);
        byUuid.edit(productNew);
    }

    @Override
    @CacheEvict(cacheNames = "products", key = "#uuid")
    public void delete(UUID uuid) throws eLunchError.ObjectNotFound {
        deleteEntity(uuid,productRepository);
    }

    @Override
    public ProductDTOExtended getByUuid(UUID uuid) throws eLunchError.ObjectNotFound {
        return getDtoByUuid(uuid,productRepository,productMapper::mapToDtoExtended, Product.class);
    }

    @Override
    public Product validate(Product object) throws eLunchError.ObjectNotFound {
        return getEntityByUuid(productRepository, object.getUuid(), Product.class);
    }
}