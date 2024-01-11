package net.reywob.grizzcycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    RelativeLayout menuScanBtn, menuSearchBtn;
    Button openMapButton;
    ImageView mapImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Assign the menu buttons to variables
        menuScanBtn = findViewById(R.id.menu_scan_button);
        menuSearchBtn = findViewById(R.id.menu_search_button);
        // Find the "Open Map" button by its ID
        openMapButton = findViewById(R.id.open_map_button);
        // Find the map image by its ID
        mapImage = findViewById(R.id.map_image);

        // Initially hide the map image
        mapImage.setVisibility(View.INVISIBLE);

        openMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toggle the visibility of the map image
                if (mapImage.getVisibility() == View.VISIBLE) {
                    mapImage.setVisibility(View.INVISIBLE);
                    openMapButton.setText("OPEN MAP");
                } else {
                    mapImage.setVisibility(View.VISIBLE);
                    openMapButton.setText("CLOSE MAP");
                }
            }
        });

        menuScanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go to the Barcode Scanner page
                Intent intent = new Intent(getApplicationContext(), BarcodeScannerActivity.class);
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
}

