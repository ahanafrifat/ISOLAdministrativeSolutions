package com.appinionbd.isoladministrativesolutions.interfaces.presenterInterface;

import android.content.Context;
import android.graphics.Bitmap;

import java.io.File;

public interface IProceedIssuance {

    interface View{


    }
    interface Presenter{

        void proceedUpload(Context context, String name, String comment, File file);

    }
}
