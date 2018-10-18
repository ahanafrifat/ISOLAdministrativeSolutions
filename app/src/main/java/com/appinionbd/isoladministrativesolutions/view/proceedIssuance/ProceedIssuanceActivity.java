package com.appinionbd.isoladministrativesolutions.view.proceedIssuance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.appinionbd.isoladministrativesolutions.R;
import com.appinionbd.isoladministrativesolutions.interfaces.presenterInterface.IProceedIssuance;
import com.appinionbd.isoladministrativesolutions.presenter.ProceedIssuancePresenter;

import io.realm.Realm;

public class ProceedIssuanceActivity extends AppCompatActivity implements IProceedIssuance.View {

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
    }
}
