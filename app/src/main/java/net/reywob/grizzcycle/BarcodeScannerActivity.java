package net.reywob.grizzcycle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class BarcodeScannerActivity extends AppCompatActivity {

    RelativeLayout menuHomeBtn, menuSearchBtn;
    private IntentIntegrator integrator = new IntentIntegrator(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startBarcodeScanner();
        // Set the layout to the scanner activity
        setContentView(R.layout.activity_scanner);
        // Assign the menu buttons to variables
        menuHomeBtn = findViewById(R.id.menu_home_button);
        menuSearchBtn = findViewById(R.id.menu_search_button);

        menuHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go to the Home page
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        menuSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go to the Barcode Scanner page
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);
            }
        });
    }

    private void startBarcodeScanner() {
        integrator.setCameraId(0); // Use the rear camera by default
        integrator.setBeepEnabled(true); // Enable beep sound on successful scan
        integrator.setOrientationLocked(true);
        integrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        // if the intentResult is null then
        // toast a message as "cancelled"
        if (intentResult != null) {
            if (intentResult.getContents() == null) {
                Toast.makeText(getBaseContext(), "Cancelled", Toast.LENGTH_SHORT).show();
            } else {
                // if the intentResult is not null we'll set
                // the content and format of scan message
                String upc = intentResult.getContents();

                if (!upc.isEmpty()) {
                    //              Toast.makeText(this, upc, Toast.LENGTH_SHORT).show();
                    String apiUrl = "https://csi4999.reywob.net/api/data/" + upc;
                    new HttpAsyncTask(this).execute(apiUrl);
                } else {
                    Toast.makeText(BarcodeScannerActivity.this, "Empty search query!", Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}