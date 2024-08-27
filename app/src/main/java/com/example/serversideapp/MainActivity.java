package com.example.serversideapp;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
/*
hala2 3mlna application ems aserversideapp heda l application b2labo activity feha  3 button w testview
2awl button bindservies button ,unbindservies button ,getrandom number button


hala2 la7 nefta7 l mainactivity ma7al ma 7a na3mel kel logic
-2awl she 3ande Intent global esma serviceIntent le 7a esta5dmo la 2a3mel bind rabt l servies
-tene she 3ande variable inteer esmo randomNumberValues heda le 7a ysayef l random number le 7ayeje mn servies
-telet sha8le mne7tej bshakl asese immplement ServiceConnection object heda service connection object la7 nest3mlo la na3mel bind to servies 2awl ma na3mel serviceconnection obejct 3ande 2 methods wa7de onserviceconnected ,onservicedisconnected
-rabe3 she lezm na3mel 2 messanger object global wa7de esma randomnumberrequstMessanger ,wa7de esma randomnumberrecivemessanger  :randomnumberrequstMessanger  hayde l messanger la7 nest5dmo la na3mel requst talab la nersel talab lal servies  w randomnumberrecivemessanger la7 ne7tejo l ta3moul ma3 l message le mab3ote elna mn l services
fa le hek  b method l onserviesconnected la7 nshfo eno la7 2om bt7he2et hawde l tnen messanger l mesanger le 5asa bel requst la7 2a3teha parameter l heye l binder 3ashn wasel l binder bel messanger  heda l binder mawjoud bel parameter heda howe nafso l binder le 7a yeraj3 mn l onbind method mn l servies le 3mlneha b tene application le b2lbo servies w b2lab l disconnected method l messanger l tnen b7otoun null w la7 est5dem boolean value esmo mIsBound bas yen3ml call la disconnected bade 7oto false w bas ya3mel connected bade 2a3ml true la shof eno iza l bind succefully 2aw la2
-fa hala2 bel oncreate ba3d ma 5slnsa l conection bade 2a3ml immplement lal intent ba3mel intent intent=new intent()
b3alb l intent b7aded she esmo setcommponent b7aded l mokawen hay tare2a tenye la na3mel explicit intent ne7na mna3ref bel explicit intent bekoun wade7 l mokawen le badna nsha8lo fa iza rja3na 3al application le fe servies la7 nshof eno fe esm package la hayde l application heda l package bekoun mo2hal bel kemel fully qulified lal servies le 3mlta fa ana best5dem hay l package bet7ded l intent bhay l application fa bjeeb esm l package ta3el heda l app w bjeeb l package ta3el l appservies dot esm l class  le howe 3emel immplement lal servies hay tare2a tenye la na3mel explixit intent bas mn app la app
fa bhay tarw2a 7atyna serves le badna nsha8ela
-hala2 bade 2a3mel 3 methods wa7de lal bind wa7de lal unbind wa7de l fetchrandomnumber mn l servies
private void bindToRemoteservies(){
        //b2lab hay l method best3mel method esma bindservies w beb3tla servies intent w beb3tla l connection le 3mlto ana w flag le howe Bindautocreate
        bindService(serviceIntent,randomNumberServiesConnection,BIND_AUTO_CREATE);
        Toast.makeText(this, "service bound", Toast.LENGTH_SHORT).show();
    }

private void unbindFromRemoteService(){
        //w method l unbindservies la7 tefsele l etesal been l activity w servies mn 5elel method unbind bte5od l connection
        w ba3mel l boolean varible le 3ande false eno unbind
        unbindService(randomNumberServiesConnection);
        mIsBound=false;
    }

hala2 bas 2a3ml bind fa ne7na mnkoun 3am nrou7 n2olo lal ssystem 3mele connect ma3 hay servies le mawjoiude bel application tene le fe servies fa l sytem byestjeb la 2ele w iza 3emel connect sa7  ne7na mnrou7 mna3ml connection ma3 servies fa w servies bta3mel call lal onbind method e btb3tlna l l bind be method l onstartconnection fa ne7na ba3tna l ibind ta3el l meessanger w 7atyne b hay l messanger fa bhay tare2a btkoun 3am twasel l messanger le 3ande taba3 request bel messnager tene w l messanger tene 3am ykoun mawsoul assln  bel handler le howe mas2ol 3an l handle message le btosalne  l messnager le mawjioud bel server  mn l parameter hala2 ne7na 5aslna 2awl part mn l tawselete bheda shakl bte2dar l activity ta3mel connect ma3 servies
hala2 l part l metba2e howe keef l servies bada testjeb elna w teb3tlna l random number w l activity bada te3rdo bel textview
mojarad ma l servies te5od mn l activity message fa t2om b b handle hay l message  w 7a ta3mel respone lal activity b message le heye l activity le talabet l random number bas lezm ya3ref mn ween eje l request heda servies la heek mne7tej la n3ayen 3enwen la "from" bas neb3at l message la heek la n2om be2neshe2 messanger tene b2lab l application le fe l activity le bado yotlob l random number heda l messanger 3ando referance 3ana handler heda l handler wazefto eno bas yestelem l message  from servies sho maken l random number la y7oto b variable w ba3den y7oto b l heda number l revievd from servies to activity  textview
so bas neb3at l message  mn l activity lezm lezm n7et l messanger w l handler b2lab l message iza mnetzakar bel servies application b2lab l code sta3mlna she esmo replyto fa mne7tej la ta3yen heda l messanger  heda le bdawro 3ando refrence  3ala l handler to rply to attribute 5as bel message
ba3d ma na3mel heda l messanger w n5lso la7 ytem l rad back mn 5elel heda l messanger le 2omna bt3yeno bel attribute le esma reply to le 5asa bel message fa 7a ytemtawjeh heda l ra2m l random lal messnager le mawjoud bel activity ba3den la7 test2blo l handler finaly l vlaue berou7 lal activity w byen7at b2lab textview fa hay kela talab le 7a tseer w rad
hala2 la7 nefham aktr mn 5elel l code

hala2 bade 2a3ml class esmo RecieveRandomNumberHandler ened handler w mna3mel override la method le esma handle message le bteb3tlna message bel parameter
b2lab hay l method la7 2a3mel intilize la varible le esm randomnumber valuew 7ot emto zero ba3den bade 2a3ml shart mesh ne7na bas 3am nerja3 neb3t l message 3am neb3at ma3a l variable what le heye emto sefr fa ne7na mn7ol iza l varibale what le b2lab hay l message le bada toslane mn servies keen equll la varible badna na3mlo esmo get_random_number_flag emto zero rou7 ya message jeble l varible le esmo arg1 le bde5el l message sayfo b heda l varible le esmo randomnumbervalue w raj3 7oto b textview
hala2 e5er method heye njeeb l random number mn l servies
hala2 iza rje3na back ne7na 3mlna Messanger esmo randomNumberReceiveMessanger heda l messanger le 7a nest3mlo la na3ml handle lal message le 7a toslna fa heda l handler le 3mlne howe recieve l random number fa bel onservies connected mna3mel meno object la heda l messanger w mna3te l object mn heda l class l handler le 3mlto new Recivednumb..handler
heda l messanger la7 yrou7 yhaye2 heda l handler la yest2bel l message


bedalel e5er method esma fetchRandomnumber le howe mn 5elela bade e7sal 3ala ra2m l radom mn 5elel hay l method la7 t7elel kel l mashekl
hay l method bt2olak iza kenet l activity bound lezm n3ayen 3enwen lala message la hed  la7 ta3mele message b2laba value l what le howe 0 mn 5elel message jehze le heye l obtain() w ta3mele ba3den bteb3at hay l message la servies ba3den la7 test5dem la hay l message attribute esma reply to bt7ot eno howe equll lal randomnumberrecviedMessangeer heda l messanger internal b2albo eshara 3alal l handler le 7a ya3mel handle lal message l mestelme w jalb l ra2m l random w ta3yeno b textview ba3d ma3mlna heek ana mne7tej nersel talab request la njeeb l random number keef
mn 5elel l messanger le esmo randomnumberRequestMessanger heda l messanger w nersel elo message ma heda l messanger howe 3emel connect 3ala l messnager le b2lab l servies method fa enta 3am teb3at message fa tosal lal messnager le b2alb servies w lezm la temshe hay kola lezm l servies le mawjoude 3ande bhedek l app lezm tkoun exported 3ashn yshofoha kel l app le bel telephone
  requestMessage.replyTo=randomNumberReceiveMessenger;:bhay l 7ale ana k2no 3am 2olo iza badak ta3mel reply la message b3tle yeha 3ala heda l messanger ma heye l message b2laba asln messanger fa ana 3am 2olo hay l message 3enwena hyde l messanger


mn l version l android 11 w tlo3 batal ta3mel bound l activity ma3 servie ela iza 7atyna tag bel manifist heda 3arfna l package taba3 l application le fe service le badna na3mel ma3o bound
<queries>
        <package android:name="com.example.service"/>
    </queries>

    hyde 3mloha 3ashn security aktar

    hala2 feek ta3mel more secure tkoun l service eno ma ta3mel bound ela ma3 hay l application mesh ma3 aye application bta3mel method eno iza keen l package l application equll la heda l package bel bound method 3meel bound 3meel retur  8eer heek ma ta3mel return
    






*/






public class MainActivity extends AppCompatActivity {

    TextView Display_randomNB;
    Button bind_servies,unbindservies,getrandom;
    Intent serviceIntent;
    private int randomNumberValue;
    Messenger randomNumberRequestMessenger,randomNumberReceiveMessenger;
    boolean mIsBound;
    ServiceConnection randomNumberServiesConnection;
    public static final int GET_RANDOM_NUMBER_FLAG=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Display_randomNB=findViewById(R.id.diplay_randomnumber);
        bind_servies=findViewById(R.id.bind_servies);
        unbindservies=findViewById(R.id.unbind_servies);
        getrandom=findViewById(R.id.display_btn);
        class ReceiverRandomNumberHandler extends Handler{
            @Override
            public void handleMessage(@NonNull Message msg) {
                randomNumberValue=0;
                if(msg.what==GET_RANDOM_NUMBER_FLAG){
                    randomNumberValue=msg.arg1;
                }
                Display_randomNB.setText("Random Number:"+randomNumberValue);

                super.handleMessage(msg);
            }
        }

        serviceIntent = new Intent();
        serviceIntent.setComponent(new ComponentName("com.example.service","com.example.service.BoundService"));
        serviceIntent.setPackage(getPackageName());
        randomNumberServiesConnection =new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                randomNumberRequestMessenger=new Messenger(service);
                randomNumberReceiveMessenger=new Messenger(new ReceiverRandomNumberHandler());
                mIsBound=true;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                randomNumberRequestMessenger=null;
                randomNumberReceiveMessenger=null;
                mIsBound=false;
            }
        };

        bind_servies.setOnClickListener(v->{
            bindToRemoteservies();
        });

        unbindservies.setOnClickListener(v->{
            unbindFromRemoteService();

        });

        getrandom.setOnClickListener(v->{
            generatedRandomnumber();
        });



    }
    private void bindToRemoteservies(){
        //b2lab hay l method best3mel method esma bindservies w beb3tla servies intent w beb3tla l connection le 3mlto ana w flag le howe Bindautocreate
        bindService(serviceIntent,randomNumberServiesConnection,BIND_AUTO_CREATE);
        Toast.makeText(this, "service bound", Toast.LENGTH_SHORT).show();
    }

    private void unbindFromRemoteService(){
        //w method l unbindservies la7 tefsele l etesal been l activity w servies mn 5elel method unbind bte5od l connection
        if(mIsBound){
            unbindService(randomNumberServiesConnection);
            mIsBound=false;
        }
    }

    private void generatedRandomnumber(){
        if(mIsBound){
            Message requestMessage=Message.obtain(null,GET_RANDOM_NUMBER_FLAG);
            requestMessage.replyTo=randomNumberReceiveMessenger;
            try {
                randomNumberRequestMessenger.send(requestMessage);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }else{
            Toast.makeText(this, "not bounded", Toast.LENGTH_SHORT).show();
        }

    }



}