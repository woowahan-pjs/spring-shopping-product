package shopping.product.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shopping.product.application.dto.ProductCreateRequest;
import shopping.product.application.dto.ProductModifyRequest;
import shopping.product.application.dto.ProductResponse;
import shopping.product.domain.Product;
import shopping.product.exception.InvalidProductException;
import shopping.product.exception.ProductNotExistException;
import shopping.product.infra.ProfanityChecker;
import shopping.product.repository.ProductRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;
    private final ProfanityChecker profanityChecker;

    public ProductService(final ProductRepository productRepository, final ProfanityChecker profanityChecker) {
        this.productRepository = productRepository;
        this.profanityChecker = profanityChecker;
    }

    public List<ProductResponse> findAll() {
        return productRepository.findAll()
                .stream()
                .map(ProductResponse::from)
                .toList();
    }

    public ProductResponse findById(final Long id) {
        final Product product = findProductById(id);

        return ProductResponse.from(product);
    }

    @Transactional
    public ProductResponse save(final ProductCreateRequest request) {
        validateProfanity(request.getName());
        final Product product = productRepository.save(request.toEntity());

        return ProductResponse.from(product);
    }

    @Transactional
    public void update(final Long id, final ProductModifyRequest request) {
        validateProfanity(request.getName());

        final Product product = findProductById(id);

        product.modify(request.toEntity());
    }

    private void validateProfanity(final String name) {
        if (profanityChecker.containsProfanity(name)) {
            throw new InvalidProductException("상품이름에는 비속어를 포함할 수 없습니다.");
        }
    }

    @Transactional
    public void delete(final Long id) {
        final Product product = findProductById(id);

        productRepository.delete(product);
    }

    private Product findProductById(final Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotExistException("상품이 존재하지 않습니다."));
    }
}
