package com.gkftndltek.makepicture;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.Display;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.common.FirebaseVisionPoint;
import com.google.firebase.ml.vision.face.FirebaseVisionFace;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceContour;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetector;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetectorOptions;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceLandmark;

import java.util.List;

public class FaceActivity extends Activity {

    Context nContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_face);
        nContext = this;

        final RelativeLayout relative = findViewById(R.id.relative);

        FirebaseVisionFaceDetectorOptions options =
                new FirebaseVisionFaceDetectorOptions.Builder()
                        .setPerformanceMode(FirebaseVisionFaceDetectorOptions.ACCURATE)
                        .setLandmarkMode(FirebaseVisionFaceDetectorOptions.ALL_LANDMARKS)
                        .setClassificationMode(FirebaseVisionFaceDetectorOptions.ALL_CLASSIFICATIONS)
                        .build();

        final Bitmap bitmap = BitmapFactory.decodeResource(nContext.getResources(),R.drawable.faceimage);
        FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(bitmap);
        FirebaseVisionFaceDetector detector = FirebaseVision.getInstance()
                .getVisionFaceDetector(options);

        Task<List<FirebaseVisionFace>> result =
                detector.detectInImage(image)
                        .addOnSuccessListener(
                                new OnSuccessListener<List<FirebaseVisionFace>>() {
                                    @Override
                                    public void onSuccess(List<FirebaseVisionFace> faces) {
                                        // Task completed successfully
                                        // ...

                                        Point p = new Point();
                                        Display display = getWindowManager().getDefaultDisplay();
                                        display.getSize(p);

                                        // p.x p.y
                                        for (FirebaseVisionFace face : faces) {
                                            FirebaseVisionFaceLandmark leftEye = face.getLandmark(FirebaseVisionFaceLandmark.LEFT_EYE);
                                            float le_x1 = leftEye.getPosition().getX();
                                            float le_y1 = leftEye.getPosition().getY();

                                            FirebaseVisionFaceLandmark leftcheek = face.getLandmark(FirebaseVisionFaceLandmark.LEFT_CHEEK);
                                            float le_x2 = leftcheek.getPosition().getX();
                                            float le_y2 = leftcheek.getPosition().getY();

                                            FirebaseVisionFaceLandmark rightcheek = face.getLandmark(FirebaseVisionFaceLandmark.RIGHT_CHEEK);
                                            float le_x3  = rightcheek.getPosition().getX();
                                            float le_y3  = rightcheek.getPosition().getY();

                                            ImageView imageLE1 = new ImageView(nContext);
                                            imageLE1.setImageResource(R.drawable.iconfinder_braille);
                                            imageLE1.setX(p.x * le_x1 / bitmap.getWidth() - 50);
                                            imageLE1.setY(p.y * le_y1 / bitmap.getHeight() - 50);

                                            imageLE1.setLayoutParams(new RelativeLayout.LayoutParams(100,100));
                                            relative.addView(imageLE1);

                                            ImageView imageLE2 = new ImageView(nContext);
                                            imageLE2.setImageResource(R.drawable.iconfinder_contacts);
                                            imageLE2.setX(p.x * le_x2 / bitmap.getWidth() - 50);
                                            imageLE2.setY(p.y * le_y2 / bitmap.getHeight() - 50);
                                            imageLE2.setLayoutParams(new RelativeLayout.LayoutParams(100,100));
                                            relative.addView(imageLE2);

                                            ImageView imageLE3 = new ImageView(nContext);
                                            imageLE3.setImageResource(R.drawable.iconfinder_prsen_box);
                                            imageLE3.setX(p.x * le_x3 / bitmap.getWidth() - 50);
                                            imageLE3.setY(p.y * le_y3 / bitmap.getHeight()  - 50);
                                            imageLE3.setLayoutParams(new RelativeLayout.LayoutParams(100,100));
                                            relative.addView(imageLE3);
                                        }

                                    }
                                })
                        .addOnFailureListener(
                                new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        // Task failed with an exception
                                        // ...
                                    }
                                });

    }
}
