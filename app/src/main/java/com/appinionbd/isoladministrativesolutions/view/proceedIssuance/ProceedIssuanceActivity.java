package com.appinionbd.isoladministrativesolutions.view.proceedIssuance;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.button.MaterialButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.appinionbd.isoladministrativesolutions.R;
import com.appinionbd.isoladministrativesolutions.interfaces.presenterInterface.IProceedIssuance;
import com.appinionbd.isoladministrativesolutions.presenter.ProceedIssuancePresenter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import io.realm.Realm;

public class ProceedIssuanceActivity extends AppCompatActivity implements IProceedIssuance.View {

    private EditText editTextVendorName;
    private EditText editTextVendorRemarks;
    private LinearLayout linearLayoutAddInvoice;
    private ImageView imageViewInvoice;
    private MaterialButton materialButtonProceedIssuance;

    private final int RESULT_LOAD_IMG = 9001;
    private IProceedIssuance.Presenter proceedIssuancePresenter;
    private boolean checkImage;

    private Bitmap selectedFinalImage;
    private File file;

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
        imageViewInvoice = findViewById(R.id.imageView_invoice);
        materialButtonProceedIssuance = findViewById(R.id.materialButton_proceed_issuance);

        checkImage = false;

        linearLayoutAddInvoice.setOnClickListener(v -> {
            getImageFromGallery();
        });


        materialButtonProceedIssuance.setOnClickListener(v -> checkAllTheField());
    }

    private void checkAllTheField() {
        if (file != null && file.exists())
            proceedIssuancePresenter.proceedUpload(getApplicationContext(), editTextVendorName.getText().toString(),
                    editTextVendorRemarks.getText().toString(),
                    file
            );
        else{
            Toast.makeText(this,"file is null", Toast.LENGTH_LONG).show();
        }
//        if( !editTextVendorName.getText().toString().isEmpty() ||
//                !editTextVendorRemarks.getText().toString().isEmpty() ||
//                !checkImage){
//            editTextVendorName.setError("Empty Field");
//            editTextVendorRemarks.setError("Empty Field");
//        }
//        else{
//            proceedIssuancePresenter.proceedUpload(getApplicationContext(), editTextVendorName.getText().toString() ,
//                    editTextVendorRemarks.getText().toString(),
//                    selectedFinalImage
//                    );
//        }
    }

    private void getImageFromGallery() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);
    }

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);


        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                imageViewInvoice.setImageBitmap(selectedImage);
                selectedFinalImage = selectedImage;
                checkImage = true;

                String[] filePathColumn = { MediaStore.Images.Media.DATA };
                Cursor cursor = getContentResolver().query(imageUri, filePathColumn, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String filePath = cursor.getString(columnIndex);
                cursor.close();
                file = new File(filePath);
                Log.d("====", file.getAbsolutePath());

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();

            }

        } else {
            Toast.makeText(this, "You haven't picked Image", Toast.LENGTH_LONG).show();
        }
    }
}
