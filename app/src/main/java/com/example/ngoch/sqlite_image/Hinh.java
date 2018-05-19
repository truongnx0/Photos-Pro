package com.example.ngoch.sqlite_image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Hinh {
    private Bitmap bitmap;

    public Hinh(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
