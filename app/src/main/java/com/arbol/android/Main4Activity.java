package com.arbol.android;

import android.arbol.com.todoapp.R;
import android.arbol.com.todoapp.auth.BaseActivity;
import android.arbol.com.todoapp.auth.ChooserActivity;
import android.arbol.com.todoapp.auth.GoogleSignInActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class Main4Activity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        final Button button = (Button) findViewById(R.id.button_id);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                LoginManager.getInstance().logOut();
                startActivity(new Intent(Main4Activity.this,ChooserActivity.class));
            }
        });

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        final TextView helloTextView = (TextView) findViewById(R.id.userId);
        helloTextView.append(" "+currentUser.getDisplayName());
    }
}
