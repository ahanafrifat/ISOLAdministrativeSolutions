package com.appinionbd.isoladministrativesolutions.view.home;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.appinionbd.isoladministrativesolutions.R;
import com.appinionbd.isoladministrativesolutions.interfaces.RecyclerAdapterProductLibraryInterface.IRecyclerAdapterProductLibrary;
import com.appinionbd.isoladministrativesolutions.interfaces.presenterInterface.IHome;
import com.appinionbd.isoladministrativesolutions.model.dataModel.Product;
import com.appinionbd.isoladministrativesolutions.presenter.HomePresenter;
import com.appinionbd.isoladministrativesolutions.view.adapter.RecyclerAdapterProductLibrary;
import com.appinionbd.isoladministrativesolutions.view.productHome.ProductHomeActivity;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;
import io.realm.Realm;
import jp.co.recruit_lifestyle.android.widget.WaveSwipeRefreshLayout;

public class HomeActivity extends AppCompatActivity implements IHome.View {

    private AutoCompleteTextView autoCompleteTextViewSearch;
    private SwipeRefreshLayout swipeRefreshLayoutProduct;

    private RecyclerView recyclerViewProduct;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerAdapterProductLibrary recyclerAdapterProductLibrary;

    private List<Product> productList;

    IHome.Presenter iHomePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Realm.init(this);

        iHomePresenter= new HomePresenter(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        swipeRefreshLayoutProduct = findViewById(R.id.waveSwipeRefreshLayout_product);
        recyclerViewProduct = findViewById(R.id.recyclerView_product);

        autoCompleteTextViewSearch = findViewById(R.id.autoCompleteTextView_search);

        productList = new ArrayList<>();

//        layoutManager = new LinearLayoutManager(this);
//        recyclerViewProduct.setLayoutManager(layoutManager);
//        recyclerViewProduct.setHasFixedSize(true);

        swipeRefreshLayoutProduct.setOnRefreshListener(() -> {
            swipeRefreshLayoutProduct.setRefreshing(true);
            iHomePresenter.getRefreshData();
        });
        iHomePresenter.getDataWithoutWaiting();
    }

    @Override
    public void showData(List<Product> productList) {

        if(productList != null){

            this.productList = productList;

            swipeRefreshLayoutProduct.setRefreshing(false);

            layoutManager = new LinearLayoutManager(this);
            recyclerViewProduct.setLayoutManager(layoutManager);
            recyclerViewProduct.setHasFixedSize(true);

            recyclerAdapterProductLibrary = new RecyclerAdapterProductLibrary(this.productList, this, new IRecyclerAdapterProductLibrary() {
                @Override
                public void productClicked(String itemId) {
                    gotoProductHome(itemId);
                }
            });
            recyclerAdapterProductLibrary.notifyDataSetChanged();
            recyclerViewProduct.setAdapter(recyclerAdapterProductLibrary);

            List<String> stringList = new ArrayList<>();

            for(int i = 0 ; i < productList.size() ; i++){
                stringList.add(productList.get(i).getItemName());
                stringList.add(productList.get(i).getItemCode());
                stringList.add(productList.get(i).getItemId());
            }


            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this , android.R.layout.simple_list_item_1 , stringList);

            autoCompleteTextViewSearch.setAdapter(arrayAdapter);

            autoCompleteTextViewSearch.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    filter(s.toString());
                }
            });
        }
        else
        {
            Toasty.info(this , "There is no item" , Toast.LENGTH_LONG , true).show();
        }
    }

    private void filter(String text) {
        List<Product> filterProductList = new ArrayList<>();

        for(Product product : productList){
            if(product.getItemName().toLowerCase().contains(text.toLowerCase()) ||
                    product.getItemCode().toLowerCase().contains(text.toLowerCase()) ||
                    product.getItemId().toLowerCase().contains(text.toLowerCase()) ){
                filterProductList.add(product);
            }
        }

        recyclerAdapterProductLibrary.filterList(filterProductList);
    }

    private void gotoProductHome(String itemId) {
        Intent intent = new Intent(this , ProductHomeActivity.class);
        intent.putExtra("itemId", itemId);
        startActivity(intent);
    }

    @Override
    public void emptyData(String message) {

    }

    @Override
    public void networkError(String message) {

    }

    @Override
    public void otherError(String message) {

    }
}
