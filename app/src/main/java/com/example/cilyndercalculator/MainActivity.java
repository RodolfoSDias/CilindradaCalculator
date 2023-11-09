package com.example.cilyndercalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView; // Importe a classe TextView
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerUnits;
    private EditText editTextPistonDiameter;
    private EditText editTextCylinders;
    private EditText editTextCrankshaftStroke;
    private TextView textViewResult; // Adicione a variável para o TextView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerUnits = findViewById(R.id.spinnerUnits);
        editTextPistonDiameter = findViewById(R.id.editTextPistonDiameter);
        editTextCylinders = findViewById(R.id.editTextCylinders);
        editTextCrankshaftStroke = findViewById(R.id.editTextCrankshaftStroke);
        textViewResult = findViewById(R.id.textViewResult); // Associe o TextView pelo ID

        ArrayAdapter<CharSequence> unitsAdapter = ArrayAdapter.createFromResource(this, R.array.units_array, android.R.layout.simple_spinner_item);
        unitsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUnits.setAdapter(unitsAdapter);

        findViewById(R.id.buttonCalculate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateCylinders();
            }
        });
    }

    private void calculateCylinders() {
        String cylindersText = editTextCylinders.getText().toString();
        String selectedUnit = spinnerUnits.getSelectedItem().toString();
        double cylinders = Double.parseDouble(cylindersText);

        String pistonDiameterText = editTextPistonDiameter.getText().toString();
        double pistonDiameter = Double.parseDouble(pistonDiameterText);

        String crankshaftStrokeText = editTextCrankshaftStroke.getText().toString();
        double crankshaftStroke = Double.parseDouble(crankshaftStrokeText);

        double displacement;

        if (selectedUnit.equals("mm")) {
            // Calcula a cilindrada em centímetros cúbicos (cm³).
            displacement = (Math.PI * Math.pow((pistonDiameter / 2), 2) * cylinders * crankshaftStroke) / 1000;
            displayResult(displacement, "cm³");
        } else if (selectedUnit.equals("polegadas")) {
            // Calcula a cilindrada em polegadas cúbicas (in³).
            displacement = Math.PI * Math.pow((pistonDiameter / 2), 2) * cylinders * crankshaftStroke;
            displayResult(displacement, "in³");
        }
    }

    private void displayResult(double result, String unit) {
        // Formate o resultado com duas casas decimais
        String formattedResult = String.format("%.2f", result);
        String resultMessage = "A cilindrada do motor é " + formattedResult + " " + unit;
        textViewResult.setText(resultMessage);
    }

}

