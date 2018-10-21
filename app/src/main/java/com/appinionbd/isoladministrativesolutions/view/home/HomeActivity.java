package com.appinionbd.isoladministrativesolutions.view.home;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.Toast;

import com.appinionbd.isoladministrativesolutions.R;
import com.appinionbd.isoladministrativesolutions.interfaces.RecyclerAdapterProductLibraryInterface.IRecyclerAdapterProductLibrary;
import com.appinionbd.isoladministrativesolutions.interfaces.presenterInterface.IHome;
import com.appinionbd.isoladministrativesolutions.model.dataModel.Product;
import com.appinionbd.isoladministrativesolutions.presenter.HomePresenter;
import com.appinionbd.isoladministrativesolutions.view.adapter.RecyclerAdapterProductLibrary;
import com.appinionbd.isoladministrativesolutions.view.camera.CameraActivity;
import com.appinionbd.isoladministrativesolutions.view.login.LoginActivity;
import com.appinionbd.isoladministrativesolutions.view.productHome.ProductHomeActivity;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;
import io.realm.Realm;
import jp.co.recruit_lifestyle.android.widget.WaveSwipeRefreshLayout;

public class HomeActivity extends AppCompatActivity implements IHome.View {

    private ImageView imageViewScan;
    private ImageView imageViewLogout;

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

        imageViewScan = findViewById(R.id.imageView_scan);
        imageViewLogout = findViewById(R.id.imageView_logout);

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

        imageViewScan.setOnClickListener(v -> gotoCamera());
        imageViewLogout.setOnClickListener(v -> {
            showAlertDialogForLogout();
        });
    }

    @Override
    public void showData(List<Product> productList) {

        try {
            if (productList != null) {

                this.productList = productList;

                swipeRefreshLayoutProduct.setRefreshing(false);

                layoutManager = new LinearLayoutManager(this);
                recyclerViewProduct.setLayoutManager(layoutManager);
                recyclerViewProduct.setHasFixedSize(true);

                recyclerAdapterProductLibrary = new RecyclerAdapterProductLibrary(this.productList, this, new IRecyclerAdapterProductLibrary() {
                    @Override
                    public void productClicked(String itemCode) {
                        gotoProductHome(itemCode);
                    }
                });
                recyclerAdapterProductLibrary.notifyDataSetChanged();
                recyclerViewProduct.setAdapter(recyclerAdapterProductLibrary);

                List<String> stringList = new ArrayList<>();

                for (int i = 0; i < productList.size(); i++) {
                    stringList.add(productList.get(i).getItemName());
                    stringList.add(productList.get(i).getItemCode());
                    stringList.add(productList.get(i).getItemId());
                }


                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stringList);

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
            } else {
                Toasty.info(this, "There is no item", Toast.LENGTH_LONG, true).show();
            }
        }
        catch (Exception e){
            Toasty.info(this, "There is no item", Toast.LENGTH_LONG, true).show();
        }
    }

    private void filter(String text) {

        try {
            List<Product> filterProductList = new ArrayList<>();

            for (Product product : productList) {
                if (product.getItemName().toLowerCase().contains(text.toLowerCase()) ||
                        product.getItemCode().toLowerCase().contains(text.toLowerCase()) ||
                        product.getItemId().toLowerCase().contains(text.toLowerCase())) {
                    filterProductList.add(product);
                }
            }

            recyclerAdapterProductLibrary.filterList(filterProductList);
        }
        catch (Exception e){
            Toasty.error(this , "No item!" , Toast.LENGTH_LONG , true).show();
        }
    }

    private void gotoProductHome(String itemCode) {
        try {
            Intent intent = new Intent(this , ProductHomeActivity.class);
            intent.putExtra("itemCode", itemCode);
            startActivity(intent);
        }
        catch (Exception e){
            Toasty.error(this , "Error in gotoProductHome!" , Toast.LENGTH_LONG , true).show();
        }
    }

    private void gotoCamera(){
        try{
            Intent intent = new Intent(this , CameraActivity.class);
            startActivity(intent);
        }
        catch (Exception e){
            Toasty.error(this , "Error in gotoCamera!" , Toast.LENGTH_LONG , true).show();
        }
    }

    private void gotoLogout(){
        try{
            Intent intent = new Intent(this , LoginActivity.class);
            startActivity(intent);
        }
        catch (Exception e){
            Toasty.error(this , "Error in gotoCamera!" , Toast.LENGTH_LONG , true).show();
        }
    }

    private void showAlertDialogForLogout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        Spannable required = new SpannableString("Log out !");

        required.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary)),0,required.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        builder.setTitle(required);
        builder.setMessage("Are you sure ?");
        builder.setPositiveButton("OK", (dialog, which) -> {
            iHomePresenter.proceedToLogout();
        });
        builder.setNegativeButton("No" , (dialog, which) -> {

        });

        builder.create();
        builder.show();


    }

    @Override
    public void emptyData(String message) {
        Toasty.info(this , "No item!" , Toast.LENGTH_LONG , true).show();
    }

    @Override
    public void networkError(String message) {
        Toasty.info(this , "Network error!" , Toast.LENGTH_LONG , true).show();
    }

    @Override
    public void otherError(String message) {
        Toasty.info(this , "Other error!" , Toast.LENGTH_LONG , true).show();
    }

    @Override
    public void logoutSuccessful() {
        Toasty.info(this , "Logout Done!" , Toast.LENGTH_LONG , true).show();
        gotoLogout();
    }
}
