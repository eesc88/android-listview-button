package com.mimidc.listeditbutton;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

/**
 * 
 * @author 苏国�? 2015-5-6下午1:40:31
 */
public class GiftAdapter {

	private ArrayList<String> list=null;
	private Context context=null;
	private TreeMap mp=null;
	private NumCallBack numCallBack=null;
	private LinearLayout ll=null;
	private static boolean haveIM;
	public GiftAdapter(Context context, ArrayList<String> list,LinearLayout ll,boolean haveIM) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.list = list;
		this.mp = new TreeMap<Integer, EBEntity>();
		this.ll=ll;
		this.haveIM=haveIM;
		ll.removeAllViews();
		addView();
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}


	public void addView() {
		for (int i = 0; i < getCount(); i++) {
			ll.addView(getView(i));
		}
	}
	NetworkImageView niv;
	/**
	 * 
	 * @param position
	 * @return child
	 */
	private View getView(int position) {
		//父级相对布局
		RelativeLayout rl=new RelativeLayout(context);
		rl.setBackgroundResource(R.drawable.integal);
		RelativeLayout.LayoutParams paramsRl = new RelativeLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, 110);
		paramsRl.addRule(paramsRl.topMargin, 10);
		
		rl.setLayoutParams(paramsRl);
		rl.setPadding(3, 3, 3, 3);
		
		
		LinearLayout ll=new LinearLayout(context);
		RelativeLayout.LayoutParams paramsLL = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
		ll.setOrientation(LinearLayout.HORIZONTAL);
		ll.setGravity(Gravity.CENTER_VERTICAL);
		paramsLL.rightMargin=3;
		paramsLL.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		ll.setLayoutParams(paramsLL);
		//输入�?
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		EditText et = new EditText(context);
		et.setInputType(InputType.TYPE_CLASS_NUMBER);
		et.setText("0");
		et.setGravity(Gravity.CENTER);
		params.width = 80;
		et.setLayoutParams(params);
		et.addTextChangedListener(new TextWatcher() {

			int beforeNum;
			int afterNum;

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub

			}

			@SuppressLint("NewApi")
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				String nums = s.toString();
				if (nums == null || nums.isEmpty()) {
					beforeNum = 0;
				} else {
					beforeNum = Integer.parseInt(nums);
				}
			}

			@SuppressLint("NewApi")
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				String nums = s.toString();
				if (nums == null || nums.isEmpty()) {
					afterNum = 0;
				} else {
					afterNum = Integer.parseInt(nums);
				}
				if (numCallBack != null) {
					numCallBack.numCallBack(afterNum - beforeNum);
				}
			}
		});
		
		
		
		params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		ImageView addIV = new ImageView(context);
		addIV.setBackgroundResource(R.drawable.add_button);

		params.width = 50;
		params.height = 50;
		addIV.setLayoutParams(params);
		
		//点击按钮
		ImageView lessenIV = new ImageView(context);
		lessenIV.setBackgroundResource(R.drawable.lessen_button);

		lessenIV.setLayoutParams(params);

		addIV.setOnClickListener(new ListOnClickListener(position, 1));
		lessenIV.setOnClickListener(new ListOnClickListener(position, -1));

		EBEntity ebe = new EBEntity(et, addIV, lessenIV);
		ebe.setId(position);
		mp.put(position, ebe);
		ll.removeAllViews();
		ll.addView(ebe.getLessenIV());
		ll.addView(ebe.getEditText());
		ll.addView(ebe.getAddIV());
		rl.addView(ll);
		
		
		//左边图片
		if (haveIM) {
			Log.i("sys", "添加图片�?�?...");
			niv=new NetworkImageView(context);
			RelativeLayout.LayoutParams paramsNTV=new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
			niv.setLayoutParams(paramsNTV);
			//niv.setImageResource(R.drawable.back);
			niv.setImageUrl("http://p2.so.qhimg.com/t014465520bda407637.jpg", new VolleyUtils().getImageLoader(context));
			rl.addView(niv);
		}
	    //左边文字信息
		TextView name=new TextView(context);
		RelativeLayout.LayoutParams paramsName=new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		if (haveIM) {
			//paramsName.addRule(RelativeLayout.RIGHT_OF,niv.getId());
		}
		name.setLayoutParams(paramsName);
		name.setText(list.get(position));
		name.setTextSize(20);
		rl.addView(name);
		
		TextView type=new TextView(context);
		RelativeLayout.LayoutParams paramsType=new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		
		if (haveIM) {
			//paramsType.addRule(RelativeLayout.LEFT_OF,niv.getId());
		}else{
			paramsType.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		}
		paramsType.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		
		paramsType.bottomMargin=0;
		type.setLayoutParams(paramsType);
		type.setText("消�??:"+position+"积分");
		type.setTextColor(context.getResources().getColor(R.color.gift_price_color));
		rl.addView(type);
		return rl;
	}

	/**
	 * 
	 * @author 苏国�? 2015-5-6下午2:07:05
	 */
	class ListOnClickListener implements OnClickListener {
		int position;
		int state = 0;// 1加�??-1�?

		public ListOnClickListener(int position, int state) {
			// TODO Auto-generated constructor stub
			super();
			this.position = position;
			this.state = state;
		}

		@SuppressLint("NewApi")
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			EBEntity ete = (EBEntity) mp.get(position);
			EditText et = ete.getEditText();
			String s = et.getText().toString();
			if (s != null) {

				Integer ig;
				if (s.isEmpty()) {
					ig = 0;
				} else {
					ig = Integer.parseInt(s);
				}
				if (state == 1) {
					et.setText((ig + 1) + "");
				} else if (state == -1) {
					ig = ig - 1;
					ig = ig > 0 ? ig : 0;
					et.setText(ig + "");
				}
			}
		}

	}

	/**
	 * @param numCallBack
	 */
	public void setNumCallBack(NumCallBack numCallBack) {
		this.numCallBack = numCallBack;
	}

	/**
	 * 清除�?有已输入数据
	 */
	public void clearAll() {
		// TODO Auto-generated method stub
		Iterator it = mp.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			EBEntity ete = (EBEntity) entry.getValue();
			ete.getEditText().setText("0");
		}
	}

}
