package com.example.encript_decriypt;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;


public class ThreeFragment extends Fragment{
    private ClipboardManager myClipboard;
    private ClipData myClip;
    TextView pkey1,pkey2;
    public ThreeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_three, container, false);
        pkey1 = view.findViewById(R.id.entext2);
        pkey2 = view.findViewById(R.id.entext3);
        myClipboard = (ClipboardManager) Objects.requireNonNull(getActivity()).getSystemService(Context.CLIPBOARD_SERVICE);
        String s = "3172757144062701929409134629315979206869911001430488395490176964122130879683432238054977903156826606257678923403428793032898375891279299754702002421575053";
        pkey1.setText(s);
        s = "2426019190088737134209834842935161075525937356491532727483425206328835040473337195176850662036247172908477981512538867103261301631944305112012058279559308";
        pkey2.setText(s);
        if (pkey1 != null) {
            pkey1.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    String text;
                    text = pkey1.getText().toString();
                    myClip = ClipData.newPlainText("text", text);
                    myClipboard.setPrimaryClip(myClip);
                    Toast.makeText(getActivity(), "Text Copied",
                            Toast.LENGTH_SHORT).show();

                    return true;
                }
            });
        }if (pkey2 != null) {
            pkey2.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    String text;
                    text = pkey2.getText().toString();
                    myClip = ClipData.newPlainText("text", text);
                    myClipboard.setPrimaryClip(myClip);
                    Toast.makeText(getActivity(), "Text Copied",
                            Toast.LENGTH_SHORT).show();

                    return true;
                }
            });
        }
        Button privacyPolicyButton = (Button) view.findViewById(R.id.privacyPolicyButton);
        privacyPolicyButton.setTextSize(18);
        privacyPolicyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(getActivity(),
                        PrivacyPolicyActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

}
