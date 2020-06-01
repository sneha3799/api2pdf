package com.example.happinessdetector;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.item:
                openDocumentFromFileManager();
                return true;
//            case R.id.item1:
//                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.t1);

        if(!Python.isStarted())
            Python.start(new AndroidPlatform(this));

        Python py = Python.getInstance();
        PyObject pyf = py.getModule("conversion");
        PyObject obj = pyf.callAttr("convert");

        Log.i("response",obj.toString());

//        Python py = Python.getInstance();
//        PyObject pyf = py.getModule("conversion");
//        PyObject obj = pyf.callAttr("convert","");

//        textView.setText(obj.toString());
    }

    private void openDocumentFromFileManager() {
        Intent i =new Intent();
        i.setType("application/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        if(PermissionsHelper.getPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE,R.string.title_storage_permission,R.string.text_storage_permission,1111)){
            startActivityForResult(Intent.createChooser(i,"select document"),111);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try{
            if(resultCode == RESULT_OK){
                switch(requestCode) {
                    case 111:
                        FileInputStream inputStream = (FileInputStream) getContentResolver().openInputStream(data.getData());

//                        Python py = Python.getInstance();
//                        PyObject pyf = py.getModule("conversion");
//                        PyObject obj = pyf.callAttr("convert");
//
//                        Log.i("response",obj.toString());
//                        textView.setText(obj.toString());
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
