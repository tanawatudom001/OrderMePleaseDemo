package kku.coe.dev.ordermepleasedemo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import info.hoang8f.widget.FButton;
import kku.coe.dev.ordermepleasedemo.Common.Common;
import kku.coe.dev.ordermepleasedemo.Model.User;



public class SignIn extends AppCompatActivity {

    EditText edtPhone,edtPassword;
    Button btnSignIn;

    FirebaseDatabase db;
    DatabaseReference users;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        edtPassword = (EditText)findViewById(R.id.edtPassword);
        edtPhone = (EditText)findViewById(R.id.edtPhone);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);

        db = FirebaseDatabase.getInstance();
        users = db.getReference("User");

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInUser(edtPhone.getText().toString(), edtPassword.getText().toString());

            }
        });
    }
    private void signInUser(String phone, String password) {
        final ProgressDialog mDialog = new ProgressDialog(SignIn.this);
        mDialog.setMessage("Please waiting ...");
        mDialog.show();

        final String  localPhone = phone;
        final String  localPassword = password;
        users.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(localPhone).exists()) {
                    mDialog.dismiss();
                    User user = dataSnapshot.child(localPhone).getValue(User.class);
                    user.setPhone(localPhone);
                    if (Boolean.parseBoolean(user.getIsStaff())) { // if IsStaff is true
                        if (user.getPassword().equals(localPassword)) {
                            Intent Iogin = new Intent(SignIn.this,Home.class);
                            Common.currentUser = user;
                            startActivity(Iogin);
                            finish();

                        }
                        // Login true
                        else
                            Toast.makeText(SignIn.this, "Wrong Password!! ", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(SignIn.this, "Please login with Staff Account", Toast.LENGTH_SHORT).show();
                }

                else {
                    mDialog.dismiss();
                    Toast.makeText(SignIn.this, "User not exist in Database", Toast.LENGTH_SHORT).show();

                }
                //overide oncancelled
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
    }

}

