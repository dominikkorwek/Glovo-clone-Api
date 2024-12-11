package pl.dodo.eLunchApp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import pl.dodo.eLunchApp.dto.Discount.DiscountCodeDTOBasic;
import pl.dodo.eLunchApp.dto.Discount.DiscountCodeDTOExtended;
import pl.dodo.eLunchApp.exceptions.Result;
import pl.dodo.eLunchApp.mapper.DiscountCodeMapper;
import pl.dodo.eLunchApp.model.DiscountCode;
import pl.dodo.eLunchApp.repository.DiscountCodeRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "discountCodes")
public class DiscountCodeServiceImpl extends BaseService implements DiscountCodeService{
    private final DiscountCodeRepository discountCodeRepository;
    private final DiscountCodeMapper discountCodeMapper;

    @Override
    @Cacheable(cacheNames = "discountCodes")
    public List<DiscountCodeDTOBasic> getAll() {
        return discountCodeRepository.findAll().stream()
                .map(discountCodeMapper::mapToDtoBasic)
                .toList();
    }

    @Override
    @CacheEvict(cacheNames = "discountCodes", allEntries = true)
    public Result<Void> put(UUID uuid, DiscountCodeDTOExtended basicDelivererDto) {
        //todo
    }

    @Override
    @CacheEvict(cacheNames = "discountCodes", key = "#uuid")
    public Result<Void> delete(UUID uuid) {
        return deleteEntity(uuid,discountCodeRepository);
    }

    @Override
    public Result<DiscountCodeDTOExtended> getByUuid(UUID uuid) {
        return getByUuid(uuid,discountCodeRepository,discountCodeMapper::mapToDtoExtended, DiscountCode.class);
    }
}
