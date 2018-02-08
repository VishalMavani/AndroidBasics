package com.easternts.thenewboston;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.amigold.fundapter.BindDictionary;
import com.amigold.fundapter.FunDapter;
import com.amigold.fundapter.extractors.StringExtractor;
import com.easternts.thenewboston.models.Product;

import java.util.ArrayList;

public class FunDapterDemo extends AppCompatActivity {
    ListView lvProducts;
    ArrayList<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fun_dapter);

        initializeComponents();
        products.add(new Product("Coke", 100, 0.5));
        products.add(new Product("Spirit", 150, 0.25));
        products.add(new Product("Cocktail", 50, 0.65));
        products.add(new Product("Fanta", 120, 0.75));
        products.add(new Product("Juice", 180, 1.5));
        products.add(new Product("Thumps Up", 190, 2.5));

        BindDictionary<Product> dictionary = new BindDictionary<>();

        dictionary.addStringField(R.id.tvName, new StringExtractor<Product>() {
            @Override
            public String getStringValue(Product item, int position) {
                return item.getName();
            }
        });

        dictionary.addStringField(R.id.tvQuantity, new StringExtractor<Product>() {
            @Override
            public String getStringValue(Product item, int position) {
                return "" + item.getQuantity();
            }
        });
        dictionary.addStringField(R.id.tvPrice, new StringExtractor<Product>() {
            @Override
            public String getStringValue(Product item, int position) {
                return "" + item.getPrice();
            }
        });

        FunDapter<Product> funDapter = new FunDapter<>(FunDapterDemo.this, products, R.layout.product_row, dictionary);

        lvProducts.setAdapter(funDapter);
        lvProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(FunDapterDemo.this, "Product: " + products.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initializeComponents() {
        lvProducts = (ListView) findViewById(R.id.lvProducts);
        products = new ArrayList<>();
    }
}
