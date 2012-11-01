package cl.alma.adc.sw.android;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;



public class AlmastatusActivity extends Activity {
	public String steName = "";
	public String acs = "";
	public String almasw = "";
	public HashMap<String, String> antennaPads = new HashMap<String, String>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		final Button buttonaos = (Button) findViewById(R.id.aos);
		buttonaos.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Context ctx = getBaseContext();
				if (isNetworkAvailable(ctx)) {
					try {
						getFromWS("AOS");
						Intent myIntent = new Intent(AlmastatusActivity.this,
								StestatusActivity.class);
						Bundle bundle = fillBundle();
						myIntent.putExtras(bundle);
						AlmastatusActivity.this.startActivity(myIntent);
					} catch (Exception e) {
						Context context = getBaseContext();
						e.printStackTrace();
						Toast toast = Toast.makeText(context,
								"Couldn't connect to server", 20);
						toast.show();
					}
				} else {
					Context context = getBaseContext();
					Toast toast = Toast.makeText(context,
							"Couldn't connect to server", 20);
					toast.show();
				}
			}
		});

		final Button buttontfint = (Button) findViewById(R.id.tfint);
		buttontfint.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Context ctx = getBaseContext();
				if (isNetworkAvailable(ctx)) {
					try {
						getFromWS("TFINT");
						Intent myIntent = new Intent(AlmastatusActivity.this,
								StestatusActivity.class);
						Bundle bundle = fillBundle();
						myIntent.putExtras(bundle);
						AlmastatusActivity.this.startActivity(myIntent);
					} catch (Exception e) {
						Context context = getBaseContext();
						e.printStackTrace();
						Toast toast = Toast.makeText(context,
								"Couldn't connect to server", 20);
						toast.show();
					}
				} else {
					Context context = getBaseContext();
					Toast toast = Toast.makeText(context,
							"Couldn't connect to server", 20);
					toast.show();
				}

			}
		});

		final Button buttontfsd = (Button) findViewById(R.id.tfsd);
		buttontfsd.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Context ctx = getBaseContext();
				if (isNetworkAvailable(ctx)) {

					try {
						getFromWS("TFSD");
						Intent myIntent = new Intent(AlmastatusActivity.this,
								StestatusActivity.class);
						Bundle bundle = fillBundle();
						myIntent.putExtras(bundle);
						AlmastatusActivity.this.startActivity(myIntent);
					} catch (Exception e) {
						Context context = getBaseContext();
						e.printStackTrace();
						Toast toast = Toast.makeText(context,
								"Couldn't connect to server", 20);
						toast.show();
					}
				} else {
					Context context = getBaseContext();
					Toast toast = Toast.makeText(context,
							"Couldn't connect to server", 20);
					toast.show();
				}

			}
		});

		final Button buttontfohg = (Button) findViewById(R.id.tfohg);
		buttontfohg.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Context ctx = getBaseContext();
				if (isNetworkAvailable(ctx)) {

					try {
						getFromWS("TFOHG");
						Intent myIntent = new Intent(AlmastatusActivity.this,
								StestatusActivity.class);
						Bundle bundle = fillBundle();
						myIntent.putExtras(bundle);
						AlmastatusActivity.this.startActivity(myIntent);
					} catch (Exception e) {
						Context context = getBaseContext();
						e.printStackTrace();
						Toast toast = Toast.makeText(context,
								"Couldn't connect to server", 20);
						toast.show();
					}

				} else {
					Context context = getBaseContext();
					Toast toast = Toast.makeText(context,
							"Couldn't connect to server", 20);
					toast.show();
				}

			}
		});

		final Button buttontfeng = (Button) findViewById(R.id.tfeng);
		buttontfeng.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Context ctx = getBaseContext();
				if (isNetworkAvailable(ctx)) {

					try {
						getFromWS("TFENG");
						Intent myIntent = new Intent(AlmastatusActivity.this,
								StestatusActivity.class);
						Bundle bundle = fillBundle();
						myIntent.putExtras(bundle);
						AlmastatusActivity.this.startActivity(myIntent);
					} catch (Exception e) {
						Context context = getBaseContext();
						e.printStackTrace();
						Toast toast = Toast.makeText(context,
								"Couldn't connect to server", 20);
						toast.show();
					}
				} else {
					Context context = getBaseContext();
					Toast toast = Toast.makeText(context,
							"Couldn't connect to server", 20);
					toast.show();
				}

			}
		});

	}

	public Bundle fillBundle() {
		Bundle bundle = new Bundle();
		bundle.putString("steName", steName);
		bundle.putString("acs", acs);
		bundle.putString("almasw", almasw);
		bundle.putSerializable("antennapads", antennaPads);
		return bundle;
	}

	public boolean isNetworkAvailable(Context ctx) {
		ConnectivityManager conMgr = (ConnectivityManager) ctx
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo i = conMgr.getActiveNetworkInfo();
		if (i == null)
			return false;
		if (!i.isConnected())
			return false;
		if (!i.isAvailable())
			return false;
		return true;
	}

	public void getFromWS(String stename) throws IOException,
			XmlPullParserException, ParserConfigurationException, SAXException {

		String METHOD_NAME = "getAntennasInfo";
		String NAMESPACE = "http://vrfs.alma.cl/nusoap/xsd";
		String SOAP_ACTION = NAMESPACE + METHOD_NAME;
		String URL = "http://vrfs.alma.cl/getAntInfoWS.php?wsdl";

		SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
		request.addProperty("STE", stename);

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.dotNet = true;
		envelope.setOutputSoapObject(request);
		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		androidHttpTransport.call(SOAP_ACTION, envelope);
		Object result = envelope.getResponse();
		String xmlResult = result.toString();
		ByteArrayInputStream xml = new ByteArrayInputStream(
				xmlResult.getBytes());
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(xml);

		doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName("antenna");
		NodeList steNodeList = doc.getElementsByTagName("ste");

		Node ste = steNodeList.item(0);
		Element steElement = (Element) ste;
		String acs = steElement.getAttribute("acs");
		String almasw = steElement.getAttribute("alma");

		this.steName = stename;
		this.almasw = almasw;
		this.acs = acs;
		this.antennaPads.clear();

		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				String antennaName = eElement.getNodeValue();
				String pad = eElement.getAttribute("pad");
				antennaPads.put(antennaName.split("-")[0], pad);
			}
		}

	}
}
