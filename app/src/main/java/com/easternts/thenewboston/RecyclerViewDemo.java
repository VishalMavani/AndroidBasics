package com.easternts.thenewboston;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.easternts.thenewboston.adapters.ProductAdapter;
import com.easternts.thenewboston.models.Product;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewDemo extends AppCompatActivity implements View.OnClickListener {
    RecyclerView rvProducts;
    FloatingActionButton fabAddProduct;
    List<Product> productList;
    ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view_demo);
        initializeComponents();
        createProductList();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rvProducts.setLayoutManager(layoutManager);
        rvProducts.setItemAnimator(new DefaultItemAnimator());
        rvProducts.setAdapter(productAdapter);
    }

    private void initializeComponents() {
        rvProducts = (RecyclerView) findViewById(R.id.rvProducts);
        fabAddProduct = (FloatingActionButton) findViewById(R.id.fabAddProduct);
        fabAddProduct.setOnClickListener(this);
        productList = new ArrayList<>();
        productAdapter = new ProductAdapter(productList);
    }

    private void createProductList() {
        productList.add(new Product("Coke", 100, 0.5));
        productList.add(new Product("Spirit", 150, 0.25));
        productList.add(new Product("Cocktail", 50, 0.65));
        productList.add(new Product("Fanta", 120, 0.75));
        productList.add(new Product("Juice", 180, 1.5));
        productList.add(new Product("Thumps Up", 190, 2.5));
        productList.add(new Product("Coke", 100, 0.5));
        productList.add(new Product("Spirit", 150, 0.25));
        productList.add(new Product("Cocktail", 50, 0.65));
        productList.add(new Product("Fanta", 120, 0.75));
        productList.add(new Product("Juice", 180, 1.5));
        productList.add(new Product("Thumps Up", 190, 2.5));
        productList.add(new Product("Coke", 100, 0.5));
        productList.add(new Product("Spirit", 150, 0.25));
        productList.add(new Product("Cocktail", 50, 0.65));
        productList.add(new Product("Fanta", 120, 0.75));
        productList.add(new Product("Juice", 180, 1.5));
        productList.add(new Product("Thumps Up", 190, 2.5));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fabAddProduct:
                productList.add(1, new Product("Fruti", 140, 0.3));
                productAdapter.notifyItemInserted(1);
                break;
        }
    }
}
