package pl.dodo.eLunchApp.repository;

import org.springframework.stereotype.Repository;
import pl.dodo.eLunchApp.model.Product;

@Repository
public interface ProductRepository  extends BaseRepository<Product> {
}
