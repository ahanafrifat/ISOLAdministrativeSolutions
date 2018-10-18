package com.appinionbd.isoladministrativesolutions.presenter;

import com.appinionbd.isoladministrativesolutions.interfaces.presenterInterface.IProceedIssuance;

public class ProceedIssuancePresenter implements IProceedIssuance.Presenter {
    private IProceedIssuance.View view;

    public ProceedIssuancePresenter() {
    }

    public ProceedIssuancePresenter(IProceedIssuance.View view) {
        this.view = view;
    }
}
