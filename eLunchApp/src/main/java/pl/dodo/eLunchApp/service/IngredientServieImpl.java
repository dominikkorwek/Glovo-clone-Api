package pl.dodo.eLunchApp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import pl.dodo.eLunchApp.dto.Ingredient.IngredientDTOBasic;
import pl.dodo.eLunchApp.exceptions.Result;
import pl.dodo.eLunchApp.exceptions.eLunchError;
import pl.dodo.eLunchApp.mapper.IngredientMapper;
import pl.dodo.eLunchApp.model.Ingredient;
import pl.dodo.eLunchApp.repository.IngredientRepository;

import java.util.List;
import java.util.Optional;
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
        return getAllEntites(ingredientRepository,ingredientMapper::mapToDtoBasic);
    }

    @Override
    @CacheEvict(cacheNames = "ingredients", allEntries = true)
    public Result<Void> put(UUID uuid, IngredientDTOBasic dtoBasic) {
        if (!dtoBasic.getUuid().equals(uuid))
            return Result.failure(new eLunchError.InvalidUuid(dtoBasic.getUuid(),uuid));

        Optional<Ingredient> byUuid = ingredientRepository.findByUuid(uuid);
        if (byUuid.isEmpty())
            return Result.failure(new eLunchError.ObjectNotFound(Ingredient.class));

        byUuid.get().edit(ingredientMapper.mapToEntity(dtoBasic));
        return Result.success(null);
    }

    @Override
    @CacheEvict(cacheNames = "ingredients", key = "#uuid")
    public Result<Void> delete(UUID uuid) {
        return deleteEntity(uuid,ingredientRepository);
    }

    @Override
    public Result<IngredientDTOBasic> getByUuid(UUID uuid) {
        return getEntityByUuid(uuid,ingredientRepository,ingredientMapper::mapToDtoBasic, Ingredient.class);
    }
}
