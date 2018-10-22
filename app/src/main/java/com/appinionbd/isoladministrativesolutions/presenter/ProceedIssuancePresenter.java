package com.appinionbd.isoladministrativesolutions.presenter;

import android.content.Context;
import android.graphics.Bitmap;

import com.appinionbd.isoladministrativesolutions.interfaces.presenterInterface.IProceedIssuance;
import com.appinionbd.isoladministrativesolutions.model.dataHolder.SavedProduct;
import com.appinionbd.isoladministrativesolutions.model.dataHolder.UserToken;
import com.appinionbd.isoladministrativesolutions.model.dataModel.FloorList;
import com.appinionbd.isoladministrativesolutions.model.dataModel.Issuance;
import com.appinionbd.isoladministrativesolutions.model.dataModel.ItemList;
import com.appinionbd.isoladministrativesolutions.model.dataModel.UserInfo;
import com.appinionbd.isoladministrativesolutions.networking.uploadInvoice.UploadInvoice;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okio.ByteString;

public class ProceedIssuancePresenter implements IProceedIssuance.Presenter {
    private IProceedIssuance.View view;

    public ProceedIssuancePresenter() {
    }

    public ProceedIssuancePresenter(IProceedIssuance.View view) {
        this.view = view;
    }

    @Override
    public void proceedUpload(Context context, String name, String comment, File file) {

        Issuance issuance = new Issuance();
        List<ItemList> itemLists = new ArrayList<>();
        List<FloorList> floorLists = new ArrayList<>();

        String token= "";
        try(Realm realmInstance = Realm.getDefaultInstance()) {
            UserToken userToken = realmInstance.where(UserToken.class).findFirst();
            token = userToken.getToken();

//            UserInfo userInfo = realmInstance.where(UserInfo.class).findFirst();
//
//            issuance.setVendorName(userInfo.getUserName());
//            issuance.setUserId(userInfo.getUserId());
//            issuance.setRemark(comment);
//
//            RealmResults<SavedProduct> savedProductRealmResults = realmInstance.where(SavedProduct.class).findAll();
//
//            String tempItemCode = "";
//
//            for(SavedProduct savedProduct : savedProductRealmResults){
//
//                RealmResults<SavedProduct> savedProductRealmResultsTemp = realmInstance
//                        .where(SavedProduct.class)
//                        .equalTo("itemCode" , savedProduct.getItemCode())
//                        .findAll();
//
//                if(tempItemCode == "") {
//
//                    tempItemCode = savedProduct.getItemCode();
//                    ItemList itemList = new ItemList();
//                    itemList.setItemCode(savedProduct.getItemCode());
//
//                    FloorList floorListTemp = new FloorList();
//                }
//
//
//            }
        }

        RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("upload", file.getName(), reqFile);
        RequestBody uploadName = RequestBody.create(MediaType.parse("text/plain"), "name");

        UploadInvoice.getUploadInvoice().uploadImage(token, body, uploadName, issuance);
    }
}
