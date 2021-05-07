package com.example.opencvlearning;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.opencvlearning.demo.HelloOpenCVSampleActivity;
import com.example.opencvlearning.demo.image_processing.BasicDrawingActivity;
import com.permissionx.guolindev.PermissionCollection;
import com.permissionx.guolindev.PermissionX;
import com.permissionx.guolindev.callback.ExplainReasonCallback;
import com.permissionx.guolindev.callback.RequestCallback;
import com.permissionx.guolindev.request.ExplainScope;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private AppCompatButton helloButton;
    private AppCompatButton basicDrawingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        helloButton = findViewById(R.id.bt_hello);
        helloButton.setOnClickListener(this);
        basicDrawingButton = findViewById(R.id.bt_basic_drawing);
        basicDrawingButton.setOnClickListener(this);

        requestPermissions();
    }

    private void requestPermissions() {
        PermissionCollection permissionCollection = PermissionX.init(this);
        permissionCollection.permissions(Manifest.permission.CAMERA)
                .onExplainRequestReason((scope, deniedList) ->
                        scope.showRequestReasonDialog(deniedList, "Core fundamental are based on these permissions", "Ok", "Cancel")).request(new RequestCallback() {
            @Override
            public void onResult(boolean allGranted, List<String> grantedList, List<String> deniedList) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_hello:
                startActivity(new Intent(this, HelloOpenCVSampleActivity.class));
                break;
            case R.id.bt_basic_drawing:
                startActivity(new Intent(this, BasicDrawingActivity.class));
                break;
        }
    }
}