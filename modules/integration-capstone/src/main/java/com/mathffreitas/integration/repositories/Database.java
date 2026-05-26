package com.mathffreitas.integration.repositories;

import com.mathffreitas.integration.models.ProductExhibition;
import com.mathffreitas.integration.models.types.Category;
import com.mathffreitas.integration.repositories.utils.DatabaseSettings;
import com.mathffreitas.integration.repositories.utils.Mock;

import java.lang.IO;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;
import java.util.Map;
import java.util.Set;
import java.util.Optional;
import java.util.Comparator;
public class Database {
    private final DatabaseSettings settings;
    private History history = new HistoryImpl();
    private Mock mock;
    // non thread safe
    private final HashMap<Long, ProductExhibition> productsLegacy = new HashMap<>();
    // thread safe -> to use in concurrency
    private final ConcurrentHashMap<Long, ProductExhibition> products = new ConcurrentHashMap<>();

    public Database(DatabaseSettings settings) {
        this.settings = settings;
        if (isEnabledMockingData()) {
            this.mock = new Mock();
        }
    }

    public List<ProductExhibition> getProducts() {
        List<ProductExhibition> products = getProductsList();
        if (isEnabledLogging()) {
            products.forEach(IO::println);
        }
        return products;
    }

    public History getHistory() {
        return history;
    }

    public void addProduct(ProductExhibition productExibition) {
        this.products.put(productExibition.id(), productExibition);
    }

    public void updateProductPriceById(Long id, BigDecimal newPrice) {
        Optional<ProductExhibition> currentProduct = getProductById(id);
        if (currentProduct.isEmpty()) {
            return;
        }
        ProductExhibition updatedProduct = currentProduct.get().setNewPrice(newPrice);
        this.products.put(id, updatedProduct);
        if (isEnabledLogging()) {
            IO.println(updatedProduct);
        }
    }

    public Set<Category> getAllCategories() {
        Set<Category> categories = getCategoriesStream()
                .collect(Collectors.toCollection(HashSet::new));

        if (isEnabledLogging()) {
            categories.forEach(IO::println);
        }
        return categories;
    }

    public Set<Category> getAllCategoriesByOrder() {
        Set<Category> categories = getCategoriesStream()
                .collect(Collectors.toCollection(LinkedHashSet::new));

        if (isEnabledLogging()) {
            categories.forEach(IO::println);
        }

        return categories;
    }

    public Set<Category> getAllCategoriesByTree(Comparator<Category> comparator) {
        Set<Category> categories = getCategoriesStream()
                .collect(Collectors.toCollection(() ->
                        comparator != null ? new TreeSet<>(comparator) : new TreeSet<>()
                ));

        if (isEnabledLogging()) {
            categories.forEach(IO::println);
        }

        return categories;
    }

    public Map<Category, Long> getMapPeerCategoryQuantity() {
        Map<Category, Long> categoryMap = getProductsStream()
                .collect(Collectors.groupingBy(
                        ProductExhibition::category,
                        // remove below comment if you want to order by appearing
                        //LinkedHashMap::new,
                        // remove below comment if you want to order by enum order
                        //TreeMap::new,
                        Collectors.counting()
                ));

        if (isEnabledLogging()) {
            IO.println(categoryMap);
        }

        return categoryMap;
    }

    public Optional<ProductExhibition> getProductById(Long id) {
        Optional<ProductExhibition> productExibition = Optional.ofNullable(this.products.get(id));

        if (isEnabledLogging()) {
            productExibition.ifPresentOrElse(
                    p -> IO.println(p),
                    () -> IO.println("Product not found with id " + id)
            );
        }

        productExibition.ifPresent(this::registerGetActionInHistory);

        return productExibition;
    }

    public void addCollectionOfProducts(List<ProductExhibition> products) {
        HashMap<Long, ProductExhibition> productsMap = new HashMap<>();
        products.forEach(p -> productsMap.put(p.id(), p));
        this.products.putAll(productsMap);
    }

    public ProductExhibition getProductByIndex(int index) {
        ProductExhibition targetProduct = getProductsList().get(index);
        if (isEnabledLogging()) {
            IO.println(targetProduct.name());
        }
        return targetProduct;
    }

    public void removeProductById(Long id) {
        if (isEnabledLogging()) {
            IO.println("Current Size: " + getSizeOfProducts());
        }
        this.products.remove(id);
        if (isEnabledLogging()) {
            IO.println("Updated Size: " + getSizeOfProducts());
        }
    }

    public int getSizeOfProducts() {
        return this.products.size();
    }

    public void initDatabase() {
        getInitialData();
    }

    private void getInitialData() {
        List<ProductExhibition> productExibitions = new ArrayList<>();
        if (isEnabledMockingData()) {
            productExibitions = this.mock.mockProductsData();
        }
        addCollectionOfProducts(productExibitions);
    }

    private Stream<ProductExhibition> getProductsStream() {
        return getProductsList().stream();
    }

    private Stream<Category> getCategoriesStream() {
        return this.getProductsStream().map(ProductExhibition::category);
    }

    private List<ProductExhibition> getProductsList() {
        return new ArrayList<>(this.products.values());
    }
    
    private boolean isEnabledLogging() {
        return this.settings.enableLogs();
    }

    private boolean isEnabledMockingData() {
        return this.settings.mockData();
    }

    private void registerGetActionInHistory(ProductExhibition productExibition) {
        this.history.registerGetData(productExibition);
    }

}
