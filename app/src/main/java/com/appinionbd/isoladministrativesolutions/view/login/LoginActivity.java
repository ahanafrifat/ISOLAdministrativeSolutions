package com.appinionbd.isoladministrativesolutions.view.login;

import android.content.Intent;
import android.support.design.button.MaterialButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.appinionbd.isoladministrativesolutions.R;
import com.appinionbd.isoladministrativesolutions.interfaces.presenterInterface.ILogin;
import com.appinionbd.isoladministrativesolutions.model.dataModel.UserInfo;
import com.appinionbd.isoladministrativesolutions.presenter.LoginPresenter;
import com.appinionbd.isoladministrativesolutions.view.home.HomeActivity;

import es.dmoral.toasty.Toasty;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class LoginActivity extends AppCompatActivity implements ILogin.View{

    private EditText editTextUsername;
    private EditText editTextPassword;
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

        editTextUsername = findViewById(R.id.editText_username);
        editTextPassword = findViewById(R.id.editText_password);

        buttonLogin = findViewById(R.id.button_login);
        buttonLogin.setOnClickListener(v ->
                        loginPresenter.loginVerification(editTextUsername.getText().toString() , editTextPassword.getText().toString())
//                gotoMainActivity()
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
        Toasty.error(this ,"Connection Error!" , Toast.LENGTH_LONG , true).show();
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
}
