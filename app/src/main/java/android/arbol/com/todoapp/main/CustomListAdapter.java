package android.arbol.com.todoapp.main;

/**
 * Created by Jorge on 28/08/2017.
 */

import android.app.Activity;
import android.arbol.com.todoapp.R;
import android.arbol.com.todoapp.main.entities.ObjectCare;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import static com.facebook.FacebookSdk.getApplicationContext;

public class CustomListAdapter extends BaseAdapter implements View.OnClickListener {

    /*********** Declare Used Variables *********/
    private Activity activity;
    private ArrayList<ObjectCare> data;
    private static LayoutInflater inflater = null;
    public Resources res;
    ObjectCare tempValues = null;
    int i = 0;

    /*************  CustomAdapter Constructor *****************/
    public CustomListAdapter(Activity a, ArrayList<ObjectCare> d, Resources resLocal) {

        /********** Take passed values **********/
        activity = a;
        data = d;
        res = resLocal;

        /***********  Layout inflator to call external xml layout () ***********/
        inflater = (LayoutInflater) activity.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    /******** What is the size of Passed Arraylist Size ************/
    public int getCount() {
        if (data.size() <= 0)
            return 1;
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    /********* Create a holder Class to contain inflated xml file elements *********/
    public static class ViewHolder {

        public ImageView imageView;
        public TextView name;
        //public TextView imageName;
        public TextView zone;

    }

    /****** Depends upon data size called for each row , Create each ListView row *****/
    public View getView(int position, View convertView, ViewGroup parent) {

        View vi = convertView;
        ViewHolder holder;

        if (convertView == null) {

            /****** Inflate tabitem.xml file for each row ( Defined below ) *******/
            vi = inflater.inflate(R.layout.item_list_content, null);

            /****** View Holder Object to contain tabitem.xml file elements ******/

            holder = new ViewHolder();
            holder.imageView = (ImageView) vi.findViewById(R.id.objectImageId);
            holder.name = (TextView) vi.findViewById(R.id.name);
            //holder.imageName = (TextView) vi.findViewById(R.id.imageName);
            holder.zone = (TextView) vi.findViewById(R.id.zone);

            /************  Set holder with LayoutInflater ************/
            vi.setTag(holder);
        } else
            holder = (ViewHolder) vi.getTag();

        if (data.size() <= 0) {
            holder.name.setText("No Data");

        } else {
            /***** Get each Model object from Arraylist ********/
            tempValues = null;
            tempValues = (ObjectCare) data.get(position);
            Log.v("CustomAdapter", "=====Row button clicked=====>>"+position);
            showImageFoto(vi, tempValues.getFotoId());
            holder.imageView = (ImageView) vi.findViewById(R.id.objectImageId);

            holder.name.setText(  tempValues.getName());
            //holder.imageName.setText(  tempValues.getFotoId());
            holder.zone.setText(tempValues.getAddress().getZone());

            /******** Set Item Click Listner for LayoutInflater for each row *******/

            vi.setOnClickListener(new OnItemClickListener(position));
        }
        /************  Set Model values in Holder elements ***********/

        return vi;
    }

    private void showImageFoto(final View vi, String image) {
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        StorageReference mStorageRef = firebaseStorage.getReference();
        mStorageRef.child(image).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                // Got the download URL for 'users/me/profile.png'
                ImageView imageViewObect = (ImageView) vi.findViewById(R.id.objectImageId);
                String downloadUri = uri.getPath();

                Picasso.with(getApplicationContext()).load(uri.toString()).into(imageViewObect);
                Log.v("link ", downloadUri.toString()); /// The string(file link) that you need
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
            }
        });
    }

    @Override
    public void onClick(View v) {
        Log.v("CustomAdapter", "=====Row button clicked=====");
    }

    /********* Called when Item click in ListView ************/
    private class OnItemClickListener implements View.OnClickListener {
        private double mLat;
        private double mLg;
        private String name;

        OnItemClickListener(int position) {
            mLat = tempValues.getAddress().getLatitude();
            mLg = tempValues.getAddress().getLongitude();
            name = tempValues.getName();
        }

        @Override
        public void onClick(View arg0) {


            MainPageActivity sct = (MainPageActivity) activity;

            /****  Call  onItemClick Method inside CustomListViewAndroidExample Class ( See Below )****/
            sct.onItemClick(mLat, mLg, name);

        }
    }
}