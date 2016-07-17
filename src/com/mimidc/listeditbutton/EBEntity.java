package com.mimidc.listeditbutton;

import java.io.Serializable;

import android.widget.EditText;
import android.widget.ImageView;
/**
 * 
 * @author �չ���
 *2015-5-5����6:03:53
 */
public class EBEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6597044423011569132L;
	private ImageView addIV, lessenIV;
	private EditText editText;
	private int id;

	public EBEntity() {
		// TODO Auto-generated constructor stub
	}

	public EBEntity(EditText editText, ImageView addIV,
			ImageView lessenIV) {
		this.addIV = addIV;
		this.lessenIV = lessenIV;
		this.editText = editText;
	}

	public ImageView getAddIV() {
		return addIV;
	}

	public void setAddIV(ImageView addIV) {
		this.addIV = addIV;
	}

	public ImageView getLessenIV() {
		return lessenIV;
	}

	public void setLessenIV(ImageView lessenIV) {
		this.lessenIV = lessenIV;
	}

	public EditText getEditText() {
		return editText;
	}

	public void setEditText(EditText editText) {
		this.editText = editText;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
		editText.setId(id);
		lessenIV.setId(id);
		addIV.setId(id);
	}
	
	
}
