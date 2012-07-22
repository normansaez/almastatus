package cl.alma.adc.sw.android;
import java.util.HashMap;
import java.util.Map.Entry;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class StestatusActivity extends Activity {    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ste);
        
        Bundle bundle = getIntent().getExtras();

        String steName = bundle.getString("steName");
        String acs = bundle.getString("acs");
        String almasw = bundle.getString("almasw");
        HashMap<String, String> antennaPads = (HashMap<String, String>) bundle.getSerializable("antennapads");
        
    
        TextView tv = (TextView) findViewById(R.id.ste);  
        
        tv.setText("\nSTE: " +steName);
        
        TextView tv2 = (TextView) findViewById(R.id.acs);    
        tv2.setText("Release: "+ acs);
        
        TextView tv3 = (TextView) findViewById(R.id.almasw);    
        tv3.setText("Build: " + almasw);
   
        TextView tv4 = (TextView) findViewById(R.id.num);    
        tv4.setText("Antennas configured: "+antennaPads.size());
       // tv4.setText("Antennas configured: ");
        
        String padtoshow = "\nAntenna  \t->\t\tPad\n";
        for (Entry<String, String> antennaiditerator: antennaPads.entrySet()){
        	padtoshow += antennaiditerator.getKey() + "  \t\t->\t\t" + antennaiditerator.getValue() +"\n";
			}
        
        TextView tv5 = (TextView) findViewById(R.id.pads);    
        tv5.setText(padtoshow);
        
		}
}
