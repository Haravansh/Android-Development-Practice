package com.harry.firestoreapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Button SaveBTN;
    private EditText nameET;
    private EditText emailET;
    private TextView textView;
    private Button ShowBTN;
    private Button UpdateBTN;
    private Button DeleteBTN;

    // Firebase Firestore:
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference freindRef = db.collection("Users").
            document("Friends");

    private CollectionReference collectionReference = db.collection("Users");


    // KEYs
    public static final String KEY_NAME = "name";
    public static final String KEY_EMAIL = "email";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameET = findViewById(R.id.nameET);
        emailET = findViewById(R.id.emailET);
        SaveBTN = findViewById(R.id.SaveBTN);

        textView = findViewById(R.id.text);

        SaveBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //SaveDataToFireStore();

                // Save Data to new document
                SaveDataToNewDocument();
            }
        });

        ShowBTN = findViewById(R.id.readBTN);
        ShowBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ReadData();

                // Get ALl Documents in collection
                GetAllDocumentsInCollection();
            }
        });

        DeleteBTN = findViewById(R.id.deleteBTN);
        DeleteBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeleteData();

            }
        });


    }

    private void GetAllDocumentsInCollection() {

        collectionReference.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        String data = "";
                        // Looping through all the documents in collection
                        for (QueryDocumentSnapshot snapshots : queryDocumentSnapshots) {
                            // Log.v("TAGY",snapshots.getString(KEY_NAME));
                            // Log.v("TAGY", snapshots.getString(KEY_EMAIL));

                            // Transforming snapshots into objects
                            // Each document is now an object of type friend
                            Friend friend = snapshots.toObject(Friend.class);

                            // Adding object data to string
                            data += "Name: " + friend.getName() +
                                    " Email: " + friend.getEmail() + "\n";
                        }

                        // Setting the textView to the retrieved data
                        textView.setText(data);


                    }
                });


    }

    private void SaveDataToNewDocument() {

        String name = nameET.getText().toString();
        String email = emailET.getText().toString();

        Friend friend = new Friend(name, email);

        collectionReference.add(friend);


    }

    private void DeleteData() {

        // Delete value according to name
        freindRef.update(KEY_NAME, FieldValue.delete());

        // Delete value according to email
        freindRef.update(KEY_EMAIL, FieldValue.delete());

    }

    private void DeleteAll() {
        // Deleting all data from doc
        freindRef.delete();
    }


    private void ReadData() {
        freindRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        // Let's display retrieved data into textview
                        if (documentSnapshot.exists()) {
                            String fname = documentSnapshot.getString(KEY_NAME);
                            String femail = documentSnapshot.getString(KEY_EMAIL);

                            textView.setText("Username: " + fname + "\nUser Email: " + femail);

                        }
                    }
                });

        UpdateBTN = findViewById(R.id.updateBTN);
        UpdateBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateData();
            }
        });


    }

    private void UpdateData() {
        String name = nameET.getText().toString().trim();
        String email = emailET.getText().toString().trim();

        Map<String, Object> data = new HashMap<>();
        data.put(KEY_NAME, name);
        data.put(KEY_EMAIL, email);


        freindRef.update(data)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(MainActivity.this,
                                "Update Success !", Toast.LENGTH_SHORT).show();
                    }
                });


    }

    private void SaveDataToFireStore() {
        String name = nameET.getText().toString().trim();
        String email = emailET.getText().toString().trim();

        Friend f1 = new Friend();
        f1.setName(name);
        f1.setEmail(email);

        // Saving Custom object (Java Object: Friend.class)


//        // Saving data as key-value pairs (MAP data structure)
//
//        Map<String, Object> data = new HashMap<>();
//        data.put(KEY_NAME,name);
//        data.put(KEY_EMAIL,email);

        // Saving in Collections:
        db.collection("Users")
                .document("Friends")
                .set(f1)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getApplicationContext(), "Successfully Created!", Toast.LENGTH_SHORT).show();
                    }
                })
                // You can add other listeners
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Failed!!!", Toast.LENGTH_SHORT).show();
                    }
                });


    }

    @Override
    protected void onStart() {
        super.onStart();

        GetAllDocumentsInCollection();

        // Amzing !!!!  Let's decrease font size
        // Listening all the time during the app lifecycle

//        freindRef.addSnapshotListener(this,
//                new EventListener<DocumentSnapshot>() {
//                    @Override
//                    public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
//
//                        if (error != null){
//                            Toast.makeText(MainActivity.this,
//                                    "Error Found!", Toast.LENGTH_SHORT).show();
//                        }
//                        if (value != null && value.exists()){
//
//                            // Getting data ( custom Object )
//                            Friend  friend = value.toObject(Friend.class);
//
////                            String fname = value.getString(KEY_NAME);
////                            String femail = value.getString(KEY_EMAIL);
//
//                            textView.setText("Username: " + friend.getName()+
//                                    "\nUser Email: "+friend.getEmail());
//
//                        }
//                    }
//                });
    }


// Building CRUD APP:

// 1- Saving Simple Data on Firestore (Creating Data)       [DONE]
// 2- Reading Simple Data from Firestore (Retrieving Data)  [DONE]
// 2.1- Listening to Snapshot changes                       [DONE]
// 3- Updating simple Data                                  [DONE]
// 4- Deleting Data (K-V pairs) from Firestore              [DONE]
// 5- Saving Custom Data ( Java POJO) into Firestore        [DONE]
// 6- Creating multiple documents                           [DONE]
// 7- Retrieving Multiple Documents into LOG                [DONE]
// 8- Retrieving Multiple Documents into Objects (Friends)  [DONE]
}