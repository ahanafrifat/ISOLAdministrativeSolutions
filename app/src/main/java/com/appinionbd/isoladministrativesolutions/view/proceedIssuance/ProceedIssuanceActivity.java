package com.appinionbd.isoladministrativesolutions.view.proceedIssuance;

import android.support.design.button.MaterialButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.appinionbd.isoladministrativesolutions.R;
import com.appinionbd.isoladministrativesolutions.interfaces.presenterInterface.IProceedIssuance;
import com.appinionbd.isoladministrativesolutions.presenter.ProceedIssuancePresenter;

import io.realm.Realm;

public class ProceedIssuanceActivity extends AppCompatActivity implements IProceedIssuance.View {

    private EditText editTextVendorName;
    private EditText editTextVendorRemarks;
    private LinearLayout linearLayoutAddInvoice;
    private MaterialButton materialButtonProceedIssuance;


    private IProceedIssuance.Presenter proceedIssuancePresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proceed_issuance);

        Realm.init(this);

        proceedIssuancePresenter = new ProceedIssuancePresenter();
    }

    @Override
    protected void onStart() {
        super.onStart();

        editTextVendorName = findViewById(R.id.editText_vendor_name);
        editTextVendorRemarks = findViewById(R.id.editText_vendor_remarks);
        linearLayoutAddInvoice = findViewById(R.id.linearLayout_add_invoice);
        materialButtonProceedIssuance = findViewById(R.id.materialButton_proceed_issuance);
    }
}
