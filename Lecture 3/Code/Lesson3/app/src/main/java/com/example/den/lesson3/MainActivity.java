package com.example.den.lesson3;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity implements AdapterView.OnItemSelectedListener {

    /*TODO Homework
    1 - create a data class CarObject (like CheeseObject)
    2 - create a layout for car with imageView (like custom_item)
    3 - create a ViewHolder for your layout(2) of CarObject(1) (like ViewHolderCheese)
    4 - show CarObject (1) in your layout (2) using your VieHolder(3) in Grid View
    *5 - when press on car - show toast with name of the pressed car

    ***********************************************************
    ************* How to get car names and images *************
    ***********************************************************

    // CarObject - your data class (1), it may have initer of (String carName, Drawable carImage)

    Resources res = getResources();
    final String[] carsNameArray = res.getStringArray(R.array.car_types);
    final ArrayList<CarObject> carsList = new ArrayList<CarObject>();
    for (int i = 0; i < carsNameArray.length; i++) {
       String carName = carsNameArray[i];
       int resourceId =  this.getResources().getIdentifier(carName.toLowerCase(), "drawable", getPackageName());
       Drawable carImage = getResources().getDrawable(resourceId);
       CarObject newCar = new CarObject(carName, carImage);
       carsList.add(newCar);
    }

    */

    //  ****************************************************************
    //  ************************* LISTENER *****************************
    //  ****************************************************************

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        Resources res = getResources();
        String[] items = res.getStringArray(R.array.planets_array);

        Toast toast = Toast.makeText(this, items[pos], Toast.LENGTH_SHORT);
        toast.show();
    }

    public void onNothingSelected(AdapterView<?> parent) {}

    //  ****************************************************************
    //  ************************* LIST VIEW ****************************
    //  ****************************************************************

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.layout_streets);
//
//        // Get list view from layout
//        ListView listView = (ListView) findViewById(R.id.listView);
//
//        // Create data array
//        final String[] streets = getResources().getStringArray(R.array.streets_array);
//
//        // Create adapter
//        ArrayAdapter<String> adapter =
//                new ArrayAdapter<String>(this,
//                        R.layout.item, // layout  of items
//                        R.id.text, // id of textView in layout
//                        streets // string array of data
//                );
//
//        // Set adapter
//        listView.setAdapter(adapter);
//    }


    //  ****************************************************************
    //  ************************** SPINNER *****************************
    //  ****************************************************************

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        // Get spinner from layout
//        Spinner spinner = (Spinner) findViewById(R.id.planets_spinner);
//
//        // Create adapter from data array
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//                R.array.planets_array, android.R.layout.simple_spinner_item);
//
//        // Set adapter
//        spinner.setAdapter(adapter);
//
//        // Set dropdown appearance
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        // Set on item select listener
//        spinner.setOnItemSelectedListener(this);
//    }


    //  ****************************************************************
    //  ************************** GRID VIEW ***************************
    //  ****************************************************************
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        // Create data array
//        final String[] cheeses = {
//                "Parmesan",
//                "Ricotta",
//                "Fontina",
//                "Mozzarella",
//                "Cheddar"
//        };
//
//        // Create adapter
//        ArrayAdapter<String> cheeseAdapter =
//                new ArrayAdapter<String>(this,
//                        R.layout.item,
//                        R.id.text,
//                        cheeses
//                );
//
//        // Create grid view
//        GridView cheeseGrid = new GridView(this);
//        setContentView(cheeseGrid);
//
//        // Setup grid view appearance
//        cheeseGrid.setNumColumns(3);
//        cheeseGrid.setColumnWidth(60);
//        cheeseGrid.setVerticalSpacing(20);
//        cheeseGrid.setHorizontalSpacing(20);
//
//        // Set adapter
//        cheeseGrid.setAdapter(cheeseAdapter);
//
//        // Set on touch listener
//        cheeseGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView,
//                                    View view, int position, long rowId) {
//
//                // Generate a message based on the position
//                String message = "You clicked on " + cheeses[position];
//
//                Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
//                toast.show();
//            }
//        });
//
////        ALSO, YOU COULD TRANSFER IT TO LIST VIEW
////        ListView cheeseList = new ListView(this);
////        setContentView(cheeseList);
////        cheeseList.setAdapter(cheeseAdapter);
//    }

    //  ****************************************************************
    //  ****************** GRID VIEW WITH CUSTOM VIEW*******************
    //  ****************************************************************

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Resources res = getResources();
        final String[] carsNameArray = res.getStringArray(R.array.car_types);
        final ArrayList<CarObject> carsList = new ArrayList<CarObject>();
        for (int i = 0; i < carsNameArray.length; i++) {
            String carName = carsNameArray[i];
            int resourceId =  this.getResources().getIdentifier(carName.toLowerCase(), "drawable", getPackageName());
            Drawable carImage = getResources().getDrawable(resourceId);
            CarObject newCar = new CarObject(carName, carImage);
            carsList.add(newCar);
        }

        // Create adapter
        ArrayAdapter<CarObject> cheeseAdapterAdvanced =
                new ArrayAdapter<CarObject>(this, 0, carsList) {

                    // method to override in order to setup custom view
                    @Override
                    public View getView(int position,
                                        View convertView,
                                        ViewGroup parent) {

                        // Get data item to display
                        CarObject carObject = carsList.get(position);

                        // Check if view was reused, if not create a new
                        if (convertView == null) {
                            convertView = getLayoutInflater().inflate(R.layout.custom_item, null, false);
                        }

                        // If no saved view holder, create and setup
                        if(convertView.getTag() == null) {
                            ViewHolderCar viewHolder = new ViewHolderCar();

                            // Set reference to elements
                            viewHolder.imageViewCar = convertView.findViewById(R.id.imageViewCar);
                            viewHolder.textViewText = convertView.findViewById(R.id.textViewText);

                            // Assign to view
                            convertView.setTag(viewHolder);
                        }

                        // Get elements
                        ImageView imageViewCar = ((ViewHolderCar) convertView.getTag()).imageViewCar;
                        TextView textViewText = ((ViewHolderCar) convertView.getTag()).textViewText;

                        // Set elements data
                        imageViewCar.setImageDrawable(carObject.imageDrawable);
                        textViewText.setText(carObject.name);

                        // return view to show
                        return convertView;

                    }
                };

        // Create grid view
        GridView cheeseGrid = new GridView(this);
        setContentView(cheeseGrid);

        // Setup grid view appearance
        cheeseGrid.setNumColumns(2);
        cheeseGrid.setColumnWidth(60);
        cheeseGrid.setVerticalSpacing(20);
        cheeseGrid.setHorizontalSpacing(20);
        cheeseGrid.setAdapter(cheeseAdapterAdvanced);

        // Set on touch listener
        cheeseGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView,
                                    View view, int position, long rowId) {

                // Generate a message based on the position
                String message = "You clicked on " + carsNameArray[position];

                Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}
