package com.example.encript_decriypt;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigInteger;
import java.util.Objects;

public class TwoFragment extends Fragment{
    EditText encriptedText1,encriptedText2,encriptedText3,encriptedText4;
    Button decrypt;
    private ClipboardManager myClipboard;
    private ClipData myClip;
    TextView message;
    public TwoFragment() {
        // Required empty public constructor
    }
    public static BigInteger x =BigInteger.ZERO;
    public static BigInteger y =BigInteger.ZERO;
    //methods
    public static String normalSentence(String str)
    {
        int l = str.length();
        int convert;
        StringBuilder res = new StringBuilder();
        String temp="";
        for (int i = 1; i < l-2; i+=3)
        {
            temp = "" + str.charAt(i) + str.charAt(i+1) + str.charAt(i+2);

            int result = Integer.parseInt(temp);
            res.append(Character.toString((char) result));
        }
        return res.toString();
    }
    public static void Point_mult(BigInteger xf,BigInteger xs,BigInteger k,BigInteger a,BigInteger Mod)
    {
        BigInteger xm = BigInteger.ZERO;
        BigInteger ym = BigInteger.ZERO;
        BigInteger xt = BigInteger.ZERO;
        BigInteger yt = BigInteger.ZERO;

        while(!BigInteger.ZERO.equals(k))
        {

            if(BigInteger.ZERO.equals(k.mod(BigInteger.valueOf(2))))
            {
                k = k.divide(BigInteger.valueOf(2));
                Point_addition(xf,xs,xf,xs,a,Mod);
                xf  = x;
                xs  = y;
            }

            else
            {
                k= k.subtract(BigInteger.ONE);
                if(xm.equals(xt) && ym.equals(yt))
                {
                    xm  = (xf);
                    ym  = (xs);

                }
                else
                {
                    Point_addition(xm,ym,xf,xs,a,Mod);
                    xm  = (x);
                    ym  = (y);
                }
            }
        }
        x = xm;
        y = ym;
    }
    public static void Point_addition(BigInteger xf,BigInteger xs,BigInteger yf,BigInteger ys,BigInteger a,BigInteger Mod)
    {
        BigInteger Lamda,Inv;
        x = BigInteger.ZERO;
        y = BigInteger.ZERO;
        if(xf.equals(yf) && xs.equals(ys))
        {
            Lamda = ((((xf.multiply(xf)).mod(Mod)).multiply(BigInteger.valueOf(3))).add(a)).mod(Mod);
            Inv = (xs.multiply(BigInteger.valueOf(2))).modInverse(Mod);
            Lamda  =  (Lamda.multiply(Inv)).mod(Mod);
            x      =  (((Lamda.multiply(Lamda)).subtract(xf.multiply(BigInteger.valueOf(2)))).add(Mod)).mod(Mod);
            y      =  (((Lamda.multiply(xf.subtract(x))).subtract(xs)).add(Mod)).mod(Mod);
            return;
        }
        else
        {
            if(xf.equals(BigInteger.ZERO) && xs.equals(BigInteger.ZERO))
            {
                x = yf;
                y = ys;

                return;
            }

            if(yf.equals(BigInteger.ZERO) && ys.equals(BigInteger.ZERO))
            {
                x = xf;
                y = xs;
                return;
            }
            Lamda =  ys.subtract(xs);
            Inv = ( yf.subtract(xf)).modInverse(Mod);
            Lamda  =  (Lamda.multiply(Inv)).mod(Mod);
            x      =  (((Lamda.multiply(Lamda)).subtract(xf.add(yf))).add(Mod)).mod(Mod);
            y      =  (((Lamda.multiply(xf.subtract(x))).subtract(xs)).add(Mod)).mod(Mod);
        }
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_two, container, false);
        encriptedText1 = view.findViewById(R.id.entext1);
        myClipboard = (ClipboardManager) Objects.requireNonNull(getActivity()).getSystemService(Context.CLIPBOARD_SERVICE);
        encriptedText2 = view.findViewById(R.id.entext2);
        encriptedText3 = view.findViewById(R.id.entext3);
        encriptedText4 = view.findViewById(R.id.entext4);
        message = view.findViewById(R.id.decryptedtext);
        decrypt = view.findViewById(R.id.decrypt);
        decrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = encriptedText1.getText().toString().trim();
                String s2 = encriptedText2.getText().toString().trim();
                String s3 = encriptedText3.getText().toString().trim();
                String s4 = encriptedText4.getText().toString().trim();
                if(s1.equals("") || s2.equals("") || s3.equals("") || s4.equals(""))
                {
                    Toast.makeText(getActivity(),"Fill the above blanks to Encript", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    BigInteger key1 = new BigInteger(s1);
                    BigInteger key2 = new BigInteger(s2);
                    BigInteger key3 = new BigInteger(s3);
                    BigInteger key4 = new BigInteger(s4);

                    s1 = decryption(key1,key2,key3,key4);
                    s1 = normalSentence(s1);
                    message.setText(s1);
            }

            }
        });
        if(message!=null) {
            message.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    String text;
                    text = message.getText().toString();
                    myClip = ClipData.newPlainText("text", text);
                    myClipboard.setPrimaryClip(myClip);

                    Toast.makeText(getActivity(), "Text Copied",
                            Toast.LENGTH_SHORT).show();

                    return true;
                }
            });
        }

return view;
    }
    public static String decryption(BigInteger  Encrip1f, BigInteger  Encrip1s, BigInteger  Encrip2f,BigInteger  Encrip2s)
    {    BigInteger Mod,A,B,Decrip1f,Decript1s,Decript2f,Decript2s,P;
        Mod = new BigInteger("8948962207650232551656602815159153422162609644098354511344597187200057010413552439917934304191956942765446530386427345937963894309923928536070534607816947");
        A = new BigInteger("6294860557973063227666421306476379324074715770622746227136910445450301914281276098027990968407983962691151853678563877834221834027439718238065725844264138");
     //   B = new BigInteger("3245789008328967059274849584342077916531909009637501918328323668736179176583263496463525128488282611559800773506973771797764811498834995234341530862286627");
        P  = new BigInteger("1000000007");
        Point_mult(Encrip1f,Encrip1s,P,A,Mod);// sub to
        Decript2f= x;
        Decript2s = y.multiply(BigInteger.valueOf(-1));
        Point_addition(Encrip2f,Encrip2s,Decript2f,Decript2s,A,Mod);//
        Decrip1f= x;
       // Decript1s= y;
        BigInteger Ans = Decrip1f.divide(BigInteger.valueOf(1000));

        return Ans.toString();
    }

}
