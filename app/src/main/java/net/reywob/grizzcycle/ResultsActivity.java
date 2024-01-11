package net.reywob.grizzcycle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultsActivity extends AppCompatActivity {

    RelativeLayout menuHomeBtn, menuScanBtn, menuSearchBtn;
    TextView itemBarcodeLabel, itemTitle, itemTypeLabel, itemMaterialLabel, recycleStatusLabel, recycleStatusDescription;
    ImageView itemImageView, recycleStatusIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        // Assign the menu buttons to variables
        menuHomeBtn = findViewById(R.id.menu_home_button);
        menuScanBtn = findViewById(R.id.menu_scan_button);
        menuSearchBtn = findViewById(R.id.menu_search_button);

        // Assign the UI elements to variables
        itemBarcodeLabel = findViewById(R.id.itemBarcodeLabel);
        itemTitle = findViewById(R.id.itemTitle);
        itemTypeLabel = findViewById(R.id.itemTypeLabel);
        itemMaterialLabel = findViewById(R.id.itemMaterialLabel);
        recycleStatusLabel = findViewById(R.id.recycleStatusLabel);
        recycleStatusDescription = findViewById(R.id.recycleStatusDescription);
        itemImageView = findViewById(R.id.itemImageView);
        recycleStatusIcon = findViewById(R.id.recycleStatusIcon);

        // Retrieve variables from the Intent
        Intent intent = getIntent();
        String itemBarcode = intent.getStringExtra("itemBarcode");
        String itemName = intent.getStringExtra("itemName");
        String itemType = intent.getStringExtra("itemType");
        String itemMaterial = intent.getStringExtra("itemMaterial");
        int isRecyclable = intent.getIntExtra("isRecyclable", 0); // Default value if not found

        // Update the TextViews and ImageViews to displays the results given by the database
        itemTitle.setText(itemName);
        itemBarcodeLabel.setText("Barcode #: " + itemBarcode);
        itemTypeLabel.setText("Type: " + itemType);
        itemMaterialLabel.setText("Material: " + itemMaterial);
        itemImageView.setImageResource(R.drawable.ic_launcher_background); //Update this to show the actual picture of the item (DB entry needs to be "R.drawable.picture_name")
        // Update the recyclability status
        if (isRecyclable == 1) {
            recycleStatusLabel.setText("This item IS recyclable!");
            recycleStatusDescription.setText("Please deliver this item to the nearest recycling location.");
            recycleStatusIcon.setImageResource(R.drawable.recycle_icon);
        } else {
            recycleStatusLabel.setText("This item is NOT recyclable.");
            recycleStatusDescription.setText("Please deposit this item in the landfill garbage.");
            recycleStatusIcon.setImageResource(R.drawable.trash_icon);
        }

        menuHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go to the Home page
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
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
