package com.appinionbd.isoladministrativesolutions.view.camera;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;

import com.appinionbd.isoladministrativesolutions.R;
import com.google.android.gms.samples.vision.barcodereader.BarcodeCapture;
import com.google.android.gms.samples.vision.barcodereader.BarcodeGraphic;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.barcode.Barcode;

import java.util.List;

import xyz.belvi.mobilevisionbarcodescanner.BarcodeRetriever;


public class CameraActivity extends AppCompatActivity implements BarcodeRetriever {

//    implements BarcodeRetriever
    BarcodeCapture barcodeCapture;
    private static final String TAG = "BarcodeMain";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
    }

    @Override
    protected void onStart() {
        super.onStart();

        barcodeCapture = (BarcodeCapture) getSupportFragmentManager().findFragmentById(R.id.barcode);
        barcodeCapture.setRetrieval(this);

        barcodeCapture.setShowDrawRect(true)
                .setSupportMultipleScan(false)
                .setTouchAsCallback(true)
                .shouldAutoFocus(true)
                .setShowFlash(false)
                .setBarcodeFormat(Barcode.ALL_FORMATS)
                .setCameraFacing(CameraSource.CAMERA_FACING_BACK)
                .setShouldShowText(true);

        barcodeCapture.refresh();
    }

    @Override
    public void onRetrieved(Barcode barcode) {
        Log.d(TAG, "Barcode read: " + barcode.displayValue);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                AlertDialog.Builder builder = new AlertDialog.Builder(CameraActivity.this)
                        .setTitle("code retrieved")
                        .setMessage(barcode.displayValue);
                builder.show();
            }
        });
        barcodeCapture.stopScanning();
    }

    @Override
    public void onRetrievedMultiple(Barcode barcode, List<BarcodeGraphic> list) {

    }

    @Override
    public void onBitmapScanned(SparseArray<Barcode> sparseArray) {

    }

    @Override
    public void onRetrievedFailed(String s) {

    }

    @Override
    public void onPermissionRequestDenied() {

    }

//    @Override
//    public void onRetrieved(Barcode barcode) {
//        Log.d(TAG, "Barcode read: " + barcode.displayValue);
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//
//                AlertDialog.Builder builder = new AlertDialog.Builder(CameraActivity.this)
//                        .setTitle("code retrieved")
//                        .setMessage(barcode.displayValue);
//                builder.show();
//            }
//        });
//        barcodeCapture.stopScanning();
//    }
//
//    @Override
//    public void onRetrievedMultiple(Barcode barcode, List<BarcodeGraphic> list) {
//
////        runOnUiThread(new Runnable() {
////            @Override
////            public void run() {
////                String message = "Code selected : " + closetToClick.displayValue + "\n\nother " +
////                        "codes in frame include : \n";
////                for (int index = 0; index < barcodeGraphics.size(); index++) {
////                    Barcode barcode = barcodeGraphics.get(index).getBarcode();
////                    message += (index + 1) + ". " + barcode.displayValue + "\n";
////                }
////                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this)
////                        .setTitle("code retrieved")
////                        .setMessage(message);
////                builder.show();
////            }
////        });
//
//    }
//
//    @Override
//    public void onBitmapScanned(SparseArray<Barcode> sparseArray) {
//
//    }
//
//    @Override
//    public void onRetrievedFailed(String s) {
//
//    }
//
//    @Override
//    public void onPermissionRequestDenied() {
//
//    }
}
