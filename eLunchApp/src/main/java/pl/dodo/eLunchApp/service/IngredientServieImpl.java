package pl.dodo.eLunchApp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import pl.dodo.eLunchApp.dto.Ingredient.IngredientDTOBasic;
import pl.dodo.eLunchApp.exceptions.Result;
import pl.dodo.eLunchApp.mapper.IngredientMapper;
import pl.dodo.eLunchApp.model.Ingredient;
import pl.dodo.eLunchApp.repository.IngredientRepository;

import java.util.List;
import java.util.UUID;

@Service
@CacheConfig(cacheNames = "ingredients")
@RequiredArgsConstructor
public class IngredientServieImpl extends BaseService implements IngredientService {
    private final IngredientRepository ingredientRepository;
    private final IngredientMapper ingredientMapper;

    @Override
    @Cacheable(cacheNames = "ingredients")
    public List<IngredientDTOBasic> getAll() {
        return ingredientRepository.findAll().stream()
                .map(ingredientMapper::mapToDtoBasic)
                .toList();
    }

    @Override
    @CacheEvict(cacheNames = "ingredients", allEntries = true)
    public void put(UUID uuid, IngredientDTOBasic basicDelivererDto) {
        //todo
    }

    @Override
    @CacheEvict(cacheNames = "ingredients", key = "#uuid")
    public Result<Void> delete(UUID uuid) {
        return deleteEntity(uuid,ingredientRepository);
    }

    @Override
    public Result<IngredientDTOBasic> getByUuid(UUID uuid) {
        return getByUuid(uuid,ingredientRepository,ingredientMapper::mapToDtoBasic, Ingredient.class);
    }
}
