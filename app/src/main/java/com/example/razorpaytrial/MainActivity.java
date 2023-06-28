package com.example.razorpaytrial;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.razorpaytrial.databinding.ActivityMainBinding;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements PaymentResultListener {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Checkout.preload(MainActivity.this);

        binding.paybtn.setOnClickListener(v -> {

            // edittext se amount uthao k kitna payment send krna he
//            String amount = binding.amountet.getText().toString();
            // uska round figure kr lo jese 3.14159 ka 3.00
//            int amt = Math.round(Float.parseFloat(amount) * 100);


//            // initialize razorpay checkout here
//            Checkout checkout = new Checkout();
//
//            // set key id
//            // this key should be our's
//            checkout.setKeyID("rzp_test_8k2hlzOvyMiFXA");
//
//            // set image
//            checkout.setImage(com.razorpay.R.drawable.rzp_logo);
//
//            // initialize json Ojbect
//            JSONObject object = new JSONObject();
//
//            try {
//                // put name
//                object.put("name", "Android Developer Yogesh");
//
//                // put description
//                object.put("description", "Test Payment");
//
//                // put theme color
//                object.put("theme.color", "#0093DD");
//
//                // put currency unit
//                object.put("currency", "INR");
//
//                // put amount
//                object.put("amount", amt);
//
//                // put mobile number
////                 sender's number
//                object.put("prefill.contact","8225055150@paytm");
//
//                // put email
//                // sender's email
//                //object.put("prefill.email","ricky.khanna@gmail.com");
//
//                // open razorpay checkout
//
//                checkout.open(MainActivity.this, object);
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }

            String samount = binding.amountet.getText().toString();

            // rounding off the amount.
            int amount = Math.round(Float.parseFloat(samount) * 100);

            // initialize Razorpay account.
            Checkout checkout = new Checkout();

            // set your id as below
            checkout.setKeyID("rzp_test_8k2hlzOvyMiFXA");

            // set image
            checkout.setImage(com.razorpay.R.drawable.rzp_logo);

            // initialize json object
            JSONObject object = new JSONObject();
            try {
                // to put name
                object.put("name", "Geeks for Geeks");

                // put description
                object.put("description", "Test payment");

                // to set theme color
                object.put("theme.color", "");

                // put the currency
                object.put("currency", "INR");

                // put amount
                object.put("amount", amount);
                

                // open razorpay to checkout activity
                checkout.open(MainActivity.this, object);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
    }

    private void startPayment(String receiverUPI, int amount) {
        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_8k2hlzOvyMiFXA");

        try {
            JSONObject options = new JSONObject();
            options.put("name", "Your App");
            options.put("description", "Purchase Description");
            options.put("currency", "INR");
            options.put("amount", amount * 100); // Amount in paise

            // Set UPI details
            JSONObject prefill = new JSONObject();
            prefill.put("vpa", receiverUPI);
            options.put("prefill", prefill);

            checkout.open(this, options);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPaymentSuccess(String s) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Payment Id");
        builder.setMessage(s);
        builder.show();
    }

    @Override
    public void onPaymentError(int i, String s) {
        Log.d("dfsdfsdfsdfgsdfg", s);
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}