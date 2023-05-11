package edu.curtin.productviewer.model;

import java.util.*;

/**
 * Represents a product group. Product groups are compared on the basis of 
 * their names.
 */
public class ProductGroup implements Comparable<ProductGroup>, CatalogueEntry
{
    private String name;
    private Set<CatalogueEntry> products;
    private ProductGroup parent;
    
    public ProductGroup(String name)
    {
        this.name = name;
        this.products = new TreeSet<>();
        this.parent = null;
    }

    public ProductGroup(String name, ProductGroup parent)
    {
        this.name = name;
        this.products = new TreeSet<>();
        this.parent = parent;
    }
    
    public String getName()
    {
        return name;
    }
    
    public Set<Product> getProducts()
    {
        return Collections.unmodifiableSet(products);
    }

    @Override
    public ProductGroup getParent()
    {
        return parent;
    }
    
    public boolean hasProduct(Product p)
    {
        return products.contains(p);
    }
    
    public void addProduct(Product p)
    {
        products.add(p);
    }

    @Override
    public float getPrice()
    {
        float total;

        for (CatalogueEntry product : products) 
        {
            total += product.getPrice();
        }

        return total;
    }
    
    @Override
    public boolean equals(Object obj)
    {
        boolean eq = false;
        if(obj instanceof ProductGroup)
        {
            eq = name.equals(((ProductGroup)obj).name);
        }
        return eq;
    }
    
    @Override
    public int hashCode()
    {
        return name.hashCode();
    }
    
    @Override
    public int compareTo(ProductGroup group)
    {
        return name.compareTo(group.name);
    }
}
