package com.example.encript_decriypt;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigInteger;
import java.util.Objects;
import java.util.Random;

import static android.content.Context.CLIPBOARD_SERVICE;

public class OneFragment extends Fragment{

    public OneFragment() {
        // Required empty public constructor
    }
    //variables
    TextView encriptedText1,encriptedText2,encriptedText3,encriptedText4;
    EditText pbkey1,pbkey2,message;

//clip
private ClipboardManager myClipboard;
    private ClipData myClip;
    public static String e1 = "",e2="",e3="",e4="";
    Button encript;
    public static BigInteger x =BigInteger.ZERO;
    public static BigInteger y =BigInteger.ZERO;
    //methods
    public static BigInteger power(BigInteger base, BigInteger e, BigInteger m)
    {
        BigInteger result = BigInteger.ONE;
        base = base.mod(m);

        for (int idx = 0; idx < e.bitLength(); ++idx) {
            if (e.testBit(idx)) {
                result = result.multiply(base).mod(m);
            }
            base = base.multiply(base).mod(m);
        }
        return result;
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
    public static String ASCIISentence(String str)
    {
        int l = str.length();
        int convert;
        String res = "1",temp="";
        for (int i = 0; i < l; i++) {
            convert = str.charAt(i);
            temp    = Integer.toString(convert);
            if(temp.length()==1)
            {
                temp = "00" + temp;
            }
            if(temp.length()==2)
            {
                temp = "0" + temp;
            }
            res = res + temp;
        }
        return res;
    }
    public static BigInteger nextRandomBigInteger(BigInteger n) {
        Random rand = new Random();
        BigInteger result = new BigInteger(n.bitLength(), rand);
        while( result.compareTo(n) >= 40) {
            result = new BigInteger(n.bitLength(), rand);
        }
        return result;
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
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        // Inflate the layout for this fragment
        encriptedText1 = view.findViewById(R.id.entext1);
        myClipboard = (ClipboardManager) Objects.requireNonNull(getActivity()).getSystemService(Context.CLIPBOARD_SERVICE);
        encriptedText2 = view.findViewById(R.id.entext2);
        encriptedText3 = view.findViewById(R.id.entext3);
        encriptedText4 = view.findViewById(R.id.entext4);
        pbkey1 = view.findViewById(R.id.pbf);
        pbkey2 = view.findViewById(R.id.pbs);
        encript = view.findViewById(R.id.encript);
        message = view.findViewById(R.id.message);
        encript.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String s1 = pbkey1.getText().toString().trim();
                String s2 = pbkey2.getText().toString().trim();
                String s3 = message.getText().toString();
                s3 = ASCIISentence(s3); //converting to its ascii values
                if(s1.equals("") || s2.equals("") || s3.equals("") )
                {
                    Toast.makeText(getActivity(),"Fill the above blanks to Encript", Toast.LENGTH_SHORT).show();
                }
                else {
                    BigInteger key1 = new BigInteger(s1);
                    BigInteger key2 = new BigInteger(s2);
                    BigInteger Mess = new BigInteger(s3);
                    encryption(key1, key2, Mess);

                    encriptedText1.setText(e1);
                    encriptedText2.setText(e2);
                    encriptedText3.setText(e3);
                    encriptedText4.setText(e4);
                }
            }
        });
        encriptedText1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                String text;
                text = encriptedText1.getText().toString();
                myClip = ClipData.newPlainText("text", text);
                myClipboard.setPrimaryClip(myClip);

                Toast.makeText(getActivity(), "Text Copied",
                        Toast.LENGTH_SHORT).show();

                return true;
            }
        });
        encriptedText3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                String text;
                text = encriptedText3.getText().toString();
                myClip = ClipData.newPlainText("text", text);
                myClipboard.setPrimaryClip(myClip);

                Toast.makeText(getActivity(), "Text Copied",
                        Toast.LENGTH_SHORT).show();

                return true;
            }
        });
        encriptedText2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                String text;
                text = encriptedText2.getText().toString();
                myClip = ClipData.newPlainText("text", text);
                myClipboard.setPrimaryClip(myClip);

                Toast.makeText(getActivity(), "Text Copied",
                        Toast.LENGTH_SHORT).show();

                return true;
            }
        });
        encriptedText4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                String text;
                text = encriptedText4.getText().toString();
                myClip = ClipData.newPlainText("text", text);
                myClipboard.setPrimaryClip(myClip);

                Toast.makeText(getActivity(), "Text Copied",
                        Toast.LENGTH_SHORT).show();

                return true;
            }
        });


        return view;
    }
    //main method
    public static void encryption(BigInteger Pbf,BigInteger Pbs,BigInteger Mess)
    {
        BigInteger Mod,C,X,Y,A,B,K,Gf,Gs,Pms,Pmf,Encrip1f,Encrip1s,Encrip2f,Encrip2s,Pirxf,Pirxs,P;
        P  = new BigInteger("1000000007");
        Mod = new BigInteger("8948962207650232551656602815159153422162609644098354511344597187200057010413552439917934304191956942765446530386427345937963894309923928536070534607816947");
        A = new BigInteger("6294860557973063227666421306476379324074715770622746227136910445450301914281276098027990968407983962691151853678563877834221834027439718238065725844264138");
        B = new BigInteger("3245789008328967059274849584342077916531909009637501918328323668736179176583263496463525128488282611559800773506973771797764811498834995234341530862286627");
        Gf = new BigInteger("6792059140424575174435640431269195087843153390102521881468023012732047482579853077545647446272866794936371522410774532686582484617946013928874296844351522");
        Gs = new BigInteger("6592244555240112873324748381429610341312712940326266331327445066687010545415256461097707483288650216992613090185042957716318301180159234788504307628509330");
        K = new BigInteger(String.valueOf(nextRandomBigInteger(P.subtract(BigInteger.ONE))));
        Pmf = BigInteger.ZERO;
        Pms = BigInteger.ZERO;

        for (int i = 0; i < 1000; ++i)
        {

            C  = (Mess.multiply(BigInteger.valueOf(1000))).add(BigInteger.valueOf(i));
            X = (((C.multiply(C)).mod(Mod)).multiply(C)).mod(Mod);
            Y =  (A.multiply(C)).mod(Mod);
            X = (X.add(Y.add(B))).mod(Mod);
            Y = (Mod.subtract(BigInteger.ONE)).divide(BigInteger.valueOf(2));
            if(power(X,Y,Mod).equals(BigInteger.ONE))
            {
                Y = (Mod.add(BigInteger.ONE)).divide(BigInteger.valueOf(4));
                Pmf = C;
                Pms = power(X,Y,Mod);
                break;
            }
        }
        Point_mult(Pbf,Pbs,K,A,Mod);//k times public key
        Pirxf= x;
        Pirxs = y;
       // Log.w("prirx",Pirxf.toString() + " "  + Pirxs.toString());
        Point_mult(Gf,Gs,K,A,Mod);//k times generator
        Encrip1f= x;
        Encrip1s = y;
        e1 = Encrip1f.toString();
        e2 = Encrip1s.toString();

        Point_addition(Pirxf,Pirxs,Pmf,Pms,A,Mod);// one of the points in elliptical curve
       // Log.w("pmf",Pmf.toString() + "  "  + Pms.toString());
        Encrip2f= x;
        Encrip2s = y;
     //   Log.w("e",Encrip2f.toString() + " "  + Encrip2s.toString());
        e3 = Encrip2f.toString();
        e4 = Encrip2s.toString();

    }

}
