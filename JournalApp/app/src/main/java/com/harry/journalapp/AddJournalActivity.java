package com.harry.journalapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.harry.journalapp.model.Journal;

import java.util.Date;

import util.JournalUser;

public class AddJournalActivity extends AppCompatActivity {

    private static final int GALLERY_CODE = 1;
    //widgets
    private Button saveButton;
    private ProgressBar progressBar;
    private ImageView addPhotoButton;
    private EditText titleEditText;
    private EditText thoughtsEditText;
    private TextView currentUserTextView;
    private ImageView imageView;


    //User Id & Username
    private String currentUserId;
    private String currentUserName;

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseUser user;

    //Connection to  Firestore
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private StorageReference storageReference;

    private CollectionReference collectionReference = db.collection("Journal");
    private Uri imageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_journal);

        storageReference = FirebaseStorage.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.post_progressBar);
        titleEditText = findViewById(R.id.post_title_et);
        thoughtsEditText = findViewById(R.id.post_description_et);
        currentUserTextView = findViewById(R.id.post_username_textview);

        imageView = findViewById(R.id.post_imageView);
        saveButton = findViewById(R.id.post_save_journal_button);
        addPhotoButton = findViewById(R.id.postCameraButton);

        progressBar.setVisibility(View.INVISIBLE);

        if(JournalUser.getInstance() != null){
            currentUserId = JournalUser.getInstance().getUserId();
            currentUserName = JournalUser.getInstance().getUsername();

            currentUserTextView.setText(currentUserName);
        }

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                user = firebaseAuth.getCurrentUser();
                if(user != null){

                }else{

                }
            }
        };

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveJournal();
            }
        });

        addPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Getting image from gallary
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent,GALLERY_CODE);
            }
        });

    }

    private void SaveJournal() {
        final String title  = titleEditText.getText().toString().trim();
        final String thoughts = thoughtsEditText.getText().toString().trim();

        progressBar.setVisibility(View.VISIBLE);

        if(!TextUtils.isEmpty(title) && !TextUtils.isEmpty(thoughts) && imageUri != null){

            //the saving path of the images in Storage Firebase:
            //..../journal_images/our_image.png
            final StorageReference filepath = storageReference.child("journal_images").child("my_image_"+ Timestamp.now().getSeconds());


            //Uploading the images
            filepath.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            String imageUrl = uri.toString();

                            //Creating object of Journal
                            Journal journal =  new Journal();
                            journal.setTitle(title);
                            journal.setThoughts(thoughts);
                            journal.setImageUrl(imageUrl);
                            journal.setTimeAdded(new Timestamp(new Date()));
                            journal.setUserName(currentUserName);
                            journal.setUserId(currentUserId);


                            //Invoking Collection Reference
                            collectionReference.add(journal).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                        @Override
                                        public void onSuccess(DocumentReference documentReference) {
                                            progressBar.setVisibility(View.INVISIBLE);
                                            startActivity(new Intent(AddJournalActivity.this,
                                                    JournalListActivity.class));
                                            finish();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(getApplicationContext(), "Failed: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressBar.setVisibility(View.INVISIBLE);
                }
            });
        }else{
            progressBar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GALLERY_CODE && resultCode == RESULT_OK){
            if(data != null){
                imageUri = data.getData();  // Getting the date
                imageView.setImageURI(imageUri);  //showing the image
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        user = firebaseAuth.getCurrentUser();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(firebaseAuth != null){
            firebaseAuth.removeAuthStateListener(authStateListener);
        }
    }
}
