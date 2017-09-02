package android.arbol.com.todoapp.main;

import android.arbol.com.todoapp.R;
import android.arbol.com.todoapp.auth.BaseActivity;
import android.arbol.com.todoapp.auth.ChooserActivity;
import android.arbol.com.todoapp.main.entities.GroupCare;
import android.arbol.com.todoapp.main.entities.MediaCare;
import android.arbol.com.todoapp.main.entities.ObjectCare;
import android.arbol.com.todoapp.main.entities.UserCare;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import android.arbol.com.todoapp.main.entities.AddressObjectCare;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class AddObjectActivity extends BaseActivity {

    private RecyclerView mRecyclerView;
    private StorageReference mStorageRef;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activiry_add_object);

        final Button buttonId = (Button) findViewById(R.id.button_id);
        buttonId.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                LoginManager.getInstance().logOut();
                startActivity(new Intent(AddObjectActivity.this, ChooserActivity.class));
            }
        });

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        final TextView userId = (TextView) findViewById(R.id.userId);
        userId.append(" " + currentUser.getDisplayName());

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("appArbol_ObjectCare");

        final ListView listView = (ListView) findViewById(R.id.listViewArbol);
        // Create a new Adapter
        final ArrayAdapter<Map<String,String>> adapter = new ArrayAdapter<Map<String,String>>(this,
                android.R.layout.simple_list_item_1);
        // Assign adapter to ListView
        listView.setAdapter(adapter);
        // Connect to the Firebase database

        // Get a reference to the todoItems child items it the database

        // Assign a listener to detect changes to the child items
        // of the database reference.
        myRef.addChildEventListener(new ChildEventListener() {

            // This function is called once for each child that exists
            // when the listener is added. Then it is called
            // each time a new child is added.
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                Map<String, String> map = (Map<String, String>) dataSnapshot.getValue();
                adapter.add(map);
            }

            // This function is called each time a child item is removed.
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                HashMap<String,String> value = dataSnapshot.getValue(HashMap.class);
                adapter.remove(value);
            }

            // The following functions are also required in ChildEventListener implementations.
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
            }

            public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("TAG:", "Failed to read value.", error.toException());
            }
        });


        // Add items via the Button and EditText at the bottom of the window.
        final EditText text = (EditText) findViewById(R.id.todoText);
        final Button button = (Button) findViewById(R.id.addButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference myRef = database.getReference("appArbol_ObjectCare");
                String uid = myRef.child("objects").push().getKey();

                Map<String, ObjectCare> objectCareMap = createObjectCare(uid);
                myRef.child("objects").setValue(objectCareMap);

                Map<String, GroupCare> groupCareMap = createGroup(uid);
                myRef.child("groups").setValue(groupCareMap);

                Map<String, MediaCare> imageCareMap = createImageGroup(uid);
                myRef.child("images").setValue(imageCareMap);

                Map<String, UserCare> userCareMap = createUserCare(uid);
                myRef.child("user").setValue(userCareMap);
                finish();
            }
        });

        // Delete items when clicked
  /*      listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Query myQuery = myRef.orderByValue().equalTo((String)
                        listView.getItemAtPosition(position));

                myQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChildren()) {
                            DataSnapshot firstChild = dataSnapshot.getChildren().iterator().next();
                            firstChild.getRef().removeValue();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                })
                ;}
        })
        ;}
        */
/*        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        mStorageRef=firebaseStorage.getReference();
     mStorageRef.child("image1.jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    // Got the download URL for 'users/me/profile.png'
                    try{
                        imageView = (ImageView) findViewById(R.id.imageView1);
                        URL url = new URL(uri.getPath());
                        Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                        imageView.setImageBitmap(bmp);
                    }catch (Exception e){
                        Log.e("Error ",e.getMessage());
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle any errors
                }
            });
    }*/
    }

    private  Map<String, ObjectCare> createObjectCare(String uid) {
        ObjectCare objectCare = new ObjectCare();
        Map<String, ObjectCare> objectCareMap = new HashMap<String, ObjectCare>();
        Map<String,Boolean> groups = new HashMap<String, Boolean>();
        Map<String,Boolean> images = new HashMap<String, Boolean>();

        AddressObjectCare address = new AddressObjectCare();
        address.setLatitude(25.686613);
        address.setLongitude(-100.316116);

        objectCare.setUid(uid);
        objectCare.setName("Arbol 1");
        images.put("imagesObject"+objectCare.getUid(),true);
        objectCare.setImages(images);
        groups.put("groupCuidadores"+objectCare.getUid(),true);
        groups.put("groupPagadores"+objectCare.getUid(),true);
        objectCare.setGroups(groups);
        objectCare.setAddress(address);

        objectCareMap.put(objectCare.getUid(),objectCare);

        objectCareMap.put(uid,objectCare);
        return objectCareMap;
    }

    private  Map<String, GroupCare> createGroup(String uid) {
        Map<String,Boolean> object = new HashMap<String, Boolean>();
        Map<String,Map<String,String>> pagos = new HashMap<String,Map<String,String>>();
        Map<String,Map<String,String>> cobros = new HashMap<String,Map<String,String>> ();
        object.put(uid,true);
        GroupCare groupCare = new GroupCare();
        groupCare.setNameGroup("groupCuidadores"+uid);
        groupCare.setObjects(object);
        Map<String,String> datosPago = new HashMap<String,String>();
        Map<String,String> datosPago2 = new HashMap<String,String>();
        datosPago.put("monto","$100");
        datosPago.put("date","02/09/2017");
        datosPago.put("type","paypal");
        pagos.put("0",datosPago);
        datosPago2.put("monto","$200");
        datosPago2.put("date","01/09/2017");
        datosPago2.put("type","tdc");
        pagos.put("1",datosPago2);
        groupCare.setPagos(pagos);
        groupCare.setCobros(cobros);

        Map<String, GroupCare> groupCareMap = new HashMap<String, GroupCare>();
        groupCareMap.put("groupCuidadores"+uid,groupCare);
        return groupCareMap;
    }

    private  Map<String, MediaCare> createImageGroup(String uid) {
        Map<String,Boolean> object = new HashMap<String, Boolean>();
        object.put(uid,true);
        MediaCare mediaCare = new MediaCare();
        mediaCare.setMediaName("imagesObject"+uid);
        Map<String,String> images = new HashMap<String, String>();
        images.put(uid,"image1.gif");
        images.put("uid2","image2.gif");
        mediaCare.setImages(images);
        mediaCare.setObjects(object);

        Map<String, MediaCare> imageCareMap = new HashMap<String, MediaCare>();
        imageCareMap.put("imagesObject"+uid, mediaCare);
        return imageCareMap;
    }

    private  Map<String, UserCare> createUserCare(String uid) {
        Map<String,Boolean> groups = new HashMap<String, Boolean>();
        groups.put("groupCuidadores"+uid,true);
        UserCare userCare = new UserCare();
        final EditText text = (EditText) findViewById(R.id.todoText);
        userCare.setUserName(text.getText().toString());
        userCare.setGroups(groups);

        Map<String, UserCare> userCareMap = new HashMap<String, UserCare>();
        userCareMap.put("user"+uid, userCare);
        return userCareMap;
    }
}

