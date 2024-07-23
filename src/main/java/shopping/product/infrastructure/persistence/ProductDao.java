package shopping.product.infrastructure.persistence;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;
import shopping.product.infrastructure.api.dto.ProductDetailResponse;

import javax.sql.DataSource;

@Component
public class ProductDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ProductDao(final DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public ProductDetailResponse find(final long shopId, final long productId) {
        final SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("shopId", shopId)
                .addValue("id", productId);

        return namedParameterJdbcTemplate.queryForObject(
                """
                        SELECT 
                            p.name as product_name, p.amount, p.image_url, sc.name as category_name, s.name as shop_name, se.name as seller_name   
                        FROM products p
                        LEFT JOIN shops s ON p.shop_id = s.id 
                        LEFT JOIN sub_categories sc ON p.sub_category_id = sc.id 
                        LEFT JOIN sellers se ON s.seller_id = se.id
                        WHERE p.id = :id 
                        """,
                namedParameters, new ProductMapper()
        );
    }
}

