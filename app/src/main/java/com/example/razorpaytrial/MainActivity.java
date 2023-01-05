package com.example.razorpaytrial;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

        binding.paybtn.setOnClickListener(v -> {

            // edittext se amount uthao k kitna payment send krna he
            String amount = binding.amountet.getText().toString();
            // uska round figure kr lo jese 3.14159 ka 3.00
            int amt = Math.round(Float.parseFloat(amount) * 100);

            // initialize razorpay checkout here
            Checkout checkout = new Checkout();

            // set key id
            // this key should be our's
            checkout.setKeyID("rzp_test_7jVbXwD6YudRUL");

            // set image
            checkout.setImage(com.razorpay.R.drawable.rzp_logo);

            // initialize json Ojbect
            JSONObject object=new JSONObject();

            try {
                // put name
                object.put("name", "Android Developer Yogesh");

                // put description
                object.put("description", "Test Payment");

                // put theme color
                object.put("theme.color","#0093DD");

                // put currency unit
                object.put("currency","INR");

                // put amount
                object.put("amount",amt);

                // put mobile number
                // sender's number
                //object.put("prefill.contact","7000563591");

                // put email
                // sender's email
                //object.put("prefill.email","ricky.khanna@gmail.com");

                // open razorpay checkout

                checkout.open(MainActivity.this,object);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void onPaymentSuccess(String s) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);

        builder.setTitle("Payment Id");
        builder.setMessage(s);
        builder.show();
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}