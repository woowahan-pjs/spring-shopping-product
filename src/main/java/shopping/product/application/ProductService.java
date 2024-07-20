package shopping.product.application;

import org.springframework.stereotype.Service;
import shopping.product.application.dto.ProductRequest;
import shopping.product.application.dto.ProductResponse;
import shopping.product.domain.Product;
import shopping.product.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponse save(final ProductRequest createRequest) {
        final Product product = productRepository.save(ProductRequest.toEntity(createRequest));

        return ProductResponse.from(product);
    }

    public List<ProductResponse> findAll() {
        return productRepository.findAll()
                .stream()
                .map(ProductResponse::from)
                .toList();
    }

    public ProductResponse findById(final Long id) {
        return null;
    }

    public void update(final Long id, final ProductRequest request) {

    }

    public void delete(final Long id) {

    }
}
