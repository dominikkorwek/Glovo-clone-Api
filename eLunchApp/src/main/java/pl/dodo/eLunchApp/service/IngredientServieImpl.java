package pl.dodo.eLunchApp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import pl.dodo.eLunchApp.dto.Ingredient.IngredientDTOBasic;
import pl.dodo.eLunchApp.exceptions.eLunchError;
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
        return getAllEntites(ingredientRepository,ingredientMapper::mapToDtoBasic);
    }

    @Override
    public void add(IngredientDTOBasic dtoBasic) {
        addEntity(dtoBasic, ingredientRepository, ingredientMapper::mapToEntity);
    }

    @Override
    @CacheEvict(cacheNames = "ingredients", allEntries = true)
    public void edit(UUID uuid, IngredientDTOBasic dtoBasic) throws eLunchError.InvalidUuid, eLunchError.ObjectNotFound {
        if (!dtoBasic.getUuid().equals(uuid))
            throw new eLunchError.InvalidUuid(dtoBasic.getUuid(),uuid);

        Ingredient byUuid = ingredientRepository.findByUuid(uuid)
                .orElseThrow(() -> new eLunchError.ObjectNotFound(Ingredient.class));

        byUuid.edit(ingredientMapper.mapToEntity(dtoBasic));
    }

    @Override
    @CacheEvict(cacheNames = "ingredients", key = "#uuid")
    public void delete(UUID uuid) throws eLunchError.ObjectNotFound {
        deleteEntity(uuid,ingredientRepository);
    }

    @Override
    public IngredientDTOBasic getByUuid(UUID uuid) throws eLunchError.ObjectNotFound {
        return getDtoByUuid(uuid,ingredientRepository,ingredientMapper::mapToDtoBasic, Ingredient.class);
    }

    @Override
    public Ingredient validate(Ingredient object) throws eLunchError.ObjectNotFound {
        return getEntityByUuid(ingredientRepository, object.getUuid(), Ingredient.class);
    }
}
