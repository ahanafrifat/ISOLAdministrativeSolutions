package com.appinionbd.isoladministrativesolutions.view.login;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.design.button.MaterialButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.EditText;
import android.widget.Toast;

import com.appinionbd.isoladministrativesolutions.R;
import com.appinionbd.isoladministrativesolutions.interfaces.presenterInterface.ILogin;
import com.appinionbd.isoladministrativesolutions.model.dataModel.UserInfo;
import com.appinionbd.isoladministrativesolutions.presenter.LoginPresenter;
import com.appinionbd.isoladministrativesolutions.view.home.HomeActivity;

import java.util.Objects;

import es.dmoral.toasty.Toasty;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class LoginActivity extends AppCompatActivity implements ILogin.View{

    private TextInputEditText textInputEditTextUsername;
    private TextInputEditText textInputEditTextPassword;
    private MaterialButton buttonLogin;

    ILogin.Presenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Realm.init(this);

        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);

        loginPresenter = new LoginPresenter(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        textInputEditTextUsername = findViewById(R.id.textInputEditText_username);
        textInputEditTextPassword = findViewById(R.id.textInputEditText_password);

        buttonLogin = findViewById(R.id.button_login);
        buttonLogin.setOnClickListener(v ->
                {
                    if(isNetworkConnected()) {
                        loginPresenter.loginVerification(textInputEditTextUsername.getText().toString(), textInputEditTextPassword.getText().toString());
//                gotoMainActivity()
                    }
                    else{
                        showAlertDialogNetworkError();
                    }
                }
        );

    }

    @Override
    public void loginSuccessful(UserInfo userInfo) {
        Toasty.success(this ,"Login Successful!" , Toast.LENGTH_LONG , true).show();
        gotoMainActivity();
    }

    @Override
    public void loginFailed() {
        Toasty.error(this ,"Login Failed" , Toast.LENGTH_LONG , true).show();
    }

    @Override
    public void wrongUsername() {
        Toasty.error(this ,"Wrong Username !" , Toast.LENGTH_LONG , true).show();
    }

    @Override
    public void wrongPassword() {
        Toasty.error(this ,"Wrong Password!" , Toast.LENGTH_LONG , true).show();
    }

    @Override
    public void connectionError() {
        Toasty.error(this ,"Network Error!" , Toast.LENGTH_LONG , true).show();
    }

    @Override
    public void authFailedShowInView() {
        Toasty.error(this ,"Auth Failed!" , Toast.LENGTH_LONG , true).show();
    }

    @Override
    public void wrongUserNameOrPasswordShowInView() {
        Toasty.error(this ,"Wrong Username Or Password" , Toast.LENGTH_LONG , true).show();
    }

    private void gotoMainActivity() {
        Intent intent = new Intent(this , HomeActivity.class);
        startActivity(intent);
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) Objects.requireNonNull(this).getSystemService(Context.CONNECTIVITY_SERVICE);
        assert cm != null;
        return cm.getActiveNetworkInfo() != null;
    }

    private void showAlertDialogNetworkError() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        Spannable required = new SpannableString("No Internet !");

        required.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary)),0,required.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        builder.setTitle(required);
        builder.setMessage("Please check your connection and try again !");
        builder.setPositiveButton("OK", (dialog, which) -> {

        });

        builder.create();
        builder.show();


    }
}
