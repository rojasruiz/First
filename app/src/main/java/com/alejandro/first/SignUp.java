package com.alejandro.first;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    EditText fecha;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView fotoRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        fotoRes = (ImageView) findViewById(R.id.user_avatar);

        fecha = (EditText) findViewById(R.id.et_birthdate);
        fecha.setOnClickListener(this::showDatePicker);
    }

    public void showDatePicker(View view) {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because January is zero
                final String selectedDate = day + " / " + (month + 1) + " / " + year;
                fecha.setText(selectedDate);
            }
        });

        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    private void dispatchTakePictureIntent() {
        Toast toast = Toast.makeText(this, "going APPBAR CAMERA 1", Toast.LENGTH_LONG);
        toast.show();
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            fotoRes.setImageBitmap(imageBitmap);
        }
    }

    public void takePhoto(View view) {
        dispatchTakePictureIntent();
    }

    public void registerAction(View view) {
        Toast.makeText(this, "Registration succesful", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(SignUp.this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}