package com.mimidc.listeditbutton;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	private ArrayList<String> s;
	private LinearLayout ll;
	private Button button;
	private TextView allTV;
	private int allnum;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ll = (LinearLayout) findViewById(R.id.list);

		button = (Button) findViewById(R.id.button);
		allTV = (TextView) findViewById(R.id.allnum);

		s = new ArrayList<String>();
		for (int i = 0; i < 30; i++) {
			s.add("好唔好:" + i);
		}
		GiftAdapter g=new GiftAdapter(this, s, ll, true);
		
		
	}

}
