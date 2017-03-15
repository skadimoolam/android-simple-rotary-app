package dev.adi.poc.rotarydemo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import dev.adi.poc.rotarydemo.R;
import dev.adi.poc.rotarydemo.adapter.MemberListAdapter;
import dev.adi.poc.rotarydemo.model.MemberModel;

public class MembersActivity extends AppCompatActivity {

    RecyclerView rvListMembers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_members);

        getSupportActionBar().setTitle("Members");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rvListMembers = (RecyclerView) findViewById(R.id.rv_list_memebers);
        rvListMembers.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<MemberModel> listMembers = new ArrayList<>();
        listMembers.add(new MemberModel("Adi", "8978897889", "", "11 AUG", "", "AB-"));
        listMembers.add(new MemberModel("Prabhu", "9008897889", "prabhu@gmail.com", "15 SEPT", "9 NOV", "B-"));
        listMembers.add(new MemberModel("Gova", "7898897889", "", "4 NOV", "", "A-"));
        listMembers.add(new MemberModel("Rajesh", "8999897889", "", "14 MAY", "", "AB+"));
        listMembers.add(new MemberModel("Surya", "8654397889", "", "3 NOV", "23 MAR", "O-"));

        rvListMembers.setAdapter(new MemberListAdapter(this, listMembers));

//        findViewById(R.id.mem_btn_admin).setOnClickListener(this);
//        findViewById(R.id.mem_btn_president).setOnClickListener(this);
//        findViewById(R.id.mem_btn_secratary).setOnClickListener(this);
//        findViewById(R.id.mem_btn_vice).setOnClickListener(this);
    }

//    @Override
//    public void onClick(View view) {
//        String toastText = null;
//        switch (view.getId()) {
//            case R.id.mem_btn_admin:
//                toastText = "Administrator";
//                break;
//
//            case R.id.mem_btn_president:
//                toastText = "President";
//                break;
//
//            case R.id.mem_btn_secratary:
//                toastText = "Secratary";
//                break;
//
//            case R.id.mem_btn_vice:
//                toastText = "Vice-President";
//                break;
//        }
//
//        if (toastText != null) {
//            Toast.makeText(this, toastText, Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(this, "Nothing selected", Toast.LENGTH_SHORT).show();
//        }
//    }
}
