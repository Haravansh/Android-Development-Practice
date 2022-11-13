package com.harry.journalapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import util.JournalUser;

public class MainActivity extends AppCompatActivity {

    // Widgets
    Button loginBTN;
    Button createAccBTN;
    private EditText emailET;
    private EditText passET;

    //Firebase Authentication
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseUser currentUser;


    // Firebase Connection
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private CollectionReference collectionReference = db.collection("Users");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginBTN = findViewById(R.id.email_sign_in_button);
        createAccBTN = findViewById(R.id.create_acct_BTN);
        emailET = findViewById(R.id.email);
        passET = findViewById(R.id.password);


        //initialize the auth ref
        firebaseAuth = FirebaseAuth.getInstance();






        createAccBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(i);
            }
        });

        loginBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginEmailPasswordUser(
                        emailET.getText().toString().trim(),
                        passET.getText().toString().trim()
                );
            }
        });
    }

    private void LoginEmailPasswordUser(String email, String pwd) {
        //checking for empty texts
        if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pwd)){
            firebaseAuth.signInWithEmailAndPassword(email, pwd)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            assert user != null;
                            final String currentUserId = user.getUid();
                            collectionReference.whereEqualTo("userId", currentUserId)
                                    .addSnapshotListener(new EventListener<QuerySnapshot>() {
                                        @Override
                                        public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                                            if(error != null){

                                            }
                                            assert value != null;
                                            if(!value.isEmpty()){
                                                //Getting all querydoc snapshots
                                                for(QueryDocumentSnapshot snapshot : value){
                                                    JournalUser journalUser = JournalUser.getInstance();
                                                    journalUser.setUsername(snapshot.getString("username"));
                                                    journalUser.setUserId(snapshot.getString("userId"));

                                                    //Go to List Activity after successful login
                                                    //Lets display the list of journals after login
                                                    //startActivity(new Intent(MainActivity.this, AddJournalActivity.class));
                                                    startActivity(new Intent(MainActivity.this,JournalListActivity.class));

                                                }
                                            }
                                        }
                                    });
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            //If failed
                            Toast.makeText(MainActivity.this, "Something went wrong "+e, Toast.LENGTH_SHORT).show();
                        }
                    });
        }else{
            Toast.makeText(MainActivity.this, "Please Enter email & password", Toast.LENGTH_SHORT).show();
        }


    }
}